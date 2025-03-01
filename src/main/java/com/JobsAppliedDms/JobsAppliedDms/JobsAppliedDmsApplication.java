package com.JobsAppliedDms.JobsAppliedDms;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
public class JobsAppliedDmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobsAppliedDmsApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
