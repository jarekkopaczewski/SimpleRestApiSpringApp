package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Person;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.repository.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person addPerson(@RequestBody Person person){ return personRepository.save(person); }

    public List<Person> getPeople() { return personRepository.findAll(); }

    public Person updatePerson(@RequestBody Person newPerson, @PathVariable Long id) {
        return personRepository.findById(Math.toIntExact(id))
                .map(event -> {
                    event.setName(newPerson.getName());
                    event.setSurname(newPerson.getSurname());
                    return personRepository.save(event);
                })
                .orElseGet(() -> {
                    newPerson.setPersonId(id);
                    return personRepository.save(newPerson);
                });
    }

    public ResponseEntity deletePerson(@RequestBody int personId) {
        personRepository.deleteById(personId);
        return ResponseEntity.ok().build();
    }
}
