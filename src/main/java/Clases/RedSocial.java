/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Carlos
 */
public class RedSocial {
    
    private String tipoRedSocial, username;

    public RedSocial(String redSocial, String username) {
        this.tipoRedSocial = redSocial;
        this.username = username;
    }
    
    @Override
    public String toString(){
        return tipoRedSocial + ":" + username;
    }

    public String getTipoRedSocial() {
        return tipoRedSocial;
    }

    public String getUsername() {
        return username;
    }

    public void setTipoRedSocial(String tipoRedSocial) {
        this.tipoRedSocial = tipoRedSocial;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
