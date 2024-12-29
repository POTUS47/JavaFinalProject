<template>

  <img v-show="activeSlideIndex !== 0" src="@/assets/mmy/to-top.svg" class="to-top" @click="goToTop" />

  <swiper :direction="'vertical'" :slidesPerView="1" :mousewheel="true" :speed="1000" class="mySwiper" ref="mySwiper"
    @slideChange="onSlideChange" :options="swiperOptions" @swiper="onSwiper"
    :modules="[Mousewheel, Navigation, Pagination]">
    <swiper-slide>
      <div class="slide0">
        <Navbar />
        <div class="container">
          <div class="card b1" style="top: 200px;" @click="typeChange(5, '金彩流光')">
            <img src="@/assets/categories/金彩流光.png" alt="金彩流光">
            <p>【金彩流光】</p>
          </div>
          <div class="card b2" style="top: 290px;" @click="typeChange(4, '逸趣风雅')">
            <img src="@/assets/categories/逸趣风雅.png" alt="逸趣风雅">
            <p>【逸趣风雅】</p>
          </div>
          <div class="card b3" style="top: 150px;" @click="typeChange(3, '织艺锦缎')">
            <img src="@/assets/categories/织艺锦缎.png" alt="织艺锦缎">
            <p>【织艺锦缎】</p>
          </div>
          <div class="card b4" style="top: 250px;" @click="typeChange(2, '墨香文房')">
            <img src="@/assets/categories/墨香文房.png" alt="墨香文房">
            <p>【墨香文房】</p>
          </div>
          <div class="card b5" style="top: 320px;" @click="typeChange(1, '匠心器皿')">
            <img src="@/assets/categories/匠心器皿.png" alt="匠心器皿">
            <p>【匠心器皿】</p>
          </div>
          <div class="card b6" style="top: 180px;" @click="typeChange(0, '其他')">
            <img src="@/assets/categories/其他.png" alt="其他">
            <p>【其他】</p>
          </div>
        </div>
      </div>
    </swiper-slide>

    <swiper-slide>
      <div class="slide1">
        <aside class="sidebar-category">
          <!-- <div>
            <img src="@/assets/mmy/arrow_left.svg"/>
           </div> -->
          <div v-for="(subCategory, index) in AllCategories[currentSumCategory]?.subCategories || []"
            :key="subCategory.subCategoryId" :class="{ selected: selectedCategory === index }" class="category"
            @click="filter(subCategory, index)">
            {{ subCategory.subCategoryName }}
          </div>
        </aside>
        <div class="display-container">
          <div class="container-block"
            v-show="AllCategories.length > 0 && AllCategories[currentSumCategory].subCategories[selectedCategory].subCategoryName === '全部' && AllCategories[currentSumCategory].largeCategoryName !== '其他'">
            <img src="@/assets/mmy/blue_background.jpg">
            <div class="inner-block">
              <div class="slider-top-right">
                <div class="picture-and-text">
                  <div class="category_text">{{ AllCategories.length > 0 ?
                    AllCategories[currentSumCategory].largeCategoryName : '' }}</div>
                  <img :src="picUrl" class="picture">
                  <div class="text">
                    <p>&nbsp;&nbsp;{{ text }}</p>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="product-container">
            <div v-if="paginatedProducts.length !== 0">
              <div class="display-items">
                <div v-for="product in paginatedProducts" :key="product.productId" class="product-item"
                  @click="handleProductClick(product.productId)">
                  <img :src="product.imageUrl" :alt="product.productId" class="product-image" />
                  <h2>{{ product.productName }}</h2>
                  <p>价格: ¥{{ product.productPrice }}</p>
                </div>
              </div>
              <div class="pagination">
                <button @click="productPageChange(currentPage - 1)" :disabled="currentPage === 1">上一页</button>
                <span>第 {{ currentPage }} 页 / 共 {{ productsPages }} 页</span>
                <button @click="productPageChange(currentPage + 1)"
                  :disabled="currentPage === productsPages">下一页</button>
              </div>
            </div>
            <div v-else>
              <span
                style="font-family: Arial, sans-serif; font-size: 20px; display: block; margin-bottom: 13px; color: black;">
                该分类暂无商品
              </span>
            </div>
          </div>
        </div>
      </div>
    </swiper-slide>
  </swiper>

</template>

<script setup>

import { Swiper, SwiperSlide } from 'swiper/vue';
import { Navigation, Pagination } from 'swiper/modules';
import 'swiper/css';
import { Mousewheel } from 'swiper/modules';

import Navbar from '../components/Navbar.vue';
import { ref, computed, onMounted, reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axiosInstance from '../router/axios';

const text=ref('');
const picUrl = ref('');
const categoryUrl = "/src/assets/categories/"
const detailDescription1 = "匠心器皿，承载着东方美学与实用主义的完美结合。从细腻温润的瓷器到质朴自然的陶器；从光泽如镜的铜器到坚韧耐用的铁器；从光泽流转的漆器到晶莹剔透的琉璃；每一项技艺背后都有一个故事，每一件作品都是一个时代的缩影。这里不仅有古典家具的优雅，也有铸铁技艺的精湛，更有紫砂陶与陶瓷彩瓷的缤纷色彩。每一种材质都在诉说着属于自己的历史。"
const detailDescription2 = "墨香文房，汇集了中华千年文脉之精华。从一管宣笔的精挑细选，到一滴徽墨的研磨；从一张楮皮纸的轻薄透明，到一方砚台的沉稳厚重。这里不仅有书写工具的传承与发展，更有如书画毡、简牍等文化载体的再现。每一款产品，都是对古代文人雅士生活方式的复刻，让使用者在挥毫泼墨间，感受那份独有的宁静与深远。"
const detailDescription3 = "织艺锦缎，传承千年的织造艺术，在这里得以重现其辉煌。从锦绣成衣到丝绸布匹，每一件作品都承载着匠人的心血与智慧。无论是细腻的缂丝，还是典雅的香港中式长衫；不论是庄重的满族旗袍，还是雍容的龙凤旗袍；亦或是苗族织锦的多彩斑斓，地毯织造的精妙绝伦，每一道工序都是对传统技艺的致敬。更有蚕丝织造技艺与古老的蜡染、扎染工艺，在这里交织出一幅幅绚丽多彩的历史画卷。"
const detailDescription4 = "欢迎来到“逸趣风雅”，一个寻找传统韵味与现代生活完美融合的理想之地。在这里，传统与现代交融，艺术与实用并重。从细致入微的手绘扇面，对弈黑白的棋盘，到精致典雅的绸伞…物品不仅承载了古朴历史的记忆，更点缀着当下生活的典雅。"
const detailDescription5 = "金彩流光汇集了各种精美的金属工艺品，从华丽的花丝镶嵌到优雅的金漆工艺，再到细腻的金银细工与璀璨的苗族银饰。每一件作品都是匠人们卓越技艺的结晶，将传统金属工艺之美展现得淋漓尽致，让人感受到金属艺术的无限魅力。"

// const nowSubCategoryId=ref('05000')

//swiper滑动实现
const currentSumCategory = ref(3);
const mySwiper = ref(null);
let swiperInstance = null;
const onSwiper = (swiper) => {
  swiperInstance = swiper;
}
const activeSlideIndex = ref(0);
function onSlideChange(swiper) {
  activeSlideIndex.value = swiper.activeIndex;
}
const goToTop = () => {
  swiperInstance.slideTo(0);
};
const typeChange = (id, name) => {
  pageSize.value = 4; //进入“全部”分类，每页4个商品
  if (name == "其他") {
    pageSize.value = 12; //进入“其他-全部”分类，每页8个商品
  }
  currentPage.value = 1; //重置分页器当前页数
  console.log(`name is ${name}`);
  getCategoryDetail(name); //获取当前大分类描述

  currentSumCategory.value = id;
  swiperInstance.slideTo(1);
  selectedCategory.value = 0;
  getProducts(AllCategories[currentSumCategory.value].subCategories[selectedCategory.value].subCategoryId);

  currentType.value = name;

};


//关于分类
const selectedCategory = ref(0);
const message = ref('');
const AllCategories = reactive([]);
const getCategories = async () => {
  try {
    console.log('尝试获取分类');
    const response = await axiosInstance.get('/productController/GetAllCategories');
    response.data.data.forEach(category => {
      AllCategories.push(category);
    });
    console.log(`AllCategories is ${JSON.stringify(AllCategories, null, 2)}`)
  } catch (error) {
    if (error.response) {
      message.value = error.response.data.msg;
    } else {
      message.value = '获取分类失败';
    }
  }
  console.log(message.value);
};

const filter = (subCategory, index) => {

  selectedCategory.value = index;
  console.log(` ${subCategory.subCategoryId} 被点击`);
  console.log(`selectedCategory is ${selectedCategory.value}`);

  currentPage.value = 1; //重置分页器当前页数
  if (selectedCategory.value == 0 && subCategory.subCategoryId != '05000') { //进入“全部”分类，每页4个商品
    pageSize.value = 4;
  }
  else { //其余小分类，每页展示8个商品
    pageSize.value = 12;
  }
  getProducts(AllCategories[currentSumCategory.value].subCategories[selectedCategory.value].subCategoryId);
}

//关于商品
const displayProducts = reactive([]);
const getProducts = async (Id) => {
  console.log(`Id is ${Id}`);
  try {
    const response = await axiosInstance.get('/productController/Classification/getProductsBySubTagId', {
      params: {
        subTagId: Id
      }
    });
    if (displayProducts.length > 0) {
      displayProducts.splice(0, displayProducts.length);
    }
    response.data.data.forEach(product => {
      if (!product.saleOrNot) {
        displayProducts.push(product);
      }
    });
  } catch (error) {
    if (error.response) {
      message.value = error.response.data.msg;
    } else {
      message.value = '获取商品失败';
    }
  }
  console.log(message.value);
};

onMounted(() => {
  getCategories();
  getProducts("03000");
  getCategoryDetail('金彩流光'); //获取当前大分类描述
})

const currentType = ref('服装')
const router = useRouter();

/////////////////////////////////
const pageSize = ref(4);
const currentPage = ref(1);

const totalProducts = computed(() => displayProducts.length);
const productsPages = computed(() => Math.ceil(totalProducts.value / pageSize.value));

// 处理并分页后的商品数据
const paginatedProducts = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return displayProducts.slice(start, end);
});

// 切换分页页面
const productPageChange = (page) => {
  if (page >= 1 && page <= productsPages.value) {
    currentPage.value = page;
  }
};

const handleProductClick = (productId) => {
  localStorage.setItem('productIdOfDetail', productId);
  router.push('/productdetail');
};


const getCategoryDetail = async (name) => {
  picUrl.value = categoryUrl + name + '.jpg';
  if(name=='金彩流光'){
    text.value = detailDescription5;
    return;
  }
  if(name=='逸趣风雅'){
    text.value = detailDescription4;
    return;
  }
  if(name=='织艺锦缎'){
    text.value = detailDescription3;
    return;
  }
  if(name=='墨香文房'){
    text.value = detailDescription2;
    return;
  }
  if(name=='匠心器皿'){
    text.value = detailDescription1;
    return;
  }
  
};


</script>




<style scoped>
html,
body {
  height: 100%;
  margin: 0;
  overflow: hidden;

}

.to-top {
  position: fixed;
  bottom: 80px;
  right: 60px;
  z-index: 3;

  width: 50px;
  height: 50px;

  transition: opacity 0.5s ease-in-out 1s;
}

.swiper {
  width: 100%;
  height: 100%;
}

.slide0,
.slide1 {
  width: 100%;
  height: 100%;
}

.slide0 {
  background-color: #f0f0f0;
  background-image: url('@/assets/categories/复古条纹.png');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  height: 100vh;
}

.slide1 {
  background-color: cadetblue;
  display: flex;
  height: 100vh;
}

.container {
  display: flex;
  justify-content: center;
  overflow: hidden;
  width: 100%;
  height: 100%;
  z-index: 2;
}

.card {
  width: 150px;
  height: 250px;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  box-shadow: -10px 0px 30px rgba(0, 0, 0, 0.4);
  text-align: center;
  z-index: 2;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  cursor: pointer;
}

.b1 {
  background-image: url('@/assets/categories/b1.png');
  background-size: cover;
}

.b2 {
  background-image: url('@/assets/categories/b2.png');
  background-size: cover;
}

.b3 {
  background-image: url('@/assets/categories/b3.png');
  background-size: cover;
}

.b4 {
  background-image: url('@/assets/categories/b4.png');
  background-size: cover;
}

.b5 {
  background-image: url('@/assets/categories/b5.png');
  background-size: cover;
}

.b6 {
  background-image: url('@/assets/categories/b6.png');
  background-size: cover;
}

.card:hover {
  transform: translateY(-10px);
  box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
}

.card img {
  width: 80%;
  margin-top: 20px;
}

@font-face {
  font-family: 'SJxingshu';
  src: url('../assets/fonts/SJxingshu.ttf') format('truetype');
}

.card p {
  font-family: 'SJxingshu', serif;
  font-size: 22px;
  padding-top: 10px;
  color: white;
}


.merchandise-container {
  display: flex;
  height: 100%;
  height: 100%;
  background-image: url('@/assets/categories/背景图.jpg');
}

.sidebar {
  flex: 0 0 200px;
  background-color: #003366;
  /* 深蓝色背景 */
  color: #ffffff;
  /* 白色文本 */
  padding: 20px;
  box-shadow: 2px 0 10px rgba(0, 0, 0, 0.2);
  /* 更明显的阴影 */
  display: flex;
  flex-direction: column;
}

.sidebar h2 {
  font-size: 24px;
  margin-bottom: 20px;
  color: #e0e0e0;
  /* 较浅的颜色，提升对比度 */
}

.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.main-content {
  flex: 1;
  padding: 20px;
  /* overflow-y: auto; */
}

.main-content h1 {
  font-size: 24px;
  margin-bottom: 20px;
}

/* .product-display {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.product-item {
  flex: 0 0 30%;
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
  transition: transform 0.3s, box-shadow 0.3s;
}

.product-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 10px rgba(0,0,0,0.15);
}

.product-image {
  width: 100%;
  height: auto;
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
} */

.display-items {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  min-height: 480px;
  padding: 20px 20px 10px 60px;
  /* justify-content: space-between; */

}

.product-item {
  flex: 0 0 23%;
  background-color: #fff;
  padding: 10px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s, box-shadow 0.3s;
  height: 250px;
  cursor: pointer;
}

.product-image {
  max-height: 110px;
  max-width: 100%;
  object-fit: cover;
  margin-top: 20px;
  border-radius: 5px;
  margin-bottom: 10px;
  margin-left: 10px;
  margin-right: 10px;
}

.product-item h2 {
  font-size: 18px;
  margin: 0 0 5px;
}

.product-item p {
  font-size: 16px;
  margin: 0 0 5px;
}

.pagination {
  margin-top: 20px;
  margin-bottom: 20px;
}

.display-items2 {
  flex-wrap: wrap;
  gap: 20px;
  min-height: 480px;
}

.pagination {
  /* margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px; */
  margin-top: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  position: absolute;
  bottom: 0;
  left: 50%;
  background-color: white;
  transform: translateX(-50%);
}





.animate__animated.animate__slideInUp {
  --animate-duration: 1.2s;
  animation-delay: 0.5s;
}

.animate__animated.animate__slideInDown {
  --animate-duration: 1.2s;
  animation-delay: 0.5s;
}

.swiper {
  width: 100%;
  height: 100%;
}

.image-with-description {
  display: flex;
  align-items: center;
  position: relative;
  z-index: 1;
  background-color: white;
  padding: 20px;
  margin-right: 80px;
  margin-bottom: 20px;
  border-radius: 15px;
  /* 增加圆角 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  /* 添加轻微阴影 */
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  /* 添加交互效果 */
}

.image-with-description img {
  width: 200px;
  height: auto;
  margin-right: 20px;
  border-radius: 10px;
  /* 为图片添加圆角 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  /* 为图片添加轻微阴影 */
}

.image-with-description .description {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  font-size: 16px;
  color: #333;
  line-height: 1.5;
}

.nav-item {
  color: #333;
}

.sidebar-category {
  flex: 0 0 200px;
  background-color: #fff;
  padding: 20px;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
}

.category {
  color: #7c6a6a;
  font-size: 16px;
  padding: 8px 12px;
  margin-bottom: 8px;
  cursor: pointer;
  border-radius: 4px;
  transition: background-color 0.2s;
}

.category.selected {
  background-color: #3D5164;
  font-weight: bold;
  color: #ffffff;
}

.display-container {
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
  align-items: center;
  /* margin-top: 16px; */
  /* margin-left: 16px; */
  padding: 12px 0 0;
  width: 100%;
  min-height: 70vh;
  background-color: #333;
}

/* ***********图文容器*********** */
.container-block {
  overflow: hidden;
  border-radius: 15px;
  color: #fff;
  display: inline-block;
  margin: 10px;
  width: 85%;
  height: 45%;
  position: relative;

  &::before {
    /* 特别修改1 */
    /* 企图用覆盖层的颜色改变原本背景色,并保留原本图案*/
    /* background-color: rgba(130, 17, 31,0.3); */
    /* background-color: rgba(255, 255, 255,0.3); */
    bottom: 0;
    content: '';
    display: block;
    position: absolute;
    top: 0;
    width: 100%;
  }

  &:hover {

    .inner-block:before,
    .slider-top-right:after {
      height: 100%;
    }

    .inner-block:after,
    .slider-top-right:before {
      width: 100%;
    }
  }

  img {
    display: block;
    max-width: 100%;
  }
}

.product-container {
  overflow: hidden;
  border-radius: 15px;
  background-color: #fff;
  display: inline-block;
  margin: 10px;
  width: 85%;
  flex: 1;
  position: relative;
  padding: 15px 15px;
  background-image: url('../assets/mmy/background.jpg');
}

.slider-top-right:before,
.inner-block:after {
  height: 2px;
  transition: width .75s ease;
  width: 0%;
}

.slider-top-right:after,
.inner-block:before {
  height: 0%;
  transition: height .75s ease;
  width: 2px;
}

.inner-block:before,
.inner-block:after,
.slider-top-right:before,
.slider-top-right:after {
  background-color: #fff;
  content: '';
  display: block;
  position: absolute;
}

.inner-block {
  font-size: 2em;
  width: 90%;
  height: 90%;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  margin: auto;

  &:before {
    bottom: 0;
    left: 0;
  }

  &:after {
    bottom: 0;
    right: 0;
  }
}

.slider-top-right {
  position: relative;
  width: 100%;
  height: 100%;
  display: flex;
  /* 使用 Flexbox */
  justify-content: center;
  /* 水平居中 */
  align-items: center;

  /* 垂直居中 */
  &:before {
    top: 0;
    left: 0;
  }

  &:after {
    top: 0;
    right: 0;
  }
}

.picture-and-text {
  width: 95%;
  height: 90%;
  display: flex;
  flex-direction: row;
}

.picture {
  width: 35%;
  /* height:90%; */
  border-radius: 20px;
}

.text {
  /* 华文宋 */
  width: 45%;
  font-family: 'Noto Serif SC', serif;
  font-size: 22px;
  padding: 10px 10px 20px 50px;
  overflow-y: auto;
  max-height: 93%;
  /* 限制最大高度为父容器高度 */
  text-align: left;

}

/* 特别修改2 */
/* 自定义滚动条样式 */
.text::-webkit-scrollbar {
  width: 10px;
}

.text::-webkit-scrollbar-track {
  background: #f1f1f1;
  border-radius: 10px;
}

.text::-webkit-scrollbar-thumb {
  background: #5f697a;
  border-radius: 10px;
}

.category_text {
  writing-mode: vertical-rl;
  font-family: 'SJxingshu', serif;
  font-size: 40px;
  padding-right: 20px;
}
</style>
