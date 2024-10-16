package control;

import boardifier.control.ControllerAction;
import boardifier.model.GameException;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import view.BattleShipView;
import boardifier.control.Controller;
import boardifier.model.Model;
import boardifier.view.View;

public class BattleShipAction extends ControllerAction implements EventHandler<ActionEvent> {
    private final BattleShipView battleShipView;

    public BattleShipAction(Model model, View view, Controller control) {
        super(model, view, control);
        this.battleShipView = (BattleShipView) view;
        battleShipView.getRootPane();
        this.setMenuHandlers();
    }

    // Set the menu handlers and the actions to do when buttons are clicked
    private void setMenuHandlers() {
        battleShipView.getMenuNewGame().setOnAction(e -> {
            try {
                control.startGame();
            } catch (GameException err) {
                System.err.println(err.getMessage());
                System.exit(1);
            }
        });
        battleShipView.getMenuIntro().setOnAction(e -> {
            control.stopGame();
            battleShipView.resetView();
        });
        battleShipView.getMenuQuit().setOnAction(e -> System.exit(0));

    }
}
