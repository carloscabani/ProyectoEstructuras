/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Clases.Foto;
import static Controller.CreacionContactosController.lstfotoPerfiles;
import static Controller.ListaContactosController.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class VistaContactoIndividualController implements Initializable {

    @FXML
    private VBox vbportada;
    @FXML
    private ImageView photoIndividual;
    @FXML
    private Label lbna;
    @FXML
    private HBox hbicons1;
    @FXML
    private ImageView imgllamada1;
    @FXML
    private ImageView imgSMS1;
    @FXML
    private ImageView imgvideo1;
    @FXML
    private Pane paneDatos;
    @FXML
    private Label lce;
    @FXML
    private Label lper;
    @FXML
    private Label lred;
    @FXML
    private Label lbem;
    @FXML
    private Label lbdir;
    @FXML
    private Label lbfe;
    @FXML
    private Label lcorre;
    @FXML
    private Button btinicio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarInfo();
    }    

    @FXML
    private void volverListaContactos(ActionEvent event) {
        try {
            App.setRoot("ListaContactos");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Excepción no manejada: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void mostrarInfo(){
        lbna.setText(contactoSelected.getNombre()+" "+contactoSelected.getApellido());
        lce.setText(contactoSelected.getTlf().getTlf() + " - " + contactoSelected);
        lcorre.setText(contactoSelected.getEmail().getCorreo() + " - " + contactoSelected.getEmail().getTipo());
        lbfe.setText(contactoSelected.getFecha().getTipoFecha() + " - " + contactoSelected.getFecha().getFecha());
        System.out.println(cadicional);
        
//        if(cadicional == null){
//          lper.setText("ninguno");
//        }else{
//          lper.setText(cadicional+" - "+ typec);
//        }
        lper.setText(cadicional+" - "+ typec);
        lred.setText(redsoc+" - "+typered);
        lbem.setText(empre);

        lbdir.setText(contactoSelected.getDir().getUbicacion() + " - " + contactoSelected.getDir().getTipoDireccion());

        for (Foto f : lstfotoPerfiles) {
            if (f.getNombre().equals(contactoSelected.getNombre()) && f.getApellido().equals(contactoSelected.getApellido())) {
                try (FileInputStream input = new FileInputStream("src/main/resources/pics/"+f.getImagen())) {

                    Image image = new Image(input, 90, 100, true, false);
                    photoIndividual.setImage(image);

                } catch (IOException exep) {
                    System.out.println("error");
                }
            }
        }
    }
}
