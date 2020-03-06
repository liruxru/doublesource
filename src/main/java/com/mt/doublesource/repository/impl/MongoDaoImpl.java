package com.mt.doublesource.repository.impl;

import com.mt.doublesource.document.BiasedLoadDao;
import com.mt.doublesource.repository.MongoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/*
 *项目名: tls
 *文件名: MongoDaoImpl
 *创建者: mt-liuy liuyang@tjmeiteng.com
 *创建时间:2019/4/17 12:13
 *描述: TODO
 */
@Repository
public class MongoDaoImpl implements MongoDao {

    @Autowired@Qualifier("userDb1MongoTemplate")
    private MongoTemplate mongoTemplate;
    @Autowired@Qualifier("userDb2MongoTemplate")
    private MongoTemplate userDb2MongoTemplate;

    /**
     * 取得偏载历史记录
     * @param biasedLoad
     * @return
     */
    @Override
    public BiasedLoadDao findBiasedLoad(BiasedLoadDao biasedLoad) {
        Criteria criatira = new Criteria();
        criatira.andOperator(Criteria.where("taskNum").is(biasedLoad.getTaskNum()),
                Criteria.where("trainNum").is(biasedLoad.getTrainNum()));
        BiasedLoadDao biasedLoadDao = mongoTemplate.findOne(new Query(criatira), BiasedLoadDao.class);
        return biasedLoadDao;
    }

    @Override
    public BiasedLoadDao findBiasedLoad2(BiasedLoadDao biasedLoadDao) {
        Criteria criatira = new Criteria();
        criatira.andOperator(Criteria.where("taskNum").is(biasedLoadDao.getTaskNum()),
                Criteria.where("trainNum").is(biasedLoadDao.getTrainNum()));
        BiasedLoadDao biasedLoad = userDb2MongoTemplate.findOne(new Query(criatira), BiasedLoadDao.class);
        return biasedLoad;
    }

}
