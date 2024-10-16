package model;

import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

/**
 * Class that represent a ship
 */
public class Ship extends GameElement {
    /**
     * name of the ship
     */
    private final String name;
    /**
     * size of the ship
     */
    private final int size;
    /**
     * state of the ship (destroyed or not)
     */
    private boolean isShipDestroyed;
    /**
     * an array that contains the ship parts of the ship
     */
    private final ShipParts[] ShipParts;

    /**
     * The constructor of the ship, create a ship with a given size, name and color
     *
     * @param gameStageModel the game stage model where the ship is added
     * @param size           length of the ship
     * @param name           name of the ship
     * @param color          color of the ship
     */
    public Ship(String name, int size, int color, GameStageModel gameStageModel) {
        super(gameStageModel);
        this.size = size;
        this.name = name;
        this.ShipParts = new ShipParts[size];
        this.isShipDestroyed = false;
        for (int i = 0; i < getSize(); i++) {
            ShipParts shipPart = new ShipParts(this, color, getGameStage());
            setShipParts(i, shipPart);
            gameStageModel.addElement(shipPart);
        }
    }

    /**
     * getter to access to the state of the ship
     *
     * @return the state of the ship
     */
    public boolean getIsShipDestroyed() {
        return this.isShipDestroyed;
    }

    /**
     * getter to access to the name of the ship
     *
     * @return the name of the ship
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter to access to the size of the ship
     *
     * @return the size of the ship
     */
    public int getSize() {
        return this.size;
    }

    /**
     * getter to access to the ship parts of the ship
     *
     * @return the array containing the ship parts of the ship
     */
    public ShipParts[] getShipParts() {
        return this.ShipParts;
    }

    /**
     * getter to access to a ship part at a given index
     *
     * @param i the index to get the ship part
     * @return a ship part at the given index
     */
    public ShipParts getShipParts(int i) {
        return ShipParts[i];
    }

    /**
     * setter to add a ship part to the ship
     *
     * @param i        the index of the ship part in the array
     * @param shipPart the ship part to be added
     */
    public void setShipParts(int i, ShipParts shipPart) {
        ShipParts[i] = shipPart;
    }

    /**
     * check if a ship is destroyed (so check if all the ship parts are destroyed)
     *
     * @return true if all the ship parts of the ship are destroyed
     */
    public boolean isShipDestroyed() {
        for (ShipParts shipPart : ShipParts) {
            if (!shipPart.getIsDestroyed()) {
                return false;
            }
        }
        this.setShipDestroyed(true);
        return true;
    }

    /**
     * setter to set a ship destroyed or not
     *
     * @param shipDestroyed true if the ship has to be set destroyed
     */
    public void setShipDestroyed(boolean shipDestroyed) {
        isShipDestroyed = shipDestroyed;
    }
}
