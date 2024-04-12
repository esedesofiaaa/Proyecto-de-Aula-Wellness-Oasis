package controller;

import model.domain.paciente.Paciente;
import model.repository.PacientesRepository;
import estructurasDatos.listas.DoubleLinkedList;

public class PacientesController {
    private final PacientesRepository pacientesRepository;

    public PacientesController() {
        this.pacientesRepository = new PacientesRepository();
    }

    /**
     * Método para agregar un nuevo paciente
     * @param nuevoPaciente El nuevo paciente a agregar
     */
    public void agregarPaciente(Paciente nuevoPaciente) {

        pacientesRepository.añadirNuevoPaciente(nuevoPaciente);
    }

    /**
     * Método para eliminar un paciente
     * @param paciente El paciente a eliminar
     */
    public void eliminarPaciente(Paciente paciente) {
        pacientesRepository.eliminarPaciente(paciente);
    }

    /**
     * Método para buscar un paciente por su id
     * @param idPaciente El id del paciente que se desea buscar
     * @return El paciente si se encuentra, o null si no se encuentra
     */
    public Paciente buscarPacientePorId(String idPaciente) {
        return pacientesRepository.obtenerPorId(idPaciente);
    }

    /**
     * Método para obtener todos los pacientes
     * @return Una lista de todos los pacientes
     */
    public DoubleLinkedList<Paciente> obtenerTodos() {
        return pacientesRepository.obtenerTodos();
    }

    // Otros métodos del controlador según las necesidades
}
