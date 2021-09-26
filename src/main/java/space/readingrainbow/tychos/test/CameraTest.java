package space.readingrainbow.tychos.test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import space.readingrainbow.tychos.simulator.AxesMarker;
import space.readingrainbow.tychos.simulator.NavigableCamera;

public class CameraTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        root.getChildren().add(new AxesMarker(200));
        Scene scene = new Scene(root, 800, 800, Color.BLACK);
        NavigableCamera camera = new NavigableCamera(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
