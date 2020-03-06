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
 *描述: 装车程序报警通道 todo 暂时不需要
 */
@Component("TLSAlarmListener")
@Slf4j
public class RedisSubTLSAlarmMessageListener implements MessageListener {
    private RedisSerializer<?> serializer = new StringRedisSerializer();
    @Override
    public void onMessage(Message message, byte[] bytes) {
        Object body = serializer.deserialize(message.getBody());


    }
}
