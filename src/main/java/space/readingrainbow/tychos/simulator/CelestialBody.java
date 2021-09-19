package space.readingrainbow.tychos.simulator;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import lombok.Getter;

public abstract class CelestialBody extends Sphere {
    @Getter Point3D currentPosition;
    @Getter Orbit orbit;    // null for bodies which do not orbit

    CelestialBody(Orbit orbit) {
        this.orbit = orbit;
    }

    abstract public double size();
    abstract public double orbitalSpeed();
    abstract public double rotationalSpeed ();
    abstract public double startPosition();

    public String name() {
        return this.getClass().getSimpleName();
    }

    double tilt() {
        return 0;
    }
    double tiltB() {
        return 0;
    }
    Color color() {
        return null;
    }
}
