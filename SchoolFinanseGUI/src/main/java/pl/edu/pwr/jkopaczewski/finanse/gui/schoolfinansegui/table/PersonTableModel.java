package pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.table;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.jkopaczewski.finanse.gui.schoolfinansegui.dto.PersonDto;

@Getter
@Setter
public class PersonTableModel {
    private Long personId;
    private String name;
    private String surname;


    public PersonTableModel(Long personId, String name, String surname) {
        this.personId = personId;
        this.name = name;
        this.surname = surname;
    }

    public static PersonTableModel of(PersonDto dto){
        return new PersonTableModel(dto.getPersonId(), dto.getName(), dto.getSurname());
    }
}
