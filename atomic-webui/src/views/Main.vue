<style lang="less">
    @import "./main.less";
</style>
<template>
    <div class="main" :class="{'main-hide-text': shrink}">
        <div class="sidebar-menu-con" :style="{width: shrink?'60px':'200px', overflow: shrink ? 'visible' : 'auto'}">
            <shrinkable-menu
                :shrink="shrink"
                @on-change="handleSubmenuChange"
                :theme="menuTheme"
                :before-push="beforePush"
                :open-names="openedSubmenuArr"
                :menu-list="permissionRouters">
                <div slot="top" class="logo-con">
                    <img v-show="!shrink" class="logo-full" :src="logoSrc[0]" key="max-logo" />
                    <img v-show="shrink" class="logo-mini" :src="logoSrc[1]" key="min-logo" />
                </div>
            </shrinkable-menu>
        </div>
        <div class="main-header-con" :style="{paddingLeft: shrink?'60px':'200px'}">
            <div class="main-header">
                <div class="navicon-con">
                    <Button :style="{transform: 'rotateZ(' + (this.shrink ? '-90' : '0') + 'deg)'}" type="text" @click="toggleClick">
                        <Icon type="navicon" size="32"></Icon>
                    </Button>
                </div>
                <div class="header-middle-con">
                    <div class="main-breadcrumb">
                        <breadcrumb-nav :currentPath="currentPath"></breadcrumb-nav>
                    </div>
                </div>
                <div class="header-avator-con">
                    <full-screen v-model="isFullScreen" @on-change="fullscreenChange"></full-screen>
                    <lock-screen></lock-screen>
                    <!--<message-tip v-model="mesCount"></message-tip>-->
                    <div class="lock-screen-btn-con">
                        <Tooltip content="下载源码" placement="bottom">
                            <a href="#" style="color: #657180;" target="_blank"><Icon type="social-github" size="20"></Icon></a>
                        </Tooltip>
                    </div>
                    <theme-switch></theme-switch>

                    <div class="user-dropdown-menu-con">
                        <Row type="flex" justify="end" align="middle" class="user-dropdown-innercon">
                            <Dropdown transfer trigger="click" placement="bottom-end">
                                <a href="javascript:void(0)">
                                    <span class="main-user-name">{{ nickname }}</span>
                                    <Avatar :src="avatar" style="background: #619fe7;"></Avatar>
                                </a>
                                <DropdownMenu slot="list" style="width: 240px;position: relative;top: -5px;">
                                    <div class="header" style="text-align: center; margin-bottom: 5px;padding: 10px 0px;background: rgb(73, 80, 96);">
                                        <img class="user-avatar-detail" style="border-radius: 50%;border: 2px solid #e6e6e6;" :src="avatar" width="100" height="100" />
                                    </div>
                                    <div class="info" style="text-align: center;margin:10px 0px 10px 0px;">
                                        <span style="font-size: 14px">
                                          {{nickname}}
                                          <span style="display: block;font-size: 12px; color: #6f7180">{{email}}</span>
                                        </span>
                                        <div style="margin-top: 5px;">
                                            <Tag style="font-size: 12px;border-radius: 0px"
                                                 type="border" color="green"
                                                 v-for="role in roles" :key="role" size="small">{{role}}</Tag>
                                        </div>
                                    </div>
                                    <div class="footer" style="margin: 5px 10px;border-top: 1px solid #dcdfe6;padding-top: 10px">
                                        <Button @click="updatePwdModel = true" style="float: left" icon="information-circled" type="primary" size="small">修改密码</Button>
                                        <Button @click="logout" style="float: right" icon="log-out" type="error" size="small">注销登录</Button>
                                    </div>
                                </DropdownMenu>
                            </Dropdown>

                        </Row>
                    </div>
                </div>
            </div>
            <div class="tags-con">
                <tags-page-opened :pageTagsList="pageOpenedList"></tags-page-opened>
            </div>
        </div>
        <div class="single-page-con" :style="{left: shrink?'60px':'200px'}">
            <div class="single-page">
                <keep-alive :include="cachePage">
                    <router-view></router-view>
                </keep-alive>
            </div>
        </div>

        <!--修改密码弹出框-->
        <Modal v-model="updatePwdModel">
            <p slot="header" style="text-align:left">
                <Icon type="information-circled"></Icon>
                <span>修改密码</span>
            </p>
            <Form ref="updatePwdForm" :model="updatePwdForm" :rules="updatePwdFormRules" label-position="top">
                <FormItem prop="oldPassword" label="原始密码">
                    <Input type="password" v-model="updatePwdForm.oldPassword" placeholder="请输入原始密码" icon="locked" />
                </FormItem>
                <FormItem prop="newPassword" label="新密码">
                    <Input type="password" v-model="updatePwdForm.newPassword" placeholder="请输入新密码" icon="locked" />
                </FormItem>
                <FormItem prop="reNewPassword" label="重复密码">
                    <Input type="password" v-model="updatePwdForm.reNewPassword" placeholder="再次输入新密码" icon="locked" />
                </FormItem>
            </Form>
            <div slot="footer">
                <Button type="warning" size="large" long :loading="updatePwdModelLoading" @click="updatePassword">提交</Button>
            </div>
        </Modal>
    </div>
</template>
<script>
    import {mapGetters} from 'vuex';
    import {getLogoSrc} from "../../static/logo";
    import shrinkableMenu from './main-components/shrinkable-menu/shrinkable-menu.vue';
    import tagsPageOpened from './main-components/tags-page-opened.vue';
    import breadcrumbNav from './main-components/breadcrumb-nav.vue';
    import fullScreen from './main-components/fullscreen.vue';
    import lockScreen from './main-components/lockscreen/lockscreen.vue';
    import messageTip from './main-components/message-tip.vue';
    import themeSwitch from './main-components/theme-switch/theme-switch.vue';
    import util from '@/libs/util.js';

    export default {
        components: {
            shrinkableMenu,
            tagsPageOpened,
            breadcrumbNav,
            fullScreen,
            lockScreen,
            messageTip,
            themeSwitch
        },
        data () {
            // 重复密码验证器
            const validateReNewPassword = (rule, value, callback) => {
                if (value !== this.updatePwdForm.newPassword) {
                    callback(new Error('两次输入的新密码不一致'));
                } else {
                    callback();
                }
            };
            return {
                shrink: false,
                isFullScreen: false,
                updatePwdModel: false,
                updatePwdModelLoading: false,
                openedSubmenuArr: this.$store.state.app.openedSubmenuArr,
                updatePwdForm: {
                    oldPassword: '',
                    newPassword: '',
                    reNewPassword: ''
                },
                updatePwdFormRules: {
                    oldPassword: [{required: true, message: '原始密码不能为空', trigger: 'blur'}],
                    newPassword: [{required: true, message: '新密码不能为空', trigger: 'blur'}],
                    reNewPassword: [
                        {required: true, message: '重复密码不能为空', trigger: 'blur'},
                        {validator: validateReNewPassword, trigger: 'blur'}
                    ]
                }
            };
        },
        computed: {
            ...mapGetters([
                "nickname",
                "avatar",
                "username",
                "email",
                "roles",
                "permissionRouters",
                "pageOpenedList",
                "currentPath",
                "cachePage",
                "lang",
                "menuTheme",
                "themeColor",
                "logoSrc"
            ])
        },
        methods: {
            init () {
                let pathArr = util.setCurrentPath(this, this.$route.name);
                if (pathArr.length >= 2) {
                    this.$store.commit('addOpenSubmenu', pathArr[1].name);
                }
                this.checkTag(this.$route.name);
            },
            toggleClick () {
                this.shrink = !this.shrink;
            },
            logout () {
                this.$Modal.confirm({
                    title: '提示',
                    content: '确定注销登陆？',
                    onOk: () => {
                        this.$store.dispatch('LogOut').then(() => {
                            location.reload(); // 为了重新实例化vue-router对象 避免bug
                        });
                    }
                });
            },
            updatePassword () {
                // console.log('click updatePassword ok..');
                this.$refs.updatePwdForm.validate((valid) => {
                    if (valid) {
                        this.updatePwdModelLoading = true;
                        this.$store.dispatch('UpdatePassword', this.updatePwdForm).then(() => {
                            this.updatePwdModelLoading = false;
                            this.updatePwdModel = false;
                            // 注销用户
                            this.$Notice.success({title: '操作成功,将在3秒后注销登录'});
                            setTimeout(() => {
                                this.$store.dispatch('LogOut').then(() => {
                                    location.reload(); // 为了重新实例化vue-router对象 避免bug
                                });
                            }, 3000);
                        }).catch(() => {
                            this.updatePwdModelLoading = false;
                        });
                    }
                });
            },
            checkTag (name) {
                let openpageHasTag = this.pageOpenedList.some(item => {
                    if (item.name === name) {
                        return true;
                    }
                });
                if (!openpageHasTag) { //  解决关闭当前标签后再点击回退按钮会退到当前页时没有标签的问题
                    util.openNewPage(this, name, this.$route.params || {}, this.$route.query || {});
                }
            },
            handleSubmenuChange (val) {
                // console.log(val)
            },
            beforePush (name) {
                // if (name === 'accesstest_index') {
                //     return false;
                // } else {
                //     return true;
                // }
                return true;
            },
            fullscreenChange (isFullScreen) {
                // console.log(isFullScreen);
            }
        },
        watch: {
            '$route' (to) {
                this.$store.commit('setCurrentPageName', to.name);
                let pathArr = util.setCurrentPath(this, to.name);
                if (pathArr.length > 2) {
                    this.$store.commit('addOpenSubmenu', pathArr[1].name);
                }
                this.checkTag(to.name);
                localStorage.currentPageName = to.name;
            },
            lang () {
                util.setCurrentPath(this, this.$route.name); // 在切换语言时用于刷新面包屑
            }
        },
        mounted () {
            this.init();
        },
        created () {
            // 显示打开的页面的列表
            this.$store.commit('setOpenedList');
        }
    };
</script>
