package entities.menu.actions;

import enums.Status;
import java.util.Scanner;

import abstracts.menu.AbstractAction;
import entities.Card;

import dao.cardDAO;

public class UpdateCardMenu extends AbstractAction {

    public UpdateCardMenu(Scanner scanner, cardDAO cardDao) {
        super(scanner, cardDao);
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

            if (!title.isEmpty()) {
                existinCard.setTitle(title);
            }

            System.out.println("Entrez un nouveau status pour la todo (TODO, DOING, DONE): (actuel : "
                    + existinCard.getStatus() + ")");
            String statusStr = scanner.nextLine();
            Status status = statusStr.isEmpty() ? existinCard.getStatus() : Status.valueOf(statusStr.toUpperCase());

            Card updatedCard = new Card(existinCard.getId(), existinCard.getTitle(), status, existinCard.getIs_done());
            
            System.out.println("Voulez-vous modifier cette carte ? (O/N)");
            String response = scanner.nextLine();

            while(!response.equalsIgnoreCase("O") && !response.equalsIgnoreCase("N")){
                System.out.println("Veuillez entrer une réponse valide (O/N)");
                response = scanner.nextLine();
            }

            cardDao.save(updatedCard);
            System.out.println("Appuyez sur une touche pour continuer...");
            scanner.nextLine();

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

}
