package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService  {

    private UserDao userDaoImplements = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDaoImplements.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoImplements.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {

        userDaoImplements.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoImplements.removeUserById(id);

    }

    public List<User> getAllUsers() {

        return userDaoImplements.getAllUsers();
    }

    public void cleanUsersTable() {

        userDaoImplements.cleanUsersTable();
    }
}
