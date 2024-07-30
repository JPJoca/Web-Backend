package rs.raf.projekat.services;

import rs.raf.projekat.entities.Article;
import rs.raf.projekat.entities.Destination;
import rs.raf.projekat.repositories.articles.ArticleRepository;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.List;

public class ArticleService {
    @Inject
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles(){
        return this.articleRepository.getAllArticle();
    }

    public List<Article> getMostViewdArticle(Timestamp date){
        return this.articleRepository.getMostViewdArticle( date);
    }

    public List<Article> getAllDestinationArticles(Integer id){
        return this.articleRepository.getAllDestinationArticle(id);
    }

    public Article getArticleById(int id){
        return this.articleRepository.getArticleById(id);
    }

    public Article createArticle(Article article){
        return this.articleRepository.createArticle(article);
    }

    public Article updateArticle(Article article , Integer id){
        return this.articleRepository.updateArticle(article, id);
    }

    public void deleteArticle(int id){
        this.articleRepository.deleteArticle(id);
    }
}
