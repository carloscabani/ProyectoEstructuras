/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.List;

/**
 *
 * @author Carlos
 */
//public class Contacto {
public class Contacto implements Comparable<Contacto>{
    
    private String nombre;
    
    private String apellido;
    
    private Telefono tlf;
  
    private Direccion dir;
    
    private Email email;
    
    private PersonaAdicional per; 

    
    private Fecha fecha;
    
    private RedSocial redSocial;
    
    private String empresa;
    
    private String rutaArchivoTxt = "src/main/resources/archivos/Contactos.txt";

    public Contacto(String nombre, String apellido, Telefono tlf, Direccion dir, Email email, Fecha fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tlf = tlf;
        this.dir = dir;
        this.email = email;
        this.fecha = fecha;
    }

    public Contacto(String n, String ape, PersonaAdicional personaAd, RedSocial redSocial, String empresa) {
        this.nombre = n;
        this.apellido = ape;
        this.per = personaAd;
        this.redSocial = redSocial;
        this.empresa = empresa;
    }
    
    
    
    
    
    
    public String getRutaTxt(){
        return rutaArchivoTxt;
    }
    
    public void setRutaTxt(String ruta) {
        this.rutaArchivoTxt = ruta;
    }
    
    
    public PersonaAdicional getPer() {
        return per;
    }

    public void setPer(PersonaAdicional per) {
        this.per = per;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Telefono getTlf() {
        return tlf;
    }

    public void setTlf(Telefono tlf) {
        this.tlf = tlf;
    }

    public Direccion getDir() {
        return dir;
    }

    public void setDir(Direccion dir) {
        this.dir = dir;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public RedSocial getRedSocial() {
        return redSocial;
    }

    public void setRedSocial(RedSocial redSocial) {
        this.redSocial = redSocial;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    @Override
    public int compareTo(Contacto otroContacto) {
        return this.getNombre().compareTo(otroContacto.getNombre());
    }
    

    @Override
    public String toString() {
        return nombre +" "+ apellido;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Contacto otroContacto = (Contacto) obj;
        if (apellido == null) {
            return nombre.equals(otroContacto.nombre);
        } else {
            return nombre.equals(otroContacto.nombre) && apellido.equals(otroContacto.apellido);
        }
    }
   
}