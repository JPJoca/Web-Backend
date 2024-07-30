package rs.raf.projekat.repositories.articles;

import rs.raf.projekat.entities.Article;
import rs.raf.projekat.entities.Destination;
import rs.raf.projekat.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySqlArticleRepository extends MySqlAbstractRepository implements ArticleRepository {
    @Override
    public List<Article> getAllArticle() {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("SELECT * FROM articles ORDER BY published DESC");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                articles.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("published"),
                        resultSet.getInt("author_id"),
                        resultSet.getInt("number_of_visits"),
                        resultSet.getInt("destination_id")
                        ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return articles;
    }
    @Override
    public List<Article> getMostViewdArticle(Timestamp date) {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());

        // Oduzmite jedan mesec
        calendar.add(Calendar.MONTH, -1);

        // Konvertujte ponovo u Timestamp
        Timestamp previousMonthTimestamp = new Timestamp(calendar.getTimeInMillis());
        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("SELECT * FROM articles WHERE published > ? ORDER BY number_of_visits DESC");
            statement.setTimestamp(1, previousMonthTimestamp);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                articles.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("published"),
                        resultSet.getInt("author_id"),
                        resultSet.getInt("number_of_visits"),
                        resultSet.getInt("destination_id")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return articles;
    }
    @Override
    public List<Article> getAllDestinationArticle(Integer destinationId) {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("SELECT * FROM articles WHERE destination_id = ? ORDER BY published DESC");
            statement.setInt(1, destinationId);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                articles.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("published"),
                        resultSet.getInt("author_id"),
                        resultSet.getInt("number_of_visits"),
                        resultSet.getInt("destination_id")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return articles;
    }

    @Override
    public Article getArticleById(int id) {
        System.out.println(id);
        Article article = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("SELECT * FROM articles WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                article = new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("text"),
                        resultSet.getTimestamp("published"),
                        resultSet.getInt("author_id"),
                        resultSet.getInt("number_of_visits"),
                        resultSet.getInt("destination_id")
                );
            }

        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return article;
    }


    @Override
    public Article createArticle(Article article) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            statement = connection.prepareStatement("INSERT INTO articles (title, text, published, number_of_visits, author_id, destination_id) VALUES (?, ?, ?, ?, ?, ?)",generatedColumns);
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getText());
            statement.setTimestamp(3, article.getPublished());
            statement.setInt(4, article.getNumberOfVisits());
            statement.setInt(6, article.getDestinationId());
            statement.setInt(5, article.getAuthorId());

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                article.setId(resultSet.getInt(1));
            }

        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return article;
    }

    @Override
    public Article updateArticle(Article article, Integer id) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("UPDATE articles SET title = ?, text = ?, published = ?,number_of_visits = ?, author_id = ?, destination_id = ? WHERE id = ?");
            statement.setString(1, article.getTitle());
            statement.setString(2, article.getText());
            statement.setTimestamp(3, article.getPublished());
            statement.setInt(4, article.getNumberOfVisits());
            statement.setInt(5, article.getAuthorId());
            statement.setInt(6, article.getDestinationId());
            statement.setInt(7, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating destination failed, no rows affected.");
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeConnection(connection);
        }
        return article;
    }

    @Override
    public void deleteArticle(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("DELETE FROM articles WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();
            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeConnection(connection);
        }
    }
}
