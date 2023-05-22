package jm.task.core.jdbc.util;

import java.net.URL;
import java.sql.*;

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


}
