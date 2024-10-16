package view;

import boardifier.model.GameElement;
import boardifier.view.ElementLook;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.Missile;

public class MissileLook extends ElementLook {
    private Circle circle;
    private int radius;

    /**
     * Constructor of the MissileLook
     *
     * @param element the game element
     */
    public MissileLook(GameElement element) {
        super(element);
    }

    @Override
    public void onFaceChange() {
        clearGroup();
        render();
    }

    /**
     * render of the missile depending on the color
     */
    protected void render() {

        Missile missile = (Missile) element;
        Circle circle = new Circle(5);
        if (missile.getColor() == 1) {
            circle.setFill(Color.BLACK);
            addShape(circle);
        } else if (missile.getColor() == 0) {
            circle.setFill(Color.WHITE);
            addShape(circle);
        } else {
            circle.setFill(Color.RED);
            addShape(circle);
        }

    }


}
