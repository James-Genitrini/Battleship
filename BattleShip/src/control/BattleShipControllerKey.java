package control;

import boardifier.control.Controller;
import boardifier.control.ControllerKey;
import boardifier.control.Logger;
import boardifier.model.Model;
import boardifier.view.View;
import javafx.event.*;
import javafx.scene.input.*;

/**
 * A basic keystrokes handler.
 * Generally useless for board games, but it can still be used if needed
 */
public class BattleShipControllerKey extends ControllerKey implements EventHandler<KeyEvent> {

    public BattleShipControllerKey(Model model, View view, Controller control) {
        super(model, view, control);
    }

    // Cleat here we have no keyEvent in the game
    public void handle(KeyEvent event) {
        if (!model.isCaptureKeyEvent())
            return;
        Logger.debug(event.getCode().toString());
    }
}
