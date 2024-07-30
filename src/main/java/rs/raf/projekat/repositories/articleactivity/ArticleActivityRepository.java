package rs.raf.projekat.repositories.articleactivity;

import rs.raf.projekat.entities.Activity;
import rs.raf.projekat.entities.Article;

import java.util.List;

public interface ArticleActivityRepository {
    public void addArticleActivity(Integer article_id, Integer activity_id);

    public void deleteArticleActivity(Integer article_id, Integer activity_id);

    public void deleteAllActivitiesForArticle(Integer article_id);

    public List<Activity> getActivitiesForArticle(Integer article_id);

    public List<Article> getArticlesForActivity(Integer activity_id);

}
