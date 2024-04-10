package controller;
import estructurasDatos.listas.DoubleLinkedList;
import model.MotivoCita.Control;
import model.citas.Cita;
/*import model.Especialidad.Especialidad;
import model.MotivoCita;*/
import model.Paciente;
import model.repository.CitaRepository;
import model.repository.PacientesRepository;
import model.repository.RegistroExamenRepository;

public class CitasController {

    private final CitaRepository citaRepository;
    private final PacientesRepository pacientesRepository;
    private final PacientesController pacientesController;
    private final RegistroExamenRepository  registroExamenRepository;
    public DoubleLinkedList<Paciente> listaExamenes;

    // Constructor
    public CitasController() {
        this.pacientesController = new PacientesController();
        this.registroExamenRepository = new RegistroExamenRepository();
        this.pacientesRepository = new PacientesRepository();
        this.citaRepository = new CitaRepository();

    }
    public void agendarCitaControl(String idPaciente, boolean pagado, Control motivoCitaControl) {

        // Ubica el paciente en la listaPacientes por su id,  para evitar agregar todos los datos del paciente
        Paciente pacienteTemp =  pacientesRepository.obtenerPorId(idPaciente);
        if(pacienteTemp!= null) {
            Cita cita = new CitaControl(idPaciente, false, motivoCitaControl);
            citaRepository.guardarCita(cita);
            pacienteTemp.agregarCitaAlHistorial(cita);
            System.out.println("Cita agendada para el paciente: " + pacienteTemp.getNombre() + " " + pacienteTemp.getApellido());

        }else {
            System.out.println("El paciente no está registrado, ");

        }

        // Crear la cita para el paciente y asociarla al paciente


        // Agregar la cita al historial de citas del paciente
    }
/*
    public void agendarCitaValoracion(String idPaciente, boolean pagado, boolean tomado, Valoracion motivoCitaValoracion) {

        // Ubica el paciente en la listaPacientes por si id,  para evitar agregar todos los datos del paciente
        Paciente pacienteTemp =  listaPacientes.buscarPacientePorId(idPaciente);
        // Verificar si el paciente ya está registrado
        int indicePaciente = listaPacientes.buscarElemento(pacienteTemp);
        if (indicePaciente == -1) { // El paciente no está registrado, se agrega a la lista de pacientes
            listaPacientes.agregarAlFinal(pacienteTemp);
        }

        // Crear la cita para el paciente y asociarla al paciente
        Cita cita = new CitaValoracion(idPaciente, false, false, motivoCitaValoracion);
        listaCitas.agregarAlFinal(cita);

        // Agregar la cita al historial de citas del paciente
        pacienteTemp.agregarCitaAlHistorial(cita);
    }

    public void agendarCitaExamen(String idPaciente, boolean pagado, boolean tomado, Examen motivoCitaExamen) {

        // Ubica el paciente en la listaPacientes por si id,  para evitar agregar todos los datos del paciente
        Paciente pacienteTemp =  listaPacientes.buscarPacientePorId(idPaciente);
        // Verificar si el paciente ya está registrado
        int indicePaciente = listaPacientes.buscarElemento(pacienteTemp);
        if (indicePaciente == -1) { // El paciente no está registrado, se agrega a la lista de pacientes
            listaPacientes.agregarAlFinal(pacienteTemp);
        }

        // Crear la cita para el paciente y asociarla al paciente
        Cita cita = new CitaExamen(idPaciente, false, false, motivoCitaExamen);
        listaCitas.agregarAlFinal(cita);

        // Agregar la cita al historial de citas del paciente
        pacienteTemp.agregarCitaAlHistorial(cita);
    }

*/
//metodo para validar si el tipo de cita es  Control y cambiar el boolean de pagado a true
}
