package entities.menu.actions;

import java.util.List;
import java.util.Scanner;

import entities.Card;

import config.DatabaseConfig;
import dao.cardDAO;
import interfaces.factories.menu.Action;

public class AllCardMenu implements Action {
    private DatabaseConfig connectionDB = new DatabaseConfig();
    private cardDAO cardDao;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("Liste de toutes les todos :");

        try {
            List<Card> cards = new cardDAO(connectionDB.getConnection()).findAll();

            if(cards.isEmpty()){
                System.out.println("Aucune carte trouvée.");
            } else {
                for (Card card : cards) {
                    System.out.println(card);
                }
                System.out.println("Fin de la liste.");
                System.out.println("Appuyez sur une touche pour continuer...");
                scanner.nextLine();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
