package com.mt.doublesource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoubleDataSourceApplication.class)
@WebAppConfiguration
public class RedisTest {
    @Autowired@Qualifier("rtdRedisTemplate")
    private RedisTemplate<String,String> rtdRedisTemplate;
    @Autowired@Qualifier("systemRedisTemplate")
    private RedisTemplate<String,String> systemRedisTemplate;
    @Test
    public void test1(){
        rtdRedisTemplate.opsForValue().set("test","test");
        String test = rtdRedisTemplate.opsForValue().get("test");
        System.out.println(test);
        test = systemRedisTemplate.opsForValue().get("test");
        System.out.println(test);
        systemRedisTemplate.opsForValue().set("test","test1");
        test = systemRedisTemplate.opsForValue().get("test");
        System.out.println(test);
    }
}
