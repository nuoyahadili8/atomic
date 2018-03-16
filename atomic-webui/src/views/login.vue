<style lang="less">
    @import './login.less';
</style>

<template>
    <div class="login" @keydown.enter="handleSubmit" >
        <div class="login-con">
            <Card :bordered="false">
                <div class="login-title" slot="title">
                    <img src="../assets/images/login/login_logo.svg" width="118" height="113" />
                    <p class="sub-title">SpringCloud & Vue2.x 敏捷开发解决方案</p>
                </div>
                <div class="form-con">
                    <Form ref="form" :model="form" :rules="rules">
                        <FormItem prop="userName">
                            <Input size="large" v-model="form.userName" placeholder="请输入用户名" icon="person" />
                        </FormItem>
                        <FormItem prop="password">
                            <Input size="large" type="password" v-model="form.password" placeholder="请输入密码" icon="locked"/>
                        </FormItem>
                        <FormItem>
                            <Button size="large" @click="handleSubmit" class="form-submit" :loading="loading">
                                <span v-if="!loading"><Icon type="checkmark-circled"></Icon> 登入系统</span>
                                <span v-else>正在登陆..</span>
                            </Button>
                        </FormItem>
                    </Form>
                    <p class="login-tip">&copy; 2018 github.al.com. <a href="https://github.com/nuoyahadili8" target="_blank">Fork me</a></p>
                </div>
            </Card>
        </div>
    </div>
</template>

<script>
    import Input from 'iview/src/components/input/input';

    export default {
        components: {Input},
        data () {
            return {
                form: {
                    userName: '',
                    password: '',
                    client_id: 'atomic',
                    client_secret: 'atomic'
                },
                rules: {
                    userName: [{required: true, message: '账号不能为空', trigger: 'blur'}],
                    password: [{required: true, message: '密码不能为空', trigger: 'blur'}],
                    client_id: [{required: true, message: '密码不能为空', trigger: 'blur'}],
                    client_secret: [{required: true, message: '密码不能为空', trigger: 'blur'}]
                },
                loading: false
            };
        },
        methods: {
            handleSubmit () {
                this.$refs.form.validate((valid) => {
                    if (valid) {
                        this.loading = true
                        this.$store.dispatch('Login', this.form).then(() => {
                            this.$router.push('/home');
                            // this.loading = false;
                        }).catch((res) => {
                            console.log(res);
                            this.loading = false;
                        });
                    }
                });
            }
        }
    };
</script>

<style>

</style>
