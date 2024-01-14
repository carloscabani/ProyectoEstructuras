/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class PatronContraseniaController implements Initializable {

    @FXML
    private Label lbmensaje;
    @FXML
    private PasswordField txpasword;
    @FXML
    private Button bt1;
    @FXML
    private Button bt2;
    @FXML
    private Button bt3;
    @FXML
    private Button bt4;
    @FXML
    private Button bt5;
    @FXML
    private Button bt6;
    @FXML
    private Button bt7;
    @FXML
    private Button bt8;
    @FXML
    private Button bt9;
    @FXML
    private Button btborrar;
    @FXML
    private Button bt10;
    @FXML
    private Button btavanzar;
    @FXML
    private Label lbconfirmacion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txpasword.setText("");
        asignarEventoBotonesNumericos();
        borrardigito();
        aceptar();
    }
    
    public void escribirDigito(String digito) {
        String tex = txpasword.getText() + digito;
        txpasword.setText(tex);
    }
    private void asignarEventoBotonesNumericos() {
        for (int i = 0; i <= 9; i++) {
            Button btn = obtenerBotonPorNumero(i);
            btn.setOnAction(e -> escribirDigito(String.valueOf(i)));
        }
    }

    private Button obtenerBotonPorNumero(int numero) {
        switch (numero) {
            case 1: return bt1;
            case 2: return bt2;
            case 3: return bt3;
            case 4: return bt4;
            case 5: return bt5;
            case 6: return bt6;
            case 7: return bt7;
            case 8: return bt8;
            case 9: return bt9;
            case 0: return bt10;
            default: return null;
        }
    }
        
    public void borrardigito() {
        btborrar.setOnAction((ActionEvent e) -> {
            String currentText = txpasword.getText();
            if (!currentText.isEmpty()) {
                // Eliminar el último carácter
                String newText = currentText.substring(0, currentText.length() - 1);
                txpasword.setText(newText);
            }
        });
    }

    public void aceptar() {
        btavanzar.setOnAction((ActionEvent e) -> {
            if(txpasword.getText().equals("2023545")){
                            
            try {
                App.setRoot("ListaContactos");
            } catch (IOException ex) {
                Logger.getLogger(PatronContraseniaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
                lbconfirmacion.setText("Pin incorrecto");
            }
            
            

        });
    }
}
