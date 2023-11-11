package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static String pathFiles = "src/main/resources/archivos/";
    public static String pics = "src/main/resources/pics/";
    public static String icons = "src/main/resources/icons/";
    

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 462, 598);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
//        Class<?> claseActual = App.class;
//
//         Obtener el ClassLoader para cargar recursos
//        ClassLoader classLoader = claseActual.getClassLoader();
//
//         Obtener la ruta del paquete
//        String nombrePaquete = claseActual.getPackage().getName();
//        String rutaPaquete = nombrePaquete.replace('.', '/');
//
//         Concatenar la ruta del paquete con el nombre del archivo
//        String nombreArchivo = "archivo.txt";
//        String rutaCompleta = rutaPaquete + "/" + nombreArchivo;
//
//         Imprimir la ruta completa
//        System.out.println("Ruta del archivo: " + rutaCompleta);

    }

}