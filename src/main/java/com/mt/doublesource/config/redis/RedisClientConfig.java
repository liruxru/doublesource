package com.mt.doublesource.config.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 *项目名: tls
 *文件名: RedisTemplateConfig
 *创建者: mt-liuy liuyang@tjmeiteng.com
 *创建时间:2019/4/19 13:31
 *描述: redis客户端的配置
 * 定义了
 * 1.与pre交互的redis客户端
 * 2.系统缓存中的客户端
 */
@Configuration
//@PropertySource(value = "classpath:redis-config.properties", encoding = "utf-8")
public class RedisClientConfig {

    /**
     * 用来监听pre实时数据的客户端
     * @param factoryA
     * @return
     */
    @Bean(name = "rtdRedisTemplate")
    public StringRedisTemplate rtdRedisClientTemplate(@Autowired @Qualifier("factoryPre")LettuceConnectionFactory factoryA){
        StringRedisTemplate template = getRedisTemplate();
        template.setConnectionFactory(factoryA);
        return template;
    }

    /**
     * 用于系统实时消息的客户端
     * @param factoryB
     * @return
     */
    @Bean(name="systemRedisTemplate")
    public StringRedisTemplate systemClientRedis(@Autowired @Qualifier("factoryTls") LettuceConnectionFactory factoryB){
        StringRedisTemplate template = getRedisTemplate();
        template.setConnectionFactory(factoryB);
        return template;
    }


    @Bean
    @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    @Scope(value = "prototype")
    public GenericObjectPoolConfig redisPool(){
        return new GenericObjectPoolConfig();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.redis-pre")
    public RedisStandaloneConfiguration redisConfigA(){
        return new RedisStandaloneConfiguration();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis.redis-tls")
    public RedisStandaloneConfiguration redisConfigB(){
        return new RedisStandaloneConfiguration();
    }


    @Bean("factoryPre")
    @Primary
    public LettuceConnectionFactory factoryA(GenericObjectPoolConfig config, RedisStandaloneConfiguration redisConfigA){
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(config).commandTimeout(Duration.ofMillis(config.getMaxWaitMillis())).build();
        return new LettuceConnectionFactory(redisConfigA, clientConfiguration);
    }

    @Bean("factoryTls")
    public LettuceConnectionFactory factoryB(GenericObjectPoolConfig config, RedisStandaloneConfiguration redisConfigB){
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(config).commandTimeout(Duration.ofMillis(config.getMaxWaitMillis())).build();
        return new LettuceConnectionFactory(redisConfigB, clientConfiguration);
    }




    private StringRedisTemplate getRedisTemplate(){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}
