package model;

import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class ShipParts extends GameElement {
    private final Ship belongingShip;
    private int color;
    private boolean isDestroyed;

    /**
     * constructor of the ship part
     *
     * @param belongingShip the ship to which the ship part belongs
     * @param color the color of the ship part
     * @param gameStageModel the game stage model where the ship part is added
     */
    public ShipParts(Ship belongingShip, int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        ElementTypes.register("shipPart", 51);
        type = ElementTypes.getType("shipPart");
        this.belongingShip = belongingShip;
        this.color = color;
        this.isDestroyed = false;
    }

    /**
     * get the ship to which the ship part belongs
     *
     * @return the ship to which the ship part belongs
     */
    public Ship getBelongingShip() {
        return belongingShip;
    }

    /**
     * getter to check if the ship part is destroyed
     * 
     * @return true if the ship part is destroyed
     */
    public boolean getIsDestroyed() {
        return isDestroyed;
    }

    /**
     * setter to set the state of the ship part (destroyed or not)
     * 
     * @param isDestroyed true if the ship part is destroyed
     */
    public void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    /**
     * method to set the ship part as destroyed
     */
    public void destroy() {
        isDestroyed = true;
    }

    /**
     * getter to access to the color of the ship part (identity the player)
     * 
     * @return the color of the ship part
     */
    public int getColor() {
        return color;
    }

    /**
     * setter for the color of the ship part
     * 
     * @param color the color of the ship part
     */
    public void setColor(int color) {
        this.color = color;
    }
}
