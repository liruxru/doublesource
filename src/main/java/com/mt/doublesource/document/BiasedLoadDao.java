package com.mt.doublesource.document;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * 偏载的二维记录
 *
 * @author howie
 * @date 2019/12/3 20:34
 */
@Data
@Document(collection = "RadarData")
public class BiasedLoadDao {

    /**
     * 任务编号
     */
    private String taskNum;

    /**
     * 车厢编号
     */
    private String trainNum;

    /**
     * 车厢序号
     */
    private Integer orderNum;

    /**
     * 偏载的二维记录
     */
    private List<BiasedLoad> biasedLoadList;

    /**
     * 录入时间
     */
    private Date createTime;
}
