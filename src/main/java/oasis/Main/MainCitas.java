package oasis.Main;

import oasis.controller.CitasController;
import oasis.estructurasDatos.GeneradorCodigo;
import oasis.model.MotivoCita.Control;
import oasis.model.domain.cita.Cita;
import oasis.model.repository.CitaRepository;

public class MainCitas {
    public static void main(String[] args) {

        GeneradorCodigo generadorCodigo = new GeneradorCodigo();
        CitaRepository citaRepository = new CitaRepository();
        CitasController citasController = new CitasController();
        //Cita citaTemp = new Cita("3", "VALORACION", "PEDIATRIA", "123456", true);
       /* citasController.agendarCitaControlValoracion("3","VALORACION","MEDICINA_GENERAL", "123456", true);
        citasController.agendarCitaControlValoracion("2","VALORACION","DERMATOLOGIA", "123456", true);
       // citasController.agendarCitaControlValoracion(citaTemp);
        //probar cita examen
        citasController.agendarCitaExamen("3", "S953", "123456", true);
        citasController.agendarCitaExamen("2", "V566", "123456", false);
        citasController.eliminarCita("PE787");
        citasController.eliminarCita("DV550");*/

        citasController.modificarCita("MV809","radicadoExamen", "A637");
        //citasController.mostrarCitas();
    }

}
