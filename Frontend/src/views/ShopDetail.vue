<template>

  <div >
    <div class="header1" v-show="role==='买家'">
      <Navbar  />
    </div>
    <div class="header2" v-show="role==='商家'">
      <el-button 
              @click="enterSellerHome"
              style="display: flex;
              align-items: center;
              justify-content: center;
              font-size: 21px;
              border-radius: 5px;
              border: 2px solid #FFFFFF;
              background-color:#a61b29;
              color:#FFFFFF;
              cursor: pointer;
              width: auto;"
            >
            返回首页
        </el-button>
    </div>

    <Loading v-show="isLoading" />
    
    <div v-show="!isLoading" class="main-container">
      <div class="main-content">
        <div class="shop-title">
          <div style="display: flex; gap: 10px">
            <div class="shop-avatar">
              <img src="../assets/mmy/avatar.jpeg" alt="Shop Avatar" class="shop-avatar" />
            </div>
            <div class="shop-info">
                <div class="shop-name">
                    <span>{{shopinfo.storeName}}</span>
                </div>
                <div class="other-info">
                    <span>本店评分：{{shopinfo.storeScore}}</span>
                    <span>本店地址：{{shopinfo.Address}}</span>
                    <span>本店简介：{{shopinfo.description}}</span>
                </div>
            </div>
          </div>
          <div class="favoriteButton">
            <el-button 
              v-show="role==='买家'"
              :class="isFavorite ? 'favorite-active' : 'favorite-inactive'" 
              @click="clickFavorite" 
            >
              {{ isFavorite ? '已收藏' : '收藏' }}
            </el-button>
          </div>
        </div>

        <div class="toggle-row">
          <div class="blank"></div>
          <el-button :class="{ active: currentView === 'products' }" @click="currentView = 'products'">商品</el-button>
          <el-button :class="{ active: currentView === 'remarks' }" @click="currentView = 'remarks'">评价</el-button>
        </div>

        <div v-if="currentView === 'products'" class="shop-content">
          <div class="sidebar-category">
            <div role="tree" class="sidebar">
              <div
                v-for="category in categories"
                :key="category.id"
                :id="'category-' + category.id"
                role="treeitem"
                tabindex="0"
                :class="{ selected: selectedCategory === category.id }"
                class="category"
                @click="filterProducts(category,'Sider')"
              >
                {{ category.name }}
              </div>
            </div>
          </div>
          <div class="product-display">
            <div class="product" v-if="products.length!==0">
              <div class="display-items">
                <div 
                  v-for="product in products" 
                  :key="product.productId" 
                  class="product-item"
                >
                  <div @click="handleProductClick(product.productId)">
                    <img :src="product.imageurl" :alt="product.productId" class="product-image" />
                    <!-- <img 
                      src="../assets/wall.jpg"
                      class="product-image" 
                    /> -->
                    <div class="product-text">
                      <h2>{{ product.productName }}</h2>
                      <p>价格: ¥{{ product.productPrice }}</p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div v-else>
              <div style="height:30px"></div>
              <span style="font-family: Arial, sans-serif; font-size: 16px; display: block; margin-bottom: 13px;">
                本店暂无商品
              </span>
            </div>
          </div>
        </div>
          <!-- 评价展示内容 -->
        <div v-if="currentView === 'remarks'" class="shop-remarks">
          <div class="remarks-content">
            <span style="font-family: Arial, sans-serif; font-size: 20px; font-weight: bold; text-align: left; display: block; margin-bottom: 13px;">
              用户评价
            </span>
            <div v-if="remarks.length !== 0">
              <div 
                v-for="remark in remarks" 
                :key="remark.orderId" 
                class="remarks">
                <div class="remark-header">
                  <div class='remark-avatar'>
                    <img src="../assets/mmy/avatar2.jpg" :alt="buyerAvatar" class='remark-avatar'/>
                  </div>
                  <div class="remark-buyerName" style="font-size: 16px; font-weight: bold; "> {{ remark.buyerName }} </div>
                </div>
                <div class="remark-content">
                  <div class="remark-score" style="font-size: 16px; text-align: left;">
                    评分：{{ remark.orderScore }}
                  </div>
                  <div class="remark-text" style="font-size: 16px; text-align: left;">
                    评价：{{ remark.orderRemark }}
                  </div>
                  <div class="splitLine"></div>
                </div>
              </div>
            </div>
            <div v-else>
              <span style="font-family: Arial, sans-serif; font-size: 20px; display: block; margin-bottom: 13px;">
              暂无评价
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
  
<script setup>
import { reactive, ref, onMounted  } from 'vue';
import { useRouter } from 'vue-router';
import Navbar from '../components/Navbar.vue';
import Loading from '../views/templates/LoadingView.vue';
import { ElButton, ElMessage, ElInput } from 'element-plus';
import 'element-plus/dist/index.css';
import axiosInstance from '../router/axios';

const router = useRouter();
const currentView = ref('products');
const role=localStorage.getItem('role');
const storeId = localStorage.getItem('storeIdOfDetail');

const shopinfo = reactive({avatar:"",storeId:"",storeName:"",storeScore:0,Address:"",description:""});
shopinfo.storeId = storeId;
const isFavorite = ref(0);
const categories = ref([
  { id: 1, name: '全部商品' },
]);
const products = reactive([]);
const remarks = reactive([]);

const isLoading = ref(true);

const checkLoadingStatus = () => {
  isLoading.value = false;
};

const enterSellerHome=()=>{
  router.push('/businesshomepage');
}
  

const selectedCategory = ref(1);



const handleProductClick = (productId) => {
  localStorage.setItem('productIdOfDetail',productId);
  router.push('/productdetail');
};


const message1 = ref('');
const fetchStoreInfo = async () => {
  try {
    const response = await axiosInstance.get(`/users/store/Info/${storeId}`);
    const data = response.data;
    shopinfo.storeName = data.data.storeName;
    shopinfo.storeScore = data.data.storeScore;
    shopinfo.Address = data.data.address;
    shopinfo.avatar = data.data.photoId;
    shopinfo.description = data.data.description;
    message1.value = '已获取店铺信息';
    checkLoadingStatus(); //检查加载状态
  } catch (error) {
    if (error.response) {
      message1.value = error.response.data.msg;
    } else {
      message1.value = '获取店铺信息失败';
    }
  }
  console.log(message1.value);
};

const message2 = ref('');
const fetchIsBookmarked = async () => {
  try {
    const response = await axiosInstance.get('shopping/favourite/is-store-bookmarked', {
      params: {
        storeId: shopinfo.storeId
      }
    });
    isFavorite.value = response.data.data;
    message2.value = '已获取收藏信息';
    checkLoadingStatus(); //检查加载状态
  } catch (error) {
    if (error.response) {
      message2.value = error.response.data.msg;
    } else {
      message2.value = '获取收藏信息失败';
    }
  }
  console.log(message2.value);
};


const message3= ref('');
const fetchAllProducts = async () => {
  try {
    const response = await axiosInstance.get(`/productController/GetProductsByStoreIdAndViewType`,{
      params: {
        storeId: shopinfo.storeId,
        isBuyer:role=='买家'
      }
    });
    if (products.length > 0) {
      products.splice(0, products.length);//清空数组
    }
    response.data.forEach(product => {
      products.push(product);
    });
    message3.value = '已获取全部商品信息';
    checkLoadingStatus(); 
  } catch (error) {
    if (error.response) {
      message3.value = error.response.data;
    } else {
      message3.value = '获取全部信息失败';
    }
  }
  console.log(message3.value);
};

const clickFavorite = () => {
    bookmarkStore();
};

const bookmarkStore = async () => {
  try {
    const response = await axiosInstance.post('/shopping/favourite/bookmark-store',{
      "storeId": shopinfo.storeId
    }
    );
    if (response.data.code == 200) {
      isFavorite.value=!isFavorite.value;
    } 
  } catch (error) {
    ElMessage.error(error.response.data.msg);
  }
};

const fetchProductsByTag = async (tag) => {
  try {
    const response = await axiosInstance.get(`/productController/products/${shopinfo.storeId}`, {
    });
    if (products.length > 0) {
      products.splice(0, products.length);//清空数组
    }
    response.data.forEach(product => {
      if(product.storeTag==tag){
        products.push(product);
      }
    });
    checkLoadingStatus(); 
  } catch (error) {
    ElMessage.error(error.response.data.msg);
  }
};

const message8= ref('');
const fetchRemarks = async () => {
  try {
    const response = await axiosInstance.get('/shopping/order/get-store-remarks', {
      params: {
        storeId: shopinfo.storeId
      }
    });
    if (remarks.length > 0) {
      remarks.splice(0, remarks.length);
    }
    response.data.data.forEach(remark => {
      if(remark.orderRemark){
        remarks.push(remark);
      }
    });
    message8.value = '已获取评论信息';
    console.log(remarks);
    checkLoadingStatus(); //检查加载状态
  } catch (error) {
    if (error.response) {
      message8.value = error.response.data;
    } else {
      message8.value = '获取评论信息失败';
    }
  }
  console.log(message8.value);
};

onMounted(() => {
  fetchStoreInfo();
  if(role=='买家'){
    fetchIsBookmarked();
  }
  fetchAllProducts();
  fetchRemarks();
});
  
</script>

<style scoped>

.header2{
  width:100%;
  background-color: #a61b29;
  position: relative;
  padding:5px 0px 5px 10px;
}

.main-container{
  align-items: center;
  background-color: #f4f6f8;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  min-height: 100vh;
  padding-bottom: 48px;
  position: relative;
  overflow: auto;
}

.main-content{
  margin-top: 20px;
  max-width: 1200px;
  width:80%;
  justify-content: center;
  box-sizing: border-box;
}

.shop-title{
  background-color: #fff;
  border-radius: 16px;
  box-sizing: border-box;
  display: flex;
  justify-content: space-between;
  padding: 16px 20px 20px;
  width: 100%;
}

.shop-avatar{
  height: 100px;
  width: 100px;
  border-radius: 100px;
}

.shop-info{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-left: 10px;
}

.shop-name{
  font-size: 24px;
  font-weight: bold;
  padding: 5px 0;
}

.other-info{
  font-size: 15px;
  padding: 5px 0;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  text-align: left;
}

.favoriteButton{
  justify-self: end;
  align-self: end;
}

.favorite-active {
  background-color: #bdaead;
  font-weight: bold;
  color:#ffffff;
}

.favorite-inactive {
  background-color: #c21f30;
  font-weight: bold;
  color: white;
}

.shop-content{
  align-items: flex-start;
  display: flex;
  justify-content: space-between;
  width: 100%;
}

.sidebar-category{
  background-color: #fff;
  border-radius: 16px;
  box-sizing: border-box;
  min-height: 70vh;
  margin-top: 16px;
  padding: 8px;
  position: sticky;
  top: 10px;
  width: 20%;
}

.product-display{
  background-color: #fff;
  border-radius: 16px;
  box-sizing: border-box;
  margin-top: 16px;
  margin-left: 16px;
  padding: 24px 0 0;
  width: 100%;
  min-height: 70vh;
}

.search-box{
  width: 45%;
  margin-left:50%;  
  display: flex;
  gap: 10px;
}

.search-box .el-button:hover{
  background-color: #bdaead;
  font-weight: bold;
  color:#ffffff;
}

.sidebar {
  background-color: #fff;
  padding: 8px;
  border-radius: 8px;
}

.category {
  font-size: 16px;
  padding: 8px 12px;
  margin-bottom: 8px;
  cursor: pointer;
  border-radius: 4px;
  transition: background-color 0.2s;
}

.category.selected {
  background-color: #a61b29;
  font-weight: bold;
  color:#ffffff;
}

.category:hover {
  background-color: #bdaead;
  font-weight: bold;
  color:#fff;
}

.product {
  margin-top: 20px;
  margin-left: 20px;
  margin-bottom: 20px;
  display: flex;
  width: 100%;
}


.display-items {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.product-item {
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
  transition: transform 0.3s, box-shadow 0.3s;
  display: grid;
  width: 221px;
  height: 280px;
  cursor: pointer;
}


.product-item:hover {
  transform: translateY(-2px);
  /* box-shadow: 0 4px 10px rgba(0,0,0,0.15); */
}

.product-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
  border-radius: 5px;
  margin-bottom: 10px;
}

.product-item h2 {
  font-size: 18px;
  margin: 0 0 10px;
}

.product-item p {
  font-size: 16px;
  margin: 0 0 10px;
}

.product-text{
  align-self: end;
}

.toggle-row {
  background-color: #fff;
  padding: 8px;
  border-radius: 8px;
  display: flex;
  justify-content: flex-start;
  margin-top: 16px;
}

.blank{
  width: 20px;
}

.toggle-row .el-button {
  font-size: 16px;
  padding: 8px 12px;
  cursor: pointer;
  border-radius: 4px;
}

.toggle-row .el-button.active {
  background-color: #a61b29;
  font-weight: bold;
  color:#fff;
}

.toggle-row .el-button:hover {
  background-color: #bdaead;
  font-weight: bold;
  color:#fff;
}

.shop-remarks{
  background-color: #fff;
  border-radius: 16px;
  box-sizing: border-box;
  min-height: 70vh;
  margin-top: 16px;
  padding: 8px;
  position: sticky;
  top: 10px;
  width: 100%;
  display: flex;
  justify-content: center;
}

.remarks{
  margin-top: 20px;
  margin-bottom: 20px;
}

.remarks-content{
  width: 85%;
  margin-top: 10px;
}

.remark-header{
  display: flex;
  flex-direction: row;
}

.remark-avatar{
  height: 40px;
  width: 40px;
  border-radius: 40px;
}

.remark-buyerName{
  margin-left: 20px;
  font-weight: 500;
  font-size: 14px;
  color: #11192d;
  line-height: 20px;
}

.remark-content {
  margin-left: 55px;
  margin-bottom: 16px;
  display: flex;
  flex-direction: column;
  justify-content: left;
}

.splitLine{
  margin: 12px 0;
  height: 1px;
  background-color: #f0f3f5;
}

  </style>