package com.spring.batch.dao;

import javax.validation.Valid;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class PersistentConetext {
	
	@Value("${spring.datasource.url:null}")
	private String url;
	
	@Value("${spring.datasource.username:test}")
	private String userName;
	
	@Value("${spring.datasource.password:test}")
	private String password;
	
	@Value("${spring.datasource.driverclass.name:null}")
	private String driverClassName;
	
	//DataSource
	
	@Bean
	DataSource dataSource(){
		DataSource dataSource = new DataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setMaxActive(5);
		return dataSource;
	}
	
	@Bean(value="jdbcTemplate")
	NamedParameterJdbcTemplate jdbcTemplate(){
		return new NamedParameterJdbcTemplate(dataSource());
	}
	
	

}
