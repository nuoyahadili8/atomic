import Vue from 'vue'
import Router from 'vue-router'
import Main from '../views/Main';

Vue.use(Router);

/**
 * 通用路由, 注意此部分路由不会显示在菜单中
 * 默认情况下，webapp只注册了这一部分路由，在用户登陆后, 将根据授权服务器与asyncRouterMap之间比较后新增新的路由
 * @type {*[]}
 */
export const commonRouterMap = [
    {path: '/login', name: 'login', meta: {title: '登录系统 - Atomic'}, component: () => import('@/views/login.vue')},
    {path: '/locking',name: 'locking',meta: {title: '页面已锁定 - Atomic'},component: () => import('@/views/main-components/lockscreen/components/locking-page.vue')},
    {path: '/403',name: 'error-403', meta: {title: '403 - 权限不足'},component: () => import('@/views/error-page/403.vue')},
    {path: '/500',name: 'error-500',meta: {title: '403 - 内部错误'},component: () => import('@/views/error-page/500.vue')},
    {path: '/404',name: 'error-404',meta: {title: '404 - 页面不存在'},component: () => import('@/views/error-page/404.vue')},
    {
        path: '/',
        name: 'home',
        redirect: '/home',
        component: Main,
        hidden: true,
        children: [
            {path: 'home', title: {i18n: 'home'}, name: 'home_index', component: () => import('@/views/home/home.vue')}
        ]
    }
];

export default new Router({
    // mode: 'history', // 后端支持可开
    scrollBehavior: () => ({y: 0}),
    routes: commonRouterMap
})

/**
 * 异步路由，此处用于展示菜单（在运行过程中会根据后台授权服务器决定是否加载
 * 与后台服务器数据库的对应关系：this.name = tb_resource.code
 * @type {*[]}
 */
export const asyncRouterMap = [
    {
        path: '/system',
        icon: 'gear-b',
        name: 'systemManager',
        title: '系统管理',
        component: Main,
        children: [
            {
                path: 'userManager',
                icon: 'person-stalker',
                name: 'userManager',
                title: '用户管理',
                component: () => import('@/views/system/userManager/index.vue')
            },
            {
                path: 'roleManager',
                icon: 'social-buffer',
                name: 'roleManager',
                title: '角色管理',
                component: () => import('@/views/my-components/text-editor/text-editor.vue')
            },
            {
                path: 'resourceManager',
                icon: 'navicon-round',
                name: 'resourceManager',
                title: '资源管理',
                component: () => import('@/views/my-components/text-editor/text-editor.vue')
            },
            {
                path: 'sectionManager',
                icon: 'network',
                name: 'sectionManager',
                title: '部门管理',
                component: () => import('@/views/my-components/text-editor/text-editor.vue')
            }
        ]
    },
    {
        path: '/examples',
        icon: 'social-buffer',
        name: 'iview-examples',
        title: 'iView控件示例',
        component: Main,
        children: [
            {
                path: 'text-editor',
                icon: 'compose',
                name: 'text-editor',
                title: '富文本编辑器',
                component: () => import('@/views/my-components/text-editor/text-editor.vue')
            },
            {
                path: 'md-editor',
                icon: 'pound',
                name: 'md-editor',
                title: 'Markdown编辑器',
                component: () => import('@/views/my-components/markdown-editor/markdown-editor.vue')
            },
            {
                path: 'image-editor',
                icon: 'crop',
                name: 'image-editor',
                title: '图片预览编辑',
                component: () => import('@/views/my-components/image-editor/image-editor.vue')
            },
            {
                path: 'draggable-list',
                icon: 'arrow-move',
                name: 'draggable-list',
                title: '可拖拽列表',
                component: () => import('@/views/my-components/draggable-list/draggable-list.vue')
            },
            {
                path: 'area-linkage',
                icon: 'ios-more',
                name: 'area-linkage',
                title: '城市级联',
                component: () => import('@/views/my-components/area-linkage/area-linkage.vue')
            },
            {
                path: 'file-upload',
                icon: 'android-upload',
                name: 'file-upload',
                title: '文件上传',
                component: () => import('@/views/my-components/file-upload/file-upload.vue')
            },
            {
                path: 'count-to',
                icon: 'arrow-graph-up-right',
                name: 'count-to',
                title: '数字渐变',
                component: () => import('@/views/my-components/count-to/count-to.vue')
            },
            {
                path: 'split-pane-page',
                icon: 'ios-pause',
                name: 'split-pane-page',
                title: 'split-pane',
                component: () => import('@/views/my-components/split-pane/split-pane-page.vue')
            },
            {
                path: 'artical-publish',
                title: '文章发布',
                name: 'artical-publish',
                icon: 'compose',
                component: () => import('@/views/form/article-publish/article-publish.vue')
            },
            {
                path: 'workflow',
                title: '工作流',
                name: 'workflow',
                icon: 'arrow-swap',
                component: () => import('@/views/form/work-flow/work-flow.vue')
            },
            {
                path: 'dragableTable',
                title: '表格可拖拽排序',
                name: 'dragable-table',
                icon: 'arrow-move',
                component: () => import('@/views/tables/dragable-table.vue')
            },
            {
                path: 'editableTable',
                title: '可编辑表格',
                name: 'editable-table',
                icon: 'edit',
                component: () => import('@/views/tables/editable-table.vue')
            },
            {
                path: 'searchableTable',
                title: '可搜索表格',
                name: 'searchable-table',
                icon: 'search',
                component: () => import('@/views/tables/searchable-table.vue')
            },
            {
                path: 'exportableTable',
                title: '表格导出数据',
                name: 'exportable-table',
                icon: 'code-download',
                component: () => import('@/views/tables/exportable-table.vue')
            },
            {
                path: 'table2image',
                title: '表格转图片',
                name: 'table-to-image',
                icon: 'images',
                component: () => import('@/views/tables/table-to-image.vue')
            },
            {
                path: 'mutative-router',
                title: '动态路由',
                name: 'mutative-router',
                icon: 'link',
                component: () => import('@/views/advanced-router/mutative-router.vue')
            },
            {
                path: 'argument-page',
                title: '带参页面111',
                name: 'argument-page',
                icon: 'android-send',
                component: () => import('@/views/advanced-router/argument-page.vue')
            }
        ]
    }
];
