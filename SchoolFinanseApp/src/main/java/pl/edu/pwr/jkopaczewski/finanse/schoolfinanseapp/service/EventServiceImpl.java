package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Event;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.repository.EventRepository;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event addEvent(@RequestBody Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAllEvents() {
        return eventRepository.findAll();
    }

    public Event updateEvent(@RequestBody Event newEvent, @PathVariable Long id) {
        return eventRepository.findById(Math.toIntExact(id))
                .map(event -> {
                    event.setName(newEvent.getName());
                    event.setDate(newEvent.getDate());
                    event.setPlace(newEvent.getPlace());
                    return eventRepository.save(event);
                })
                .orElseGet(() -> {
                    newEvent.setEventId(id);
                    return eventRepository.save(newEvent);
                });
    }

    public ResponseEntity deleteEvent(@RequestBody int eventId) {
        eventRepository.deleteById(eventId);
        return ResponseEntity.ok().build();
    }
}
