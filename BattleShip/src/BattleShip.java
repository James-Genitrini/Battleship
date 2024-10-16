import boardifier.control.Logger;
import control.BattleShipController;
import boardifier.control.StageFactory;
import boardifier.model.Model;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.BattleShipRootPane;
import view.BattleShipView;

public class BattleShip extends Application {

    public static void main(String[] args) {
        launch();
    }

    /**
     * Start the application
     *
     * @param stage the stage
     */
    @Override
    public void start(Stage stage) {

        Logger.setLevel(Logger.LOGGER_DEBUG);

        // Set the icon of the game
        stage.getIcons().add(new Image("https://risibank.fr/cache/medias/0/25/2595/259540/full.png"));

        Model model = new Model();
        StageFactory.registerModelAndView("BattleShip", "model.StageModel", "view.BattleShipStageView");
        BattleShipRootPane rootPane = new BattleShipRootPane();
        BattleShipView view = new BattleShipView(model, stage, rootPane);
        BattleShipController control = new BattleShipController(model, view);

        stage.setTitle("BattleShip - The Gameuuu");
        control.setFirstStageName("BattleShip");
        stage.setScene(rootPane.getScene());
        stage.show();
    }
}
