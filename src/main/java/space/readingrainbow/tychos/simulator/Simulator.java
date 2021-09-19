package space.readingrainbow.tychos.simulator;

import javafx.geometry.Point3D;
import javafx.scene.*;
import lombok.Getter;
import space.readingrainbow.tychos.simulator.stars.Constellation;

import java.util.List;

public class Simulator {
    Point3D origin;
    Barycenter barycenter;
    Earth earth;
    Luna luna;
    List<Constellation> constellations;

    Group group;
    @Getter Scene scene;

    public Simulator() {
        origin = new Point3D(0, 0, 0);

        barycenter = new Barycenter(origin);
        earth = new Earth(barycenter);
        luna = new Luna(earth);

        group = new Group();
        group.getChildren().add(barycenter);
        group.getChildren().add(earth);
        group.getChildren().add(luna);

        scene = new Scene(group, 500, 500);

        Camera camera = new PerspectiveCamera();
        scene.setCamera(camera);

    }

    void initConstellations() {
    }

}
