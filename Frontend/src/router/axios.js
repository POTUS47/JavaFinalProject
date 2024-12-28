import axios from 'axios';


const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api', 
  withCredentials: true, // 允许跨域请求发送 Cookie
  timeout: 60000, // 请求超时时间
  headers: {
    'Content-Type': 'application/json',
  },
}); 

export default axiosInstance;