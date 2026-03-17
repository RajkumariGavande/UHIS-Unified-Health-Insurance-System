package com.admin.app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MediShieldAdminServiceApplication {

	@Bean
	public ModelMapper mapper() {
		return new ModelMapper();
	}
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MediShieldAdminServiceApplication.class, args);
		context.getBean(ModelMapper.class);
	}

}
