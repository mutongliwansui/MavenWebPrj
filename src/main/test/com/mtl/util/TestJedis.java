package com.mtl.util;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.logging.Logger;

public class TestJedis {
    private static Logger LOGGER = Logger.getLogger("TestJedis");
    @Test
    public void testConnect(){
        Jedis jedis = new Jedis("192.168.29.128",6379);
        LOGGER.info("连接Redis成功了！");
        jedis.auth("ly520101");
        LOGGER.info("密码验证成功");
//        jedis.set("java","1234");
        String value = jedis.get("userinfo");
        System.out.println("userinfo:"+value);
    }
}
