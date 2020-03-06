package com.mt.doublesource.config.redis;

/*
 *项目名: tls
 *文件名: SystemChannelConfig
 *创建者: mt-liuy liuyang@tjmeiteng.com
 *创建时间:2019/6/5 10:37
 *描述: 系统关注的通道的配置
 */
public class SystemChannelConfig {
    /**
     * pre数据通道
     */
    public static final String PRE_POINT_MSG_CHANNEL= "sendChannel";
    /**
     * 接收pre报警的通道
     */
    public static final String PRE_ALARM_CHANNEL= "sendAlarmChannel";

    /**
     * 接收溜槽算法控制消息的通道
     */
    public static final String SYS_COMMAND_CHANNEL= "TLSControlChannel";
    /**
     * 接收装车系统各个程序消息的通道
     */
    public static final String SYS_MSG_CHANNEL= "TLSDataChannel";
    /**
     * 接收装车各个系统报警消息的通道
     */
    public static final String SYS_ALARM_CHANNEL= "TLSAlarmChannel";

}
