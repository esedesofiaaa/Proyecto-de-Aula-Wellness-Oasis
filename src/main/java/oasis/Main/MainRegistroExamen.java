package oasis.Main;


import oasis.controller.RegistroExamenController;
import oasis.model.repository.RegistroExamenRepository;

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
        //registroExamenRepository.eliminarExamen(registroExamenRepository.buscarPorRadicadoExamen("S953"));
        registroExamenController.buscarPorIdPaciente("2").mostrarLista();

    }



}
