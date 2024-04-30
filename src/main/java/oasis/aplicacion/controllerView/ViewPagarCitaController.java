package oasis.aplicacion.controllerView;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import oasis.controller.CitasController;
import oasis.controller.PacientesController;

public class ViewPagarCitaController {
    @FXML
    private TextField idCodigoCita;

    private final CitasController citasController; //Instanciamos el controlador del backend

    public ViewPagarCitaController() {//Constructor
        this.citasController = new CitasController();
    }//Constructor

    public void pagarCita(){
        String idCita = idCodigoCita.getText(); //el get de lo que se escribe en el textfield
        citasController.pagarCita(idCita);
    }//pagarCita
}