package com.mt.doublesource.config.redis.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/*
 *项目名: tls
 *文件名: RedisSubPreAlarmMessageListener
 *创建者: mt-liuy liuyang@tjmeiteng.com
 *创建时间:2019/4/19 14:08
 *描述: pre告警通道订阅
 */
@Component("alarmListener")
@Slf4j
public class RedisSubPreAlarmMessageListener implements MessageListener {
    private RedisSerializer<?> serializer = new StringRedisSerializer();
    @Override
    public void onMessage(Message message, byte[] bytes) {
        Object body = serializer.deserialize(message.getBody());
        log.error("收到pre传来的告警信息--->{}", body.toString());
    }
}
