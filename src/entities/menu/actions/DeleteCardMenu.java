package entities.menu.actions;

import java.util.Scanner;

import abstracts.menu.AbstractAction;
import config.DatabaseConfig;
import dao.cardDAO;
import interfaces.factories.menu.Action;

public class DeleteCardMenu extends AbstractAction {

    public DeleteCardMenu(Scanner scanner, cardDAO cardDao) {
       super(scanner, cardDao);
    }

    @Override
    public void execute() {
        System.out.println("Suppression d'une carte..");

        System.out.println("Entrez l'ID de la carte à supprimer : ");
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un ID valide : ");
            scanner.next();
        }
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            System.out.println("êtes-vous sûr de vouloir supprimer la carte ? (O/N)");
            String response = scanner.nextLine();

            if (!response.equalsIgnoreCase("O")) {
                System.out.println("Suppression annulée.");
                return;
            } else {
                boolean result = cardDao.delete(id);
                if (result) {

                    System.out.println("Carte supprimée avec succès !");
                } else {
                    System.out.println("Carte non trouvée.");
                }
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
