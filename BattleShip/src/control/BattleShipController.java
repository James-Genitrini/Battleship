package control;

import boardifier.control.Controller;
import boardifier.control.Logger;
import boardifier.model.GameException;
import boardifier.model.Model;
import boardifier.view.View;
import model.StageModel;
import view.BattleShipView;

public class BattleShipController extends Controller {
    public BattleShipController(Model model, View view) {
        super(model, view);
        setControlKey(new BattleShipControllerKey(model, view, this));
        setControlMouse(new BattleShipControllerMouse(model, view, this));
        setControlAction(new BattleShipAction(model, view, this));
    }

    @Override
    public void endOfTurn() {
        // Get the current stage
        StageModel stageModel = (StageModel) model.getGameStage();
        // GO to the next player
        model.setNextPlayer();
//        System.out.println(model.getIdPlayer());
        // Check if the game is over
        if (stageModel.isEndOfTheGame()) {
            System.out.println("End of the game");
            stageModel.displayResults();
        } else {
            // Update the boards state (chown or not) depending on the player
            if (model.getIdPlayer() == 0) {
                stageModel.updateBoardsState();
            } else if (model.getIdPlayer() == 1) {
                stageModel.updateBoardsState();
            }
            // IA implementation possible here but still waiting for it

        }
    }

    @Override
    public void startGame() throws GameException {
        if (firstStageName.isEmpty())
            throw new GameException("Ste the name of the first stage");
        Logger.trace("STARTING GAME");
        startStage(firstStageName);
        BattleShipView battleShipView = (BattleShipView) view;
        // Launching the setting popup
        battleShipView.gameSetupPopup();
        // Setting the first id to 1 (the game go --> player 2 and then player 1)
        model.setIdPlayer(1);
        endOfTurn();
    }
}
