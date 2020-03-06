package com.mt.doublesource.config.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.database1")
public class UserDb1Config extends AbstractMongoConfig {
    //设置为默认数据源，如果定义mongoTemplate的时候未指定数据源，将默认为此数据源

    @Bean
    @Primary
    public MongoClient  mongoClient(){
        ServerAddress serverAddress = new ServerAddress(host, port);
        MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(100)
                .socketTimeout(30000)
                .connectTimeout(3000)
                .build();
        MongoClient mongoClient = new MongoClient(serverAddress, mongoCredential, options);
        return  mongoClient;
    }

    @Primary
    @Override
    public @Bean(name = "userDb1MongoTemplate")
    MongoTemplate getMongoTemplate(MongoMappingContext context) throws Exception {
        MongoDbFactory mongoDbFactory = mongoDbFactory();
        System.out.println(mongoDbFactory);
//        mongoDbFactory.
        //去除保存实体时，spring data mongodb 自动添加的_class字段
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);

        return mongoTemplate;
    }
}