package com.mt.doublesource.config.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = {"com.mt.doublesource.mapper.src"}, sqlSessionTemplateRef = "srcSqlSessionTemplate")
public class MysqlSrcConfig {
    @Value("${spring.datasource.database1.driverClassName}")
    String driverClassName;
    @Value("${spring.datasource.database1.username}")
    String username;
    @Value("${spring.datasource.database1.password}")
    String password;
    @Value("${spring.datasource.database1.url}")
    String url;

    @Bean(name="srcDataSource")
    @ConfigurationProperties(prefix="spring.datasource.database1.druid")//prefix值必须是application.properteis中对应属性的前缀
    public DataSource srcDataSource() throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        druid.setDriverClassName(driverClassName);
        druid.setUrl(url);
        druid.setUsername(username);
        druid.setPassword(password);
        return druid;
    }


    @Bean(name="srcSqlSessionFactory")
    public SqlSessionFactory srcSqlSessionFactory(@Qualifier("srcDataSource")DataSource dataSource)throws Exception{
        // 创建Mybatis的连接会话工厂实例
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);//// 设置数据源bean
        //添加XML目录
        ResourcePatternResolver resolver=new PathMatchingResourcePatternResolver();
        try{
            bean.setMapperLocations(resolver.getResources("classpath:mapping/*Mapper.xml"));//// 设置mapper文件路径
            bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
            return bean.getObject();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean(name="srcSqlSessionTemplate")
    public SqlSessionTemplate baseSqlSessionTemplate(@Qualifier("srcSqlSessionFactory")SqlSessionFactory sqlSessionFactory)throws Exception{
        SqlSessionTemplate template=new SqlSessionTemplate(sqlSessionFactory);//使用上面配置的Factory
        return  template;
    }
}
