package com.mt.doublesource.document;

import lombok.Data;

/**
 * 偏载的二维记录
 *
 * @author howie
 * @date 2019/12/3 20:34
 */
@Data
public class BiasedLoad {

    /**
     * 点位
     */
    private Integer point;

    /**
     * 对应点的高度
     */
    private Float value;
}
