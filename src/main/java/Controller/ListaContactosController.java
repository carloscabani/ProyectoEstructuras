/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Clases.*;
import ListTDA.LLDouble;
import ListTDA.NodeList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class ListaContactosController implements Initializable {
    
    public static LLDouble<Contacto> listaContactos = new LLDouble<>();
    public static ListView<Contacto> listViewContactos = new ListView<>();
    public static VBox contenerdorList = new VBox();
    public static String pathFiles = "archivos/";

    @FXML
    private HBox hbCabezera;
    @FXML
    private Label lbcontactos;
    @FXML
    private VBox vblista;
    
    @FXML
    private HBox hbAnadir;
    @FXML
    private Button btagregar;
    @FXML
    private TextField txtBuscador;
    @FXML
    private Button visualizarButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarListView();   
    }  


    public void cargarListView() {
        if (listViewContactos != null) {
            listViewContactos.getItems().clear();
            NodeList<Contacto> nodoActual = listaContactos.getFirst();
            while(nodoActual!= null){
                Contacto informacion = nodoActual.getContenido();
                //listViewContactos.getItems().add(informacion.getNombre());
                listViewContactos.getItems().add(informacion);
                nodoActual = nodoActual.getSiguiente();    
            }      
        }
        
        contenerdorList.getChildren().add(listViewContactos);
        vblista.getChildren().add(contenerdorList);
        
    }


    @FXML
    private void VentanaCrear(ActionEvent event) throws IOException {
        contenerdorList.getChildren().clear();
        App.setRoot("CreacionContactos");
        
        
    }

    @FXML
    private void limpiarTxtBuscador(MouseEvent event) {
        txtBuscador.clear();
    }

    @FXML
    private void visualizarInfo(ActionEvent event) throws IOException{
        App.setRoot("PerfilContacto");
    }
    
    public void actualizarlistaContactos(){
        
        Thread backgroundthread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(5000);
                        System.out.println("Hola");
                        Platform.runLater(() -> {                         
                            contenerdorList.getChildren().clear();
                            cargarListView();
                        });   
                    } 
                    catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }   
            }
        });
        backgroundthread.setDaemon(true);
        backgroundthread.start();
    }
}
    


