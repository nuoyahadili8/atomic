/* eslint-disable no-console */
import axios from 'axios';
import {getToken} from './token';
import {Notice, Message} from 'iview';

// 创建axios实例
const service = axios.create({
    timeout: 15000 // 请求超时时间
});

// Request 拦截器
service.interceptors.request.use(config => {
    if (getToken()) {
        // 让每个请求携带自定义token 请根据实际情况自行修改
        config.headers['Authorization'] = 'Bearer ' + getToken();
    }
    return config;
}, error => {
    // Do something with request error
    console.log(error); // for debug
    Promise.reject(error);
});

// Response 拦截器
service.interceptors.response.use(
    (response) => {
        const ret = response.data;
        // 业务级异常
        if (ret && ret.code && ret.code !== 200) {
            errorMessage(ret.message);
            return Promise.reject(ret); // 破坏promise链, reject之后的promise不在执行then(),而是直接调用catch()
        }
        return response;
    },
    (error) => {
        const ret = error.response;

        // 系统级异常
        if (ret.status === 400) {
            errorMessage(ret.data.error_description || error.message); // 登陆失败、参数错误等
        } else if (ret.status === 403 || ret.status === 401) {
            errorMessage(ret.data.message); // 无访问权限
        } else {
            errorMessage('异常：[' + error.message + ']');
        }
        return Promise.reject(error);
    }
);

export function successMessage (msg) {
    Message.success(msg);
}

export function errorMessage (msg) {
    Notice.error({
        title: '错误提示',
        desc: msg,
        duration: 6
    });
}

export default service;
