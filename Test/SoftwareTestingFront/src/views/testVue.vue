<template>
  <div class="project-integration-test-main">
    <StructureGraph />
  </div>
  <div>
    <button @click="clickFunc('1')">测试按钮1-跳转API1</button>
    <button @click="clickFunc('2')">测试按钮2-跳转API2</button>
    <button @click="clickFunc('3')">测试按钮3-跳转API3</button>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import StructureGraph from "@/components/StructureGraph/StructureGraph.vue";

const router = useRouter();

const clickFunc = (id) => {
  router.push({ path: `test2/${id}` })
}
</script>


<!-- <template>
  <div class="main-container">
    <div class="tabs-container">
      <router-link to="/project/integration/structure">结构图</router-link>
      <router-link 
        v-for="testAPI in testAPIs" 
        :key="testAPI.id"
        :to="`/project/integration/${testAPI.id}`"
      >
        {{ testAPI.title }}
      </router-link>
    </div>
    <div class="main-content">
      <router-view></router-view>
    </div>
  </div>
</template> -->

<!-- <script setup>
import { ref } from 'vue'

const testAPIs = ref([
  {id: "API1", title: "API1_name"},
  {id: "API2", title: "API2_name"},
  {id: "API3", title: "API3_name"},
])
</script> -->

<!-- <style scoped>
.tabs-container{
  display: flex;
  height: 50px;
  border-bottom: 1px solid #ddd;
}

.tabs-container a {
  margin-right: 25px;
  font-size: 1.2rem;
  font-weight: bold;
  color: #252525;
  text-decoration: none;
  /* transition: all 0.3s ease; */
}

.tabs-container a.router-link-active {
  border-bottom: 2px solid #252525;
}

.tabs-container a:hover {
  background-color: #fff;
}

.main-content{
  background-color: #fff;
}
</style> -->