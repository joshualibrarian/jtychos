package space.readingrainbow.tychos.simulator;

import javafx.scene.paint.Color;
import lombok.Getter;

public class Luna extends CelestialBody {
    @Getter Color color = Color.web("0x8b8b8b");
    @Getter double size = 1;
    @Getter double startPosition = 260.8;
    @Getter double orbitalSpeed = 83.28517;
    @Getter double rotationalSpeed = 0;

    Luna(CelestialBody orbiting) {
        super(new LunaOrbit(orbiting));
    }

    static class LunaOrbit extends Orbit {
        @Getter double radius = 10;
        @Getter double tiltA = -1.8;
        @Getter double tiltB = -2.6;

        LunaOrbit(CelestialBody orbiting) {
            super(orbiting);
        }
    }
}


//        tilt: 0,
//        orbitCentera: 0.4,
//        orbitCenterb: -0.9,
//        orbitCenterc: 0,

//
//        textureUrl: 'https://raw.githubusercontent.com/pholmq/tsnova-resources/master/Moon.jpg',
//        visible: true,
//        containerObj:"",
//        orbitObj:"",
//        planetObj:"",
//        pivotObj:"",
//        axisHelper: true,
//
//        traceLength : sYear * 18,
//        traceStep : sDay,
//        traceOn: false,
//        traceLine: false,
//        traceStartPos : 0,
//        traceCurrPos : 0,
//        traceArrIndex : 0,
//        };