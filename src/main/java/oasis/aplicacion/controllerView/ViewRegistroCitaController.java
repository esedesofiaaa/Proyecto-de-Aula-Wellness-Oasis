package oasis.aplicacion.controllerView;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import oasis.controller.CitasController;
import oasis.controller.MedicosController;
import oasis.estructurasDatos.listas.DoubleLinkedList;
import oasis.model.domain.Especialidad;
import oasis.model.domain.cita.Cita;
import oasis.model.domain.medico.Medico;
import javafx.scene.control.ComboBox;


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
    private ComboBox<DoubleLinkedList> idComboBoxMedicos;


    private final CitasController citaController;
    private  final MedicosController medicosController;


    public ViewRegistroCitaController( ) {
        this.medicosController = new MedicosController();
        this.citaController = new CitasController();
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
        Especialidad especialidadSeleccionada = tipoEspecialidadComboBox.getValue();

        // Verificar si la especialidad seleccionada no es null antes de continuar
        if (especialidadSeleccionada != null) {
            DoubleLinkedList<Medico> medicoEspecialidad = new DoubleLinkedList<Medico>();
            medicoEspecialidad = medicosController.buscarMedicoPorEspecialidad(especialidadSeleccionada.toString());

            idComboBoxMedicos.getItems().add(medicoEspecialidad);
            // Filtrar los médicos por la especialidad seleccionada y agregarlos al ComboBox
           /* for (int i = 0; i <medicoEspecialidad.tamano() ; i++) {
                idComboBoxMedicos.getItems().addAll(i);

            }*/

        }

        // Agregar un listener al ComboBox de especialidades para actualizar la lista de médicos
        tipoEspecialidadComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                idComboBoxMedicos.getItems().clear(); // Limpiar los médicos anteriores
                idComboBoxMedicos.getItems().addAll(filtrarMedicosPorEspecialidad(newValue)); // Agregar los nuevos médicos filtrados
            }
        });
    }//Elementos del combo box

    @FXML
    private void guardarCita (){
        // Obtener los valores de los campos de la vista
        /* Se debe declaran las variables que se quieren extraer con su tipo de variable y nombre y se igualan
         * a la get de los id de los campos de la vista, por eso estos nombres deben coincidir con los id creados
         * para cada campo*/
        String documentoPaciente = idDocuementoPaciente.getText().trim();
        String motivoCita = idMotivoCita.getText().trim();
        String medico = idMedico.getText().trim();
        Especialidad especialidad = tipoEspecialidadComboBox.getValue();
        boolean pagado = obtenerPagadoSeleccionado();

        if (documentoPaciente.isEmpty() || motivoCita.isEmpty() || medico.isEmpty() || especialidad == null) {
            System.out.println("Por favor completa todos los campos.");
            return; // Salir del método si falta algún dato
        }

        //agregar el nuevo medico utilizando el controlador de paciente
        citaController.agendarCitaControlValoracion(documentoPaciente, motivoCita, especialidad.toString(),medico,  pagado);

        // Limpiar los campos después de agregar el paciente (opcional)
        limpiarCampos();

        System.out.println("Cita guardada con éxito");
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
        idMedico.clear();
        idPagado.clear();
    }//limpiarCampos

    //Metodo para filtrar los medicos por especialidad
    private DoubleLinkedList<Medico> filtrarMedicosPorEspecialidad(Especialidad especialidad) {
        return medicosController.buscarMedicoPorEspecialidad(especialidad.toString());
    }//filtrarMedicosPorEspecialidad
}