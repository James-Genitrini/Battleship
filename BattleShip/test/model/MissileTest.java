package model;

import boardifier.model.GameStageModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MissileTest {

    private Missile missile;
    private GameStageModel gameStageModel;

    @BeforeEach
    public void setup() {
        gameStageModel = mock(GameStageModel.class);
        missile = new Missile(Missile.Missile_Touch, gameStageModel);
    }

    @Test
    public void testGetColor() {
        assertEquals(Missile.Missile_Touch, missile.getColor());
    }

    @Test
    public void testSetColor() {
        missile.setColor(Missile.Missile_Miss);
        assertEquals(Missile.Missile_Miss, missile.getColor());
    }

    @Test
    public void testGetSymbol() {
        assertEquals("X", missile.getSymbol());
    }

    @Test
    public void testTypeRegistration() {
        assertEquals(50, missile.getType());
    }
}
