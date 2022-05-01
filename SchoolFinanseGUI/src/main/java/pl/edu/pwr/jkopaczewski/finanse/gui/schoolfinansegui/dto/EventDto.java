package pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto;

import lombok.Data;

@Data
public class EventDto {
    private long eventId;
    private String name;
    private String place;
    private String date;
}
