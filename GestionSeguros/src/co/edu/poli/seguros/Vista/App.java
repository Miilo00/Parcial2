package co.edu.poli.seguros.Vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal que inicializa y lanza la aplicación JavaFX.
 * 
 * @author Cristian Camilo Vera
 * @version 1.0
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/poli/seguros/Vista/Seguros.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            primaryStage.setTitle("Gestión de Seguros - Poli");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}