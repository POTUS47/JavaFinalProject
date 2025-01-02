<template>
  <swiper :direction="'vertical'" :slidesPerView="1" :mousewheel="true" :speed="1000" :modules="[Mousewheel]"
    class="mySwiper" @slideChange="onSlideChange">
    <swiper-slide>
      <div class="page1">
        <Navbar class="narbar" />
        <div class="LRcontainer">
          <img src="@/assets/mmy/arrow_left.svg" class="arrow-button1"
            @click="setAd((currentAdIndex - 1 + numAds) % numAds)" @mouseenter="stopTimer" @mouseleave="startTimer" />
          <img src="@/assets/mmy/arrow_right.svg" class="arrow-button2" @click="setAd((currentAdIndex + 1) % numAds)"
            @mouseenter="stopTimer" @mouseleave="startTimer" />
          <div class="ad">
            <img v-show="currentAdIndex === 0" src="@/assets/mmy/ad1.png" />
            <div class="ad2" v-show="currentAdIndex === 1">
              <img src="@/assets/mmy/ad2.png" />
              <div class="ad2_text">
                <div class="column1">文房雅韵</div>
                <div class="column2">墨香千年</div>
              </div>
            </div>
            <div class="ad3" v-show="currentAdIndex === 2">
              <img src="@/assets/mmy/ad3.jpg" />
            </div>
            <div class="knotArea" @mouseenter="stopTimer" @mouseleave="startTimer">
              <img v-show="currentAdIndex !== 0" src="@/assets/mmy/knot.svg" class="knot1" @click="setAd(0)" />
              <img v-show="currentAdIndex === 0" src="@/assets/mmy/active_knot.svg" class="knot1" @click="setAd(0)" />
              <img v-show="currentAdIndex !== 1" src="@/assets/mmy/knot.svg" class="knot2" @click="setAd(1)" />
              <img v-show="currentAdIndex === 1" src="@/assets/mmy/active_knot.svg" class="knot2" @click="setAd(1)" />
              <img v-show="currentAdIndex !== 2" src="@/assets/mmy/knot.svg" class="knot3" @click="setAd(2)" />
              <img v-show="currentAdIndex === 2" src="@/assets/mmy/active_knot.svg" class="knot3" @click="setAd(2)" />
            </div>
          </div>
        </div>
      </div>

    </swiper-slide>

    <swiper-slide>
      <div class="page2">

        <transition v-show="activeSlideIndex === 1" appear class="animate__animated animate__slideInDown">
          <img src="@/assets/mmy/fan_up.png" class="fan_up">
        </transition>
        <transition v-show="activeSlideIndex === 1" appear class="animate__animated animate__slideInUp">
          <img src="@/assets/mmy/fan_down.png" class="fan_down">
        </transition>
        <div v-if="activeSlideIndex === 1" class="product-display">
          <div v-for="product in products" :key="product.productId" class="product-item"
            @click="goToProductDetail(product.productId)">
            <img :src="product.productPic" :alt="product.productName" class="product-image" />
            <div class="product-info">
              <p class="product-price">
                <span class="special-price">价格</span> ¥{{ product.productPrice }}
              </p>
              <h2 class="product-name">【{{ product.productName }}】
              </h2>
            </div>
          </div>
        </div>

      </div>
    </swiper-slide>

    <swiper-slide>
      <div class="page3">
        <!-- -------------笨方法长盛不衰------------- -->
        <div class="sidebar">
          <div class="one-choice1" @click="navigateTo('/merchandise/1')">
            <img class="IconPage3" src="@/assets/mmy/工艺品.png">
            <p>商品</p>
          </div>

          <div class="one-choice2" @click="navigateTo('/oneyuan')">
            <img class="IconPage3" src="@/assets/mmy/家具.png">
            <p>一元购</p>
          </div>

          <div class="one-choice1" @click="navigateTo('/cart')">
            <img class="IconPage3" src="@/assets/mmy/首饰.png">
            <p>收藏夹</p>

          </div>

          <div class="one-choice2" @click="navigateTo('/ordercentre')">
            <img class="IconPage3" src="@/assets/mmy/小物件.png">
            <p>订单中心</p>
          </div>

          <div class="one-choice1" @click="navigateTo('/BuyerInformation')">
            <img class="IconPage3" src="@/assets/mmy/服装.png">
            <p>个人中心</p>
          </div>
        </div>

        <div class="foot">
          <p>机构官网：https://www.tongji.edu.cn</p>
          <div class="police">
            <img src="@/assets/mmy/公安.png">
            <div class="text">沪公网安备&nbspXXXXXXXXXXXXXX号&nbsp&nbsp京ICP备XXXXXXXX号-1</div>
          </div>
          <p>网站维护：同济大学软件学院创意购团队&nbsp&nbsp联系方式：123456789</p>
          <p>建议使用Edge、Chrome、Firefox浏览器，最佳分辨率1920×1080</p>
        </div>
      </div>
    </swiper-slide>
  </swiper>
</template>

<script setup>
import { Swiper, SwiperSlide } from 'swiper/vue';
import axiosInstance from '../router/axios';
import 'swiper/css';
import { Mousewheel } from 'swiper/modules';
import Navbar from '../components/Navbar.vue';
// import HomeCategory from './HomeCategory.vue';
import { ref, onMounted, onBeforeUnmount, computed } from 'vue';
import 'animate.css';
import { useRouter } from 'vue-router';

const currentAdIndex = ref(0);
const products = ref([]);
const numAds = 3;
const activeSlideIndex = ref(0);
let adTimer = null;  // 用于存储计时器 ID，这样写是为了知道数据类型

////////////////////////// 每2.5秒调用setAd函数
const startTimer = () => {
  if (adTimer === null) {
    adTimer = setInterval(() => {
      setAd((currentAdIndex.value + 1) % numAds);
    }, 2500);
  }
};
const stopTimer = () => {
  if (adTimer !== null) {
    clearInterval(adTimer);
    adTimer = null;
  }
};
onMounted(() => {
  startTimer();//内部调用计时器
  fetchRecommendProducts();
  console.log('Swiper initialized');
});
onBeforeUnmount(() => {
  console.log('Swiper cleaned up');
});


function onSlideChange(swiper) {
  activeSlideIndex.value = swiper.activeIndex;
  console.log(`activeSlideIndex.value is ${activeSlideIndex.value}`);
}

function setAd(index) {
  currentAdIndex.value = index;
}
const router = useRouter();

function navigateTo(link) {
  router.push(link);
}

document.querySelectorAll('.icon').forEach(icon => {
  icon.addEventListener('click', () => {
    icon.classList.add('shake');
    // Remove the class after animation ends
    setTimeout(() => {
      icon.classList.remove('shake');
    }, 500); // Match the duration of the animation
  });
});

const goToProductDetail = (productId) => {
  // console.log('Selected Store ID:', productId);
  localStorage.setItem('productIdOfDetail', productId);  // 存储 productId
  console.log('跳转至 /productdetail');
  router.push('/productdetail');  // 跳转到商品详情页
};

const fetchRecommendProducts = async () => {
  try {
    const response = await axiosInstance.post('/productController/recommend/user');
    products.value = response.data.data;
    console.log('返回数据', response.data);

    if (response.data) {
      products.value = response.data.data;
      console.log('products:', products.value);
    } else {
      products.value = [];
      // ElMessage.error('暂无相关推荐');
    }
  } catch (err) {
    // ElMessage.error("推荐出现错误啦");
  }

};


</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Ma+Shan+Zheng&display=swap');


div {
  user-select: none;
  outline: none;
  cursor: default;
}

#app {
  height: 100%;
}

html,
body {
  position: relative;
  font-family: 'Ma Shan Zheng', cursive;
  height: 100%;
}

body {
  background: #eee;
  font-family: 'Ma Shan Zheng', cursive;
  /* font-family: Helvetica Neue, Helvetica, Arial, sans-serif; */
  font-size: 14px;
  color: #000;
  margin: 0;
  padding: 0;
}

.swiper {
  width: 100%;
  height: 100%;
}

.navbar {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.3);
  color: #FFFFFF;
  z-index: 3;
}

/* Use :deep() to target the inner elements of the navbar */
:deep(.navbar-bottom) {
  background-color: rgba(0, 0, 0, 0.3) !important;
}

:deep(.search-container) {
  background-color: rgba(0, 0, 0, 0.3) !important;
  color: #FFFFFF !important;
}

:deep(.search-input::placeholder) {
  color: #FFFFFF !important;
}

:deep(.search-button) {
  background-color: rgba(0, 0, 0, 0.3) !important;
  color: #FFFFFF !important;
}

:deep(.navbar-item .nav-link.active),
:deep(.navbar-item .nav-link:hover) {
  background-color: rgba(0, 0, 0, 0.3) !important;
  color: #FFFFFF !important;
}

:deep(.navbar-item .nav-link) {
  color: #FFFFFF !important;
}

.mySwiper {
  height: 100vh;
  width: 100%;
}

.LRcontainer {
  background-color: rgba(36, 63, 51);
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  /* 添加这个以便子元素可以使用绝对定位 */
}

.ad,
.ad2,
.ad3 {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  overflow: hidden;
  position: relative;
  /* 确保 .knotArea 绝对定位基于 .ad */
}

.ad img,
.ad2 img,
.ad3 img {
  width: 100%;
  height: auto;
  object-fit: cover;
  /* 保证图片自适应 */
  z-index: 1;
}

.ad2 {
  background-image: url("../assets/mmy/texture2.png");
}

.ad3 {
  background-color: #243569;
}

@font-face {
  font-family: 'SJxingshu';
  src: url('../assets/fonts/SJxingshu.ttf') format('truetype');
}

.ad2_text {
  display: flex;
  flex-direction: row;
  z-index: 3;

  color: white;
  position: fixed;
  top: 200px;
  left: 180px;
  /* gap:10px; */

  font-size: 70px;
  font-family: 'SJxingshu', serif;
}

.column1,
.column2 {
  writing-mode: vertical-rl;
  /* 将文字垂直排列 */
}

.column1 {}

.column2 {
  padding-top: 150px;
}

.arrow-button1,
.arrow-button2,
.knot1,
.knot2,
.knot3 {
  cursor: pointer;
  z-index: 2;
}

.arrow-button1 {
  width: 50px;
  height: 50px;
  position: fixed;
  top: 50%;
  left: 70px;
}

.arrow-button2 {
  width: 50px;
  height: 50px;
  position: fixed;
  top: 50%;
  right: 70px;
}

.arrow-button2:hover,
.arrow-button1:hover {
  box-shadow: 0 0 5px rgba(229, 215, 215, 0.5);
  transform: scale(1.1);
}

.knotArea {
  display: flex;
  justify-content: center;
  gap: 15px;
  position: absolute;
  bottom: 20px;
  /* 你可以调整这个值来改变位置 */
  width: 10%;
}

.ad_discount {
  width: 130vh;
  display: block;
  margin: auto;
}


.page2 {
  overflow: hidden;
  width: 100%;
  height: 100%;
  position: relative;
  background-image: url("../assets/mmy/blue_background.jpg");
}

.fan_up {
  position: absolute;
  top: 0;
  right: 50%;
  margin-right: -95px;
  width: 990px;
  height: 472px;
  z-index: 1;
}

.fan_down {
  position: absolute;
  bottom: 0;
  left: 50%;
  margin-left: -260px;
  width: 1222px;
  height: 540px;
  z-index: 2;
}

.animate__animated.animate__slideInUp {
  --animate-duration: 1.2s;
  animation-delay: 0.5s;
}

.animate__animated.animate__slideInDown {
  --animate-duration: 1.2s;
  animation-delay: 0.5s;
}

.page3 {
  width: 100%;
  height: 100%;
  display: flex;
  overflow: hidden;
  /* flex-direction: column; */
  justify-content: center;
  background-image: url("../assets/mmy/background.jpg");
  background-color: rgba(65, 33, 10, 0.2);
  font-family: 'Noto Serif SC', serif;

}

.sidebar {
  top: 20%;
  display: flex;
  flex-direction: row;
  position: relative;
  width: 1000px;
  /* align-items: center; */

}

.one-choice1,
.one-choice2 {
  position: relative;
  width: 200px;
  height: 178.34px;
}

.one-choice1 {
  background-image: url("../assets/mmy/红底.jpg");
}

.one-choice2 {
  background-image: url("../assets/mmy/黄底.jpg");
}

.IconPage3 {
  position: absolute;
  top: 10px;
  left: 50%;
  transform: translateX(-50%);
  z-index: 2;
  width: 60%;
  transition: transform 0.3s ease;
  transform-origin: center;
  cursor: pointer;
}

.IconPage3:hover {
  transform: translateX(-50%) scale(1.05);
}

.one-choice1 p,
.one-choice2 p {
  position: absolute;
  font-family: 'Noto Serif SC', serif;
  font-size: 20px;
  bottom: 10px;
  left: 50%;
  transform: translateX(-50%);
  color: #FFFFFF;
  cursor: pointer;
  z-index: 2;
}

.foot {
  bottom: 5%;
  display: flex;
  flex-direction: column;
  position: absolute;
  width: 1000px;
  align-items: center;
  font-size: 16px;
}

.police {
  display: flex;
  flex-direction: row;
}

.text1,
.text2 {
  overflow: auto;
  z-index: 4;
  position: fixed;
}

.text1 {
  width: 42%;
  height: 37%;
  right: 20px;
  top: 30px;
}

.text2 {
  width: 37%;
  height: 37%;
  left: 40px;
  bottom: 30px;
}

.text1 h2,
.text2 h2 {
  font-family: 'SJxingshu';
  color: white;
  font-size: 30px;
}

.text1 p,
.text2 p {
  font-family: 'Noto Serif SC', serif;
  color: white;
  font-size: 20px;
  text-align: left;

}

.text1::-webkit-scrollbar {
  width: 10px;
}

.text1::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 10px;
}

.text1::-webkit-scrollbar-thumb {
  background: #5f697a;
  border-radius: 10px;
}

.text2::-webkit-scrollbar {
  width: 10px;
}

.text2::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 10px;
}

.text2::-webkit-scrollbar-thumb {
  background: #5f697a;
  border-radius: 10px;
}

/* 以下是商品推荐 */
.product-display {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  padding-top: 20px;
  padding-left: 100px;
  padding-right: 100px;
  /* background-image: url('@/assets/wy/background.jpg'); */
  background-size: cover;
  background-position: center;
  height: 100%;
  overflow-x: hidden;
  align-items: stretch;
  /* 使每个商品容器拉伸到相同高度 */
  z-index: 3;  /* 高于扇子图片 */
  margin-top: 3%;  /* 上边距，基于父容器的宽度 */
  margin-bottom: 3%;  /* 下边距，基于父容器的宽度 */
  /* gap: 10px;  控制每一行的商品项之间的间距 */
}

.product-item {
  width: calc(25% - 20px);
  /* 每行显示四个商品，减去间隙 */
  height: 255px;
  padding: 20px;
  border: 1px solid #e7e7e7;
  border-radius: 10px;
  background-color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
  /* 使鼠标在悬停时变成手型指针 */
  transition: border-color 0.3s ease;
  /* 添加平滑的边框颜色变化效果 */
  z-index: 4;  /* 高于扇子图片 */
}

.product-item:hover {
  border-color: #a61b29;
  /* 悬停时的边框颜色 */
}

.product-image {
  /* width: 258px; */
  width: 85%;
  /* 固定宽度 */
  height: 170px;
  height:65%;
  /* 固定高度 */
  object-fit: cover;
  /* 保持图片比例并裁剪超出部分 */
  /* object-fit: cover; */
  border-radius: 10px;
  margin-bottom: 10px;
}

.product-info {
  text-align: center;
  /* padding-bottom: 10px; */
}

.product-name {
  font-size: 16px;
  color: #333;
  margin-bottom: 10px;
}

.product-price {
  font-size: 18px;
  color: #a61b29;
  margin-bottom: 10px;
}

.special-price {
  background-color: #a61b29;
  color: #fff;
  padding: 2px 5px;
  border-radius: 5px;
}

.end-of-list {
  width: 100%;
  /* 确保容器占满整个宽度 */
  text-align: center;
  margin: 20px 0;
  font-size: 24px;
  color: #a61b29;
  display: flex;
  justify-content: center;
  /* 水平居中 */
  align-items: center;
  /* 垂直居中 */
}

.loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.loading-spinner {
  border: 5px solid #f3f3f3;
  border-radius: 50%;
  border-top: 5px solid #3498db;
  width: 50px;
  height: 50px;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }

  100% {
    transform: rotate(360deg);
  }
}

.error-message-container {
  background-color: white;
  height: 100%;

}

.error-container {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 50px;
}

.error-image {
  width: 120px;
  height: auto;
  margin-bottom: 20px;
  /* 给图片和文字之间留出空隙 */
}

.error-text {
  font-size: 24px;
  color: #a61b29;
  text-align: center;
}
</style>