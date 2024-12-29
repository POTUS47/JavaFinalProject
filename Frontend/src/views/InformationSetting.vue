<!-- 设置界面的账号信息 -->
<template>
    <div class="container">
        <SettingSidebar />
        <div class="main-content">
            <SettingHeader />
            <hr>
            <div class="content">
                <div class="main-container">

                    <h2>账号基础信息管理</h2>
                    <div class="user-info">
                        <el-card class="custom-card-width">
                            <el-form :model="userInfo" label-width="100px" class="user-form">
                                <el-row gutter="20"> <!-- 设置列间距 -->
                                    <el-col :span="12">
                                        <el-form-item label="用户名"
                                            :rules="[{ required: true, message: '请输入用户名', trigger: 'blur' }]">
                                            <el-input v-model="userInfo.username" placeholder="请输入用户名"></el-input>
                                        </el-form-item>
                                    </el-col>
                                    <!-- <el-col :span="12">
                            <el-form-item label="性别" :rules="[{ required: true, message: '请选择性别', trigger: 'change' }]">
                            <el-select v-model="userInfo.gender" placeholder="请选择性别">
                                <el-option label="男" value="male"></el-option>
                                <el-option label="女" value="female"></el-option>
                            </el-select>
                            </el-form-item>
                        </el-col> -->
                                </el-row>

                                <!-- <el-row gutter="20">
                        <el-col :span="12">
                            <el-form-item label="年龄" :rules="[{ required: true, message: '请输入年龄', trigger: 'blur' }]">
                            <el-input v-model.number="userInfo.age" type="number" placeholder="请输入年龄"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="收货地址" :rules="[{ required: true, message: '请输入收货地址', trigger: 'blur' }]">
                            <el-input v-model="address.detail" placeholder="请输入收货地址"></el-input>
                            </el-form-item>
                        </el-col>
                        </el-row> -->

                                <div class="form-footer">
                                    <el-button type="primary" @click="updateUserInfo">保存</el-button>
                                </div>
                            </el-form>
                        </el-card>
                    </div>

                    <h2>账号消费积分查看</h2>
                    <div class="table-container">
                        <div class="points">
                            <el-card class="custom-card-width">
                                <p>您当前的积分：{{ userInfo.credits }}</p>
                            </el-card>
                        </div>
                    </div>

                    <h2>账号头像简介上传</h2>
                    <div class="form-container">
                        <el-card class="custom-card-width">
                            <el-form :model="userimades" :rules="rules" ref="form" class="user-form">
                                <el-form-item label="上传头像" prop="image">
                                    <img v-if="userimades.ima" :src="userimades.ima" alt="当前图片"
                                        style="width: 40px; height: 40px;border-radius: 50%;" />
                                    <input type="file" @change="handleFile" accept="image/*" />
                                </el-form-item>
                                <el-form-item label="上传简介">
                                    <el-input v-model="userimades.description" maxlength="20"
                                        show-word-limit></el-input>
                                </el-form-item>
                            </el-form>
                            <div class="form-footer">
                                <el-button type="primary" @click="handleUpload">上传</el-button>
                            </div>
                        </el-card>
                    </div>
                </div>

            </div>
        </div>
    </div>

</template>

<script>
import SettingSidebar from '../components/SettingSidebar.vue'
import SettingHeader from '../components/SettingHeader.vue'
import { reactive, ref, computed, onMounted } from 'vue';
import { ElTable, ElTableColumn, ElPagination, ElButton, ElDialog, ElForm, ElFormItem, ElInput, ElRow, ElCol, ElSelect, ElOption, ElMessage } from 'element-plus';
import 'element-plus/dist/index.css';
import axiosInstance from '../router/axios';

export default {
    name: 'InformationSetting',
    components: {
        SettingSidebar,
        SettingHeader
    },
    data() {
        return {
            //账号信息
            userInfo: {
                username: '',
                gender: '',
                age: null,
                credits: null,
                description: '',
                address: '',
                photo_id: '',
                email: ''
            },
            userimades: {
                photo_id: '',
                ima: '',
                file: null,
                describtion: ''
            }

        };
    },
    methods: {
        //获取用户信息
        async getUserInfo() {
            try {
                const response = await axiosInstance.get(`/users/buyer/myInfo`);
                this.userInfo = {
                    username: response.data.data.userName,
                    gender: response.data.data.gender,
                    age: response.data.data.age,
                    credits: response.data.data.totalCredits,
                    address: response.data.data.address,
                    description: response.data.data.describtion,
                    photo_id: response.data.data.photoId,
                    email: response.data.data.email
                };
                this.userimades = {
                    photo_id: response.data.data.photoId,
                    describtion: response.data.data.describtion
                };
            } catch (error) {
                console.error('请求失败:获取用户信息', error);
                this.$message.error('请求用户信息失败，请稍后再试');
            }
        },

        async getImage(imageId) {
            try {
                const response = await axiosInstance.get(`/images/${imageId}`, {
                    responseType: 'blob', // 指定响应类型为 Blob（适合处理图片或文件流）
                });

                // 创建图片 URL
                const imageUrl = URL.createObjectURL(response.data);
                this.userimades.ima = imageUrl; // 更新图片 URL 到 data 中

                console.log('图片加载成功:', imageUrl);
            } catch (error) {
                console.error('请求失败:获取图片', error);
                this.$message.error('请求头像失败，请稍后再试');
            }
        },

        //更新用户信息
        async updateUserInfo() {
            //const userId = localStorage.getItem('userId');
            // if (!this.userInfo.username || !this.userInfo.gender || this.userInfo.age === null || !this.address.detail) {
            //     this.$message.error('所有字段都必须填写');
            //     return;
            // }
            if (!this.userInfo.username) {
                this.$message.error('不能为空！');
                return;
            }

            // 验证年龄必须大于或等于0，并且是整数
            // if (this.userInfo.age < 0 || !Number.isInteger(this.userInfo.age)) {
            //     this.$message.error('年龄必须是大于或等于0的整数');
            //     return;
            // }

            // 准备请求体
            // const requestBody = {
            //     accountId: userId || null, // 如果 userId 不存在，传递 null
            //     userName: this.userInfo.username || null, // 如果 username 不存在，传递 null
            //     gender: this.userInfo.gender || null, // 如果 gender 不存在，传递 null
            //     age: (this.userInfo.age >= 0 && Number.isInteger(this.userInfo.age)) ? this.userInfo.age : null, // 如果 age 不符合要求，传递 null
            //     address: this.address.detail || null // 如果 address 不存在，传递 null
            // };

            try {
                const response = await axiosInstance.put('/users/updateUsername', null, {
                    params: {
                        newUsername: this.userInfo.username, // 添加新用户名作为参数
                    },
                });
                this.$message.success('用户名更新成功！');
            } catch (error) {
                console.error('请求失败:', error); // 输出错误信息
                this.$message.error('更新用户名请求失败，请稍后再试');
            }
        },


        //上传头像简介
        handleFile(event) {
            const file = event.target.files[0];
            if (file) {
                console.log('选中的文件:', file);
                this.userimades.file = file;
                this.userimades.ima = URL.createObjectURL(file);
                console.log('userfile', file, 'userima', this.userimades.ima);
            } else {
                console.log('文件选择失败或无效');
            }
        },

        handleFileChange(event) {
            const file = event.target.files[0];
            if (file) {
                const validTypes = ['image/jpeg', 'image/jpg', 'image/png'];
                if (validTypes.includes(file.type)) {
                    const reader = new FileReader();
                    reader.onload = (e) => {
                        this.userimades.ima = e.target.result;
                        console.log('Loaded image:', this.userimades.ima); // 输出图片数据
                    };
                    reader.readAsDataURL(file);
                } else {
                    this.$message.error('请上传正确格式的图片 (.jpg 或 .png)');
                }
            }
        },

        //上传头像简介
        async handleUpload() {
            try {
                const formData = new FormData();
                const Photo = this.userimades.file;
                const Describtion = this.userimades.describtion;
                //const Id = localStorage.getItem('userId');
                if (this.userimades.file) {
                    formData.append('file', Photo); // 确保这是 File 对象
                }
                try {
                    const type = '头像';
                    const response = await axiosInstance.post(`/upload_image/${type}`, formData, {
                        headers: {
                            'Content-Type': 'multipart/form-data',
                        },
                    });
                    console.log('头像上传成功:', response.data);
                    this.$message.success('上传成功，请刷新网页以查看最新状态');

                    if (response.data.data) {
                        this.userimades.photo_id = response.data.data;
                        // 更新本地展示的头像
                    }

                } catch (error) {
                    console.error('头像上传失败:', error);
                }
                // 处理简介更新
                if (Describtion) {
                    try {
                        const descriptionResponse = await axiosInstance.put(`/users/updateDescription`, null, {
                            params: {
                                newDescription: Describtion
                            }
                        });
                        console.log('简介更新成功:', descriptionResponse.data);
                        this.$message.success('简介更新成功');
                    } catch (error) {
                        console.error('简介更新失败:', error);
                        this.$message.error('简介更新失败，请稍后再试');
                    }
                }

            } catch (error) {
                console.error('请求失败: 头像简介上传', error.response ? error.response.data : error.message);
                this.$message.error('头像简介上传请求失败，请稍后再试');
            }
        },
    },
    watch: {
        'userInfo.photo_id': function (newPhotoId) {
            if (newPhotoId) {
                this.getImage(newPhotoId);
            }
        }
    },
    mounted() {
        this.getUserInfo();
        this.getImage(this.userInfo.photo_id);
    }
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
    background-color: rgb(237, 227, 228);
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


.user-info {
    padding: 15px;
}

.table-container {
    padding: 15px;
}

.form-container {
    padding: 15px;
}

.el-button--primary {
    background-color: #a13232;
    border-color: #a13232;
}

.el-button--primary:hover {
    background-color: #8b2b2b;
    border-color: #8b2b2b;
}

.custom-card-width {
    max-width: 1000px;
    /* 设置宽度为400px，您可以根据需要调整 */
    margin-left: 0;
}

.form-footer {
    display: flex;
    justify-content: flex-end;
}
</style>