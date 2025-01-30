package abstracts.menu;
import java.util.Scanner;
import dao.cardDAO;
import interfaces.factories.menu.Action;

public abstract class AbstractAction implements Action {
   protected final Scanner scanner;
   protected final cardDAO cardDao;

   public AbstractAction(Scanner scanner, cardDAO cardDao) {
      this.scanner = scanner;
      this.cardDao = cardDao;
   }
}
