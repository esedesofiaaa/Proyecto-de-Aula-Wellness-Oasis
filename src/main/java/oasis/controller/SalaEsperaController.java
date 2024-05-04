package oasis.controller;

import oasis.aplicacion.controllerView.ViewSalaDeEsperaController;
import oasis.model.domain.cita.Cita;
import oasis.model.repository.SalaEsperaRepository;

import java.util.Timer;
import java.util.TimerTask;

public class SalaEsperaController {
    private final SalaEsperaRepository salaEsperaRepository;
    private final ViewSalaDeEsperaController viewSalaDeEsperaController;
    private Timer timer;

    public SalaEsperaController() {
        this.salaEsperaRepository = new SalaEsperaRepository();
        this.viewSalaDeEsperaController = new ViewSalaDeEsperaController();
        this.timer = new Timer();

        // Iniciar el temporizador para actualizar cada 30 segundos
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                actualizarYMostrarCita();
            }
        }, 0, 30 * 1000); // Se ejecuta cada 30 segundos
    }

    private void actualizarYMostrarCita() {
        Cita cita = salaEsperaRepository.verCitaEnPantalla();
        if (cita != null) {
            viewSalaDeEsperaController.agregarCita(cita);
            salaEsperaRepository.eliminarCitaDeSalaDeEspera();
        }
    }

    // Método para obtener la siguiente cita de la sala de espera
    public Cita obtenerSiguienteCita() {
        return salaEsperaRepository.obtenerSiguienteCita();
    }

    // Método para ver la cita en pantalla
    public Cita verCitaEnPantalla() {
        return salaEsperaRepository.verCitaEnPantalla();
    }

    // Método para eliminar cita de la sala de espera
    public void eliminarCitaDeSalaDeEspera() {
        salaEsperaRepository.eliminarCitaDeSalaDeEspera();
    }
}
