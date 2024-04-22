package controller;
import estructurasDatos.listas.DoubleLinkedList;
import model.Medico;
import model.MotivoCita.Control;
import model.domain.cita.Cita;
/*import model.Especialidad.Especialidad;
import model.MotivoCita;*/
import model.domain.paciente.Paciente;
import model.repository.CitaRepository;
import model.repository.MedicoRepository;
import model.repository.PacientesRepository;
import model.repository.RegistroExamenRepository;

public class CitasController {

    private final CitaRepository citaRepository;
    private final PacientesRepository pacientesRepository;
    private final PacientesController pacientesController;
    private final RegistroExamenRepository  registroExamenRepository;

    private final MedicosController medicosController;
    public DoubleLinkedList<Paciente> listaExamenes;

    // Constructor
    public CitasController() {
        this.medicosController =    new MedicosController();
        this.pacientesController = new PacientesController();
        this.registroExamenRepository = new RegistroExamenRepository();
        this.pacientesRepository = new PacientesRepository();
        this.citaRepository = new CitaRepository();

    }
    public void agendarCitaControlValoracion(Cita cita) {
        // Ubica el paciente en la listaPacientes por su id, para evitar agregar todos los datos del paciente
        Paciente pacienteTemp = pacientesRepository.obtenerPorId(cita.getIdPaciente());
        Medico medicoTemp = medicosController.buscarMedicoPorId(cita.getMedico());

        if (pacienteTemp != null && medicoTemp != null) {
            // Verificar si el costo debe ser actualizado según el motivo de la cita y la especialidad
            actualizarCostoCita(cita);

            citaRepository.guardarCita(cita);
            System.out.println("Cita agendada para el paciente: " + pacienteTemp.getNombre() + " " + pacienteTemp.getApellido() +
                    ". Tu código de cita es: " + cita.getIdCita());
        } else {
            System.out.println("El paciente o el médico no están registrados en el sistema.");
        }
    }

    private void actualizarCostoCita(Cita cita) {
        if ("CONTROL".equals(cita.getMotivoCita())) {
            cita.setCosto("Gratis");
        } else if ("VALORACION".equals(cita.getMotivoCita())) {
            if (!"MEDICINA_GENERAL".equals(cita.getEspecialidad())) {
                cita.setCosto("4500");
            } else {
                cita.setCosto("2500");
            }
        } else if ("EXAMEN".equals(cita.getMotivoCita())) {
            cita.setCosto("5000");
        }
    }

    public DoubleLinkedList<Cita> citasDelPaciente (String idPaciente) {
        citaRepository.buscarCitasPorIdPaciente(idPaciente).mostrarLista();
        return citaRepository.buscarCitasPorIdPaciente(idPaciente);
    }



    /*
    public void agendarCitaExamen (String idPaciente, String motivoCita, boolean pagado) {
        Paciente pacienteTemp =  pacientesRepository.obtenerPorId(idPaciente);
        if(pacienteTemp!= null) {
            Cita cita = new Cita( pacienteTemp.getId(), motivoCita , pagado) {
            citaRepository.guardarCita(cita);
            pacienteTemp.agregarCitaAlHistorial(cita);
            System.out.println("Cita agendada para el paciente: " + pacienteTemp.getNombre() + " " + pacienteTemp.getApellido()+
            '\''+"Tu codigo de cita es:" + cita.getCodigoCita());

        }else {
            System.out.println("El paciente no esta registrado en el sistema");

        }
    }*/
}
