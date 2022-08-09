package org.example.config;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/**
 * @author: ymm
 * @date: 2022/8/8
 * @version: 1.0.0
 * @description:
 */
@Configuration
@ComponentScan("org.example")
@Import(DataSourceConfig.class)
@EnableAspectJAutoProxy
public class SpringConfig {

    @Bean("queryRunner")
    public QueryRunner getQueryRunner(@Autowired DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

}
