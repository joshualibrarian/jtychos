package space.readingrainbow.tychos.simulator;

import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;


public class NavigableCamera extends Group {
    private final Camera camera = new PerspectiveCamera(true);

    private final Rotate rx = new Rotate(0, Rotate.X_AXIS);
    private final Rotate ry = new Rotate(0, Rotate.Y_AXIS);
    private final Rotate rz = new Rotate(0, Rotate.Z_AXIS);
    public Translate t  = new Translate();
    public Translate p  = new Translate();
    public Translate ip = new Translate();
    public Rotate pivot = new Rotate();

    private double x, y, z, angleX, angleY;
    private Node pickedNode;

    public NavigableCamera(Scene scene)  {
        camera.setFarClip(6000);
        camera.setNearClip(0.01);
        getChildren().add(camera);

        //okay, sure this works and the camera ends up pointing at the origin, but "level" with it.
//        setTranslateZ(-1000);

        //but why oh why doesn't this work to end with the camera looking "down" at the origin from above?
//        setTranslateY(-500);
//        Point3D pivot = Point3D.ZERO;
//        Rotate pointDown = new Rotate(90, pivot.getX(), pivot.getY(), pivot.getZ(), Rotate.X_AXIS);
//        getTransforms().add(pointDown);

        t.setZ(-800);
        getTransforms().addAll(t, p, rx, rz, ry, ip);

        initMouseControl(scene);
        initTouchControls(scene);
        initKeyboardControl(scene);

        scene.setCamera(camera);
    }

    private void initMouseControl(Scene scene) {
        scene.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
            angleX = rx.getAngle();
            angleY = ry.getAngle();
            System.out.println("angleX: " + angleX);
            System.out.println("angleY: " + angleY);

            PickResult pickResult = event.getPickResult();
            pickedNode = pickResult.getIntersectedNode();
            double x, y, z;
//            if(pickedNode != null) {
//                x = parentToLocal(pickedNode.getBoundsInParent()).getCenterX();
//                y = parentToLocal(pickedNode.getBoundsInParent()).getCenterY();
//                z = parentToLocal(pickedNode.getBoundsInParent()).getCenterZ();
//            } else {
                Point3D p = pickResult.getIntersectedPoint();
                System.out.println(p);
                pivot(p.getX(), p.getY(), p.getZ());
//            }

        });

        scene.setOnMouseDragged(event -> {
            System.out.println("X: " + event.getSceneX());
            System.out.println("Y: " + event.getSceneY());
            rotateX(angleX - (x - event.getSceneY()));
            rotateY(angleY + x - event.getSceneX());
        });

        scene.setOnMouseDragReleased(event -> {
            pivot(0, 0, 0);
        });

        scene.setOnScroll(event -> {
//            rotateX.setAngle(rotateX.getAngle() + event.getDeltaY() / 10);
//            rotateY.setAngle(rotateY.getAngle() - event.getDeltaX() / 10);
//            setTranslateX(getTranslateX() + event.getDeltaX());
//            setTranslateY(getTranslateY() + event.getDeltaY());
            System.out.println(event.getDeltaY());

//            TranslateZ(getTranslateZ() + event.getDeltaY());
            t.setZ(t.getZ() + event.getDeltaY());
        });
    }

    private void initKeyboardControl(Scene scene) {
        scene.setOnKeyPressed(event -> {
            System.out.println("current axis x: " + rx.getAxis());
            System.out.println("current axis y: " + ry.getAxis());
            System.out.println("current axis z: " + rz.getAxis());

            KeyCode code = event.getCode();
            switch (code) {
                case SPACE:
                    translateZ(10);
//                    t.setZ(t.getZ() + 10);
                    break;
                case BACK_SPACE:
                    translateZ(-10);
                    break;
                case RIGHT:
//                  rotateX(10);
                    rotateX(10);
                    break;
                case LEFT:
                    rotateX(-10);
//                    ry.setAngle(ry.getAngle() - 5);
                    break;
                case UP:
                    rotateY(-10);
//                    rx.setAngle(rx.getAngle() + 5);
                    break;
                case DOWN:
//                    rx.setAngle(rx.getAngle() - 5);
                    rotateY(10);
                    break;
            }
        });
    }

    private void moveLocalX(double amount) {
        Point3D p = localToParent(getTranslateX() + amount, getTranslateY(), getTranslateZ());
        moveToParentPoint(p);
    }

    private void moveLocalY(double amount) {
        Point3D p = localToParent(getTranslateX(), getTranslateY() + amount, getTranslateZ());
        moveToParentPoint(p);
    }

    private void moveLocalZ(double amount) {
        Point3D p = localToParent(getTranslateX(), getTranslateY(), getTranslateZ() + amount);
        moveToParentPoint(p);
    }

    private void moveToParentPoint(Point3D p) {
        setTranslateX(p.getX());
        setTranslateY(p.getY());
        setTranslateZ(p.getZ());
    }

    private void initTouchControls(Scene scene) {
        // on an entirely side note, I really don't get why this can't just be here, but it can be a member of the class? 🤨
        // double z;
        scene.setOnZoomStarted(event -> {
            z = event.getZ();
        });


        // frankly I can't even visualize what a "scale" means in the context of a "camera"
        // what I want for "zoom" is to move the camera "forwards" or "backwards" as in towards the
        // direction it's facing and away from it, but when I have tried this, I end up moving just
        // along the Z axis, wherever I'm facing!
        scene.setOnZoom(event -> {
            if (z > 0) {
                setScaleZ(getScaleZ() + event.getZoomFactor());
            } else {
                setScaleZ(getScaleZ() - event.getZoomFactor());
            }
        });
    }

    private void translate(double x, double y, double z) {
        t.setX(x);
        t.setY(y);
        t.setZ(z);
    }

    private void translateX(double x) {
        t.setX(t.getX() + x);
    }

    private void translateY(double y) {
        t.setY(t.getY() + y);
    }

    private void translateZ(double z) {
        t.setZ(t.getZ() + z);
    }

    private void pivot(Point3D point) {
        pivot(point.getX(), point.getY(), point.getZ());
    }

    private void pivot(double x, double y, double z) {
       rx.setPivotX(x);
       rx.setPivotY(y);
       rx.setPivotZ(y);
       ry.setPivotX(x);
       ry.setPivotY(y);
       ry.setPivotZ(y);
       rz.setPivotX(x);
       rz.setPivotY(y);
       rz.setPivotZ(y);
    }

//    private void pivot(double x, double y, double z) {
//        p.setX(x);
//        p.setY(y);
//        p.setZ(z);
//        ip.setX(-x);
//        ip.setY(-y);
//        ip.setZ(-z);
//    }

    private void rotateX(double angle) {
        rx.setAngle(rx.getAngle() + angle);
    }

    private void rotateY(double angle) {
        ry.setAngle(ry.getAngle() + angle);
    }

    private void rotateZ(double angle) {
        rz.setAngle(rz.getAngle() + angle);
    }

    public void reset() {
        t.setX(0.0);
        t.setY(0.0);
        t.setZ(0.0);
        rx.setAngle(0.0);
        ry.setAngle(0.0);
        rz.setAngle(0.0);
        p.setX(0.0);
        p.setY(0.0);
        p.setZ(0.0);
        ip.setX(0.0);
        ip.setY(0.0);
        ip.setZ(0.0);
    }
}
