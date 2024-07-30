package rs.raf.projekat.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Activity {
    private int id;
    private String name;
    private String description;

    public Activity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Activity() {
    }
}
