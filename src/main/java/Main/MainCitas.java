package Main;

import controller.CitasController;
import estructurasDatos.GeneradorCodigo;
import model.MotivoCita.Control;
import model.domain.cita.Cita;
import model.repository.CitaRepository;

public class MainCitas {
    public static void main(String[] args) {

        GeneradorCodigo generadorCodigo = new GeneradorCodigo();
        CitaRepository citaRepository = new CitaRepository();
        CitasController citasController = new CitasController();
        /*Cita citaTemp = new Cita("3", "VALORACION", "PEDIATRIA", "123456", true);
        citasController.agendarCitaControlValoracion(new Cita("3","VALORACION","MEDICINA_GENERAL", "123456", true));
        citasController.agendarCitaControlValoracion(new Cita("2","VALORACION","DERMATOLOGIA", "123456", true));
        citasController.agendarCitaControlValoracion(citaTemp);
        //probar cita examen
        citasController.agendarCitaExamen("3", "S953", "123456", true);
        citasController.agendarCitaExamen("2", "V566", "123456", false);
     *//*   citasController.eliminarCita("PSE832");
        citasController.eliminarCita("PV290");*/

        //citasController.modificarCitaExamen("PE727","radicadoExamen", "S953");
        citasController.mostrarCitas();
    }

}
