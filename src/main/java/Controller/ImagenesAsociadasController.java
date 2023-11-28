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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ImagenesAsociadasController implements Initializable {

    public static LLDouble<Foto> listaFotosAsociadas = new LLDouble<>();
    private int indiceImagenActual = 0;
    private Foto elementoFotoActual;
    
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
    
//    private void mostrarPictureActual(){
//        if(!listaFotosAsociadas.isEmpty()){
//            Foto pictureActual = listaFotosAsociadas.get(indiceImagenActual);
//            String firstImage= pictureActual.getListaImagenesAsociadas().get(0);
//            mostrarImagen(firstImage);
//        }
//    
//    }
    
    
}
