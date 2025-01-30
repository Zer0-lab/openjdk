package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabbaseConfig {
    private static final String URL = "jdbc:mysql://database:3306/app";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    public static void getConnection() throws Exception {
        Connection connection = null;
        try {
             connection = DriverManager.getConnection(URL, USER, PASSWORD);
             System.out.println("Connecté à la da db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
