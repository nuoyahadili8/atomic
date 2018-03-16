package com.github.al.nosql.redis.test;

import redis.clients.jedis.Jedis;

/**
 * @Author An
 * @Description:
 * @Date: create in 2018/3/11 15:56
 * @Modified By:
 */
public class RedisStringJava {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost",6379);
        System.out.println("连接成功");
        jedis.select(11);
        //设置 redis 字符串数据
        jedis.set("atomic001", "www.atomic.com");

        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("atomic001"));
    }
}
