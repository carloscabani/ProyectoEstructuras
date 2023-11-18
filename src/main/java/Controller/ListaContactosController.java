/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Clases.*;
import static Controller.CreacionContactosController.lstfotoPerfiles;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class ListaContactosController implements Initializable {
    
    public static Contacto contactoSelected;
    public static String nombreSelected;
    public static String apellidoSelected;
    public static String cadicional;
    public static String redsoc;
    public static String empre;
    public static String typec;
    public static String typered;
    public static int selectedIndex;
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
        
        listContactosSettings();
       
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
//            for(Contacto y: lstCamposAdicionales){
//                System.out.println(y.getPer().getPersona()+"-"+y.getPer().getTipoPersona());
//                System.out.println(y.getRedSocial().getUsername()+"-"+y.getRedSocial().getTipoRedSocial());
//                System.out.println(y.getEmpresa());
//                System.out.println(lstCamposAdicionales);
//                System.out.println(y.getPer().getPersona());
//                System.out.println(y.getRedSocial().getUsername());
//                System.out.println(y.getEmpresa());
//            }
        }else{
            System.out.println("listaCampoAdicionales llena");
        }   
        
        if (lstfotoPerfiles.isEmpty()) {
            cargarfotoPerfiles();
            System.out.println(lstfotoPerfiles);
        } else {
            System.out.println("lstfotoPerfiles ya esta cargada");
        }



        listViewContactos.getItems().clear();
        cargarListView();
        

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
        try (BufferedReader archivo = new BufferedReader(new FileReader("src/main/resources/archivos/CamposAdicionales.txt"))) {
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
    
    public void listContactosSettings(){
        listViewContactos.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // con esto obtegno el nombre del elemento cuando le hago click pilasssss
                //System.out.println("Elemento seleccionado: " + listViewContactos.getSelectionModel().getSelectedItem());
                Contacto selectedItem = listViewContactos.getSelectionModel().getSelectedItem();
                
                contactoSelected = selectedItem;
                nombreSelected= listViewContactos.getSelectionModel().getSelectedItem().getNombre();
                apellidoSelected = listViewContactos.getSelectionModel().getSelectedItem().getApellido();
                selectedIndex = listViewContactos.getSelectionModel().getSelectedIndex();
                System.out.println(selectedIndex);
//                  
//            // Verificar si se seleccionó un elemento
//            if (selectedIndex != -1) {
//                // Eliminar el elemento seleccionado
//                items.remove(selectedIndex);
//            }
//                
                
                
                for(Contacto cf: lstCamposAdicionales){
                    if(selectedItem.getNombre().equals(cf.getNombre()) && selectedItem.getApellido().equals(cf.getApellido())){
                        cadicional = cf.getPer().getPersona();
                        redsoc=cf.getRedSocial().getUsername();
                        empre = cf.getEmpresa();
                        typered= cf.getRedSocial().getTipoRedSocial();
                        typec= cf.getPer().getTipoPersona();
                    }
                }
                
                if (selectedItem != null) {
                    mostrarVentanaEmergente(selectedItem.getNombre()+" "+selectedItem.getApellido());
                }
            }
        });
    }
    
    private void mostrarVentanaEmergente(String selectedItem) {
        Stage ventanaEmergente = new Stage();
        ventanaEmergente.initModality(Modality.APPLICATION_MODAL);
        ventanaEmergente.setTitle("Opciones de Contacto");

        Label label = new Label("¿Qué deseas hacer con el contacto seleccionado?");
        Button btnEditar = new Button("Editar");
        Button btnEliminar = new Button("Eliminar");


        btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try { 
                    System.out.println("Editar contacto: " + selectedItem);
                    App.setRoot("EditarContacto");
                    ventanaEmergente.close();
                } catch (IOException ex) {
                    Logger.getLogger(ListaContactosController.class.getName()).log(Level.SEVERE, null, ex);
                }
         
            }
        });

        btnEliminar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para eliminar el contacto
                System.out.println("Eliminar contacto: " + selectedItem);
                ventanaEmergente.close();
            }
        });

        VBox vcontenedor=new VBox(10);
        HBox hbox = new HBox(10);
        HBox h1 = new HBox(10);
        

        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        h1.getChildren().addAll(btnEliminar,btnEditar);
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(30);
        vcontenedor.getChildren().addAll(hbox,h1);
        Scene escena = new Scene(vcontenedor, 300, 150);
      
        ventanaEmergente.setScene(escena);

        ventanaEmergente.showAndWait();
    }
    
    public void cargarfotoPerfiles(){
        try (BufferedReader archivo = new BufferedReader(new FileReader("src/main/resources/archivos/FotosPerfil.txt"))) {
            String datos;
            while ((datos = archivo.readLine()) != null) {
                String[] p = datos.split(",");
                String n= p[0];
                String ap=p[1];
                String fotopath=p[2];
                
                Foto f = new Foto(n,ap,fotopath);
                lstfotoPerfiles.add(f);
           }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
    


