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
        System.out.println("Ajouter une nouvelle carte à la todo");
        System.out.println("Ajouter un titre à la todo: ");
        String title = scanner.nextLine();

        System.out.println("Choisseez un status à cette todo (TODO, DOING, DONE)");
        String statusStr = scanner.nextLine();
        Status status = Status.valueOf(statusStr.toUpperCase());

        Card newCard = new Card(Optional.empty(), title, status, false);

        try {
            cardDao.save(newCard);
            System.out.println("Carte ajoutée avec succès !");

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());

        }
    }
}