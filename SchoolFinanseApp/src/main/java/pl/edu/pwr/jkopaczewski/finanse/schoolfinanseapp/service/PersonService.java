package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service;

import org.springframework.http.ResponseEntity;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Person;

import java.util.List;

public interface PersonService {
    Person addPerson(Person person);
    List<Person> getPeople();
    Person updatePerson(Person person, Long id);
    ResponseEntity deletePerson(int personId);
}
