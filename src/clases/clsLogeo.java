/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Usuario
 */
public class clsLogeo {
    protected String usuario;
    protected String clave;
    
    public clsLogeo(String usuario, String clave) {
        //nombre, cedula, telefono, cargo, valorquincena
        this.usuario = usuario;
        this.clave = clave;
       
    }
    
    //Setter y Getter
    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    //
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the nombre to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
}
