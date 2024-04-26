module oasis {
    requires com.google.gson;
    requires java.logging;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;

    opens oasis.aplicacion to javafx.fxml;
    opens oasis.controller to javafx.fxml;

    // Exporta el paquete que contiene la clase Paciente
    exports oasis.model.domain.paciente to com.google.gson;

    exports oasis.aplicacion;
    exports oasis.controller;
}
