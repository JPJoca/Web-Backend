package rs.raf.projekat.repositories.comment;

import rs.raf.projekat.entities.Comment;

import java.util.List;

public interface CommentRepository {

    public List<Comment> allCommentsForArticle(int articleId);

    public Comment findArticleById(int id);

    public Comment addComment(Comment comment);

    public void deleteComment(int id);

    public void deleteCommentsForArticle(int articleId);
}
