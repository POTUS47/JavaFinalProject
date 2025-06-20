<template>
    <div class="main">
        <div class="title">登录功能压力测试报告</div>
        <div class="select-group">
          <label>测试用例类别：</label>
          <select v-model="selectedVersion">
            <option v-for="version in testVersion" :key="version.id" :value="version.id">
              {{ version.name }}
            </option>
          </select>
        </div>
        <div class="iframe-wrapper">
            <iframe :src="selectedUrl"
                frameborder="0"
                width="100%"
                height="1000px"
                @load="resizeIframe"
                >
            </iframe>
        </div>
    </div>
</template>

<script setup>
import { computed, ref } from 'vue'

const selectedVersion = ref(1) // 默认选择第一个版本
const testVersion = ref([
    { id: 1, name: '20并发测试版本', url: '/pressureTest/report20/index.html' },
    { id: 2, name: '25并发测试版本', url: '/pressureTest/report25/index.html' },
    { id: 3, name: '30并发测试版本', url: '/pressureTest/report30/index.html' },
    { id: 4, name: '35并发测试版本', url: '/pressureTest/report35/index.html' },
    { id: 5, name: '40并发测试版本', url: '/pressureTest/report40/index.html' },
    { id: 6, name: '45并发测试版本', url: '/pressureTest/report45/index.html' },
    { id: 7, name: '50并发测试版本', url: '/pressureTest/report50/index.html' }
])
const selectedUrl = computed(() => {
    const version = testVersion.value.find(v => v.id === selectedVersion.value)
    return version ? version.url : ''
})

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

.select-group {
  display: flex;
  align-items: center;
  margin: 15px 0;
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

.iframe-wrapper{
    width: 100%;
    height: calc(100% - 110px);
    overflow: auto;
}

iframe {
  width: 100%;
  height: 100%; /* 填满容器 */
  border: none;
  display: block;
}
</style>