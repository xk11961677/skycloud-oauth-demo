package com.skycloud.mybatis;

import com.skycloud.mybatis.factory.FirstDataSourceFactory;
import com.skycloud.mybatis.factory.SecondDataSourceFactory;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * 设计公用mybatis starter , 支持第二数据源 ,支持原有mybatis生成器
 * <p>
 * 1. datasource
 * 2. sqlSessionFactory
 * 3. sqlSessionTemplate
 */
@Configuration
@ImportAutoConfiguration({FirstDataSourceFactory.class, SecondDataSourceFactory.class})
public class MyBatisAutoConfigurer {

}
