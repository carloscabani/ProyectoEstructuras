/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Clases.Contacto;
import Clases.Foto;
//import static Controller.CreacionContactosController.lstfotoPerfiles;
import static Controller.ListaContactosController.*;
import ListTDA.ArrayG9;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
public class PerfilContactoController implements Initializable {
    
    public int indiceActual = 0;
    public static ArrayG9 <Foto> lstfotoPerfiles = new ArrayG9<>();



    @FXML
    private VBox vbportada;
    @FXML
    private ImageView imgFotoPerfil;
    @FXML
    private Label lbname;
    @FXML
    private Pane paneDatos;
    @FXML
    private Label lbcell;
    @FXML
    private Label lbcont;
    @FXML
    private Label lbpagina;
    @FXML
    private Label lbempresa;
    @FXML
    private Label lbdireccion;
    @FXML
    private Label lbfecha;
    @FXML
    private Label lbcorreo;
    @FXML
    private Pane paneTrabajoFoto;
    @FXML
    private HBox hbicons;
    @FXML
    private Pane paneBotones;
    @FXML
    private Button btanterior;
    @FXML
    private Button btnext;
    @FXML
    private ImageView imgllamada;
    @FXML
    private ImageView imgSMS;
    @FXML
    private ImageView imgvideo;
    @FXML
    private Button bthome;
    @FXML
    private ImageView imgGPS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        RegresarHome();
        cargarListaPerfiles();
        if(!listaContactos.isEmpty()){
            mostrarPersonaActual();
        }
    }    

    @FXML
    private void mostrarAnterior(ActionEvent event) {
        if (!listaContactos.isEmpty()) {
            if (indiceActual > 0) {
                indiceActual--;
            } else {
                indiceActual = listaContactos.size() - 1;
            }
            mostrarPersonaActual();
        }
    }

    @FXML
    private void VentanaInicio(ActionEvent event) throws IOException {
//        App.setRoot("ListaContactos");
    }
    
    public void RegresarHome(){
        bthome.setOnAction((ActionEvent e) -> {
            try {
                App.setRoot("ListaContactos");
            } catch (IOException ex) {
                Logger.getLogger(PerfilContactoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    private void mostrarSiguiente(ActionEvent event) {

        if (!listaContactos.isEmpty()) {
            if (indiceActual < listaContactos.size() - 1) {
                indiceActual++;
            } else {
                indiceActual = 0; 
            }
            mostrarPersonaActual();
        }

    }

    private void mostrarPersonaActual() {
        // Generar un número aleatorio entre 1 y 20
        Random random = new Random();
        int nAleatorio = random.nextInt(20) + 1;
        Contacto persona = listaContactos.get(indiceActual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaComoString = persona.getFecha().getFecha().format(formatter);
        lbname.setText(persona.getNombre() + " " + persona.getApellido());
        lbcell.setText(persona.getTlf().getTlf() + " - " + persona.getTlf().getTipoTlf());
        if(persona.getPer().getTipoPersona().equals("")){
           lbcont.setText("ninguno");
        }else{
            lbcont.setText(persona.getPer().getPersona() + " - " + persona.getPer().getTipoPersona());
        }
        
        if(persona.getRedSocial().getUsername().equals("")){
            lbpagina.setText("ninguno");
        }else{
            lbpagina.setText(persona.getRedSocial().getTipoRedSocial()+" - "+persona.getRedSocial().getUsername());
        }
        
        if (persona.getEmpresa().equals("")){
            lbempresa.setText("ninguno");
        } else {
            lbempresa.setText(persona.getEmpresa());
        }


        lbdireccion.setText(persona.getDir().getUbicacion() + " - " + persona.getDir().getTipoDireccion());
        lbfecha.setText(fechaComoString + " - " + persona.getFecha().getTipoFecha());
        lbcorreo.setText(persona.getEmail().getCorreo() + " - " + persona.getEmail().getTipo());
        seleccionImagen(nAleatorio);
        for (Foto ft : lstfotoPerfiles) {
            if ((ft.getNombre().equals(persona.getNombre())) && (ft.getApellido().equals(persona.getApellido()))) {
                try (FileInputStream input = new FileInputStream("src/main/resources/pics/" + cargarListaPerfiles(persona.getNombre(), persona.getApellido()))) {

                    Image image = new Image(input, 90, 100, true, false);
                    imgFotoPerfil.setImage(image);

                } catch (IOException e) {
                    System.out.println("error");
                }
            }
        }
        
        
        
        



    }
    
    
    // este es un metodo de prueba no esta en uso por el momento
    public String cargarListaPerfiles(String n, String ap) {
        String imagenAsociada = null;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/archivos/FotosPerfil.txt", StandardCharsets.UTF_8))) {
            String linea = br.readLine();
            while (linea != null) {
                String p[] = linea.split(",");
                if(n.equals(p[0]) && ap.equals(p[1])){
                    imagenAsociada = p[2];
                    System.out.println(imagenAsociada);
                }
                linea= br.readLine();

            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se pudo encontrar el archivo");
        } catch (IOException e) {
            System.out.println("ERROOOORRR.......");
        }
        
      
        
        return imagenAsociada;

    }
    
    public void cargarListaPerfiles() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/archivos/FotosPerfil.txt", StandardCharsets.UTF_8))) {
            String linea = br.readLine();
            while (linea != null) {
                String p[] = linea.split(",");
                String s1 = p[0];
                String s2 = p[1];
                String s3 = p[2];
                lstfotoPerfiles.add(new Foto(s1, s2, s3));

                linea = br.readLine();

            }
        } catch (FileNotFoundException ex) {
            System.out.println("No se pudo encontrar el archivo");
        } catch (IOException e) {
            System.out.println("ERROOOORRR.......");
        }

    }

    public void seleccionImagen(int index) {
        String num= String.valueOf(index);
        try (FileInputStream input = new FileInputStream("src/main/resources/ubicaciones/" + "ubicacion"+num+".png")) {
            //System.out.println("src/main/resources/ubicaciones/" + "ubicacion"+num);
            Image image = new Image(input, 308, 137, true, false); 
            imgGPS.setImage(image);

        } catch (IOException e) {
            System.out.println("error");
        }

    }
}
