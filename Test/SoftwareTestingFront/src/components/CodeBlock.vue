<template>
  <pre><code :class="`language-${language}`" ref="codeRef">{{ code || '' }}</code></pre>
</template>

<script setup>
import { onMounted, onUpdated, ref, watch, nextTick } from 'vue';
import hljs from 'highlight.js';
import 'highlight.js/styles/github.css';


const props = defineProps({
  code: {
    type: String,
    required: true,
    default: ''
  },
  language: {
    type: String,
    default: 'javascript'
  }
});

const codeRef = ref();

const highlight = () => {
  if (codeRef.value) {
    // ！！！重要：删除上一次的高亮标记；否则会导致变化后的代码没有高亮样式
    delete codeRef.value.dataset.highlighted;
    // 重新高亮
    hljs.highlightElement(codeRef.value);
  }
};

onMounted(() => {
  highlight();
});

onUpdated(() => {
  highlight();
});
</script>

<style scoped>
pre {
  font-family: 'JetBrains Mono', monospace;
  line-height: 1.5;
  padding: 5px;
  overflow-x: auto;
  border: 1px solid #e5e5e5;
  border-radius: 6px;
  margin: 10px 0;
  margin-bottom: 20px;
}

code {
  font-family: 'JetBrains Mono', monospace;
  white-space: pre;
  word-wrap: normal;
}
</style>
