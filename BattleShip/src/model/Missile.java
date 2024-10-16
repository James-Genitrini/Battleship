package model;

import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class Missile extends GameElement {
    private int color;

    /**
     * Create a missile with a given color (color to indentify the player)
     * 
     * @param color color of the missile
     */
    public Missile(int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        // registering element types defined especially for this game
        this.color = color;
    }

    /**
     * get the color of the missile
     * 
     * @return color of the missile
     */
    public int getColor() {
        return color;
    }

    /**
     * Get the symbol of the missile (for console version not used in GUI)
     * 
     * @return the symbol of the missile
     */
    public String getSymbol() {
        return "X";
    }

    /**
     * set the color of the missile
     * 
     * @param color color of the missile
     */
    public void setColor(int color) {
        this.color = color;
    }
}
