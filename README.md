## SpringCloud && Vue 分布式微架构 + 前后端分离解决方案
> **目前正在开发阶段, 感谢老A、lengleng和wuwenze**

## 技术栈
- 后端：SpringCloud、oAuth2、JWT、Eureka、Fegin、Ribbon、Zuul、Hystrix...
- 前端：Vue2.x、iView、Webpack...(前端开源项目：https://github.com/artiely/vue-admin-iview.git)

## 预览
![](doc/images/login.jpg)
![](doc/images/home.jpg)
![](doc/images/user.jpg)
![](doc/images/lock.jpg)

## 目录结构

    Atomic:
          ├─atomic-authority `(授权中心:5005)`
          ├─atomic-common    (通用模块)
          ├─atomic-config    `(配置中心:8888)`
          ├─atomic-gateway   `(对外暴露的API服务网关:5002)`
          ├─atomic-persistence (持久化工具)
          │  ├─atomic-db-mybatis (Mybatis-Plus集成实现)
          │  └─atomic-nosql-redis  (Redis集成实现)
          ├─atomic-registration  `(Eureka服务注册中心:8761)`
          ├─atomic-usercenter  `(用户中心微服务:5004)`
          └─atomic-webui `(基于vue的前端实现, 使用iview-admin脚手架:8080)`
              ├─build (项目构建配置)
              ├─src
              │  ├─api  (API请求封装)
              │  ├─assets (静态资源)
              │  ├─components (自定义组件)
              │  ├─libs (工具文件)
              │  ├─locale (多语言文件)
              │  ├─router (路由配置)
              │  ├─store  (状态管理)
              │  ├─styles (样式文件)
              │  ├─template (模板文件)
              │  ├─utils  (自定义工具)
              │  ├─vendors (公共库文件)
              │  └─views  (页面文件)
              └─static  (静态资源)

## 开发进度

 - 2018/03/16： 初次提交, 完成项目搭建, 实现用户登陆、注销、修改密码（基于SpringSecurity + oAuth2 + JWT）

  
## 如何启动？
**请确保已具备以下环境：**
 1. jdk8
 2. mysql
 3. redis
 4. IntelliJ IDEA(不推荐使用eclipse系列的开发工具)
    ***注**：idea需安装lombok插件, 详见：https://www.cnblogs.com/aligege/p/7797642.html*
 5. nodejs (v8.9.3)
 6. npm (v5.5.1)

**配置hosts：**

    127.0.0.1       atomic-authority.com
    127.0.0.1       atomic-config.com
    127.0.0.1       atomic-gateway.com
    127.0.0.1       atomic-registration.com
    127.0.0.1       atomic-usercenter.com
    
**拉取项目：**

先fork项目到你的账户，再拉取代码到本地:

    git clone https://gitee.com/{{yourUsername}}/atomic.git
    

**导入项目、修改配置：**

1.导入数据库脚本：

/atomic-parent/doc/sqls/atomic.sql (请根据文件名事先创建好数据库, 编码为UTF-8)

2.通过idea导入Atomic项目 （使用Maven的方式导入），等待项目building完毕后，修改相关配置：

    /atomic-parent/atomic-config-repo/application-dev.yml
    在此文件中修改有关mysql和redis连接相关的参数
    
    /Atomic/atomic-config/src/main/resources/application.yml
    在此文件中指定有关SpringCloudConfig配置中心的仓库地址：
      # 配置中心git仓库
      cloud:
        config:
          label: master
          server:
            git:
              uri: https://gitee.com/{{yourUsername}}/atomic.git
              searchPaths: atomic-config-repo
              username:
              password:

提交相关的修改到github。

3.关于vue的API访问跨域代理（可使用默认配置）

详见： atomic-parent/atomic-webui/build/webpack.dev.config.js

    //设置跨域代理
    devServer: {
        disableHostCheck: true,
        historyApiFallback: true,
        hot: true,
        inline: true,
        stats: {colors: true},
        proxy: {
            // 授权服务器
            '/auth/*': {
                target: "http://atomic-gateway.com:5002/api/auth/",
                logLevel: "debug",
                changeOrigin: true,
                pathRewrite: {
                    "^/auth": ""
                }
            },
            // 用户中心服务器
            '/uc/*': {
                target: "http://atomic-gateway.com:5002/api/uc/",
                logLevel: "debug",
                changeOrigin: true,
                pathRewrite: {
                    "^/uc": ""
                }
            }
        }
    }


**启动运行：**

先启动后端服务，按顺序依次启动以下服务：

    1. RegistrationApplication
    2. ConfigApplication
    3. UserCenterApplication
    4. AuthorityApplication
    5. GatewayApplication
    
再启动前端项目：

打开idea自带的终端命令行工具, 依次执行以下命令：

    cd atomic-webui
    npm install
    npm run dev


打开浏览器查看：http://localhost:8080, 默认用户名密码：admin / password
