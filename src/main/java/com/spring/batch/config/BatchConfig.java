package com.spring.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.batch.model.StudentDTO;

@Configuration
public class BatchConfig {
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("restStudentReader")
	ItemReader<StudentDTO> restStudentReader;
	
	@Autowired
	@Qualifier("studentItemWriter")
	ItemWriter<StudentDTO> studentItemWriter;
	
	@Bean
	public Job job(){
		return jobBuilderFactory.get("studeentJob")
				.incrementer(new RunIdIncrementer())
				.flow(step())
				.end()
				.build();
	}
	
	@Bean
	public Step step(){
		return stepBuilderFactory.get("studentStep")
				.<StudentDTO, StudentDTO>chunk(1)
				.reader(restStudentReader)
				.writer(studentItemWriter)
				.build();
	}

}
