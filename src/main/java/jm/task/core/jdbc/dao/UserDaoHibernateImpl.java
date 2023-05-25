package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    private static final SessionFactory sessionfactory = Util.getConnectionHibernate();



    @Override
    public void createUsersTable() {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS User" +
                " (id mediumint not null auto_increment, name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age tinyint, " +
                "PRIMARY KEY (id))").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.createSQLQuery("DROP TABLE IF EXISTS User").executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.save(new User(name,lastName,age));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }


    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.delete(session.get(User.class,id));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.getTransaction();
        List<User> userList = new ArrayList<>();
        try {
            transaction.begin();
            userList = session.createQuery("from User").list();
            transaction.commit();
            return userList;

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionfactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
