package view;

import boardifier.model.ContainerElement;
import boardifier.view.ClassicBoardLook;
import javafx.scene.paint.Color;

public class BattleShipBoardLook extends ClassicBoardLook {

    /**
     * Constructor of the BattleShipBoardLook creating a new board look for the game
     *
     * @param size the size of the board
     * @param color the color of the board
     * @param element the container element
     */
    public BattleShipBoardLook(int size, Color color, ContainerElement element) {
        super(size / 3, element, 0, Color.LIGHTSKYBLUE, Color.LIGHTBLUE, 1, Color.PINK, 6, color, true);
    }
}
