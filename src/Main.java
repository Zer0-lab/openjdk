import java.util.Scanner;

import config.DatabaseConfig;
import dao.cardDAO;
import entities.menu.Menu;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            DatabaseConfig connectionDB = new DatabaseConfig();
            cardDAO cardDao = new cardDAO(connectionDB.getConnection());
            System.out.println("\n");

             Menu menu = new Menu(scanner,connectionDB, cardDao);
             menu.display();
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}