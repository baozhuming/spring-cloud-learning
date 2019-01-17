package com.thymeleaf.thymeleaf.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {
    @Autowired
    JobLauncher cvsJobLauncher;
    @Autowired
    Job importJob;
    public JobParameters jobParameters;
    @RequestMapping("/imp")
    public String imp(@RequestParam(name = "filename") String fileName) throws Exception{
        String path = fileName+".csv";
        jobParameters = new JobParametersBuilder()
                .addLong("time",System.currentTimeMillis())
                .addString("input.file.name",path)
                .toJobParameters();
        cvsJobLauncher.run(importJob,jobParameters);
        return "ok";
    }
}
