package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.dto.PaymentDto;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.dto.PersonDto;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Person;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @GetMapping("/person")
    public List<PersonDto> getPeople() {
        return personService.getPeople()
                .stream()
                .map(PersonDto::of)
                .collect(Collectors.toList());
    }

    @PutMapping("/person/{id}")
    public Person updatePerson(@RequestBody Person newPerson, @PathVariable Long id) { return personService.updatePerson(newPerson, id); }

    @DeleteMapping("/person")
    public ResponseEntity deletePerson(@RequestBody int personId) { return personService.deletePerson(personId); }
}
