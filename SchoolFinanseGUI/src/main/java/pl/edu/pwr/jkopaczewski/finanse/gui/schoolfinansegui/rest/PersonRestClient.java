package pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto.PersonDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PersonRestClient {
    private static final String GET_PEOPLE_URL = "http://localhost:8080/person";
    private static final String POST_PEOPLE_URL = "http://localhost:8080/person";
    private final RestTemplate restTemplate;

    public PersonRestClient() {
        restTemplate = new RestTemplate();
    }

    public List<PersonDto> getPeople() {
        ResponseEntity<PersonDto[]> people = restTemplate.getForEntity(GET_PEOPLE_URL, PersonDto[].class);
        return Arrays.asList(Objects.requireNonNull(people.getBody()));
    }

    public void addPerson(PersonDto personDto){
        restTemplate.postForObject(POST_PEOPLE_URL, personDto, PersonDto.class);
    }
}
