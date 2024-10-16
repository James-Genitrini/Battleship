package control;

import boardifier.model.GameElement;
import boardifier.model.Model;
import boardifier.model.Player;
import boardifier.model.TextElement;
import model.BattleShipBoard;
import model.Missile;
import model.ShipParts;
import model.StageModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class BattleShipControllerTest {
    private Model model;
    private StageModel stageModel;
    private Player player;
    private BattleShipBoard attackBoard;
    private BattleShipBoard attackBoard2;
    private BattleShipBoard boatBoard;
    private BattleShipBoard boatBoard2;

    private BattleShipController controller;

    @BeforeEach
    public void setup() {
        model = mock(Model.class);
        stageModel = mock(StageModel.class);
        player = mock(Player.class);
        attackBoard = mock(BattleShipBoard.class);
        attackBoard2 = mock(BattleShipBoard.class);
        boatBoard = mock(BattleShipBoard.class);
        boatBoard2 = mock(BattleShipBoard.class);


        when(model.getGameStage()).thenReturn(stageModel);
        when(model.getCurrentPlayer()).thenReturn(player);
        when(stageModel.getBoatBoard()).thenReturn(boatBoard);
        when(stageModel.getBoatBoard2()).thenReturn(boatBoard2);
        when(stageModel.getAttackBoard()).thenReturn(attackBoard);
        when(stageModel.getAttackBoard2()).thenReturn(attackBoard2);

        controller = new BattleShipController(model, null);


    }

    @Test
    public void testAnalyseAndVariantWithVariant2() {
        String line = "2";

        boolean result = controller.analyseAndVariant(line);

        assertTrue(result);
        verify(stageModel).changeVariant();
    }

    @Test
    public void testAnalyseAndVariantWithVariant1() {
        String line = "1";

        boolean result = controller.analyseAndVariant(line);

        assertTrue(result);
        verify(stageModel, never()).changeVariant();
    }

    @Test
    public void testAnalyseAndVariantWithInvalidInput() {
        String line = "3";

        boolean result = controller.analyseAndVariant(line);

        assertFalse(result);
        verify(stageModel, never()).changeVariant();
    }

    @Test
    public void testAnalyseAndPlaceShipValidVerticalPlacement() {
        when(stageModel.getShipParts()).thenReturn(new ShipParts[]{new ShipParts(1, stageModel), new ShipParts(1, stageModel), new ShipParts(1, stageModel)});

        String input = "A1A3";
        int boatSize = 3;

        boolean result = controller.analyseAndPlaceShip(input, boatSize);

        assertTrue(result);
    }

    @Test
    public void testAnalyseAndPlaceShipInvalidDiagonalPlacement() {
        when(stageModel.getShipParts()).thenReturn(new ShipParts[]{new ShipParts(1, stageModel), new ShipParts(1, stageModel), new ShipParts(1, stageModel)});

        String input = "A1B2";
        int boatSize = 2;

        boolean result = controller.analyseAndPlaceShip(input, boatSize);

        assertFalse(result);
    }

    @Test
    public void testAnalyseAndPlaceShipInvalidOutOfBoundsPlacement() {
        when(stageModel.getShipParts()).thenReturn(new ShipParts[]{new ShipParts(1, stageModel), new ShipParts(1, stageModel), new ShipParts(1, stageModel)});

        String input = "A1A4";
        int boatSize = 3;

        boolean result = controller.analyseAndPlaceShip(input, boatSize);

        assertFalse(result);
    }

    @Test
    public void testAnalyseAndPlayValidAttack(){
        when(stageModel.getMissilesMiss()).thenReturn(new Missile[]{new Missile(1, stageModel), new Missile(1, stageModel), new Missile(1, stageModel)});

        String input = "E5";

        boolean result = controller.analyseAndPlay(input);

        assertFalse(result);
    }

    @Test
    public void testAnalyseAndPlayInvalidAttack(){
        when(stageModel.getMissilesMiss()).thenReturn(new Missile[]{new Missile(1, stageModel)});

        String input = "A11";

        boolean result = controller.analyseAndPlay(input);

        assertFalse(result);
    }

}
