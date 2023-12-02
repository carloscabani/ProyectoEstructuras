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
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    public static String contAdicional;
    public static String redSoc;
    public static String empresa;
    public static String typeContactAdicional;
    public static String typeRedSocial;
    public static int selectedIndex;
    public static Map<String,String> etiquetasPersonas = new HashMap<>();
    public static ListView<Contacto> viewContactosCopia = new ListView<Contacto>();
    public static LLDouble<Contacto> listaContactos = new LLDouble<>();
    public static ArrayG9<Contacto> lstCamposAdicionales = new ArrayG9<>();
    public static ListView<Contacto> listViewContactos = new ListView<>();
    public static VBox contenedorList = new VBox();
    public static String pathFiles = "archivos/";
    public static Label ltitleApellido= new Label("Ingrese un apellido:");
    public static Label ltitleDireccion= new Label("Ingrese una direccion:");
    public static Label ltitleConAdicional= new Label("Ingrese un contacto asociado:");
    public static Label ltitlEtiquetas= new Label("seleccione una etiqueta:");
    public static ComboBox<String> cbEContacto= new ComboBox();
    public static TextField txdatoAbuscar = new TextField();
    public static VBox ponertexto = new VBox();

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
    @FXML
    private RadioButton rdapellido;
    @FXML
    private ToggleGroup option1;
    @FXML
    private RadioButton rdireccion;
    @FXML
    private RadioButton rdcontactoasociado;
    @FXML
    private VBox vbdatosbusqueda;
    @FXML
    private Button btocultar;
    @FXML
    private VBox vbcontenedorSecundario;
    @FXML
    private HBox hbotonesBusqueda;
    @FXML
    private Button btbuscar;
    @FXML
    private RadioButton rdEtiquetas;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listViewContactos.getStyleClass().add("list-view");
        listViewContactos.setOnMouseClicked(this::listContactosSettings);
        viewContactosCopia.setOnMouseClicked(this::listContactosCopiaSettings);
        
        if(etiquetasPersonas.isEmpty()){
            cargarMapaEtiquetas();
            System.out.println("Se cargo el mapa exitosamente............");
        }else{
            System.out.println("El mapa de etiquetas ya esta cargado..........");
        }
        
       
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
        }else{
            System.out.println("listaCampoAdicionales llena");
        }   
        
        if (lstfotoPerfiles.isEmpty()) {
            cargarfotoPerfiles();
            System.out.println(lstfotoPerfiles);
        } else {
            System.out.println("lstfotoPerfiles ya esta cargada");
        }
        

        cargarListView();
        txtBuscador.requestFocus();


        txtBuscador.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrarContactos(newValue);
        });
        

    } 
    
    public void filtrarContactos(String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            vblista.getChildren().clear();
            cargarListView();
        } else {
            Platform.runLater(() -> {
                listViewContactos.getItems().clear();
                NodeList<Contacto> nodoActual = listaContactos.getFirst();
                while (nodoActual != null) {
                    Contacto informacion = nodoActual.getContenido();
                    // Si el nombre del contacto contiene el filtro, agrégalo al ListView
                    if (informacion.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                        listViewContactos.getItems().add(informacion);
                    }
                    nodoActual = nodoActual.getSiguiente();
                }
            });
        }
    }

    public void cargarListView() {

        if (listViewContactos != null) {
            listViewContactos.getItems().clear();
            for(Contacto c : listaContactos ){
                listViewContactos.getItems().add(c);
            }
        }
        if (!contenedorList.getChildren().contains(listViewContactos)) {
            contenedorList.getChildren().add(listViewContactos);
        }
        vblista.getChildren().add(contenedorList);
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
        System.out.println("------------------------------------------------------------");

    try (BufferedReader archivo = new BufferedReader(new FileReader("src/main/resources/archivos/CamposAdicionales.txt"))) {
        String datos;

        while ((datos = archivo.readLine()) != null) {
            System.out.println("Datos leídos: " + datos);
            datos+=" ";
            String[] p = datos.split(",");
            if (p.length >= 5) {
                String nombre = p[0].trim();
                String apellido = p[1].trim();
                String tipoPer = obtenerDato(p[2], 1).trim();
                String PerContac = obtenerDato(p[2], 0).trim();
                String tipoRed = obtenerDato(p[3], 1).trim();
                String userRed = obtenerDato(p[3], 0).trim();
                String empresa = p[4].trim();

                System.out.println("Persona adicional: " + tipoPer + " " + PerContac);
                System.out.println("RRSS: " + tipoRed + " " + userRed);
                System.out.println("Empresa: " + empresa);
                System.out.println("////////////");

                Contacto camposCont1 = new Contacto(nombre, apellido, new PersonaAdicional(PerContac, tipoPer), new RedSocial(tipoRed, userRed), empresa);

                System.out.println("YA SE CARGO CAMPO Adicional con exito........");
                lstCamposAdicionales.add(camposCont1);
                System.out.println("Datos guardados: " + nombre+", "+ apellido+", "+ 
                        tipoPer+" "+PerContac+", "+ tipoRed+" "+userRed+", "+ empresa );
                System.out.println("------------------------------------------------------------");
            } else {
                System.out.println("Formato incorrecto en la línea: " + datos);
            }
        }

        System.out.println("listaCampoAdicionales cargada");
        System.out.println(lstCamposAdicionales);
        
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }

    }
    
    private String obtenerDato(String cadena, int indice) {
        String[] datos = cadena.split("-");
        return (datos.length > indice) ? datos[indice].trim() : "ninguno";
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

    public void listContactosSettings(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Contacto selectedItem = listViewContactos.getSelectionModel().getSelectedItem();

            contactoSelected = selectedItem;
            nombreSelected = listViewContactos.getSelectionModel().getSelectedItem().getNombre();
            apellidoSelected = listViewContactos.getSelectionModel().getSelectedItem().getApellido();
            selectedIndex = listViewContactos.getSelectionModel().getSelectedIndex();

            for (Contacto cf : lstCamposAdicionales) {
                if (selectedItem.getNombre().equals(cf.getNombre()) && selectedItem.getApellido().equals(cf.getApellido())) {
                    contAdicional = cf.getPer().getPersona();
                    redSoc = cf.getRedSocial().getUsername();
                    empresa = cf.getEmpresa();
                    typeRedSocial = cf.getRedSocial().getTipoRedSocial();
                    typeContactAdicional = cf.getPer().getTipoPersona();
                }
            }

            if (selectedItem != null) {
                mostrarVentanaEmergente(selectedItem.getNombre() + " " + selectedItem.getApellido());
            }

        } else if (event.getButton() == MouseButton.SECONDARY) {
            Contacto selectedItem = listViewContactos.getSelectionModel().getSelectedItem();

            contactoSelected = selectedItem;
            System.out.println("este contacto es: " + contactoSelected);
            nombreSelected = listViewContactos.getSelectionModel().getSelectedItem().getNombre();
            apellidoSelected = listViewContactos.getSelectionModel().getSelectedItem().getApellido();
            selectedIndex = listViewContactos.getSelectionModel().getSelectedIndex();

            VentanaEtiquetas(contactoSelected);

        }
    }
    
    public void listContactosCopiaSettings(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            Contacto selectedItem = viewContactosCopia.getSelectionModel().getSelectedItem();
            contactoSelected = selectedItem;
            System.out.println("este contacto es: "+contactoSelected);
            nombreSelected = viewContactosCopia.getSelectionModel().getSelectedItem().getNombre();
            apellidoSelected = viewContactosCopia.getSelectionModel().getSelectedItem().getApellido();
            selectedIndex = viewContactosCopia.getSelectionModel().getSelectedIndex();

            for (Contacto cf : lstCamposAdicionales) {
                if (selectedItem.getNombre().equals(cf.getNombre()) && selectedItem.getApellido().equals(cf.getApellido())) {
                    contAdicional = cf.getPer().getPersona();
                    redSoc = cf.getRedSocial().getUsername();
                    empresa = cf.getEmpresa();
                    typeRedSocial = cf.getRedSocial().getTipoRedSocial();
                    typeContactAdicional = cf.getPer().getTipoPersona();
                }
            }

            if (selectedItem != null) {
                mostrarVentanaEmergente(selectedItem.getNombre() + " " + selectedItem.getApellido());
            }

        } else if (event.getButton() == MouseButton.SECONDARY) {
            System.out.println("Clic derecho en la ListViewCopia");
            Contacto selectedItem = viewContactosCopia.getSelectionModel().getSelectedItem();

            contactoSelected = selectedItem;
            System.out.println("este contacto es: "+contactoSelected);
            nombreSelected = viewContactosCopia.getSelectionModel().getSelectedItem().getNombre();
            apellidoSelected = viewContactosCopia.getSelectionModel().getSelectedItem().getApellido();
            selectedIndex = viewContactosCopia.getSelectionModel().getSelectedIndex();            
            
            if (selectedItem != null) {
                VentanaEtiquetas(contactoSelected);
            }
            
            
        }
    }
    
    
    public void mostrarVentanaEmergente(String selectedItem) {
        Stage ventanaEmergente = new Stage();
        ventanaEmergente.initModality(Modality.APPLICATION_MODAL);
        ventanaEmergente.setTitle("Opciones de Contacto");

        Label label = new Label("¿Qué deseas hacer con el contacto seleccionado?");
        Button btnEditar = new Button("Editar");
        Button btnverConta = new Button("Ver contacto");


        btnEditar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try { 
                    System.out.println("Editar contacto: " + selectedItem);
                    String[] palabras = selectedItem.split("\\s+");
                    
                    
                    if (palabras.length == 1) {
                        System.out.println("Es una empresa: " + selectedItem);
                        
                        App.setRoot("EditarEmpresa");
                    } else {
                        System.out.println("Es una persona: " + selectedItem);
                        
                        App.setRoot("EditarContacto");
                    }
                    
                    ventanaEmergente.close();
                } catch (IOException ex) {
                    Logger.getLogger(ListaContactosController.class.getName()).log(Level.SEVERE, null, ex);
                }
         
            }
        });

        btnverConta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                    App.setRoot("VistaContactoIndividual");
                } catch (IOException ex) {
                    Logger.getLogger(ListaContactosController.class.getName()).log(Level.SEVERE, null, ex);
                }
                contenedorList.getChildren().clear();
                ventanaEmergente.close();
            }
        });

        VBox vcontenedor=new VBox(10);
        HBox hbox = new HBox(10);
        HBox h1 = new HBox(10);
        

        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        h1.getChildren().addAll(btnverConta,btnEditar);
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(30);
        vcontenedor.getChildren().addAll(hbox,h1);
        
        vcontenedor.setStyle("-fx-background-color: #FFE4C4");
        btnverConta.setStyle("-fx-background-color: #FA8072; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 5, 5);");        
        btnEditar.setStyle("-fx-background-color: #FA8072; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 5, 5);");        
        
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

    
    public void ventanaBusqueda(String selectedItem) {
        Stage ventanaEmergente = new Stage();
        ventanaEmergente.initModality(Modality.APPLICATION_MODAL);
        ventanaEmergente.setTitle("Busqueda Avanzada");

        Label label = new Label("¿Por cual campo desea realizar su busqueda?");
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

        VBox vcontenedor = new VBox(10);
        HBox hbox = new HBox(10);
        HBox h1 = new HBox(10);

        hbox.getChildren().add(label);
        hbox.setAlignment(Pos.CENTER);
        h1.getChildren().addAll(btnEliminar, btnEditar);
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(30);
        vcontenedor.getChildren().addAll(hbox, h1);
        Scene escena = new Scene(vcontenedor, 300, 150);

        ventanaEmergente.setScene(escena);

        ventanaEmergente.showAndWait();
    }
    

    @FXML
    private void buscarPorApellido(ActionEvent event) {
         if (vbdatosbusqueda.getChildren().isEmpty()) {
            vbdatosbusqueda.getChildren().addAll(ltitleApellido, txdatoAbuscar);
            vbdatosbusqueda.setSpacing(10);
        } else {
            txdatoAbuscar.clear(); 
            vbdatosbusqueda.getChildren().clear();
            vbdatosbusqueda.getChildren().addAll(ltitleApellido, txdatoAbuscar);
            vbdatosbusqueda.setSpacing(10);
        }

    }

    @FXML
    private void buscarPorDireccion(ActionEvent event) {
         if (vbdatosbusqueda.getChildren().isEmpty()) {
            vbdatosbusqueda.getChildren().addAll(ltitleDireccion, txdatoAbuscar);
            vbdatosbusqueda.setSpacing(10);
        } else {
            txdatoAbuscar.clear();
            vbdatosbusqueda.getChildren().clear();
            vbdatosbusqueda.getChildren().addAll(ltitleDireccion, txdatoAbuscar);
            vbdatosbusqueda.setSpacing(10);
        }
    }

    @FXML
    private void buscarPorContactoAsociado(ActionEvent event) {
        
         if (vbdatosbusqueda.getChildren().isEmpty()) {
            vbdatosbusqueda.getChildren().addAll(ltitleConAdicional, txdatoAbuscar);
            vbdatosbusqueda.setSpacing(10);
        } else {
            txdatoAbuscar.clear(); 
            vbdatosbusqueda.getChildren().clear();
            vbdatosbusqueda.getChildren().addAll(ltitleConAdicional, txdatoAbuscar);
            vbdatosbusqueda.setSpacing(10);
        }
    }
    
    @FXML
    private void buscarPorEtiquetas(ActionEvent event) {
        cbEContacto.setValue("");
        if(cbEContacto.getItems().isEmpty()){
            cbEContacto.getItems().addAll("Familia","Trabajo","Amigos","Universidad");
        }
        

        if (vbdatosbusqueda.getChildren().isEmpty()) {
            vbdatosbusqueda.getChildren().addAll(ltitlEtiquetas, cbEContacto);
            vbdatosbusqueda.setSpacing(10);
        } else {
            txdatoAbuscar.clear();
            vbdatosbusqueda.getChildren().clear();
            vbdatosbusqueda.getChildren().addAll(ltitlEtiquetas, cbEContacto);
            vbdatosbusqueda.setSpacing(10);
        }

    }

    @FXML
    private void ocultarBusqueda(ActionEvent event) {
        
        vbdatosbusqueda.getChildren().clear();
        option1.getSelectedToggle().setSelected(false);
        contenedorList.getChildren().clear(); 
        vblista.getChildren().clear();  
        
        cargarListView();
    }
    
    public void VentanaEtiquetas(Contacto co) {
        Stage g = new Stage();
        Label label = new Label("Seleccione una etiqueta para: "+co.getNombre()+" "+co.getApellido());
        Button btguardar = new Button("Aceptar");
        RadioButton rdfamilia = new RadioButton ("Familia");
        RadioButton rdTrabajo = new RadioButton ("Trabajo");
        RadioButton rdAmigos = new RadioButton ("Amigos");
        RadioButton rdUniversidad = new RadioButton ("Universidad");
        ImageView imEtiquetas = new ImageView();
        ToggleGroup EtiquetasContactos = new ToggleGroup();
        rdfamilia.setToggleGroup(EtiquetasContactos);
        rdTrabajo.setToggleGroup(EtiquetasContactos);
        rdAmigos.setToggleGroup(EtiquetasContactos);
        rdUniversidad.setToggleGroup(EtiquetasContactos);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        rdfamilia.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        rdTrabajo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        rdAmigos.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        rdUniversidad.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        HBox hcontenedor = new HBox();
        hcontenedor.getChildren().add(label);
        hcontenedor.setPrefWidth(500);
        hcontenedor.setPrefHeight(58);
        hcontenedor.setAlignment(Pos.CENTER);
        HBox hradioButtons = new HBox();
        hradioButtons.getChildren().addAll(rdfamilia,rdTrabajo,rdAmigos,rdUniversidad);
        hradioButtons.setPrefWidth(500);
        hradioButtons.setPrefHeight(65);
        hradioButtons.setAlignment(Pos.CENTER);
        hradioButtons.setSpacing(25);
        VBox h1 = new VBox();

        VBox rootNuevo = new VBox();
        rootNuevo.getChildren().addAll(hcontenedor, hradioButtons, h1);

        rdfamilia.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (h1.getChildren().isEmpty()) {
                    try (FileInputStream input = new FileInputStream("src/main/resources/icons/familia.gif")) {
                        Image image = new Image(input, 150, 180, true, false);
                        imEtiquetas.setImage(image);
                    } catch (IOException exep) {
                        System.out.println("error");
                    }
                    h1.getChildren().addAll(imEtiquetas, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(20);
                } else {
                    h1.getChildren().clear();
                    try (FileInputStream input = new FileInputStream("src/main/resources/icons/familia.gif")) {
                        Image image = new Image(input, 150, 180, true, false);
                        imEtiquetas.setImage(image);
                    } catch (IOException exep) {
                        System.out.println("error");
                    }
                    h1.getChildren().addAll(imEtiquetas, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(10);
                }
            }

        });
        rdTrabajo.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (h1.getChildren().isEmpty()) {
                    try (FileInputStream input = new FileInputStream("src/main/resources/icons/trabajo.gif")) {
                        Image image = new Image(input, 150, 180, true, false);
                        imEtiquetas.setImage(image);
                    } catch (IOException exep) {
                        System.out.println("error");
                    }
                    h1.getChildren().addAll(imEtiquetas, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(20);
                } else {
                    h1.getChildren().clear();
                    try (FileInputStream input = new FileInputStream("src/main/resources/icons/trabajo.gif")) {
                        Image image = new Image(input, 150, 180, true, false);
                        imEtiquetas.setImage(image);
                    } catch (IOException exep) {
                        System.out.println("error");
                    }
                    h1.getChildren().addAll(imEtiquetas, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(10);
                }
            }

        });
        rdAmigos.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (h1.getChildren().isEmpty()) {
                    try (FileInputStream input = new FileInputStream("src/main/resources/icons/amigos.jpg")) {
                        Image image = new Image(input, 150, 180, true, false);
                        imEtiquetas.setImage(image);
                    } catch (IOException exep) {
                        System.out.println("error");
                    }
                    h1.getChildren().addAll(imEtiquetas, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(20);
                } else {
                    h1.getChildren().clear();
                    try (FileInputStream input = new FileInputStream("src/main/resources/icons/amigos.jpg")) {
                        Image image = new Image(input, 150, 180, true, false);
                        imEtiquetas.setImage(image);
                    } catch (IOException exep) {
                        System.out.println("error");
                    }
                    h1.getChildren().addAll(imEtiquetas, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(10);
                }
            }

        });
        rdUniversidad.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                if (h1.getChildren().isEmpty()) {
                    try (FileInputStream input = new FileInputStream("src/main/resources/icons/universidad.jpg")) {
                        Image image = new Image(input, 150, 180, true, false);
                        imEtiquetas.setImage(image);
                    } catch (IOException exep) {
                        System.out.println("error");
                    }
                    h1.getChildren().addAll(imEtiquetas, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(20);
                } else {
                    h1.getChildren().clear();
                    try (FileInputStream input = new FileInputStream("src/main/resources/icons/universidad.jpg")) {
                        Image image = new Image(input, 150, 180, true, false);
                        imEtiquetas.setImage(image);
                    } catch (IOException exep) {
                        System.out.println("error");
                    }
                    h1.getChildren().addAll(imEtiquetas, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(10);
                }
            }

        });  
        
        
        rootNuevo.setStyle("-fx-background-color: #FFE4C4");
        btguardar.setStyle("-fx-background-color: #FA8072; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 5, 5);");        
        
        Scene s = new Scene(rootNuevo, 500, 350);

        
        g.setScene(s);
        g.setTitle("Etiquetas");
        g.show();
        


        btguardar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(rdfamilia.isSelected()){
                    etiquetasPersonas.put(co.getNombre()+" "+co.getApellido(), "Familia" );
                    escribirArchivoEtiquetas(co.getNombre(), co.getApellido(), "Familia");
                }else if(rdTrabajo.isSelected()){
                    etiquetasPersonas.put(co.getNombre()+" "+co.getApellido(), "Trabajo" );
                    escribirArchivoEtiquetas(co.getNombre(), co.getApellido(), "Trabajo");
                }else if (rdAmigos.isSelected()){
                    etiquetasPersonas.put(co.getNombre()+" "+co.getApellido(), "Amigos" );
                    escribirArchivoEtiquetas(co.getNombre(), co.getApellido(), "Amigos");
                }else{
                    etiquetasPersonas.put(co.getNombre()+" "+co.getApellido(), "Universidad" );
                    escribirArchivoEtiquetas(co.getNombre(), co.getApellido(), "Universidad");
                }
                

                g.close();
         
            }
        });

    }
    
    public void escribirArchivoEtiquetas(String name, String ape, String etique) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/Etiquetas.txt", true))) {
            String cadena= name+","+ape+","+etique;
            writer.write(cadena);
            System.out.println("Se escribio la etiqueta del Contacto exitosamenteeeeee.......");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public void cargarMapaEtiquetas() {
        try (BufferedReader archivo = new BufferedReader(new FileReader("src/main/resources/archivos/Etiquetas.txt"))) {
            String datos;
            while ((datos = archivo.readLine()) != null) {
                String[] p = datos.split(",");
                String Contacto = p[0]+" "+p[1];
                String etiqueta = p[2];
                etiquetasPersonas.put(Contacto, etiqueta);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    @FXML
    private void realizarBusquedaPersonalizada(ActionEvent event) {
        RadioButton selectedRadioButton = (RadioButton) option1.getSelectedToggle();
        if (selectedRadioButton != null) {
            String tipoBusqueda = selectedRadioButton.getId();

            String valorBusqueda = txdatoAbuscar.getText();
            String etiquetaBox = cbEContacto.getValue();

            switch (tipoBusqueda) {
                case "rdapellido":
                    filtrarPorApellido(valorBusqueda);
                    break;
                case "rdireccion":
                    filtrarPorDireccion(valorBusqueda);
                    break;
                case "rdcontactoasociado":
                    filtrarPorContactoAsociado(valorBusqueda);
                    break;
                case "rdEtiquetas":
                    filtrarPorEtiquetas(etiquetaBox);
                    break;                    
                default:
                    break;
            }
        }
        
    }

    public void filtrarPorApellido(String apellido) {

        contenedorList.getChildren().clear();
        vblista.getChildren().clear();
        viewContactosCopia.getItems().clear();

        if (viewContactosCopia.getItems().isEmpty()) {

            for (Contacto contc : listaContactos) {
                if (contc.getApellido().equalsIgnoreCase(apellido)) {
                    viewContactosCopia.getItems().add(contc);
                }

            }

        }
        System.out.println("lista copia despues de haber pasado por el while:");
        for (Contacto item : viewContactosCopia.getItems()) {
            System.out.println(item);
        }

        contenedorList.getChildren().add(viewContactosCopia);
        vblista.getChildren().add(contenedorList);

    }



    public void filtrarPorDireccion(String direccion) {
        contenedorList.getChildren().clear();
        vblista.getChildren().clear();
        viewContactosCopia.getItems().clear();

        if (viewContactosCopia.getItems().isEmpty()) {

            for (Contacto contc : listaContactos) {
                if (contc.getDir().getUbicacion().equalsIgnoreCase(direccion)) {
                    viewContactosCopia.getItems().add(contc);
                }

            }

        }
        System.out.println("lista copia despues de haber pasado por el while:");
        for (Contacto item : viewContactosCopia.getItems()) {
            System.out.println(item);
        }
        contenedorList.getChildren().add(viewContactosCopia);
        vblista.getChildren().add(contenedorList);
        
        
        
        
    }

    public void filtrarPorContactoAsociado(String contactoAsociado) {

        contenedorList.getChildren().clear();
        vblista.getChildren().clear();
        viewContactosCopia.getItems().clear();

        if (viewContactosCopia.getItems().isEmpty()) {

            for (Contacto contc : lstCamposAdicionales) {
                if (contc.getPer().getPersona().equalsIgnoreCase(contactoAsociado)) {
                    for(Contacto contcoriginal:listaContactos ){
                        if(contc.getNombre().equals(contcoriginal.getNombre())&&contc.getApellido().equals(contcoriginal.getApellido())){
                            viewContactosCopia.getItems().add(contcoriginal);
                        }
                    }

                }

            }

        }
        System.out.println("lista copia despues de haber pasado por el while:");
        for (Contacto item : viewContactosCopia.getItems()) {
            System.out.println(item);
        }

        contenedorList.getChildren().add(viewContactosCopia);
        vblista.getChildren().add(contenedorList);
    }

    public void filtrarPorEtiquetas(String etiqueta) {
        contenedorList.getChildren().clear();
        vblista.getChildren().clear();
        viewContactosCopia.getItems().clear();

        if (viewContactosCopia.getItems().isEmpty()) {

            for (Map.Entry<String, String> entry : etiquetasPersonas.entrySet()) {
                int indexEspacio = entry.getKey().indexOf(" ");
                String nombre= entry.getKey().substring(0,indexEspacio);
                String apellido = entry.getKey().substring(indexEspacio+1);
                if (etiqueta.equalsIgnoreCase(entry.getValue())) {
                    for (Contacto contc : listaContactos) {
                        if (contc.getNombre().equalsIgnoreCase(nombre) && contc.getApellido().equalsIgnoreCase(apellido)) {
                            viewContactosCopia.getItems().add(contc);
                        }

                    }
                }
            }

        }
        System.out.println("lista copia despues de haber pasado por el while:");
        for (Contacto item : viewContactosCopia.getItems()) {
            System.out.println(item);
        }

        contenedorList.getChildren().add(viewContactosCopia);
        vblista.getChildren().add(contenedorList);
    }

    



}


