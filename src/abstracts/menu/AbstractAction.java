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

   protected boolean isResponseValid(String message) {
      String reponse;

      do {
         System.out.print(message + " (O/N) : ");
         reponse = scanner.nextLine().toUpperCase();
      } while (!reponse.equalsIgnoreCase("O") && !reponse.equalsIgnoreCase("N"));
      return reponse.equalsIgnoreCase("O");
   }

   protected <E extends Enum<E>> E isEnumValid(Class<E> enumType, String message) {
      E enumValue = null;
      while (enumValue == null) {
          System.out.print(message);
          String input = scanner.nextLine().trim().toUpperCase();
          try {
              enumValue = Enum.valueOf(enumType, input);
          } catch (IllegalArgumentException e) {
              System.out.println("Valeur invalide ! Veuillez entrer une des valeurs suivantes :");
              for (E value : enumType.getEnumConstants()) {
                  System.out.println("- " + value.name());
              }
          }
      }
      return enumValue;
  } 
}
