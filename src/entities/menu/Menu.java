package entities.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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

    public Menu(){;
        actions = new HashMap<>();
        scanner = new Scanner(System.in);

        actions.put(1, new AllCardMenu());
        actions.put(2, new AllDoneCardMenu());
        actions.put(3, new AddCardMenu());
        actions.put(4, new UpdateCardMenu());
        actions.put(5, new DeleteCardMenu());
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

        scanner.close();
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("⚠️ Veuillez entrer un nombre valide !");
            scanner.next();
        }
        return scanner.nextInt();
    }
    
}
