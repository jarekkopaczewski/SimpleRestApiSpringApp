package pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.table;

import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto.EventDto;

@Getter
@Setter
public class EventTableModel {
    private final Long eventId;
    private final String name;
    private final String place;
    private final String date;

    public EventTableModel(Long eventId, String name, String place, String date) {
        this.eventId = eventId;
        this.name = name;
        this.place = place;
        this.date = date;
    }

    public static EventTableModel of(EventDto dto){
        return new EventTableModel(dto.getEventId(), dto.getName(), dto.getPlace(), dto.getDate());
    }
}
