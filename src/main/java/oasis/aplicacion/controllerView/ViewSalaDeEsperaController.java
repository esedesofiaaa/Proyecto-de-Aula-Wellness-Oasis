package oasis.aplicacion.controllerView;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import oasis.model.domain.cita.Cita;
import oasis.estructurasDatos.listas.QueueList;

import java.util.Timer;
import java.util.TimerTask;

public class ViewSalaDeEsperaController {

    @FXML
    private Label idModulo1;
    @FXML
    private Label idModulo2;
    @FXML
    private Label idModulo3;

    private QueueList<Cita> colaCitas = new QueueList<>();
    private static final int ESPACIOS_SALA = 3;
    private int contador = 0;

    public void mostrarCita(Cita cita) {
        Platform.runLater(() -> {
            // Mostrar la cita en el espacio correspondiente
            switch (contador % ESPACIOS_SALA) {
                case 0:
                    mostrarCitaEnEspacio(idModulo1, cita);
                    break;
                case 1:
                    mostrarCitaEnEspacio(idModulo2, cita);
                    break;
                case 2:
                    mostrarCitaEnEspacio(idModulo3, cita);
                    break;
                default:
                    break;
            }
            contador++;
        });
    }

    private void mostrarCitaEnEspacio(Label label, Cita cita) {
        Platform.runLater(() -> {
            if (label != null) {
                label.setText(cita.getIdCita() + " - " + cita.getIdPaciente());
                // Temporizador para eliminar la cita después de 30 segundos
                Timer temporizador = new Timer();
                temporizador.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> label.setText(""));
                    }
                }, 30 * 1000); // 30 segundos
            } else {
                System.err.println("Error: El Label es nulo en mostrarCitaEnEspacio");
            }
        });
    }

    public void agregarCita(Cita cita) {
        colaCitas.enqueue(cita);
        mostrarCita(colaCitas.dequeue()); // Mostrar la primera cita al agregarla
    }
}
