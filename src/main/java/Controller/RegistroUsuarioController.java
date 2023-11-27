/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import static Controller.LoginController.mapaUsuarios;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class RegistroUsuarioController implements Initializable {

    @FXML
    private Button btAceptar;
    @FXML
    private TextField txusuarioNuevo;
    @FXML
    private TextField txcontrasenia;
    @FXML
    private TextField txconfirmacionContra;
    @FXML
    private Label lberror;
    @FXML
    private ImageView imgvisto1;
    @FXML
    private ImageView imgvisto2;
    @FXML
    private ImageView imgvisto3;
    @FXML
    private AnchorPane paneRegistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lberror.setVisible(false);
    }    

    @FXML
    private void registrarUsuario(ActionEvent event) throws IOException {
        if (mapaUsuarios.containsKey(txusuarioNuevo.getText())) {
            lberror.setVisible(true);
        } else {
            escribirEnArchivoLogin();
            App.setRoot("Login");
        }
    }

    @FXML
    private void validarCasillasUsuarioLlena(MouseEvent event) {
        if (txusuarioNuevo.getText().equals("")) {
            try (FileInputStream input = new FileInputStream("src/main/resources/icons/incorrecto.png")) {
                Image image = new Image(input, 80, 90, true, false);
                imgvisto1.setImage(image);
            } catch (IOException exep) {
                System.out.println("error");
            }
        } else {
            try (FileInputStream input = new FileInputStream("src/main/resources/icons/correcto.jpg")) {
                Image image = new Image(input, 80, 90, true, false);
                imgvisto1.setImage(image);
            } catch (IOException exep) {
                System.out.println("error");
            }
        }
    }

    @FXML
    private void validarCasillasContraLlena(MouseEvent event) {
        if (txcontrasenia.getText().equals("")) {
            try (FileInputStream input = new FileInputStream("src/main/resources/icons/incorrecto.png")) {
                Image image = new Image(input, 80, 90, true, false);
                imgvisto2.setImage(image);
            } catch (IOException exep) {
                System.out.println("error");
            }
        } else {
            try (FileInputStream input = new FileInputStream("src/main/resources/icons/correcto.jpg")) {
                Image image = new Image(input, 80, 90, true, false);
                imgvisto2.setImage(image);
            } catch (IOException exep) {
                System.out.println("error");
            }
        }        
    }

    @FXML
    private void validarCasillaUltimaLlena(MouseEvent event) {
        if (txconfirmacionContra.getText().equals("")) {
            try (FileInputStream input = new FileInputStream("src/main/resources/icons/incorrecto.png")) {
                Image image = new Image(input, 80, 90, true, false);
                imgvisto3.setImage(image);
            } catch (IOException exep) {
                System.out.println("error");
            }
        } else {
            try (FileInputStream input = new FileInputStream("src/main/resources/icons/correcto.jpg")) {
                Image image = new Image(input, 80, 90, true, false);
                imgvisto3.setImage(image);
            } catch (IOException exep) {
                System.out.println("error");
            }
        }                
    }
    
    public void escribirEnArchivoLogin(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/Login.txt",true))) {
            String usuario  = txusuarioNuevo.getText();
            String pasword = txcontrasenia.getText();
            String cadena = usuario+","+pasword;
            writer.write(cadena);
            System.out.println("Se agrego el usuario al registro login.txt exitosamenteeeeee.......");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }        
    }
    
}

