/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.time.LocalDate;


/**
 *
 * @author Carlos
 */
public class Fecha {
    
    private String tipoFecha;
    private LocalDate fecha;

    public Fecha(String tipoFecha, LocalDate fecha) {
        this.tipoFecha = tipoFecha;
        this.fecha = fecha;
    }
    
    
    
    @Override
    public String toString(){
        return tipoFecha + ":" + fecha;
    }

    public String getTipoFecha() {
        return tipoFecha;
    }

    public void setTipoFecha(String tipoFecha) {
        this.tipoFecha = tipoFecha;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    
    
    
}
