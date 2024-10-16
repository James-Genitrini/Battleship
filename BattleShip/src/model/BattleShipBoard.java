package model;

import boardifier.control.Logger;
import boardifier.model.GameStageModel;
import boardifier.model.ContainerElement;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

public class BattleShipBoard extends ContainerElement {
    /**
     * Create a new BattleShipBoard
     * 
     * @param name           the name of the board
     * @param x              the x position of the board
     * @param y              the y position of the board
     * @param gameStageModel the gameStageModel
     */
    public BattleShipBoard(String name, int x, int y, GameStageModel gameStageModel) {
        // Call contructor of ContainerElement to create a new BattleShipBoard with a name, coordinates and a size (10*10)
        // for battlehship game and the related gameStageModel
        super(name, x, y, 10, 10, gameStageModel);
    }

    /**
     * set the valid cells reachable by player
     * 
     * @param number number of valid cells
     */
    public void setValidCells(int number) {
        resetReachableCells(false);
        java.util.List<Point> valid = computeValidCells(number);
        if (valid != null) {
            for (Point p : valid) {
                reachableCells[p.y][p.x] = true;
            }
        }
    }

    /**
     * Compute the cells that are reachable by the player
     * 
     * @param number the number of cells that are reachable
     * @return the list of reachable cells
     */
    public java.util.List<Point> computeValidCells(int number) {
        List<Point> lst = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (reachableCells[j][i]) {
                    lst.add(new Point(i, j));
                }
            }
        }
        return lst;
    }

    /**
     * setter for x position of the board
     * 
     * @param x the x position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * setter for y position of the board
     * 
     * @param y the y position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Place a ship on the board
     * 
     * @param ship       the ship
     * @param x          x position
     * @param y          y position
     * @param horizontal the orientation (boolean true for horizontal, false for vertical)
     */
    public void placeShipOnBoard(Ship ship, int x, int y, boolean horizontal) {
        for (int i = 0; i < ship.getSize(); i++) {
            if (horizontal) {
                addElement(ship.getShipParts(i), x, y + i);
            } else {
                addElement(ship.getShipParts(i), x + i, y);
            }
        }
    }

    /**
     * place a new missile on the board
     * 
     * @param missile the missile
     * @param x       x position
     * @param y       y position
     */
    public void placeMissileOnBoard(Missile missile, int x, int y) {
        addElement(missile, x, y);
    }
}
