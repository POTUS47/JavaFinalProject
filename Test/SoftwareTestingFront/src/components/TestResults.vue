<template>
  <div class="test-results" v-if="results.length">
    <div class="title-2">测试结果</div>
    <table>
      <thead>
        <tr>
          <th>用例ID</th>
          <th>输入</th>
          <th>预期输出</th>
          <th>实际输出</th>
          <th>结果</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="result in results" :key="result.id">
          <td>{{ result.id }}</td>
          <td>{{ result.input }}</td>
          <td>{{ result.expected }}</td>
          <td>{{ result.actual }}</td>
          <td :class="{ 'success': result.passed, 'error': !result.passed }">
            {{ result.passed ? '通过' : '失败' }}
          </td>
        </tr>
      </tbody>
    </table>
    
    <div class="charts-container">
      <div ref="pieChartRef" class="pie-chart"></div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch, nextTick } from 'vue'
import * as echarts from 'echarts'

const props = defineProps({
  results: {
    type: Array,
    required: true,
    default: () => []
  }
})

const pieChartRef = ref(null)
let chart = null

const initChart = () => {
  if (pieChartRef.value) {
    chart = echarts.init(pieChartRef.value)
  }
}

const updateChart = () => {
  if (!chart) return
  
  const passedCount = props.results.filter(r => r.passed).length
  const failedCount = props.results.filter(r => !r.passed).length
  
  const option = {
    title: {
      text: '测试用例结果统计',
      left: 'center'
    },
    tooltip: {
      trigger: 'item',
      formatter: '{b}: {c} ({d}%)'
    },
    legend: {
      orient: 'vertical',
      left: 'left'
    },
    series: [
      {
        type: 'pie',
        radius: '50%',
        data: [
          { value: failedCount, name: '失败' },
          { value: passedCount, name: '通过' }
        ],
        emphasis: {
          itemStyle: {
            shadowBlur: 10,
            shadowOffsetX: 0,
            shadowColor: 'rgba(0, 0, 0, 0.5)'
          }
        }
      }
    ]
  }
  
  chart.setOption(option)
}

onMounted(() => {
  initChart()
  updateChart()
})

watch(() => props.results, () => {
  updateChart()
}, { deep: true })
</script>

<style scoped>
.test-results {
  background-color: #fff;
  border-radius: 4px;
}

.title-2{
  font-size: 20px;
  font-weight: bold;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  padding: 12px;
  text-align: left;
  border-bottom: 1px solid #ebeef5;
}

th {
  background-color: #f5f7fa;
  color: #606266;
  font-weight: 500;
}

tr:hover {
  background-color: #f5f7fa;
}

.success {
  color: #67c23a;
}

.error {
  color: #f56c6c;
}

.charts-container {
  margin: 20px 0;
  display: flex;
  justify-content: center;
  min-height: 300px;
}

.pie-chart {
  width: 400px;
  height: 300px;
}
</style> 