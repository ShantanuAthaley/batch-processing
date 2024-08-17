package dev.batch.batchprocessing;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ReaderWriterConfiguration {
  /*must be classpath/resources folder; can be externalized to application.properties */
  final String FILE_NAME = "csv-data.csv";

  /**
   * Reader Bean
   *
   * @return FlatFileItemReader
   */
  @Bean
  FlatFileItemReader<Person> personReader() {
    return new FlatFileItemReaderBuilder<Person>()
      .name("personItemReader")
      .resource(new ClassPathResource(FILE_NAME))
      .delimited()
      .names("name", "surname", "age")
      //.lineMapper(personLineMapper())
      .fieldSetMapper(personFieldSetMapper())
      .targetType(Person.class)
      .build();
  }


  /**
   * Processor
   *
   * @return PersonProcessor
   */
  @Bean
  PersonProcessor processor() {
    return new PersonProcessor();
  }


  /**
   * Writer
   *
   * @param dataSource
   * @return
   */
  @Bean
  public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<Person>()
      .sql("INSERT INTO people (first_name, last_name, age) VALUES (:name, :surName, :age)")
      .dataSource(dataSource)
      .beanMapped()
      .build();
  }

  /**
   * Various ways to map CSV file to Person object
   * LineMapper and FieldSetMapper choose which one to use
   *
   * @return LineMapper
   */

  @Bean
  LineMapper<Person> personLineMapper() {
    return (line, lineNumber) -> {
      String[] fields = line.split(",");
      Person person = new Person();
      person.setName(fields[0]);
      person.setSurname(fields[1]);
      person.setAge(Integer.parseInt(fields[2]));
      return person;
    };
  }

  /**
   * Various ways to map CSV file to Person object
   * LineMapper and FieldSetMapper choose which one to use
   *
   * @return FieldSetMapper
   */
  @Bean
  FieldSetMapper<Person> personFieldSetMapper() {
    return (fieldSet) -> {
      Person person = new Person();
      if (fieldSet.hasNames()) {
        person.setName(fieldSet.readString("name"));
        person.setSurname(fieldSet.readString("surname"));
        person.setAge(fieldSet.readInt("age"));
      } else {
        person.setName(fieldSet.readString(0));
        person.setSurname(fieldSet.readString(1));
        person.setAge(fieldSet.readInt(2));
      }
      return person;
    };

  }
}
