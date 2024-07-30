package rs.raf.projekat.entities;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Comment {

    private Integer id;
    private Integer articleId;
    private String author;
    private String text;
    private Timestamp date;

    public Comment(Integer id, Integer articleId, String author, String text, Timestamp date) {
        this.id = id;
        this.articleId = articleId;
        this.author = author;
        this.text = text;
        this.date = date;
    }

    public Comment() {
    }
}
