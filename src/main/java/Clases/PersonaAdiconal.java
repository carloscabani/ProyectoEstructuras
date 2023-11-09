/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Abeni
 */
public class PersonaAdiconal {
    private String nombreCompleto;
    private String tipo;

    public PersonaAdiconal(String nombre, String tipo) {
        this.nombreCompleto = nombre;
        this.tipo = tipo;
    }

    public String getPersona() {
        return nombreCompleto;
    }

    public void setPersona(String nombre) {
        this.nombreCompleto = nombre;
    }

    public String getTipoPersona() {
        return tipo;
    }

    public void setTipoPersona(String tipo) {
        this.tipo = tipo;
    }
    
    public String toString(){
    
        return tipo + ":" + nombreCompleto;
    }
    
    

    
    
}
