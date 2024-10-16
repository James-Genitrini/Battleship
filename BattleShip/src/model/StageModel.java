package model;

import boardifier.model.GameStageModel;
import boardifier.model.Model;
import boardifier.model.StageElementsFactory;
import boardifier.model.TextElement;

import java.util.ArrayList;
import java.util.List;

public class StageModel extends GameStageModel {
    private int variantPicked = 1;
    private int boatNumber = 0;
    private int boatNumberPlayer1 = 0;
    private int boatNumberPlayer2 = 0;
    private int missileNumberPlayer1;
    private int missileNumberPlayer2;
    private int missileLimit;
    public final static int STATE_PLACEMENT = 0;
    public final static int STATE_ATTACK = 1;
    private int destroyedShipsPlayer1;
    private int destroyedShipsPlayer2;
    private int hitsPlayer1;
    private int hitsPlayer2;

    /**
     * @return the default element factory for this model
     *
     *         Create all the elements of the stage
     */
    @Override
    public StageElementsFactory getDefaultElementFactory() {
        return new StageFactory(this);
    }

    /**
     * constructor
     *
     * @param name
     * @param model
     */
    public StageModel(String name, Model model) {
        super(name, model);
        setupCallbacks();
    }

    private void setupCallbacks() {
        onPutInContainer((element, gridDest, rowDest, colDest) -> {
            switch (getVariant()) {
                case 1:
                    if (getState() == StageModel.STATE_PLACEMENT && getBoatNumber(model.getIdPlayer()) + 1 != 5) {
                        displayCement(defineShips(getVariant(), model.getIdPlayer())
                                .get(getBoatNumber(model.getIdPlayer()) + 1));
                    } else {
                        baseDisplayCement();
                    }
                    break;
                case 2:
                    if (getState() == StageModel.STATE_PLACEMENT && getBoatNumber(model.getIdPlayer()) + 1 != 10) {
                        displayCement(defineShips(getVariant(), model.getIdPlayer())
                                .get(getBoatNumber(model.getIdPlayer()) + 1));
                    } else {
                        baseDisplayCement();
                    }
                    break;
            }

        });
    }

    // Text element for the game (update during the game)
    private TextElement text;


    /**
     * Setter for the text element
     * @param text
     */
    public void setText(TextElement text) {
        this.text = text;
        addElement(text);
    }

    /**
     * Update the text element
     * @return the text element
     */
    public TextElement updateText() {
        return text;
    }

    // Log text for the game
    private String logText = "Welcome to BattleShip!";

    /**
     * Get the log text
     * @return the log text
     */
    public String getLogText() {
        return logText;
    }

    /**
     * Set the log text
     * @param logText
     */
    public void setLogText(String logText) {
        this.logText = logText;
    }

    /**
     * Display the text for the placement of a ship (yeah that's a little word game)
     * @param ship
     */
    public void displayCement(Ship ship) {
        text.setText("Place your " + ship.getName() + " on the board. Size: " + ship.getSize());
    }

    /**
     * Display the base text for the placement of a ship
     */
    public void baseDisplayCement() {
        text.setText("Place your aircraft carrier on the board. Size: 5");
    }

    /**
     * Set the variant of the game
     *
     * @param variant the variant of the game
     */
    public void setVariant(int variant) {
        this.variantPicked = variant;
        if (variant == 1) {
            boatNumber = 5;
        } else if (variant == 2) {
            boatNumber = 10;
        } else {
            boatNumber = 5;
        }
    }

    /**
     * Get the variant of the game
     *
     * @return the variant of the game
     */
    public int getVariant() {
        return variantPicked;
    }

    /**
     * Get the number of boats
     * @return
     */
    public int getBoatNumber() {
        return boatNumber;
    }

    /**
     * Get the number of boats for a player
     * @param player
     * @return
     */
    public int getBoatNumber(int player) {
        return (player == 1) ? boatNumberPlayer1 : boatNumberPlayer2;
    }

    /**
     * Set the number of boats
     * @param boatNumber
     */
    public void setBoatNumber(int boatNumber) {
        this.boatNumber = boatNumber;
    }

    /**
     * Get the number of boats for player 1
     * @return
     */
    public int getBoatNumberPlayer1() {
        return boatNumberPlayer1;
    }

    /**
     * Get the number of boats for player 2
     * @return
     */
    public int getBoatNumberPlayer2() {
        return boatNumberPlayer2;
    }

    /**
     * Add a boat number for a player
     * @param player
     */
    public void addBoatNumber(int player) {
        if (player == 1) {
            boatNumberPlayer1++;
        } else {
            boatNumberPlayer2++;
        }
    }

    /**
     * Check if the ship placement phase is over
     * @return
     */
    public boolean isShipPlacementPhaseOver() {
        return boatNumberPlayer1 == boatNumber && boatNumberPlayer2 == boatNumber;
    }

    /**
     * Get the number of missiles for player 1
     * @return
     */
    public int getMissileNumberPlayer1() {
        return missileNumberPlayer1;
    }

    /**
     * Get the number of missiles for player 2
     * @return
     */
    public int getMissileNumberPlayer2() {
        return missileNumberPlayer2;
    }

    /**
     * Add a missile number for a player
     * @param player
     */
    public void addMissileNumber(int player) {
        if (player == 1) {
            missileNumberPlayer1++;
        } else {
            missileNumberPlayer2++;
        }
    }

    // Array of missiles for the boat board
    private final ArrayList<Missile> boatBoardPlayer1Missiles = new ArrayList<Missile>();

    /**
     *  Get the missiles for the boat board of player 1
     * @return
     */
    public ArrayList<Missile> getBoatBoardPlayer1Missiles() {
        return boatBoardPlayer1Missiles;
    }

    /**
     * Get a missile for the boat board of player 1
     * @param index
     * @return
     */
    public Missile getBoatBoardPlayer1Missile(int index) {
        return boatBoardPlayer1Missiles.get(index);
    }

    // Array of missiles for the boat board
    private final ArrayList<Missile> boatBoardPlayer2Missiles = new ArrayList<Missile>();

    /**
     * Get the missiles for the boat board of player 2
     * @return
     */
    public ArrayList<Missile> getBoatBoardPlayer2Missiles() {
        return boatBoardPlayer2Missiles;
    }

    /**
     * Get a missile for the boat board of player 2
     * @param index
     * @return
     */
    public Missile getBoatBoardPlayer2Missile(int index) {
        return boatBoardPlayer2Missiles.get(index);
    }

    // Declatation of the boards
    private BattleShipBoard boatBoardPlayer1;
    private BattleShipBoard boatBoardPlayer2;

    private BattleShipBoard attackBoardPlayer1;
    private BattleShipBoard attackBoardPlayer2;

    // Declaration of the ships for the different variants
    private final ArrayList<Ship> player1ShipsVar1 = new ArrayList<>();
    private final ArrayList<Ship> player2ShipsVar1 = new ArrayList<>();

    private final ArrayList<Ship> player1ShipsVar2 = new ArrayList<>();
    private final ArrayList<Ship> player2ShipsVar2 = new ArrayList<>();

    private final ArrayList<Missile> player1Missiles = new ArrayList<>();
    private final ArrayList<Missile> player2Missiles = new ArrayList<>();

    /**
     * Get the boat board of player 1
     * @return
     */
    public BattleShipBoard getBoatBoardPlayer1() {
        return boatBoardPlayer1;
    }

    /**
     * Get the boat board of player 2
     * @return
     */
    public BattleShipBoard getBoatBoardPlayer2() {
        return boatBoardPlayer2;
    }

    /**
     * Get the attack board of player 1
     * @return
     */
    public BattleShipBoard getAttackBoardPlayer1() {
        return attackBoardPlayer1;
    }

    /**
     * Get the attack board of player 2
     * @return
     */
    public BattleShipBoard getAttackBoardPlayer2() {
        return attackBoardPlayer2;
    }

    /**
     * Set the boat board of player 1
     * @param boatBoard
     */
    public void setPlayer1BoatBoard(BattleShipBoard boatBoard) {
        boatBoardPlayer1 = boatBoard;
        addContainer(boatBoardPlayer1);
    }

    /**
     * Set the boat board of player 2
     * @param boatBoard
     */
    public void setPlayer2BoatBoard(BattleShipBoard boatBoard) {
        boatBoardPlayer2 = boatBoard;
        addContainer(boatBoardPlayer2);
    }

    /**
     * Show the boat board of player 1
     */
    public void showBoatBoardPlayer1() {
        boatBoardPlayer1.setVisible(true);
    }

    /**
     * Show the boat board of player 2
     */
    public void showBoatBoardPlayer2() {
        boatBoardPlayer2.setVisible(true);
    }

    /**
     * Hide the boat board of player 1
     */
    public void hideBoatBoardPlayer1() {
        boatBoardPlayer1.setVisible(false);
    }

    /**
     * Hide the boat board of player 2
     */
    public void hideBoatBoardPlayer2() {
        boatBoardPlayer2.setVisible(false);
    }

    /**
     * Set the attack board of player 1
     * @param attackBoard
     */
    public void setPlayer1AttackBoard(BattleShipBoard attackBoard) {
        attackBoardPlayer1 = attackBoard;
        addContainer(attackBoardPlayer1);
    }

    /**
     * Set the attack board of player 2
     * @param attackBoard
     */
    public void setPlayer2AttackBoard(BattleShipBoard attackBoard) {
        attackBoardPlayer2 = attackBoard;
        addContainer(attackBoardPlayer2);
    }

    /**
     * Show the attack board of player 1
     */
    public void showAttackBoardPlayer1() {
        attackBoardPlayer1.setVisible(true);
    }

    /**
     * Show the attack board of player 2
     */
    public void showAttackBoardPlayer2() {
        attackBoardPlayer2.setVisible(true);
    }

    /**
     * Hide the attack board of player 1
     */
    public void hideAttackBoardPlayer1() {
        attackBoardPlayer1.setVisible(false);
    }

    /**
     * Hide the attack board of player 2
     */
    public void hideAttackBoardPlayer2() {
        attackBoardPlayer2.setVisible(false);
    }

    /**
     * Add a ship to the player 1 ships for variant 1
     */
    public ArrayList<Ship> getPlayer1ShipsVar1() {
        return player1ShipsVar1;
    }

    /**
     * get the player 2 ships for variant 1
     */
    public ArrayList<Ship> getPlayer2ShipsVar1() {
        return player2ShipsVar1;
    }

    /**
     * get the player 1 ships for variant 2
     */
    public ArrayList<Ship> getPlayer1ShipsVar2() {
        return player1ShipsVar2;
    }

    /**
     * get the player 2 ships for variant 2
     */
    public ArrayList<Ship> getPlayer2ShipsVar2() {
        return player2ShipsVar2;
    }

    /**
     * get the player 1 missiles
     */
    public ArrayList<Missile> getPlayer1Missiles() {
        return player1Missiles;
    }

    /**
     * get the player 2 missiles
     */
    public ArrayList<Missile> getPlayer2Missiles() {
        return player2Missiles;
    }

    /**
     * get a player 1 missile at a given index
     */
    public Missile getPlayer1Missile(int index) {
        return player1Missiles.get(index);
    }

    /**
     * get a player 2 missile at a given index
     */
    public Missile getPlayer2Missile(int index) {
        return player2Missiles.get(index);
    }

    /**
     * get the number of missiles for the player given in parameter
     * @param player
     */
    public int getPlayerMissileNumber(int player) {
        return (player == 1) ? missileNumberPlayer1 : missileNumberPlayer2;
    }

    /**
     * set the limit of missiles
     * @param missileLimit
     */
    public void setMissileLimit(int missileLimit) {
        this.missileLimit = missileLimit;
    }

    /**
     * get the limit of missiles
     */
    public int getMissileLimit() {
        return missileLimit;
    }

    /**
     * update the board with the boards to hide or show
     */
    public void updateBoardsState() {
        StageModel stageModel = (StageModel) model.getGameStage();

        if (model.getIdPlayer() == 0) {
            stageModel.showBoatBoardPlayer1();
            stageModel.hideBoatBoardPlayer2();
            stageModel.showAttackBoardPlayer1();
            stageModel.hideAttackBoardPlayer2();
        } else if (model.getIdPlayer() == 1) {
            stageModel.showBoatBoardPlayer2();
            stageModel.hideBoatBoardPlayer1();
            stageModel.showAttackBoardPlayer2();
            stageModel.hideAttackBoardPlayer1();
        } else {
            System.out.println("Error in updateBoardsState");
            stageModel.hideBoatBoardPlayer1();
            stageModel.hideBoatBoardPlayer2();
            stageModel.hideAttackBoardPlayer1();
            stageModel.hideAttackBoardPlayer2();
        }
    }

    /**
     * boolean to check if there is ships remaining for player 1
     */
    public boolean shipsRemainingPlayer1() {
        List<Ship> ships = (variantPicked == 1) ? player1ShipsVar1 : player1ShipsVar2;

        for (Ship ship : ships) {
            if (!ship.isShipDestroyed()) {
                return false;
            }
        }

        return true;

    }

    /**
     * boolean to check if there is ships remaining for player 2
     */
    public boolean shipsRemainingPlayer2() {
        List<Ship> ships = (variantPicked == 1) ? player2ShipsVar1 : player2ShipsVar2;

        for (Ship ship : ships) {
            if (!ship.isShipDestroyed()) {
                return false;
            }
        }

        return true;
    }

    /**
     * define the placement board for a player
     * @param player
     * @return BattleShipBoard
     */
    public BattleShipBoard defineBoatBoard(int player) {
        if (player == 0) {
            return getBoatBoardPlayer1();
        } else if (player == 1) {
            return getBoatBoardPlayer2();
        } else
            return getBoatBoardPlayer1();
    }

    /**
     * define the attack board for a player
     * @param player
     * @return BattleShipBoard
     */
    public BattleShipBoard defineAttackBoard(int player) {
        if (player == 0) {
            return getAttackBoardPlayer1();
        } else if (player == 1) {
            return getAttackBoardPlayer2();
        } else
            return null;
    }

    /**
     * define the ships for a player
     * @param variant
     * @param player
     * @return the array of ships
     */
    public ArrayList<Ship> defineShips(int variant, int player) {
        if (variant == 1) {
            return (player == 0) ? player1ShipsVar1 : player2ShipsVar1;
        } else if (variant == 2) {
            return (player == 0) ? player1ShipsVar2 : player2ShipsVar2;
        } else {
            return null;
        }
    }

    /**
     * define the boat board for the enemy
     * @param player
     * @return BattleShipBoard
     */
    public BattleShipBoard defineEniBoard(int player) {
        if (player == 0) {
            return getBoatBoardPlayer2();
        } else if (player == 1) {
            return getBoatBoardPlayer1();
        } else {
            return null;
        }
    }

    /**
     * define the missiles for a player
     * @param player
     * @return the array of missiles
     */
    public ArrayList<Missile> defineMissiles(int player) {
        if (player == 0) {
            return getPlayer1Missiles();
        } else if (player == 1) {
            return getPlayer2Missiles();
        } else {
            return null;
        }
    }

    /**
     * define the missiles to place on a boat board (your boat board when you play)
     * @param player
     * @return the array of missiles
     */
    public ArrayList<Missile> defineMissilesBoatBoard(int player) {
        if (player == 0) {
            return getBoatBoardPlayer1Missiles();
        } else if (player == 1) {
            return getBoatBoardPlayer2Missiles();
        } else {
            return null;
        }
    }

    /**
     * calculate the area needed for a ship
     * @return
     */
    public int[][] areaForShipCalc(int colindex, int rowindex) {
        int[][] caseNeeded = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                caseNeeded[i][j] = 0;
            }
        }
        caseNeeded[rowindex][colindex] = -1;
        return caseNeeded;
    }

    /**
     * check if the placement of a ship is correct
     * @param gameVariant
     * @param player
     * @param shipNumber
     * @param horizontal
     * @param y
     * @param x
     * @return boolean
     */
    public boolean checkCorrectPlacement(int gameVariant, int player, int shipNumber, boolean horizontal, int y,
            int x) {
        ArrayList<Ship> ships = defineShips(gameVariant, player);
        int[][] startShipIndex = areaForShipCalc(y, x);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (startShipIndex[i][j] == -1) {
                    if (horizontal) {
                        for (int k = 0; k < ships.get(shipNumber).getSize() - 1; k++) {
                            if (j + 1 >= 10) {
                                updateText().setText(
                                        "Invalid placement ! The ship will go out of the board on the right");
                                return false;
                            }
                            startShipIndex[i][j + 1] = -1;
                            j++;
                        }
                    } else {
                        for (int k = 0; k < ships.get(shipNumber).getSize() - 1; k++) {
                            if (i + 1 >= 10) {
                                updateText().setText(
                                        "Invalid placement ! The ship will go out of the board on the bottom");
                                return false;
                            }
                            startShipIndex[i + 1][j] = -1;
                            i++;
                        }
                    }
                }
            }
        }
        return isInValideCells(gameVariant, player, shipNumber, startShipIndex, horizontal, y, x);
    }

    /**
     * check if the placement of a ship is valid (/!\ to the inverted y and x coordinates for future utilisation due to the loops and grid)
     * @param gameVariant
     * @param player
     * @param shipNumber
     * @param shipFutureCoordinates
     * @param horizontal
     * @param y
     * @param x
     * @return boolean
     */
    public boolean isInValideCells(int gameVariant, int player, int shipNumber, int[][] shipFutureCoordinates,
            boolean horizontal, int y, int x) {
        int[][] availableCoordinates = calcValidCells(player);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                // side checks
                if (j - 1 >= 0) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i][j - 1] == 1) {
                        availableCoordinates[i][j - 1] = 0;
                    }
                }
                if (j + 1 <= 9) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i][j + 1] == 1) {
                        availableCoordinates[i][j + 1] = 0;
                    }
                }
                if (i - 1 >= 0) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i - 1][j] == 1) {
                        availableCoordinates[i - 1][j] = 0;
                    }
                }
                if (i + 1 <= 9) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i + 1][j] == 1) {
                        availableCoordinates[i + 1][j] = 0;
                    }
                }
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i - 1][j - 1] == 1) {
                        availableCoordinates[i - 1][j - 1] = 0;
                    }
                }
                // corners check
                if (i - 1 >= 0 && j + 1 <= 9) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i - 1][j + 1] == 1) {
                        availableCoordinates[i - 1][j + 1] = 0;
                    }
                }
                if (i + 1 <= 8 && j - 1 >= 0) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i + 1][j - 1] == 1) {
                        availableCoordinates[i + 1][j - 1] = 0;
                    }
                }
                if (i + 1 <= 9 && j + 1 <= 8) {
                    if (availableCoordinates[i][j] == -1 && availableCoordinates[i + 1][j + 1] == 1) {
                        availableCoordinates[i + 1][j + 1] = 0;
                    }
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (availableCoordinates[i][j] == 0) {
                    availableCoordinates[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (availableCoordinates[i][j] == shipFutureCoordinates[i][j]) {
                    TextElement log = updateText();
                    log.setText("Wrong placement ! A ship cannot be in collision with another ship or in a corner");
                    return false;
                }
            }
        }
        addShipAfterCheck(gameVariant, player, shipNumber, horizontal, y, x);
        return true;
    }

    /**
     * calculate the valid cells for a player
     * @param player
     * @return int[][]
     */
    public int[][] calcValidCells(int player) {
        int[][] validCells = new int[10][10];
        BattleShipBoard board = defineBoatBoard(player);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board.isEmptyAt(i, j)) {
                    validCells[i][j] = 1;
                } else {
                    validCells[i][j] = -1;
                }
            }
        }
        return validCells;
    }

    /**
     * add a ship after the check of the placement
     * @param gameVariant
     * @param player
     * @param shipNumber
     * @param horizontal
     * @param y
     * @param x
     */
    public void addShipAfterCheck(int gameVariant, int player, int shipNumber, boolean horizontal, int y, int x) {
        BattleShipBoard board = defineBoatBoard(player);
        ArrayList<Ship> ships = defineShips(gameVariant, player);
        board.placeShipOnBoard(ships.get(shipNumber), x, y, horizontal);
        addBoatNumber(model.getIdPlayer());
    }

    /**
     * launch a missile on the board
     * @param player
     * @param missileIndex
     * @param posX
     * @param posY
     * @return
     */
    public String launchMissile(int player, int missileIndex, int posX, int posY) {
        if (areaAlreadyHit(player, posX, posY)) {
            return "You have already hit this area!";
        }

        BattleShipBoard attackBoard = defineAttackBoard(player);
        ArrayList<Missile> missiles = defineMissiles(player);
        BattleShipBoard eniBoard = defineEniBoard(player);
        ArrayList<Missile> missilesBoatBoard = defineMissilesBoatBoard(player);

        return handleMissileLaunch(player, missileIndex, posX, posY, attackBoard, missiles, eniBoard, missilesBoatBoard);
    }

    /**
     * handle the missile launch by checking if the missile hit or missed
     * @param player
     * @param missileIndex
     * @param posX
     * @param posY
     * @param attackBoard
     * @param missiles
     * @param eniBoard
     * @param missilesBoatBoard
     * @return
     */
    private String handleMissileLaunch(int player, int missileIndex, int posX, int posY,
                                       BattleShipBoard attackBoard, ArrayList<Missile> missiles,
                                       BattleShipBoard eniBoard, ArrayList<Missile> missilesBoatBoard) {
        if (eniBoard.isEmptyAt(posX, posY)) {
            return processMissedShot(player, missileIndex, posX, posY, attackBoard, missiles, eniBoard, missilesBoatBoard);
        } else {
            return processHitShot(player, missileIndex, posX, posY, attackBoard, missiles, eniBoard);
        }
    }

    /**
     * case of a missed shot
     * @param player
     * @param missileIndex
     * @param posX
     * @param posY
     * @param attackBoard
     * @param missiles
     * @param eniBoard
     * @param missilesBoatBoard
     * @return
     */
    private String processMissedShot(int player, int missileIndex, int posX, int posY,
                                     BattleShipBoard attackBoard, ArrayList<Missile> missiles,
                                     BattleShipBoard eniBoard, ArrayList<Missile> missilesBoatBoard) {
        Missile missile = missiles.get(missileIndex);
        missile.setColor(0);
        missile.addChangeFaceEvent();
        attackBoard.placeMissileOnBoard(missile, posX, posY);
        addMissileNumber(player);

        Missile missileBoatBoard = missilesBoatBoard.get(missileIndex);
        eniBoard.placeMissileOnBoard(missileBoatBoard, posX, posY);


        updateLogText("Missed !");
        return "Missed !";
    }

    /**
     * case of a hit shot
     * @param player
     * @param missileIndex
     * @param posX
     * @param posY
     * @param attackBoard
     * @param missiles
     * @param eniBoard
     * @return
     */
    private String processHitShot(int player, int missileIndex, int posX, int posY,
                                  BattleShipBoard attackBoard, ArrayList<Missile> missiles,
                                  BattleShipBoard eniBoard) {
        Missile missile = missiles.get(missileIndex);
        missile.setColor(1);
        attackBoard.placeMissileOnBoard(missile, posX, posY);

        ShipParts sp = (ShipParts) eniBoard.getElement(posX, posY);
        sp.setDestroyed(true);
        sp.addChangeFaceEvent();
        addMissileNumber(player);

        if (sp.getBelongingShip().isShipDestroyed()) {
            updateLogText("You have sunk a ship!");
            incrementNumberShipsSunk(player);
            incrementNumberHits(player);
            return "Your " + sp.getBelongingShip().getName() + " has been sunk!";
        } else {
            updateLogText("You have hit a ship!");
            incrementNumberHits(player);
            return "hit on " + sp.getBelongingShip().getName();
        }
    }

    /**
     * update the log text for the game
     * @param text
     */
    private void updateLogText(String text) {
        this.logText = text;
        TextElement logTextElement = updateText();
        logTextElement.setText(logText);
    }

    /**
     * check if the area has already been hit
     * @param player
     * @param posX
     * @param posY
     * @return
     */
    public boolean areaAlreadyHit(int player, int posX, int posY) {
        BattleShipBoard boatBoard = defineAttackBoard(player);
        return boatBoard.isElementAt(posX, posY);
    }

    /**
     * Increment the number of ships sunk
     * @param player
     */
    public void incrementNumberShipsSunk(int player) {
        if (player == 1) {
            destroyedShipsPlayer1++;
        } else {
            destroyedShipsPlayer2++;
        }
    }

    /**
     * Increment the number of hits
     * @param player
     */
    public void incrementNumberHits(int player) {
        if (player == 1) {
            hitsPlayer1++;
        } else {
            hitsPlayer2++;
        }
    }

    // End game methods

    /**
     * Check if the game is over
     * @return
     */
    public boolean isEndOfTheGame() {
        return shipsRemainingPlayer1() || shipsRemainingPlayer2();
    }

    /**
     * Calculate the winner of the game
     * @return
     */
    public int calcWinner() {
        if (destroyedShipsPlayer1 > destroyedShipsPlayer2) {
            return 0;
        } else if (destroyedShipsPlayer1 < destroyedShipsPlayer2) {
            return 1;
        } else {
            return calcWinnerCaseEqual();
        }
    }

    /**
     * Calculate the winner in case of equality
     * @return
     */
    public int calcWinnerCaseEqual() {
        if (destroyedShipsPlayer1 > destroyedShipsPlayer2) {
            return 0;
        } else if (destroyedShipsPlayer1 < destroyedShipsPlayer2) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Display the results of the game
     */
    public void displayResults() {
        StageModel stageModel = (StageModel) model.getGameStage();

        if (stageModel.shipsRemainingPlayer2()) {
            model.setIdWinner(0);
        } else if (stageModel.shipsRemainingPlayer1()) {
            model.setIdWinner(1);
        } else {
            int winner = calcWinner();
            updateWinnerAndNextPlayer(winner);
        }

        model.stopGame();
    }

    /**
     * Update the winner and the next player in case of equality
     * @param winner
     */
    private void updateWinnerAndNextPlayer(int winner) {
        if (winner == 0) {
            if (model.getIdPlayer() == 1) {
                model.setNextPlayer();
            }
            model.setIdWinner(0);
        } else if (winner == 1) {
            if (model.getIdPlayer() == 0) {
                model.setNextPlayer();
            }
            model.setIdWinner(1);
        } else {
            model.setIdWinner(-1);
        }
    }


}
