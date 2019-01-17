package com.thymeleaf.thymeleaf.batch.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 自动批处理
 */
@Service
public class ScheduledTaskService {
    @Autowired
    JobLauncher cvsJobLauncher;
    @Autowired
    Job importJob;
    public JobParameters jobParameters;
//    @Scheduled(fixedRate = 2000)
    public void execute() throws Exception{
        jobParameters = jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .toJobParameters();
        cvsJobLauncher.run(importJob,jobParameters);
    }

}
