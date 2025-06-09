<template>
  <svg
    :width="svgWidth"
    :height="svgHeight"
    :style="{
      left: minX + 'px',
      top: minY + 'px',
      pointerEvents: 'none',
    }"
  >
    <!-- 为每个线段创建单独的点击区域 -->
    <template v-for="(segment, index) in lineSegments" :key="`segment-${index}`">
      <!-- 透明的粗线段用于点击区域 -->
      <path
        :d="segment.path"
        stroke="transparent"
        :stroke-width="Math.max(props.strokeWidth * 3, 8)"
        fill="none"
        :stroke-linecap="props.strokeLinecap"
        :stroke-linejoin="props.strokeLinejoin"
        @click="$emit('click-line')"
        @mouseenter="hoveredSegment = index"
        @mouseleave="hoveredSegment = -1"
        class="click-area"
      />
    </template>

    <!-- 可见的完整线条 -->
    <path
      :d="pathData"
      :stroke="props.stroke"
      :stroke-width="hoveredSegment >= 0 ? props.strokeWidth + 1 : props.strokeWidth"
      :fill="props.fill"
      :stroke-linecap="props.strokeLinecap"
      :stroke-linejoin="props.strokeLinejoin"
      :style="{
        filter: hoveredSegment >= 0 ? 'brightness(0.8)' : 'none',
        transition: 'stroke-width 0.1s ease, filter 0.1s ease',
      }"
      class="visible-line"
    />
  </svg>
</template>

<script setup>
import { computed, ref } from "vue";

const props = defineProps({
  points: {
    type: Array,
    required: true,
    validator: (points) =>
      points.length >= 2 && points.every((p) => p.x !== undefined && p.y !== undefined),
  },
  stroke: {
    type: String,
    default: "black",
  },
  strokeWidth: {
    type: Number,
    default: 2,
  },
  fill: {
    type: String,
    default: "none",
  },
  strokeLinecap: {
    type: String,
    default: "round",
  },
  strokeLinejoin: {
    type: String,
    default: "round",
  },
  margin: {
    type: Number,
    default: 10,
  },
});

const emit = defineEmits(["click-line"]);

const hoveredSegment = ref(-1);

// 计算边界框
const minX = computed(() => Math.min(...props.points.map((p) => p.x)) - props.margin);
const minY = computed(() => Math.min(...props.points.map((p) => p.y)) - props.margin);
const maxX = computed(() => Math.max(...props.points.map((p) => p.x)) + props.margin);
const maxY = computed(() => Math.max(...props.points.map((p) => p.y)) + props.margin);

const svgWidth = computed(() => maxX.value - minX.value);
const svgHeight = computed(() => maxY.value - minY.value);

// 生成路径字符串
const pathData = computed(() => {
  if (props.points.length < 2) return "";

  const adjustedPoints = props.points.map((p) => ({
    x: p.x - minX.value,
    y: p.y - minY.value,
  }));

  let path = `M ${adjustedPoints[0].x} ${adjustedPoints[0].y}`;
  for (let i = 1; i < adjustedPoints.length; i++) {
    path += ` L ${adjustedPoints[i].x} ${adjustedPoints[i].y}`;
  }
  return path;
});

// 计算每个线段
const lineSegments = computed(() => {
  if (props.points.length < 2) return [];

  const adjustedPoints = props.points.map((p) => ({
    x: p.x - minX.value,
    y: p.y - minY.value,
  }));

  const segments = [];
  for (let i = 0; i < adjustedPoints.length - 1; i++) {
    const start = adjustedPoints[i];
    const end = adjustedPoints[i + 1];
    segments.push({
      path: `M ${start.x} ${start.y} L ${end.x} ${end.y}`,
      start,
      end,
    });
  }
  return segments;
});
</script>

<style scoped>
svg {
  position: absolute;
  display: block;
  margin: 0 0;
  pointer-events: none;
}

.click-area {
  cursor: pointer;
  pointer-events: all;
}

.visible-line {
  pointer-events: none;
}
</style>
