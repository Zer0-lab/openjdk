package entities.menu.actions;

import java.util.List;
import java.util.Scanner;

import abstracts.menu.AbstractAction;
import entities.Card;

import dao.cardDAO;

public class AllCardMenu extends AbstractAction {

    public AllCardMenu(Scanner scanner, cardDAO cardDao) {
        super(scanner, cardDao);
    }

    @Override
    public void execute() {
        System.out.println("Liste de toutes les todos :");

        try {
            List<Card> cards = cardDao.findAll();

            if (cards.isEmpty()) {
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
