package Main;

import controller.CitasController;
import model.MotivoCita.Control;

public class MainCitas {
    public static void main(String[] args) {

        CitasController citasController = new CitasController();

        citasController.agendarCitaControl("2",true, Control.DERMATOLOGIA );
    }

}
