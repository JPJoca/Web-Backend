package rs.raf.projekat.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Article {

    private Integer id;
    private String title;
    private String text;
    private Timestamp published;
    private Integer authorId;
    private int numberOfVisits;
    private Integer destinationId;

    public Article(Integer id, String title, String text, Timestamp published, Integer authorId, int numberOfVisits, Integer destinationId) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.published = published;
        this.authorId = authorId;
        this.numberOfVisits = numberOfVisits;
        this.destinationId = destinationId;
    }

    public Article() {

    }
}
