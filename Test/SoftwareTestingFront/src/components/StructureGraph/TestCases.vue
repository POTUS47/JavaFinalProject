<template>
  <div class="test-results">
    <table>
      <thead>
        <tr>
          <th v-for="header in csvData.headers" :key="header">{{ header }}</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in csvData.contents" :key="row.id">
          <td v-for="(cell, index) in row.values" :key="index">
            {{ cell }}
          </td>
          <!-- <td :class="{ success: result.passed, error: !result.passed }">
            {{ result.passed ? "通过" : "失败" }}
          </td> -->
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import Papa from "papaparse";

const csvData = ref({
  headers: [],
  contents: [],
});

const props = defineProps({
  filename: {
    type: String,
    default: "calculateCommission",
  },
});

onMounted(() => {
  const csvUrl = new URL(
    `/exercises/testcases/${props.filename}.csv`,
    import.meta.url
  ).href;
  Papa.parse(csvUrl, {
    download: true,
    header: true,
    skipEmptyLines: true,
    complete: (results) => {
      console.log(results);
      csvData.value.headers = results.meta.fields;
      csvData.value.contents = results.data.map((rowObj, idx) => ({
        id: idx,
        values: csvData.value.headers.map((h) => rowObj[h]),
      }));
    },
    error: (error) => {
      console.error("CSV parsing error:", error);
    },
  });
  console.log(csvData.value);
});
</script>

<style scoped>
.test-results {
  background-color: #fff;
  border-radius: 4px;
}

.title-2 {
  font-size: 20px;
  font-weight: bold;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th,
td {
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
