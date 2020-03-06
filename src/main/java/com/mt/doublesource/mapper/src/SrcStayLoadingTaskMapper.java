package com.mt.doublesource.mapper.src;

import com.mt.doublesource.pojo.StayLoadingTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SrcStayLoadingTaskMapper {
    List<StayLoadingTask> selectAll();
}
