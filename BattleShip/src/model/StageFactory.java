package model;

import boardifier.model.GameStageModel;
import boardifier.model.StageElementsFactory;
import boardifier.model.TextElement;
import javafx.stage.Screen;

public class StageFactory extends StageElementsFactory {
    private StageModel stageModel;

    /**
     * Create a factory for the stage
     * 
     * @param gameStageModel the game stage model
     */
    public StageFactory(GameStageModel gameStageModel) {
        super(gameStageModel);
        stageModel = (StageModel) gameStageModel;
    }

    /**
     * Set up the stage
     * Create the elements of the stage
     */
    @Override
    public void setup() {
        // Create the different boards
        BattleShipBoard BoatBoard = new BattleShipBoard("player1boatBoard", 0, 0, stageModel);
        stageModel.setPlayer1BoatBoard(BoatBoard);
        BattleShipBoard BoatBoard2 = new BattleShipBoard("player2boatBoard", 0, 0, stageModel);
        stageModel.setPlayer2BoatBoard(BoatBoard2);
        BattleShipBoard AttackBoard = new BattleShipBoard("player1attackBoard", 800, 0, stageModel);
        stageModel.setPlayer1AttackBoard(AttackBoard);
        BattleShipBoard AttackBoard2 = new BattleShipBoard("player2attackBoard", 800, 0, stageModel);
        stageModel.setPlayer2AttackBoard(AttackBoard2);

        // The ships for variant 1
        // Player 1
        Ship player1size5 = new Ship("AirCraftCarrier", 5, 1, stageModel);
        Ship player1size4 = new Ship("Cruiser", 4, 1, stageModel);
        Ship player1size3_1 = new Ship("Destroyer1", 3, 1, stageModel);
        Ship player1size3_2 = new Ship("Destroyer2", 3, 1, stageModel);
        Ship player1size2 = new Ship("Torpedo", 2, 1, stageModel);
        stageModel.getPlayer1ShipsVar1().add(player1size5);
        stageModel.getPlayer1ShipsVar1().add(player1size4);
        stageModel.getPlayer1ShipsVar1().add(player1size3_1);
        stageModel.getPlayer1ShipsVar1().add(player1size3_2);
        stageModel.getPlayer1ShipsVar1().add(player1size2);

        // Player 2
        Ship player2size5 = new Ship("AirCraftCarrier", 5, 2, stageModel);
        Ship player2size4 = new Ship("Cruiser", 4, 2, stageModel);
        Ship player2size3_1 = new Ship("Destroyer1", 3, 2, stageModel);
        Ship player2size3_2 = new Ship("Destroyer2", 3, 2, stageModel);
        Ship player2size2 = new Ship("Torpedo", 2, 2, stageModel);
        stageModel.getPlayer2ShipsVar1().add(player2size5);
        stageModel.getPlayer2ShipsVar1().add(player2size4);
        stageModel.getPlayer2ShipsVar1().add(player2size3_1);
        stageModel.getPlayer2ShipsVar1().add(player2size3_2);
        stageModel.getPlayer2ShipsVar1().add(player2size2);

        // The ships for variant 2
        // Player 1
        Ship player1size4v2 = new Ship("Cruiser", 4, 1, stageModel);
        Ship player1size3_1v2 = new Ship("Destroyer1", 3, 1, stageModel);
        Ship player1size3_2v2 = new Ship("Destroyer2", 3, 1, stageModel);
        Ship player1size2_1v2 = new Ship("Torpedo1", 2, 1, stageModel);
        Ship player1size2_2v2 = new Ship("Torpedo2", 2, 1, stageModel);
        Ship player1size2_3v2 = new Ship("Torpedo3", 2, 1, stageModel);
        Ship player1size1_1v2 = new Ship("Submarine1", 1, 1, stageModel);
        Ship player1size1_2v2 = new Ship("Submarine2", 1, 1, stageModel);
        Ship player1size1_3v2 = new Ship("Submarine3", 1, 1, stageModel);
        Ship player1size1_4v2 = new Ship("Submarine4", 1, 1, stageModel);
        stageModel.getPlayer1ShipsVar2().add(player1size4v2);
        stageModel.getPlayer1ShipsVar2().add(player1size3_1v2);
        stageModel.getPlayer1ShipsVar2().add(player1size3_2v2);
        stageModel.getPlayer1ShipsVar2().add(player1size2_1v2);
        stageModel.getPlayer1ShipsVar2().add(player1size2_2v2);
        stageModel.getPlayer1ShipsVar2().add(player1size2_3v2);
        stageModel.getPlayer1ShipsVar2().add(player1size1_1v2);
        stageModel.getPlayer1ShipsVar2().add(player1size1_2v2);
        stageModel.getPlayer1ShipsVar2().add(player1size1_3v2);
        stageModel.getPlayer1ShipsVar2().add(player1size1_4v2);

        // Player 2
        Ship player2size4v2 = new Ship("Cruiser", 4, 2, stageModel);
        Ship player2size3_1v2 = new Ship("Destroyer1", 3, 2, stageModel);
        Ship player2size3_2v2 = new Ship("Destroyer2", 3, 2, stageModel);
        Ship player2size2_1v2 = new Ship("Torpedo1", 2, 2, stageModel);
        Ship player2size2_2v2 = new Ship("Torpedo2", 2, 2, stageModel);
        Ship player2size2_3v2 = new Ship("Torpedo3", 2, 2, stageModel);
        Ship player2size1_1v2 = new Ship("Submarine1", 1, 2, stageModel);
        Ship player2size1_2v2 = new Ship("Submarine2", 1, 2, stageModel);
        Ship player2size1_3v2 = new Ship("Submarine3", 1, 2, stageModel);
        Ship player2size1_4v2 = new Ship("Submarine4", 1, 2, stageModel);
        stageModel.getPlayer2ShipsVar2().add(player2size4v2);
        stageModel.getPlayer2ShipsVar2().add(player2size3_1v2);
        stageModel.getPlayer2ShipsVar2().add(player2size3_2v2);
        stageModel.getPlayer2ShipsVar2().add(player2size2_1v2);
        stageModel.getPlayer2ShipsVar2().add(player2size2_2v2);
        stageModel.getPlayer2ShipsVar2().add(player2size2_3v2);
        stageModel.getPlayer2ShipsVar2().add(player2size1_1v2);
        stageModel.getPlayer2ShipsVar2().add(player2size1_2v2);
        stageModel.getPlayer2ShipsVar2().add(player2size1_3v2);
        stageModel.getPlayer2ShipsVar2().add(player2size1_4v2);

        // Create the missiles for each player - Attack board size
        // Player 1
        for (int i = 0; i < 50; i++) {
            Missile missile = new Missile(1, stageModel);
            stageModel.getPlayer1Missiles().add(missile);
            stageModel.addElement(missile);
        }

        // Player 2
        for (int i = 0; i < 50; i++) {
            Missile missile = new Missile(2, stageModel);
            stageModel.getPlayer2Missiles().add(missile);
            stageModel.addElement(missile);
        }

        // Create the missiles for each player - Boat board size
        // Player 1
        for (int i = 0; i < 50; i++) {
            Missile missile = new Missile(1, stageModel);
            stageModel.getBoatBoardPlayer1Missiles().add(missile);
            stageModel.addElement(missile);
        }

        // Player 2
        for (int i = 0; i < 50; i++) {
            Missile missile = new Missile(2, stageModel);
            stageModel.getBoatBoardPlayer2Missiles().add(missile);
            stageModel.addElement(missile);
        }

        // Create the text element for the different text updates
        TextElement text = new TextElement(stageModel.getLogText(), stageModel);
        stageModel.setText(text);
        text.setLocation(Screen.getPrimary().getBounds().getWidth() / 4 + 50, 600);
    }
}
