package oasis.aplicacion.controllerView;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oasis.controller.CitasController;
import oasis.controller.MedicosController;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.Especialidad;
import oasis.model.domain.cita.Cita;
import oasis.model.domain.medico.Medico;
import javafx.scene.control.ComboBox;
import oasis.model.repository.SalaEsperaRepository;


public class ViewRegistroCitaController {


    @FXML
    private TextField idDocuementoPaciente;

    @FXML
    private TextField idMotivoCita;

    @FXML
    private TextField idMedico;

    @FXML
    private TextField idPagado;

    @FXML
    private ComboBox<Especialidad> tipoEspecialidadComboBox;

    @FXML
    private ComboBox<String> idComboBoxMedicos;

    @FXML
    private Label idMensajeLabel;

    private final CitasController citaController;
    private final  SalaEsperaRepository salaEsperaRepository;

    private final MedicosController medicosController;
    private String idMedicoMomentario;


    public ViewRegistroCitaController() {
        this.medicosController = new MedicosController();
        this.citaController = new CitasController();
        this.salaEsperaRepository = new SalaEsperaRepository();
    }

    @FXML
    private void initialize() {
        // Agregar los tipos de especialidad al ComboBox
        tipoEspecialidadComboBox.getItems().addAll(
                Especialidad.CARDIOLOGIA,
                Especialidad.DERMATOLOGIA,
                Especialidad.NUTRICION,
                Especialidad.MEDICINA_GENERAL,
                Especialidad.ODONTOLOGIA,
                Especialidad.PEDIATRIA,
                Especialidad.PSIQUIATRIA
        );

        // Obtener la especialidad seleccionada del ComboBox

        tipoEspecialidadComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Limpiar los médicos anteriores
                idComboBoxMedicos.getItems().clear();
                // Obtener los nombres de los médicos por especialidad y agregarlos al ComboBox
                DoubleLinkedList<Medico> medicoEspecialidad = filtrarMedicosPorEspecialidad(tipoEspecialidadComboBox.getValue());
                for (int i = 0; i < medicoEspecialidad.tamano(); i++) {
                    Medico medico = medicoEspecialidad.buscarPorIndiceIterar(i);
                    idComboBoxMedicos.getItems().add(medico.getNombre());
                }

            }
        });

    }//Elementos del combo box

    @FXML
    private void guardarCita() {
        // Obtener los valores de los campos de la vista
        String documentoPaciente = idDocuementoPaciente.getText().trim();
        String motivoCita = idMotivoCita.getText().trim();
        String medico =medicosController.buscarIdPorNombre(idComboBoxMedicos.getValue()); // Obtener el valor del ComemboBox directamente
        Especialidad especialidad = tipoEspecialidadComboBox.getValue();
        boolean pagado = obtenerPagadoSeleccionado();

        if (documentoPaciente.isEmpty() || motivoCita.isEmpty() || medico.isEmpty() || especialidad == null) {
            idMensajeLabel.setText("Por favor completa todos los campos");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.RED);
            return; // Salir del método si falta algún dato
        }


        // Agregar la cita utilizando el controlador de citas
        Cita cita = citaController.agendarCitaControlValoracion(documentoPaciente, motivoCita, especialidad.toString(), medico, pagado);


        if (pagado){
            salaEsperaRepository.agregarCitaASalaDeEspera(cita);
            idMensajeLabel.setText("Cita agendada con éxito, puedes pasar a la sala de espera directamente");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
        } else{
            idMensajeLabel.setText("Cita agendada con éxito, recuerda pagar tu cita para ser atendido.");
            idMensajeLabel.setTextFill(javafx.scene.paint.Color.CORNFLOWERBLUE);
        }
        System.out.println("Cita guardada con éxito");

        // Limpiar los campos después de agregar la cita
        limpiarCampos();

    }//guardar

    private boolean obtenerPagadoSeleccionado() {
        // Lógica para obtener la opción de "true" o "false" del TextField, por ejemplo:
        String valorPagado = idPagado.getText().trim().toLowerCase(); // Convertir a minúsculas para ser case-insensitive
        return "true".equals(valorPagado); // Devuelve true si el texto es "true", de lo contrario, devuelve false
    }

    // Método para limpiar los campos de la vista
    private void limpiarCampos() {
        idDocuementoPaciente.clear();
        tipoEspecialidadComboBox.getSelectionModel().clearSelection();
        idMotivoCita.clear();
        idComboBoxMedicos.getSelectionModel().clearSelection();
        idPagado.clear();
    }//limpiarCampos

    //Metodo para filtrar los medicos por especialidad
    private DoubleLinkedList<Medico> filtrarMedicosPorEspecialidad(Especialidad especialidad) {
        // Este método debería devolver la lista de médicos filtrada por la especialidad
        return medicosController.buscarMedicoPorEspecialidad(especialidad);
    }//filtrarMedicosPorEspecialidad
}