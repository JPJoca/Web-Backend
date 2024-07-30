package rs.raf.projekat.services;

import rs.raf.projekat.entities.Activity;
import rs.raf.projekat.entities.Article;
import rs.raf.projekat.repositories.activity.ActivityRepository;
import rs.raf.projekat.repositories.articleactivity.ArticleActivityRepository;

import javax.inject.Inject;
import java.util.List;

public class ArticleActivityService {

    @Inject
    private ArticleActivityRepository articleActivityRepository;

    public void addArticleActivity(Integer article_id, Integer activity_id) {
        this.articleActivityRepository.addArticleActivity(article_id, activity_id);
    }

    public void deleteArticleActivity(Integer article_id, Integer activity_id) {
        this.articleActivityRepository.deleteArticleActivity(article_id, activity_id);
    }

    public void deleteAllActivitiesForArticle(Integer article_id) {
        this.articleActivityRepository.deleteAllActivitiesForArticle(article_id);
    }

    public List<Activity> getActivitiesForArticle(Integer articleId) {
        return this.articleActivityRepository.getActivitiesForArticle(articleId);
    }

    public List<Article> getArticlesForActivity(Integer activityId) {
        return this.articleActivityRepository.getArticlesForActivity(activityId);
    }
}
