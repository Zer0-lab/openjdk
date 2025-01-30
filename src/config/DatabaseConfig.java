package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:mysql://database:3306/app";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private Connection connection;

    public  Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion à la base de données réussie !");
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée !");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
