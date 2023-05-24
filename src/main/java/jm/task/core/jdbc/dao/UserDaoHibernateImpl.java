package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getConnectionHibernate();
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS User" +
                " (id mediumint not null auto_increment, name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age tinyint, " +
                "PRIMARY KEY (id))").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getConnectionHibernate();
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getConnectionHibernate();
        session.beginTransaction();
        session.save(new User(name,lastName,age));
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getConnectionHibernate();
        session.beginTransaction();
        session.delete(session.get(User.class,id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Session session = Util.getConnectionHibernate();
        session.beginTransaction();
        userList = session.createQuery("from User").list();
        session.getTransaction();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getConnectionHibernate();
        session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
