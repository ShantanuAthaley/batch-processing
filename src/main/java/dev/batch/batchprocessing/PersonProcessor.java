package dev.batch.batchprocessing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class PersonProcessor implements ItemProcessor<Person, Person> {
  @Override
  public Person process(Person person) throws Exception {
    log.info("Processing person {}", person);
    final String firstName = person.getName().toUpperCase();
    final String lastName = person.getSurname().toUpperCase();
    Person transformedPerson = new Person(firstName, lastName, person.getAge());
    log.info("Transformed person {} to {}", person, transformedPerson);
    return transformedPerson;
  }
}
