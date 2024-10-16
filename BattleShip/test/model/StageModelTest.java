package model;

import boardifier.model.Model;
import boardifier.model.TextElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StageModelTest {

    private StageModel stageModel;
    private Model model;

    @BeforeEach
    public void setup() {
        model = mock(Model.class);
        stageModel = new StageModel("TestStage", model);
    }

    @Test
    public void testAddPlayer1Point() {
        stageModel.addPlayer1Point(1);
        assertEquals(1, stageModel.getPlayer1Point());
    }

    @Test
    public void testAddPlayer2Point() {
        stageModel.addPlayer2Point(1);
        assertEquals(1, stageModel.getPlayer2Point());
    }

    @Test
    public void testComputePartyResult_Player1Wins() {
        stageModel.addPlayer1Point(17);
        TextElement playerName = mock(TextElement.class);
        stageModel.setPlayerName(playerName);

        stageModel.computePartyResult();

        verify(playerName).setText("Player 1 wins");
        verify(model).setIdWinner(0);
        verify(model).stopStage();
    }

    @Test
    public void testComputePartyResult_Player2Wins() {
        stageModel.addPlayer2Point(17);
        TextElement playerName = mock(TextElement.class);
        stageModel.setPlayerName(playerName);

        stageModel.computePartyResult();

        verify(playerName).setText("Player 2 wins");
        verify(model).setIdWinner(1);
        verify(model).stopStage();
    }

    @Test
    public void testSetMissilesTouch() {
        Missile[] missilesTouch = new Missile[10];
        for (int i = 0; i < 10; i++) {
            missilesTouch[i] = mock(Missile.class);
        }
        stageModel.setMissilesTouch(missilesTouch);
        assertArrayEquals(missilesTouch, stageModel.getMissilesTouch());
    }

    @Test
    public void testSetMissilesMiss() {
        Missile[] missilesMiss = new Missile[10];
        for (int i = 0; i < 10; i++) {
            missilesMiss[i] = mock(Missile.class);
        }
        stageModel.setMissilesMiss(missilesMiss);
        assertArrayEquals(missilesMiss, stageModel.getMissilesMiss());
    }

    @Test
    public void testSetShipParts() {
        ShipParts[] shipParts = new ShipParts[5];
        for (int i = 0; i < 5; i++) {
            shipParts[i] = mock(ShipParts.class);
        }
        stageModel.setShipParts(shipParts);
        assertArrayEquals(shipParts, stageModel.getShipParts());
    }

    @Test
    public void testChangeVariant() {
        stageModel.changeVariant();
        assertEquals(20, stageModel.getMaxPlayerPoint());
        assertEquals(60, stageModel.getPlayer1toPlay());
        assertEquals(60, stageModel.getPlayer2toPlay());
        assertArrayEquals(new int[]{4, 3, 3, 2, 2, 2, 1, 1, 1, 1}, stageModel.getBoatSizes());
        assertArrayEquals(new String[]{"Cruiser", "Destroyer", "Destroyer", "Torpedo", "Torpedo", "Torpedo}", "Submarine", "Submarine", "Submarine", "Submarine"}, stageModel.getBoatNames());
    }
}
