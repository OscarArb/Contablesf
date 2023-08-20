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
public class clsEmpleado {

    
    // Atributos
    protected int id;
    protected String nombre;
    protected String cedula;
    protected String telefono;
    protected String cargo;
    protected int valorquincena;
    protected String tipoContrato;
    protected String correo;
    protected String banco;
    protected String cuentaCobro;
    private String fechaVinculacion;
    private String fechaFinContrato;
    
    
    // Metodos
    // Constructores
    
    public clsEmpleado(int id,String nombre, String cedula,String telefono, String cargo, int valorquincena,String tipoContrato,String correo,String banco,String cuentaCobro, String fechaVinculacion, String fechaFinContrato)  {
        //nombre, cedula, telefono, cargo, valorquincena
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.cargo = cargo;
        this.valorquincena = valorquincena;
        this.tipoContrato = tipoContrato;
        this.correo = correo;
        this.banco = banco;
        this.cuentaCobro= cuentaCobro;
        this.fechaVinculacion= fechaVinculacion;
        this.fechaFinContrato = fechaFinContrato;
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

    
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    /**
     * @return the valorquincena
     */
    public int getValorquincena() {
        return valorquincena;
    }

    /**
     * @param valorquincena
     */
    public void setValorquincena(int valorquincena) {
        this.valorquincena = valorquincena;
    }
    public String getTipoContrato() {
        return tipoContrato;
    }

    /**
     * @param tipoContrato
     */
    public void setTipoContrato(String tipoContrato) {
        this.cargo = tipoContrato;
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
    public String getBanco() {
        return banco;
    }

    /**
     * @param banco
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }
    public String getCuentaCobro() {
        return cuentaCobro;
    }

    /**
     * @param cuentaCobro
     */
    public void setCuentaCobro(String cuentaCobro) {
        this.cuentaCobro = cuentaCobro;
    }
    
    /**
     * @return the fechaVinculacion
     */
    public String getFechaVinculacion() {
        return fechaVinculacion;
    }

    /**
     * @param fechaVinculacion the fechaVinculacion to set
     */
    public void setFechaVinculacion(String fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    /**
     * @return the fechaFinContrato
     */
    public String getFechaFinContrato() {
        return fechaFinContrato;
    }

    /**
     * @param fechaFinContrato the fechaFinContrato to set
     */
    public void setFechaFinContrato(String fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }
    
    
    
    
}
