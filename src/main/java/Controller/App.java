package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

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
        scene = new Scene(loadFXML("Login"), 462, 600);
        stage.setScene(scene);
        stage.setTitle("LinkBook");
        stage.getIcons().add(new Image("file:Image/IconApp.png"));
        
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

    }

}