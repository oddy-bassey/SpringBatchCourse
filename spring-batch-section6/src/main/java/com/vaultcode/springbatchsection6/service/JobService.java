package com.vaultcode.springbatchsection6.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class JobService {

    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("chunkJob")
    @Autowired
    private Job firstJob;

    @Qualifier("taskletJob")
    @Autowired
    private Job secondJob;

    @Async
    public void startJob (String jobName) {
        try {
            Map<String, JobParameter<?>> params = new HashMap<>();
            params.put("currentTime", new JobParameter<>(System.currentTimeMillis(), Long.class));
            JobParameters jobParameters = new JobParameters(params);
            JobExecution jobExecution = null;

            if(jobName.equals("chunkJob")) {
                jobExecution = jobLauncher.run(firstJob, jobParameters);
            } else if(jobName.equals("taskletJob")) {
                jobExecution = jobLauncher.run(secondJob, jobParameters);
            }
            assert jobExecution != null;
            log.info("Job execution id = {}", jobExecution.getJobInstance().getId());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
