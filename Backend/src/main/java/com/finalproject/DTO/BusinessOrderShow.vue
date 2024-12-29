<!-- 商家界面的订单管理的内容界面 -->
<template>
  <div class="CommodityShow">
    <div class="SearchContainer">
      <el-input v-model="searchOrder" placeholder="请输入订单ID（在全部订单中搜索）" style="display: inline-block;"></el-input>
      <el-button type="primary" @click="searchOrderById" >搜索</el-button>
      <el-input v-model="searchTime" placeholder="根据创建时间筛选（在全部订单中搜索）" style="display: inline-block;"></el-input>
      <el-button type="primary" @click="searchOrderByTime">筛选</el-button>
    </div>

    <!-- 表格 -->
    <div class="TableContainer">
      <el-table :data="currentPageData" class="CommodityTable" height="760">
        <el-table-column type="index" />
        <el-table-column label="订单信息" width="200">
          <template #default="scope">
            <div>订单号：{{ scope.row.id }}</div>
            <div v-for="item in scope.row.orderItems" :key="item.itemId" class="product-info">
              <div>{{ item.productName }}</div>
              <div>￥{{ item.productPrice }}</div>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="creationTime" label="创建时间" width="150"></el-table-column>
        <el-table-column prop="orderStatus" label="订单状态" width="150"></el-table-column>
        <el-table-column prop="orderPrice" label="订单金额" width="150"></el-table-column>
        <el-table-column prop="practicalPrice" label="" width="150"></el-table-column>
        <el-table-column label="售后状态">
          <template #default="scope">
            <div v-for="item in scope.row.orderItems" :key="item.itemId" class="after-sale-status">
              <div class="status-content">
                <template v-if="item.itemStatus === '无售后'">
                  <span>无</span>
                </template>
                <template v-else-if="item.itemStatus === '售后中' && item.result===true">
                  <el-button
                      size="small"
                      type="warning"
                      @click="handleReturnRequest(item,true)"
                  >
                    同意退货
                  </el-button>
                  <el-button
                      size="small"
                      type="warning"
                      @click="handleReturnRequest(item,false)"
                  >
                    拒绝退货
                  </el-button>
                </template>
<!--                <template v-else-if="item.itemStatus === '售后中' && item.result===false">-->
<!--                  <el-button-->
<!--                      size="small"-->
<!--                      type="warning"-->
<!--                      @click="showArbitrateDialog(item.itemId)"-->
<!--                  >-->
<!--                    申请仲裁-->
<!--                  </el-button>-->
<!--                </template>-->
                <template v-else-if="item.itemStatus === '售后结束'">
                  <span class="finished-status">已售后结束</span>
                </template>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="scope">
            <el-button-group>
<!--              <el-button size="mini" type="primary" @click="handleCheck(scope.row)">买家评价</el-button>-->
              <el-button
                  v-if="scope.row.orderStatus === '处理中'"
                  size="mini"
                  type="primary"
                  @click="handleCheckTwo(scope.row)"
              >
                快递
              </el-button>
            </el-button-group>
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


    <!-- 快递单号 -->
    <div v-if="dialogVisibleTwo" class="SettingPopUp">
      <div v-if="currentProduct" class="SettingContent">
        <span class="close" @click="dialogVisibleTwo = false">&times;</span>
        <template v-if="currentProduct.deliveryNumber">
          <p>快递单号: {{ currentProduct.deliveryNumber }}</p>
        </template>
        <template v-else>
          <el-form @submit.prevent="updateDeliveryNumber">
            <el-form-item label="输入快递单号">
              <el-input v-model="currentProduct.deliveryNumberInput"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="updateDeliveryNumber">提交</el-button>
            </el-form-item>
          </el-form>
        </template>
      </div>
    </div>

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

    //展示订单状态展示订单
    const fetchOrders = async () => {
      try {
        // 修改 API 路径
        const response = await axiosInstance.get('/shopping/order/get-all-store-orders');

        if (Array.isArray(response.data.data)) {  // 注意这里需要访问 response.data.data
          const processedOrders = response.data.data
              .map(order => {
                // 根据新接口调整字段映射
                // const creationTime = order.createTime ? new Date(order.createTime).toLocaleString() : 'N/A';
                let returnStatus = '';

                // // 保持原有的订单状态逻辑
                // if (order.orderStatus === '已完成') {
                //   returnStatus = '待同意';
                // } else if (order.orderStatus === '已退货') {
                //   returnStatus = '已同意';
                // } else if (!order.returN_OR_NOT) {
                //   returnStatus = '待同意';
                // }

                let orderStatusText = order.orderStatus;
                if (order.orderStatus === '待处理') {
                  orderStatusText = '待发货';
                } else if (order.orderStatus === '待付款') {
                  return null;
                }

                return {
                  id: order.orderId || 'N/A',
                  creationTime: order.createTime,
                  orderStatus: orderStatusText || 'Unknown',
                  orderPrice: order.totalPay !== undefined ? order.totalPay : 'N/A',
                  returnRequested: order.returN_OR_NOT != null ? order.returN_OR_NOT : false,
                  returnStatus: returnStatus,
                  remark: order.remark || 'No remarks',
                  score: order.score !== undefined ? order.score : 'N/A',
                  deliveryNumber: order.deliveryNumber || '',
                  arbitration:'',
                  // 新增字段
                  username: order.username || 'N/A',
                  storeName: order.storeName || 'N/A',
                  address: order.address || 'N/A',
                  paid: order.paid || false,
                  orderItems: order.orderItems?.map(item => ({
                    itemId: item.itemId,
                    productId: item.productId,
                    productName: item.productName,
                    productImage: item.productImage,
                    productPrice: item.productPrice,
                    itemStatus:item.itemStatus,
                    result:true,
                  })) || [],
                };
              })
              .filter(order => order !== null);

          console.log('Processed Orders:', processedOrders);
          products.value = processedOrders;
        } else {
          console.error('Unexpected response format:', response.data);
        }
      } catch (error) {
        console.error('获取订单数据失败:', error);
      }
    };

    const handleChange = (viewTypeValue) => {
      orderStatus.value = viewTypeValue;
      fetchOrders();
    };
    //根据订单Id搜索订单
    const searchOrderById = () => {
      if (searchOrder.value.trim() !== '') {
        fetchOrderById(searchOrder.value.trim());
      } else {
        fetchOrders();
      }
    };
    const fetchOrderById = async (orderId) => {
      const storeId = localStorage.getItem('userId'); // 替换为实际的 storeId
      try {
        const response = await axiosInstance.get('/StoreOrder/GetOrderById', {
          params: {
            storeId: storeId,
            orderId: orderId
          }
        });

        if (response.data) {
          // 处理返回的订单数据
          const order = response.data;
          const creationTime = order.creatE_TIME ? new Date(order.creatE_TIME).toLocaleString() : 'N/A';
          let returnStatus = '';

          if (order.ordeR_STATUS === '待退货' && order.returN_OR_NOT) {
            returnStatus = '待同意';
          } else if (order.ordeR_STATUS === '已退货' && order.returN_OR_NOT) {
            returnStatus = '已同意';
          } else if (!order.returN_OR_NOT) {
            returnStatus = '待同意';
          }

          let orderStatusText = order.ordeR_STATUS;
          if (order.ordeR_STATUS === '已付款') {
            orderStatusText = '待发货';
          } else if (order.ordeR_STATUS === '待付款') {
            return null; // 过滤掉 "待付款" 的订单
          }

          const orderData = {
            id: order.ordeR_ID || 'N/A',
            creationTime: creationTime,
            orderStatus: orderStatusText || 'Unknown',
            orderPrice: order.totaL_PAY !== undefined ? order.totaL_PAY : 'N/A',
            practicalPrice: order.actuaL_PAY !== undefined ? order.actuaL_PAY : 'N/A',
            returnRequested: order.returN_OR_NOT != null ? order.returN_OR_NOT : false,
            returnStatus: returnStatus,
            remark: order.remark || 'No remarks',
            score: order.score !== undefined ? order.score : 'N/A',
            deliveryNumber: order.deliverY_NUMBER || 'N/A',
          };

          products.value = [orderData]; // 更新products为单个订单的数据
        } else {
          console.error('未找到订单');
        }
      } catch (error) {
        products.value = [];
        console.error('通过订单ID获取订单数据失败:', error);
      }
    };
    //根据创建时间筛选订单
    const searchOrderByTime = () => {
      if (searchTime.value.trim() !== '') {
        fetchOrderByTime(searchTime.value.trim());
      } else {
        fetchOrders();
      }
    };
    const fetchOrderByTime = async (date) => {
      const storeId =localStorage.getItem('userId');
      try {
        const response = await axiosInstance.get('/StoreOrder/GetOrdersByDate', {
          params: {
            storeId: storeId,
            date: date
          }
        });

        if (response.data && Array.isArray(response.data)) {
          const orders = response.data
              .map(order => {
                const creationTime = order.creatE_TIME ? new Date(order.creatE_TIME).toLocaleString() : 'N/A';
                let returnStatus = '';

                if (order.ordeR_STATUS === '待退货' && order.returN_OR_NOT) {
                  returnStatus = '待同意';
                } else if (order.ordeR_STATUS === '已退货' && order.returN_OR_NOT) {
                  returnStatus = '已同意';
                } else if (!order.returN_OR_NOT) {
                  returnStatus = '待同意';
                }

                let orderStatusText = order.ordeR_STATUS;
                if (order.ordeR_STATUS === '已付款') {
                  orderStatusText = '待发货';
                } else if (order.ordeR_STATUS === '待付款') {
                  return null; // 过滤掉 "待付款" 的订单
                }

                return {
                  id: order.ordeR_ID || 'N/A',
                  creationTime: creationTime,
                  orderStatus: orderStatusText || 'Unknown',
                  orderPrice: order.totaL_PAY !== undefined ? order.totaL_PAY : 'N/A',
                  practicalPrice: order.actuaL_PAY !== undefined ? order.actuaL_PAY : 'N/A',
                  returnRequested: order.returN_OR_NOT != null ? order.returN_OR_NOT : false,
                  returnStatus: returnStatus,
                  remark: order.remark || 'No remarks',
                  score: order.score !== undefined ? order.score : 'N/A',
                  deliveryNumber: order.deliverY_NUMBER || 'N/A',
                };
              })
              .filter(order => order !== null); // 过滤掉 "待付款" 的订单

          products.value = orders;
        } else {
          console.error('未找到订单');
        }
      } catch (error) {
        products.value = [];
        console.error('通过日期获取订单数据失败:', error);
      }
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
            result: result
          };
          item.result = result
          // 发送请求到后端确认退货
          const response = await axiosInstance.post( `/afterSell/returns/${item.itemId}/approveReturn`,
              requestBody,
              {
                headers: {
                  'Content-Type': 'application/json'
                }
              });

          if (response.status === 200) {

            row.returnStatus = '已完成';
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
          console.error('确认退货时发生错误:', error.response ? error.response.data : error.message);
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
      searchOrderById,
      searchTime,
      searchOrderByTime,
      updateDeliveryNumber,
      showArbitrateDialog,
      arbitrate
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