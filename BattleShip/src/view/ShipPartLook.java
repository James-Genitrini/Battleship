package view;

import boardifier.model.GameElement;
import boardifier.view.ElementLook;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.ShipParts;

public class ShipPartLook extends ElementLook {
    private int radius;

    /**
     * Constructor
     * 
     * @param element the element
     */
    public ShipPartLook(GameElement element) {
        super(element, 1);

        render();
    }

    @Override
    public void onFaceChange() {
        clearGroup();
        render();
    }

    /**
     * render of the ship part depending on the color and if it is destroyed or not
     */
    protected void render() {

        ShipParts shipParts = (ShipParts) element;
        Circle circle = new Circle(10);
        if (shipParts.getIsDestroyed()) {
            if (shipParts.getColor() == 1) {
                circle.setFill(Color.RED);
                addShape(circle);
            } else {
                circle.setFill(Color.RED);
                addShape(circle);
            }
        } else if (shipParts.getColor() == 1) {
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            addShape(circle);
        } else {
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.RED);
            addShape(circle);
        }
    }
}
