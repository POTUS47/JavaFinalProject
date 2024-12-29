import axios from 'axios';


const axiosInstance = axios.create({
  baseURL: 'http://47.97.59.189:8080/api', 
  withCredentials: true, // 允许跨域请求发送 Cookie
  timeout: 60000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json',
  },
}); 


// 请求拦截器，添加 Authorization 头
axiosInstance.interceptors.request.use(config => {
  const token = localStorage.getItem('token'); // 从 localStorage 获取 Token
  if (token) {
    config.headers.Authorization = `${token}`; // 设置Token
  }
  return config;
}, error => Promise.reject(error));


export default axiosInstance;