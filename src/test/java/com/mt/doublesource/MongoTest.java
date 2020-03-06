package com.mt.doublesource;

import com.mt.doublesource.document.BiasedLoadDao;
import com.mt.doublesource.repository.MongoDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoubleDataSourceApplication.class)
@WebAppConfiguration
public class MongoTest {
    @Autowired
    private MongoDao mongoDao;
    @Test
    public void test1(){
        BiasedLoadDao biasedLoadDao = new BiasedLoadDao();
        biasedLoadDao.setTaskNum("67E433D4-351C-42BD-9A11-CC4328FEAB06");
        biasedLoadDao.setTrainNum("10");
        BiasedLoadDao biasedLoad = mongoDao.findBiasedLoad(biasedLoadDao);
        System.out.println(biasedLoad.getBiasedLoadList());
    }

    @Test
    public void test2(){
        BiasedLoadDao biasedLoadDao = new BiasedLoadDao();
        biasedLoadDao.setTaskNum("C41CA3F8-A4D2-4438-BC8D-A5AEC7DDD9FF");
        biasedLoadDao.setTrainNum("1");
        BiasedLoadDao biasedLoad = mongoDao.findBiasedLoad2(biasedLoadDao);
        System.out.println(biasedLoad.getBiasedLoadList());
    }
}
