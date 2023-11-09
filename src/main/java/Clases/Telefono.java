/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Carlos
 */
public class Telefono {
    
    private String tipoTlf, tlf;

    public Telefono(String tipo, String tlf) {
        this.tipoTlf = tipo;
        this.tlf = tlf;
    }

    @Override
    public String toString(){
        return tipoTlf + ":" + tlf;
    }

    public String getTipoTlf() {
        return tipoTlf;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTipoTlf(String tipoTlf) {
        this.tipoTlf = tipoTlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }
    
    
    
}
