/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Clases.Foto;
import static Controller.CreacionContactosController.lstfotoPerfiles;
import static Controller.ListaContactosController.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class VistaContactoIndividualController implements Initializable {

    @FXML
    private VBox vbportada;
    @FXML
    private ImageView photoIndividual;
    @FXML
    private Label lbna;
    @FXML
    private HBox hbicons1;
    @FXML
    private ImageView imgllamada1;
    @FXML
    private ImageView imgSMS1;
    @FXML
    private ImageView imgvideo1;
    @FXML
    private Pane paneDatos;
    @FXML
    private Label lce;
    @FXML
    private Label lper;
    @FXML
    private Label lred;
    @FXML
    private Label lbem;
    @FXML
    private Label lbdir;
    @FXML
    private Label lbfe;
    @FXML
    private Label lcorre;
    @FXML
    private Button btinicio;
    @FXML
    private AnchorPane paneVistaContactoIndividual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mostrarInfo();
    }    

    @FXML
    private void volverListaContactos(ActionEvent event) {
        try {
            App.setRoot("ListaContactos");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Excepción no manejada: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void mostrarInfo(){
        lbna.setText(contactoSelected.getNombre()+" "+contactoSelected.getApellido());
        lce.setText(contactoSelected.getTlf().getTlf() + " - " + contactoSelected);
        lcorre.setText(contactoSelected.getEmail().getCorreo() + " - " + contactoSelected.getEmail().getTipo());
        lbfe.setText(contactoSelected.getFecha().getTipoFecha() + " - " + contactoSelected.getFecha().getFecha());
        System.out.println(contAdicional);
        lper.setText(contAdicional+" - "+ typeContactAdicional);
        lred.setText(redSoc+" - "+typeRedSocial);
        lbem.setText(empresa);

        lbdir.setText(contactoSelected.getDir().getUbicacion() + " - " + contactoSelected.getDir().getTipoDireccion());

        for (Foto f : lstfotoPerfiles) {
            if (f.getNombre().equals(contactoSelected.getNombre()) && f.getApellido().equals(contactoSelected.getApellido())) {
                try (FileInputStream input = new FileInputStream("src/main/resources/pics/"+f.getImagen())) {

                    Image image = new Image(input, 90, 100, true, false);
                    photoIndividual.setImage(image);

                } catch (IOException exep) {
                    System.out.println("error");
                }
            }
        }
    }

    @FXML
    private void MostrarOpcionesExportado(MouseEvent event) {
        exportadoContactoIndividual();
    }
    public void exportadoContactoIndividual() {
        Stage g = new Stage();
        Label label = new Label("¿Como desea exportar la informacion?");
        Button btguardar = new Button("Exportar");
        RadioButton rdImagen = new RadioButton ("Imagen");
        RadioButton rdArchivo = new RadioButton ("Archivo");
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
                WritableImage image = paneVistaContactoIndividual.snapshot(new SnapshotParameters(), null);

                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG files (*.jpg)", "*.jpg"));

                File file = fileChooser.showSaveDialog(g);

                if (file != null) {
                    try {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/ContactosExportados/InfoContacto"+lbna.getText()+c))) {
            writer.write("Nombre: "+lbna.getText()+"\n");
            writer.write("Telefono: "+lce.getText()+"\n");
            writer.write("Correo: "+lcorre.getText()+"\n");
            writer.write("Fecha especial: "+lbfe.getText()+"\n");
            writer.write("Contacto adicional: "+lper.getText()+"\n");
            writer.write("Red Social: "+lred.getText()+"\n");
            writer.write("Direccion: "+lbdir.getText()+"\n");
            System.out.println("Se escribio el usuario ha exportar an el achivo exitosamenteeeeee.......");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }        
    }    
}
