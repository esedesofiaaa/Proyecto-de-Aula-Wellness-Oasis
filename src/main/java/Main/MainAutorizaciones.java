package Main;

import controller.AutorizacionController;
import model.repository.AutorizacionRepository;

public class MainAutorizaciones {
      public static void main(String[] args) {

            AutorizacionRepository autorizacionRepository = new AutorizacionRepository();

            AutorizacionController autorizacionController = new AutorizacionController();

            autorizacionController.agregarPilaAutroizacion( "L080");

            autorizacionController.agregarPilaAutroizacion( "Y998");
            autorizacionController.agregarPilaAutroizacion( "P745");
            autorizacionController.agregarPilaAutroizacion( "S849");

           // autorizacionController.autorizarExamen();

            // autorizacionRepository.actualizarAutorizacionExamen();


            //autorizacionController.obtenerTodos();

      }
}
