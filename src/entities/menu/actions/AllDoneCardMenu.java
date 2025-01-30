package entities.menu.actions;

import java.util.List;
import java.util.Scanner;

import config.DatabaseConfig;
import dao.cardDAO;
import entities.Card;
import interfaces.factories.menu.Action;

public class AllDoneCardMenu implements Action {
    private DatabaseConfig connectionDB = new DatabaseConfig();
    private cardDAO cardDao;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void execute() {
        System.out.println("Liste des todos :");

        try {
            List<Card> cards = new cardDAO(connectionDB.getConnection()).findAllNotDone();

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
