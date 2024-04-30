package oasis.Main;

import oasis.controller.MedicosController;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.Especialidad;
import oasis.model.domain.medico.Medico;

public class MainMedicos {
    public static void main(String[] args) {

        MedicosController medicos = new MedicosController();
        medicos.agregarMedico(new Medico("Juan Perez", Especialidad.PEDIATRIA, "123455"));
        medicos.agregarMedico(new Medico("Maria Lopez", Especialidad.CARDIOLOGIA, "123456"));

        medicos.buscarMedicoPorEspecialidad("PEDIATRIA");
        DoubleLinkedList<Medico> medicosPediatras = medicos.buscarMedicoPorEspecialidad("PEDIATRIA");

        medicos.eliminarMedico(medicos.buscarMedicoPorId("123455"));

        medicos.obtenerTodos().mostrarLista();
    }

}
