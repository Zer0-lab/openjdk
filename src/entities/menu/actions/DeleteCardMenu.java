package entities.menu.actions;

import java.util.Scanner;

import config.DatabaseConfig;
import dao.cardDAO;
import interfaces.factories.menu.Action;

public class DeleteCardMenu implements Action {
    private DatabaseConfig connectionDB = new DatabaseConfig();
    private cardDAO cardDao;
    private Scanner scanner = new Scanner(System.in);

    public DeleteCardMenu() {
        try {
            this.cardDao = new cardDAO(connectionDB.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            boolean result = cardDao.delete(id);
            if(result){
                System.out.println("Carte supprimée avec succès !");
            } else {
                System.out.println("Carte non trouvée.");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }   
}
