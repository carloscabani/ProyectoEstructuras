/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Clases.*;
import ListTDA.ArrayG9;
import ListTDA.LLDouble;
import ListTDA.NodeList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public static ArrayG9<Contacto> lstCamposAdicionales = new ArrayG9<>();
    public static ListView<Contacto> listViewContactos = new ListView<>();
    public static VBox contenedorList = new VBox();
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
        if(listaContactos.isEmpty()){
          cargarListaContactos(); 
          System.out.println("listaContactos cargada");
         
            
        }else{
            System.out.println("listaContactos ya esta llena");
        }
        
        if(lstCamposAdicionales.isEmpty()){
            cargarlistaCamposAdicionales();
            System.out.println("listaCampoAdicionales cargada");
            System.out.println(lstCamposAdicionales);
            for(Contacto y: lstCamposAdicionales){
//                System.out.println(y.getPer().getPersona()+"-"+y.getPer().getTipoPersona());
//                System.out.println(y.getRedSocial().getUsername()+"-"+y.getRedSocial().getTipoRedSocial());
//                System.out.println(y.getEmpresa());
                System.out.println(lstCamposAdicionales);
                System.out.println(y.getPer().getPersona());
                System.out.println(y.getRedSocial().getUsername());
                System.out.println(y.getEmpresa());
            }
        }else{
            System.out.println("listaCampoAdicionales llena");
        }


//        cargarListaContactos();
//        cargarlistaCamposAdicionales();
        listViewContactos.getItems().clear();
        cargarListView();
//        actualizarlistaContactos();
//        if (!listViewContactos.getItems().isEmpty()) {
//            cargarListView();
//        }
    }  


    public void cargarListView() {
        
        Platform.runLater(() -> {
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
            if(!contenedorList.getChildren().contains(listViewContactos)){
                contenedorList.getChildren().add(listViewContactos);
                }
            vblista.getChildren().add(contenedorList);
    });
    }


    @FXML
    private void VentanaCrear(ActionEvent event) throws IOException {
        contenedorList.getChildren().clear();
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
    
    public void cargarListaContactos() {
        try (BufferedReader archivo = new BufferedReader(new FileReader("src/main/resources/archivos/Contactos.txt"))) {
            String datos;
            while ((datos = archivo.readLine()) != null) {
                String[] p = datos.split(",");
                String Nombre = p[0];
                String apellido=p[1];
                int caractel =p[2].indexOf("-");
                String cell =p[2].substring(caractel+1);
                String tipocell = p[2].substring(0,caractel);
                int caracDire= p[3].indexOf("-");
                String ubi = p[3].substring(caracDire+1);
                String tipoubi = p[3].substring(0,caracDire);
                int caracEmail = p[4].indexOf("-");
                String ema = p[4].substring(0, caracEmail);
                String tipoEma = p[4].substring(caracEmail+1);
                int caracFecha = p[5].indexOf("-");
                String da = p[5].substring(caracFecha+1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fec = LocalDate.parse(da, formatter);
                String tFecha = p[5].substring(0, caracFecha);
                                
                Contacto cont1 = new Contacto(Nombre,apellido,new Telefono(tipocell,cell),new Direccion(tipoubi,ubi),new Email(
                tipoEma,ema),new Fecha(tFecha,fec));
                
                System.out.println("Ya se cargo listaContactos con exito....");
                listaContactos.add(cont1);
                

           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void cargarlistaCamposAdicionales(){        
        try (BufferedReader archivo = new BufferedReader(new FileReader("src/main/resources/archivos/CamposAdiconales.txt"))) {
            String datos;
            while ((datos = archivo.readLine()) != null) {
                String[] p = datos.split(",");
                String nombre=p[0];
                String apellido=p[1];
                String tipoPer=null;
                String PerContac=null;
                String userRed=null;
                String tipoRed =null;
                String empresa = null;
                
                int caracperAdi = p[2].indexOf("-");
                if (p[2].substring(0,caracperAdi).equals("ninguno")) {
                    tipoPer = "ninguno";
                    PerContac = "ninguno";
                } else {
                    tipoPer = p[2].substring(caracperAdi+1);
                    PerContac = p[2].substring(0,caracperAdi);
                }
               
                int caracRed = p[3].indexOf("-");
                if (p[3].substring(caracperAdi).equals("ninguno")) {
                    userRed = "ninguno";
                    tipoRed = "ninguno";
                } else {
                    userRed = p[3].substring(caracRed+1);
                    tipoRed = p[3].substring(0,caracRed);
                }
                
                if(p[4].equals("ninguno")){
                    empresa = "ninguno";
                }else{
                    empresa = p[4];
                }
                
                
                Contacto camposCont1 = new Contacto(nombre,apellido,new PersonaAdiconal(PerContac,tipoPer), new RedSocial(tipoRed,userRed),empresa);
                
                System.out.println("YA SE CARGO CAMPO Adicional con exito........");
                lstCamposAdicionales.add(camposCont1);
                

           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    
        
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
//                            contenedorList.getChildren().clear();
                                cargarListView();
                                listViewContactos.getItems().clear();

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
    


