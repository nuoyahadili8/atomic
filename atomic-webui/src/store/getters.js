/* eslint-disable no-trailing-spaces */
const getters = {
    // user
    token: state => state.user.token,
    refresh_token: state => state.user.refresh_token,
    nickname: state => state.user.nickname,
    email: state => state.user.email,
    username: state => state.user.username,
    avatar: state => state.user.avatar,
    roles: state => state.user.roles,
    menus: state => state.user.menus,
    permissions: state => state.user.permissions,

    // app
    cachePage: state => state.app.cachePage,
    lang: state => state.app.lang,
    isFullScreen: state => state.app.isFullScreen,
    openedSubmenuArr: state => state.app.openedSubmenuArr,
    menuTheme: state => state.app.menuTheme,
    themeColor: state => state.app.themeColor,
    logoSrc: state =>  state.app.logoSrc,
    pageOpenedList: state => state.app.pageOpenedList,
    currentPageName: state => state.app.currentPageName,
    currentPath: state => state.app.currentPath,
    tagsList: state => state.app.tagsList,

    // permission
    permissionRouters: state => state.permission.permissionRouters
};
export default getters;
