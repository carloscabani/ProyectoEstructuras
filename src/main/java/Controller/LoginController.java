/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class LoginController implements Initializable {

    public static Map<String,String> mapaUsuarios = new HashMap<>();
    @FXML
    private Label lbusuario;
    @FXML
    private Label lcconrasenia;
    @FXML
    private PasswordField txcontra;
    @FXML
    private TextField txuser;
    @FXML
    private Button btlogin;
    @FXML
    private Button btregistrer;
    @FXML
    private Label lbsms;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbsms.setText("");
        if (mapaUsuarios.isEmpty()) {
            cargarMapaUsuarios();
            System.out.println("Tu Mapa ha sido llenado correctamente.");
        } else {
            System.out.println("Tu mapa ya esta lleno.");
        }
        
    }    

    @FXML
    private void abrirVentanaPrincipal(ActionEvent event) throws IOException {
            for (Map.Entry<String, String> entry : mapaUsuarios.entrySet()) {
                if(!entry.getKey().equals(txuser.getText()) && !entry.getValue().equals(txcontra.getText())){
                    lbsms.setText("El usuario ingresado no existe, por favor registrese");
                }else if(entry.getKey().equals(txuser.getText()) && !entry.getValue().equals(txcontra.getText())){
                    lbsms.setText("Contraseña incorrecta");
                }else if (!entry.getKey().equals(txuser.getText()) && entry.getValue().equals(txcontra.getText())){
                    lbsms.setText("Usuario incorrecta");
                }else if (entry.getKey().equals(txuser.getText()) && entry.getValue().equals(txcontra.getText())){
                    App.setRoot("primary");
                }
            }        
        
        
    }

    @FXML
    private void abrirVentanaRegistrer(ActionEvent event) throws IOException {
        App.setRoot("registroUsuario");
    }
    
    public void cargarMapaUsuarios(){
        try (BufferedReader archivo = new BufferedReader(new FileReader("src/main/resources/archivos/Login.txt"))) {
            String datos;
            while ((datos = archivo.readLine()) != null) {
                String[] p = datos.split(",");
                String user= p[0];
                String pasword= p[1];
                
                mapaUsuarios.put(user, pasword);
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }        
        
        
    }
    
    
}
