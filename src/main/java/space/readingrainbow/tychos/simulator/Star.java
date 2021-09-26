package space.readingrainbow.tychos.simulator;

import javafx.scene.Group;
import lombok.Builder;
import javafx.scene.effect.Light;

public class Star extends Group {
    double rightAscension;
    double declination;
    double magnitude;
//    double hours;
//    double minutes;
//    double seconds;

    Light light;

    Star(double ra, double dec, double m) {
        rightAscension = ra;
        declination = dec;
        magnitude = m;


    }
}
