package com.employee.portal.employeeportalservice.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author jogeswar.sahu
 *
 */
/**
 * The Class RepositoryConfiguration.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "com.employee.portal.employeeportalservice.entity" })
@EnableJpaRepositories(basePackages = { "com.employee.portal.employeeportalservice.repository" })
@EnableTransactionManagement
public class RepositoryConfiguration {

}
