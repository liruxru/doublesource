package com.mt.doublesource.config.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

public abstract class AbstractMongoConfig {
    //定义相关连接数据库参数
    protected String host, database, username, password;
    protected int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    /**
     * 创建MongoDbFactory，不同数据源继承该方法创建对应的MongoDbFactory。
     * @return
     * @throws Exception
     */
    public MongoDbFactory mongoDbFactory() throws Exception {

        return new SimpleMongoDbFactory(mongoClient(), database);
    }

    /**
     * 不整这个  会自动注入一个连接本地的mongo
     * 要么配置 @SpringBootApplication(exclude = {MongoAutoConfiguration.class,
     *         SpringDataWebAutoConfiguration.class})
     * @return
     */
    protected abstract MongoClient mongoClient();

    /**
     * 抽象方法，用于返回MongoTemplate
     * @param context
     * @return
     * @throws Exception
     */
    abstract public MongoTemplate getMongoTemplate(MongoMappingContext context) throws Exception;
}
