package entities.menu.actions;

import enums.Status;
import java.util.Optional;
import java.util.Scanner;

import config.DatabaseConfig;
import dao.cardDAO;
import entities.Card;
import interfaces.factories.menu.Action;

public class AddCardMenu implements Action {
    private DatabaseConfig connectionDB = new DatabaseConfig();
    private Scanner scanner = new Scanner(System.in);
    private cardDAO cardDao;

    public AddCardMenu() {
        try {
            this.cardDao = new cardDAO(connectionDB.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void execute() {
        System.out.println("Ajouter une nouvelle carte");
        System.out.println("Titre");
        String titel = scanner.nextLine();

        System.out.println("Status (TODO, DOING, DONE)");
        String statusStr = scanner.nextLine();
        Status status = Status.valueOf(statusStr.toUpperCase());

        Card newCard = new Card(Optional.empty(), titel, status, false);

         try {
            if (cardDao.save(newCard)) {
                System.out.println("✅ Carte ajoutée avec succès !");
            } else {
                System.out.println("❌ Échec de l'ajout.");
            }
        } catch (Exception e) {
            System.out.println("❌ Erreur : " + e.getMessage());
        }

    }
}