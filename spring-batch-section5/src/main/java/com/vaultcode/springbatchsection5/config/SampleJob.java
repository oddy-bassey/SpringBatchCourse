package com.vaultcode.springbatchsection5.config;

import com.vaultcode.springbatchsection5.listener.JobListener;
import com.vaultcode.springbatchsection5.listener.StepListener;
import com.vaultcode.springbatchsection5.service.SecondTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SampleJob {

    private final SecondTasklet secondTasklet;
    private final JobListener jobListener;
    private final StepListener stepListener;

    @Bean
    public Job secondJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("First Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .build();
    }

    private  Step secondStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
         return new StepBuilder("Second Step", jobRepository)
                 .tasklet(secondTasklet, platformTransactionManager)
                 .listener(stepListener)
                 .build();
    }
}
