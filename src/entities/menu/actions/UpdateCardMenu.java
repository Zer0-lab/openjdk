package entities.menu.actions;

import enums.Status;
import java.util.Scanner;

import javax.management.RuntimeErrorException;
import entities.Card;

import config.DatabaseConfig;
import dao.cardDAO;
import interfaces.factories.menu.Action;

public class UpdateCardMenu implements Action {

    private DatabaseConfig connectionDB = new DatabaseConfig();
    private cardDAO cardDao;
    private Scanner scanner = new Scanner(System.in);

    public UpdateCardMenu() {
        try {
            this.cardDao = new cardDAO(connectionDB.getConnection());
        } catch (Exception e) {
            throw new RuntimeException("Erreur de connexion à la base de données", e);
        }
    }

    @Override
    public void execute() {
        System.out.println("Modifier une todo : ");
        System.out.println("Entrez l'id de la todo à modifier : ");

        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un ID valide : ");
            scanner.next();
        }

        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Card existinCard = cardDao.findById(id);
            
            System.out.println("Entrez un nouveau titre pour la todo : (actuel : " + existinCard.getTitle() + ")");
            String title = scanner.nextLine();

            if(!title.isEmpty()){
                existinCard.setTitle(title);
            }

            System.out.println("Entrez un nouveau status pour la todo (TODO, DOING, DONE): (actuel : " + existinCard.getStatus() + ")");
            String statusStr = scanner.nextLine();
            Status status = statusStr.isEmpty() ? existinCard.getStatus() : Status.valueOf(statusStr.toUpperCase());

            Card updatedCard = new Card(existinCard.getId(), existinCard.getTitle(), status, existinCard.getIs_done());

            boolean result = cardDao.save(updatedCard);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
    
}
