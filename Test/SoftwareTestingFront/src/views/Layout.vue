<template>
  <div class="layout">
    <div class="sidebar">
      <!-- <h2>测试工具</h2> -->
      <h2>Software Testing</h2>
      <div class="menu">
        <div class="menu-group">
          <h3>练习测试</h3>
          <router-link 
            v-for="exercise in exercises" 
            :key="exercise.id"
            :to="`/exercise/${exercise.id}`"
          >
            {{ exercise.title }}
          </router-link>
        </div>
        <div class="menu-group">
          <h3>项目测试</h3>
          <router-link to="/project/unit">单元测试</router-link>
          <router-link to="/project/integration">集成测试</router-link>
          <router-link to="/project/system">系统测试</router-link>
          <router-link to="/project/pressure">压力测试</router-link>
          <!-- <router-link to="/project/test">测试</router-link> -->
        </div>
      </div>
    </div>
    <div class="main">
      <router-view></router-view>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const exercises = ref([])

onMounted(async () => {
  try {
    const response = await fetch('/exercises/list.json')
    const data = await response.json()
    exercises.value = data.exercises
  } catch (error) {
    console.error('加载练习列表失败:', error)
  }
})
</script>

<style scoped>
.layout {
  position: absolute;
  left: 0;
  display: flex;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
}

.sidebar {
  width: 250px;
  min-width: 250px;
  background-color: #f5f5f5;
  padding: 20px;
  border-right: 1px solid #ddd;
  overflow-y: auto;
  height: 100%;
}

.sidebar h2 {
  color: #252525;
  font-weight: bold;
  margin-bottom: 10px;
}

.menu-group {
  margin-bottom: 20px;
}

.menu-group h3 {
  margin-bottom: 10px;
  color: #252525;
  font-size: 16px;
  font-weight: bold;
}

.menu-group a {
  display: block;
  padding: 8px 12px;
  color: #252525;
  text-decoration: none;
  border-radius: 4px;
  margin-bottom: 4px;
  transition: all 0.3s ease;
}

.menu-group a:hover {
  background-color: #dddddd8a;
}

.menu-group a.router-link-active {
  background-color: #dddddd8a;
  font-weight: bold;
}

.main {
  flex: 1;
  overflow-y: auto;
  height: 100%;
  background-color: #fff;
}
</style> 