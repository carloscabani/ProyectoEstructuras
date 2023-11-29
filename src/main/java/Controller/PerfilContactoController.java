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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private ImageView imgGPS;
    
    private String imagenPersonaActual;
    
    public static Contacto contactoActual;
    
    @FXML
    private Button deleteButton;
    @FXML
    private Label labelEmpresa;
    @FXML
    private ImageView imgImportar;
    @FXML
    private AnchorPane paneContactosVisualizacion;
    @FXML
    private AnchorPane panegeneral;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        RegresarHome();
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
        
        System.out.println("Datos actuales: "+ contactoActual +", "+ imagenPersonaActual);
    }

    @FXML
    private void VentanaInicio(ActionEvent event) throws IOException {
        App.setRoot("ListaContactos");
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
        
        System.out.println("Datos actuales: "+ contactoActual +", "+ imagenPersonaActual);

    }

    private void mostrarPersonaActual() {
        // Generar un número aleatorio entre 1 y 20
        Random random = new Random();
        int nAleatorio = random.nextInt(20) + 1;

        Contacto persona = listaContactos.get(indiceActual);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String fechaComoString = persona.getFecha().getFecha().format(formatter);

        lbname.setText(persona.getNombre() + " " + (persona.getApellido() != null ? persona.getApellido() : ""));
        lbcell.setText(persona.getTlf().getTlf() + " - " + persona.getTlf().getTipoTlf());
        lbdireccion.setText(persona.getDir().getUbicacion() + " - " + persona.getDir().getTipoDireccion());
        lbfecha.setText(fechaComoString + " - " + persona.getFecha().getTipoFecha());
        lbcorreo.setText(persona.getEmail().getCorreo() + " - " + persona.getEmail().getTipo());
        seleccionImagen(nAleatorio);

        // Restablecer los valores predeterminados
        lbcont.setText("");
        lbpagina.setText("");
        lbempresa.setText("");
        lbempresa.setVisible(false);
        labelEmpresa.setVisible(false);

        for (Contacto camp : lstCamposAdicionales) {
            if (camp.equals(persona)) {
                System.out.println("-------------------------");
                System.out.println("Dentro del for esta: "+ camp);
                
                System.out.println(camp.getNombre());

                String personaAdicional = camp.getPer().getPersona();
                String tipoPersona = camp.getPer().getTipoPersona();
                String tipoRedSocial = camp.getRedSocial().getTipoRedSocial();
                String username = camp.getRedSocial().getUsername();
                String empresa = camp.getEmpresa();

                lbcont.setText(personaAdicional + " - " + tipoPersona);
                lbpagina.setText(tipoRedSocial + " - " + username);
                System.out.println("Acaba de enviar el contenido a los labels");

                if (!"".equals(persona.getApellido()) && !"".equals(empresa)) {
                    lbempresa.setText(empresa);
                    lbempresa.setVisible(true);
                    labelEmpresa.setVisible(true);
                    System.out.println("Entro aqui 2");

                }

                System.out.println("Elementos adicionales: "+ personaAdicional+", "+ username+ ", "+ empresa );
                break; 
            }
        
        
        
    }
    
        
        contactoActual = persona;
        imagenPersonaActual = cargarListaPerfiles(persona.getNombre(), persona.getApellido());
        
        
  
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
                    //System.out.println(imagenAsociada);
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
    
    public static void deleteFromFile (String name, String apellido, String archivoTxt){
        File archivo = new File(archivoTxt);
        File temporal = new File(archivo.getParent(),"temporal_"+archivo.getName());
        
        try(BufferedReader lectura = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8));
            BufferedWriter escritura = new BufferedWriter(new FileWriter(temporal,false ))){
                
                String lineaDelete = name+","+apellido;
                String lineaActual;
                
                
                while((lineaActual=lectura.readLine()) != null){
                    if( !lineaActual.contains(lineaDelete)){
                        
                        escritura.write(lineaActual+ System.getProperty("line.separator"));
                        
                        }
                
                }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        try{
            Files.move(temporal.toPath(), archivo.toPath(), StandardCopyOption.REPLACE_EXISTING );
        
        }catch(IOException e){e.printStackTrace();}
        
    }
    
    

    @FXML
    private void delete(ActionEvent event) throws IOException {
        
        System.out.println(listaContactos);
        String nombre= contactoActual.getNombre();
        String apellido = contactoActual.getApellido();
        //eliminar desde listaContactos
        
        System.out.println("Lista actualizada: "+ listaContactos);
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmacion de eliminar contacto");
        alert.setContentText("¿Estas seguro de eliminar a ese contacto?");
        alert.showAndWait();
            if(alert.getResult().equals(ButtonType.OK)){
      
            listaContactos.remove(contactoActual);
            //eliminar contacto en Contactos.txt
            deleteFromFile(nombre, apellido,"src/main/resources/archivos/Contactos.txt");
            //eliminar contacto en FotosPerfil.txt
            deleteFromFile(nombre, apellido, "src/main/resources/archivos/FotosPerfil.txt");
            //eliminar contacto en CamposAdicionales.txt
            deleteFromFile(nombre, apellido, "src/main/resources/archivos/CamposAdicionales.txt");
            //eliminar contacto de ImagenesAsociadas.txt
            deleteFromFile(nombre, apellido,"src/main/resources/archivos/ImagenesAsociadas.txt");
            App.setRoot("ListaContactos");
            System.out.println("Lista actualizada: "+ listaContactos);
            
            
            }
            
    }

    @FXML
    private void importarContacto(MouseEvent event) {
        VentanaOpcionesExportado();
    }

    public void VentanaOpcionesExportado() {
        Stage g = new Stage();
        //g.initModality(Modality.APPLICATION_MODAL);
        //g.setTitle("Etiquetas");

        Label label = new Label("¿Como desea exportar la informacion?");
        Button btguardar = new Button("Exportar");
        RadioButton rdImagen = new RadioButton ("Imagen");
        RadioButton rdArchivo = new RadioButton ("Archivo");
        ComboBox <String>cbImagen = new ComboBox ();
        ComboBox <String>cbArchivo = new ComboBox ();
        ImageView imgEnviando = new ImageView();
        ToggleGroup EtiquetasContactos = new ToggleGroup();
        rdImagen.setToggleGroup(EtiquetasContactos);
        rdArchivo.setToggleGroup(EtiquetasContactos);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        rdImagen.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        rdArchivo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        HBox hcontenedor = new HBox();
        hcontenedor.getChildren().add(label);
        hcontenedor.setPrefWidth(500);
        hcontenedor.setPrefHeight(58);
        hcontenedor.setAlignment(Pos.CENTER);
        HBox hradioButtons = new HBox();
        hradioButtons.getChildren().addAll(rdImagen,rdArchivo);
        hradioButtons.setPrefWidth(500);
        hradioButtons.setPrefHeight(65);
        hradioButtons.setAlignment(Pos.CENTER);
        hradioButtons.setSpacing(50);
        VBox h1 = new VBox();

        VBox rootNuevo = new VBox();
        rootNuevo.getChildren().addAll(hcontenedor, hradioButtons, h1);

        rdImagen.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                //cbImagen.setValue("");
                if (h1.getChildren().isEmpty()) {
                    try (FileInputStream input = new FileInputStream("src/main/resources/icons/enviando.gif")) {
                        Image image = new Image(input, 250, 150, true, false);
                        imgEnviando.setImage(image);
                    } catch (IOException exep) {
                        System.out.println("error");
                    }
                    h1.getChildren().addAll(imgEnviando, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(20);
                } else {
                    h1.getChildren().clear();
                    try (FileInputStream input = new FileInputStream("src/main/resources/icons/enviando.gif")) {
                        Image image = new Image(input, 250, 150, true, false);
                        imgEnviando.setImage(image);
                    } catch (IOException exep) {
                        System.out.println("error");
                    }
                    h1.getChildren().addAll(imgEnviando, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(20);
                }
            }

        });
        rdArchivo.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                cbArchivo.setValue("");
                if (h1.getChildren().isEmpty()) {
                    if(cbArchivo.getItems().isEmpty()){
                        cbArchivo.getItems().addAll(".txt",".csv");
                    }
                    h1.getChildren().addAll(cbArchivo, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(50);
                } else {
                    h1.getChildren().clear();
                    if(cbArchivo.getItems().isEmpty()){
                        cbArchivo.getItems().addAll(".txt",".csv");
                    }
                    h1.getChildren().addAll(cbArchivo, btguardar);
                    h1.setPrefWidth(500);
                    h1.setPrefHeight(216);
                    h1.setAlignment(Pos.CENTER);
                    h1.setSpacing(50);
                }
            }

        });       
        
        Scene s = new Scene(rootNuevo, 500, 350);

        g.setScene(s);
        g.setTitle("Exportar Contacto");
        g.show();
  
        btguardar.setOnAction(event -> {
            if (rdImagen.isSelected()) {
                // Capturar la ventana como una imagen
                WritableImage image = paneContactosVisualizacion.snapshot(new SnapshotParameters(), null);

                // Mostrar un cuadro de diálogo para seleccionar la ubicación de guardado
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG files (*.jpg)", "*.jpg"));

                File file = fileChooser.showSaveDialog(g);

                if (file != null) {
                    try {
                        // Convertir la imagen a un formato compatible con ImageIO
                        javax.imageio.ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                        mostrarAlerta("Imagen guardada exitosamente", "La imagen se ha guardado en: " + file.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                        mostrarAlerta("Error al guardar la imagen", "Ocurrió un error al intentar guardar la imagen.");
                    }
                }
            } else if (rdArchivo.isSelected()) {
                String tipoFile = cbArchivo.getValue();
                ArchivoContactoExportado(tipoFile);
                
            }

            g.close();
        });

    }

    public void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    
    public void ArchivoContactoExportado(String c){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/ContactosExportados/InfoContacto"+lbname.getText()+c))) {
            writer.write("Nombre: "+lbname.getText()+"\n");
            writer.write("Telefono: "+lbcell.getText()+"\n");
            writer.write("Correo: "+lbcorreo.getText()+"\n");
            writer.write("Fecha especial: "+lbfecha.getText()+"\n");
            writer.write("Contacto adicional: "+lbcont.getText()+"\n");
            writer.write("Red Social: "+lbpagina.getText()+"\n");
            writer.write("Direccion: "+lbdireccion.getText()+"\n");
            System.out.println("Se escribio el usuario ha exportar an el achivo exitosamenteeeeee.......");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    @FXML
    private void ventanaVisualizarFotos(MouseEvent event) throws IOException {
        App.setRoot("ImagenesAsociadas");
    }
    
    
}