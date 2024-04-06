package Main;

import controller.CitasController;
import estructurasDatos.listas.DoubleLinkedList;
import model.*;

public class Main {
    public static void main(String[] args) {
        // Crear instancia del controlador de citas
        CitasController citasController = new CitasController();

        // Crear pacientes
        Paciente paciente1 = new Paciente(TipoDocumento.CC, "1","Juan", "Pérez", "30", "123456789", "juan123@gmail");
        Paciente paciente2 = new Paciente(TipoDocumento.TI, "2","María", "Gómez", "25", "987654321", "maria456@gmail");

        MotivoCita motivoCita = new MotivoCitaValoracion("2000");

        // agregar pacientes a listaPaciente
        citasController.listaPacientes.agregarAlFinal(paciente1);
        citasController.listaPacientes.agregarAlFinal(paciente2);

        // Agendar citas para los pacientes
        citasController.agendarCita("1", Especialidad.GENERAL, "Dr. López", motivoCita);
        //citasController.agendarCita("2", Especialidad.CARDIOLOGIA, "Dr. Martínez", MotivoCita.EXAMENES);

        // Mostrar historial de citas de los pacientes
        paciente1.citasPaciente();
        paciente2.citasPaciente();
    }


}
