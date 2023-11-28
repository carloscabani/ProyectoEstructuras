/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import ListTDA.LLDouble;

/**
 *
 * @author Abeni
 */
public class Foto {
    private String nombre;
    private String apellido;
    private String imagen;
    private LLDouble<String> listaImagenesAsociadas;

    public LLDouble<String> getListaImagenesAsociadas() {
        return listaImagenesAsociadas;
    }

    public void setListaImagenesAsociadas(LLDouble<String> listaImagenesAsociadas) {
        this.listaImagenesAsociadas = listaImagenesAsociadas;
    }

    public Foto(String nombre, String apellido, String imagen) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.imagen = imagen;
    }
    
    public Foto(String nombre, String apellido, LLDouble imagenesAsociadas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.listaImagenesAsociadas = imagenesAsociadas;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return nombre+","+apellido+","+imagen;
    }
    
    
    
    
    
}
