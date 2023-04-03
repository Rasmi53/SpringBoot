package com.fueiji.employee.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = { "com.fueiji.employee.jpa.repository" })
@EnableTransactionManagement
public class JPAConfiguration {

}
