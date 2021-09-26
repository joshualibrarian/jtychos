module space.readingrainbow.tychos {
    requires javafx.controls;

//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires validatorfx;
//    requires org.kordamp.ikonli.javafx;
//    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires java.logging;
//    requires com.github.gbenroscieznce;

    exports space.readingrainbow.tychos;
    exports space.readingrainbow.tychos.simulator;
    exports space.readingrainbow.tychos.test;
}