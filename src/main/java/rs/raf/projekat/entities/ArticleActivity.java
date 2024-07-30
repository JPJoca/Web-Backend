package rs.raf.projekat.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleActivity {

    private Integer articleId;
    private Integer activityId;

    public ArticleActivity(Integer articleId, Integer activityId) {
        this.articleId = articleId;
        this.activityId = activityId;
    }

    public ArticleActivity() {
    }
}
