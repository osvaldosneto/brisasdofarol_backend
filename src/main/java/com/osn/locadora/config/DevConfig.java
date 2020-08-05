package com.osn.locadora.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.osn.locadora.services.EmailService;
import com.osn.locadora.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
