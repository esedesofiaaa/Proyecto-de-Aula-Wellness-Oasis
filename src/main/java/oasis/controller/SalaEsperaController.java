package oasis.controller;

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
        Cita siguienteCita = salaEsperaRepository.obtenerSiguienteCita();
        if (siguienteCita != null) {
            viewSalaDeEsperaController.mostrarCita(siguienteCita);
        }
    }
}
