/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author OscarArb
 */
public class clsUsuario {
    // Atributos
    protected int id;
    protected int usuario;
    protected String empleado;
    protected String cajero;
    protected String administrador;
    protected String password;
  
    public clsUsuario(int id,int usuario,String empleado,String cajero,String administrador, String password) {
        this.id= id;
        this.usuario=usuario;
        this.empleado=empleado;
        this.cajero= cajero;
        this.administrador= administrador;
        this.password=password;
    }

    
    
    //Setter y Getter
        /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    

    

    

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
    
    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public String getAdministrador() {
        return administrador;
    }

    public void setAdministrador(String administrador) {
        this.administrador = administrador;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
}
