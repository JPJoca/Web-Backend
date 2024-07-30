package rs.raf.projekat.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Destination {

    private Integer id;
    private String name;
    private String description;

    public Destination(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Destination() {
    }
}
