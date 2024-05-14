package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oasis.controller.CitasController;
import oasis.model.repository.SalaEsperaRepository;
import oasis.model.domain.cita.Cita;

public class ViewPagarCitaController {
    @FXML
    private TextField idCodigoCita;

    @FXML
    private Label idMensajeLabel;

    private final CitasController citasController;
    private final SalaEsperaRepository salaEsperaRepository;

    public ViewPagarCitaController() {
        this.citasController = new CitasController();
        this.salaEsperaRepository = new SalaEsperaRepository();
    }

    public void pagarCita() {
        String idCita = idCodigoCita.getText();
        Cita cita = citasController.buscarCitaPorId(idCita);

        if (cita == null) {
            idMensajeLabel.setText("Cita no encontrada.");
        } else if (cita.isPagado()) { // Suponiendo que el método isPagada verifica si la cita ya está pagada
            idMensajeLabel.setText("La cita ya está paga.");
        } else {
            boolean pagoExitoso = citasController.pagarCita(idCita); // Suponiendo que el método pagarCita actualiza el estado de la cita y devuelve un booleano
            if (pagoExitoso) {
                salaEsperaRepository.agregarCitaASalaDeEspera(cita);
                idMensajeLabel.setText("Tu cita fue pagada con éxito.");
            } else {
                idMensajeLabel.setText("Hubo un problema al pagar la cita.");
            }
        }
    }
}
