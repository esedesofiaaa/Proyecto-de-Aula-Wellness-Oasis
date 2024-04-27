package oasis.controller;

import oasis.model.domain.cita.Cita;
import oasis.model.repository.CitaRepository;
import oasis.model.repository.SalaEsperaRepository;

public class SalaEsperaController {

    private final SalaEsperaRepository salaEsperaRepository;
    private final CitaRepository citaRepository;
    private final FinalController finalController;

    public SalaEsperaController() {
        this.citaRepository = new CitaRepository();
        this.salaEsperaRepository = new SalaEsperaRepository();
        this.finalController = new FinalController();

    }

    public void registrarCita(String idCita){
        Cita cita = citaRepository.buscarCitaPorId(idCita);
        if (cita != null && cita.isPagado()) {
            salaEsperaRepository.agregarCitaASalaDeEspera(cita);
        } else {
            System.out.println("La cita no existe en el sistema o no ha sido pagada.");
        }

        /*//metodo de buscar cit apor id
        luego de verificar que si exista la cita y el examen este autorizado
        Crear metodo de buscar cita por idCita
        la cita pagada se agrega a la  cola sala de espera

        */
    }

    public void eliminarCitaDeSalaDeEspera(){
        salaEsperaRepository.actualizarCitaTomada();
        Cita cita = salaEsperaRepository.verCitaEnPantalla();
        finalController.agregarCitaAFinal(cita);
        salaEsperaRepository.eliminarCitaDeSalaDeEspera();
    }
   /* meotod para mostrar la cit aen pantalla, peek
    y tambien eliminarlo de historias de citas porque ya la tomo
    */


    //metodo para agregar el eliminado a la lista final
}
