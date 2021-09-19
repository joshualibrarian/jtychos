module space.readingrainbow.tychos {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires lombok;

    opens space.readingrainbow.tychos to javafx.fxml;
    exports space.readingrainbow.tychos;
    exports space.readingrainbow.tychos.simulator;
    opens space.readingrainbow.tychos.simulator to javafx.fxml;
    exports space.readingrainbow.tychos.simulator.stars;
    opens space.readingrainbow.tychos.simulator.stars to javafx.fxml;
}