package Main;


import controller.RegistroExamenController;
import model.MotivoCita.Examen;

public class MainRegistroExamen {
    public static void main(String[] args) {

        RegistroExamenController registroExamenController = new RegistroExamenController();

        registroExamenController.agregarExamen(Examen.ELECTROCARDIOGRAMA, "1");
        registroExamenController.agregarExamen(Examen.ANALISIS_DE_SANGRE, "3");
        registroExamenController.agregarExamen(Examen.EXAMEN_FISICO_INFANTIL, "2");
        registroExamenController.agregarExamen(Examen.ENTREVISTA_PSIQUIATRICA, "3");

        registroExamenController.agregarExamen(Examen.DERMATOSCOPIA, "2");
        registroExamenController.registroExamenRepository.obtenerTodos().mostrarLista();
    }


}
