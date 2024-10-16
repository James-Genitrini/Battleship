package control;

import boardifier.control.Controller;
import boardifier.control.ControllerMouse;
import boardifier.model.Coord2D;
import boardifier.model.GameElement;
import boardifier.model.Model;
import boardifier.model.TextElement;
import boardifier.view.GridLook;
import boardifier.view.View;
import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import model.BattleShipBoard;
import model.StageModel;

import java.util.List;
import java.util.Optional;

public class BattleShipControllerMouse extends ControllerMouse implements EventHandler<MouseEvent> {
    public BattleShipControllerMouse(Model model, View view, Controller control) {
        super(model, view, control);
    }

    /**
     * Define the board to use depending on the state of the game
     *
     * @return the board to use
     */
    public BattleShipBoard defineBoard() {
        StageModel stageModel = (StageModel) model.getGameStage();
        int playerId = model.getIdPlayer();
        int stageState = stageModel.getState();

        if (playerId == 0) {
            return getBoardForPlayer1(stageModel, stageState);
        } else if (playerId == 1) {
            return getBoardForPlayer2(stageModel, stageState);
        }
        return null;
    }

    /**
     * Get the board for player 1 depending of the phase
     *
     * @param stageModel the stage model
     * @param stageState the state of the stage
     * @return the board for player 1
     */
    private BattleShipBoard getBoardForPlayer1(StageModel stageModel, int stageState) {
        if (stageState == StageModel.STATE_PLACEMENT) {
            return stageModel.getBoatBoardPlayer1();
        } else {
            return stageModel.getAttackBoardPlayer1();
        }
    }

    /**
     * Get the board for player 2 depending of the phase
     *
     * @param stageModel the stage model
     * @param stageState the state of the stage
     * @return the board for player 2
     */
    private BattleShipBoard getBoardForPlayer2(StageModel stageModel, int stageState) {
        if (stageState == StageModel.STATE_PLACEMENT) {
            return stageModel.getBoatBoardPlayer2();
        } else {
            return stageModel.getAttackBoardPlayer2();
        }
    }


    @Override
    public void handle(MouseEvent event) {
        StageModel stageModel = (StageModel) model.getGameStage();

        BattleShipBoard board = defineBoard();

        // check if mouse event is captured
        if (!model.isCaptureMouseEvent())
            return;

        // get the click x,y if exists
        Coord2D clic = new Coord2D(event.getSceneX(), event.getSceneY());
        // get elements at that position
        List<GameElement> list = control.elementsAt(clic);

        // check the state of the game
        if (stageModel.getState() == StageModel.STATE_PLACEMENT) {
            System.out.println("Placement");
            GridLook lookBoard = (GridLook) control.getElementLook(board);
            int[] dest = lookBoard.getCellFromSceneLocation(clic);
            int x = dest[0];
            int y = dest[1];

            // Alert to ask placement
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Placement choice");
            alert.setHeaderText("Make your choice");
            alert.setContentText("Pick an origin point, then the boat will be placed horizontally at the right or vertically at the bottom.");
            alert.setGraphic(null);
            // Link css
            alert.getDialogPane().getStylesheets().add(getClass().getResource("../resources/style.css").toExternalForm());
            alert.getDialogPane().getStyleClass().add("alertContinue");

            // Buttons to ask
            ButtonType buttonTypeYes = new ButtonType("Horizontal --");
            ButtonType buttonTypeNo = new ButtonType("Vertical |");

            // Set the buttons on the alert
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            boolean isHorizontal = false;
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes) {
                isHorizontal = true;
            }
            System.out.println(stageModel.getBoatNumber(model.getIdPlayer()));
            if (stageModel.checkCorrectPlacement(stageModel.getVariant(), model.getIdPlayer(),
                    stageModel.getBoatNumber(model.getIdPlayer()), isHorizontal, y, x)) {
                if (stageModel.isShipPlacementPhaseOver()) {
                    stageModel.setState(StageModel.STATE_ATTACK);
                    control.endOfTurn();
                } else if (stageModel.getBoatNumber(model.getIdPlayer()) == stageModel.getBoatNumber()) {
                    System.out.println("End of placement for player " + model.getIdPlayer());
                    control.endOfTurn();
                }
            }
            System.out.println(stageModel.getBoatNumberPlayer1());
            System.out.println(stageModel.getBoatNumber());
            System.out.println(stageModel.isShipPlacementPhaseOver());
        } else if (stageModel.getState() == StageModel.STATE_ATTACK) {
            System.out.println("Attack");
            System.out.println(model.getIdPlayer());
            GridLook lookBoard = (GridLook) control.getElementLook(board);
            System.out.println(board);
            int[] dest = lookBoard.getCellFromSceneLocation(clic);
            if (stageModel.areaAlreadyHit(model.getIdPlayer(), dest[0], dest[1])) {
                return;
            } else {
                String logText = stageModel.launchMissile(model.getIdPlayer(),
                        stageModel.getPlayerMissileNumber(model.getIdPlayer()), dest[0], dest[1]);

                model.setCaptureMouseEvent(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                // Link css
                alert.getDialogPane().getStylesheets().add(getClass().getResource("../resources/style.css").toExternalForm());
                alert.getDialogPane().getStyleClass().add("alertContinue");
                alert.setTitle("Next player !");
                alert.setHeaderText("Click OK when ready to switch to the next  (3 seconds before switching)");
                Image image = new Image("https://i.pinimg.com/originals/50/45/c7/5045c7b2ad8c5556d428cc88f28deb86.png");
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(50);
                imageView.setFitWidth(50);
                alert.setGraphic(imageView);
                alert.showAndWait();
                // Add a pause to let the player switching safely
                PauseTransition pause = new PauseTransition(Duration.seconds(2));
                pause.setOnFinished(e -> {
                    TextElement log = stageModel.updateText();
                    control.endOfTurn();
                    log.setText(logText);
                    model.setCaptureMouseEvent(true);
                });
                pause.play();
            }
        }
    }
}
