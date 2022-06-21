module uv.mx.carlos.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires ProjectoFinalJDBC;
    requires java.sql;
    requires javax.persistence;

    opens uv.mx.carlos.proyectofinal to javafx.fxml;
    exports uv.mx.carlos.proyectofinal;
}