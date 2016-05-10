package com.worksap.stm2016.config;

import com.worksap.stm2016.audit.AuditorAwareImpl;
import com.worksap.stm2016.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@EnableJpaRepositories(basePackages = {"com.worksap.stm2016.repository"})
@EnableTransactionManagement
@EnableJpaAuditing
public class AuditingConfig {
	@Bean
	public AuditorAware<User> auditorProvider() {
		return new AuditorAwareImpl();
	}
}