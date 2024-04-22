package Main;

import controller.CitasController;
import estructurasDatos.GeneradorCodigo;
import model.MotivoCita.Control;
import model.domain.cita.Cita;

public class MainCitas {
    public static void main(String[] args) {

        GeneradorCodigo generadorCodigo = new GeneradorCodigo();

        CitasController citasController = new CitasController();

        citasController.agendarCitaControlValoracion(new Cita("3","VALORACION","MEDICINA_GENERAL", "123456", true));
        citasController.agendarCitaControlValoracion(new Cita("2","VALORACION","DERMATOLOGIA", "123456", true));;

        //probar cita examen
        citasController.agendarCitaExamen("2", "S953", "123456", true);
        citasController.agendarCitaExamen("3", "V566", "123456", false);
    }

}
