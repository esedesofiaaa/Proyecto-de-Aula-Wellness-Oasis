module oasis {
    requires com.google.gson;
    requires java.logging;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens oasis.aplicacion to javafx.fxml;
    opens oasis.controller to javafx.fxml;
    opens oasis.model.domain.paciente to com.google.gson;
    opens oasis.estructurasDatos.listas to com.google.gson;
    opens oasis.model.domain.cita to com.google.gson;
    opens oasis.model.repository to com.google.gson;
    opens oasis.model to com.google.gson;
    opens oasis.estructurasDatos to com.google.gson;
    opens   oasis. model. domain. motivoCita to com.google.gson;


    exports oasis.aplicacion;
    exports oasis.controller;
    exports oasis.model;
    exports oasis.estructurasDatos.listas;
}