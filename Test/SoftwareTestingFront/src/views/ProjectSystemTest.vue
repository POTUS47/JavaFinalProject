<template>
  <div class="main">
    <div class="title">系统测试</div>
    <button class="testBtn" @click="handleTestClick">启动Selenium自动化测试</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';

function handleTestClick() {
    runSideTest();
}

async function runSideTest() {
  try {
    const response = await fetch('/api/run-side-test', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ 
        testPath: '/systemTest/systemTest.side' 
      })
    });
    const result = await response.json();
    console.log('测试结果:', result);
  } catch (error) {
    console.error('测试执行失败:', error);
  }
}

</script>

<style scoped>
.main {
  height: calc(100vh - 40px);
  width: calc(100% - 40px);
  margin: 20px;
  padding: 20px;
  background-color: #fff;
  border: 1px solid #e5e5e5;
  border-radius: 6px;
  overflow-x: hidden;
  overflow-y: auto;
}

.title {
  font-size: 24px;
  font-weight: bold;
  color: #252525;
  margin-bottom: 10px;
}
</style> 