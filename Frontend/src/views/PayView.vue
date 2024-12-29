<template>
  <Loading v-show="isLoading || (!isLoading && isAlipayLoading)" />
  <div v-show="!isLoading && !isAlipayLoading" class="Pcontainer">
    <div></div>
    <div class="procedure">
      <div class="procedure1">
        <img src="@/assets/mmy/number1.svg" class="number" />
        <div class="text">1.确认订单信息</div>
      </div>
      <div class="line">———————</div>
      <div class="procedure2">
        <img src="@/assets/mmy/number2.svg" class="number" />
        <div class="text">&nbsp2.支付&nbsp&nbsp</div>
      </div>
      <div class="line" v-show="isPaid === true">———————</div>
      <div class="procedure3" v-show="isPaid === true">
        <img src="@/assets/mmy/number3.svg" class="number" />
        <div class="text-active">3.查看订单信息</div>
      </div>
    </div>
    <div class="address">
      <div class="highlight-text">收货信息</div>
      <div class="addressInfo">
        <img class="addressImage" src="@/assets/mmy/location.svg">
        <div class="text1">寄送至&#8201;&#8201;&#8201;&#8201;</div>
        <div class="customerAddress">{{ customer.address }}&#8201;&#8201;({{ customer.name }}&#8201;收)&#8201;&#8201;
        </div>
        <el-button @click="openDialog" class="changeAddress" style="
              font-size: 20px;
              border-radius: 5px;
              border: 2px solid #a61b29;
              color:#a61b29;
              cursor: pointer;
              width: auto;
              height:32px;
              margin-top:5px;
              padding-left:20px;
              padding-right:20px;
              right:40px;
              position: absolute">修改信息</el-button>
      </div>
      <el-dialog v-model="dialogVisible" width="35%" :style="{ borderRadius: '15px' }">
        <div class="dialog-content">

          <div class="dialog-line">
            <div class="dialog-text">&#8201;收&#8201;件&#8201;人&#8201; </div>
            <el-input v-model="customer.name" placeholder="请输入收件人姓名" clearable style="width: 350px;
                      height:40px;
                      font-size:17px;"></el-input>
          </div>

          <div class="dialog-line">
            <div class="dialog-text">所在地区 </div>
            <el-cascader :options='options' v-model='selectedOptions' placeholder="省/市/区（县）" class="custom-cascader"
              style="width: 350px;" @change='addressChange'></el-cascader>
          </div>
          <div class="dialog-line">
            <div class="dialog-text">详细地址 </div>
            <el-input v-model="address2" placeholder="请输入详细地址（具体到小区、门牌号等）" clearable style="width: 350px;
                      height:40px;
                      font-size:17px;"></el-input>
          </div>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button style="border: 2px solid rgba(0,0,0,0.4);
              width: 20%;
              height:40px;
              font-size:20px;">取 消</el-button>
          <el-button @click="changeBuyerInfo" style="border: 2px solid rgba(0,0,0,0.4);
               background-color:rgba(0,0,0,0.4);
               color:white;
               width: 20%;
               height:40px;
               font-size:20px;">确 定</el-button>
        </span>
      </el-dialog>

    </div>
    <!-- <div class="orderInfo">
      <div class="storeArea">
        <img src="@/assets/mmy/store-active.svg" class="storeImage" @click="enterStore">
        <div class="text2" @click="enterStore">{{ product.storeName }}&#8201;&#8201;></div>
      </div>
      <div class="productArea">
        <img :src="product.pictures ? product.pictures[0].imageUrl : ''" class="productImage" alt="图片加载失败">
        <div class="orderDetail">
          <div class="text-p">{{ product.name }}</div>
          <div class="text-p2">￥{{ product.price }}</div>
          <div class="text-p1">订单编号:&#8201;&#8201;{{ order.id }}</div>
          <div class="text-p1">创建时间:&#8201;&#8201;{{ order.createTime }}</div>
        </div>
      </div>
    </div> -->

    <div v-for="order in orderInfo" :key="order.orderId" class="orderInfo">
      <!-- 遍历该订单的商品 -->
      <div class="productArea" v-for="item in order.orderItems" :key="item.productId">
        <img :src="item.productImage ? item.productImage : ''" class="productImage" alt="图片加载失败">
        <div class="orderDetail">
          <div class="text-p">{{ item.productName }}</div>
          <div class="text-p2">￥{{ item.productPrice }}</div>
        </div>
      </div>
      <div class="text-p1">订单编号:&#8201;&#8201;{{ order.orderId }}</div>
      <div class="text-p1">创建时间:&#8201;&#8201;{{ order.createTime }}</div>
    </div>

    <div class="price" v-show="isNew === true">
      <div class="storeArea">
        <div class="text-price1">价格明细</div>
      </div>
      <div class="text-price">商品原价：&#8201;&#8201;{{ (totalOriginalPrice) }}元</div>
      <!-- 写不完了，去他妈的逻辑 -->
      <div>
        <div class="useCredit">
          <div class="text-price-active">是否使用积分</div>
          <el-radio-group v-model="isUseCredits" style="margin-left: 10px;margin-bottom:0px">
            <el-radio label="yes">是</el-radio>
            <el-radio label="no">否</el-radio>
          </el-radio-group>
        </div>
        <div class="text-price-active-small">当前积分{{ 10024 }},可抵扣{{ 10 }}元</div>
      </div>
      <div class="text-price-active">价格合计：{{ finalPrice }}元</div>
      <div class="pay">
        <el-button @click="checkPay" class="payMoney" style="
              font-size: 20px;
              border-radius: 5px;
              background-color: #a61b29;
              color:white;
              cursor: pointer;
              width: auto;
              height:43px;
              right:60px;
              bottom:20px;
              position: absolute">支付￥{{ finalPrice }}</el-button>
        <el-dialog v-model="payVisible" width="20%" :style="{ borderRadius: '15px' }">
          <div v-show="isPaySuccess === true">
            <p class="text_pay_isSuccess">支付成功</p>
            <p class="text_pay">获得积分：&#8201;&#8201;{{ bonusCredits }}</p>
          </div>
          <div v-show="isPaySuccess === false">
            <p class="text_pay_isSuccess">支付失败</p>
            <p class="text_pay">钱包余额不足，请及时充值</p>
          </div>

        </el-dialog>
      </div>
    </div>
    <el-dialog v-model="payChoiceVisible" width="20%" :style="{ borderRadius: '15px' }">
      <h2>请选择支付方式</h2>
      <el-radio-group v-model="payWay" style="display:flex;
            flex-direction: column;
            margin-left: 10px;
            margin-bottom:0px">
        <el-radio label="wallet">钱包</el-radio>
        <el-radio label="alipay">支付宝</el-radio>
      </el-radio-group>
      <el-button @click="openPay" style="
              font-size: 16px;
              border-radius: 5px;
              border: 2px solid #a61b29;
              background-color: #a61b29;
              color:#FFF;
              cursor: pointer;
              width: auto;
              height:32px;
              margin-top:5px;
              padding-left:20px;
              padding-right:20px;
              right:40px;">确认支付</el-button>
    </el-dialog>
  </div>
</template>
<!-- 去掉lang="ts"就不会标红了 -->
<script setup>
import { ref, computed, onMounted, watch } from 'vue';
import { ElDialog, ElButton, ElMessage } from 'element-plus';
import { regionData, codeToText } from 'element-china-area-data';
import axiosInstance from '../router/axios';
import { useRouter } from 'vue-router';
import Loading from '../views/templates/LoadingView.vue';

//接收路由参数
import { useRoute } from 'vue-router';
//页面加载
const isLoading = ref(true);
const isAlipayLoading = ref(false);
const route = useRoute();

// 是否是新建订单
const isNew = route.query.isNew === 'true';

const order = ref({ id: '', createTime: '' });
//收货人相关信息
const customer = ref({
  name: '',
  address: '',
  credits: 300
});
const dialogVisible = ref(false);
const payVisible = ref(false);
const payChoiceVisible = ref(false);
const address1 = ref('');
const address2 = ref('');
const isUseCredits = ref('no');
//用了toFixed(2)就是string类型的了
const creditPrice = ref(0);
// 省市区信息
const options = ref(regionData);
const selectedOptions = ref(['110000', '110100', '110101']);

//支付后获得积分以及剩余积分
const bonusCredits = ref(0);
//支付方式
const payWay = ref('wallet');
//是否支付成功
const isPaySuccess = ref(false);
////订单支付结束后（不管支付成功没成功）都跳转回原来的页面
const returnUrl = ref('');
const router = useRouter();
const routerPath = localStorage.getItem('routerPath');
watch(payVisible, (newValue, oldValue) => {
  if (newValue === false && oldValue === true) {
    // 当 `isPaySuccess` 变为 `false` 时执行操作
    console.log('支付失败，状态变为 false');
    // 跳转到指定页面
    const path = routerPath ? routerPath : '/home';
    router.push(path);
  }
});

const productIds = ref([]);
const orderIds = ref([]);
const orderInfo = ref([]);
onMounted(async () => {
  const productIdStr = route.query.product;
  if (productIdStr) {
    productIds.value = JSON.parse(productIdStr);
    console.log(`productIds.value is ${JSON.stringify(productIds.value, null, 2)}`)
  }
  const orderIdStr = route.query.orderIds;
  if (orderIdStr) {
    orderIds = JSON.parse(orderIdStr);
    console.log(`orderIds is ${JSON.stringify(orderIds, null, 2)}`)
  }
  if (isNew == true) {
    addOrders();
  } else {
    getOrders();
  }
}
)

const totalOriginalPrice = computed(() => {
  return orderInfo.value.reduce((total, order) => {
    return total + order.orderItems.reduce((subTotal, item) => subTotal + item.productPrice, 0);
  }, 0);
});

const finalPrice = computed(() => {
  if (isUseCredits.value === 'yes') {
    return totalOriginalPrice.value - 10; // 计算最终价格并保留两位小数
  } else {
    return totalOriginalPrice.value; // 如果不使用积分，最终价格就是折扣价格
  }
});
const openDialog = () => {
  dialogVisible.value = true;
}
const addOrders = async () => {
  try {
    const response = await axiosInstance.post(`/shopping/order/add-orders`, {
      // "productIds": productIds.value
      "productIds": [
        "p587121065594885",
        "p587123104530437",
        "p587124488126469",
        "p587173063831557",
        "p587176093687813",
        "p587180477075461"
      ]
    });
    isLoading.value = false;
    orderInfo.value = response.data.data;
    ElMessage.info(response.data.msg);
    console.log(`orderInfo is ${JSON.stringify(orderInfo.value, null, 2)}`);

    // 收货人信息
    customer.value.name = response.data.username;
    console.log(`customer.value.name is ${customer.value.name}`)
    if (customer.value.name == null) {
      customer.value.name = '未知收货人';
    }
    customer.value.address = response.data.address;
    if (customer.value.address == null) {
      customer.value.address = '未知地';
    }

  } catch (error) {
    ElMessage.error(error.response.data.msg);
  }

}
const getOrders = async () => {
  try {
    const response = await axiosInstance.get(`/shopping/order/getOrders`, {
      params: {
        "orderIds": orderIds
      }
    });
    isLoading.value = false;
    orderInfo.value = response.data.data;
    ElMessage.info(response.data.msg);
    console.log(`orderInfo is ${JSON.stringify(orderInfo.value, null, 2)}`);

    // 收货人信息
    customer.value.name = response.data.username;
    console.log(`customer.value.name is ${customer.value.name}`)
    if (customer.value.name == null) {
      customer.value.name = '未知收货人';
    }
    customer.value.address = response.data.address;
    if (customer.value.address == null) {
      customer.value.address = '未知地';
    }

  } catch (error) {
    ElMessage.error(error.response.data.msg);
  }
};
const changeBuyerInfo = async () => {
  if (!address1.value || !address2.value) {
    ElMessage.error('请保证地址不为空');
  } else {
    dialogVisible.value = false;
    customer.value.address = address1.value + address2.value;
    console.log(`customer.value.address is ${customer.value.address}`);
  }

  for (let i = 0; i < orderInfo.value.length; i++) {
    try {
      const response = await axiosInstance.post(`/shopping/order/change-buyer-info`, {
        "orderId": orderInfo.value[i].orderId,
        "name": customer.value.name,
        "address": customer.value.address
      });
      ElMessage.success(response.data.msg);
    } catch (error) {
      ElMessage.error(error.response.data.msg);
    }
  }


};
// 将地址编号转换为汉字
const addressChange = () => {
  const arr = selectedOptions.value;
  address1.value = codeToText[arr[0]] + codeToText[arr[1]] + codeToText[arr[2]];
  console.log(address1);
}

const checkPay = () => {
  if (customer.value.name === "未知收货人" || customer.value.address === "未知地") {
    ElMessage.error("请补充收货信息");
  } else {
    payChoiceVisible.value = true;
  }
}

//钱包支付
const openPay = async () => {
  payChoiceVisible.value = false;
  payVisible.value = true;
  for (let i = 0; i < orderInfo.value.length; i++) {
    try {
      const response = await axiosInstance.post(`/shopping/order/pay-order`, {
        "orderId": orderInfo.value[i].orderId,
        "usedCredits": isUseCredits.value === 'yes' ? 200 : 0,
      });
      bonusCredits.value += response.data.bonus;
      isPaySuccess.value = true;
    } catch (error) {
      isPaySuccess.value = false;
    }
  }
}

</script>

<style scoped>
div {
  user-select: none;
  outline: none;
  cursor: default;
}

:deep(.el-radio.is-checked .el-radio__inner) {
  border-color: #a61b29;
  /* 修改边框颜色 */
  background-color: #a61b29;
  /* 修改选中颜色 */
}

:deep(.el-radio .el-radio__label) {
  color: #a61b29;
  /* 修改文字颜色 */
  font-size: 16px;
  /* 修改文字大小 */
}

.Pcontainer {
  font-family: 'Noto Serif SC', serif;
  background-color: rgba(204, 204, 204, 0.4);
  display: flex;
  align-items: center;
  /*这才是水平对齐 */
  flex-direction: column;
  height: 100vh;
  overflow-x: hidden;
  /*隐藏水平滚动条 */
  overflow: auto;
  background-image: url("../assets/mmy/background.jpg");

  /* position:relative; */
}

.procedure {
  margin-top: 20px;
  display: flex;
  align-items: center;
  /*这才是水平对齐 */
  flex-direction: row;
  align-items: right;
}

.procedure1,
.procedure2,
.procedure3 {
  display: flex;
  flex-direction: column;
  align-items: center;
  /*这才是水平对齐 */
}

.number {
  width: 40px;
  /* 调整图标大小 */
  height: 40px;
  /* 调整图标大小 */
  margin-right: 8px;
  /* 图标与文本之间的间距 */
}

.text {
  font-size: 15px;
}

.text-active {
  font-size: 15px;
  color: #a61b29;
}

.line {
  padding-bottom: 15px;
  padding-right: 20px;
}

.address,
.orderInfo,
.price {
  background-color: #FFFFFF;
  width: 900px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  /* 添加阴影效果（可选） */
  box-sizing: border-box;
  /* 使内边距和边框包含在宽度和高度内 */
  border-radius: 15px;
  position: relative;
  margin-top: 15px;
}

.orderInfo {
  padding: 5px;
}

.address {
  display: flex;
  flex-direction: column;
  /* height:40px; */
  min-height: 90px;
}

.addressInfo {
  display: flex;
  flex-direction: row;
  width: 100%;
}

.addressImage {
  width: 35px;
  height: 30px;
  padding-top: 5px;
}

.text1,
.customerAddress {
  font-size: 20px;
  padding-top: 5px;
}

.changeAddress:hover {
  color: black;
  /* 鼠标悬停时文字颜色保持不变 */
  background-color: white;
  /* 鼠标悬停时背景颜色保持不变 */
  border: 2px solid black;
  /* 鼠标悬停时边框颜色保持不变 */
}

.dialog-content {
  display: flex;
  flex-direction: column;
  /* 使内容竖直排列 */
  gap: 20px;
}

.dialog-line {
  display: flex;
  justify-content: center;
  flex-direction: row;
}

.dialog-text {
  padding-right: 10px;
  padding-top: 5px;
  font-size: 20px;
}

.dialog-footer {
  display: flex;
  justify-content: center;
  padding-top: 25px;
}

.orderInfo {
  display: flex;
  flex-direction: column;
  /* height: 250px; */
  padding-left: 20px;
}

.storeArea {
  display: flex;
  flex-direction: row;
  padding-top: 10px;
}

.storeImage {
  width: 30px;
  height: 30px;
  padding-right: 5px;
  cursor: pointer;
}

.productArea {
  margin-top: 20px;
  min-height: 150px;
  display: flex;
  flex-direction: row;
}

.productImage {
  width: 125px;
  height: 125px;
  border-radius: 5px;
  margin-left: 30px;
  background-color: #a61b29;
  color: #FFFFFF;
}

.orderDetail {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding-left: 50px;
}

.text2 {
  font-size: 20px;
  margin-right: 10px;
  cursor: pointer;
  color: #a61b29;
}

.text-p {
  font-size: 23px;
}

.text-p1 {
  font-size: 20px;
  color: rgba(0, 0, 0, 0.6);
}

.text-p2 {
  font-size: 19px;
  color: rgba(0, 0, 0, 0.8);
}

.price {
  display: flex;
  flex-direction: column;
  min-height: 250px;
  align-items: start;
  padding: 10px 0px 15px 20px;
  position: relative;
}

.highlight-text {
  width: 100%;
  border-radius: 15px 15px 0px 0px;
  background-color: #a61b29;
  color: #FFFFFF;

  text-align: left;
  padding-left: 20px;
  font-size: 21px;
  padding-top: 5px;
  padding-bottom: 5px;
}

.text-price {
  padding-left: 20px;
  font-size: 20px;
  padding-bottom: 2px;
}

.text-price-active {
  padding-left: 20px;
  font-size: 20px;
  /* padding-bottom: 2px; */
  font-weight: bold;
  color: #a61b29;
}

.text-price-active-small {
  padding-left: 60px;
  font-size: 17px;
  padding-bottom: 2px;
  font-weight: bold;
  color: #000000;
}

.text-price1 {
  font-size: 20px;
  text-align: left;
  color: #a61b29;
  padding-bottom: 10px;
}

.useCredit {
  display: flex;
  flex-direction: row;
}

.text_pay_isSuccess {
  font-size: 22px;
  margin-bottom: 10px;
}

.text_pay {
  margin-bottom: 7px;
  font-size: 20px;
}

.payMoney:hover {
  transform: scale(1.02);
  /* Scale up by 20% */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.3);
}

.loading-text {
  font-size: 22px;
}
</style>