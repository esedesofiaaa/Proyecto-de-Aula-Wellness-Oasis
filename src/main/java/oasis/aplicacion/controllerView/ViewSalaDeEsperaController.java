package oasis.aplicacion.controllerView;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import oasis.controller.SalaEsperaController;
import oasis.model.domain.cita.Cita;

import java.util.LinkedList;
import java.util.Queue;

public class ViewSalaDeEsperaController {

    @FXML
    private Label idModulo1;
    @FXML
    private Label idModulo2;
    @FXML
    private Label idModulo3;

    private SalaEsperaController salaEsperaController;
    private Queue<Cita> colaCitas = new LinkedList<>();
    private static final int ESPACIOS_SALA = 3;
    private int contador = 0;

    public void setSalaEsperaController(SalaEsperaController salaEsperaController) {
        this.salaEsperaController = salaEsperaController;
        iniciarTemporizador();
    }

    private void iniciarTemporizador() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(30000); // Esperar 30 segundos
                    Platform.runLater(this::actualizarInterfaz); // Actualizar la interfaz en el hilo de JavaFX
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true); // Hacer que el hilo sea daemon para que se detenga al cerrar la aplicación
        thread.start();
    }

    private void actualizarInterfaz() {
        if (!colaCitas.isEmpty()) {
            Cita nuevaCita = salaEsperaController.obtenerSiguienteCita();
            if (nuevaCita != null) {
                colaCitas.add(nuevaCita);
                actualizarVista();
            }
        }
    }

    private void actualizarVista() {
        Cita cita = colaCitas.poll(); // Obtener y eliminar el primer elemento de la cola
        if (cita != null) {
            switch (contador % ESPACIOS_SALA) {
                case 0:
                    Platform.runLater(() -> idModulo1.setText(cita.getIdCita() + " - " + cita.getIdPaciente()));
                    break;
                case 1:
                    Platform.runLater(() -> idModulo2.setText(cita.getIdCita() + " - " + cita.getIdPaciente()));
                    break;
                case 2:
                    Platform.runLater(() -> idModulo3.setText(cita.getIdCita() + " - " + cita.getIdPaciente()));
                    break;
                default:
                    break;
            }
            contador++;
        }
    }

    public void agregarCita(Cita cita) {
        colaCitas.add(cita);
        actualizarVista();
    }
}
