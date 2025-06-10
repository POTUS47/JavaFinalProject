<template>
  <div
    class="canvas"
    :style="{
      width: canvasWidth + 'px',
      height: canvasHeight + 'px',
    }"
  >
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
    <ElPopover
      v-for="(line, index) in lines"
      :title="line.popoverTitle || 'Line Popover'"
      :content="line.popoverContent || 'This is a popover content'"
      placement="top"
    >
      <template #reference>
        <GraphLineTemp
          :key="`line-${index}`"
          :points="line.points"
          :stroke="line.stroke"
          :stroke-width="line.strokeWidth"
          :stroke-linecap="line.strokeLinecap"
          :stroke-linejoin="line.strokeLinejoin"
          :style="{ zIndex: 100 }"
          @click-line="clickLine(line)"
        />
      </template>
    </ElPopover>

    <!-- 最顶层：后渲染可点击的圆圈，确保在上层 -->
    <ElPopover
      v-for="(circle, index) in clickableCircles"
      :title="circle.popoverTitle || 'Circle Popover'"
      :content="circle.popoverContent || 'This is a popover content'"
      placement="right"
    >
      <template #reference>
        <GraphCircleTemp
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
      </template>
    </ElPopover>
  </div>
  <Transition name="popover-fade">
    <div v-if="showPopover" class="popover-container">
      <Popover @close="showPopover = false" :popover-table-filename="popoverTableFilename" />
    </div>
  </Transition>
</template>

<script setup>
import { ref, computed } from "vue";
import GraphCircleTemp from "./GraphCircleTemp.vue";
import GraphLineTemp from "./GraphLineTemp.vue";
import CanvasConf from "./CanvasConf.json";
import Popover from "./Popover.vue";
import { ElPopover } from "element-plus";

const clickableCircles = ref(CanvasConf.clickableCircles);
const unclickableCircles = ref(CanvasConf.unclickableCircles);
const lines = ref(CanvasConf.lines);
const showPopover = ref(false);
const popoverTableFilename = ref("");

function clickCircle(circle) {
  // alert(`Clicked on ${circle.text}`);
  popoverTableFilename.value = circle.filename || "default";
  showPopover.value = true;
}

function clickLine(line) {
  alert(`Clicked on line: ${line.name || "Unnamed line"}`);
}

const padding = 10; // canvas边距

// 计算canvas尺寸
const canvasWidth = computed(() => {
  const allItems = [
    ...clickableCircles.value.map((c) => ({ x: c.cx, y: c.cy, r: c.r })),
    ...unclickableCircles.value.map((c) => ({ x: c.cx, y: c.cy, r: c.r })),
    ...lines.value.flatMap((l) => l.points.map((p) => ({ x: p.x, y: p.y, r: 0 }))),
  ];

  if (allItems.length === 0) return 800;

  const maxX = Math.max(...allItems.map((item) => item.x + item.r));
  return maxX + padding * 2;
});

const canvasHeight = computed(() => {
  const allItems = [
    ...clickableCircles.value.map((c) => ({ x: c.cx, y: c.cy, r: c.r })),
    ...unclickableCircles.value.map((c) => ({ x: c.cx, y: c.cy, r: c.r })),
    ...lines.value.flatMap((l) => l.points.map((p) => ({ x: p.x, y: p.y, r: 0 }))),
  ];

  if (allItems.length === 0) return 600;

  const maxY = Math.max(...allItems.map((item) => item.y + item.r));
  const height = maxY + padding * 2;
  if (showPopover.value) {
    // 如果有popover，增加额外的空间
    return height * 1.5;
  }
  // 否则正常计算
  return height;
});
</script>

<style scoped>
.canvas {
  min-width: 800px;
  min-height: 600px;
  background-color: #f0f0f0;
  border: 1px solid #ccc;
  font-size: 24px;
  color: #333;
  position: relative;
  overflow: visible;
}

.circle-text {
  font-size: 14px;
}

.popover-fade-enter-active,
.popover-fade-leave-active {
  transition: opacity 0.2s ease, transform 0.2s ease;
}

.popover-fade-enter-from,
.popover-fade-leave-to {
  opacity: 0;
  transform: translateY(40%);
}

.popover-fade-enter-to,
.popover-fade-leave-from {
  opacity: 1;
  transform: translateY(0);
}

.popover-container {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
  pointer-events: none;
}
</style>
