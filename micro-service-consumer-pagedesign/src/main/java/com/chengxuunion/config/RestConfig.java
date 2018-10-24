package com.chengxuunion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest配置
 *
 * @author kutome
 * @date 2018年10月24日
 * @version V1.0
 */
@Configuration
public class RestConfig {

	/**
	 * 客户端使用对象配置
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
