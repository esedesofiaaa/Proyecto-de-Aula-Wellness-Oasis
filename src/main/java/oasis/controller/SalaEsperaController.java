package oasis.controller;

import javafx.application.Platform;
import oasis.aplicacion.controllerView.ViewSalaDeEsperaController;
import oasis.model.domain.cita.Cita;
import oasis.model.repository.SalaEsperaRepository;

import java.util.Timer;
import java.util.TimerTask;

public class SalaEsperaController {
    private Timer timer;
    private final ViewSalaDeEsperaController viewSalaDeEsperaController;
    private final SalaEsperaRepository salaEsperaRepository;

    public SalaEsperaController(ViewSalaDeEsperaController viewSalaDeEsperaController) {
        this.viewSalaDeEsperaController = viewSalaDeEsperaController;
        this.salaEsperaRepository = new SalaEsperaRepository();
        iniciarTemporizador();
    }

    private void iniciarTemporizador() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                actualizarYMostrarCita();
            }
        }, 0, 30 * 1000); // Se ejecuta cada 30 segundos
    }

    private void actualizarYMostrarCita() {
        // Lógica para obtener la siguiente cita y actualizar la vista
        Cita siguienteCita = obtenerSiguienteCita();
        if (siguienteCita != null) {
            viewSalaDeEsperaController.mostrarCita(siguienteCita);
        }
    }

    private Cita obtenerSiguienteCita() {
        // Lógica para obtener la siguiente cita de la sala de espera
        return null; // Debes implementar este método según la lógica de tu aplicación
    }
}
