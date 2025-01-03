<!-- 商家界面的订单管理总览界面 -->
<template>
  <div class="BusinessOrder">
    <Sidebar />
    <BusinessAfterSaleTopbar @changeView="handleChangeView" />
    <div class="CommodityContent">
      <component :is="currentView" ref="currentView"></component>
    </div>
  </div>
</template>

<script>
import BusinessAfterSaleShow from './BusinessAfterSaleShow.vue';
import BusinessAfterSaleTopBar from './BusinessAfterSaleTopbar.vue';
import Sidebar from './BusinessSidebar.vue'
//import BusinessAfterSaleTopBar from "@/views/BusinessAfterSaleTopbar.vue";

export default {
  name: 'BusinessAfterSale',
  components: {
    BusinessAfterSaleShow,
    BusinessAfterSaleTopBar,
    Sidebar
  },
  data() {
    return {
      viewType: 1,
      currentView: 'BusinessAfterSaleShow'
    }
  },
  methods: {
    handleChangeView(viewType) {
      this.viewType = viewType;
      this.$nextTick(() => {
        if (this.$refs.currentView && this.$refs.currentView.handleChange) {
          this.$refs.currentView.handleChange(viewType);
        } else {
          console.error('handleChange 方法在 currentView 组件中未找到');
        }
      });
    },
  }
};
</script>
