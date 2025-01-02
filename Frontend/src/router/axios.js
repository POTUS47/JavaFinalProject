import axios from 'axios';

let url = '';
if (process.env.NODE_ENV === 'development') {
  url = 'http://localhost:8080/api'
} else {
  url = 'http://47.97.59.189:17990/backend/api'
}

const axiosInstance = axios.create({
  baseURL: url, // api base_url
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