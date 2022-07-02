package com.ysahin.devicepool;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author yusufSahin
 *
 */
@Configuration
@ComponentScan
public class ApplicationConfig {

	@Value("${http.read.timeout:3000}")
	private int readTimeout;

	@Value("${http.connect.timeout:3000}")
	private int connectTimeout;

	/*
	 * This is restTemplate class. We have to make it as Bean because of it is not component.
	 */
	@Bean
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(connectTimeout))
				.setReadTimeout(Duration.ofMillis(readTimeout)).build();

		return restTemplate;
	}
}
