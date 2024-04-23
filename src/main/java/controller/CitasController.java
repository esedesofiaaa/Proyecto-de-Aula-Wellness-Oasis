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


    //Metodo para eliminar una cita por el idCita
    public void eliminarCita(String idCita) {
        Cita cita = citaRepository.buscarCitaPorId(idCita);
        if (cita != null) {
            citaRepository.eliminarCita(cita);
        } else {
            System.out.println("La cita con id " + idCita + " no existe.");
        }
    }
    //Metodo para mostrar modificar citas
    //Buscar la cita, eliminarla pero mantener ese valor temporalmente
    // y modificar el atributo seleccionado
    public void modificarCita(String idCita, String atributo, String valor) {
        Cita cita = citaRepository.buscarCitaPorId(idCita);


        if (cita != null) {
            citaRepository.eliminarCita(cita);
                if(cita.getMotivoCita().equals("EXAMEN")){

                    CitaExamen citaExamen = new CitaExamen(
                            cita.getIdPaciente(),
                            cita.getEspecialidad(),
                            cita.getMedico(),
                            cita.isPagado(),
                            ((CitaExamen) cita).getRadicadoExamen(), // Obtener el radicadoExamen de la cita original
                            ((CitaExamen) cita).isAutorizado()
                    );
                    //Atributos solo de CitaExamen
                    switch (atributo) {
                            case "radicadoExamen":
                                citaExamen.setRadicadoExamen(valor);
                                break;
                            default:
                                System.out.println("El atributo " + atributo + " no es válido.");
                                break;
                        }
                }
            //Atributos compartidos de Cita
            switch (atributo) {
                case "motivoCita":
                    cita.setMotivoCita(valor);
                    break;
                case "medico":
                    cita.setMedico(valor);
                    break;
                case "especialidad":
                    cita.setEspecialidad(valor);
                    break;
                case "pagado":
                    cita.setPagado(Boolean.parseBoolean(valor));
                    break;
                default:
                    System.out.println("El atributo " + atributo + " no es válido.");
                    break;
            }
            citaRepository.guardarCita(cita);
            System.out.println("Cita modificada: " + cita.getIdCita());
        } else {
            System.out.println("La cita con id " + idCita + " no existe.");
        }
    }
    // 1 REVISAR SI FUNCIONA COMO TIPO CITA
    // 2 SI AL CAMBIAR EL MOTIVOCITA
    // A EXAMEN, COMO MANEJARLO (continue en la linea x), NO PASA NADA SI CAMBIA DE CONTROL A VALORACION
    // 3 PENSAR COMO SERIA EL FLUJO DESDE LA VISTA

    public void mostrarCitas() {
        citaRepository.obtenerTodos().mostrarLista();
    }

    public void mostrarCitaPorId(String idCita) {
        Cita cita = citaRepository.buscarCitaPorId(idCita);
        if (cita != null) {
            System.out.println(cita.toString());
        } else {
            System.out.println("La cita con id " + idCita + " no existe.");
        }
    }


}
