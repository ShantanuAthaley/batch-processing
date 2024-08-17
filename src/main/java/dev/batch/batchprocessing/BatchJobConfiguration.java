package dev.batch.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchJobConfiguration {

  JobBuilderFactory jobBuilderFactory;
  StepBuilderFactory stepBuilderFactory;

  public BatchJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
    this.jobBuilderFactory = jobBuilderFactory;
    this.stepBuilderFactory = stepBuilderFactory;
  }

  @Bean
  Job importPersonJob(JobRepository jobRepository,
                      Step importPersonStep,
                      JobCompletionNotificationListener jobCompletionNotificationListener) {
    return jobBuilderFactory.get("csvToDbJob")
      .repository(jobRepository)
      .start(importPersonStep)
      .listener(jobCompletionNotificationListener)
      .build();
  }

  @Bean
  Step importPersonStep(ItemReader<Person> reader, ItemProcessor<Person, Person> processor, JdbcBatchItemWriter<Person> writer) {
    assert stepBuilderFactory != null;
    return stepBuilderFactory.get("csvToDbJob")
      .<Person, Person>chunk(10)
      .reader(reader)
      .processor(processor)
      .writer(writer)
      .allowStartIfComplete(true)
      .build();
  }
}

