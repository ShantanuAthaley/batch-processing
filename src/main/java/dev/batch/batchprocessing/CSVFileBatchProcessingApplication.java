package dev.batch.batchprocessing;

import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class CSVFileBatchProcessingApplication {
	private JdbcTemplate jdbcTemplate;

	public CSVFileBatchProcessingApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(CSVFileBatchProcessingApplication.class, args);
	}

	@PostConstruct
	public void initializeSchema() {
		final String createPeopleTableIfNotExists = "CREATE TABLE IF NOT EXISTS `people`  (\n" +
			"    person_id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
			"    first_name VARCHAR(20),\n" +
			"    last_name VARCHAR(20),\n" +
			"    age INT NOT NULL\n" +
			");";
		jdbcTemplate.execute(createPeopleTableIfNotExists);
	}


}

