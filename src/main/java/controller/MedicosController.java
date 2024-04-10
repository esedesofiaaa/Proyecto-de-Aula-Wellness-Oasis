package controller;

import estructurasDatos.listas.DoubleLinkedList;
import model.Medico;
import model.Paciente;
import model.repository.MedicoRepository;

public class MedicosController {

    private final MedicoRepository medicoRepository;

    public MedicosController() {
        this.medicoRepository = new MedicoRepository();
    }

    public void agregarMedico (Medico nuevoMedico) {
        medicoRepository.añadirNuevoMedico(nuevoMedico);
    }

    public void eliminarMedico (Medico medico) {
        medicoRepository.eliminarMedico(medico);
    }

    public Medico buscarMedicoPorId (String idMedico) {
        return medicoRepository.obtenerPorId(idMedico);
    }

    public DoubleLinkedList<Medico> obtenerTodos() {
        return medicoRepository.obtenerTodos();
    }

    //buscar medico por especialidad
    public DoubleLinkedList<Medico> buscarMedicoPorEspecialidad (String especialidad) {
        medicoRepository.buscarPorEspecialidad(especialidad).mostrarLista();
        return medicoRepository.buscarPorEspecialidad(especialidad);
    }


}
