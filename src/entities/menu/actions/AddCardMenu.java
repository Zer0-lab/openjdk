package entities.menu.actions;

import enums.Status;
import java.util.Optional;
import java.util.Scanner;

import abstracts.menu.AbstractAction;
import dao.cardDAO;
import entities.Card;

public class AddCardMenu extends AbstractAction {

    public AddCardMenu(Scanner scanner, cardDAO cardDao) {
        super(scanner, cardDao);
    }

    @Override
    public void execute() {
        System.out.println("Ajouter une nouvelle carte à la todo");

        String title;
        do {
            System.out.println("Ajouter un titre à la todo: ");
            title = scanner.nextLine();
            if (title.isEmpty()) {
                System.out.println("Le titre ne peut pas être vide");

            }
        } while (title.isEmpty());

        Status statusStr = isEnumValid(Status.class, "Choisissez un statut (TODO, DOING, DONE) :");
        Status status = statusStr;

        Card newCard = new Card(Optional.empty(), title, status, false);

        if (!isResponseValid("Voulez-vous ajouter cette carte ? ")) {
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