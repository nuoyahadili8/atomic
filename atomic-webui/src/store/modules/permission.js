import {commonRouterMap, asyncRouterMap} from '../../router';
import {getAllMenu} from '../../api/menu';

/**
 * 判断当前路由是否包含在用户菜单权限中
 * @param menus 当前用户拥有的菜单（code）
 * @param route 当前访问的路由
 * @returns {*}
 */
function hasPermission(menus, route) {
    console.log('*************' + route.name);
    console.log(route.name + ' --> ' + route.name ? menus[route.name] !== undefined : true);
    return route.name ? menus[route.name] !== undefined : true;
};

/**
 * 递归过滤用户路由表，返回符合当前用户角色权限的路由表
 * @param routerMap
 * @param menus
 * @param allMenus
 */
function filterAsyncRouterMap(routerMap, menus, allMenus) {
    // alert(menus.length);
    // for (var k = 0, length = menus.length; k < length; k++) {
    //     console.log('&&&&&&&' + menus[k]);
    // }
    const accessedRouters = routerMap.filter((route) => {
    // console.log(' >> filterAsyncRouterMap >>');
    // console.log(route.title + ' hasPermission? ' + route.name + '>>>>>>' + hasPermission(menus, route));
        if (hasPermission(menus, route)) {
            // 动态构建路由信息,让其保持和服务器一致。
            route.title = allMenus[route.name].name || route.title;
            route.icon = allMenus[route.name].icon || route.icon;
            route.path = allMenus[route.name].path || route.path;

            if (route.children && route.children.length) {
                route.children = filterAsyncRouterMap(route.children, menus, allMenus);
            }
            return true;
        }
        return false;
    });
    return accessedRouters;
}

const permission = {
    state: {
        routers: commonRouterMap,
        permissionRouters: []
    },
    mutations: {
        SET_ROUTERS: (state, routers) => {
            state.permissionRouters = routers; // 权限路由,由服务端结合异步路由获得,这部分用来做菜单
            state.routers = commonRouterMap.concat(routers); // 将获取到的动态路由注册
        }
    },
    actions: {
        // 构建动态路由
        GenerateRoutes({commit}, menus) {
            return new Promise(resolve => {
                getAllMenu().then((res) => {
                    const data = res.data.body;
                    const allMenus = {};
                    for (let i = 0; i < data.length; i++) {
                        allMenus[data[i].code] = data[i];
                    }
                    console.log('***********' + data[0].name + '************' + data[0].title + '*************' + data[0].code);
                    commit('SET_ROUTERS', filterAsyncRouterMap(asyncRouterMap, allMenus, allMenus));
                    resolve();
                });
            });
        }
    }
};

export default permission;
