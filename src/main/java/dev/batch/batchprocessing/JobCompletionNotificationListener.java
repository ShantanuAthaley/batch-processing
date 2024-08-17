package dev.batch.batchprocessing;

import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobCompletionNotificationListener  implements JobExecutionListener {

  private final JdbcTemplate jdbcTemplate;

  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void beforeJob(JobExecution jobExecution) {
   log.info("Job starting: {}", new Date());
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    log.info("Job started at {} ", jobExecution.getStartTime());
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
      log.info("Job completed successfully at {}", new Date());
    }
    jdbcTemplate.query("SELECT * FROM BATCH_JOB_EXECUTION",
      new DataClassRowMapper<>(Person.class)).forEach(person -> log.info("Found <{{}}> in the datbase.", person));
  }
}
