package entities.menu;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import config.DatabaseConfig;
import dao.cardDAO;
import entities.menu.actions.AddCardMenu;
import entities.menu.actions.AllCardMenu;
import entities.menu.actions.AllDoneCardMenu;
import entities.menu.actions.DeleteCardMenu;
import entities.menu.actions.ExitMenu;
import entities.menu.actions.UpdateCardMenu;
import interfaces.factories.menu.Action;

public class Menu {
    private final Map<Integer, Action> actions;
    private final Scanner scanner;
    private final DatabaseConfig databaseConfig;
    private final cardDAO cardDao;
    private final Connection connection;

    public Menu() throws Exception {
        this.scanner = new Scanner(System.in);
        this.databaseConfig = new DatabaseConfig();
        this.connection = databaseConfig.getConnection();
        this.cardDao = new cardDAO(connection);
        this.actions = new HashMap<>();

        actions.put(1, new AllCardMenu(scanner, cardDao));
        actions.put(2, new AllDoneCardMenu(scanner, cardDao));
        actions.put(3, new AddCardMenu(scanner, cardDao)); 
        actions.put(4, new UpdateCardMenu(scanner, cardDao)); 
        actions.put(5, new DeleteCardMenu(scanner, cardDao)); 
        actions.put(6, new ExitMenu());
    }

    public void display(){
        int choice;
        do {
            System.out.println("📝 Menu");
            System.out.println("1. Afficher toutes les cartes");
            System.out.println("2. Afficher les cartes terminées");
            System.out.println("3. Ajouter une todo");
            System.out.println("4. Mettre à jour une todo");
            System.out.println("5. Supprimer une todo");
            System.out.println("6. Quitter");
            System.out.print("Votre choix : ");

            choice = getUserChoice();

            if (actions.containsKey(choice)) {
                actions.get(choice).execute();
            } else {
                System.out.println("Choix invalide");
            }
        } while (choice != 6);
        closeRessources();
       
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("⚠️ Veuillez entrer un nombre valide !");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private void closeRessources(){
        System.out.println("Fermeture de la connexion à la base de données...");
        scanner.close();
        databaseConfig.closeConnection();
    }
    
}
