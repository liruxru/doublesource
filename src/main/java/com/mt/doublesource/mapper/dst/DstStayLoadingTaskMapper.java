package com.mt.doublesource.mapper.dst;

import org.apache.ibatis.annotations.Mapper;
import com.mt.doublesource.pojo.StayLoadingTask;

import java.util.List;

@Mapper
public interface DstStayLoadingTaskMapper {
    List<StayLoadingTask> selectAll();
}
