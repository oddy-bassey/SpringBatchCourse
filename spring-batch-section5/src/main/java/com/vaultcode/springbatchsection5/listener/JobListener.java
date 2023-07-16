package com.vaultcode.springbatchsection5.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info(">> Before job {}", jobExecution.getJobInstance().getJobName());
        log.info("-- Job params {}", jobExecution.getJobParameters());
        log.info("<< Job execution context {}", jobExecution.getExecutionContext());
        jobExecution.getExecutionContext().put("override", "true;deltaX");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info(">> After job {}", jobExecution.getJobInstance().getJobName());
        log.info("-- Job params {}", jobExecution.getJobParameters());
        log.info("<< Job execution context {}", jobExecution.getExecutionContext());
    }
}
