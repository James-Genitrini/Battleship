package view;

import boardifier.model.GameStageModel;
import boardifier.view.GameStageView;
import boardifier.view.TextLook;
import javafx.scene.paint.Color;
import model.Missile;
import model.Ship;
import model.ShipParts;
import model.StageModel;

public class BattleShipStageView extends GameStageView {
    /**
     * Constructor
     * 
     * @param name           the name of the stage view
     * @param gameStageModel the game stage model
     */
    public BattleShipStageView(String name, GameStageModel gameStageModel) {
        super(name, gameStageModel);
    }

    /**
     * Create the looks for the game elements
     */
    @Override
    public void createLooks() {
        // Get the model
        StageModel model = (StageModel) gameStageModel;

        // Create the looks for the different boards (boat board and attack board - Black for player 1 and Red for player 2)
        addLook(new BattleShipBoardLook(130, Color.BLACK, model.getBoatBoardPlayer1()));
        addLook(new BattleShipBoardLook(130, Color.BLACK, model.getAttackBoardPlayer1()));
        addLook(new BattleShipBoardLook(130, Color.RED, model.getBoatBoardPlayer2()));
        addLook(new BattleShipBoardLook(130, Color.RED, model.getAttackBoardPlayer2()));

        // Create the looks for the different ships and missiles

        // New look for all the ships parts of the ships of player 1 variant 1
        for (Ship ship : model.getPlayer1ShipsVar1()) {
            for (ShipParts shipPart : ship.getShipParts()) {
                addLook(new ShipPartLook(shipPart));
            }
        }

        // New look for all the ships parts of the ships of player 2 variant 1
        for (Ship ship : model.getPlayer2ShipsVar1()) {
            for (ShipParts shipPart : ship.getShipParts()) {
                addLook(new ShipPartLook(shipPart));
            }
        }

        // New look for all the ships parts of the ships of player 1 variant 2
        for (Ship ship : model.getPlayer1ShipsVar2()) {
            for (ShipParts shipPart : ship.getShipParts()) {
                addLook(new ShipPartLook(shipPart));
            }
        }

        // New look for all the ships parts of the ships of player 2 variant 2
        for (Ship ship : model.getPlayer2ShipsVar2()) {
            for (ShipParts shipPart : ship.getShipParts()) {
                addLook(new ShipPartLook(shipPart));
            }
        }

        // New look for all the missiles of player 1
        for (Missile missile : model.getPlayer1Missiles()) {
            addLook(new MissileLook(missile));
        }

        // New look for all the missiles of player 2
        for (Missile missile : model.getPlayer2Missiles()) {
            addLook(new MissileLook(missile));
        }

        // New look for all the missiles of the boat board of player 1
        for (Missile missile : model.getBoatBoardPlayer1Missiles()) {
            addLook(new MissileLook(missile));
        }

        // New look for all the missiles of the boat board of player 2
        for (Missile missile : model.getBoatBoardPlayer2Missiles()) {
            addLook(new MissileLook(missile));
        }

        // New look for the text of the game
        addLook(new TextLook(30, "0x000", model.updateText()));
    }
}