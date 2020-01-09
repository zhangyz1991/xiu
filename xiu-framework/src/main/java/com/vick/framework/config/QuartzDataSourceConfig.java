package com.vick.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * 自定义quartz数据源 若无此配置,则quartz的数据源为springboot默认的数据源
 *
 * @author zyz
 * @since 2020-01-03
 */
@Configuration
public class QuartzDataSourceConfig {

    /**
     * 作者：zyz 日期：2020/1/9 描述：@QuartzDataSource注解指明了quartz的数据源
     */
    @Profile("test")
    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = "spring.datasource.test-quartz")
    public DataSource testQuartzDataSource() {
        //未指定数据源类型,所以使用springboot默认数据源HikariCP
        return DataSourceBuilder.create().build();
    }

    @Profile("dev")
    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = "spring.datasource.druid.dev-quartz")
    public DataSource devQuartzDataSource() {
        //指定了数据源类型为DruidDataSource
        return new DruidDataSource();
    }

    @Profile("product")
    @Bean
    @QuartzDataSource
    @ConfigurationProperties(prefix = "spring.datasource.druid.quartz")
    public DataSource quartzDataSource() {
        //指定了数据源类型为DruidDataSource
        return new DruidDataSource();
    }

}
