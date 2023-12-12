package workflow.service.impl;

import workflow.entity.Person;

import java.util.List;

public interface PersonDAO {

    Person getPersonById(Long id);

    List<Person> getAllPersons();

    boolean deletePerson(Person person);

    boolean updatePerson(Person person);

    boolean createPerson(Person person);
}
