package view;

import boardifier.view.RootPane;
import boardifier.view.View;
import boardifier.model.Model;
import boardifier.model.Player;
import javafx.animation.PauseTransition;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import java.util.List;
import java.util.Objects;

import model.*;

public class BattleShipView extends View {

    // menu items for menu bar
    private MenuItem menuNewGame;
    private MenuItem menuIntro;
    private MenuItem menuQuit;

    /**
     * Constructor
     * 
     * @param model    the model
     * @param stage    the stage
     * @param rootPane the root pane
     */
    public BattleShipView(Model model, Stage stage, RootPane rootPane) {
        super(model, stage, rootPane);
    }

    /**
     * Create the menu bar
     */
    @Override
    protected void createMenuBar() {
        menuBar = new MenuBar();
        Menu menuGame = new Menu("Menu");
        menuNewGame = new MenuItem("New Game");
        menuIntro = new MenuItem("Introduction");
        menuQuit = new MenuItem("Quit");

        menuGame.getItems().add(menuNewGame);
        menuGame.getItems().add(menuIntro);
        menuGame.getItems().add(menuQuit);

        menuBar.getMenus().add(menuGame);
        // Link css
        menuBar.getStylesheets()
                .add(Objects.requireNonNull(getClass().getResource("../resources/style.css")).toExternalForm());
        menuBar.getStyleClass().add("menuBar");
        menuGame.getStyleClass().add("menu");
        menuNewGame.getStyleClass().add("menuItem");
        menuIntro.getStyleClass().add("menuItem");
        menuQuit.getStyleClass().add("menuItem");
    }

    /**
     * Get the menu item for new game
     *
     * @return the menu item for new game
     */
    public MenuItem getMenuNewGame() {
        return menuNewGame;
    }

    /**
     * Get the menu item for introduction
     *
     * @return the menu item for introduction
     */
    public MenuItem getMenuIntro() {
        return menuIntro;
    }

    /**
     * Get the menu item for quit
     *
     * @return the menu item for quit
     */
    public MenuItem getMenuQuit() {
        return menuQuit;
    }

    /**
     * Show the introduction
     */
    public void gameSetupPopup() {
        StageModel stageModel = (StageModel) model.getGameStage();
        // Hide all the boards
        stageModel.hideBoatBoardPlayer1();
        stageModel.hideAttackBoardPlayer1();
        stageModel.hideBoatBoardPlayer2();
        stageModel.hideAttackBoardPlayer2();

        // Create an alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        // Set the text, title, comportemebt and remove the image
        alert.setTitle("Game Setup");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setResizable(false);

        // Set the buttons
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Gridpane for the content
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Dropdown for game variant selection
        Label variantLabel = new Label("Pick a variant :");
        ComboBox<Integer> variantComboBox = new ComboBox<>();
        variantComboBox.getItems().addAll(1, 2);
        variantComboBox.setValue(1);

        // Text fields for player names - player 1
        Label player1Label = new Label("First player name:");
        TextField player1TextField = new TextField("Jean");
        player1TextField.setPromptText("Enter the name");

        // Text fields for player names - player 2
        Label player2Label = new Label("Second player name:");
        TextField player2TextField = new TextField("Clanche");
        player2TextField.setPromptText("Enter the name");


        // Text field for number of missiles
        Label missilesLabel = new Label("Number of Missiles (between 35 and 100):");
        TextField missilesTextField = new TextField("35");

        // Set the default number of missiles based on the variant
        variantComboBox.setOnAction(e -> {
            switch (variantComboBox.getValue()) {
                case 1:
                    missilesTextField.setText("35");
                    break;
                case 2:
                    missilesTextField.setText("50");
                    break;
            }
        });

        // Validate the number of missiles or show an error
        missilesTextField.textProperty().addListener((base, baseVal, newVal) -> {
            try {
                int missiles = Integer.parseInt(newVal);
                int min = variantComboBox.getValue() == 1 ? 35 : 50;
                try {
                    if (missiles < min || missiles > 100) {
                        missilesTextField.setStyle("-fx-border-color: red;");
                        missilesLabel.setText("BETWEEN 35 AND 100 MISSILES!!!");
                        missiles = 30;
                    } else {
                        missilesTextField.setStyle(null);
                        missilesLabel.setText("It's ok (OwO)");
                        PauseTransition pause = new PauseTransition(javafx.util.Duration.seconds(1));
                        pause.setOnFinished(event -> missilesLabel.setText("Number of Missiles (between 35 and 100):"));
                        pause.play();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } catch (NumberFormatException ex) {
                missilesTextField.setStyle("-fx-border-color: red;");
                missilesLabel.setText("Enter a number!!!");
            }
        });

        Label modeLabel = new Label("Game Mode:");
        ComboBox<String> modeComboBox = new ComboBox<>();
        modeComboBox.getItems().addAll("Player vs Player", "Player vs AI", "AI vs AI");
        // Default value
        modeComboBox.setValue("Player vs Player");

        modeComboBox.setOnAction(e -> {
            String player1Name = "Jean";
            String player2Name = "Clanche";

            String selectedMode = modeComboBox.getValue();
            if ("Player vs AI".equals(selectedMode)) {
                player2Name = "AI";
            } else if ("AI vs AI".equals(selectedMode)) {
                player1Name = "AI 1";
                player2Name = "AI 2";
            }

            player1TextField.setText(player1Name);
            player2TextField.setText(player2Name);
        });

        //
        Label difficultyLabel = new Label("Select an AI");
        ComboBox<Integer> difficultyComboBox = new ComboBox<>();
        difficultyComboBox.getItems().addAll(1, 2, 34909);
        difficultyComboBox.setValue(1); // Set default value
        difficultyComboBox.setDisable(true); // Still wiating for implementation

        modeComboBox.setOnAction(e -> {
            String selectedMode = modeComboBox.getValue();
            difficultyComboBox.setDisable(!"Player vs AI".equals(selectedMode) && !"AI vs AI".equals(selectedMode));
        });

        // Add controls to the grid
        grid.add(variantLabel, 0, 2);
        grid.add(variantComboBox, 1, 2);
        grid.add(player1Label, 0, 0);
        grid.add(player2Label, 0, 1);
        grid.add(player1TextField, 1, 0);
        grid.add(player2TextField, 1, 1);
        grid.add(missilesLabel, 0, 3);
        grid.add(missilesTextField, 1, 3);
        grid.add(modeLabel, 0, 4);
        grid.add(modeComboBox, 1, 4);
        grid.add(difficultyLabel, 0, 5);
        grid.add(difficultyComboBox, 1, 5);

        // Set the grid to the dialog
        alert.getDialogPane().setContent(grid);

        // Show the dialog and wait for the user's response
        alert.getDialogPane().getStylesheets()
                .add(Objects.requireNonNull(getClass().getResource("../resources/style.css")).toExternalForm());
        alert.getDialogPane().getStyleClass().add("myDialog");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String player1Name = player1TextField.getText();
                String player2Name = player2TextField.getText();
                int variant = variantComboBox.getValue();
                stageModel.setVariant(variant);
                if (variant == 1) {
                    stageModel.setBoatNumber(5);
                } else if (variant == 2) {
                    stageModel.setBoatNumber(10);
                }
                int number = Integer.parseInt(missilesTextField.getText());
                stageModel.setMissileLimit(number);
                String mode = modeComboBox.getValue();
                List<Player> players = model.getPlayers();
                players.clear();
                if (Objects.equals(mode, "AI vs AI")) {
                    model.addComputerPlayer(player1Name);
                    model.addComputerPlayer(player2Name);
                } else if (Objects.equals(mode, "Player vs AI")) {
                    model.addHumanPlayer(player1Name);
                    model.addComputerPlayer(player2Name);
                } else if (Objects.equals(mode, "Player vs Player")) {
                    model.addHumanPlayer(player1Name);
                    model.addHumanPlayer(player2Name);
                }
                int difficulty = difficultyComboBox.getValue();
                for (Player player : players) {
                    System.out.println(player);
                }
                System.out.println("Player 1 Name: " + player1Name);
                System.out.println("Player 2 Name: " + player2Name);
                System.out.println("Game Variant: " + variant);
                System.out.println("Number of Missiles: " + number);
                System.out.println("Mode : " + mode);
                if (!difficultyComboBox.isDisabled()) {
                    System.out.println("AI: " + difficulty);
                }
            } else if (response == ButtonType.CANCEL) {
                System.out.println("Game setup cancelled");
                menuQuit.fire();
            }
        });
    }
}
