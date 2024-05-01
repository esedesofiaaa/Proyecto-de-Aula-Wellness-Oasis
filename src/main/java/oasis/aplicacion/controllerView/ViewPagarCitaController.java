package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oasis.controller.CitasController;

public class ViewPagarCitaController {
    @FXML
    private TextField idCodigoCita;

    @FXML
    private Label idMensajeLabel;

    private final CitasController citasController;

    public ViewPagarCitaController() {
        this.citasController = new CitasController();
    }

    public void pagarCita() {
        String idCita = idCodigoCita.getText();
        boolean pagoExitoso = citasController.pagarCita(idCita); // Suponiendo que el método pagarCita devuelve un booleano indicando si el pago fue exitoso

        if (pagoExitoso) {
            idMensajeLabel.setText("Tu cita fue pagada con éxito.");
        } else {
            idMensajeLabel.setText("Cita no encontrada.");
        }
    }
}
