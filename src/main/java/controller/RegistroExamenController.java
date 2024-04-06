package controller;
import model.MotivoCita.Examen;
import model.Paciente;
import model.RegistroExamen;
import model.repository.RegistroExamenRepository;
import model.repository.PacientesRepository;


public class RegistroExamenController {

    public RegistroExamenRepository registroExamenRepository = new RegistroExamenRepository();
    private  PacientesController  pacientesController = new PacientesController();


    public boolean validarIdPaciente(String idPaciente) {
        Paciente paciente = pacientesController.buscarPacientePorId(idPaciente);
        return paciente != null; // Si el paciente es diferente de null, significa que existe
    }
    public void agregarExamen(Examen motivoCitaExamen, String idPaciente) {
        boolean autorizado = false;
        if (!validarIdPaciente(idPaciente)) {
            System.out.println("El paciente con id " + idPaciente + " no existe, el examen no fue registrado");
            return;
        }
        RegistroExamen registroExamen = new RegistroExamen(motivoCitaExamen, idPaciente, autorizado);
        registroExamenRepository.añadirNuevoRegistroExamen(registroExamen);
        System.out.println("Examen registrado: " + registroExamen.getRadicadoExamen());

        // Aquí podrías realizar más lógica relacionada con la creación o manipulación de registros de examen
    }

    //Metodo para cambiar el boolean autorizado a true

    public void actualizarAutorizacionExamen(RegistroExamen registroExamen) {
        registroExamenRepository.actualizarAutorizacionExamen(RegistroExamen registroExamen);
    }

}
