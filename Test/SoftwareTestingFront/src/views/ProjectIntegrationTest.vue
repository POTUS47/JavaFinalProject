<template>
  <div class="project-integration-test-main">
    <StructureGraph />
  </div>
</template>

<script setup>
import StructureGraph from "@/components/StructureGraph/StructureGraph.vue";
</script>

<style scoped>
.project-integration-test-main {
  padding: 20px;
  min-height: calc(100vh - 40px);
  width: 100%;
  background-color: #f5f5f5;
  border-radius: 8px;
  overflow: auto;
  box-sizing: border-box;
}
</style>
