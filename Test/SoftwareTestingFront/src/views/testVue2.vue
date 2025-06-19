<template>
  <div class="top-container">
    <button @click="routeBack()"> < </button>
    <span>测试Vue2</span>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router';

const route = useRoute()
console.log(route.params.id)

const router = useRouter()
const routeBack = () => {
  router.push({ path: '/project/test' })
}

</script>

<style scoped>
.top-container{
  width: 100%;
  height: 40px;
  border-bottom: 1px solid #ddd;
}

.top-container span{
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
</style>