package space.readingrainbow.tychos.simulator;

import javafx.geometry.Point3D;
import lombok.Getter;

abstract class Orbit {
    @Getter CelestialBody orbiting;
    @Getter Point3D currentCenter;

    Orbit(CelestialBody orbitingBody) {
        this.orbiting = orbitingBody;
        currentCenter = orbitingBody.currentPosition();
    }

    abstract double radius();

    double tiltA() {
        return 0;
    }
    double tiltB() {
        return 0;
    }
}
