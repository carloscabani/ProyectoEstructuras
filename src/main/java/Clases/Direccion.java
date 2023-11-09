/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Carlos
 */
public class Direccion {
    
    private String tipoDireccion, ubicacion;

    public Direccion(String tipoDireccion, String ubicacion) {
        this.tipoDireccion = tipoDireccion;
        this.ubicacion = ubicacion;
    }
    
    @Override
    public String toString(){
        return tipoDireccion + ":" + ubicacion;
    }


    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    
}
