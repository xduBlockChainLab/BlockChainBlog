package com.bc208.blog.config.datasourceConfig;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author QingheLi
 */
@Configuration
@MapperScan(basePackages = "com/bc208/blog/repository/quartz/mapper", sqlSessionFactoryRef = "quartzSqlSessionFactory")
public class DataSourceQuartzConfig {
    /**
     * mapper.xml所在地址
     */
    private static final String MAPPER_LOCATION = "classpath*:mapper/quartzMapper/*.xml";

    /**
     * 数据源
     */
    @Bean(name = "quartzDataSource")
    @QuartzDataSource
    // 读取spring.datasource.slave前缀的配置文件映射成对应的配置对象
    @ConfigurationProperties(prefix = "spring.datasource.quartz")
    public DataSource dataSource() {
        DataSource build = DataSourceBuilder.create().build();
        return build;
    }


    /**
     * 事务管理器
     */
    @Bean(name = "quartzTransactionManager")
    public PlatformTransactionManager dataSourceTransactionManager(@Qualifier("quartzDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * session工厂
     */

    @Bean(name = "quartzSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("quartzDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceQuartzConfig.MAPPER_LOCATION));
        return sessionFactoryBean.getObject();
    }

}
