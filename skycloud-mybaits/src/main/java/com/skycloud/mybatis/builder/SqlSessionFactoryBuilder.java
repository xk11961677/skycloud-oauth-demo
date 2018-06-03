package com.skycloud.mybatis.builder;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author sky
 */
public class SqlSessionFactoryBuilder {

    public static SqlSessionFactoryBuilder create() {
        return new SqlSessionFactoryBuilder();
    }

    public SqlSessionFactory build(DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            Resource[] resources = resolver.getResources("classpath:/mybatis/mapper/*Mapper.xml");

            Resource[] res = Arrays.copyOf(resources, resources.length + 1);

            res[resources.length] = resolver.getResource("classpath:/mybatis/ReuseSQL.xml");

            bean.setMapperLocations(res);
            bean.setConfigLocation(resolver.getResource("classpath:/mybatis/mybatis-config.xml"));

            return bean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
