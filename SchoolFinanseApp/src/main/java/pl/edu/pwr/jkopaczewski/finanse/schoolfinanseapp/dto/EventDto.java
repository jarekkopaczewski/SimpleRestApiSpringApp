package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.dto;

import lombok.Data;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Event;

@Data
public class EventDto {
    private long eventId;
    private String name;
    private String place;
    private String date;

    public static EventDto of(Event event){
        EventDto dto = new EventDto();
        dto.setDate(event.getDate());
        dto.setEventId(event.getEventId());
        dto.setPlace(event.getPlace());
        dto.setName(event.getName());
        return dto;
    }
}
