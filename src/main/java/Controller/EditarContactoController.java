/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controller;

import Clases.Contacto;
import Clases.Direccion;
import Clases.Email;
import Clases.Fecha;
import Clases.Foto;
import Clases.PersonaAdiconal;
import Clases.RedSocial;
import Clases.Telefono;
import static Controller.CreacionContactosController.lstfotoPerfiles;
import static Controller.ImagenesAsociadasController.conseguirFotosAsociadas;
import static Controller.ListaContactosController.*;
import static Controller.ListaContactosController.contactoSelected;
import static Controller.ListaContactosController.empre;
import static Controller.ListaContactosController.redsoc;
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
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class EditarContactoController implements Initializable {
    
    public static Contacto contactoEditado;
    public static String imagenContactoEditado;
    public static Contacto DatosAdicionales;
    public static String newfoto;

    @FXML
    private Label lbtitulo;
    @FXML
    private TextField newnombre;
    @FXML
    private TextField newapellido;
    @FXML
    private ImageView imgfActual;
    @FXML
    private TextField newtelefono;
    @FXML
    private ComboBox<String> bcell;
    @FXML
    private DatePicker newfecha;
    @FXML
    private ComboBox<String> bfecha;
    @FXML
    private TextField newcontacto;
    @FXML
    private ComboBox<String> badicional;
    @FXML
    private TextField newdireccion;
    @FXML
    private ComboBox<String> bdireccion;
    @FXML
    private TextField newred;
    @FXML
    private TextField newempresa;
    @FXML
    private TextField newemail;
    @FXML
    private ComboBox<String> bred;
    @FXML
    private ComboBox<String> bemail;
    @FXML
    private Button bteditarCon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ponerimagenAsociada();
        
        if(bdireccion.getItems().isEmpty() && bfecha.getItems().isEmpty() && bcell.getItems().isEmpty() && badicional.getItems().isEmpty() && bred.getItems().isEmpty()){
            anadirComboBox();
        }
        
        mostrarInfor();
        System.out.println("Datos actuales: "+ contactoEditado +", "+ imagenContactoEditado);
        System.out.println(newfoto);
    }   
    
    public void mostrarInfor() {
        
        newnombre.setText(contactoSelected.getNombre());
        newapellido.setText(contactoSelected.getApellido());
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //String fechaComoString = contactoSelected.getFecha().getFecha().format(formatter);
        newfecha.setValue(contactoSelected.getFecha().getFecha());
        newdireccion.setText(contactoSelected.getDir().getUbicacion());
        newtelefono.setText(contactoSelected.getTlf().getTlf());
        newcontacto.setText(cadicional);
        newred.setText(redsoc);
        newempresa.setText(empre);
        newemail.setText(contactoSelected.getEmail().getTipo());
        bcell.setValue(contactoSelected.getTlf().getTipoTlf());
        bdireccion.setValue(contactoSelected.getDir().getTipoDireccion());
        bfecha.setValue(contactoSelected.getFecha().getTipoFecha());
        badicional.setValue(typec);
        bred.setValue(typered);
        bemail.setValue(contactoSelected.getEmail().getCorreo());
        ponerimagenAsociada();
    }
    
    public void anadirComboBox(){
        bcell.getItems().addAll("Movil","Trabajo","Casa","Principal","Fax del Trabajo");
        bdireccion.getItems().addAll("Trabajo","Casa","Otro");
        bemail.getItems().addAll("Trabajo","Casa","Otro");
        bfecha.getItems().addAll("Cumpleaños","Aniversario","Otro");
        badicional.getItems().addAll("Asistente","Madre","Hermano","Hijo","Padre","Amigo","Jefe","Esposa");
        bred.getItems().addAll("Facebook","Instagram","Tik Tok","Twitter");
    }
    
    public void ponerimagenAsociada(){
        for(Foto f : lstfotoPerfiles){
            if(contactoSelected.getNombre().equals(f.getNombre())&& contactoSelected.getApellido().equals(f.getApellido())){
                try (FileInputStream input = new FileInputStream("src/main/resources/pics/"+f.getImagen() )) {

                    Image image = new Image(input, 90, 100, true, false);
                    imgfActual.setImage(image);

                } catch (IOException e) {
                    System.out.println("error al conseguir la imagen asociada");
                }
            }
        }
    }

    public void changeImage(File newImageFile) {

        newfoto = newImageFile.getName();
        try {
            // Cargar la nueva imagen usando ImageIO
            FileInputStream inputStream = new FileInputStream(newImageFile);
            Image newImage = new Image(inputStream);

            // Actualizar la ImageView con la nueva imagen
            imgfActual.setImage(newImage);

            // Puedes guardar la ruta de la nueva imagen en una variable si es necesario
            // String newPath = newImageFile.getAbsolutePath();
            // ...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void CambiarImagen(MouseEvent event) {
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


    public static void deleteFromFileEditContact (String name, String apellido, String archivoTxt){
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
    private void guardarNewCambios(ActionEvent event) throws IOException {
        for (Contacto cte: listaContactos){
            if(cte.getNombre().equals(nombreSelected) && cte.getApellido().equals(apellidoSelected)){
                listaContactos.remove(cte);
            }
        }
        
        for(Contacto ct2: lstCamposAdicionales){
            if (ct2.getNombre().equals(nombreSelected) && ct2.getApellido().equals(apellidoSelected)) {
                lstCamposAdicionales.remove(ct2);
            }
        }

        for (Foto ct3 : lstfotoPerfiles) {
            if (ct3.getNombre().equals(nombreSelected) && ct3.getApellido().equals(apellidoSelected)) {
                lstfotoPerfiles.remove(ct3);
            }
        }
        
        String nombreSeleccionado = contactoSelected.getNombre();
        String apellidoSeleccionado = contactoSelected.getApellido();
        

//        listViewContactos.getItems().remove(4);
        
        Contacto people = new Contacto(newnombre.getText(),newapellido.getText(),new Telefono(bcell.getValue(), newtelefono.getText()),new Direccion(bdireccion.getValue(),newdireccion.getText()),
                          new Email(bemail.getValue(),newemail.getText()), new Fecha(bfecha.getValue(), newfecha.getValue()));
        Contacto dateperson= new Contacto(newnombre.getText(),newapellido.getText(),new PersonaAdiconal(newcontacto.getText(), badicional.getValue()), new RedSocial(bred.getValue(), newred.getText()), newempresa.getText());
        
        
        contactoEditado = people;
        DatosAdicionales = dateperson;
        
        System.out.println("Esta es la lista contactos:");
        System.out.println(listaContactos);
        String nombre= contactoEditado.getNombre();
        String apellido = contactoEditado.getApellido();
        //String fotoEdit= newfoto;
        //Foto im= new Foto(nombre,apellido,fotoEdit);
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmacion de Editar contacto");
        alert.setContentText("¿Estas seguro de Guardar los cambios realizados?");
        alert.showAndWait();
            if(alert.getResult().equals(ButtonType.OK)){
      
            //listaContactos.remove(contactoEditado);
            System.out.println(listaContactos);
            //lstCamposAdicionales.remove(DatosAdicionales);
            //lstfotoPerfiles.remove(im);
         
            System.out.println("Entra para eliminar en CONTACTOS.TXT");
            //eliminar contacto en Contactos.txt
            deleteFromFileEditContact(nombreSeleccionado, apellidoSeleccionado,"src/main/resources/archivos/Contactos.txt");
            listaContactos.add(contactoEditado);
            EscribirArchivoContactosEditados();
            
            System.out.println("Entra para eliminar en CamposAdicionales.TXT");
            //eliminar contacto en CamposAdicionales.txt
            deleteFromFileEditContact(nombreSeleccionado, apellidoSeleccionado, "src/main/resources/archivos/CamposAdicionales.txt");
            lstCamposAdicionales.add(DatosAdicionales);
            EscribirArchivoCamposAdicionalesEditados();
            
            String nombreSelected = contactoSelected.getNombre();
            String apellidoSelected = contactoSelected.getApellido();
            LLDouble resultado = conseguirFotosAsociadas(nombreSelected, apellidoSelected);
            
            deleteFromFileEditContact(nombreSelected, apellidoSelected, "src/main/resources/archivos/ImagenesAsociadas.txt");
            
            
            
            deleteFromFileEditContact(nombreSeleccionado, apellidoSeleccionado, "src/main/resources/archivos/FotosPerfil.txt");
            
            if(newfoto != null){  
                //eliminar contacto en FotosPerfil.txt
                System.out.println("Entra para eliminar en Fotos.TXT");
                Foto im= new Foto(nombre, apellido, newfoto);
                lstfotoPerfiles.add(im);
                escribirArchivosImagenesPersonEdit(newfoto);
                escribirArchivoImagenAsociada(newfoto, resultado);
                
            }
            
            App.setRoot("ListaContactos");
            System.out.println("Lista actualizada contactos: "+ listaContactos);
            System.out.println("Lista Campos Adiconales: "+ lstCamposAdicionales );
            System.out.println("Lista perfiles fotos: "+lstfotoPerfiles);
            
            
            }
            
    }
    
    public void escribirArchivoImagenAsociada(String foto, LLDouble<String> lista) {
        String nombre = DatosAdicionales.getNombre();
        String apellido = DatosAdicionales.getApellido();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.pathFiles + "ImagenesAsociadas.txt",true))) {
            StringBuilder fotos = new StringBuilder() ;
            Iterator<String> iterator = lista.iterator();
            while (iterator.hasNext()) {
                fotos.append(iterator.next());
                if (iterator.hasNext()) {
                    fotos.append(",");
            }
        }
            
            String fotosAso = fotos.toString();
            
            String cadena = nombre+","+apellido+","+foto+","+fotosAso;
            writer.write(cadena);
            writer.newLine();
            System.out.println("Registro de imagen asociada a al persona editada EXITOsoooooooo.......");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void EscribirArchivoContactosEditados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/Contactos.txt", true))) {
            //formato:
            //Nombre,Apellido,Telefono,Direccion,Email,Fecha pertinente,Persona Adiconal,Red social,Empresa
            String nombre = contactoEditado.getNombre();
            String Apellido = contactoEditado.getApellido();
            String Telefono = contactoEditado.getTlf().getTipoTlf() + "-" + contactoEditado.getTlf().getTlf();
            String dir = contactoEditado.getDir().getTipoDireccion() + "-" + contactoEditado.getDir().getUbicacion();
            String emai = contactoEditado.getEmail().getTipo() + "-" + contactoEditado.getEmail().getCorreo();
            String fechapertinente = contactoEditado.getFecha().getTipoFecha() + "-" + contactoEditado.getFecha().getFecha();
            String cadena = nombre + "," + Apellido + "," + Telefono + "," + dir + "," + emai + "," + fechapertinente;
            writer.write(cadena);
            System.out.println("Se escribio en el archivo el contacto ya editado exitosamenteeeeee.......");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EscribirArchivoCamposAdicionalesEditados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/CamposAdicionales.txt", true))) {
            String nombre = DatosAdicionales.getNombre();
            String apellido = DatosAdicionales.getApellido();
            String conAdicon = "ninguno";
            String red = "ninguno";
            String empresa = "ninguno";
            if (DatosAdicionales.getPer().getPersona().equals("")) {
                conAdicon = "ninguno-ninguno";
            } else {
                conAdicon = DatosAdicionales.getPer().getPersona() + "-" + DatosAdicionales.getPer().getTipoPersona();
            }
            if (DatosAdicionales.getRedSocial().getUsername().equals("")) {
                red = "ninguno-ninguno";
            } else {
                red = DatosAdicionales.getRedSocial().getUsername() + "-" + DatosAdicionales.getRedSocial().getTipoRedSocial();
            }
            if (DatosAdicionales.getEmpresa().equals("")) {
                empresa = "ninguno";
            } else {
                empresa = DatosAdicionales.getEmpresa();
            }
            writer.write(nombre + "," + apellido + "," + conAdicon + "," + red + "," + empresa);
            System.out.println("Se escribieron campos adicionales del contacto Editado exitosamenteeeeee.......");

            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void escribirArchivosImagenesPersonEdit(String i) {
        String nombre = DatosAdicionales.getNombre();
        String apellido = DatosAdicionales.getApellido();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.pathFiles + "FotosPerfil.txt",true))) {
            String cadena = nombre+","+apellido+","+i;
            writer.write(cadena);
            writer.newLine();
            
            System.out.println("Registro de imagen asociada a al persona editada EXITOsoooooooo.......");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void volver(MouseEvent event) throws IOException {
        App.setRoot("ListaContactos");
    }
    

}
