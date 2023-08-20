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
public class clsEmpresas {
    protected String empresa;
    protected String nit;
    protected String direccion;
    protected String email;
    protected String telefono;
    protected String usuario;
    protected String clave;
    
    
    //metodos
    
    public clsEmpresas(String empresa, String nit,String direccion, String email, String telefono,String usuario, String clave) {
        //nombre, cedula, telefono, cargo, valorquincena
        this.empresa = empresa;
        this.nit = nit;
        this.direccion = direccion;
        this.email =email;
        this.telefono = telefono;
        this.usuario = usuario;
        this.clave = clave;
    }
    
    //Setter y Getter
    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the nombre to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    //
    public String getNit() {
        return nit;
    }

    /**
     * @param nit the nombre to set
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the fechapago to set
     */
    public void setCargo(String email) {
        this.email = email;
    }
    
     /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the fechapago to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
     /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the fechapago to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
     /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }
    
}
