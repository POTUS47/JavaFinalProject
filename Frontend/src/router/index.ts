import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Merchabdise from '../views/MerchandiseView.vue'
import Bazaar from '../views/BazaarView.vue'
import Cart from '../views/CartView.vue'
import Ordercentre from '../views/OrdercentreView.vue'
import Personalcentre from '../views/PersonalcentreView.vue'
import LoginAndRegister from '../views/LoginAndRegisterView.vue'
import AdminHeaderSec from '../components/AdminHeaderSec.vue'
import PlatformInfo from '../views/PlatformInfo.vue';
import MerchantCertification from '../views/MerchantCertification.vue';
import ReportManagement from '../views/ReportManagement.vue';
import AdminSidebarMenu from '../components/AdminSidebarMenu.vue'
import MerchantPage from '../views/MerchantPage.vue'
import MerchantShowcase from '../views/MerchantShowcase.vue'
import SearchProductShowcase from '../views/SearchProductShowcase.vue'
import BazaarMerchandise from '../views/BazaarMerchandise.vue'

//设置界面
import BuyerInformation from '../views/InformationSetting.vue'
import BuyerWallet from '../views/WalletSetting.vue'
import BuyerPassword from '../views/PasswordSetting.vue'
import LogoutSetting from '../views/LogoutSetting.vue'
import BusinessInformation from '../views/InformationSettingBusiness.vue'
import BusinessPassword from '../views/PasswordSettingBusiness.vue'
import BusinessLogout from '../views/LogoutSettingBusiness.vue'
//商家界面
import BusinessHomePage from '../views/BusinessHomePage.vue';
import BusinessOrder from '../views/BusinessOrderManage.vue';
import BusinessCommodity from '../views/BusinessCommodity.vue';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/loginandregister', // 重定向到登录注册页面
    },
    {
      path: '/loginandregister',
      name: 'LoginAndRegister',
      component: LoginAndRegister
    },
    {
      path: '/home',
      name: 'home',
      component: HomeView
    },
    {
      path: '/merchandise/:category',
      name: 'Merchabdise',
      component: Merchabdise
    },
    {
      path: '/bazaar',
      name: 'Bazaar',
      component: Bazaar
    },
    {
      path: '/cart',
      name: 'Cart',
      component: Cart
    },
    {
      path: '/ordercentre',
      name: 'Ordercentre',
      component: Ordercentre
    },
    {
      path: '/personalcentre',
      name: 'Personalcentre',
      component: Personalcentre
    },
    {
      path: '/adminheadersec',
      name: 'AdminHeaderSec',
      component: AdminHeaderSec
    },
    { path: '/platform-info', 
      name: 'PlatformInfo', 
      component: PlatformInfo 
    },
    { path: '/merchant-certification', 
      name: 'MerchantCertification', 
      component: MerchantCertification 
    },
    { path: '/report-management', 
      name: 'ReportManagement', 
      component: ReportManagement 
    },
    { path: '/adminsidebarmenu', 
      name: 'AdminSidebarMenu', 
      component: AdminSidebarMenu 
    },
    { path: '/merchantpage',  
      name: 'MerchantPage', 
      component: MerchantPage 
    },
    {
      path: '/businesshomepage',
      name: 'BusinessHomePage',
      component: () => import('../views/BusinessHomePage.vue')
    },
    {
      path: '/businessordermanage',
      name: 'BusinessOrderManage',
      component: () => import('../views/BusinessOrderManage.vue')
    },
    {
      path: '/businesscommodity',
      name: 'BusinessCommodity',
      component: () => import('../views/BusinessCommodity.vue')
    },
    {
      path: '/viewpost',
      name: 'ViewPost',
      component: () => import('../views/ViewPost.vue')
    },
    { path: '/merchantshowcase', 
      name: 'MerchantShowcase', 
      component: MerchantShowcase 
    },
    { path: '/searchproductshowcase', 
      name: 'SearchProductShowcase', 
      component: SearchProductShowcase 
    },
    { path: '/bazaarmerchandise', 
      name: 'BazaarMerchandise', 
      component: BazaarMerchandise 
    },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../views/AboutView.vue')
    },
    {
      path: '/productdetail',
      name: 'ProductDetail',
      component: () => import('../views/ProductDetail.vue')
    },
    {
      path: '/oneYuanDetail',
      name: 'oneYuanDetail',
      component:  () => import('../views/OneYuanDetail.vue')
    },
    {
      path: '/oneYuanPay',
      name: 'oneYuanPay',
      component:  () => import('../views/OneYuanPay.vue')
    },
    {
      path: '/oneYuan',
      name: 'oneYuan',
      component:  () => import('../views/OneYuan.vue')
    },
    {
      path: '/pay',
      name: 'Pay',
      component: () => import('../views/PayView.vue')
    },
    {
      path: '/shopdetail',
      name: 'ShopDetail',
      component: () => import('../views/ShopDetail.vue')
    },
    //设置界面
    { path: '/BuyerInformation', 
      name: 'BuyerInformation', 
      component: BuyerInformation 
    },
    { path: '/BuyerWallet', 
      name: 'BuyerWallet', 
      component: BuyerWallet
    },
    { path: '/BuyerPassword', 
      name: 'BuyerPassword', 
      component: BuyerPassword
    },
    { path: '/LogoutSetting', 
      name: 'LogoutSetting', 
      component: LogoutSetting
    },
    { path: '/LogoutSetting', 
      name: 'LogoutSetting', 
      component: LogoutSetting
    },
    { path: '/BusinessInformation', 
      name: 'BusinessInformation', 
      component: BusinessInformation
    },
    { path: '/BusinessPassword', 
      name: 'BusinessPassword', 
      component: BusinessPassword
    },
    { path: '/BusinessLogout', 
      name: 'BusinessLogout', 
      component: BusinessLogout
    },
    //商家界面
    { path: '/BusinessHomePage', 
      name: 'BusinessHomePage', 
      component: BusinessHomePage
    },
    { path: '/BusinessOrder', 
      name: 'BusinessOrder', 
      component: BusinessOrder
    },
    { path: '/BusinessCommodity', 
      name: 'BusinessCommodity', 
      component: BusinessCommodity
    },
    {
      path:'/picTextContainer',
      name:'picTextContainer',
      component: () => import('../views/templates/PicTextContainer.vue')
    },
    {
      path:'/aside',
      name:'aside',
      component: () => import('../views/templates/aside.vue')
    },
    {
      path:'/loading',
      name:'loading',
      component: () => import('../views/templates/LoadingView.vue')
    }

  ]
})
export default router