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
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
@ConditionalOnProperty(prefix = "zyk.mybatis.first", value = "enabled", havingValue = "true")
public class FirstDataSourceFactory {

    @Primary
    @Bean("dataSourcefirst")
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource dataSourceFirst(){
        return DruidDataSourceBuilder.create().build();
    }


    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("dataSourcefirst") DataSource dataSourcefirst) {
        return SqlSessionFactoryBuilder.create().build(dataSourcefirst);
    }

    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

//    @Bean(name = "txManagerFirst")
//    @Primary
//    public DataSourceTransactionManager transactionManager(@Qualifier("dataSourcefirst") DataSource dataSourcefirst) {
//        return new DataSourceTransactionManager(dataSourcefirst);
//    }

}
