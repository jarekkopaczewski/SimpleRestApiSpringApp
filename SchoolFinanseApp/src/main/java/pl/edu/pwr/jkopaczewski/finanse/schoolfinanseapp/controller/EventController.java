package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.dto.EventDto;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Event;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service.EventService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EventController {
    @Autowired
    EventService eventService;

    @PostMapping("/events")
    public Event addEvent(@RequestBody Event event) { return eventService.addEvent(event); }

    /**
     * zamiana listy na strumień, a następnie zamiana Event na EventDto za pomocą map
     * @return
     */
    @GetMapping("/events")
    public List<EventDto> findAllEvents() {
        return eventService.findAllEvents()
                .stream()
                .map(EventDto::of)
                .collect(Collectors.toList());
    }

    @PutMapping("/events/{id}")
    public Event updateEvent(@RequestBody Event newEvent, @PathVariable Long id) { return eventService.updateEvent(newEvent, id); }

    @DeleteMapping("/events")
    public ResponseEntity deleteEvent(@RequestBody int eventId) { return eventService.deleteEvent(eventId); }
}
