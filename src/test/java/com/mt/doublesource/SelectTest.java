package com.mt.doublesource;

import com.mt.doublesource.mapper.dst.DstStayLoadingTaskMapper;
import com.mt.doublesource.pojo.StayLoadingTask;
import com.mt.doublesource.service.TaskSevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoubleDataSourceApplication.class)
@WebAppConfiguration
public class SelectTest {
    @Autowired
    private TaskSevice taskSevice;
    @Autowired
    private DstStayLoadingTaskMapper dstStayLoadingTaskMapper;

    @Test
    public void test(){
        List<StayLoadingTask> stayLoadingTasks = taskSevice.selectAll();
        System.out.println(stayLoadingTasks.size());
//        for (StayLoadingTask stayLoadingTask : stayLoadingTasks) {
//            System.out.println(stayLoadingTask);
//        }
        List<StayLoadingTask> stayLoadingTasks1 = dstStayLoadingTaskMapper.selectAll();
        System.out.println(stayLoadingTasks1.size());
//        for (StayLoadingTask stayLoadingTask : stayLoadingTasks1) {
//            System.out.println(stayLoadingTask);
//        }

    }
}
