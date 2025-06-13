<template>
  <div>
    单元测试页面
    <el-button type="primary">Primary</el-button>
    <CodeBlock :code="code" :language="language" />
  </div>
</template>

<script setup>
import { ElButton } from 'element-plus'
import { ref, onMounted } from 'vue';
import CodeBlock from '../components/CodeBlock.vue'

const code = ref('');
const language = ref('java');

// 动态路径，可从选择器绑定变量构造
const filePath = '/unitTest/orderItemClass/addOrderItem/v1/code.java';

const loadCode = async () => {
  try {
    const res = await fetch(filePath);
    if (!res.ok) throw new Error('加载失败');
    code.value = await res.text();
  } catch (err) {
    console.error('读取代码文件失败:', err);
    code.value = '// 文件读取失败';
  }
};

// 组件挂载后加载一次
onMounted(loadCode);

</script>

<style scoped>
.el-button--primary {
  background-color: #252525; /* 主颜色 */
  border-color: #252525; /* 边框颜色 */
}
</style> 