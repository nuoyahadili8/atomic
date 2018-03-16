import iView from 'iview';
import store from '../store/index';
import router from './index';
import {getToken} from '../utils/token';
import Util from '../libs/util';
import Cookies from 'js-cookie';

/**
 * 路由级别权限拦截器(注册在main.js)
 * 其主要功能有：
 * 1. 锁屏状态判定
 * 2. 登陆状态判定
 * 3. 用户状态信息管理
 * 4. 动态路由构建
 */

const whiteList = ['/login']; // 不重定向白名单
router.beforeEach((to, from, next) => {
    iView.LoadingBar.start();
    Util.title(to.meta.title);
    // 1. 判断是否为锁屏状态：
    if (Cookies.get('locking') === '1' && to.name !== 'locking') {
        next({replace: true, path: '/locking'});
    } else if (Cookies.get('locking') === '0' && to.name === 'locking') {
        next(false);
    } else {
        // 2. 判断是否登陆
        if (getToken()) {
            if (to.path === '/login') {
                next('/home');
            } else {
                if (store.getters.nickname === '') {
                    // 拉取用户信息
                    store.dispatch('GetInfo').then((res) => {
                        // 生成可访问的路由表
                        store.dispatch('GenerateRoutes', store.getters.menus).then(() => {
                            router.addRoutes(store.getters.permissionRouters); // 动态添加可访问路由表
                            next({...to});// hack方法 确保addRoutes已完成
                        });
                    }).catch(() => {
                        // 拉取用户信息失败
                        store.dispatch('FedLogOut').then(() => {
                            next('/login');
                        });
                    });
                } else {
                    next();
                }
            }
        } else {
            if (whiteList.indexOf(to.path) !== -1) {
                next();
            } else {
                next('/login');
            }
        }
    }
});

router.afterEach((to) => {
    Util.openNewPage(router.app, to.name, to.params, to.query);
    iView.LoadingBar.finish();
    window.scrollTo(0, 0);
});
