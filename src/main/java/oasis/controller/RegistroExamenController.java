package oasis.controller;
import oasis.model.domain.motivoCita.Examen;
import oasis.model.domain.paciente.Paciente;
import oasis.model.RegistroExamen;
import oasis.model.repository.RegistroExamenRepository;


public class RegistroExamenController {

    public RegistroExamenRepository registroExamenRepository;
    private  PacientesController  pacientesController = new PacientesController();

    public RegistroExamenController() {
        this.registroExamenRepository = new RegistroExamenRepository();
        this.pacientesController = new PacientesController();
    }

    public boolean validarIdPaciente(String idPaciente) {
        Paciente paciente = pacientesController.buscarPacientePorId(idPaciente);
        // Verificar si el paciente es null antes de intentar acceder a sus propiedades
        if (paciente != null) {
            // Realizar otras operaciones con el paciente si es necesario
            return true;
        } else {
            System.out.println("El paciente con id " + idPaciente + " no existe.");
            return false;
        }
    }

    public void agregarExamen(Examen motivoCitaExamen, String idPaciente) {
        boolean autorizado = false;
        if (!validarIdPaciente(idPaciente)) {
            System.out.println("El paciente con id " + idPaciente + " no existe, el examen no fue registrado");
            return;
        }
        RegistroExamen registroExamen = new RegistroExamen(motivoCitaExamen, idPaciente);
        registroExamenRepository.añadirNuevoRegistroExamen(registroExamen);
        System.out.println("Examen registrado: " + registroExamen.getRadicadoExamen());

        // Aquí podrías realizar más lógica relacionada con la creación o manipulación de registros de examen
    }

    //Metodo para revisar si el radicado examen existe
    public boolean validarRadicadoExamen(String radicadoExamen) {
        RegistroExamen registroExamen = registroExamenRepository.buscarPorRadicadoExamen(radicadoExamen);
        if (registroExamen != null) {
            return true;
        } else {
            System.out.println("El radicado de examen " + radicadoExamen + " no existe.");
            return false;
        }
    }
    //Metodo para cambiar el boolean autorizado a true



}
