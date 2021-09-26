package space.readingrainbow.tychos.test;

import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import space.readingrainbow.tychos.simulator.AxesMarker;

public class TestCamera extends Application {
    Group root = new Group();
    AxesMarker marker = new AxesMarker(200);
    Camera camera = new PerspectiveCamera(true);
    Rotate rotation;

    @Override
    public void start(Stage primaryStage) throws Exception {

        root.getChildren().add(marker);

        Scene scene = new Scene(root, 800, 800, Color.BLACK);

        // this "backs you off" from the origin by 1000
        camera.setTranslateZ(-1000);

//        camera.setTranslateY(-200);
//        camera.getTransforms().add(new Rotate(45, 0, 0, 0, Rotate.Y_AXIS));

        // this is the crucial part here, where you set the pivot point
        // of your rotation, in this case 1000 "in front" of you
        // (these are "local coordinates" to the camera)
        rotation = new Rotate(0, 0, 0, 1000, new Point3D(10, 5, 1));
        camera.getTransforms().add(rotation);

        camera.setNearClip(0.01);
        camera.setFarClip(10000);
        scene.setCamera(camera);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            switch(event.getCode()) {
                case LEFT:
                    rotation.setAngle(rotation.getAngle() - 10);
                    break;
                case RIGHT:
                    rotation.setAngle(rotation.getAngle() + 10);
                    break;
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}

