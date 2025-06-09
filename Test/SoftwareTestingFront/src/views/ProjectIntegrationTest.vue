<template>
  <div class="project-integration-test-main">
    <p>集成测试页面</p>
    <StructurGraph />
  </div>
</template>

<script setup>
import StructurGraph from '@/components/StructureGraph/StructurGraph.vue';
</script>

<style scoped>
.project-integration-test-main {
  padding: 20px;
  height: calc(100% - 40px);
  width: calc(100% - 40px);
  background-color: #f5f5f5;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style> 