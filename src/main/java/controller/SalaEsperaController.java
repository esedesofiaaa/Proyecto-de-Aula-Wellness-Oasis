package controller;

import model.repository.SalaEsperaRepository;

public class SalaEsperaController {

    private final SalaEsperaRepository salaEsperaRepository;

    public SalaEsperaController() {
        this.salaEsperaRepository = new SalaEsperaRepository();
    }

    public void registrarCita(String idCita){
        
        /*//metodo de buscar cit apor id
        luego de verificar que si exista la cita y el examen este autorizado
        Crear metodo de buscar cita por idCita
        la cita pagada se agrega a la  cola sala de espera

        */
    }

   /* meotod para mostrar la cit aen pantalla, peek
    y tambien eliminarlo de historias de citas porque ya la tomo
    */


    //metodo para agregar el eliminado a la lista final
}
