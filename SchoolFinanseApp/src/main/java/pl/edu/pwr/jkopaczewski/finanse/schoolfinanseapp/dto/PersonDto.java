package pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.dto;

import lombok.Data;
import pl.edu.pwr.jkopaczewski.finanse.schoolfinanseapp.model.Person;

@Data
public class PersonDto {
    private long personId;
    private String name;
    private String surname;

    public static PersonDto of(Person person){
        PersonDto dto = new PersonDto();
        dto.setPersonId(person.getPersonId());
        dto.setName(person.getName());
        dto.setSurname(person.getSurname());
        return dto;
    }
}
