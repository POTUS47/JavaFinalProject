<template>
  <div class="exercise-test">
    <div class="exercise-info">
      <h1> 练习题目：{{ exercise?.title }}</h1>
      <div class="description-text"> 问题描述：{{ exercise?.description }}</div>
    </div>

    <div class="test-controls">
      <div class="select-group">
        <label>测试用例类别：</label>
        <select v-model="selectedTestCaseType">
          <option v-for="type in testCaseTypes" :key="type.id" :value="type.id">
            {{ type.name }}
          </option>
        </select>
      </div>

      <div class="select-group">
        <label>代码版本：</label>
        <select v-model="selectedCodeVersion">
          <option v-for="version in exercise?.codeVersions" :key="version.id" :value="version.id">
            {{ version.name }}
          </option>
        </select>
      </div>

      <button @click="runTest" :disabled="!canRunTest">开始测试</button>
    </div>

    <div class="code-section">
      <div class="title-2">测试代码</div>
      <CodeBlock 
        :code="currentCodeVersion?.code || ''" 
        language="javascript" 
      />
    </div>
    
    <TestResults v-if="testResults.length" :results="testResults" />
  </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useRoute } from 'vue-router'
import Papa from 'papaparse'
import CodeBlock from '../components/CodeBlock.vue'
import TestResults from '../components/TestResults.vue'

const route = useRoute()
const exercise = ref(null)
const testCaseTypes = ref([])
const testResults = ref([])

// 目前选择的用例版本和代码版本id
const selectedTestCaseType = ref('')
const selectedCodeVersion = ref('')

// 目前选择的用例版本和代码版本对象
const currentTestCaseType = computed(() => {
  return testCaseTypes.value.find(t => t.id === selectedTestCaseType.value)
})
const currentCodeVersion = computed(() => {
  return exercise.value?.codeVersions.find(v => v.id === selectedCodeVersion.value)
})

// 用于判断能否进行测试
const canRunTest = computed(() => {
  return selectedTestCaseType.value && selectedCodeVersion.value
})

// csv格式用例数据转化为按用例类别分类的用例数组
const convertCSVToTestCaseTypes = (rawData) => {
  const groupedData = []

  const groupMap = new Map()

  rawData.forEach(item => {
    const { type_id, type_name, case_id, input, expected, notes } = item
    const parsedInput = JSON.parse(input) // 将字符串 "[3,4,5]" 转为数组 [3, 4, 5]

    if (!groupMap.has(type_id)) {
      const newGroup = {
        id: type_id,
        name: type_name,
        cases: []
      }
      groupMap.set(type_id, newGroup)
      groupedData.push(newGroup)
    }

    groupMap.get(type_id).cases.push({
      id: case_id,
      input: parsedInput,
      expected: expected,
      notes: notes
    })
  })

  return groupedData;
}

// 加载练习数据的函数
const loadExerciseData = async () => {
  try {
    // 加载练习基本信息
    const exerciseResponse = await fetch(`/src/assets/exercises/${route.params.id}.json`)
    exercise.value = await exerciseResponse.json()
    
    // 加载测试用例
    const testCasesResponse = await fetch(`/src/assets/exercises/testcases/${route.params.id}.csv`)    
    const csvText = await testCasesResponse.text()
    const csvData = Papa.parse(csvText, {
      header: true,
      skipEmptyLines: true,
    })
    testCaseTypes.value = convertCSVToTestCaseTypes(csvData.data)
    console.log(testCaseTypes.value);
    
    // 默认选择的测试用例类型及代码版本
    if (testCaseTypes.value.length) {
      selectedTestCaseType.value = testCaseTypes.value[0].id
    }
    if (exercise.value.codeVersions.length) {
      selectedCodeVersion.value = exercise.value.codeVersions[0].id
    }
  } catch (error) {
    console.error('加载练习数据失败:', error)
  }
}

// 监听路由参数变化
watch(
  () => route.params.id,
  () => {
    loadExerciseData()
  }
)

onMounted(() => {
  loadExerciseData()
})

const runTest = () => {
  // 创建函数执行环境
  const testFunction = new Function('return ' + currentCodeVersion.value.code)()
  
  testResults.value = currentTestCaseType.value.cases.map(testCase => {
    try {
      // 执行测试代码
      const actual = testFunction(...testCase.input)
      return {
        id: testCase.id,
        input: JSON.stringify(testCase.input),
        expected: testCase.expected,
        actual: actual,
        passed: actual === testCase.expected,
        notes: testCase.notes ? testCase.notes : '-'
      }
    } catch (error) {
      return {
        id: testCase.id,
        input: JSON.stringify(testCase.input),
        expected: testCase.expected,
        actual: `执行错误: ${error.message}`,
        passed: false,
        notes: testCase.notes ? testCase.notes : '-'
      }
    }
  })
}
</script>

<style scoped>
.exercise-test {
  min-height: 100%;
  width: 100%;
  padding: 20px;
  background-color: #fff;
  padding: 25px;
  border: 1px solid #e5e5e5;
  border-radius: 6px;
}

.exercise-info {
  margin-bottom: 30px;
}

.exercise-info h1 {
  font-size: 24px;
  font-weight: bold;
  color: #252525;
  margin-bottom: 10px;
}

.description-text{
  color: #252525;
  font-size: 1rem;
}

.test-controls {
  display: flex;
  gap: 20px;
  margin-bottom: 30px;
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

.select-group select {
  padding: 8px 12px;
  border-radius: 4px;
  border: 1px solid #dcdfe6;
  background-color: #fff;
  color: #333;
  min-width: 200px;
}

.select-group select:focus {
  outline: none;
  border-color: #252525;
}

/* .code-section{
  width: 80%;
} */

.title-2{
  font-size: 20px;
  font-weight: bold;
}

.test-controls button {
  padding: 8px 20px;
  background-color: #252525;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.test-controls button:hover:not(:disabled) {
  background-color: #585858;
}

.test-controls button:disabled {
  background-color: #909090;
  cursor: not-allowed;
}

</style> 