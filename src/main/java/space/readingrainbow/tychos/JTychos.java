package space.readingrainbow.tychos;

import javafx.application.Application;
import javafx.stage.Stage;
import space.readingrainbow.tychos.simulator.CelestialConfig;
import space.readingrainbow.tychos.simulator.CelestialException;
import space.readingrainbow.tychos.simulator.Simulator;

import java.io.IOException;
import java.io.InputStream;

public class JTychos extends Application {

    @Override
    public void start(Stage stage) throws IOException, CelestialException {
        stage.setTitle("TYCHOS Simulator");
        InputStream s = getClass().getResourceAsStream("celestial.config.yaml");

        CelestialConfig config = new CelestialConfig(s);
        Simulator simulation = new Simulator(config);

        stage.setScene(simulation.scene());
        stage.show();

//        simulation.play();
    }

    public static void main(String[] args) {
        launch();
    }
}