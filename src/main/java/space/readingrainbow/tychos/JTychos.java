package space.readingrainbow.tychos;

import javafx.application.Application;
import javafx.stage.Stage;
import space.readingrainbow.tychos.simulator.Simulator;

import java.io.IOException;

public class JTychos extends Application {

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(JTychos.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
        var sim = new Simulator();

        stage.setScene(sim.scene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}