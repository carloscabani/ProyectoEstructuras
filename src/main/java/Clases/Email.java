/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Carlos
 */
public class Email {
    
    private String tipo;
    private String correo;

    public Email(String tipo, String direccion) {
        this.tipo = tipo;
        this.correo = direccion;
    }
    
    @Override
    public String toString(){
        return tipo + ":" + correo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    
}
