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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@MapperScan(basePackages = {"com.mt.doublesource.mapper.dst"}, sqlSessionTemplateRef = "dstSqlSessionTemplate")
public class MysqlDstConfig {

    @Value("${spring.datasource.database2.driverClassName}")
    String driverClassName;
    @Value("${spring.datasource.database2.username}")
    String username;
    @Value("${spring.datasource.database2.password}")
    String password;
    @Value("${spring.datasource.database2.url}")
    String url;

    @Bean(name="dstDataSource")
    @Primary//必须加此注解，不然报错，下一个类则不需要添加 spring.datasource
    @ConfigurationProperties(prefix="spring.datasource.database2.druid")//prefix值必须是application.properteis中对应属性的前缀
    public DataSource dstDataSource() throws SQLException {
        DruidDataSource druid = new DruidDataSource();
        druid.setDriverClassName(driverClassName);
        druid.setUrl(url);
        druid.setUsername(username);
        druid.setPassword(password);
        return druid;
    }


    @Bean(name="dstSqlSessionFactory")
    @Primary
    public SqlSessionFactory dstSqlSessionFactory(@Qualifier("dstDataSource")DataSource dataSource)throws Exception{
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

    @Bean(name="dstSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate baseSqlSessionTemplate(@Qualifier("dstSqlSessionFactory")SqlSessionFactory sqlSessionFactory)throws Exception{
        SqlSessionTemplate template=new SqlSessionTemplate(sqlSessionFactory);//使用上面配置的Factory
        return  template;
    }
}
