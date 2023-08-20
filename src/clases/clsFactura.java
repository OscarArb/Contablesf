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
public class clsFactura {
    // Atributos
    protected int id;
    protected String fecha;
    protected String numeroFactura;
    protected String idCliente;
    protected int total;
    protected String nombreCliente;
    protected int cajero;
  
    
    // Metodos
    // Constructores
    
    public clsFactura(int id,String fecha,String numeroFactura,String idCliente,int total,String nombreCliente, int cajero) {
        this.id= id;
        this.fecha=fecha;
        this.numeroFactura=numeroFactura;
        this.idCliente=idCliente;
        this.total=total;
        this.nombreCliente=nombreCliente;
        this.cajero=cajero;
        
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
    
    public int getCajero() {
        return cajero;
    }

    public void setCajero(int cajero) {
        this.cajero = cajero;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String  idCliente) {
        this.idCliente = idCliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    
    

    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
   
}
