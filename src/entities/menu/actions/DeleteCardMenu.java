package entities.menu.actions;

import interfaces.factories.menu.Action;

public class DeleteCardMenu implements Action {
    
    @Override
    public void execute() {
        System.out.println("Suppression d'une carte..");
    }
    
}
