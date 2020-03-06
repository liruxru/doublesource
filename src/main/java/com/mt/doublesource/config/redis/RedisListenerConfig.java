package com.mt.doublesource.config.redis;

import com.mt.doublesource.config.redis.listener.RedisSubModelCollectListener;
import com.mt.doublesource.config.redis.listener.RedisSubPreNormalMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import javax.annotation.Resource;

/**
 *项目名: tls
 *文件名: RedisTemplateConfig
 *创建者: mt-liuy liuyang@tjmeiteng.com
 *创建时间:2019/4/19 13:31
 *描述: pre往redis发送消息时用的 stringRedisTemplate value的序列化使用的 jackon   本系统同pre相同
 *
 *
 */
@Configuration
public class RedisListenerConfig {

    /**
     * pre 实时数据更改的监听
     */
    @Resource(name="normalListener")
    private MessageListener normalListener;

    @Resource(name ="modelListener")
    private MessageListener modelListener;
    /**
     * pre报警通道的监听
     */
    @Resource(name="alarmListener")
    private MessageListener alarmListener;
    /**
     *本程序本身的报警通道监听
     * 其他程序或者算法 发送报警消息
     * 在这里统一解析消息 统一控制
     */
    @Resource(name ="TLSDataListener")
    private MessageListener TLSDataListener;
    @Resource(name ="TLSAlarmListener")
    private MessageListener TLSAlarmListener;


    /**
     * 本程序命令通道下发的监听
     * 主要用于溜槽控制与主控程序的交互
     * 溜槽控制向通道中发送消息   主控监听通道 解析命令 实现控制下发
     */
    @Resource(name ="TLSControlListener")
    private MessageListener TLSControlListener;



    /**
     * 配置redis订阅  主要与pre交互
     * @param redisTemplate
     * @return
     */
    @Bean("preContainer")
    RedisMessageListenerContainer containerPre(@Autowired @Qualifier("rtdRedisTemplate") RedisTemplate redisTemplate) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        container.addMessageListener(normalListener, new ChannelTopic(SystemChannelConfig.PRE_POINT_MSG_CHANNEL));
        container.addMessageListener(alarmListener, new ChannelTopic(SystemChannelConfig.PRE_ALARM_CHANNEL));
        container.addMessageListener(modelListener, new ChannelTopic(SystemChannelConfig.PRE_POINT_MSG_CHANNEL));
        ((RedisSubPreNormalMessageListener)normalListener).setRedisTemplate(systemRedisTemplate);
        ((RedisSubModelCollectListener)modelListener).setRedisTemplate(systemRedisTemplate);

        return container;
    }

    /**
     *配置智能装车各个程序之间的消息订阅
     */
    @Resource(name="systemRedisTemplate")
    private RedisTemplate systemRedisTemplate;
    @Bean("systemContainer")
    RedisMessageListenerContainer containerSystem() {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(systemRedisTemplate.getConnectionFactory());
        container.addMessageListener(TLSDataListener, new ChannelTopic(SystemChannelConfig.SYS_MSG_CHANNEL));
        container.addMessageListener(TLSAlarmListener, new ChannelTopic(SystemChannelConfig.SYS_ALARM_CHANNEL));
        container.addMessageListener(TLSControlListener, new ChannelTopic(SystemChannelConfig.SYS_COMMAND_CHANNEL));
        return container;
    }

}
