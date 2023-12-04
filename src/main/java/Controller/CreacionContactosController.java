
package Controller;

import Clases.*;
import static Controller.ListaContactosController.*;
import ListTDA.ArrayG9;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
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


/**
 * FXML Controller class
 *
 * @author Abeni
 */
public class CreacionContactosController implements Initializable {
    
    public static ArrayG9<String> etiqueta1 = new ArrayG9<>();
    public static ArrayG9<String> etiqueta2 = new ArrayG9<>();
    public static ArrayG9<String> etiqueta3 = new ArrayG9<>();
    public static ArrayG9<String> etiqueta4 = new ArrayG9<>();
    public static ArrayG9<String> etiqueta5 = new ArrayG9<>();
    
    //etiquetas contacto empresa
    public static ArrayG9<String> etiqueta6 = new ArrayG9<>();
    public static ArrayG9<String> etiqueta7 = new ArrayG9<>();
    public static ArrayG9<String> etiqueta8 = new ArrayG9<>();
    public static ArrayG9<String> etiqueta9 = new ArrayG9<>();
    public static ArrayG9<String> etiqueta10 = new ArrayG9<>();
    public static ArrayG9<String> etiqueta11= new ArrayG9<>();
    
    
    public static ArrayG9 <Foto> lstfotoPerfiles = new ArrayG9<>();

    String ni = null;
    ComboBox c1 = new ComboBox();
    ComboBox c2 = new ComboBox();
    Label l1 = new Label("Persona Contacto:");
    Label l2 = new Label("Red Social:      ");
    Label l3 = new Label("Empresa:");
    TextField contAsociado = new TextField();
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
    @FXML
    private TextField txtNombreEmpresa;
    @FXML
    private TextField txtDireccionEmpresa;
    @FXML
    private TextField txtTelefonoEmpresa;
    @FXML
    private TextField txtEmailEmpresa;
    @FXML
    private DatePicker txtFechaEmpresa;
    @FXML
    private TextField txtContactoAsoEmpresa;
    @FXML
    private TextField txtWebEmpresa;
    @FXML
    private ComboBox<String> comboBoxDir;
    @FXML
    private ComboBox<String> comboBoxTlf;
    @FXML
    private ComboBox<String> comboBoxEmail;
    @FXML
    private ComboBox<String> comboBoxFecha;
    @FXML
    private ComboBox<String> comboBoxContAso;
    @FXML
    private ComboBox<String> comboBoxWeb;
    @FXML
    private ImageView fotoPerfilEmpresa;
    @FXML
    private Button buttonSaveB;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contAsociado.getStyleClass().add("txfield");
        tempresa.getStyleClass().add("txfield");
        tsitioweb.getStyleClass().add("txfield");
        c1.getStyleClass().add("combo");
        c2.getStyleClass().add("combo");
        
        if (etiqueta1.isEmpty() && etiqueta2.isEmpty() && etiqueta3.isEmpty() && etiqueta3.isEmpty() && etiqueta4.isEmpty() && etiqueta5.isEmpty() && 
                etiqueta6.isEmpty() && etiqueta7.isEmpty() && etiqueta8.isEmpty() && etiqueta9.isEmpty() && etiqueta10.isEmpty() && etiqueta11.isEmpty()){
            cargarEtiquetas();
            System.out.println("Etiquetas Cargadas");

        } else {
            System.out.println("Etiquetas llenas");
        }

        camposAdicionales();

        if (cbdireccion.getItems().isEmpty() && cbemail.getItems().isEmpty() && cbtelefono.getItems().isEmpty() && cbfecha.getItems().isEmpty() && c1.getItems().isEmpty() && c2.getItems().isEmpty()
                && comboBoxDir.getItems().isEmpty() && comboBoxTlf.getItems().isEmpty() && comboBoxEmail.getItems().isEmpty() && comboBoxFecha.getItems().isEmpty()
                && comboBoxContAso.getItems().isEmpty() && comboBoxWeb.getItems().isEmpty()) {
            
            cargarCombobox();
            System.out.println("Combo Boxs Cargados");
        } else {
            System.out.println("Combo Boxs llenos");
        }

    }

    
    @FXML
    private void enviarListaContactos(ActionEvent event) {
        btguardar.setOnAction((ActionEvent e) -> {
            String typePersonaContacto = (String) c1.getValue();
            String nombre = txnombre.getText();
            String apellido = txapellido.getText();
            Telefono Tel = new Telefono (txtelefono.getText(),cbtelefono.getValue());
            Direccion di = new Direccion(cbdireccion.getValue(), txdireccion.getText());
            Email em= new Email(cbemail.getValue(),txemail.getText());
            PersonaAdicional per=new PersonaAdicional(contAsociado.getText(), typePersonaContacto);
            
            LocalDate fecha = txcalendario.getValue();
            Fecha fech= new Fecha(cbfecha.getValue(),fecha);
            RedSocial web= new RedSocial((String) c2.getValue(), tsitioweb.getText());
            String Empresa= tempresa.getText();
            
            Contacto people1= new Contacto(nombre,apellido,Tel,di,em,fech);
            Contacto camposAdicion1= new Contacto(nombre,apellido,per,web,Empresa);
            Foto ft= new Foto(nombre,apellido,ni);
            lstfotoPerfiles.add(ft);
            listaContactos.add(people1);
            lstCamposAdicionales.add(camposAdicion1);
            EscribirArchivoContactos();
            EscribirArchivoCamposAdicionales();

            escribirArchivosImagenes(ni);
            agregarAlArchivoImagenesAsociadas(ni);
            
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
            contAsociado.clear();
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
    private void guardarEmpresa(ActionEvent event) {
        

        buttonSaveB.setOnAction((ActionEvent e) -> {
            String nombre = txtNombreEmpresa.getText();
            String apellido = "";
            String Empresa= "";
            Telefono Tel = new Telefono (txtTelefonoEmpresa.getText(),comboBoxTlf.getValue());
            Direccion di = new Direccion(comboBoxDir.getValue(), txtDireccionEmpresa.getText());
            Email em= new Email(comboBoxEmail.getValue(),txtEmailEmpresa.getText());
            PersonaAdicional per=new PersonaAdicional(txtContactoAsoEmpresa.getText(),comboBoxContAso.getValue());
            
            LocalDate fecha = txtFechaEmpresa.getValue();
            Fecha fech= new Fecha(comboBoxFecha.getValue(),fecha);
            RedSocial web= new RedSocial(comboBoxWeb.getValue(), txtWebEmpresa.getText());
          
            
            Contacto person= new Contacto(nombre,apellido,Tel,di,em,fech);
            Contacto camposAdicion2= new Contacto(nombre,apellido, per,web,Empresa);
            Foto ft= new Foto(nombre,apellido,ni);
            lstfotoPerfiles.add(ft);
            listaContactos.add(person);
            lstCamposAdicionales.add(camposAdicion2);
            EscribirEmpresaContactosTxt();
            EscribirEmpresaCamposAdicionalesTxt();
          
            escribirEmpresaFotosPerfilTxt(ni);
            
            escribirEmpresaImagenesAsociadasTxt(ni);

            try (FileInputStream input = new FileInputStream("src/main/resources/icons/logoAgregarImagen.png" )) {

                Image image = new Image(input, 90, 100, true, false);
                fotoPerfilEmpresa.setImage(image);

            } catch (IOException exep) {
                System.out.println("error");
            }

            txtNombreEmpresa.clear();
            txtTelefonoEmpresa.clear();
            txtDireccionEmpresa.clear();
            txtEmailEmpresa.clear();
            txtFechaEmpresa.setValue(null);
            txtContactoAsoEmpresa.clear();
            txtWebEmpresa.clear();
            comboBoxDir.setValue("");
            comboBoxTlf.setValue("");
            comboBoxEmail.setValue("");
            comboBoxFecha.setValue("");
            comboBoxWeb.setValue("");
            comboBoxContAso.setValue("");
            

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
            hbpercontacto.getChildren().addAll(l1,contAsociado,c1);
            hbpercontacto.setSpacing(20);//20
            hbsitioweb.getChildren().addAll(l2,tsitioweb,c2);
            hbsitioweb.setSpacing(30);//30
            l2.setMaxHeight(27);//27
            l2.setMaxWidth(150);//150
            hbempresa.getChildren().addAll(l3,tempresa);
            hbempresa.setSpacing(75);//75
            hbempresa.setPadding(new Insets(10, 0, 0, 10));
          
            
        });
    }
    
    public void cargarEtiquetas() {
        //Etiquetas contacto persona        
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
        etiqueta4.add("Madre");
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
        etiqueta5.add("Twitter");

        //Etiquetas contacto empresa
        etiqueta6.add("Sede Central"); etiqueta6.add("Direccion");etiqueta6.add("Sucursal");
        etiqueta7.add("Telefono Convencional"); etiqueta7.add("Atencion al cliente"); etiqueta7.add("Soporte");
        etiqueta8.add("Atencion al cliente"); etiqueta8.add("Consultas"); etiqueta8.add("Recursos Humanos");
        etiqueta9.add("Fundacion");etiqueta9.add("Aniversario Empresarial"); etiqueta8.add("Ventas especiales");
        etiqueta10.add("Director"); etiqueta10.add("Ventas"); etiqueta10.add("Coordinacion");
        etiqueta11.add("Facebook"); etiqueta11.add("Instagram"); etiqueta11.add("Twitter");
        etiqueta11.add("TikTok"); etiqueta11.add("Web"); 
        
        
          
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
    
        if (comboBoxDir.getItems().isEmpty()) {
            for (String dirEmpresa : etiqueta6) {
                comboBoxDir.getItems().add(dirEmpresa);
            }
        }
        
        if (comboBoxTlf.getItems().isEmpty()) {
            for (String tlf : etiqueta7) {
                comboBoxTlf.getItems().add(tlf);
            }
        }
        
        if (comboBoxEmail.getItems().isEmpty()) {
            for (String email : etiqueta8) {
                comboBoxEmail.getItems().add(email);
            }
        }
        
        if (comboBoxFecha.getItems().isEmpty()) {
            for (String fecha : etiqueta9) {
                comboBoxFecha.getItems().add(fecha);
            }
        }
        
        if (comboBoxContAso.getItems().isEmpty()) {
            for (String asociado : etiqueta10) {
                comboBoxContAso.getItems().add(asociado);
            }
        }
        
        if (comboBoxWeb.getItems().isEmpty()) {
            for (String web : etiqueta11) {
                comboBoxWeb.getItems().add(web);
            }
        }

    }

    @FXML
    private void ocultarCampos(ActionEvent event) {
        hbpercontacto.getChildren().clear();
        hbsitioweb.getChildren().clear();
        hbempresa.getChildren().clear();
    }
    
    public void EscribirArchivoContactos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/Contactos.txt",true))) {
            //formato:
            //Nombre,Apellido,Telefono,Direccion,Email,Fecha pertinente,Persona Adiconal,Red social,Empresa
            String nombre=txnombre.getText();
            String Apellido=txapellido.getText();
            String Telefono = cbtelefono.getValue()+"-"+txtelefono.getText();
            String dir=cbdireccion.getValue()+"-"+txdireccion.getText();
            String emai= cbemail.getValue()+"-"+txemail.getText();
            String fechapertinente = cbfecha.getValue()+"-"+txcalendario.getValue();
            String cadena = nombre+","+Apellido+","+Telefono+","+dir+","+emai+","+fechapertinente;
            writer.write(cadena);
            System.out.println("Se escribio un contacto exitosamenteeeeee.......");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void EscribirEmpresaContactosTxt() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/Contactos.txt",true))) {
            //formato:
            //Nombre,Apellido,Telefono,Direccion,Email,Fecha pertinente,Persona Adiconal,Red social,Empresa
            String nombre=txtNombreEmpresa.getText();
            String Apellido="";
            String Telefono = comboBoxTlf.getValue()+"-"+txtTelefonoEmpresa.getText();
            String dir=comboBoxDir.getValue()+"-"+txtDireccionEmpresa.getText();
            String email= comboBoxEmail.getValue()+"-"+txtEmailEmpresa.getText();
            String fechapertinente = comboBoxFecha.getValue()+"-"+txtFechaEmpresa.getValue();
            String cadena = nombre+","+Apellido+","+Telefono+","+dir+","+email+","+fechapertinente;
            writer.write(cadena);
            System.out.println("Se escribio un contacto exitosamenteeeeee.......");
            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void EscribirArchivoCamposAdicionales(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/CamposAdicionales.txt",true))) {
            String nombre =txnombre.getText();
            String apellido = txapellido.getText();
            String conAdicon = "ninguno";
            String red = "ninguno";
            String empresa="ninguno";
            if (contAsociado.getText().equals("")) {
                conAdicon = "ninguno-ninguno";
            } else {
                conAdicon = contAsociado.getText() + "-" +c1.getValue();
            }
            if (tsitioweb.getText().equals("")) {
                red = "ninguno-ninguno";
            }else{
                red = tsitioweb.getText() +"-"+ c2.getValue();
            }
            if(tempresa.getText().equals("")){
                empresa = "ninguno";
            }else{
                empresa = tempresa.getText();
            }
            writer.write(nombre+","+apellido+","+conAdicon+","+red+","+empresa);
            System.out.println("Se escribieron campos adicionales exitosamenteeeeee.......");

            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void EscribirEmpresaCamposAdicionalesTxt(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/archivos/CamposAdicionales.txt",true))) {
            String nombre =txtNombreEmpresa.getText();
            String apellido = "";
            String contactoAdicional = txtContactoAsoEmpresa.getText()+" - "+ comboBoxContAso.getValue();
            String web = txtWebEmpresa.getText()+" - "+ comboBoxWeb.getValue();
            String empresa="";
            
            writer.write(nombre+","+apellido+","+contactoAdicional+","+web+","+empresa);
            System.out.println("Se escribieron campos adicionales exitosamenteeeeee.......");

            writer.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escribirArchivosImagenes(String i) {
        String nombre = txnombre.getText();
        String apellido = txapellido.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.pathFiles + "FotosPerfil.txt",true))) {
            String cadena = nombre+","+apellido+","+i;
            writer.write(cadena);
            writer.newLine();
            
            System.out.println("Registro EXITOsoooooooo.......");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void agregarAlArchivoImagenesAsociadas(String foto){
        String nombre = txnombre.getText();
        String apellido = txapellido.getText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.pathFiles + "ImagenesAsociadas.txt",true))) {
            String cadena = nombre+","+apellido+","+foto;
            writer.write(cadena);
            writer.newLine();
            
            System.out.println("Registro al archivo imagenes asociadas");

        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    public void escribirEmpresaFotosPerfilTxt(String i) {
        String nombre = txtNombreEmpresa.getText();
        String apellido = "";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.pathFiles + "FotosPerfil.txt",true))) {
            String cadena = nombre+","+apellido+","+i;
            writer.write(cadena);
            writer.newLine();
            
            System.out.println("Registro EXITOsoooooooo.......");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public void escribirEmpresaImagenesAsociadasTxt(String i) {
        String nombre = txtNombreEmpresa.getText();
        String apellido = "";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(App.pathFiles + "ImagenesAsociadas.txt",true))) {
            String cadena = nombre+","+apellido+","+i;
            writer.write(cadena);
            writer.newLine();
            
            System.out.println("Registro EXITOsoooooooo.......");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void changeImage(File newImageFile, ImageView imagen) {
        
        ni = newImageFile.getName();
        try {
            // Cargamos la imagen
            FileInputStream inputStream = new FileInputStream(newImageFile);
            Image newImage = new Image(inputStream);

            // Actualizacion
            imagen.setImage(newImage);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void elegirImagen(MouseEvent event) {
        elegirImagenPerfil(fotoPerfil);
    }

    @FXML
    private void elegirImagenEmpresa(MouseEvent event) {


        elegirImagenPerfil(fotoPerfilEmpresa);

        

    }
    
    private void elegirImagenPerfil(ImageView imagen){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar nueva imagen");
        
        File initialDirectory = new File("src/main/resources/pics");
        fileChooser.setInitialDirectory(initialDirectory);

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de imagen", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        Window stage = null;

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            
            changeImage(selectedFile, imagen);

        }
    }

}    
    
    

    

    
 
    

