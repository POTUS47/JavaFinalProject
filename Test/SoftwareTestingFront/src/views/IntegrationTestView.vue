<template>
  <div class="main">
    <div class="top-container">
      <button @click="routeBack">
        <svg 
          width="18" 
          height="18" 
          viewBox="0 0 24 24" 
          fill="none" 
          stroke="currentColor" 
          stroke-width="2" 
          stroke-linecap="round" 
          stroke-linejoin="round"
        >
          <path d="M19 12H5M12 19l-7-7 7-7"/>
        </svg>
      </button>
      <div class="title1">{{ testApiName }}</div>
    </div>

    <div class="main-container">
      <div class="up-container">
        <div class="title2">测试接口</div>
        <div class="info">
          <div class="description-text"> 接口描述：{{ testData?.description }}</div>
        </div>

        <div class="select-container">
          <div class="select-group">
            <label>选择代码版本：</label>
            <el-select
              v-model="selectedVersion"
              placeholder="Select Version"
              class="custom-select"
            >
              <el-option v-for="version in testApiVersionList" :key="version" :value="version">
                {{ version }}
              </el-option>
            </el-select>
          </div>

          <button class="testBtn" @click="handleTestClick" :disabled="isLoading">测试</button>
        </div>
      </div>

      <div>
        <div v-if="isLoading">正在加载测试结果...</div>

        <div v-if="showResult" class="down-container">
          <div class="test-case">
            <div class="title3">测试用例及结果</div>
            <TestResults :tableData="tableData" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { ElSelect, ElOption, ElNotification } from 'element-plus'
import Papa from 'papaparse'
import TestResults from '../components/ProjectTestResults.vue'

const route = useRoute()
const testApiId = route.params.id

const testData = ref(null)
const testApiName = ref('')
const testApiVersionList = ref([])

const selectedVersion = ref('v1') // 默认版本
const showResult = ref(false)
const isLoading = ref(false)

// 用例测试结果
const tableData = ref([])

// 获取测试对象数据
const fetchTestData = async () => {
  try {
    const response = await fetch('/integrationTest/testObjectData.json')
    const data = await response.json()
    
    if (data[testApiId]) {
      testData.value = data[testApiId]
      testApiName.value = data[testApiId].name
      testApiVersionList.value = data[testApiId].version || ["v1"]
    }
    console.log('testData:', testData.value);
    
  } catch (error) {
    console.error('获取测试对象数据失败:', error)
  }
}

// 获取测试结果
const loadCaseResult = async () => {
  if (testApiId) {
    const filePath = `/integrationTest/${testApiId}_${selectedVersion.value}.csv`;
    console.log(filePath)
    
    try {
      const res = await fetch(filePath);

      if (!res.ok) {
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
      throw new Error('读取用例文件失败:', err);
    }
  }
};

const handleTestClick = () => {
  showResult.value = false
  isLoading.value = true

  setTimeout(async () => {
    await loadCaseResult()
      .then(() => {
        ElNotification({
          title: '以显示测试结果',
          message: '可以下滑查看测试用例、结果和代码记录',
          type: 'success',
          showClose: false,
          offset: 40,
        })
        showResult.value = true
        isLoading.value = false
      })
      .catch((error) => {
        ElNotification({
          title: '加载失败',
          message: '无法加载测试结果，请稍后重试',
          type: 'error',
          showClose: false,
          offset: 40,
        })
        showResult.value = false
        isLoading.value = false
      })
    
  }, 1000)
}

onMounted(() => {
  fetchTestData()
})


const router = useRouter()
const routeBack = () => {
  router.push({ path: '/project/integration' })
}

</script>

<style scoped>
.main{
  padding: 0 20px;
  padding-bottom: 20px;
}

.top-container{
  position: absolute;
  display: flex;
  width: 100%;
  height: 60px;
  background-color: #fff;
  border-bottom: 1px solid #ddd;
  z-index: 1000;
}

.main-container{
  margin: 10px 20px;
  margin-top: 70px;
  min-height: calc(100vh - 110px);
  display: flex;
  flex-direction: column;
}

.title1{
  font-size: 1.2rem;
  font-weight: bold;
  color: #252525;
  margin-left: 10px;
  line-height: 60px;
}

.title2{
  font-size: 1.2rem;
  font-weight: bold;
  color: #252525;
  margin-bottom: 10px;
}

.info {
  margin-bottom: 10px;
  margin-left: 10px;
}

.description-text{
  color: #252525;
  font-size: 1rem;
}

.top-container button{
  padding: 3px 13px;
  background-color: transparent;
  color: #252525;
  cursor: pointer;
  border: none;
  font-size: 1.2rem;
  font-weight: bold;
}

.select-container{
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
  align-items: center;
  flex-wrap: wrap;
  margin-left: 10px;
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

.testBtn {
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

.testBtn:hover {
  background-color: #1a1a1a;
}

.testBtn:disabled {
  background-color: #888;
  color: #eee;
  cursor: not-allowed;
}

.down-container{
  border: 1px solid #e5e5e5;
  border-radius: 10px;
  margin-top: 20px;
  height: auto;
  flex: 1;
  padding: 10px 20px;
}

.title3{
  font-size: 1.3rem;
  font-weight: bold;
  margin: 10px 0;
  color: #252525;
}
</style>