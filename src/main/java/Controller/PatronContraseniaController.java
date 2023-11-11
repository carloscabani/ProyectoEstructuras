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
       aceptar();
       borrardigito();
       escrbir1();
       escrbir2();
       escrbir3();
       escrbir4();
       escrbir5();
       escrbir6();
       escrbir7();
       escrbir8();
       escrbir9();
       escrbir0();
       
    }    
    
    public void escrbir1() {
        bt1.setOnAction((ActionEvent e) -> {
            String tex = txpasword.getText()+"1";
            txpasword.setText(tex);
        });     
    }    
    public void escrbir2() {
        bt2.setOnAction((ActionEvent e) -> {
                        String tex = txpasword.getText()+"2";
            txpasword.setText(tex);
        });
    }
    public void escrbir3() {    
        bt3.setOnAction((ActionEvent e) -> {
                        String tex = txpasword.getText()+"3";
            txpasword.setText(tex);
        });
    }
    public void escrbir4() {
        bt4.setOnAction((ActionEvent e) -> {
            String tex = txpasword.getText()+"4";
            txpasword.setText(tex);
        });
    }
    public void escrbir5() {    
        bt5.setOnAction((ActionEvent e) -> {
            String tex = txpasword.getText()+"5";
            txpasword.setText(tex);
        });
    }
    public void escrbir6() {
        bt6.setOnAction((ActionEvent e) -> {
            String tex = txpasword.getText()+"6";
            txpasword.setText(tex);
        });
    }
    public void escrbir7() {    
        bt7.setOnAction((ActionEvent e) -> {
            String tex = txpasword.getText()+"7";
            txpasword.setText(tex);
        });
    }
    public void escrbir8() {    
        bt8.setOnAction((ActionEvent e) -> {
            String tex = txpasword.getText()+"8";
            txpasword.setText(tex);
        });
    }
    public void escrbir9() {
        bt9.setOnAction((ActionEvent e) -> {
            String tex = txpasword.getText()+"9";
            txpasword.setText(tex);
        });
    }
    public void escrbir0() {    
        bt10.setOnAction((ActionEvent e) -> {
            String tex = txpasword.getText()+"0";
            txpasword.setText(tex);
        });
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
