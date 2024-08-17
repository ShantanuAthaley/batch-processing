package dev.batch.batchprocessing;

import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;
import org.springframework.batch.item.file.mapping.FieldSetMapper;

/**
 * Not in use at the moment
 */
public class PersonFieldSetMapper implements FieldSetMapper<Person> {
  @Override
  public Person mapFieldSet(FieldSet fieldSet) throws BindException {
    return Person.builder().name(fieldSet.readString("name"))
      .surname(fieldSet.readString("surname"))
      .age(fieldSet.readInt("age"))
      .build();
  }
}
