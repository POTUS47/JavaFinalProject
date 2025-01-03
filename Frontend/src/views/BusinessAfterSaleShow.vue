<!-- 商家界面的订单管理的内容界面 -->
<template>
  <div class="CommodityShow">

    <!-- 表格 -->
    <div class="TableContainer">
      <el-table :data="currentPageData" class="CommodityTable" height="760">
        <el-table-column type="index" />
        <el-table-column label="订单号" width="150">
          <template #default="scope">
            <div>{{ scope.row.orderId }}</div>
          </template>
        </el-table-column>
        <el-table-column prop="returnDate" label="退货时间" width="200"></el-table-column>
        <el-table-column prop="returnReason" label="退货原因" width="150"></el-table-column>
        <el-table-column prop="returnStatus" label="退货状态" width="150"></el-table-column>
        <el-table-column prop="practicalPrice" label="" width="150"></el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <div v-for="item in scope" :key="item.returnId" class="after-sale-status">
              <div class="status-content">
                <template v-if="item.itemStatus === '待审核'">
                  <el-button
                      size="small"
                      type="warning"
                      @click="handleReturnRequest(item,true)"
                  >
                    同意退货
                  </el-button>
                </template>
                <template v-if="item.itemStatus === '申请被拒绝'">
                  <el-button
                      size="small"
                      type="warning"
                      @click="showArbitrateDialog(item.itemId)"
                  >
                    申请仲裁
                  </el-button>
                </template>
              </div>
            </div>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 买家评价 -->
    <!--    <div v-if="dialogVisible" class="SettingPopUp">-->
    <!--      <div v-if="currentProduct" class="SettingContent">-->
    <!--        <span class="close" @click="dialogVisible = false">&times;</span>-->
    <!--        <p>买家评分: {{ currentProduct.score }}</p>-->
    <!--        <p>评价内容: {{ currentProduct.remark }}</p>-->
    <!--      </div>-->
    <!--    </div>-->

    <!-- 仲裁 -->
    <el-dialog
        title="申请仲裁"
        v-model="arbitrateDialogVisible"
        width="30%"
    >
      <el-form :model="arbitrateForm">
        <el-form-item label="仲裁理由">
          <el-input
              v-model="arbitrateForm.reason"
              type="textarea"
              rows="4"
              placeholder="请输入仲裁理由"
          ></el-input>
        </el-form-item>
      </el-form>
      <template #footer>
    <span class="dialog-footer">
      <el-button @click="arbitrateDialogVisible = false">取消</el-button>
      <el-button type="primary" @click="arbitrate(arbitrateForm)">确定</el-button>
    </span>
      </template>
    </el-dialog>

    <!-- 翻页 -->
    <div class="paginationContainer">
      <el-pagination
          v-model:current-page="currentPage"
          :page-size="pageSize"
          :total="totalProducts"
          layout="total, prev, pager, next, jumper"
          @current-change="handlePageChange"
      ></el-pagination>
    </div>
  </div>

</template>

<script>
import { ref, computed, onMounted } from 'vue';
import { ElSelect, ElOption, ElInput, ElButton, ElTable, ElTableColumn, ElPagination, ElButtonGroup, ElMessage } from 'element-plus';
import axiosInstance from '../router/axios';
import 'element-plus/dist/index.css';

export default {
  components: {
    ElSelect,
    ElOption,
    ElInput,
    ElButton,
    ElTable,
    ElTableColumn,
    ElPagination,
    ElButtonGroup
  },
  setup() {
    const selectedFilter = ref('');
    const dialogVisible = ref(false);
    const dialogVisibleTwo = ref(false);
    const currentProduct = ref(null);
    const orderStatus = ref(0);
    const searchOrder = ref('');
    const searchTime = ref('');
    const products = ref([]);
    const arbitrateDialogVisible = ref(false)
    const arbitrateForm = ref({
      reason: '',
      itemId: null
    })
    const viewTypeValue=ref(1)

    //展示订单状态展示订单
    // const fetchOrders = async () => {
    //   try {
    //     const response = await axiosInstance.get('/afterSell/seller/current_returns');
    //
    //     // 检查 response.data 是否直接就是数组
    //     if (Array.isArray(response.data)) {
    //       products.value = response.data;
    //       console.log('售后订单数据:', products.value);
    //     }
    //     // 检查是否是包装在 data 字段中的数组
    //     else if (response.data?.data && Array.isArray(response.data.data)) {
    //       products.value = response.data.data;
    //       console.log('售后订单数据:', products.value);
    //     }
    //     else {
    //       console.error('接口返回格式异常:', response.data);
    //     }
    //   } catch (error) {
    //     console.error('获取售后数据失败:', error);
    //   }
    // };
    const fetchOrders = async () => {
      try {
        // 1. 首先获取所有退货订单ID
        const response = await axiosInstance.get('/afterSell/seller/current_returns');

        // 2. 确保我们得到了订单ID数组
        let orderIds = [];
        if (Array.isArray(response.data)) {
          orderIds = response.data;
        } else if (Array.isArray(response.data?.data)) {
          orderIds = response.data.data;
        } else {
          console.error('获取订单ID列表失败:', response.data);
          return;
        }

        // 3. 获取每个订单的详细信息
        const orderDetails = await Promise.all(
            orderIds.map(async (returnId) => {
              try {
                const detailResponse = await axiosInstance.get(`/afterSell/return/${returnId}`);
                // 检查并返回订单详情数据
                // 获取对应的 orderId
                const orderIdResponse = await axiosInstance.get(`/shopping/getOrderIdByItemId/${returnId}`);

                if (detailResponse.data?.code === 200) {
                  return {
                    ...detailResponse.data.data,
                    returnId, // 添加 returnId 到返回的数据中
                    orderId: orderIdResponse.data?.data // 添加 orderId
                  };
                }
                console.error(`获取订单 ${returnId} 详情失败:`, detailResponse.data);
                return null;
              } catch (error) {
                console.error(`获取订单 ${returnId} 详情出错:`, error);
                return null;
              }
            })
        );

        // 4. 过滤掉失败的请求，只保留成功获取的订单详情
        products.value = orderDetails.filter(detail => detail !== null);
        console.log('售后订单详细数据:', products.value);

      } catch (error) {
        console.error('获取售后数据失败:', error);
      }
    };



    const handleChange = (viewTypeValue) => {
      console.log("切换",viewTypeValue);
      orderStatus.value = viewTypeValue;
      fetchOrders();
    };

    //查看买家评价
    const handleCheck = (row) => {
      currentProduct.value = row;
      dialogVisible.value = true;
    };
    //查看快递单号
    const handleCheckTwo = (row) => {
      currentProduct.value = { ...row, deliveryNumberInput: '' };
      dialogVisibleTwo.value = true;
    };
    //更新快递单号
    const updateDeliveryNumber = async () => {
      if (!currentProduct.value.deliveryNumberInput) {
        ElMessage({
          message: '请输入快递单号',
          type: 'warning'
        });
        return;
      }

      try {
        const storeId =localStorage.getItem('userId'); // 替换为实际的storeId
        const orderId = currentProduct.value.id;
        const deliveryNumber = currentProduct.value.deliveryNumberInput;

        // 发送请求到后端更新快递单号
        const response = await axiosInstance.put('/shopping/order/update-delivery-number', {
          orderId: orderId,
          deliveryNumber: deliveryNumber
        });

        if (response.status === 200) {
          ElMessage({
            message: '快递单号已更新',
            type: 'success'
          });
          currentProduct.value.deliveryNumber = deliveryNumber;
          dialogVisibleTwo.value = false;
          fetchOrders();
        } else {
          ElMessage({
            message: '更新快递单号失败',
            type: 'error'
          });
        }
      } catch (error) {
        console.error('更新快递单号失败:', error.response ? error.response.data : error.message);
        ElMessage({
          message: '更新快递单号失败: ' + error.message,
          type: 'error'
        });
      }
    };

    //同意退货请求
    const handleReturnRequest = async (item,result) => {
      try {
        console.log(result);
        const requestBody = {
          reason: '好的',
          isApproved: result
        };
        // 发送请求到后端确认退货
        const response = await axiosInstance.post( `/afterSell/returns/${item.itemId}/approveReturn`,
            requestBody,
            {
              headers: {
                'Content-Type': 'application/json'
              }
            });

        if (response.data.code === 200) {
          ElMessage({
            message: '退货请求已处理。',
            type: 'success'
          });
          fetchOrders();
        } else {
          ElMessage({
            message: '退货请求确认失败',
            type: 'error'
          });
        }
      } catch (error) {
        console.error('确认退货时发生错误:', error.response.data.msg ? error.response.data.msg : error.message);
        ElMessage({
          message: '退货请求确认失败: ' + error.message,
          type: 'error'
        });
      }
    };
    const showArbitrateDialog = (itemId) => {
      arbitrateForm.value.itemId = itemId
      arbitrateDialogVisible.value = true
    }

    //申请仲裁请求
    const arbitrate = async (form) => {
      try {
        console.log(form.itemId);
        // 发送仲裁请求
        const response = await axiosInstance.post(
            `/afterSell/return/${form.itemId}/arbitration`,
            {
              reason: form.reason  // 仲裁原因
            }
        );

        if (response.data.code === 200) {  // 使用 response.data.code 判断
          ElMessage({
            message: response.data.msg || '仲裁申请已提交',
            type: 'success'
          });
          fetchOrders();  // 刷新订单列表
        } else {
          ElMessage({
            message: response.data.msg || '仲裁申请失败',
            type: 'error'
          });
        }
      } catch (error) {
        console.error('申请仲裁时发生错误:', error);
        ElMessage({
          message: error.response?.data?.msg || '仲裁申请失败',
          type: 'error'
        });
      }
    };

    //翻页
    const pageSize = 20;
    const currentPage = ref(1);
    const totalProducts = computed(() => products.value.length);
    const currentPageData = computed(() => {
      const start = (currentPage.value - 1) * pageSize;
      const end = Math.min(start + pageSize, totalProducts.value);
      return products.value.slice(start, end);
    });
    const handlePageChange = (page) => {
      currentPage.value = page;
    };
    onMounted(() => {
      fetchOrders();
    });

    return {
      selectedFilter,
      dialogVisible,
      dialogVisibleTwo,
      arbitrateForm,
      arbitrateDialogVisible,
      currentProduct,
      searchOrder,
      products,
      handleCheck,
      handleCheckTwo,
      handleReturnRequest,
      pageSize,
      currentPage,
      totalProducts,
      currentPageData,
      handlePageChange,
      handleChange,
      orderStatus,
      searchTime,
      updateDeliveryNumber,
      showArbitrateDialog,
      arbitrate,

    };
  }
};
</script>

<style scoped>
.CommodityShow {
  position: fixed;
  top: 10vh;
  left: 150px;
  right: 0;
  bottom: 0;
  background-color: #DFCDC7  ;
}

.TableContainer {
  margin: 10px;
  margin-top: 0;
}

.SearchContainer {
  margin-left: 950px;
  display: flex;
  align-items: center;
  height: 60px;
  margin-right: 10px;
  /* background-color: rgb(164, 197, 181); */
}

.paginationContainer {
  display: inline-block;
}

.SettingPopUp {
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0,0,0,0.4);
  display: flex;
  justify-content: center;
  align-items: center;
}

.SettingContent {
  color: #065f43;
  background-color: #fefefe;
  display: inline-block;
  padding: 5vh;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.space {
  margin-left: 20px;
}

.el-button--primary {
  background-color: #a13232;
  border-color: #a13232;
}

.el-button--primary:hover {
  background-color: #8b2b2b;
  border-color: #8b2b2b;
}

.product-info {
  margin-top: 8px;
  padding-left: 8px;
  border-left: 2px solid #eee;
}

.after-sale-status {
  margin: 8px 0;
}

.product-name {
  font-size: 13px;
  color: #666;
  margin-bottom: 4px;
}

.status-content {
  display: flex;
  align-items: center;
}

.finished-status {
  color: #909399;
}
</style>