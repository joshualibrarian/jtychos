package space.readingrainbow.tychos.simulator;

import javafx.scene.Group;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import lombok.Getter;

@Getter
public class Body extends Group {
    private double size;
    private Color color;

    private PhongMaterial material;
    private Sphere sphere;

    private Circle rings = null;
    private Light light = null;

    public Body(double size, Color color) {
        this.size = size;
        this.color = color;

        sphere = new Sphere(size);

        material = new PhongMaterial(color);
        sphere.setMaterial(material);

        getChildren().add(sphere);
    }
}
