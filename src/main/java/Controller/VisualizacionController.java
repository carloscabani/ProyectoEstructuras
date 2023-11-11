/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;


import Clases.Contacto;
import ListTDA.LLDouble;
import ListTDA.NodeList;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class VisualizacionController implements Initializable {

    @FXML
    private Label labelNombreCompleto;
    @FXML
    private Label labelTelefono;
    @FXML
    private Label labelDireccion;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelPersonAd;
    @FXML
    private Label labelRedes;
    @FXML
    private Label labelFecha;
    @FXML
    private Label labelEmpresa;
    
    private NodeList<Contacto> nodoActual ;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        nodoActual = ListaContactosController.listaContactos.getFirst();    
        
        Contacto persona = nodoActual.getContenido();
        String nombre = persona.getNombre();
        String apellido= persona.getApellido();
        String telefono = persona.getTlf().getTipoTlf() + ":"+ " "+persona.getTlf().getTlf();
        String direccion = persona.getDir().getTipoDireccion() +  ":"+ " " + persona.getDir().getUbicacion();
        String email = persona.getEmail().getTipo()+  ":"+ " "+ persona.getEmail().getCorreo();
        String personaAdicional = persona.getPer().getTipoPersona()+  ":"+ " "+ persona.getPer().getPersona();
        String fecha = persona.getFecha().getTipoFecha()+ ":"+ " "+persona.getFecha().getFecha();
        String redSocial = persona.getRedSocial().getTipoRedSocial()+  ":"+ " "+ persona.getRedSocial().getUsername();
        String empresa = persona.getEmpresa();
        
        labelNombreCompleto.setText(nombre+" "+apellido);
        labelTelefono.setText(telefono);
        labelDireccion.setText(direccion);
        labelEmail.setText(email);
        labelPersonAd.setText(personaAdicional);
        labelFecha.setText(fecha);
        labelEmpresa.setText("Empresa: "+empresa);
        labelRedes.setText(redSocial);       
        
       
}

    @FXML
    private void regresar(ActionEvent event) throws IOException {
    
        App.setRoot("ListaContactos");
    }

    @FXML
    private void continuarContacto(ActionEvent event){
//        int cont = 1;
//        Contacto persona = ListaContactosController.listaContactos.get(cont);

        nodoActual = ListaContactosController.listaContactos.getFirst().getSiguiente();
        
        Contacto persona = nodoActual.getContenido();

        
        
        if(ListaContactosController.listaContactos != null){
            String nombre = persona.getNombre();
            String apellido= persona.getApellido();
            String telefono = persona.getTlf().getTipoTlf() + ":"+ " "+persona.getTlf().getTlf();
            String direccion = persona.getDir().getTipoDireccion() +  ":"+ " " + persona.getDir().getUbicacion();
            String email = persona.getEmail().getTipo()+  ":"+ " "+ persona.getEmail().getCorreo();
            String personaAdicional = persona.getPer().getTipoPersona()+  ":"+ " "+ persona.getPer().getPersona();
            String fecha = persona.getFecha().getTipoFecha()+ ":"+ " "+persona.getFecha().getFecha();
            String redSocial = persona.getRedSocial().getTipoRedSocial()+  ":"+ " "+ persona.getRedSocial().getUsername();
            String empresa = persona.getEmpresa();

            labelNombreCompleto.setText(nombre+" "+apellido);
            labelTelefono.setText(telefono);
            labelDireccion.setText(direccion);
            labelEmail.setText(email);
            labelPersonAd.setText(personaAdicional);
            labelFecha.setText(fecha);
            labelEmpresa.setText("Empresa: "+empresa);
            labelRedes.setText(redSocial);       
            
//            cont++;
        }
        
    }
}
