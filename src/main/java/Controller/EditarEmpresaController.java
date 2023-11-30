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
import static Controller.EditarContactoController.contactoEditado;
import static Controller.EditarContactoController.deleteFromFileEditContact;
import static Controller.ImagenesAsociadasController.conseguirFotosAsociadas;
import static Controller.ListaContactosController.apellidoSelected;
import static Controller.ListaContactosController.cadicional;
import static Controller.ListaContactosController.contactoSelected;
import static Controller.ListaContactosController.listaContactos;
import static Controller.ListaContactosController.lstCamposAdicionales;
import static Controller.ListaContactosController.nombreSelected;
import static Controller.ListaContactosController.redsoc;
import static Controller.ListaContactosController.typec;
import static Controller.ListaContactosController.typered;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
public class EditarEmpresaController implements Initializable {
    
    public static Contacto empresaEditada;
    public static String imagenEmpresaEditada;
    public static Contacto DatosAdicionalesEmpresa;
    public static String newFotoEmpresa;

    @FXML
    private TextField labelNombreEmpresa;
    @FXML
    private TextField labelTlfEmpresa;
    @FXML
    private TextField labelContactoEmpresa;
    @FXML
    private TextField labelAddressEmpresa;
    @FXML
    private TextField labelRedSocialEmpresa;
    @FXML
    private TextField labelEmailEmpresa;
    @FXML
    private DatePicker labelDateEmpresa;
    @FXML
    private ImageView imagenEmpresa;
    @FXML
    private ComboBox<String> comboBoxTlfEmpresa;
    @FXML
    private ComboBox<String> comboBoxFechaEmpresa;
    @FXML
    private ComboBox<String> comboBoxContactoAd;
    @FXML
    private ComboBox<String> comboBoxDireccionEmpresa;
    @FXML
    private ComboBox<String> comboBoxRedSocialEmpresa;
    @FXML
    private ComboBox<String> comboBoxEmailEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ponerimagenAsociadaEmpresa(); 
        if(comboBoxDireccionEmpresa.getItems().isEmpty() && comboBoxFechaEmpresa.getItems().isEmpty() && comboBoxTlfEmpresa.getItems().isEmpty() &&
            comboBoxContactoAd.getItems().isEmpty() && comboBoxRedSocialEmpresa.getItems().isEmpty()){
            llenarComboBox();
        }
        
        mostrarInformacion();
        System.out.println("Datos actuales: "+ empresaEditada +", "+ imagenEmpresaEditada);
        System.out.println(newFotoEmpresa);
    }    

    
    public void ponerimagenAsociadaEmpresa(){
        for(Foto f : lstfotoPerfiles){
            if(contactoSelected.getNombre().equals(f.getNombre())){
                try (FileInputStream input = new FileInputStream("src/main/resources/pics/"+f.getImagen() )) {

                    Image image = new Image(input, 90, 100, true, false);
                    imagenEmpresa.setImage(image);

                } catch (IOException e) {
                    System.out.println("error al conseguir la imagen asociada");
                }
            }
        }
    
    
    }
    
    
    public void mostrarInformacion() {
        
        labelNombreEmpresa.setText(contactoSelected.getNombre());
        labelDateEmpresa.setValue(contactoSelected.getFecha().getFecha());
        labelAddressEmpresa.setText(contactoSelected.getDir().getUbicacion());
        labelTlfEmpresa.setText(contactoSelected.getTlf().getTlf());
        labelContactoEmpresa.setText(cadicional);
        labelRedSocialEmpresa.setText(redsoc);
        
        labelEmailEmpresa.setText(contactoSelected.getEmail().getTipo());
        comboBoxTlfEmpresa.setValue(contactoSelected.getTlf().getTipoTlf());
        comboBoxDireccionEmpresa.setValue(contactoSelected.getDir().getTipoDireccion());
        comboBoxFechaEmpresa.setValue(contactoSelected.getFecha().getTipoFecha());
        comboBoxContactoAd.setValue(typec);
        comboBoxRedSocialEmpresa.setValue(typered);
        comboBoxEmailEmpresa.setValue(contactoSelected.getEmail().getCorreo());
        ponerimagenAsociadaEmpresa();
    }
    
    
    public void llenarComboBox(){
        comboBoxTlfEmpresa.getItems().addAll("Telefono Convencional","Atencion al cliente","Soporte");
        comboBoxDireccionEmpresa.getItems().addAll("Sede Central","Direccion","Sucursal");
        comboBoxEmailEmpresa.getItems().addAll("Atencion al cliente","Consultas","Recursos Humanos");
        comboBoxFechaEmpresa.getItems().addAll("Fundacion","Aniversario Empresarial");
        comboBoxContactoAd.getItems().addAll("Director","Ventas","Coordinacion");
        comboBoxRedSocialEmpresa.getItems().addAll("Facebook","Instagram","Tik Tok","Twitter", "Web");
    }
    
    
    public void changeImageEmpresa(File newImageFile) {

        newFotoEmpresa = newImageFile.getName();
        try {

            FileInputStream inputStream = new FileInputStream(newImageFile);
            Image newImage = new Image(inputStream);

            imagenEmpresa.setImage(newImage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void cambiarImagenEmpresa(MouseEvent event) {
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
            
            changeImageEmpresa(selectedFile);

        }
        
        
        
    }
    
    
    public static void deleteInfoFromFile (String name, String apellido, String archivoTxt){
        File archivo = new File(archivoTxt);
        File temporal = new File(archivo.getParent(),"temporal_"+archivo.getName());
        
        try(BufferedReader lectura = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8));
            BufferedWriter escritura = new BufferedWriter(new FileWriter(temporal,false ))){
                
                String lineaDelete = name+","+"";
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
    private void volverHome(MouseEvent event) throws IOException {
        App.setRoot("ListaContactos");
    }

    @FXML
    private void guardarEmpresaEditada(MouseEvent event) throws IOException{
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
        


        
        Contacto people = new Contacto(labelNombreEmpresa.getText(),"",new Telefono(comboBoxTlfEmpresa.getValue(), labelTlfEmpresa.getText()),new Direccion(comboBoxDireccionEmpresa.getValue(),labelAddressEmpresa.getText()),
                          new Email(comboBoxEmailEmpresa.getValue(),labelEmailEmpresa.getText()), new Fecha(comboBoxFechaEmpresa.getValue(), labelDateEmpresa.getValue()));
        Contacto dateperson= new Contacto(labelNombreEmpresa.getText(),"",new PersonaAdiconal(labelContactoEmpresa.getText(), comboBoxContactoAd.getValue()), new RedSocial(comboBoxRedSocialEmpresa.getValue(), labelRedSocialEmpresa.getText()), "");
        
        
        contactoEditado = people;
        DatosAdicionalesEmpresa = dateperson;
        
        System.out.println("Esta es la lista contactos:");
        System.out.println(listaContactos);
        String nombre= contactoEditado.getNombre();
        String apellido = contactoEditado.getApellido();

        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmacion de Editar contacto");
        alert.setContentText("¿Estas seguro de Guardar los cambios realizados?");
        alert.showAndWait();
            if(alert.getResult().equals(ButtonType.OK)){
      

            System.out.println(listaContactos);

         
            System.out.println("Entra para eliminar en CONTACTOS.TXT");

            deleteInfoFromFile(nombreSeleccionado, apellidoSeleccionado,"src/main/resources/archivos/Contactos.txt");
            listaContactos.add(contactoEditado);
            escribirArchivoContactosEditado();
            
            System.out.println("Entra para eliminar en CamposAdicionales.TXT");

            deleteInfoFromFile(nombreSeleccionado, apellidoSeleccionado, "src/main/resources/archivos/CamposAdicionales.txt");
            lstCamposAdicionales.add(DatosAdicionalesEmpresa);
            EscribirArchivoCamposAdEditados();
            
            
            
            String nombreSelected = contactoSelected.getNombre();
            String apellidoSelected = "";
            LLDouble resultEmpresaPictures = conseguirFotosAsociadas(nombreSelected, apellidoSelected);
            
            deleteFromFileEditContact(nombreSelected, apellidoSelected, "src/main/resources/archivos/ImagenesAsociadas.txt");
            
            
            deleteInfoFromFile(nombreSeleccionado, apellidoSeleccionado, "src/main/resources/archivos/FotosPerfil.txt");
            
            if(newFotoEmpresa != null){  

                System.out.println("Entra para eliminar en Fotos.TXT");
                Foto im= new Foto(nombre, apellido, newFotoEmpresa);
                lstfotoPerfiles.add(im);
                escribirArchivosImagenesPersonaEditada(newFotoEmpresa);
                escribirArchivoImagenEmpresaAsociada(newFotoEmpresa, resultEmpresaPictures);
                
            }
            
            App.setRoot("ListaContactos");
            System.out.println("Lista actualizada contactos: "+ listaContactos);
            System.out.println("Lista Campos Adiconales: "+ lstCamposAdicionales );
            System.out.println("Lista perfiles fotos: "+lstfotoPerfiles);
            
            
            }
    }
    
    
    public void escribirArchivoImagenEmpresaAsociada(String foto, LLDouble<String> lista) {
        String nombre = contactoEditado.getNombre();
        String apellido = "";
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
            System.out.println("---------- -------- ----------- --------  -----");
            System.out.println("Lista de fotos: "+fotosAso);
            String cadena = nombre+","+apellido+","+foto+","+fotosAso;
            writer.write(cadena);
            writer.newLine();
            System.out.println("Registro de imagen asociada a al persona editada EXITOsoooooooo.......");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void escribirArchivosImagenesPersonaEditada(String i) {
        String nombre = DatosAdicionalesEmpresa.getNombre();
        String apellido = "";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.pathFiles + "FotosPerfil.txt",true))) {
            String cadena = nombre+","+apellido+","+i;
            writer.write(cadena);
            writer.newLine();
            
            System.out.println("Registro de imagen asociada a al persona editada EXITOsoooooooo.......");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void escribirArchivoContactosEditado() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/Contactos.txt", true))) {
            String nombre = contactoEditado.getNombre();
            String Apellido = "";
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
    
    
    public void EscribirArchivoCamposAdEditados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/CamposAdicionales.txt", true))) {
            String nombre = DatosAdicionalesEmpresa.getNombre();
            String apellido = "";
            String conAdicon = "ninguno";
            String red = "ninguno";
            String empresa = "";
            if (DatosAdicionalesEmpresa.getPer().getPersona().equals("")) {
                conAdicon = "ninguno-ninguno";
            } else {
                conAdicon = DatosAdicionalesEmpresa.getPer().getPersona() + "-" + DatosAdicionalesEmpresa.getPer().getTipoPersona();
            }
            if (DatosAdicionalesEmpresa.getRedSocial().getUsername().equals("")) {
                red = "ninguno-ninguno";
            } else {
                red = DatosAdicionalesEmpresa.getRedSocial().getUsername() + "-" + DatosAdicionalesEmpresa.getRedSocial().getTipoRedSocial();
            }
            
            writer.write(nombre + "," + apellido + "," + conAdicon + "," + red + "," + empresa);
            System.out.println("Se escribieron campos adicionales del contacto Editado exitosamenteeeeee.......");

            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
}
