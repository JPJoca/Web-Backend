package rs.raf.projekat.repositories.user;

import org.apache.commons.codec.digest.DigestUtils;
import rs.raf.projekat.entities.User;
import rs.raf.projekat.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserRepository extends MySqlAbstractRepository implements UserRepository {

    @Override
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("SELECT * FROM users ");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("user_type"),
                        resultSet.getString("status"),
                        resultSet.getString("password_hash")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return users;
    }

    @Override
    public User findUser(String email, String password) {
        User user = null;

        System.out.println(email);
        System.out.println(password);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ? AND password_hash = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("user_type"),
                        resultSet.getString("status"),
                        resultSet.getString("password_hash")
                );
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
//        finally {
//            this.closeStatement(preparedStatement);
//            this.closeResultSet(resultSet);
//            this.closeConnection(connection);
//        }

        return user;
    }

    @Override
    public User findUserById(int id) {
        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("user_type"),
                        resultSet.getString("status"),
                        resultSet.getString("password_hash")
                );
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User addUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            String[] generatedColumns = {"id"};
            preparedStatement = connection.prepareStatement("INSERT INTO users (email, first_name, last_name, user_type, status, password_hash) VALUES (?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getUserType());
            preparedStatement.setString(5, user.getStatus());
            preparedStatement.setString(6, DigestUtils.sha256Hex(user.getHashedPassword()));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                user.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User updateUserStatus(Integer id) {
        User user = null;

        user = this.findUserById(id);

        if(user.getStatus().equals("active")){
            user.setStatus("inactive");
        } else {
            user.setStatus("active");
        }

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("UPDATE users SET status = ? WHERE id = ?");
            statement.setString(1, user.getStatus());
            statement.setInt(2, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating destination failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeConnection(connection);
        }

        return user;
    }


    @Override
    public User updateUser(User user, Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = this.newConnection();

            statement = connection.prepareStatement("UPDATE users SET email = ?, first_name = ?, last_name = ?, user_type = ?, status = ?, password_hash = ? WHERE id = ?");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setString(4, user.getUserType());
            statement.setString(5, user.getStatus());
            statement.setString(6, user.getHashedPassword());
            statement.setInt(7, id);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating destination failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeConnection(connection);
        }

        return user;
    }
}
