package com.fueiji.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fueiji.employee.interceptor.AppInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AppMVCConfigure {

	@Bean
	WebMvcConfigurer mvcConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
//				log.info("Adding AppInterceptor interceptor...");
				registry.addInterceptor(new AppInterceptor());
			}

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedHeaders("*").allowedMethods("*").allowedOrigins("*");
			}
		};
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
