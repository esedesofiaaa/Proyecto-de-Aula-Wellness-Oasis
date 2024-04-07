package Main;

import controller.AutorizacionController;
import model.repository.AutorizacionRepository;

public class MainAutorizaciones {
      public static void main(String[] args) {

            AutorizacionRepository autorizacionRepository = new AutorizacionRepository();

            AutorizacionController autorizacionController = new AutorizacionController();
/*
            autorizacionController.agregarPilaAutroizacion( "N141");
            autorizacionController.agregarPilaAutroizacion( "X911");
            autorizacionController.agregarPilaAutroizacion( "V566");
            autorizacionController.agregarPilaAutroizacion( "S953");
            autorizacionController.agregarPilaAutroizacion( "A637");
  */

            autorizacionController.autorizarExamen();

            //autorizacionRepository.actualizarAutorizacionExamen();


            //autorizacionController.obtenerTodos();

      }
}
