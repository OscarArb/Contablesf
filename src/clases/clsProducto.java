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
public class clsProducto {
    // Atributos
    protected int id;
    protected String detalle;
    
    protected int valorproducto;
    
    protected String plu;
    protected String cantidad;
    protected String lote;
    protected String fechaRegistro;
    protected String fechaVencimiento;
    
    // Metodos
    // Constructores
    
    public clsProducto(int id,String detalle, String plu, int valorproducto,String cantidad,String lote,String fechaRegistro,String fechaVencimiento) {
        this.id= id;
        this.detalle = detalle;
        this.valorproducto = valorproducto;
        this.plu = plu;
        this.cantidad = cantidad;
        this.lote= lote;
        this.fechaRegistro =fechaRegistro;
        this.fechaVencimiento= fechaVencimiento;
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
    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @param detalle the detalle to set
     */
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    
    /**
     * @return the valorproducto
     */
    public int getValorproducto() {
        return valorproducto;
    }

    /**
     * @param valorproducto the valorproducto to set
     */
    public void setValorproducto(int valorproducto) {
        this.valorproducto = valorproducto;
    }
    
    public String getPlu() {
        return plu;
    }

    /**
     * @param plu the valorproducto to set
     */
    public void setPlu(String plu) {
        this.plu = plu;
    }
     public String getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the valorproducto to set
     */
    public void setCantidad(String cantidad) {
        this.plu = cantidad;
    }
    public String getLote() {
        return lote;
    }

    /**
     * @param lote the lote to set
     */
    public void setLote(String lote) {
        this.lote = lote;
    }
    public String getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
   
}
