package oasis.Main;

import oasis.controller.AutorizacionController;
import oasis.model.repository.AutorizacionRepository;

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

        /*    System.out.println(            autorizacionController.controlParaNoRepetirExamenParaAutorizar("N141"));
            //autorizacionRepository.actualizarAutorizacionExamen();*/

            autorizacionController.noAutorizarExamen();
            System.out.println(            autorizacionController.obtenerTodos());
      }
}
