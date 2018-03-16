const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const merge = require('webpack-merge');
const webpackBaseConfig = require('./webpack.base.config.js');
const fs = require('fs');
const package = require('../package.json');

fs.open('./build/env.js', 'w', function (err, fd) {
    const buf = 'export default "development";';
    fs.write(fd, buf, 0, buf.length, 0, function (err, written, buffer) {
    });
});

module.exports = merge(webpackBaseConfig, {
    devtool: '#source-map',
    output: {
        publicPath: '/dist/',
        filename: '[name].js',
        chunkFilename: '[name].chunk.js'
    },
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
                //target: "http://fastjee-gateway.com:5002/api/auth/",
                target: "http://atomic-gateway.com:5002/api/auth/",
                // target: 'http://fastjee.s1.natapp.cc/api/auth/',
                logLevel: "debug",
                changeOrigin: true,
                pathRewrite: {
                    "^/auth": ""
                }
            },
            // 用户中心服务器
            '/uc/*': {
                //target: "http://fastjee-gateway.com:5002/api/uc/",
                target: "http://atomic-gateway.com:5002/api/uc/",
                // target: 'http://fastjee.s1.natapp.cc/api/uc/',
                logLevel: "debug",
                changeOrigin: true,
                pathRewrite: {
                    "^/uc": ""
                }
            }
        }
    },
    plugins: [
        new ExtractTextPlugin({
            filename: '[name].css',
            allChunks: true
        }),
        new webpack.optimize.CommonsChunkPlugin({
            name: ['vender-exten', 'vender-base'],
            minChunks: Infinity
        }),
        new HtmlWebpackPlugin({
            title: 'iView admin v' + package.version,
            filename: '../index.html',
            inject: false
        }),
        new CopyWebpackPlugin([
            {
                from: 'src/views/main-components/theme-switch/theme'
            },
            {
                from: 'src/views/my-components/text-editor/tinymce'
            }
        ], {
            ignore: [
                'text-editor.vue'
            ]
        })
    ]
});
