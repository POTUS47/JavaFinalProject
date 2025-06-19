<template>
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
  <div>
    <div class="down-container">
      <div class="test-case">
        <div class="title2">测试用例及结果</div>
        <TestResults :tableData="tableData" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import Papa from 'papaparse'
import TestResults from '../components/ProjectTestResults.vue'

const route = useRoute()
const testApiId = route.params.id

const testData = ref(null)
const testApiName = ref('')

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
    }
  } catch (error) {
    console.error('获取测试对象数据失败:', error)
  }
}

// 获取测试结果
const loadCaseResult = async () => {
  if (testApiId) {
    const filePath = `/integrationTest/${testApiId}.csv`;
    // console.log(filePath)
    
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
    }
  }
};

onMounted(() => {
  fetchTestData()
  loadCaseResult()
})


const router = useRouter()
const routeBack = () => {
  router.push({ path: '/project/integration' })
}

</script>

<style scoped>
.top-container{
  display: flex;
  width: 100%;
  height: 40px;
  border-bottom: 1px solid #ddd;
}

.title1{
  font-size: 1.2rem;
  font-weight: bold;
  color: #252525;
  margin-left: 10px;
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

.down-container{
  border: 1px solid #e5e5e5;
  border-radius: 10px;
  margin-top: 20px;
  height: auto;
  flex: 1;
  padding: 10px 20px;
}

.title2{
  font-size: 1.3rem;
  font-weight: bold;
  margin: 10px 0;
  color: #252525;
}
</style>