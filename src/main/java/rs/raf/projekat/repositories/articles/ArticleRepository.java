package rs.raf.projekat.repositories.articles;

import rs.raf.projekat.entities.Article;
import rs.raf.projekat.entities.Destination;

import java.sql.Timestamp;
import java.util.List;

public interface ArticleRepository {
    public List<Article> getAllArticle();

    public List<Article> getMostViewdArticle(Timestamp date);

    public List<Article> getAllDestinationArticle(Integer destinationId);

    public Article getArticleById(int id);

    public Article createArticle(Article destination);

    public Article updateArticle(Article destination , Integer id);

    public void deleteArticle(int id);
}

