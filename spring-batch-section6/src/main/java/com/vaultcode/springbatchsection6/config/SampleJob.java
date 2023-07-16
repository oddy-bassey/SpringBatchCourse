package com.vaultcode.springbatchsection6.config;

import com.vaultcode.springbatchsection6.listener.JobListener;
import com.vaultcode.springbatchsection6.listener.StepListener;
import com.vaultcode.springbatchsection6.processor.DataItemProcessor;
import com.vaultcode.springbatchsection6.reader.DataItemReader;
import com.vaultcode.springbatchsection6.service.FirstTasklet;
import com.vaultcode.springbatchsection6.writer.DataItemWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class SampleJob {

    private final FirstTasklet firstTasklet;
    private final JobListener jobListener;
    private final StepListener stepListener;

    private final DataItemReader itemReader;
    private final DataItemProcessor itemProcessor;
    private final DataItemWriter itemWriter;

    @Qualifier("chunkJob")
    @Bean
    public Job chunkJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("First Chunk Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                //.listener(jobListener)
                .start(chunkStep(jobRepository, platformTransactionManager))
                .build();
    }

    private  Step chunkStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
         return new StepBuilder("First Chunk Step", jobRepository)
                 .<Integer, Integer>chunk(4, platformTransactionManager)
                 .reader(itemReader)
                 //.processor(itemProcessor)
                 .writer(itemWriter)
                 //.listener(stepListener)
                 .build();
    }

    @Qualifier("taskletJob")
    @Bean
    public Job taskletJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("First Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .start(taskletStep(jobRepository, platformTransactionManager))
                .build();
    }
    private  Step taskletStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("Second Step (tasklet)", jobRepository)
                .tasklet(firstTasklet, platformTransactionManager)
                .build();
    }
}
