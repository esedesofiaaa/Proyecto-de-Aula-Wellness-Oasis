package oasis.aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainAplicacion extends Application {

    @Override
    public void start(Stage primaryStage) {
        //start: nombre del metodo que va a ejecutar la ventana
        //Stage: Objeto de tipo ventana
        //primaryStage: argumento del metodo, es el elemento que representa la ventana
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/ViewRegistroPaciente.fxml"));
            /* EL metodo load lo que hace es crear instancias de los botones de la vista y cargarselos al AnchorPane
             * este metodo esta definido en la clase del lenguaje de FXMLLoader*/
            Scene scene = new Scene(root, 520, 740);
            /*Scene: escenas de la vista, este elemento es el que permite cambiar de escena de la vista, es decir
             * si se da click en un botton que lleve a otra vista este objeto de tipo Scene es el que permite cambiar
             * la escena de la vista, los numero son el tamaño de la scena*/
            primaryStage.setScene(scene); /* esta linea lo que se hace es decir cual es la primer scena
            por lo que le pasamos como argumento el elemento tipo Scene llamado Scene*/
            primaryStage.show(); //Para mostrar la escena
        }//try
        catch (Exception e) {//Manejo de la excepcion, falta definir como la vamos a manejar
            e.printStackTrace();
        }//catch
    }//start

    //segundo main para ejecutar la ventana
    public static void main(String[] args) {
        launch(args); //este metodo internamente llama al metodo start para ejecutar la ventana
    }//main
}
