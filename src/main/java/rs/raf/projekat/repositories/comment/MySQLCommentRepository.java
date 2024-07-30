package rs.raf.projekat.repositories.comment;


import rs.raf.projekat.entities.Comment;
import rs.raf.projekat.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQLCommentRepository extends MySqlAbstractRepository implements CommentRepository {
    @Override
    public List<Comment> allCommentsForArticle(int articleId) {
        List<Comment> comments = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE article_id = ? ORDER BY date");
            preparedStatement.setInt(1, articleId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                comments.add(new Comment(
                        resultSet.getInt("id"),
                        resultSet.getInt("article_id"),
                        resultSet.getString("author"),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("date")
                ));
            }

//            resultSet.close();
//            preparedStatement.close();
//            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }


        return comments;
    }

    @Override
    public Comment findArticleById(int id) {
        return null;
    }

    @Override
    public Comment addComment(Comment comment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
            connection = this.newConnection();

            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("INSERT INTO comments (article_id, author, text, date) VALUES (?, ?, ?, ?)", generatedColumns);
            preparedStatement.setInt(1, comment.getArticleId());
            preparedStatement.setString(2, comment.getAuthor());
            preparedStatement.setString(3, comment.getText());
            preparedStatement.setTimestamp(4, comment.getDate());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                comment.setId(resultSet.getInt(1));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return comment;
    }

    @Override
    public void deleteComment(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM comments WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }

    }

    @Override
    public void deleteCommentsForArticle(int articleId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM comments WHERE article_id = ?");
            preparedStatement.setInt(1, articleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
