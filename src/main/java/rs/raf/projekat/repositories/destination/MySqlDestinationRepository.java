package rs.raf.projekat.repositories.destination;

import rs.raf.projekat.entities.Destination;
import rs.raf.projekat.repositories.MySqlAbstractRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlDestinationRepository extends MySqlAbstractRepository implements DestinationRepository {
    @Override
    public List<Destination> getAllDestinations() {
        List<Destination> destinations = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("SELECT * FROM destinations");
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                destinations.add(new Destination(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                ));
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return destinations;
    }

    @Override
    public Destination getDestinationById(int id) {
        Destination destination = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("SELECT * FROM destinations WHERE id = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                destination = new Destination(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
            }

        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return destination;
    }

    @Override
    public Destination createDestination(Destination destination) {


        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            statement = connection.prepareStatement("INSERT INTO destinations (name, description) VALUES (?, ?)",generatedColumns);
            statement.setString(1, destination.getName());
            statement.setString(2, destination.getDescription());

            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();

            if(resultSet.next()){
                destination.setId(resultSet.getInt(1));
            }

        } catch(Exception e){
            e.printStackTrace();
        }finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return destination;
    }

    @Override
    public Destination updateDestination(Destination destination, Integer id) {


        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

            statement = connection.prepareStatement("UPDATE destinations SET name = ?, description = ? WHERE id = ?");
            statement.setString(1, destination.getName());
            statement.setString(2, destination.getDescription());
            statement.setInt(3, id);

            int affectedRows = statement.executeUpdate();
            System.out.println(affectedRows);
            System.out.println(destination);
            if (affectedRows == 0) {
                throw new SQLException("Updating destination failed, no rows affected.");
            }
        } catch(Exception e){
            System.out.println("AAAAAAAAAAAAAAAAAAa");
            e.printStackTrace();
        }finally {
            System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBb");
            this.closeStatement(statement);
            this.closeConnection(connection);
        }
        System.out.println(destination.getName());
        return destination;
    }

    @Override
    public void deleteDestination(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = this.newConnection();

           statement = connection.prepareStatement("DELETE FROM destinations WHERE id = ?");
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
