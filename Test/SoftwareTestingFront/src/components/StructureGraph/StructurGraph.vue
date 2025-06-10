<template>
  <div class="canvas">
    <!-- 最底层：先渲染不可点击的圆圈 -->
    <GraphCircleTemp
      v-for="(circle, index) in unclickableCircles"
      :key="`unclickable-${index}`"
      :cx="circle.cx"
      :cy="circle.cy"
      :r="circle.r"
      :stroke="circle.stroke"
      :stroke-width="circle.strokeWidth"
      :fill="circle.fill"
      :clickable="false"
      :style="{ zIndex: 1 }"
    >
      <span class="circle-text">{{ circle.text }}</span>
    </GraphCircleTemp>

    <!-- 中间层：渲染线条 -->
    <GraphLineTemp
      v-for="(line, index) in lines"
      :key="`line-${index}`"
      :points="line.points"
      :stroke="line.stroke"
      :stroke-width="line.strokeWidth"
      :stroke-linecap="line.strokeLinecap"
      :stroke-linejoin="line.strokeLinejoin"
      :style="{ zIndex: 100 }"
      @click-line="clickLine(line)"
    />

    <!-- 最顶层：后渲染可点击的圆圈，确保在上层 -->
    <GraphCircleTemp
      v-for="(circle, index) in clickableCircles"
      :key="`clickable-${index}`"
      :cx="circle.cx"
      :cy="circle.cy"
      :r="circle.r"
      :stroke="circle.stroke"
      :stroke-width="circle.strokeWidth"
      :fill="circle.fill"
      :clickable="true"
      :style="{ zIndex: 200, pointerEvents: 'none' }"
      @click-circle="clickCircle(circle)"
    >
      <span class="circle-text">{{ circle.text }}</span>
    </GraphCircleTemp>
    
  </div>
</template>

<script setup>
import { ref } from "vue";
import GraphCircleTemp from "./GraphCircleTemp.vue";
import GraphLineTemp from "./GraphLineTemp.vue";
import CanvasConf from "./CanvasConf.json";

const clickableCircles = ref(CanvasConf.clickableCircles);
const unclickableCircles = ref(CanvasConf.unclickableCircles);
const lines = ref(CanvasConf.lines);

function clickCircle(circle) {
  alert(`Clicked on ${circle.text}`);
}

function clickLine(line) {
  alert(`Clicked on line: ${line.name || "Unnamed line"}`);
}
</script>

<style scoped>
.canvas {
  width: 100%;
  height: 100%;
  background-color: #f0f0f0;
  border: 1px solid #ccc;
  font-size: 24px;
  color: #333;
  position: relative;
}

.circle-text {
  font-size: 14px;
}
</style>
