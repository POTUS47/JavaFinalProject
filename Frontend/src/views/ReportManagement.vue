<!-- 管理员页面的举报管理 -->
<template>
  <div v-show="role !== '管理员'">
    <p style="margin-top: 100px; font-weight: bold; font-size: 16px;">请登录管理员账号！！</p>
    <router-link :to="{ name: 'LoginAndRegister' }">点击此处跳转登录界面...</router-link>
  </div>
  <div class="container" v-show="role === '管理员'">
    <AdminSidebarMenu />
    <div class="main-content">
      <AdminHeaderSec />
      <div class="content">

        <hr>
        <div class="report-management">
          <h2>申诉管理</h2>
          <div class='table-content'>
            <el-table :data="complainData">
              <el-table-column prop="itemId" label="申诉订单ID" width="170"></el-table-column>
              <el-table-column prop="buyerReason" label="申诉原因" width="300"></el-table-column>
              <el-table-column prop="sellerReason" label="商家解释" width="200"></el-table-column>
              <el-table-column prop="complainDate" label="申诉时间" width="170"></el-table-column>
              <el-table-column prop="complainStatus" label="审核状态" width="100"></el-table-column>
              <el-table-column prop="resultReason" label="审核结果原因" width="200"></el-table-column>
              <el-table-column label="操作" width="200">
                <template #default="scope">
                  <el-button :disabled="scope.row['complainStatus'] === '审核完成'" @click="open_audit_window(scope.row)"
                    type="primary">审核</el-button>
                </template>
              </el-table-column>
            </el-table>

            <el-dialog style="border-radius: 10px;" v-model="show_audit_window" title="申诉详情" width="30%">
              <div
                style="display: flex; align-items: center; justify-content: center; flex-direction: column; padding: 15px;">
                <div
                  style="display: flex; align-items: center; justify-content: center; flex-direction: row; margin-top: 12px;">
                  <ElText style="width: 80px; margin-right: 20px;">申诉订单ID</ElText>
                  <ElInput style="width: 250px" readonly v-model="current_data['itemId']"></ElInput>
                </div>
                <div
                  style="display: flex; align-items: center; justify-content: center; flex-direction: row; margin-top: 12px;">
                  <ElText style="width: 80px; margin-right: 20px;">申诉原因</ElText>
                  <ElInput style="width: 250px" type="textarea" resize="none" autosize="true" readonly
                    v-model="current_data['buyerReason']"></ElInput>
                </div>
                <div
                  style="display: flex; align-items: center; justify-content: center; flex-direction: row; margin-top: 12px;">
                  <ElText style="width: 80px; margin-right: 20px;">商家解释</ElText>
                  <ElInput style="width: 250px" type="textarea" resize="none" autosize="true" readonly
                    v-model="current_data['sellerReason']"></ElInput>
                </div>
                <div
                  style="display: flex; align-items: center; justify-content: center; flex-direction: row; margin-top: 12px;">
                  <ElText style="width: 80px; margin-right: 20px;">申诉时间</ElText>
                  <ElInput style="width: 250px" readonly v-model="current_data['complainDate']"></ElInput>
                </div>
                <div style="margin-top: 50px;">
                  <ElButton @click="switch_audit_state('支持买家')"
                    :style="{ borderColor: (audit_state === '支持买家' ? 'green' : 'white'), backgroundColor: (audit_state === '支持买家' ? '#F0FFF0 ' : 'white') }"
                    style="width: 150px; height: 50px; border-width: 3px; border-radius: 10px;">支持买家</ElButton>
                  <ElButton @click="switch_audit_state('支持卖家')"
                    :style="{ borderColor: (audit_state === '支持卖家' ? 'green' : 'white'), backgroundColor: (audit_state === '支持卖家' ? '#F0FFF0 ' : 'white') }"
                    style="width: 150px; height: 50px; border-width: 3px; border-radius: 10px;">支持卖家</ElButton>
                </div>
                <div style="width: 100%; padding: 15px;">
                  <ElText style="font-size: small; color: grey; margin-left: 5px; margin-bottom: 5px;">审核原因</ElText>
                  <ElInput style="width: 100%;" type="textarea" resize="none" autosize="true" placeholder="请输入审核结果原因"
                    v-model="current_data['resultReason']"></ElInput>
                </div>
                <div>
                  <ElButton @click="submit_audit" style="border-radius: 10px; margin-top: 20px; width: 200px; height: 50px;">提交</ElButton>
                </div>
              </div>
              <!-- <div v-if="selectedDetail">
                <div v-if="selectedDetail.type == '帖子'">
                  <p>举报者账号: {{ selectedDetail.buyerAccountId }}</p>
                  <p>举报时间: {{ selectedDetail.reportingTime }}</p>
                  <p>举报原因: {{ selectedDetail.reportingReason }}</p>
                  <hr>
                  <p style="font-weight: bold; font-size:16px">帖子内容</p>
                  <p>帖子发布时间: {{ selectedDetail.postTime }}</p>
                  <p>帖子标题: {{ selectedDetail.postTitle }}</p>
                  <p>帖子内容: {{ selectedDetail.postContent }}</p>
                  <div v-if="selectedDetail.postImages" v-for="image in selectedDetail.postImages" :key="image.imageId">
                    <img :src="image.imageUrl" alt="Post Image" style="width: 70%; object-fit: cover;" />
                  </div>
                </div>
                <div v-else>
                  <p>举报者账号: {{ selectedDetail.buyerAccountId }}</p>
                  <p>举报时间: {{ selectedDetail.reportingTime }}</p>
                  <p>举报原因: {{ selectedDetail.reportingReason }}</p>
                  <hr>
                  <p style="font-weight: bold; font-size:16px">评论内容</p>
                  <p>评论发布时间: {{ selectedDetail.postTime }}</p>
                  <p>评论内容: {{ selectedDetail.postContent }}</p>
                </div>
              </div> -->
            </el-dialog>

            <div class="pagination-container">
              <div class="pagination">
                <div style="text-align: center;">
                  <span>共 {{ totalItems }} 条</span>
                </div>
                <el-pagination background layout="prev, pager, next" :total="totalItems" :page-size="pageSize"
                  @current-change="handlePageChange" :current-page="currentPage">
                </el-pagination>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script setup>
import AdminSidebarMenu from '../components/AdminSidebarMenu.vue'
import AdminHeaderSec from '../components/AdminHeaderSec.vue'
import { reactive, ref, computed, onMounted } from 'vue';
import { ElTable, ElTableColumn, ElPagination, ElButton, ElDialog, ElMessage, ElLoading, ElInput } from 'element-plus';
import 'element-plus/dist/index.css';
import axiosInstance from '../router/axios';
import { ca } from 'element-plus/es/locales.mjs';

const userId = localStorage.getItem('userId');
const role = localStorage.getItem('role');
const show_audit_window = ref(false);

const records = reactive([]);
const message01 = ref('');
const complainData = ref([]);
const totalItems = computed(() => complainData.value.length);
const current_data = ref([]);
const audit_state = ref('');

const fetchRecords = async () => {
  //加载缓冲
  const loadingInstance = ElLoading.service({
    lock: true,
    text: '正在加载...',
    target: '.table-content',
  });

  let response = null;

  try {
    response = await axiosInstance.get('/afterSell/getAllComplain');
  } catch (error) {
    if (error.response) {
      message01.value = error.response.data;
    } else {
      message01.value = '获取数据失败';
    }
    ElMessage.error('获取数据失败，请稍后再试');
  }

  complainData.value = response.data.data;

  loadingInstance.close();
};

function open_audit_window(info) {
  current_data.value = info;
  show_audit_window.value = true;
}

function switch_audit_state(state) {
  audit_state.value = state;
}

async function submit_audit() {
  if (audit_state.value === '') {
    ElMessage.error('请选择审核结果');
    return;
  }
  if (current_data.value['resultReason'] === '') {
    ElMessage.error('请输入审核结果原因');
    return;
  }
  let resultDTO = {
    "isApproved": audit_state.value==='支持买家'?true:false,
    "reason": current_data.value['resultReason']
  }
  console.log(resultDTO.isApproved);
  console.log(resultDTO.reason);
  let res = null;

  try {
    res = await axiosInstance.post(`/afterSell/arbitration/${current_data.value['itemId']}/resolve`, resultDTO);

    if(res.data.code === 200){
      ElMessage.success('提交成功');
    } else {
      ElMessage.error('提交失败');
    }
  }catch (error) {
    if (error.response) {
      message01.value = error.response.data;
    } else {
      message01.value = '操作失败';
    }
    ElMessage.error('操作失败，请稍后再试');
  }
  
  show_audit_window.value = false;
  fetchRecords();
}

onMounted(() => {
  fetchRecords();
});

// 页面大小及当前页数
const pageSize = ref(8);
const currentPage = ref(1);

// 处理并分页后的数据
const paginatedData = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return records.slice(start, end);
});

const handlePageChange = (page) => {
  currentPage.value = page;
};

// dialog（详情窗口）相关变量
const dialogVisible = ref(false);
const selectedDetail = reactive({});

const search = (id) => {
  const report = records.find(report => report.reportId === id);
  if (report) {
    Object.assign(selectedDetail, report);
    dialogVisible.value = true;
  }
};

const message02 = ref('');
const auditReport = async (reportId, auditResult) => {
  try {
    const response = await axiosInstance.put('/Administrator/AuditReport', {
      "reportId": reportId,
      "auditResult": auditResult,
      "adminId": userId
    });
    message02.value = response.data;
    fetchRecords();
  } catch (error) {
    if (error.response) {
      message02.value = error.response.data;
    } else if (error.request) {
      message02.value = '请求未收到响应';
    } else {
      message02.value = '操作失败';
    }
  }
  ElMessage.info(message02.value);
  console.log(message02.value);
};

</script>

<style scoped>
body {
  width: 100vw;
}

h2 {
  display: block;
  font-size: 1.5em;
  margin-block-start: 0.83em;
  margin-block-end: 0.83em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
  font-weight: bold;
  unicode-bidi: isolate;
}

.container {
  display: flex;
  height: 100vh;
}

.main-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  width: 0;
}

.content {
  flex: 1;
  padding: 0 15px;
}

.report-management {
  background-color: #ffffff;
  padding: 0 10px;
  text-align: left;
}

.table-content {
  width: 100%;
}

.pagination-container {
  display: flex;
  justify-content: center;
}

.pagination {
  max-width: 100%;
}
</style>
