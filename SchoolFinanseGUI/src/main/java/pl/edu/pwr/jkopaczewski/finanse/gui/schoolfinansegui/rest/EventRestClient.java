package pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto.EventDto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EventRestClient {
    private static final String GET_EVENTS_URL = "http://localhost:8080/events";
    private static final String POST_EVENTS_URL = "http://localhost:8080/events";
    private final RestTemplate restTemplate;

    public EventRestClient() {
        restTemplate = new RestTemplate();
    }

    public List<EventDto> getEvents() {
        ResponseEntity<EventDto[]> events = restTemplate.getForEntity(GET_EVENTS_URL, EventDto[].class);
        return Arrays.asList(Objects.requireNonNull(events.getBody()));
    }

    public void addEvent(EventDto eventDto){
        restTemplate.postForObject(POST_EVENTS_URL, eventDto, EventDto.class);
    }
}
