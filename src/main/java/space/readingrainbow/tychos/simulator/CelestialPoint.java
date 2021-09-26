package space.readingrainbow.tychos.simulator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import lombok.Builder;
import lombok.Getter;

public class CelestialPoint extends Group {
    double startPosition;
    double rotationalSpeed;
    double orbitalSpeed;
//    Point3D tilt;

    AxesMarker marker;
    @Getter Orbit orbit;
    Body body;

//    Rotate rotateX, rotateZ;
    Rotate orbitRotation;

    @Getter DoubleProperty state;
    @Getter BooleanProperty visible;

    @Builder
    public CelestialPoint(String name, double startPosition,
                          double rotationalSpeed, double orbitalSpeed,
                          double tiltA, double tiltB, double markerLength,
                          Orbit orbit, Body body) {
        setId(name);
        this.startPosition = startPosition;
        this.rotationalSpeed = rotationalSpeed;
        this.orbitalSpeed = orbitalSpeed;
        this.orbit = orbit;

        state = new SimpleDoubleProperty(0);
        visible = new SimpleBooleanProperty(true);

//        rotateZ = new Rotate(tiltA * (Math.PI / 180), Rotate.Z_AXIS);
//        rotateX = new Rotate(tiltB * (Math.PI / 180), Rotate.X_AXIS);
//        getTransforms().addAll(rotateZ, rotateX);
        setRotationAxis(new Point3D(tiltB * (Math.PI / 180), 0, tiltA * (Math.PI / 180)));
        setRotate(rotationalSpeed * state.getValue());

        if (markerLength > 0) {
            marker = new AxesMarker(markerLength);
            getChildren().add(marker);
        }

        if (orbit != null) {
            setTranslateX(orbit.radius());

//            orbitRotation = new Rotate(orbitalSpeed * state.getValue() - startPosition * (Math.PI / 180), -orbit.radius(), 0, 0, orbi
//                    )
            orbit.setRotate(orbitalSpeed * state.getValue() - startPosition * (Math.PI / 180));
            getChildren().add(orbit);
        }


        Point3D parentInLocal = parentToLocal(0, 0, 0);
        System.out.println("CelestialPoint(): parentInLocal = " + parentInLocal);
        if (body != null) {
            this.body = body;
            getChildren().add(body);
        }
        state.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("TEST!!!!!!!" + oldValue + " | " + " " + newValue);
            }
        });


    }

}
