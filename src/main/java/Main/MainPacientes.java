package Main;

import controller.CitasController;
import estructurasDatos.listas.DoubleLinkedList;
import model.*;
import model.repository.PacientesRepository;

public class MainPacientes {
    public static void main(String[] args) {

        //CAMBIAR LOS METODOS PARA QUE FUNCIONEN DESDE CONTROLLER Y NO REPOSITORY
        // Crear instancia del controlador de citas
        CitasController citasController = new CitasController();

        // Crear pacientes
        Paciente paciente1 = new Paciente(TipoDocumento.CC, "1","Juan", "Pérez", "30", "123456789", "juan123@gmail");
        Paciente paciente2 = new Paciente(TipoDocumento.TI, "2","María", "Gómez", "25", "987654321", "maria456@gmail");

        PacientesRepository pacientesRepository = new PacientesRepository();


        //Agregar 3 pacientes a una LinkedList de pacientes y luego usar el metodo añadirPacientes para agregarlos al Json
        DoubleLinkedList<Paciente> pacientes = new DoubleLinkedList<>();
        pacientes.agregarAlFinal(paciente1);
        pacientes.agregarAlFinal(paciente2);
        pacientes.agregarAlFinal(new Paciente(TipoDocumento.CC, "3","Pedro", "García", "40", "123456789", "pedro123@gmail"));
        pacientesRepository.añadirPacientes(pacientes);

        // agregar pacientes a listaPaciente
     //   citasController.listaPacientes.agregarAlFinal(paciente1);
      //  citasController.listaPacientes.agregarAlFinal(paciente2);

        pacientesRepository.obtenerTodos().mostrarLista();

        pacientesRepository.eliminarPaciente(paciente1);




        System.out.println("-------------------------------------");

        pacientesRepository.obtenerTodos().mostrarLista();





        /*// Agendar citas para los pacientes
        citasController.agendarCitaValoracion("1",false, false, Valoracion.MEDICINA_GENERAL );
        citasController.agendarCitaControl("2", false, false, Control.CARDIOLOGIA);

        // Mostrar historial de citas de los pacientes
        paciente1.citasPaciente();
        paciente2.citasPaciente();

         */
    }


}
