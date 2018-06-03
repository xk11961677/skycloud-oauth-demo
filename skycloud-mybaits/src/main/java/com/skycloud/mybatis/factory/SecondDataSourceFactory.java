package com.skycloud.mybatis.factory;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.skycloud.mybatis.builder.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
@ConditionalOnProperty(prefix = "zyk.mybatis.second", value = "enabled", havingValue = "true")
public class SecondDataSourceFactory {

    @Bean("dataSourceSecond")
    @ConfigurationProperties("spring.datasource.druid.second")
    public DataSource dataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("sqlSessionFactorySecond")
    public SqlSessionFactory sqlSessionFactoryBeanTwo(@Qualifier("dataSourceSecond") DataSource dataSourceSecond) {
        return SqlSessionFactoryBuilder.create().build(dataSourceSecond);
    }

    @Bean("sqlSessionTemplateSecond")
    public SqlSessionTemplate sqlSessionTemplateTwo(@Qualifier("sqlSessionFactorySecond") SqlSessionFactory sqlSessionFactorySecond) {
        return new SqlSessionTemplate(sqlSessionFactorySecond);
    }

//    @Bean(name = "txManagerSecond")
//    @Primary
//    public DataSourceTransactionManager transactionManager(@Qualifier("dataSourceSecond") DataSource dataSourceSecond) {
//        return new DataSourceTransactionManager(dataSourceSecond);
//    }

}
