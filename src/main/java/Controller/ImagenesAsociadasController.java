/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Clases.Contacto;
import Clases.Foto;
import static Controller.PerfilContactoController.contactoActual;
import ListTDA.LLDouble;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ImagenesAsociadasController implements Initializable {

    public static LLDouble<Foto> listaFotosAsociadas = new LLDouble<>();
    private int indiceImagenActual = 0;
    private Foto elementoFotoActual;
    public static String nombreImagen;
    public static String imagenActual;
    
    
    //public Contacto contacto = contactoActual;
    
    
    @FXML
    private ImageView imagenActualAsociada;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarListaFotosAsociadas();
        presentarImagen(contactoActual);
    }    

    @FXML
    private void volverPerfilContacto(MouseEvent event) throws IOException {
        App.setRoot("PerfilContacto");
    }

    @FXML
    private void eliminarFoto(MouseEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmacion de eliminar foto");
        alert.setContentText("¿Estas seguro de eliminar la foto actual?");
        alert.showAndWait();
            if(alert.getResult().equals(ButtonType.OK)){

                //caso base
                if(elementoFotoActual!= null && !elementoFotoActual.getListaImagenesAsociadas().isEmpty()){
                    
                    elementoFotoActual.getListaImagenesAsociadas().remove(imagenActual);
                    updateArchive(elementoFotoActual.getNombre(), elementoFotoActual.getApellido(), 
                            elementoFotoActual.getListaImagenesAsociadas());
                    
                    if(indiceImagenActual>0){
                        indiceImagenActual--;
                        
                    }else if(elementoFotoActual.getListaImagenesAsociadas().size()>0){
                        indiceImagenActual = elementoFotoActual.getListaImagenesAsociadas().size() -1;
                    }
                    
                    presentarImagen(contactoActual);
        }
            }
    }

    @FXML
    private void mostrarAnterior(MouseEvent event) {
        if (!elementoFotoActual.getListaImagenesAsociadas().isEmpty()) {
            if (indiceImagenActual > 0) {
                indiceImagenActual--;
            } else {
                indiceImagenActual = elementoFotoActual.getListaImagenesAsociadas().size() - 1;
            }
        
        
        presentarImagen(contactoActual);
        
    }
        System.out.println("Imagen actual: "+ imagenActual);
    }

    @FXML
    private void mostrarSiguiente(MouseEvent event) {
        
        if (!elementoFotoActual.getListaImagenesAsociadas().isEmpty()) {
            if (indiceImagenActual < elementoFotoActual.getListaImagenesAsociadas().size() - 1) {
                indiceImagenActual++;
            } else {
                indiceImagenActual = 0; 
            }
            presentarImagen(contactoActual);
        }
        System.out.println("Imagen actual: "+ imagenActual);
        
    }
    
    public static void llenarListaFotosAsociadas(){
    
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/archivos/ImagenesAsociadas.txt", StandardCharsets.UTF_8))) {
            String linea;
            
            while((linea = br.readLine())!= null){
                String[] contenido = linea.split(",");
                String nombre = contenido[0];
                String apellido = contenido[1];
                System.out.println("En el metodo llenarListaFA");
                System.out.println(nombre+apellido);
                LLDouble<String> listImagenes = new LLDouble<>();
                
                for(int x = 2; x < contenido.length; x++){
                    listImagenes.add(contenido[x]);
                }
                
                Foto fotosAsociadas = new Foto(nombre, apellido,listImagenes );
                listaFotosAsociadas.add(fotosAsociadas);
                
            }
        }catch(IOException e){e.printStackTrace();}
        
    }
    
    
    public void presentarImagen(Contacto contact){
        String nombre = contact.getNombre();
        String apellido = contact.getApellido();
        
        //recorremos la lista
        for(Foto foto : listaFotosAsociadas){
            if(nombre.equals(foto.getNombre()) && 
                    apellido.equals(foto.getApellido())){
            
                elementoFotoActual = foto;
                String firstImage = foto.getListaImagenesAsociadas().get(indiceImagenActual);
                mostrarImagen(firstImage);
                imagenActual = firstImage;
                break;
            }
        
        }
    
    }
    
    private void mostrarImagen(String nombreImagen) {
        try (FileInputStream input = new FileInputStream("src/main/resources/pics/" + nombreImagen)) {
            Image image = new Image(input);
            imagenActualAsociada.setImage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    


    @FXML
    private void agregarFotoAsociada(MouseEvent event) {
        
        agregarFoto();
        
        
    }
    
    private void agregarFoto(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar nueva imagen");
        
        File initialDirectory = new File("src/main/resources/pics");
        fileChooser.setInitialDirectory(initialDirectory);

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        Window stage = null;

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            
            nombreImagen = selectedFile.getName();
            elementoFotoActual.getListaImagenesAsociadas().add(nombreImagen);
            String name = elementoFotoActual.getNombre();
            String lastName = elementoFotoActual.getApellido();
            agregarAlArchivo(name, lastName, nombreImagen);
        }
    }
    
    
    public static void agregarAlArchivo(String nombreContacto, String apellidoContacto, String newImage){
        File archivo = new File("src/main/resources/archivos/ImagenesAsociadas.txt");
        File temp = new File (archivo.getParent(), "temp_"+archivo.getName());
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(temp, false))) {

            String lineaActual;

            while ((lineaActual = reader.readLine()) != null) {
                String[] contenido = lineaActual.split(",");
                String nombre = contenido[0];
                String apellido = contenido[1];

                // Verificacion de linea actual
                if (nombre.equals(nombreContacto)  && 
                        apellido.equals(apellidoContacto)) {
                    
                    writer.write(lineaActual + "," + newImage + System.getProperty("line.separator"));
                } else {
         
                    writer.write(lineaActual + System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
          
            Files.move(temp.toPath(), archivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();}
        
        
    }
    
    
    public void updateArchive(String nameContacto, String lastNameContacto, LLDouble<String> listaActualizada){
        File archivo = new File("src/main/resources/archivos/ImagenesAsociadas.txt");
        File temp = new File (archivo.getParent(), "temp_"+archivo.getName());
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new FileWriter(temp, false))) {

            String lineaActual;

            while ((lineaActual = reader.readLine()) != null) {
                String[] contenido = lineaActual.split(",");
                String nombre = contenido[0];
                String apellido = contenido[1];

                // Verificacion de linea actual
                if (nombre.equals(nameContacto) && 
                        apellido.equals(lastNameContacto) ) {
                    
                    writer.write(nameContacto+","+lastNameContacto);
                    for(String image : listaActualizada){
                        writer.write(","+image);
                    }
                    writer.write(System.getProperty("line.separator"));
                            
                } else {
         
                    writer.write(lineaActual + System.getProperty("line.separator"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
          
            Files.move(temp.toPath(), archivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();}
        
    
    }
    
    public static LLDouble<String> conseguirFotosAsociadas(String nombre, String apellido) {
        LLDouble<String> resultado = new LLDouble<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/archivos/ImagenesAsociadas.txt", StandardCharsets.UTF_8))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] contenido = linea.split(",");
                String nombreActual = contenido[0].trim();
                String apellidoActual = contenido[1].trim();

                if (nombre.equals(nombreActual) && apellido.equals(apellidoActual)) {
                    for (int i = 3; i < contenido.length; i++) {
                        resultado.add(contenido[i].trim());
                    }
                    break; // No necesitamos seguir buscando después de encontrar la línea correcta
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    
    
}
