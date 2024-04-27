package oasis.aplicacion;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import oasis.controller.MedicosController;
import oasis.controller.PacientesController;
import oasis.model.domain.Especialidad;
import oasis.model.domain.medico.Medico;
import oasis.model.domain.paciente.TipoDocumento;

public class ViewRegistroMedicoController {

    //Atributos de la vista
    @FXML
    private ComboBox<Especialidad> tipoEspecialidadComboBox;
    @FXML
    private TextField idNombreMedico;
    @FXML
    private TextField idNumeroDeDocumentoMedico;

    private final MedicosController medicosController; //Se instancia el controlador de Medico
    public ViewRegistroMedicoController() {
        this.medicosController = new MedicosController();
    }//Constructor

    @FXML
    private void initialize() {
        // Agregar los tipos de documento al ComboBox
        tipoEspecialidadComboBox.getItems().addAll(
                Especialidad.CARDIOLOGIA,
                Especialidad.DERMATOLOGIA,
                Especialidad.NUTRICION,
                Especialidad.MEDICINA_GENERAL,
                Especialidad.ODONTOLOGIA,
                Especialidad.PEDIATRIA,
                Especialidad.PSIQUIATRIA
        );
    }//Elementos del combo box

    public void guardar(){
        // Obtener los valores de los campos de la vista
        /* Se debe declaran las variables que se quieren extraer con su tipo de variable y nombre y se igualan
         * a la get de los id de los campos de la vista, por eso estos nombres deben coincidir con los id creados
         * para cada campo*/
        Especialidad especialidad = tipoEspecialidadComboBox.getValue();
        String nombre = idNombreMedico.getText();
        String idMedico = idNumeroDeDocumentoMedico.getText();

        //Creamos un objeto de tipo medico
        Medico nuevoMedico = new Medico(nombre, especialidad, idMedico);

        //agregar el nuevo medico utilizando el controlador de paciente
        medicosController.agregarMedico(nuevoMedico);

        // Limpiar los campos después de agregar el paciente (opcional)
        limpiarCampos();

        System.out.println("Medico guardado con éxito");
    }//guardar

    private void limpiarCampos() {
        tipoEspecialidadComboBox.getSelectionModel().clearSelection();
        idNombreMedico.clear();
        idNumeroDeDocumentoMedico.clear();
    }//limpiarCampos
}