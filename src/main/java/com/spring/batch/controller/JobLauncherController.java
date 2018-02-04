package com.spring.batch.controller;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class JobLauncherController {
	
	Logger logger = LoggerFactory.getLogger(JobLauncherController.class);
	
	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	// @Scheduled(fixedDelayString="1000", initialDelayString="6000")
	public void launchJob(){
		try{
			
			JobParameters jobParameters = new JobParametersBuilder()
											.addDate("date", new Date())
											.toJobParameters();
			JobExecution jobExecution = jobLauncher.run(job, jobParameters);
			BatchStatus batchStatus = jobExecution.getStatus();
			while(batchStatus.isRunning()){
				logger.info("*********** Still running.... **************");
			Thread.sleep(1000);
			}
			logger.info(String.format("*********** Exit status: %s", jobExecution.getExitStatus().getExitCode()));
			JobInstance jobInstance = jobExecution.getJobInstance();
			logger.info(String.format("********* Name of the job %s", jobInstance.getJobName()));




			logger.info(String.format("*********** job instance Id: %d", jobInstance.getId()));


			
		}catch(Exception ex){
			
		logger.error("Exception while processing data  : " + ex);
			
		}
		
	}

}
