package space.readingrainbow.tychos.simulator;

import javafx.geometry.Point3D;
import lombok.Getter;

public class Barycenter extends CelestialBody {
    @Getter double size = 0;
    @Getter double rotationalSpeed = 0;
    @Getter double startPosition = 0;
    @Getter double orbitalSpeed = 0;
    @Getter double orbitRadius = 0;

    Barycenter(Point3D location) {
        super(null);
        this.currentPosition = location;
    }

}
