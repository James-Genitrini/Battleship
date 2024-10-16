package view;

import boardifier.view.RootPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class BattleShipRootPane extends RootPane {

    /**
     * Constructor of the BattleShipRootPane creating a new root pane for the game calling the constructor of the parent class
     */
    public BattleShipRootPane() {
        super();
    }

    /**
     * Method to create the default group of the root pane
     */
    @Override
    public void createDefaultGroup() {
        Rectangle frame = new Rectangle(1190, 710, Color.LIGHTGREY);
        frame.setFill(new ImagePattern(new Image("https://i.ibb.co/Qmjq23r/battle-Ship.png")));

        Text text = new Text("Playing to The BattleShip");
        text.setFont(new Font(30));
        text.setFill(Color.BLACK);
        text.setX((double) 1050 / 2 - text.getLayoutBounds().getWidth() / 2);
        text.setY((double) 600 / 2 - text.getLayoutBounds().getHeight() / 2);

        // Background music
        // Try to play the music else juste continue
        try {
            String musicFile = "BattleShip/src/resources/back_music.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Media Error: " + e);
        }


        group.getChildren().clear();
        group.getChildren().addAll(frame, text);
    }
}