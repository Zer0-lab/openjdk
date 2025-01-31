package entities.menu.actions;

import java.util.List;
import java.util.Scanner;

import abstracts.menu.AbstractAction;
import dao.cardDAO;
import entities.Card;

public class AllDoneCardMenu extends AbstractAction {

    public AllDoneCardMenu(Scanner scanner, cardDAO cardDao) {
            super(scanner, cardDao);
        }
    
        @Override
    public void execute() {
        System.out.println("Liste des todos :");

        try {
            List<Card> cards = cardDao.findAllNotDone();

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
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
