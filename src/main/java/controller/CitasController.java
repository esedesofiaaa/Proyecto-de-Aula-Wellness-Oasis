package controller;
import estructurasDatos.listas.DoubleLinkedList;
import model.Medico;
import model.MotivoCita.Control;
import model.RegistroExamen;
import model.domain.cita.Cita;
/*import model.Especialidad.Especialidad;
import model.MotivoCita;*/
import model.domain.cita.CitaExamen;
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
    private final RegistroExamenController  registroExamenController;

    private final MedicosController medicosController;
    public DoubleLinkedList<Paciente> listaExamenes;

    // Constructor
    public CitasController() {
        this.registroExamenController =  new RegistroExamenController();
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

    public void agendarCitaExamen (String idPaciente, String radicadoExamen, String medico, boolean pagado) {
        Paciente pacienteTemp = pacientesRepository.obtenerPorId(idPaciente);
        boolean existenciaRegistro = registroExamenController.validarRadicadoExamen(radicadoExamen);
        Medico medicoTemp = medicosController.buscarMedicoPorId(medico);
        RegistroExamen registroExamen = registroExamenRepository.buscarPorRadicadoExamen(radicadoExamen);
        if (pacienteTemp != null && existenciaRegistro && medicoTemp != null) {
                if(registroExamen.isAutorizado()){
                Cita cita = new CitaExamen(pacienteTemp.getId(), registroExamen.getMotivoCitaExamen().getEspecialidad() ,registroExamen.getMotivoCitaExamen().getProfesionalAsignado(), pagado , radicadoExamen, registroExamen.isAutorizado()) ;
                citaRepository.guardarCita(cita);
                System.out.println("Cita agendada para el paciente: "+pacienteTemp.getNombre()+" "+pacienteTemp.getApellido()+
                        '\''+"Tu codigo de cita es:"+cita.getIdCita());

            }else{
                System.out.println("El examen no esta autorizado");
            }
        }else {
            System.out.println("El paciente/Examen/Medico no estan registrados en el sistema");
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




}
