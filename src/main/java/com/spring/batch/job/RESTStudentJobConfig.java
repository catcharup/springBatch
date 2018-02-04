package com.spring.batch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.spring.batch.model.StudentDTO;

@Configuration
public class RESTStudentJobConfig {
	
	Logger logger = LoggerFactory.getLogger(RESTStudentJobConfig.class);
	
	@Value("${spring.batch.rest.url:null}")
	private String restUrl;
	
	@Bean
	public String getRestUrl(){
		return restUrl;
	}
 
    @Bean(name="restStudentReader")
    ItemReader<StudentDTO> restStudentReader(String restUrl, 
                                             RestTemplate restTemplate) {
    	
    	logger.info("requesting rest url is ************  : " + restUrl);
        return new RESTStudentReader(
        		restUrl, 
            restTemplate
        );
    }
}
