package entities.menu.actions;

import enums.Status;
import java.util.Optional;
import java.util.Scanner;

import abstracts.menu.AbstractAction;
import dao.cardDAO;
import entities.Card;
import interfaces.factories.menu.Action;

public class AddCardMenu extends AbstractAction {

    public AddCardMenu(Scanner scanner, cardDAO cardDao) {
        super(scanner, cardDao);
    }

    @Override
    public void execute() {
        System.out.println("Ajouter une nouvelle carte à la todo");
        System.out.println("Ajouter un titre à la todo: ");
        String title = scanner.nextLine();

        System.out.println("Quelle sera le status à cette todo (TODO, DOING, DONE)");
        String statusStr = scanner.nextLine();
        Status status = Status.valueOf(statusStr.toUpperCase());
        Card newCard = new Card(Optional.empty(), title, status, false);

        System.out.println("Voulez-vous ajouter cette carte ? (O/N)");
        String response = scanner.nextLine();

        while(!response.equalsIgnoreCase("O") && !response.equalsIgnoreCase("N")){
            System.out.println("Veuillez entrer une réponse valide (O/N)");
            response = scanner.nextLine();
        }
        if (!response.equalsIgnoreCase("O")) {
            System.out.println("Ajout annulé.");
            return;
        } else {
            try {
                cardDao.save(newCard);
                System.out.println("Carte ajoutée avec succès !");

            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());

            }
        }
    }
}