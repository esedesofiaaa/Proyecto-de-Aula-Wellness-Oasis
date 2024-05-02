package oasis.controller;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.motivoCita.Examen;
import oasis.model.domain.paciente.Paciente;
import oasis.model.domain.registroExamen.RegistroExamen;
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

    public String agregarExamen(Examen motivoCitaExamen, String idPaciente) {
        boolean autorizado = false;
        if (!validarIdPaciente(idPaciente)) {
            System.out.println("El paciente con id " + idPaciente + " no existe, el examen no fue registrado");
            return null; // Devuelve null si el paciente no existe
        }
        RegistroExamen registroExamen = new RegistroExamen(motivoCitaExamen, idPaciente);
        registroExamenRepository.añadirNuevoRegistroExamen(registroExamen);
        System.out.println("Examen registrado: " + registroExamen.getRadicadoExamen());
        return registroExamen.getRadicadoExamen(); // Devuelve el ID del examen registrado
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

    //Metodo para buscar un Examen por tipe examen
    public Examen buscarExamenPorTipoExamen(String tipoExamen) {
        Examen[] examenes = Examen.values();
        for (Examen examen : examenes) {
            if (examen.getTipoExamen().equalsIgnoreCase(tipoExamen)) {
                return examen;
            }
        }
        return null;
    }

    public DoubleLinkedList<RegistroExamen> buscarPorIdPaciente(String idPaciente) {
        return registroExamenRepository.buscarPorIdPaciente(idPaciente);
    }

        //Metodo para buscar  Examenenes por paciente y retorne la lista de examenes agendada


}
