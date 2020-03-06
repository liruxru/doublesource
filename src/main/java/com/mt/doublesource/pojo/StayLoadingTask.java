package com.mt.doublesource.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class StayLoadingTask {
    // 任务编号
    private String taskNum;

    // 原任务编号
    private String originalTaskNum;

    // 装车线
    private String loadLine;

    // 单位名称
    private String companyName;

    // 装车日期
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date loadDate;

    // 车次
    private String trainNum;

    // 车型
    private String trainModel;

    // 煤种
    private String coalVariety;

    // 商品类型
    private String product;

    // 用户
    private String userName;

    // 目标载重
    private Float targetLoad;

    // 目标热值code
    private String targetCalorificCode;

    // 目标热值
    private Double targetCalorificValue;

    // 产品分组ID
    private Integer groupId;

    // 通讯PAD
    private String communicationPad;

    // 到厂时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date arrivalTime;

    // 装车时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date loadTime;

    // 装完时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date finishedTime;

    // 总车数
    private Integer trainNumTotal;

    // 总载重
    private Float loadTotal;

    // 总标重
    private Float weightTotal;

    // 装车用时
    private Float loadUseTime;

    // 偏载量
    private Float partialLoad;

    // 平均车速
    private Float averageSpeed;

    // 总实际载重
    private Float actualLoadTotal;

    // 总误差
    private Float deviationTotal;

    // 总实际毛重
    private Float grossWeightTotal;

    // 到站
    private String arriveStation;

    // 班次
    private String trainFlights;

    // 托运人
    private String consignor;

    // 品名
    private String productName;

    // 制票日期
    private String voteMakingDate;

    // 运单号
    private String trackingNum;

    // 制票站
    private String voteMakingStation;

    // 收货人
    private String consignee;

    // 调整系数
    private String adjustmentCoefficient;

    // 装车员
    private String loadPerson;

    // 任务父ID
    private String parentId;

    // 是否是父任务 1：是，否则不是
    private String isParent;

    // 配煤信息
    private String coalMsg;

    // 删除标识 0:未删除 1:已删除
    private String mqdelete;

    // 时间戳
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date mqsjc;

    // 批次
    private String batch;

    // 任务状态
    private String taskState;

    // 最佳车速
    private Float goodSpeed;

    // 装车方式
    private String loadingMode;

    // 配煤方式
    private String coalBlendingMode;

    // 数据类型
    private String dataType;

    // 是否合格
    private String isQualified;

    // 排序
    private Integer sort;

    // 录入时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    // 创建人
    private String createPerson;

    // 系统运行状态
    private String systemStatus;
}
