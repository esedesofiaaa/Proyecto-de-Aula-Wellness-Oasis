package controller;
import estructurasDatos.listas.DoubleLinkedList;
import model.Medico;
import model.MotivoCita.Control;
import model.RegistroExamen;
import model.domain.cita.Cita;
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
        if (pacienteTemp != null) {
            if (pacienteTemp.getId().equals(registroExamen.getIdPaciente())) {        //corroborar que el examen corresponda al id del paciente al crear la cita
                if (existenciaRegistro && medicoTemp != null) {
                    if (registroExamen.isAutorizado()) {
                        Cita cita = new CitaExamen(pacienteTemp.getId(), registroExamen.getMotivoCitaExamen().getEspecialidad(), registroExamen.getMotivoCitaExamen().getProfesionalAsignado(), pagado, radicadoExamen, registroExamen.isAutorizado());
                        citaRepository.guardarCita(cita);
                        System.out.println("Cita agendada para el paciente: " + pacienteTemp.getNombre() + " " + pacienteTemp.getApellido() +
                                '\'' + "Tu codigo de cita es:" + cita.getIdCita());

                    } else {
                        System.out.println("El examen no esta autorizado");
                    }
                } else {
                    System.out.println("El Examen o Medico no estan registrados en el sistema");
                }
            } else {
                System.out.println("El radicado de examen no corresponde al id del paciente");
            }
        }else {
            System.out.println("El paciente no esta registrado en el sistema");
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
            switch (atributo) {
                case "motivoCita":
                    if (valor.equals("EXAMEN")){
                        break;
                    }

                    break;
                case "medico":
                    cita.setMedico(valor);
                    citaRepository.guardarCita(cita);

                    break;
                case "especialidad":
                    cita.setEspecialidad(valor);
                    citaRepository.guardarCita(cita);

                    break;
                case "pagado":
                    cita.setPagado(Boolean.parseBoolean(valor));
                    citaRepository.guardarCita(cita);

                    break;
                default:
                    System.out.println("El atributo " + atributo + " no es válido.");
                    break;
            }
            System.out.println("Cita modificada: " + cita.getIdCita());
        } else {
            System.out.println("La cita con id " + idCita + " no existe.");
        }
    }
    // 1 REVISAR SI FUNCIONA COMO TIPO CITA
    // 2 SI AL CAMBIAR EL MOTIVOCITA
    // A EXAMEN, COMO MANEJARLO (continue en la linea x), NO PASA NADA SI CAMBIA DE CONTROL A VALORACION
    // 3 PENSAR COMO SERIA EL FLUJO DESDE LA VISTA


    public void modificarCitaExamen(String idCita, String atributo, String valor) {
        Cita cita = citaRepository.buscarCitaPorId(idCita);
        if (cita instanceof CitaExamen) {
            // Solo proceder si la cita es de tipo CitaExamen
            CitaExamen citaExamen = (CitaExamen) cita;
            citaRepository.eliminarCita(cita);
            switch (atributo) {
                case "motivoCita": //Control y valoracion, pasar de cita examen a Cita
                    Cita citaMap=  mapCitaExamenToCita(citaExamen);
                    citaMap.setMotivoCita(valor);
                    citaRepository.guardarCita(citaMap);
                    break;
                case "radicadoExamen":
                    if (registroExamenController.validarRadicadoExamen(valor)){//Revisar que el codigo si exista en registro examen
                        citaExamen.setRadicadoExamen(valor);
                    } else {
                        System.out.println("El radicado de examen " + valor + " no existe.");
                    }
                    citaRepository.guardarCita(citaExamen);
                    break; //Corroborar que no deje agregar radicados sin autorizar, sino no puede agendar la cita
                default:
                    System.out.println("El atributo " + atributo + " no es válido.");
                    break;
            }
            System.out.println("Cita modificada: " + citaExamen.getIdCita());
        } else {
            System.out.println("La cita con id " + idCita + " no es de tipo CitaExamen.");
        }
    }

    //Falta verificar que los medicos coincidan con la especialidad en ambos modificar
    //No funcionan los cast de CItaExamen a Cita
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

    //map CitaExamen to Cita
    private Cita mapCitaExamenToCita(CitaExamen citaExamen){
        Cita cita = new Cita(citaExamen.getIdPaciente(), citaExamen.getMotivoCita(), citaExamen.getEspecialidad(), citaExamen.getMedico(), citaExamen.isPagado());
        return  cita;
    }
    // map Cita to CitaExamen
    private CitaExamen mapCitaToCitaExamen(Cita cita){
        CitaExamen citaExamen = new CitaExamen(cita.getIdPaciente(), cita.getEspecialidad(), cita.getMedico(), cita.isPagado(), ((CitaExamen) cita).getRadicadoExamen(), ((CitaExamen) cita).isAutorizado());
        return citaExamen;
    }

}
