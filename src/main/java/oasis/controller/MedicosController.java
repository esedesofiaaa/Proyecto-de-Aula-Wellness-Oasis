package oasis.controller;

import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.Especialidad;
import oasis.model.domain.medico.Medico;
import oasis.model.repository.MedicoRepository;

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
    public DoubleLinkedList<Medico> buscarMedicoPorEspecialidad (Especialidad especialidad) {
        medicoRepository.buscarPorEspecialidad(especialidad).mostrarLista();
        return medicoRepository.buscarPorEspecialidad(especialidad);
    }
//Metodo que retorne un String que sea el id de un medico y reciba el nombre del medico
    public String buscarIdPorNombre(String nombre){
        return medicoRepository.buscarIdPorNombre(nombre);
    }

}
