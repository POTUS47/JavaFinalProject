<template>
  <div class="main-container">
    <StructureGraph />
    <div>
      <button class="jump-button" @click="jumpToExternalAPI">第三方接口</button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import StructureGraph from "@/components/StructureGraph/StructureGraph.vue";

const router = useRouter();

const jumpToExternalAPI = () => {
  router.push({ path: '/project/integration/external-api' })
}
</script>

<style scoped>
.main-container {
  display: flex;
}

.jump-button {
  background-color: #252525;
  margin: 20px;
  border: none;
  color: white;
  padding: 5px 24px;
  border-radius: 4px;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
}

.jump-button:hover {
  background-color: #1a1a1a;
}

.jump-button:disabled {
  background-color: #888;
  color: #eee;
  cursor: not-allowed;
}
</style>