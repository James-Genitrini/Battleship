package model;

import boardifier.model.GameStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
public class ShipPartsTest {
    private ShipParts shipParts;
    private GameStageModel gameStageModel;

    @BeforeEach
    public void setup(){
        gameStageModel = mock(GameStageModel.class);
        shipParts = new ShipParts(1, gameStageModel);
    }

    @Test
    public void testGetSymbol(){
        assertEquals("X", shipParts.getSymbol());
    }

    @Test
    public void testSetDestroyed(){
        shipParts.setDestroyed(true);
        assertTrue(shipParts.isDestroyed());
    }

    @Test
    public void testIsDestroyed(){
        assertFalse(shipParts.isDestroyed());
    }

    @Test
    public void testDestroy(){
        shipParts.destroy();
        assertTrue(shipParts.isDestroyed());
    }

    @Test
    public void testGetColor(){
        assertEquals(1, shipParts.getColor());
    }

    @Test
    public void testSetColor(){
        shipParts.setColor(2);
        assertEquals(2, shipParts.getColor());
    }

    @Test
    public void testGetType(){
        assertEquals(51, shipParts.getType());
    }
}
