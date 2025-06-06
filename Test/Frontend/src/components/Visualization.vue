<template>
  <div ref="svgContainer"></div>
</template>

<script setup>
import * as d3 from "d3";
import { onMounted, ref } from "vue";
import structure from "../assets/structure.json";

const svgContainer = ref();

onMounted(() => {
  drawGraph(structure);
});

function drawGraph(data) {
  const width = 900;
//   const width = window.innerWidth * 0.95; // 使用 95% 的屏幕宽度
  const height = 500;
  const radius = 46; // 增加节点圆圈半径
  const subsystemGap = 350; // 增加微服务间距
  const nodeGapY = 100;

  const svg = d3
    .select(svgContainer.value)
    .append("svg")
    .attr("width", width)
    .attr("height", height);

  const defs = svg.append("defs");
  defs
    .append("marker")
    .attr("id", "arrow")
    .attr("viewBox", "0 -5 10 10")
    .attr("refX", 18)
    .attr("refY", 0)
    .attr("markerWidth", 6)
    .attr("markerHeight", 6)
    .attr("orient", "auto")
    .append("path")
    .attr("d", "M0,-5L10,0L0,5")
    .attr("fill", "black");

  const nodeData = [];
  const linkData = [];
  const classPos = {};
  const subsystemControllers = {};

  const totalSubsystems = Object.keys(data.subsystems).length;
  const totalWidth = (totalSubsystems - 1) * subsystemGap;
  const centerX = width / 2;
  const centerY = height / 2;
  const xOffset = centerX - totalWidth / 2;

  const tooltip = d3.select(svgContainer.value)
    .append("div")
    .style("position", "absolute")
    .style("background", "#fff")
    .style("border", "1px solid #ccc")
    .style("padding", "4px 8px")
    .style("border-radius", "4px")
    .style("pointer-events", "none")
    .style("opacity", 0);

  Object.entries(data.subsystems).forEach(([subsystemName, details], sIndex) => {
    const groupX = xOffset + sIndex * subsystemGap;
    const groupY = centerY - 150; // 居中 Y 坐标
    const controller = details.controller;
    const services = details.services;
    const testedClasses = new Set(details.tested_classes);
    const testedClassCalls = new Set(details.tested_class_calls.map(([a, b]) => `${a}->${b}`));

    const ctrlId = `${subsystemName}.${controller}`;
    subsystemControllers[subsystemName] = ctrlId;

    const maxNodes = services.length + 1;
    const circleRadius = 125 + maxNodes * 10;

    svg
      .append("circle")
      .attr("cx", groupX)
      .attr("cy", groupY + 160)
      .attr("r", circleRadius)
      .attr("fill", "none")
      .attr("stroke", "gray")
      .attr("stroke-dasharray", "4,2");

    svg
      .append("text")
      .attr("x", groupX)
      .attr("y", groupY + circleRadius *2+5) // 放置在大圆圈下方
      .attr("text-anchor", "middle")
      .attr("font-size", "14px")
      .attr("fill", "gray")
      .text(subsystemName);

    classPos[ctrlId] = {
      x: groupX,
      y: groupY + 160 - circleRadius + 40,
      name: controller,
      tested: testedClasses.has(controller),
      type: "controller",
      subsystem: subsystemName,
    };
    nodeData.push(classPos[ctrlId]);

    const angleStep = (Math.PI) / services.length *1.3;
    services.forEach((svc, i) => {
      const svcId = `${subsystemName}.${svc}`;
      const angle = i * angleStep;
      const innerRadius = circleRadius * 0.7;
    //   const angle = i * angleStep - Math.PI / 2; // 起始角度偏移90度，从顶部开始
      const adjustedInnerRadius = innerRadius-3; // 也可稍微增大半径，避免与控制器重叠
      classPos[svcId] = {
        x: groupX + Math.cos(angle) * adjustedInnerRadius,
        y: groupY + 160 + Math.sin(angle) * adjustedInnerRadius,
        name: svc,
        tested: testedClasses.has(svc),
        type: "service",
        subsystem: subsystemName,
      };
      nodeData.push(classPos[svcId]);
    });

    Object.entries(details.class_calls).forEach(([caller, callees]) => {
      const fromId = `${subsystemName}.${caller}`;
      callees.forEach((callee) => {
        const toId = `${subsystemName}.${callee}`;
        linkData.push({
          source: fromId,
          target: toId,
          tested: testedClassCalls.has(`${caller}->${callee}`),
        });
      });
    });
  });

  data.inter_subsystem_calls.forEach((call) => {
    const fromCtrl = subsystemControllers[call.from];
    const toCtrl = subsystemControllers[call.to];
    linkData.push({
      source: fromCtrl,
      target: toCtrl,
      tested: call.tested,
      isSubsystem: true,
    });
  });

  const lines = svg.selectAll("line").data(linkData).enter().append("line")
    // .attr("x1", d => classPos[d.source].x)
    // .attr("y1", d => classPos[d.source].y)
    // .attr("x2", d => classPos[d.target].x)
    // .attr("y2", d => classPos[d.target].y)
    .attr("x1", d => adjustLineStart(d.source, classPos[d.source], radius))
    .attr("y1", d => adjustLineStart(d.source, classPos[d.source], radius, false))
    .attr("x2", d => adjustLineEnd(d.target, classPos[d.target], radius))
    .attr("y2", d => adjustLineEnd(d.target, classPos[d.target], radius, false))//下一步可调整箭头的大小
    .attr("stroke", d => d.tested ? "red" : "#666")
    .attr("stroke-width", 3)
    .attr("marker-end", "url(#arrow)")
    .on("mouseover", function(event, d) {
      const src = classPos[d.source];
      const tgt = classPos[d.target];
      tooltip.transition().duration(200).style("opacity", 0.9);
      tooltip.html(
        d.isSubsystem
          ? `${src.subsystem} → ${tgt.subsystem}`
          : `${src.name} → ${tgt.name}`
      )
        .style("left", (event.pageX + 10) + "px")
        .style("top", (event.pageY - 20) + "px");
    })
    .on("mouseout", function() {
      tooltip.transition().duration(500).style("opacity", 0);
    });

  // 辅助函数：调整线条起点，避免被圆覆盖
  function adjustLineStart(id, pos, r, isX = true) {
    const dx = pos.x - classPos[id].x || 1;
    const dy = pos.y - classPos[id].y || 1;
    const len = Math.sqrt(dx * dx + dy * dy);
    const offsetX = (dx / len) * r ;
    const offsetY = (dy / len) * r ;
    return isX ? pos.x + offsetX : pos.y + offsetY;
  }

  // 辅助函数：调整线条终点，避免被圆覆盖
  function adjustLineEnd(id, pos, r, isX = true) {
    const dx = pos.x - classPos[id].x || 1;
    const dy = pos.y - classPos[id].y || 1;
    const len = Math.sqrt(dx * dx + dy * dy);
    const offsetX = (dx / len) * r*0.65;
    const offsetY = (dy / len) * r*0.65;
    return isX ? pos.x - offsetX : pos.y - offsetY;
  }

  const nodes = svg.selectAll(".node-group").data(nodeData).enter()
    .append("g")
    .attr("transform", d => `translate(${d.x}, ${d.y})`)
    .on("mouseover", function(event, d) {
      tooltip.transition().duration(200).style("opacity", 0.9);
      tooltip.html(`${d.subsystem} - ${d.name} (${d.type})`)
        .style("left", (event.pageX + 10) + "px")
        .style("top", (event.pageY - 20) + "px");
    })
    .on("mouseout", function() {
      tooltip.transition().duration(500).style("opacity", 0);
    });

  nodes.append("circle")
    .attr("r", radius)
    .attr("fill", d => d.tested ? "steelblue" : "white")
    .attr("stroke", "black");

  nodes.append("text")
    .attr("text-anchor", "middle")
    .attr("alignment-baseline", "middle")
    .attr("fill", d => d.tested ? "white" : "black")
    .attr("font-size", "12px")
    .attr("dy", ".3em")
    .text(d => d.name);
}
</script>

<style scoped>
svg {
  background-color: #fafafa;
}
</style>