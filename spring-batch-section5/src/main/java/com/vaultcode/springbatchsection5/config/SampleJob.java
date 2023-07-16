package com.vaultcode.springbatchsection5.config;

import com.vaultcode.springbatchsection5.listener.JobListener;
import com.vaultcode.springbatchsection5.listener.StepListener;
import com.vaultcode.springbatchsection5.processor.DataItemProcessor;
import com.vaultcode.springbatchsection5.reader.DataItemReader;
import com.vaultcode.springbatchsection5.service.SecondTasklet;
import com.vaultcode.springbatchsection5.writer.DataItemWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
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

    private final DataItemReader itemReader;
    private final DataItemProcessor itemProcessor;
    private final DataItemWriter itemWriter;

    @Bean
    public Job firstChunkJob(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        return new JobBuilder("First Chunk Job", jobRepository)
                .incrementer(new RunIdIncrementer())
                //.listener(jobListener)
                .start(firstChunkStep(jobRepository, platformTransactionManager))
                .build();
    }

    private  Step firstChunkStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
         return new StepBuilder("First Chunk Step", jobRepository)
                 .<Integer, Long>chunk(3, platformTransactionManager)
                 .reader(itemReader)
                 .processor(itemProcessor)
                 .writer(itemWriter)
                 //.listener(stepListener)
                 .build();
    }
}
