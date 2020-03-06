package com.mt.doublesource.config.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/*
 *项目名: tls
 *文件名: RedisSubTaskMessageListener
 *创建者: Administrator
 *创建时间:2019/4/29 17:35
 *描述: 装车程序控制通道监听
 */
@Component("TLSControlListener")
@Slf4j
public class RedisSubTLSControlMessageListener implements MessageListener {


    private RedisSerializer<?> serializer = new StringRedisSerializer();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String msg = serializer.deserialize(message.getBody()).toString();
        log.info("redis通道收到指令{}", msg);

    }
}
