package dev.batch.batchprocessing;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CSVFileBatchProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CSVFileBatchProcessingApplication.class, args);
	}
}


