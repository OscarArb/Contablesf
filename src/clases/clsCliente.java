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
public class clsCliente {

    
    // Atributos
    protected int id;
    protected String nombre;
    protected String cedula;
    protected String telefono;
    protected String celular;
    protected String direccion;
    protected int facturas; 
    protected String correo;
    private String fechaRegistro;
   
    // Metodos
    // Constructores
    
    public clsCliente(int id,String nombre, String cedula,String telefono, String celular, String direccion,int facturas,String correo,String fechaRegistro)  {
        //nombre, cedula, telefono, cargo, valorquincena
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.celular = celular;
        this.direccion = direccion;
        this.facturas = facturas;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
    }

   
    //Setter y Getter
   
     /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula
     */
    public void setCedula(String cedula) {
        this.nombre = cedula;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    /**
     * @return the facturas
     */
    public int getFacturas() {
        return facturas;
    }

    /**
     * @param facturas
     */
    public void setFacturas(int facturas) {
        this.facturas = facturas;
    }
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro
     */
    public void setBanco(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    

    
    
    
    
    
}
