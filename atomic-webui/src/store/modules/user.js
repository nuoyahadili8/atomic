import {login, getInfo, logout, updatePassword} from '../../api/login';
import {getToken, getRefreshToken, setToken, removeToken} from '../../utils/token';

const user = {
    state: {
        token: getToken(),
        refresh_token: getRefreshToken(),
        nickname: '',
        email: '',
        username: '',
        avatar: '',
        roles: [],
        menus: [],
        permissions: []
    },
    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token;
        },
        SET_REFRESH_TOKEN: (state, refresh_token) => {
            state.refresh_token = refresh_token;
        },
        SET_NICKNAME: (state, nickname) => {
            state.nickname = nickname;
        },
        SET_EMAIL: (state, email) => {
            state.email = email;
        },
        SET_USERNAME: (state, username) => {
            state.username = username;
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar;
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles;
        },
        SET_MENUS: (state, menus) => {
            state.menus = menus;
        },
        SET_PERMISSIONS: (state, permissions) => {
            state.permissions = permissions;
        }
    },
    actions: {
        // 登录
        Login ({ commit }, form) {
            const username = form.userName.trim();
            return new Promise((resolve, reject) => {
                login(username, form.password).then(response => {
                    const data = response.data;
                    setToken(data.access_token, data.refresh_token);
                    commit('SET_TOKEN', data.access_token);
                    commit('SET_REFRESH_TOKEN', data.refresh_token);
                    resolve();
                }).catch(error => {
                    reject(error);
                });
            });
        },
        // 获取用户信息
        GetInfo ({ commit, state }) {
            return new Promise((resolve, reject) => {
                getInfo().then(response => {
                    if (response.data.status) {
                        const body = response.data.body;
                        commit('SET_EMAIL', body.user.email);
                        commit('SET_NICKNAME', body.user.nickname);
                        commit('SET_USERNAME', body.user.username);
                        commit('SET_ROLES', body.roles);
                        commit('SET_AVATAR', body.user.avatar);
                        // 菜单
                        const menus = [];
                        for (let i = 0; i < body.menus.length; i++) {
                            menus[body.menus[i]] = true;
                        }
                        commit('SET_MENUS', menus);

                        // 权限（按钮）
                        const permissions = [];
                        for (let i = 0; i < body.permissions.length; i++) {
                            permissions[body.permissions[i]] = true;
                        }
                        commit('SET_PERMISSIONS', permissions);
                        resolve(response);
                    }
                }).catch(error => {
                    reject(error);
                });
            });
        },
        // 登出
        LogOut ({ commit, state }) {
            return new Promise((resolve, reject) => {
                logout(state.token, state.refresh_token).then((response) => {
                    if (response.data.status) {
                        // 清空用户信息
                        commit('SET_TOKEN', '');
                        commit('SET_REFRESH_TOKEN', '');
                        commit('SET_NICKNAME', '');
                        commit('SET_USERNAME', '');
                        commit('SET_ROLES', []);
                        commit('SET_PERMISSIONS', []);
                        commit('SET_AVATAR', '');
                        removeToken();

                        // 恢复默认样式
                        let themeLink = document.querySelector('link[name="theme"]');
                        themeLink.setAttribute('href', '');
                        // 清空打开的页面等数据，但是保存主题数据
                        let theme = '';
                        if (localStorage.theme) {
                            theme = localStorage.theme;
                        }
                        localStorage.clear();
                        if (theme) {
                            localStorage.theme = theme;
                        }

                        resolve();
                    }
                }).catch(error => {
                    reject(error);
                });
            });
        },
        // 修改密码
        UpdatePassword ({ commit }, form) {
            return new Promise((resolve, reject) => {
                updatePassword(form.oldPassword, form.newPassword).then((response) => {
                    resolve(response);
                }).catch(error => {
                    reject(error);
                });
            });
        },

        // 前端 登出
        FedLogOut ({ commit }) {
            return new Promise(resolve => {
                commit('SET_TOKEN', '');
                commit('SET_REFRESH_TOKEN', '');
                removeToken();
                resolve();
            });
        }
    }
};

export default user;
