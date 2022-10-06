package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try (Connection connection = Util.getSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS user" +
                     "(id INT primary key auto_increment," + "name VARCHAR(15)," +
                     "lastname VARCHAR(15)," +
                     "age TINYINT)");
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("drop table IF EXISTS user")
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into user" +
                     "(name, lastName, age)" +
                     "values (?, ?, ?)");

        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        try (Connection connection = Util.getSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ?");
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

       List <User> users = new ArrayList<>();

       try (Connection connection = Util.getSQLConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from user");
            ResultSet resultSet = preparedStatement.executeQuery();
       ) {
           while (resultSet.next()) {
               User user = new User();
               user.setId(resultSet.getLong("id"));
               user.setName(resultSet.getString("name"));
               user.setLastName(resultSet.getString("lastname"));
               user.setAge(resultSet.getByte("age"));
               users.add(user);
           }
       }
       catch (SQLException e) {
           e.printStackTrace();
       }
       return users;
}
    public void cleanUsersTable() {
        try (Connection connection = Util.getSQLConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("truncate table user")
        ) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}