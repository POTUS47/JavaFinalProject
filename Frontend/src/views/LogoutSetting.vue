<!-- 设置界面的退出登录 -->
<template>
    <div class="container">
      <SettingSidebar />
      <div class="main-content">
        <SettingHeader />
        <hr>
        <div class="content">
          <div class="main-container">
            <h2>退出登录</h2>
            <el-card class="custom-card-width">
                <div>
                    <div class="logout-section">
                        <el-button type="danger" @click="logout">点此退出</el-button>
                    </div>
                </div>
              </el-card>
                
          </div>
          
        </div>
      </div>
    </div>
    
</template>
    
<script>
import SettingSidebar from '../components/SettingSidebar.vue'
import SettingHeader from '../components/SettingHeader.vue'
import { reactive, ref, computed, onMounted  } from 'vue';
import { ElTable, ElTableColumn, ElPagination, ElButton, ElDialog, ElForm, ElFormItem, ElInput, ElRow, ElCol, ElSelect, ElOption, ElMessage } from 'element-plus';
import 'element-plus/dist/index.css';
import axiosInstance from '../router/axios';

export default {
    name:'LogoutSetting',
    components:{
        SettingSidebar,
        SettingHeader
    },
    methods:{
        async logout() {
          localStorage.removeItem('token');
        },
    },
}
</script>
  
<style scoped>
   h2 {
    display: block;
    font-size: 1.5em;
    margin-block-start: 0.83em;
    margin-block-end: 0.83em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    font-weight: bold;
    unicode-bidi: isolate;
    color: #977c7c;
  }
  .container {
    display: flex;
    height: 100vh;
    /* background-color: rgb(248,210,210); */
    /* background-color: rgb(153,97,97); */
    background-color: rgb(237,227,228);
    /* background-color: rgb(239, 235, 235); */
  }
  
  .main-content {
    flex: 1;
    display: flex;
    flex-direction: column;
    color: #ffffff;
    
  }
  .content {
    flex: 1;
  }
  .main-container {
    text-align: left;
    color: #000000;
    margin-left: 140px;
    margin-top: 40px;
  }


.el-button--danger {
    background-color: #a13232;
    border-color: #a13232;
}

.el-button--danger:hover {
    background-color: #8b2b2b;
    border-color: #8b2b2b;
}
.custom-card-width {
    max-width: 1000px; /* 设置宽度为400px，您可以根据需要调整 */
    margin-left:0;
}
.logout-section {
  display: flex;
}
  
</style>