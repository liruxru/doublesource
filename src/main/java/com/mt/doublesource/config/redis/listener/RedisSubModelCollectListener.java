package com.mt.doublesource.config.redis.listener;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/*
 *项目名: tls
 *文件名: RedisSubModelCollectListener
 *创建者: mt-liuy liuyang@tjmeiteng.com
 *创建时间:2019/8/3 13:06
 *描述: 模型采集监听
 * 模型采集
 * 动作触发:溜槽动作 闸板打开时机
 * 其他关注信息：任务编号 车厢序号 车厢型号   车厢位置:车头坐标 车尾坐标  车速  此时定量仓重量 此时溜槽的高度  时间戳
 */
@Slf4j
@Component("modelListener")
public class RedisSubModelCollectListener implements MessageListener {

    @Setter
    private RedisTemplate<String, String> redisTemplate;

    private RedisSerializer<?> serializer = new StringRedisSerializer();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        Object body = serializer.deserialize(message.getBody());

    }
}
