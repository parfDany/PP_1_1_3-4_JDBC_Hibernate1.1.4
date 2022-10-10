package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                "(id INT primary key auto_increment," + "name VARCHAR(45)," +
                "lastname VARCHAR(45)," +
                "age TINYINT)");
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        User user = new User(name, lastName, age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }


    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

    }


    @Override
    public List<User> getAllUsers() {
        return (List<User>) sessionFactory.openSession().createQuery("SELECT u FROM User u", User.class).list();
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("truncate table users");
        query.executeUpdate();
        session.getTransaction().commit();
    }
}



