package com.vaultcode.springbatchsection6.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SecondJobScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Qualifier("taskletJob")
    @Autowired
    private Job secondJob;

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void secondJobStarter() {
        try {
            Map<String, JobParameter<?>> params = new HashMap<>();
            params.put("currentTime", new JobParameter<>(System.currentTimeMillis(), Long.class));

            JobParameters jobParameters = new JobParameters(params);
            JobExecution jobExecution = jobLauncher.run(secondJob, jobParameters);

            log.info("Job execution id = {}", jobExecution.getJobInstance().getId());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
