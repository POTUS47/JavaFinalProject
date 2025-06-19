<template>
  <div class="popover-card">
    <button class="close-btn" @click="$emit('close')">
      <svg
        t="1749535311559"
        viewBox="0 0 1024 1024"
        version="1.1"
        xmlns="http://www.w3.org/2000/svg"
        p-id="1465"
        width="200"
        height="200"
        class="icon"
      >
        <path
          d="M557.312 513.248l265.28-263.904c12.544-12.48 12.608-32.704 0.128-45.248-12.512-12.576-32.704-12.608-45.248-0.128l-265.344 263.936-263.04-263.84C236.64 191.584 216.384 191.52 203.84 204 191.328 216.48 191.296 236.736 203.776 249.28l262.976 263.776L201.6 776.8c-12.544 12.48-12.608 32.704-0.128 45.248 6.24 6.272 14.464 9.44 22.688 9.44 8.16 0 16.32-3.104 22.56-9.312l265.216-263.808 265.44 266.24c6.24 6.272 14.432 9.408 22.656 9.408 8.192 0 16.352-3.136 22.592-9.344 12.512-12.48 12.544-32.704 0.064-45.248L557.312 513.248z"
          p-id="1466"
        ></path>
      </svg>
    </button>
    <h3 class="popover-title">{{ props.popoverTitle || '功能列表' }}</h3>
    <div class="items-container">
      <div 
        v-for="item in props.items" 
        :key="item.id" 
        class="item-row"
      >
        <span class="item-name">{{ item.name }}</span>
        <button 
          class="jump-btn" 
          @click="handleJump(item.id)"
        >
          跳转测试详情
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
defineEmits(["close"]);

const props = defineProps({
  popoverTitle: {
    type: String,
    default: "功能列表"
  },
  items: {
    type: Array,
    default: () => []
  }
});

function handleJump(id) {
  router.push({ path: `/project/integration/${id}` })
}
</script>

<style scoped>
.popover-card {
  background-color: rgba(255, 255, 255, 0.7);
  backdrop-filter: blur(10px);
  min-width: 30%;
  max-height: 60%;
  overflow-y: auto;
  border: 1px solid #ccc;
  border-radius: 8px 8px 0 0;
  padding: 40px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  z-index: 1000;
  align-self: flex-end;
  pointer-events: all;
  position: relative;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  padding: 10px;
  height: 35px;
  width: 50px;
  background: none;
  border: none;
  cursor: pointer;
  transition: background-color 0.15s ease;
}

.close-btn:hover {
  background-color: rgba(0, 0, 0, 0.1);
}

.icon {
  width: 100%;
  height: 100%;
}

.popover-title {
  margin: 0 0 20px 0;
  font-size: 18px;
  font-weight: bold;
  color: #333;
  text-align: center;
}

.items-container {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.item-row {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 12px;
  background-color: rgba(255, 255, 255, 0.8);
  border-radius: 6px;
  border: 1px solid #e0e0e0;
  transition: background-color 0.2s ease;
}

.item-row:hover {
  background-color: rgba(255, 255, 255, 0.95);
}

.item-name {
  font-size: 14px;
  color: #333;
  font-weight: 500;
}

.jump-btn {
  padding: 6px 12px;
  background-color: #252525;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 12px;
  transition: background-color 0.2s ease;
}

.jump-btn:hover {
  background-color: #1a1a1a;
}
</style>
