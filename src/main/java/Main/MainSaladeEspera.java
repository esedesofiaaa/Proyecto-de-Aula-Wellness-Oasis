package Main;

import controller.SalaEsperaController;

public class MainSaladeEspera {
    public static void main(String[] args) {
        // Instancia de la clase SalaEsperaController
        SalaEsperaController salaEsperaController = new SalaEsperaController();
        // Registro de una cita en la sala de espera
        //salaEsperaController.registrarCita("PSE965");
        salaEsperaController.eliminarCitaDeSalaDeEspera();

    }
}
