<template>
  <div class="tabs-container">
    <el-tabs v-model="selectedClass" class="tabs" @tab-click="handleTabsClick">
      <el-tab-pane 
        v-for="className in dataList.classList"
        :key="className"
        :name="className"
        :label="className"
      >
        <div class="class-container">
          <div class="class-content-up">
            <div class="title">测试对象选择</div>
            <div class="select-container">
              <div class="select-group">
                <label>选择测试函数：</label>
                <el-select
                  v-model="selectedFunc"
                  placeholder="Select Func"
                  class="custom-select"
                >
                  <el-option v-for="func in dataList.funcList" :key="func" :value="func">
                    {{ func }}
                  </el-option>
                </el-select>
              </div>

              <div class="select-group">
                <label>选择代码版本：</label>
                <el-select
                  v-model="selectedVersion"
                  placeholder="Select Version"
                  class="custom-select"
                >
                  <el-option v-for="version in dataList.versionList" :key="version" :value="version">
                    {{ version }}
                  </el-option>
                </el-select>
              </div>

              <button @click="handleTestClick" :disabled="isLoading">测试</button>
            </div>
          </div>

          <div>
            <!-- <div class="test-case">
              <div class="title">测试用例及结果</div>
              <TestResults :tableData="tableData" />
            </div>
            <div class="code">
              <div class="title">代码记录</div>
              <CodeBlock :code="currentCode || ''" :language="language" />
            </div> -->
            <div v-if="isLoading">正在加载测试结果...</div>

            <div v-if="showResult" class="class-content-down">
              <div class="test-case">
                <div class="title">测试用例及结果</div>
                <TestResults :tableData="tableData" />
              </div>
              <div class="code">
                <div class="title">代码记录</div>
                <CodeBlock :code="currentCode || ''" :language="language" />
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script setup>
import { ref, reactive, watch, onMounted } from 'vue'
import { ElTabs, ElTabPane, ElSelect, ElOption, ElNotification } from 'element-plus'
import Papa from 'papaparse'
import CodeBlock from '../components/CodeBlock.vue'
import TestResults from '../components/ProjectTestResults.vue'

// 获取的单元测试基本数据
const testObjectsData = ref({})

// 目前可选的类、函数、版本列表
const dataList = reactive({
  classList: [],
  funcList: [],
  versionList: []
})

// 目前选择的类、函数、版本列表
const selectedClass = ref('')
const selectedFunc = ref('')
const selectedVersion = ref('')

// 目前选择的对应版本的测试结果、代码
const currentCode = ref('')
const language = ref('java')
const tableData = ref([])

// 加载单元测试基本数据
const loadTestObjectsData = async () => {
  try {
    // 加载基本数据
    const testObjectsResponse = await fetch(`/unitTest/testObjectsData.json`)
    testObjectsData.value = await testObjectsResponse.json()
    // console.log(testObjectsData.value);
    
    // 获取可选类列表
    dataList.classList = Object.keys(testObjectsData.value)

    // 默认选择的类
    if (dataList.classList.length) {
      selectedClass.value = dataList.classList[0]
    }

  } catch (error) {
    console.error('加载单元测试数据失败:', error)
  }
}

// 切换类的响应函数
const handleTabsClick = (tab) => {
  // console.log('点击了：', tab.props.name)
  selectedClass.value = tab.props.key
}

// 获取当前版本代码
const loadCode = async (className, funcName, version) => {
  if (className && funcName && version){
    const filePath = `/unitTest/${className}/${funcName}/${version}/${funcName}_${version}.java`;
    console.log(filePath)
    try {
      const res = await fetch(filePath);
      
      if (!res.ok){
        throw new Error('加载失败');
      }
      currentCode.value = await res.text();
      if (currentCode.value.includes('<!DOCTYPE html>')) {
        currentCode.value = '// 文件读取失败';
        throw new Error('加载失败');
      }
    } catch (err) {
      console.error('读取代码文件失败:', err);
    }
  }
};

// 获取当前版本测试结果
const loadCaseResult = async (className, funcName, version) => {
  if (className && funcName && version){
    const filePath = `/unitTest/${className}/${funcName}/${version}/${funcName}_${version}.csv`;
    // console.log(filePath)
    
    try {
      const res = await fetch(filePath);
      
      if (!res.ok){
        throw new Error('加载失败');
      }
      const csvText = await res.text()

      if (csvText.includes('<!DOCTYPE html>')) {
        tableData.value = [];
        throw new Error('加载失败');
      }

      const csvData = Papa.parse(csvText, {
        header: true,
        skipEmptyLines: true,
      })
      tableData.value = csvData.data
      console.log(tableData.value);
    } catch (err) {
      console.error('读取用例文件失败:', err);
    }
  }
};

// 监听选择的类的变化，更改类下的函数默认选择、可选函数列表
watch(
  selectedClass,
  (className) => {
    if (className && dataList.classList.includes(className)){
      dataList.funcList = Object.keys(testObjectsData.value[className])
      selectedFunc.value = dataList.funcList[0]
    }
  },
  { immediate: true }
)

// 监听选择的函数的变化，更改函数下的可选版本列表、默认版本
watch(
  selectedFunc,
  (func) => {
    if (func && dataList.funcList.includes(func)){
      dataList.versionList = testObjectsData.value[selectedClass.value][func]
      selectedVersion.value = dataList.versionList[0]
    }
  },
  { immediate: true }
)

// watch(
//   [selectedClass, selectedFunc, selectedVersion],
//   ([selectedClass, selectedFunc, selectedVersion]) => {
//     loadCaseResult(selectedClass, selectedFunc, selectedVersion)
//     loadCode(selectedClass, selectedFunc, selectedVersion)
//   },
//   { immediate: true }
// )

const showResult = ref(false)
const isLoading = ref(false)
const handleTestClick = () => {
  showResult.value = false
  isLoading.value = true

  setTimeout(async () => {
    await loadCaseResult(selectedClass.value, selectedFunc.value, selectedVersion.value)
    await loadCode(selectedClass.value, selectedFunc.value, selectedVersion.value)
    ElNotification({
      title: '该函数单元测试完成!',
      message: '可以下滑查看测试用例、结果和代码记录',
      type: 'success',
      showClose: false,
      offset: 40,
    })
    showResult.value = true
    isLoading.value = false
  }, 1000)
}

onMounted(() => {
  loadTestObjectsData()
})


</script>

<style scoped>
.tabs{
  --el-tabs-header-height: 50px;
  --el-color-primary: #252525;
}

.tabs ::v-deep(.el-tabs__item) {
  padding: 12px 24px;
  font-size: 1.2rem;
  font-weight: bold;
}

.class-container{
  margin: 10px 20px;
  min-height: calc(100vh - 110px);
  display: flex;
  flex-direction: column;
}

.title{
  font-size: 1.3rem;
  font-weight: bold;
  margin: 10px 0;
  color: #252525;
}

.select-container{
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
  align-items: center;
  flex-wrap: wrap;
}

.select-group {
  display: flex;
  align-items: center;
  gap: 10px;
}

.select-group label {
  color: #666;
  font-size: 1rem;
}

.custom-select{
  width: 240px;
}

.class-content-down{
  border: 1px solid #e5e5e5;
  border-radius: 10px;
  height: auto;
  flex: 1;
  padding: 10px 20px;
}

button {
  background-color: #252525;
  border: none;
  color: white;
  padding: 5px 24px;
  border-radius: 4px;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #1a1a1a;
}

button:disabled {
  background-color: #888;
  color: #eee;
  cursor: not-allowed;
}
</style> 