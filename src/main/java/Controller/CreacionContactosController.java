/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Clases.*;
import static Controller.ListaContactosController.*;
import ListTDA.ArrayG9;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class CreacionContactosController implements Initializable {
    
    public static ArrayList<String> etiqueta1 = new ArrayList<>();
    public static ArrayList<String> etiqueta2 = new ArrayList<>();
    public static ArrayList<String> etiqueta3 = new ArrayList<>();
    public static ArrayList<String> etiqueta4 = new ArrayList<>();
    public static ArrayG9<String> etiqueta5 = new ArrayG9<>();
    public static ArrayG9 <Foto> lstfotoPerfiles = new ArrayG9<>();

    String ni = null;


    ComboBox c1 = new ComboBox();
    ComboBox c2 = new ComboBox();
    Label l1 = new Label("Persona Contacto:");
    Label l2 = new Label("Red Social:      ");
    Label l3 = new Label("Empresa:");
    TextField tperContacto = new TextField();
    TextField tempresa = new TextField();
    TextField tsitioweb = new TextField();
    Image img ;
    
    @FXML
    private TextField txnombre;
    @FXML
    private TextField txtelefono;
    @FXML
    private TextField txdireccion;
    @FXML
    private TextField txapellido;
    
    private TextField txtrabajo;
    @FXML
    private TextField txemail;
    @FXML
    private DatePicker txcalendario;
    @FXML
    private Button btguardar;
    @FXML
    private Button btback;
    @FXML
    private VBox vbcamposopcionales;
    @FXML
    private HBox hbpercontacto;
    @FXML
    private HBox hbsitioweb;
    @FXML
    private HBox hbempresa;
    @FXML
    private Button btcampos;
    @FXML
    private ComboBox<String> cbdireccion;
    @FXML
    private ComboBox<String> cbtelefono;
    @FXML
    private ComboBox<String> cbemail;
    @FXML
    private ComboBox<String> cbfecha;
    @FXML
    private Button btocultar;
    @FXML
    private ImageView fotoPerfil;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbtelefono.setValue("");
        
        cargarEtiquetas();

        camposAdicionales();
        
        cargarCombobox();
       
    }
    
    
    @FXML
    private void enviarListaContactos(ActionEvent event) {
        btguardar.setOnAction((ActionEvent e) -> {
            String tipoPersonaContacto = (String) c1.getValue();
            String Nombre = txnombre.getText();
            String Apellido = txapellido.getText();
            Telefono Tel = new Telefono (txtelefono.getText(),cbtelefono.getValue());
            Direccion di = new Direccion(txdireccion.getText(),cbdireccion.getValue());
            Email em= new Email(cbemail.getValue(),txemail.getText());
            PersonaAdiconal per=new PersonaAdiconal(tipoPersonaContacto,tperContacto.getText());
            
            LocalDate fecha = txcalendario.getValue();
            Fecha fech= new Fecha(cbfecha.getValue(),fecha);
            RedSocial web= new RedSocial((String) c2.getValue(), tsitioweb.getText());
            String Empresa= tempresa.getText();
            
            Contacto c= new Contacto(Nombre,Apellido,Tel,di,em,per,fech,web,Empresa);
            listaContactos.add(c);
            EscribirArchivoContactos();

            // esto es para obtener el nombre de la imagen que el usuario selecciono para asociarla 
            // con ese contacto
          
            escribirArchivosImagenes(ni);

            try (FileInputStream input = new FileInputStream("src/main/resources/icons/logoAgregarImagen.png" )) {

                Image image = new Image(input, 90, 100, true, false);
                fotoPerfil.setImage(image);

            } catch (IOException exep) {
                System.out.println("error");
            }

            txnombre.clear();
            txapellido.clear();
            txtelefono.clear();
            txdireccion.clear();
            txemail.clear();
            tperContacto.clear();
            tempresa.clear();
            tsitioweb.clear();
            c1.setValue("");
            c2.setValue("");
            cbdireccion.setValue("");
            cbtelefono.setValue("");
            cbemail.setValue("");
            cbfecha.setValue("");
            txcalendario.setValue(null);

        });

    }

    @FXML
    private void ventanaContactos(ActionEvent event) throws IOException {
        App.setRoot("ListaContactos");
    }
    
    public void camposAdicionales() {
        btcampos.setOnAction((ActionEvent e) -> {
           

            l1.setFont(new Font(15));
            l2.setFont(new Font(15));
            l3.setFont(new Font(15));
            hbpercontacto.getChildren().addAll(l1,tperContacto,c1);
            hbpercontacto.setSpacing(20);
            hbsitioweb.getChildren().addAll(l2,tsitioweb,c2);
            hbsitioweb.setSpacing(30);
            l2.setMaxHeight(27);
            l2.setMaxWidth(150);
            hbempresa.getChildren().addAll(l3,tempresa);
            hbempresa.setSpacing(75);
            hbempresa.setPadding(new Insets(10, 0, 0, 10));
          
            
        });
    }
    
    public void cargarEtiquetas() {
        //llenado de etiqueta1
        etiqueta1.add("Movil");
        etiqueta1.add("Trabajo");
        etiqueta1.add("Casa");
        etiqueta1.add("Principal");
        etiqueta1.add("Fax del Trabajo");
        //llenado de etiqueta2
        etiqueta2.add("Trabajo");
        etiqueta2.add("Casa");
        etiqueta2.add("Otro");
        //llenado de etiqueta3
        etiqueta3.add("Cumpleaños");
        etiqueta3.add("Aniversario");
        etiqueta3.add("Otro");
        //llenado de etiqueta4
        etiqueta4.add("Asistente");
        etiqueta4.add("Hermano");
        etiqueta4.add("Hijo");
        etiqueta4.add("Padre");
        etiqueta4.add("Amigo");
        etiqueta4.add("Jefe");
        etiqueta4.add("Esposa");
        //llenado de etiqueta5
        etiqueta5.add("Facebook");
        etiqueta5.add("Instagram");
        etiqueta5.add("Tik Tok");
        etiqueta5.add("Twuiter");

        
        
          
    }

    public void cargarCombobox() {

        if (cbdireccion.getItems().isEmpty() && cbemail.getItems().isEmpty()) {
            for (String s : etiqueta2) {
                cbdireccion.getItems().add(s);
                cbemail.getItems().add(s);
            }
        }
        if (cbtelefono.getItems().isEmpty()) {
            for (String p : etiqueta1) {
                cbtelefono.getItems().add(p);
            }
        }
        if (cbfecha.getItems().isEmpty()) {
            for (String a : etiqueta3) {
                cbfecha.getItems().add(a);
            }
        }
        if (c1.getItems().isEmpty()) {
            for (String b : etiqueta4) {
                c1.getItems().add(b);
            }
        }
        if (c2.getItems().isEmpty()) {
            for (String redes : etiqueta5) {
                c2.getItems().add(redes);
            }
        }

    }

    @FXML
    private void ocultarCampos(ActionEvent event) {
        hbpercontacto.getChildren().clear();
        hbsitioweb.getChildren().clear();
        hbempresa.getChildren().clear();
    }
    
    public void EscribirArchivoContactos(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/Contactos.txt"))) {
            //formato:
            //Nombre,Apellido,Telefono,Direccion,Email,Fecha pertinente,Persona Adiconal,Red social,Empresa
            

            ArrayList<Contacto> listaFor = new ArrayList<>();
            for(int x =0; x<listaContactos.size(); x++){
                listaFor.add(listaContactos.get(x));
            
            }
            
            
            // Escribir los datos de los estudiantes
            for (Contacto c : listaFor) {
                if (tperContacto.getText().equals("") && tsitioweb.getText().equals("") && tempresa.getText().equals("")) {
                    writer.write(c.getNombre() + "," + c.getApellido() + "," + c.getTlf().getTlf() + "-" + c.getTlf().getTipoTlf() + ","
                            + c.getDir().getUbicacion() + "-" + c.getDir().getTipoDireccion() + "," + c.getEmail().getCorreo() + "-"
                            + c.getEmail().getTipo() + "," + c.getFecha().getFecha() + "-" + c.getFecha().getTipoFecha() + "," + "ninguno-ninguno" + "," + "ninguno-ninguno" + ","
                            + "ninguno");
                } else {
                    writer.write(c.getNombre() + "," + c.getApellido() + "," + c.getTlf().getTlf() + "-" + c.getTlf().getTipoTlf() + ","
                            + c.getDir().getUbicacion() + "-" + c.getDir().getTipoDireccion() + "," + c.getEmail().getCorreo() + "-"
                            + c.getEmail().getTipo() + "," + c.getFecha().getFecha() + "-" + c.getFecha().getTipoFecha() + "," + c.getPer().getPersona() + "-" + c.getPer().getTipoPersona() + ","
                            + c.getRedSocial().getUsername() + "-" + c.getRedSocial().getTipoRedSocial() + "," + c.getEmpresa());
                }

                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirArchivosImagenes(String i) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.pathFiles + "FotosPerfil.txt",true))) {
            writer.write(txnombre.getText()+","+txapellido.getText()+","+i);
            writer.newLine();
            
            System.out.println("Registro EXITOsoooooooo.......");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    



    private void changeImage(File newImageFile) {
        
        ni = newImageFile.getName();
        try {
            // Cargar la nueva imagen usando ImageIO
            FileInputStream inputStream = new FileInputStream(newImageFile);
            Image newImage = new Image(inputStream);

            // Actualizar la ImageView con la nueva imagen
            fotoPerfil.setImage(newImage);

            // Puedes guardar la ruta de la nueva imagen en una variable si es necesario
            // String newPath = newImageFile.getAbsolutePath();
            // ...
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void elegirImagen(MouseEvent event) {
        String nameFoto = null;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar nueva imagen");
        
        File initialDirectory = new File("src/main/resources/pics");
        fileChooser.setInitialDirectory(initialDirectory);

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        Window stage = null;

        
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            
            changeImage(selectedFile);

        }
    }



}    
    
    

    

    
 
    

