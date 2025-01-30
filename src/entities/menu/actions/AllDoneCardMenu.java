package entities.menu.actions;

import interfaces.factories.menu.Action;

public class AllDoneCardMenu implements Action {

    @Override
    public void execute() {
        System.out.println("Affichage des cartes...");
    }
    
}
