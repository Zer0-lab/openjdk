import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import config.DatabaseConfig;
import dao.cardDAO;
import entities.Card;
import entities.menu.Menu;
import enums.Status;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            DatabaseConfig connectionDB = new DatabaseConfig();
            cardDAO cardDao = new cardDAO(connectionDB.getConnection());

             Menu menu = new Menu(scanner,connectionDB, cardDao);
             menu.display();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}