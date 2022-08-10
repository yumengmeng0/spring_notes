package org.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author: ymm
 * @date: 2022/8/10
 * @version: 1.0.0
 * @description:
 */
@Configuration
@ComponentScan("org.example")
@Import(DataSourceConfig.class)
@EnableTransactionManagement // 事务注解支持 <tx:annotation-driven></tx:annotation-driven>
public class SpringConfig {

    @Bean
    public JdbcTemplate getJdbcTemplate(@Autowired DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    // 事务管理器对象
    @Bean
    public TransactionManager getTransactionManager(@Autowired DataSource dataSource) {
        TransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }

}
