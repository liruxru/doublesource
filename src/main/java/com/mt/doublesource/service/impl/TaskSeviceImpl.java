package com.mt.doublesource.service.impl;

import com.mt.doublesource.mapper.src.SrcStayLoadingTaskMapper;
import com.mt.doublesource.pojo.StayLoadingTask;
import com.mt.doublesource.service.TaskSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskSeviceImpl implements TaskSevice {
    @Autowired
    private SrcStayLoadingTaskMapper srcStayLoadingTaskMapper;
    @Override
    public List<StayLoadingTask> selectAll() {
        return srcStayLoadingTaskMapper.selectAll();
    }
}
