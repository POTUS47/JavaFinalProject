<template>
  <svg
    :width="props.r * 2 + props.margin * 2"
    :height="props.r * 2 + props.margin * 2 + (props.clickable ? 0 : 40)"
    :style="{
      left: props.cx - props.r - props.margin + 'px',
      top: props.cy - props.r - props.margin + (props.clickable ? 0 : -25) + 'px',
    }"
  >
    <circle
      :cx="props.r + props.margin"
      :cy="props.r + props.margin + (props.clickable ? 0 : 25)"
      :r="props.r"
      :stroke="props.stroke"
      :stroke-width="props.strokeWidth"
      :fill="props.fill"
      :class="props.clickable ? 'clickable' : 'unclickable'"
      @click="props.clickable && emit('click-circle')"
    />
    <foreignObject
      :x="props.margin"
      :y="props.clickable ? props.margin : 5"
      :width="props.r * 2"
      :height="props.clickable ? props.r * 2 : 25"
      :style="{ pointerEvents: 'none' }"
    >
      <div
        class="slot-content"
        :class="{
          clickable: props.clickable,
          'above-circle': !props.clickable,
        }"
        @click="props.clickable && emit('click-circle')"
      >
        <slot />
      </div>
    </foreignObject>
  </svg>
</template>

<script setup>
const props = defineProps({
  cx: {
    type: Number,
    default: 50,
  },
  cy: {
    type: Number,
    default: 50,
  },
  r: {
    type: Number,
    default: 40,
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
    default: "lightblue",
  },
  margin: {
    type: Number,
    default: 10,
  },
  clickable: {
    type: Boolean,
    default: true,
  },
});

const emit = defineEmits(["click-circle"]);
</script>

<style scoped>
svg {
  position: absolute;
  display: block;
  margin: 0 0;
}

circle.clickable {
  pointer-events: all;
  cursor: pointer;
  transform-box: fill-box;
  transform-origin: center center;
  transition: filter 0.1s ease, transform 0.1s ease;
}

circle.clickable:hover {
  transform: scale(1.05);
  filter: brightness(0.9);
}

circle.unclickable {
  pointer-events: none;
}

.slot-content {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.slot-content.clickable {
  cursor: pointer;
}

.slot-content.above-circle {
  align-items: flex-start;
  padding-top: 5px;
  font-weight: bold;
  color: #333;
  font-size: 12px;
}
</style>
