package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String URL = "jdbc:mysql://localhost:3306/MyDataBase";
    private final static String USERNAME = "root";
    private final static String PASSWORD= "admin";

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось подключиться");
        }
        return connection;
    }

    public static Session getConnectionHibernate() {
        Properties propertiesHibernate= new Properties();
        propertiesHibernate.setProperty("hibernate.driver_class","org.mysql.Driver");
        propertiesHibernate.setProperty("hibernate.connection.url",URL);
        propertiesHibernate.setProperty("hibernate.connection.username",USERNAME);
        propertiesHibernate.setProperty("hibernate.connection.password",PASSWORD);
        propertiesHibernate.setProperty("show_sql","true");
        propertiesHibernate.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        propertiesHibernate.setProperty("hibernate.current_session_context_class", "thread");
        Configuration configuration = new Configuration().setProperties(propertiesHibernate).addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }




}
