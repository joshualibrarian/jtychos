package space.readingrainbow.tychos.simulator;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lombok.Getter;
import lombok.extern.java.Log;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.logging.Level;

@Log
public class Simulator {
    public static Instant SHACK_EPOCH = Instant.parse("2000-06-20T12:00:00Z");

    CelestialConfig config;

    Group root;
    NavigableCamera camera;
    @Getter Scene scene;


    double speedFactor;
    Instant utcTime;
    @Getter DoubleProperty state;
    Timeline timeline;

    public Simulator(CelestialConfig config) {
        this.config = config;
        root = new Group();

        state = new SimpleDoubleProperty(0);

        for (CelestialPoint body : config.bodies()) {
            body.state().bind(state);
            root.getChildren().add(body);
        }

        scene = new Scene(root, 1000, 1000, Color.BLACK);
//        scene.setOnKeyPressed(event -> {
//            stepForward(100);
//        });
        camera = new NavigableCamera(scene);
//        Clock.tick(Clock.systemUTC(), Duration.ofMillis(1));


    }

    public Instant timeInUTC() {
        return null;
    }

    public void timeInUTC(Instant instantToSet) {

    }

    public void moveToDate(Instant instant) {
        for(CelestialPoint point : config.bodies()) {

        }
    }

    public void positionToDays(double position) {
//        position +=
    }

    public Instant posToTime(double position) {
        position += config.solarHour * 12;
        double days = position/config.solarDay - Math.floor(position / config.solarDay);
        double hours = Math.floor(days * 24);
        double minutes = Math.floor((days * 24 - hours) * 60);
        double seconds = Math.floor(((days * 24 - hours) * 60 - minutes) * 60);
//        LocalDateTime.of()

//        let hh = ("0" + hours).slice(-2);
//        let mm = ("0" + minutes).slice(-2);
//        let ss = ("0" + seconds).slice(-2);
        return null;
    }

    public void timeToPos(Instant value) {

    }

    public void stepForward(double value) {
        state.setValue(state.getValue() + value);
        System.out.println("test");
        System.out.println(state.get());
        log.log(Level.ALL, "stepForward()!");
    }

    public void play() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(100), event -> {
                    state.setValue(state.getValue() + 10);
                }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
