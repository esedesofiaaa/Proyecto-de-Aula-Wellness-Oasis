module oasis {
    requires com.google.gson;
    requires java.logging;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens oasis.aplicacion.controllerView to javafx.fxml;
    opens oasis.aplicacion.mainView to javafx.fxml;
    opens oasis.controller to javafx.fxml;
    opens oasis.model.domain.paciente to com.google.gson;
    opens oasis.estructurasDatos.listas to com.google.gson;
    opens oasis.model.domain.cita to com.google.gson;
    opens oasis.model.repository to com.google.gson;

    opens oasis.estructurasDatos to com.google.gson;
    opens oasis.model.domain.motivoCita to com.google.gson;

    exports oasis.aplicacion.controllerView;
    exports oasis.aplicacion.mainView;
    exports oasis.controller;
    exports oasis.estructurasDatos.listas;
    exports oasis.model.domain.medico;
    opens oasis.model.domain.medico to com.google.gson;
    exports oasis.model.domain.paciente;
    exports oasis.model.domain.registroExamen;
    opens oasis.model.domain.registroExamen to com.google.gson;
    exports oasis.model.domain;
    opens oasis.model.domain to com.google.gson;

    exports oasis.Main; // Exporta el paquete oasis.Main
}
