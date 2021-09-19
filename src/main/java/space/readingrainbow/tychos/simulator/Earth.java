package space.readingrainbow.tychos.simulator;

import javafx.geometry.Point3D;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import lombok.Getter;

public class Earth extends CelestialBody {
    public static double EARTH_ROTATIONS = 366.2425;

    @Getter Color color = Color.web("0x578B7C");
    @Getter double size = 4;
    @Getter double tiltA = -23.439062;
    @Getter double tiltB = 0.26;
    @Getter double startPosition = 0;
    @Getter double orbitalSpeed = -Math.PI*2/25344;
    @Getter double rotationalSpeed = Math.PI*2* EARTH_ROTATIONS;
//                        "rotationSpeed": 2301.1694948647196,
//                        "speed": -0.0002479160869310127,

    @Getter public int sphereSegments = 320;

    Earth(CelestialBody orbiting) {
        super(new EarthOrbit(orbiting));

        this.setRadius(size);
        this.setMaterial(new PhongMaterial(color()));
    }

    static class EarthOrbit extends Orbit {
        @Getter double radius = 37.8453;

        @Getter Point3D currentCenter;
        EarthOrbit(CelestialBody orbiting) {
            super(orbiting);
        }

    }

 }