package space.readingrainbow.tychos.simulator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
class Orbit extends Group {
    double radius;

//    double velocity;
//    Point3D pivot;
//    Point3D tilt;

    CelestialPoint primary;

    Color color;
    Circle circle;

    Rotate rotateX, rotateY;

    @Builder
    public Orbit(double radius,
                 double centerA, double centerB, double centerC,
                 double tiltA, double tiltB,
                 Color color, CelestialPoint primary) {
        this.radius = radius;
        this.color = color;
        this.primary = primary;

        Translate t = new Translate();
        t.setX(radius);
        getTransforms().add(t);
        // must be the origin, so we set at (0, 0, 0)
        if (primary == null) {
            Point3D parentOriginInLocal = parentToLocal(0, 0, 0);
            Point3D localOriginInParent = localToParent(0, 0, 0);
            System.out.println("primary is null: " + parentOriginInLocal);
            System.out.println("primary is null! " + localOriginInParent);
            setTranslateX(parentOriginInLocal.getX());
            setTranslateY(parentOriginInLocal.getY());
            setTranslateZ(parentOriginInLocal.getZ());
        }

        var xRotate = new Rotate(tiltA * (Math.PI/180), Rotate.X_AXIS);
        var yRotate = new Rotate(tiltB * (Math.PI/180), Rotate.Y_AXIS);
        getTransforms().addAll(xRotate, yRotate);
        setTranslateX(centerA);
        setTranslateZ(centerB);
        setTranslateY(centerC);
        if(radius > 0 && color != null) {
            circle = new Circle(radius);
            circle.setStroke(color);
            circle.setFill(Color.TRANSPARENT);
            getChildren().add(circle);
        }
    }

    public Point3D center() {
        return null;
    }

}
