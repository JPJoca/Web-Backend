package rs.raf.projekat.services;

import rs.raf.projekat.entities.Comment;
import rs.raf.projekat.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentServices {

    @Inject
    private CommentRepository commentRepository;

    public List<Comment> allCommentsForArticle(int articleId){
        return commentRepository.allCommentsForArticle(articleId);
    }

    public Comment findArticleById(int id){
        return commentRepository.findArticleById(id);
    }

    public Comment addComment(Comment comment){
        return commentRepository.addComment(comment);
    }

    public void deleteComment(int id){
        commentRepository.deleteComment(id);
    }

    public void deleteCommentsForArticle(int articleId){
        commentRepository.deleteCommentsForArticle(articleId);
    }
}
