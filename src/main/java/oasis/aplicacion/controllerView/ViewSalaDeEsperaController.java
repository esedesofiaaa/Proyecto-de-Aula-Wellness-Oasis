package oasis.aplicacion.controllerView;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import oasis.model.domain.cita.Cita;

public class ViewSalaDeEsperaController {

    @FXML
    private Label idModulo1;
    @FXML
    private Label idModulo2;
    @FXML
    private Label idModulo3;

    private static final int ESPACIOS_SALA = 3;
    private int contador = 0;

    public void mostrarCita(Cita cita) {
        Platform.runLater(() -> {
            Label label = obtenerLabelSegunContador();
            if (label != null) {
                label.setText(cita.getIdCita() + " - " + cita.getIdPaciente());
            } else {
                System.err.println("Error: No hay espacio disponible para mostrar la cita.");
            }
        });
    }

    private Label obtenerLabelSegunContador() {
        switch (contador % ESPACIOS_SALA) {
            case 0:
                contador++;
                return idModulo1;
            case 1:
                contador++;
                return idModulo2;
            case 2:
                contador++;
                return idModulo3;
            default:
                return null;
        }
    }
}
