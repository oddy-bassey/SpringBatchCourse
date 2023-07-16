package com.vaultcode.springbatchsection4.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@Configuration
public class SampleJob {

    @Bean
    public Job firstJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("First Job", jobRepository)
                .start(firstStep(jobRepository, platformTransactionManager))
                .build();
    }

    private  Step firstStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
         return new StepBuilder("First Step", jobRepository)
                 .tasklet(firstTask(), platformTransactionManager)
                 .build();
    }

    private Tasklet firstTask() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                log.info("********This is the first Tasklet step!!*******");
                return RepeatStatus.FINISHED;
            }
        };
    }
}
