package rs.raf.projekat.repositories.articleactivity;

import rs.raf.projekat.entities.Activity;
import rs.raf.projekat.entities.Article;
import rs.raf.projekat.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlArticleActivityRepository extends MySqlAbstractRepository implements ArticleActivityRepository {
    @Override
    public void addArticleActivity(Integer article_id, Integer activity_id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try{
            connection = this.newConnection();

            //String[] generatedColumns = {"id"};
            statement = connection.prepareStatement("INSERT INTO articleactivity (article_id, activity_id) VALUES (?, ?)");
            statement.setInt(1,article_id);
            statement.setInt(2,activity_id);

            statement.executeUpdate();
            //resultSet = statement.getGeneratedKeys();

        }  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeConnection(connection);
        }
    }

    @Override
    public void deleteArticleActivity(Integer article_id, Integer activity_id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("DELETE FROM articleactivity where article_id = ? and activity_id = ?");
            statement.setInt(1, article_id);
            statement.setInt(2, activity_id);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeConnection(connection);
        }

    }

    @Override
    public void deleteAllActivitiesForArticle(Integer article_id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = this.newConnection();
            statement = connection.prepareStatement("DELETE FROM articleactivity WHERE article_id = ?");
            statement.setInt(1, article_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeConnection(connection);
        }
    }

    @Override
    public List<Activity> getActivitiesForArticle(Integer article_id) {
        List<Activity> activities = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            statement = connection.prepareStatement(
                    "SELECT a.* FROM activitys a JOIN articleactivity aa ON a.id = aa.activity_id WHERE aa.article_id = ?");
            statement.setInt(1, article_id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Activity activity = new Activity();
                activity.setId(resultSet.getInt("id"));
                activity.setName(resultSet.getString("name"));
                activity.setDescription(resultSet.getString("description"));
                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(statement);
            this.closeConnection(connection);
        }

        return activities;
    }

    @Override
    public List<Article> getArticlesForActivity(Integer activity_id) {
        List<Article> articles = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            statement = connection.prepareStatement(
                    "SELECT a.* FROM articles a JOIN articleactivity aa ON a.id = aa.article_id WHERE aa.activity_id = ?");
            statement.setInt(1, activity_id);
            resultSet = statement.executeQuery();


            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setText(resultSet.getString("text"));
                article.setPublished(resultSet.getTimestamp("published"));
                article.setNumberOfVisits(resultSet.getInt("number_of_visits"));
                article.setDestinationId(resultSet.getInt("author_id"));
                article.setDestinationId(resultSet.getInt("destination_id"));
                articles.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResultSet(resultSet);
            this.closeStatement(statement);
            this.closeConnection(connection);
        }

        return articles;
    }

}
