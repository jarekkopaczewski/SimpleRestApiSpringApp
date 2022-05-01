package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service;

import org.springframework.http.ResponseEntity;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Event;

import java.util.List;

public interface EventService {
     Event addEvent(Event event);
     List<Event> findAllEvents();
     Event updateEvent(Event event, Long id);
     ResponseEntity deleteEvent(int eventId);
}
