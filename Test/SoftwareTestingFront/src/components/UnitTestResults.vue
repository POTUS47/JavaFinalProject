<template>
  <div class="test-results" v-if="tableData.length">
    <div class="table-container">
        <table>
        <thead>
            <tr>
            <th v-for="header in headers" :key="header">{{ header }}</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(row, index) in tableData" :key="index">
            <td v-for="header in headers" :key="header" :class="getCellClass(header, row[header])">
                {{ renderCell(header, row[header]) }}
            </td>
            </tr>
        </tbody>
        </table>
    </div>

    <div class="charts-container">
      <div ref="pieChartRef" class="pie-chart"></div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch, nextTick } from 'vue'
import * as echarts from 'echarts'

const props = defineProps({
  tableData: {
    type: Array,
    required: true,
    default: () => []
  }
})

const headers = ref([])
const pieChartRef = ref(null)
let chart = null

const getCellClass = (header, value) => {
  if (header === '运行结果') {
    return value === '1' ? 'success' : 'error'
  }
  return ''
}

const renderCell = (header, value) => {
  if (header === '运行结果') {
    return value === '1' ? '通过' : '失败'
  }
  return value
}

const initChart = () => {
  if (pieChartRef.value) {
    chart = echarts.init(pieChartRef.value)
  }
}

const updateChart = () => {
  if (!pieChartRef.value) return

  if (!chart) {
    chart = echarts.init(pieChartRef.value)
  }

  if (!headers.value.includes('运行结果')) return

  const passedCount = props.tableData.filter(row => row['运行结果'] === '1').length
  const failedCount = props.tableData.filter(row => row['运行结果'] === '0').length

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

watch(() => props.tableData, (newVal) => {
  if (newVal.length > 0) {
    headers.value = Object.keys(newVal[0]).filter(key =>
      newVal.some(row => row[key] !== null && row[key] !== undefined && row[key] !== '')
    )

    nextTick(() => {
      updateChart()
    })
  }
}, { immediate: true, deep: true })

onMounted(() => {
  nextTick(() => {
    updateChart()
  })
})
</script>

<style scoped>
.test-results {
  background-color: #fff;
  border-radius: 4px;
}

.table-container {
  overflow-x: auto;
  max-width: 100%;
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
