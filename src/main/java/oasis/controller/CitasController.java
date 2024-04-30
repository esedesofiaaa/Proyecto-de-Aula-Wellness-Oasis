package oasis.controller;
import oasis.estructurasDatos.GeneradorCodigo;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.medico.Medico;
import oasis.model.domain.registroExamen.RegistroExamen;
import oasis.model.domain.cita.Cita;
import oasis.model.domain.paciente.Paciente;
import oasis.model.repository.CitaRepository;
import oasis.model.repository.PacientesRepository;
import oasis.model.repository.RegistroExamenRepository;

public class CitasController {

    private final CitaRepository citaRepository;
    private final PacientesRepository pacientesRepository;
    private final PacientesController pacientesController;
    private final RegistroExamenRepository registroExamenRepository;
    private final RegistroExamenController registroExamenController;
    GeneradorCodigo generadorCodigo = new GeneradorCodigo();

    private final MedicosController medicosController;

    // Constructor
    public CitasController() {
        this.registroExamenController = new RegistroExamenController();
        this.medicosController = new MedicosController();
        this.pacientesController = new PacientesController();
        this.registroExamenRepository = new RegistroExamenRepository();
        this.pacientesRepository = new PacientesRepository();
        this.citaRepository = new CitaRepository();

    }


    public void agendarCitaControlValoracion(String idPaciente, String motivoCita, String especialidad, String medico, boolean pagado) {
        // Ubica el paciente en la listaPacientes por su id, para evitar agregar todos los datos del paciente
        Paciente pacienteTemp = pacientesRepository.obtenerPorId(idPaciente);
        Medico medicoTemp = medicosController.buscarMedicoPorId(medico);

        if (pacienteTemp != null && medicoTemp != null) {
            // Verificar si el costo debe ser actualizado según el motivo de la cita y la especialidad
            Cita cita = new Cita(idPaciente, motivoCita, especialidad, medico, "No aplica", true, pagado);
            actualizarCostoCita(cita);
            citaRepository.guardarCita(cita);
            System.out.println("Cita agendada para el paciente: " + pacienteTemp.getNombre() + " " + pacienteTemp.getApellido() +
                    ". Tu código de cita es: " + cita.getIdCita());
        } else {
            System.out.println("El paciente o el médico no están registrados en el sistema.");
        }
    }

    public void agendarCitaExamen(String idPaciente, String radicadoExamen, String medico, boolean pagado) {
        Paciente pacienteTemp = pacientesRepository.obtenerPorId(idPaciente);
        boolean existenciaRegistro = registroExamenController.validarRadicadoExamen(radicadoExamen);
        Medico medicoTemp = medicosController.buscarMedicoPorId(medico);
        //En la vista solo debe dejar ver los medicos relacionados con la especialidad del examen par evitar conflictos
        //Ya existe ese metodo en MedicoController, sino hacer aca la validacion pero deberia caer sobre la parte visual
        RegistroExamen registroExamen = registroExamenRepository.buscarPorRadicadoExamen(radicadoExamen);
        if (pacienteTemp != null) {
            if (pacienteTemp.getId().equals(registroExamen.getIdPaciente())) {        //corroborar que el examen corresponda al id del paciente al crear la cita
                if (existenciaRegistro && medicoTemp != null) {
                    if (registroExamen.isAutorizado()) {
                        Cita cita = new Cita(pacienteTemp.getId(), "EXAMEN", registroExamen.getMotivoCitaExamen().getEspecialidad(), medico, radicadoExamen, registroExamen.isAutorizado(), pagado);
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
        } else {
            System.out.println("El paciente no esta registrado en el sistema");
        }
    }

    /**
     * Método para actualizar el costo de la cita según el motivo y la especialidad
     */

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


    public DoubleLinkedList<Cita> citasDelPaciente(String idPaciente) {
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
        Cita cita= citaRepository.buscarCitaPorId(idCita);
        if (cita != null) {
            citaRepository.eliminarCita(cita);
            switch (atributo) {
                case "motivoCita":
                    if("VALORACION".equals(valor)||"CONTROL".equals(valor)){
                        cita.setMotivoCita(valor);
                        cita.setRadicadoExamen("No aplica");
                        cita.setAutorizado(true);

                    }
                    //Falta cuando se convierte en Examen, hacer metodo para agregar los 2 atributos
                    //Que se elimine la cita y en la visual la redirija a crear una cita examen
                    break;
                case "medico":
                    cita.setMedico(valor);
                    //metodo para validar que el medico coincide con la especialidad
                    break;
                case "especialidad":
                    //si cambia la especialidad debe cambia el medico
                    //Desde la visual debe dejar escoger el medico, ya hay metodo
                    // que filtra a los medicos por especialidad
                    cita.setEspecialidad(valor);
                case "radicadoExamen":
                    if(cita.getMotivoCita().equals("EXAMEN")){
                    RegistroExamen registroExamen= registroExamenRepository.buscarPorRadicadoExamen(valor);
                        if (registroExamen!= null) {
                            //corroborar que el examen corresponda al id del paciente al crear la cita
                            if (cita.getIdPaciente().toString().equals(registroExamen.getIdPaciente().toString())) {
                                cita.setRadicadoExamen(valor);
                                cita.setEspecialidad(registroExamen.getMotivoCitaExamen().getEspecialidad());
                            } else {
                                System.out.println("El examen" + valor + "No pertenece al paciente" + cita.getIdPaciente());
                            }
                        } else{
                            System.out.println("El examen con radicado" + valor + "no existe");

                        }
                    } else {
                        System.out.println("La cita no es de tipo examen");
                    }
                break;
                default:
                    System.out.println("El atributo " + atributo + " no es válido.");
                    break;
                }
                cita.setIdCita(generadorCodigo.generarCodigo(cita.getMotivoCita(), cita.getEspecialidad()));
                citaRepository.guardarCita(cita);
                System.out.println("Cita modificada: " + cita.getIdCita());
            } else{
                System.out.println("La cita con id " + idCita + " no existe.");
            }
    }

    public void citaACitaExamen(String idCita, String atributo, String valor, String radicadoExamen, String medico, boolean pagado) {
        Cita cita = citaRepository.buscarCitaPorId(idCita);
        if (cita != null) {
            citaRepository.eliminarCita(cita);
            switch (atributo) {
                case "motivoCita":
                    if("EXAMEN".equals(valor)){
                        agendarCitaExamen(cita.getIdPaciente(), radicadoExamen, medico, pagado);
                    }
                    break;

                default:
                    System.out.println("El atributo " + atributo + " no es válido.");
                    break;
            }
        } else {
            System.out.println("La cita con id " + idCita + " no existe.");
        }
    }
        //Metodo para pagar cita
        public void pagarCita (String idCita){
            Cita cita = citaRepository.buscarCitaPorId(idCita);
            if (cita != null) {
                cita.setPagado(true);
                citaRepository.guardarCita(cita);
                System.out.println("La cita con id " + idCita + " ha sido pagada.");
            } else {
                System.out.println("La cita con id " + idCita + " no existe.");
            }
        }
        // 1 REVISAR SI FUNCIONA COMO TIPO CITA
        // 2 SI AL CAMBIAR EL MOTIVOCITA
        // A EXAMEN, COMO MANEJARLO (continue en la linea x), NO PASA NADA SI CAMBIA DE CONTROL A VALORACION
        // 3 PENSAR COMO SERIA EL FLUJO DESDE LA VISTA


        public void mostrarCitaPorId (String idCita){
            Cita cita = citaRepository.buscarCitaPorId(idCita);
            if (cita != null) {
                System.out.println(cita.toString());
            } else {
                System.out.println("La cita con id " + idCita + " no existe.");
            }
        }
        public String mostrarCitaPorIdPaciente (String idCita){
            Cita cita = citaRepository.buscarCitaPorId(idCita);
            if (cita != null) {
                String idPaciente = cita.getIdPaciente();
            }
            return null;
        }
// metodo que retorne el idPaciente de la cita que tenga como entrada el idCita

        //Metodo par amostrar todas las citas
        public void mostrarTodasLasCitas () {
                citaRepository.obtenerTodos().mostrarLista();
        }
    }

