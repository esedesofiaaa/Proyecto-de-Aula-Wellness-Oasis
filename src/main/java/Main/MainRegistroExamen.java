package Main;


import controller.RegistroExamenController;
import model.MotivoCita.Examen;
import model.repository.RegistroExamenRepository;

public class MainRegistroExamen {
    public static void main(String[] args) {

        RegistroExamenController registroExamenController = new RegistroExamenController();

        RegistroExamenRepository registroExamenRepository = new RegistroExamenRepository();

        /*registroExamenController.agregarExamen(Examen.ELECTROCARDIOGRAMA, "2");
        registroExamenController.agregarExamen(Examen.ANALISIS_DE_SANGRE, "3");
        registroExamenController.agregarExamen(Examen.EXAMEN_FISICO_INFANTIL, "2");
        registroExamenController.agregarExamen(Examen.ENTREVISTA_PSIQUIATRICA, "3");

        registroExamenController.agregarExamen(Examen.DERMATOSCOPIA, "2");
        registroExamenController.registroExamenRepository.obtenerTodos().mostrarLista();
    */
        registroExamenRepository.eliminarExamen(registroExamenRepository.buscarExamen("S953"));
    }



}
