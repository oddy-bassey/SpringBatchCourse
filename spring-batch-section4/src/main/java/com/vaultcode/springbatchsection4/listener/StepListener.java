package com.vaultcode.springbatchsection4.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info(">> Before Step {}", stepExecution.getStepName());
        log.info("-- Job exec context {}", stepExecution.getJobExecution().getExecutionContext());
        log.info("<< Step execution context {}", stepExecution.getExecutionContext());
        stepExecution.getExecutionContext().put("override", "true;deltaX");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info(">> After Step {}", stepExecution.getStepName());
        log.info("-- Job exec context {}", stepExecution.getJobExecution().getExecutionContext());
        log.info("<< Step execution context {}", stepExecution.getExecutionContext());
        return StepExecutionListener.super.afterStep(stepExecution);
    }
}
