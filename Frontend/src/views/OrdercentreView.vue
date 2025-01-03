<!-- 卖家用户的订单中心页面 -->
<script setup lang="ts">
import { ref ,computed,reactive} from 'vue';
import { ElMenu,ElButton, ElInput,ElRate,ElMessage,ElDialog} from 'element-plus';
import 'element-plus/dist/index.css';
import Navbar from '../components/Navbar.vue';
import axiosInstance from '../router/axios';
import router from '@/router';
import Loading from '../views/templates/LoadingView.vue';

const userId =localStorage.getItem('userId');
console.log('id',userId);
const option=ref(1);
const isNoData=ref(false);
const currentRow_cancel=ref(null);
const isLoading = ref(true);
const currentRow_return=ref(null);
const currentRow_star = ref(null);
const returnO=ref('');
const comment=ref(null);
const starVisible=ref(false);
const dialogVisible=ref(false);
const star=ref(null);
const returnProduct=ref('');
const returnProduct_else=ref('');
const returnVisible=ref(true);
const deliveryNumberInput=ref(null);
const returnToSalerId=ref(null);

interface OrderItemDTO {
  itemId:string;
  productId?: string;
  productName?: string;
  productImage?: string;
  productPrice?: number;
  itemStatus?:string;
}
// 前端使用的 Order 类型
interface Order {
  id: string;
  store: string;
  time: string;
  totalPay: number;
  status: string;
  paid: boolean;
  orderItems: OrderItemDTO[];
  // UI 相关字段
}
const filteredOrders = computed(() => {
  if (option.value==1) {
    return myOrders.value;
  }
  const statusMapping: { [key: number]: string } = {
    2: '待付款',
    3: '处理中',
    4: '运输中',
    5: '已完成',
    6: '售后中',
    7: '售后结束',
    8: '一元购'
  };
  if(option.value===6||option.value===7) {
    return myOrders.value.filter(order =>
        order.orderItems.some(item => item.itemStatus === statusMapping[option.value])
    );}
  else if(option.value!=8) {
    return  myOrders.value.filter(order => order.status === statusMapping[option.value]);}
  else {
    return myOneYuanOrders.value;}
});
const myOrders = ref<Order[]>([])

//   const getMyOrder = async () => {
//   axiosInstance.get('/shopping/order/get-all-buyer-orders', {
//       })
//     .then(response => {
//         const data = response.data;
//         console.log('Raw response data: ', response.data);
//         if (data && Array.isArray(data)) {
//           myOrders.value = data.map((order: any) => ({
//             id: order.orderId || '',
//             time: order.createTime || '',
//             store: order.storeName || '',
//             status: order.orderStatus || '',
//             price:order.price||0,
//             totalPay: order.totalPay || 0,
//             orderItems: order.orderItems.map(item => ({
//               productId: item.productId || '',
//               productName:item.productName.imageUrl||'',
//               productImage: item.productImage || '',
//               productPrice:item.productPrice|| 0,
//             })),
//             star: null,
//             dialogVisible: false,
//             dialogVisible_order:false,
//             starVisible: false,
//             isStar:false,
//             }));
//             console.log("成功获得订单");
//             console.log(myOrders.value);
//             myOrders.value.forEach(order => {
//             isStarred(order);
//             isLoading.value=false;
//           });
//
//         } else {
//             console.error('Invalid data format.');
//         }
//     }).catch(error => {
//         console.error(error);
//         if (error.response && error.response.status === 404) {
//           isNoData.value=true;
//           isLoading.value=false;
//         }
//     });
// };
// 获取订单数据
const getOrders = async () => {
  try {
    const response = await axiosInstance.get('/shopping/order/get-all-buyer-orders')
    console.log('Raw response:', response.data)

    if (response.data.code === 200 && response.data.data) {
      myOrders.value = response.data.data.map(order => ({
        id: order.orderId || '',
        store: order.storeName || '',
        time: order.createTime || '',
        totalPay: order.totalPay || 0,
        status: order.orderStatus || '',
        paid: order.paid || false,
        orderItems: (order.orderItems || []).map(item => ({
          itemId: item.itemId || '',
          productId: item.productId || '',
          productName: item.productName || '',
          productImage: item.productImage || '',
          productPrice: item.productPrice || 0,
          itemStatus: item.itemStatus || '无'
        })),
        // UI 状态字段
        star: null,
        dialogVisible: false,
        dialogVisible_order: false,
        starVisible: false,
        isStar: false
      }))
      console.log("成功获得订单", myOrders.value)
      isLoading.value=false;
    }
  } catch (error) {
    console.error('获取订单失败:', error)
    isNoData.value=true;
    isLoading.value=false;
  }
}
getOrders();
const myOneYuanOrders = ref([]);
const getOneYuanOrders = async () => {
  try {
    const response = await axiosInstance.get('/shopping/participated-records')
    // 存储原始一元购记录的数组
    const originalProducts = response.data.data;
    // console.log("一元购商品详情", originalProducts);
    // 遍历一元购记录，获取每个商品的详细信息
    for (const product of originalProducts) {
      try {
        // 根据商品ID获取详细信息
        const orderResponse = await axiosInstance.get(`/productController/product/${product.productId}`);

        // 合并两次返回的数据
        const combinedOrderInfo = {
          ...product, // 原始记录的信息
          ...orderResponse.data, // 详细订单信息
        };
        // 将详细信息放入 myOneYuanOrders
        myOneYuanOrders.value.push(combinedOrderInfo);
        console.log("一元购商品详情", myOneYuanOrders);
      } catch (productError) {
        console.error(`获取商品 ${product.productId} 详情失败`, productError);
      }
    }
  } catch (error) {
    console.error('获取一元购订单失败:', error)
    isNoData.value=true;
    isLoading.value=false;
  }
}
getOneYuanOrders();
// const isStarred = async (order: Order) => {
//   axiosInstance.get('/Shopping/CheckOrderRemark', {
//         params: {
//         orderId: order.id
//      }
//       })
//     .then(response => {
//       const isStarred = response.data; // 直接获取布尔结果
//       order.isStar = isStarred;
//     }).catch(error => {
//         console.error(error);
//     });
// };
// const returnProduct=ref('');
// const returnProduct_else=ref('');
// const comment=ref('');
// const admit = (order) => {
//   order.dialogVisible = false;
//   resetForm(order);
// }
const admit = async (order) => {
  console.log(order);
  if(returnProduct.value=='其他'){
    returnProduct.value=returnProduct_else.value;
  }
  axiosInstance.post(`/afterSell/orderItem/${order}/return`, {
    reason: returnProduct.value === '其他' ? returnProduct_else.value : returnProduct.value
  })
      .then(response => {
        resetForm(order);
        console.log('submitting return:成功');
        ElMessage({
          message: '申请退货成功',
          type: 'success',
        });
        getOrders();
        resetForm ()
      }).catch(error => {
    console.error('Error submitting return:', error);
  });
};
// const admitOrder = async (order) => {
//   if(returnProduct.value=='其他'){
//     returnProduct.value=returnProduct_else.value;
//   }
//     axiosInstance.delete('/Payment/DeleteOrders', {
//       params: {
//         orderId: order.id
//       }
//    })
//   .then(response => {
//     resetForm(order);
//     console.log('取消订单:成功');
//     ElMessage({
//         message: '成功取消订单',
//         type: 'success',
//         });
//         getMyOrder();
//     }).catch(error => {
//       console.error('Error submitting return:', error);
//     });
// };
// // function confirmStar(order)
// // {
// //   row.starVisible = false;
// //   row.orderStatus = '交易成功';
// // }
const confirmStar = async (order) => {
  try {
    const response = await axiosInstance.put('/shopping/order/remark-order-item', {
      orderItemId: String(order),
      remark: comment.value,
      score: star.value,
    });

    if (response.data.code === 200) {
      starVisible.value = false;
      star.value = null;
      comment.value = '';
      ElMessage({
        message: response.data.msg || '评价成功',
        type: 'success',
      });
      await getOrders();
    }

  } catch (error) {
    console.error('Error submitting rating:', error);
    ElMessage({
      message: '评价失败',
      type: 'error',
    });
  }
};

// function startRating(order) {
//   order.starVisible = true;
//   currentRow_star.value=order;
// }
function startRating(id) {
  starVisible.value = true;
  currentRow_star.value=id;
}
function returnOrder(order) {
  dialogVisible.value = true;
  currentRow_return.value=order;
};
// function cancelOrder(order) {
//   order.dialogVisible_order = true;
//   currentRow_cancel.value=order;
// };
function resetForm () {
  dialogVisible.value = false;
  currentRow_return.value=null;
  // dialogVisible_order.value=null;
  returnProduct.value = '';
  returnProduct_else.value=''
}
function resetStar(order)
{
  star.value=null;
  starVisible.value=false;
}

function confirmArrive(order)
{
  console.log(order.id)
  axiosInstance.post('/shopping/order/receive-order',{
    orderId: order.id
  })
      .then(response => {
        console.log('签收成功');
        ElMessage({
          message: '成功签收',
          type: 'success',
        });
        getOrders();
      }).catch(error => {
    console.error('Error confirm:', error);
  });
}

const returnToSaler = (id) => {
  returnVisible.value = true;
  returnToSalerId.value = id;
  console.log("退货",returnVisible.value);
};

const resetReturnForm=()=>{
  returnVisible.value = false;
  returnToSalerId.value = null;
}

//更新快递单号
const updateDeliveryNumber = async () => {
  console.log(deliveryNumberInput.value)
  try {
    const deliveryNumber = deliveryNumberInput.value;

    // 发送请求到后端更新快递单号
    const response = await axiosInstance.put('/afterSell/return/${returnToSalerId.value}/addExpressNumber', {
      deliveryNumber: deliveryNumber
    });

    if (response.status === 200) {
      ElMessage({
        message: '快递单号已更新',
        type: 'success'
      });
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

// function deleteRow(order) {
//   this.orders = this.orders.filter(order => order !== row);
//   this.filteredOrders = this.filteredOrders.filter(order => order !== row);
// }

// const product = ref({
//   name: '',
//   pictures: [ // 将 picture 改为 pictures 数组
//     {
//       imageId: '',
//       imageUrl: ''
//     }
//   ],
//   price: 0, // 原价格
//   storeName: '',
//   discountPrice: 0, // 折后价格
//   finalPrice: 0, // 最终支付的价格，考虑到会有积分使用
// });

// function gotoDetail(order:Order){
//   product.value.name=order.product;
//   product.value.pictures[0].imageId=order.picId;
//   product.value.pictures[0].imageUrl=order.pic;
//   product.value.price=order.price;
//   product.value.storeName=order.store;
//
//   product.value.finalPrice=order.actualPay;
//
//   var totalPay = order.totalPay;
//   var price = order.price;
//
// // 计算折扣力度
//   var discountRate = (totalPay / price).toFixed(2);
//   product.value.discountPrice = parseFloat(discountRate);
//
//   console.log("折扣力度",product.value.discountPrice);
//   const productStr = JSON.stringify(product.value);//序列化对象
//   console.log("序列化",productStr);
//   localStorage.setItem('productIdOfDetail',order.productId);
//   localStorage.setItem('routerPath','/ordercentre');
//   if(order.status=='待付款'){
//     router.push({path:'/pay',query:{product: productStr,isPaid:'false'}});
//   }
//   else{
//     router.push({path:'/pay',query:{product: productStr,isPaid:'true'}});
//   }
// }
const  colors=['#99A9BF', '#F7BA2A', '#FF9900'] ;// 等同于 { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
const activeIndex = ref('1');
const menuItems = ref([
  { index: 1, title: '全部' },
  { index: 2, title: '待付款' },
  { index: 3, title: '处理中' },
  { index: 4, title: '运输中' },
  { index: 5, title: '已完成' },
  { index: 6, title: '售后中' },
  { index: 7, title: '售后结束' },
  { index: 8, title: '一元购' }
]);
const menuChange = (index) => {
  activeIndex.value = index.toString();
  option.value = parseInt(index);
};
</script>

<template>
  <Navbar />
  <el-container>
    <el-aside width="18vh" style="background-color:  #82111f; ">
      <div class="big-title" style="display: flex; justify-content: center; align-items: center; width: 100%;">
        <!--    <img class="image" src="@/assets/czw/aside.svg" alt="Original Image"  />-->
        <span>订单</span>
        <!--    <img class="flipped-image" src="@/assets/czw/aside.svg" alt="Flipped Image" />-->
      </div>
      <div style="width: 100%;">
        <el-menu
            :default-active="activeIndex"
            mode="vertical"
            background-color="#ffffff"
            text-color="black"
            active-text-color="#d42517"
            @select="menuChange"
            style="width: 100%;"
        >
          <el-menu-item
              v-for="item in menuItems"
              :key="item.index"
              :index="item.index.toString()"

          >
            <span class="el-icon-menu">{{ item.title }}</span>
          </el-menu-item>
        </el-menu>
      </div>
    </el-aside>
    <el-container>
      <el-header style="text-align: left">
        <div style="line-height: 6vh;">
          <span v-if="option === 1" style="font-size: 2vh; color: #333;">全部</span>
          <span v-if="option === 2" style="font-size: 2vh; color: #333;">待付款</span>
          <span v-if="option === 3" style="font-size: 2vh; color: #333;">处理中</span>
          <span v-if="option === 4" style="font-size: 2vh; color: #333;">运输中</span>
          <span v-if="option === 5" style="font-size: 2vh; color: #333;">已完成</span>
          <span v-if="option === 6" style="font-size: 2vh; color: #333;">售后中</span>
          <span v-if="option === 7" style="font-size: 2vh; color: #333;">售后结束</span>
          <span v-if="option === 8" style="font-size: 2vh; color: #333;">一元购</span>
        </div>
      </el-header>
      <Loading v-if="isLoading" />
      <div v-else-if="isNoData">暂无订单</div>
      <el-table  v-else-if="option!=8" :data="filteredOrders" style="width: 100%">
        <el-table-column label="商品信息">
          <template v-slot="scope">
            <div class="order-header">
              <p>{{ scope.row.time }} 订单号: {{ scope.row.id }}</p>
            </div>
            <div style="display: flex; flex-direction: column;">
              <div  v-for="item in scope.row.orderItems" :key="item.productId" style="display: flex; margin-bottom: 10px;">
                <img
                    :src="item.productImage"
                    alt="Order Image"
                    style="max-width: 100px; max-height: 100px;"
                />
                <div style="margin-left: 10px;">
                  <p>{{ item.productName }}</p>
                  <p>价格: {{ item.productPrice }}</p>
                </div>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="store" label="店铺" width="160"></el-table-column>
        <el-table-column prop="province" label="商品状态" width="160">
          <template v-slot="scope">
            <div v-for="item in scope.row.orderItems" :key="'status-' + (item?.itemId || '')">
              <!-- 当状态为"无售后"时显示按钮 -->
              <el-button
                  type="text"
                  v-if="scope.row.status === '已完成' && item?.itemStatus === '无售后'"
                  @click="returnOrder(item.itemId)"
              >申请退货</el-button>
              <!-- 其他情况显示状态文本 -->
              <span v-else>{{ item?.itemStatus || '无' }}</span>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="totalPay" label="实付款" width="160"></el-table-column>
        <el-table-column prop="status" label="订单状态" width="160"></el-table-column>
        <el-table-column prop="zip" label="操作" width="160">
          <template v-slot="scope">
            <!--      <el-button type="text" v-if="scope.row.isStar === false&&scope.row.status === '已完成'" @click="startRating(scope.row)">去评价</el-button>-->
            <div v-for="item in scope.row.orderItems" :key="'status-' + (item?.itemId || '')">
            <el-button
                type="text"
                v-if="scope.row.status === '已完成'&&item.itemStatus!='售后中'"
                @click="startRating(item.itemId)"
            >去评价
            </el-button>
              <el-button
                  type="text"
                  v-if="scope.row.status === '已完成'&&item.itemStatus==='售后中'"
                  @click="returnToSaler(item.itemId)"
              >邮寄退货
              </el-button>
            </div>
            <el-button type="text" v-if="scope.row.status === '运输中'" @click="confirmArrive(scope.row)">确认收货</el-button>
          </template>
        </el-table-column>
        <!--  <el-table-column prop="o" label="订单详情" width="160">-->
        <!--    <template v-slot="scope">-->
        <!--    <el-button type="text" v-if="scope.row.orderStatus === '待付款'" @click="gotoDetail(scope.row)">去付款</el-button>-->
        <!--    <el-button type="text" v-else @click="gotoDetail(scope.row)">点击详情</el-button>-->
        <!--    </template>-->
        <!--  </el-table-column>-->
      </el-table>

<!--      一元购-->
      <el-table  v-else :data="myOneYuanOrders" style="width: 100%">
        <el-table-column label="商品信息">
          <template v-slot="scope">
            <div class="order-header">
              <p>记录: {{ scope.row.recordId }}</p>
            </div>
            <div style="display: flex; flex-direction: column;">
              <div   style="display: flex; margin-bottom: 10px;">
                <div style="margin-left: 10px;">
                  <p>{{  scope.row.productName }}</p>
                  <p>价格: {{  scope.row.productPrice }}</p>
                </div>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column  prop="province" label="结果" width="160">
          <template v-slot="scope">
           <div v-if="scope.row.result!=null">
             {{scope.row.result}}
           </div>
            <div v-else>结果未出</div>
          </template>
        </el-table-column>
        <el-table-column prop="endTime" label="结束时间" width="160"></el-table-column>
        <el-table-column prop="minParticipants" label="最小参与者" width="160"></el-table-column>
        <el-table-column prop="currentParticipants" label="当前参与者" width="160"></el-table-column>
      </el-table>
    </el-container>

    <!-- 快递单号 -->
    <div >
      <el-dialog
          customClass="custom-dialog"
          title="快递单号"
          @close="resetReturnForm()">
        :style="{ zIndex: 9999 }"
        <div style="margin-top: 10px;"></div>
        <el-input v-model="deliveryNumberInput" placeholder="请输入快递单号"></el-input>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="updateDeliveryNumber">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <div v-if="currentRow_star!=null">
      <el-dialog
          customClass="custom-dialog"
          :draggable="true"
          :style="{ zIndex: 9999 }"
          :model-append-to-body="true"
          :modal="false"
          v-model="starVisible"
          title="评价"
          width="460px"
          @close="resetStar(currentRow_star)"
          :z-index="2000"
      >
        <el-rate v-if="starVisible" v-model="star" :colors="colors"></el-rate>
        <el-input v-if="starVisible" v-model="comment" placeholder="请输入评价" class="oo"></el-input>
        <el-button type="primary" @click="confirmStar(currentRow_star)">确定</el-button>
      </el-dialog>
    </div>
    <div v-if="currentRow_return!=null">
      <el-dialog
          customClass="custom-dialog"
          :draggable="true"
          :modalAppendToBody="false"
          :modal="false"
          v-model="dialogVisible" title="退货原因" width="460px" @close="resetForm()">
        <el-select v-model="returnProduct" placeholder="请选择原因">
          <el-option label="商品与图片不符" value="商品与图片不符"></el-option>
          <el-option label="商品破损" value="商品破损"></el-option>
          <el-option label="商品假冒" value="商品假冒"></el-option>
          <el-option label="不想要了" value="不想要了"></el-option>
          <el-option label="其他" value="其他"></el-option>
        </el-select>
        <div style="margin-top: 10px;"></div>
        <el-input v-if="returnProduct === '其他' "v-model="returnProduct_else" placeholder="请输入原因"></el-input>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="admit(currentRow_return)">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </el-container>
</template>


<style scoped>
.oo{
  margin-bottom: 3vh;
}
.custom-dialog {
  position: absolute;
  top: 100%; /* 让评价组件在菜单项的下方 */
  left: 0;
  z-index: 1000; /* 提高层级以确保其显示在前面 */
}
.image {
  width: 30px; /* 根据需求调整大小 */
  height: auto;
  /* transform: rotate(-90deg);*/
}

.flipped-image {
  width: 30px; /* 确保与第一张图一致 */
  height: auto;
  transform: scaleX(-1); /* 使用CSS进行垂直翻转 */

}
.big-title{
  font-size:4vh;  /* 将字体大小设置为32像素 */
  font-weight: bold; /* 可选：让文字加粗 */
  text-align: center; /* 可选：使文字水平居中 */
  margin: 20px 0; /* 可选：设置上下的外边距 */
  font-family: "华文中宋", sans-serif;
  color: #ffffff; /* 设置文字颜色为白色 */
}
.el-menu {
  position: relative;
  overflow: hidden;
}

.el-menu-item {
  position: relative;
  transition: background-color 0.3s ease;
}

.el-menu-item::before {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  width: 0;
  height: 100%;
  background-color: #bdaead;
  z-index: 0;
  transition: width 0.3s ease;
}

.el-menu-item:hover::before {
  width: 100%;
}

.el-menu-item > span {
  position: relative;
  z-index: 1; /* 确保文本始终在滑块上方 */
  transition: color 0.3s ease;
}

.el-menu-item:hover {
  color: #ffffff;
}
.el-icon-menu{
  font-size:2vh;
  font-weight: bold; /* 可选：让文字加粗 */
  font-family: "华文中宋", sans-serif;
}
.el-header {
  background-color: #ffffff;
  color: #333;
  line-height: 18vh;
}
.order_img {
  max-width: 37vh;
  max-height: 37vh;
}
.order-divider {
  border-bottom: 1px solid #839fe0; /* 添加底部边框样式 */
  width: 100%;
}

</style>
