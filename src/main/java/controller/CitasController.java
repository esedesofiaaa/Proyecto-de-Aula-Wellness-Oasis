package controller;
import estructurasDatos.listas.DoubleLinkedList;
import model.Cita;
import model.Especialidad;
import model.MotivoCita;
import model.Paciente;

public class CitasController {
    private DoubleLinkedList<Cita> listaCitas;
    public DoubleLinkedList<Paciente> listaPacientes;

    // Constructor
    public CitasController() {
        this.listaCitas = new DoubleLinkedList<>();
        this.listaPacientes = new DoubleLinkedList<>();
    }

    // Método para agendar una cita para un paciente
    //cambiar paciente por id e  implementar el metodo buscarPacientePorId
    public void agendarCita(String idPaciente, Especialidad especialidad, String profesionalAsignado, MotivoCita motivoCita) {

        // Ubica el paciente en la listaPacientes por si id,  para evitar agregar todos los datos del paciente
        Paciente pacienteTemp =  listaPacientes.buscarPacientePorId(idPaciente);
        // Verificar si el paciente ya está registrado
        int indicePaciente = listaPacientes.buscarElemento(pacienteTemp);
        if (indicePaciente == -1) { // El paciente no está registrado, se agrega a la lista de pacientes
            listaPacientes.agregarAlFinal(pacienteTemp);
        }

        // Crear la cita para el paciente y asociarla al paciente
        Cita cita = new Cita(idPaciente, especialidad, profesionalAsignado, motivoCita, false);
        listaCitas.agregarAlFinal(cita);

        // Agregar la cita al historial de citas del paciente
        pacienteTemp.agregarCitaAlHistorial(cita);
    }

//metodo para validar si el tipo de cita es  Control y cambiar el boolean de pagado a true
}
