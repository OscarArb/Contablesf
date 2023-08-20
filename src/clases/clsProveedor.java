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
public class clsProveedor {
    // Atributos
    protected int id;
    protected String nombre;
    protected String nit;
    protected String telefono;
    protected String direccion;
    protected String correo;
    protected String gerente;
    protected String telGerente;
    protected String correoGerente;
    protected String fechaRegistrado;
    
    
    // Metodos
    // Constructores
    
    public clsProveedor(int id,String nombre, String nit,String telefono,String direccion,String correo,String gerente,String telGerente,String correoGerente,String fechaRegistrado) {
        //nombre, cedula, telefono, cargo, valorquincena
        this.id = id;
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.gerente = gerente;
        this.telGerente = telGerente;
        this.correoGerente = correoGerente;
        this.fechaRegistrado = fechaRegistrado;
       
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
    /**
     * @return the nombre
     */
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
    public String getNit() {
        return nit;
    }

    /**
     * @param nit
     */
    public void setNit(String nit) {
        this.nit = nit;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    
    public String getDireccion() {
        return direccion;
    }

    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * @return the gerente
     */
    public String getGerente() {
        return gerente;
    }

    /**
     * @param gerente the gerente to set
     */
    public void setGerente(String gerente) {
        this.gerente = gerente;
    }
    /**
     * @return the telGerente
     */
    public String getTelGerente() {
        return telGerente;
    }

    /**
     * @param telGerente the telGerente to set
     */
    public void setTelGerente(String telGerente) {
        this.telGerente = telGerente;
    }
    /**
     * @return the correoGerente
     */
    public String getCorreoGerente() {
        return correoGerente;
    }

    /**
     * @param correoGerente the correoGerente to set
     */
    public void setCorreoGerente (String correoGerente) {
        this.correoGerente = correoGerente;
    }
    /**
     * @return the fechaRegistrado
     */
    public String getFechaRegistrado() {
        return fechaRegistrado;
    }

    /**
     * @param fechaRegistrado the fechaRegistrado to set
     */
    public void setFechaRegistrado(String fechaRegistrado) {
        this.fechaRegistrado = fechaRegistrado;
    }
    
    
}
