package space.readingrainbow.tychos.simulator;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

public class AxesMarker extends Group {
    Line x, y, z;

    public AxesMarker(double length) {
        x = new Line(0, 0, length, 0);
        y = new Line(0, 0, 0, length);
        z = new Line(0, 0, length, 0);
        z.getTransforms().add(new Rotate(90, 0, 0, 0, Rotate.Y_AXIS));
        x.setStroke(Color.RED);
        y.setStroke(Color.GREEN);
        z.setStroke(Color.BLUE);
        getChildren().addAll(x, y, z);
    }
}
