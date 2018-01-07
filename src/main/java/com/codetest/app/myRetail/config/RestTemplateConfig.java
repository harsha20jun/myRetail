/**
 * 
 */
package com.codetest.app.myRetail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author rpcha
 *
 */
@Configuration
public class RestTemplateConfig {
	
	@Bean
	public RestTemplate restTemplate() {
	   return new RestTemplate();
	}

}
