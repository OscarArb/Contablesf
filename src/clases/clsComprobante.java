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
public class clsComprobante {
    // Atributos
    protected int id;
    protected int usuario;
    protected String fecha;
    protected int valor;
    
    
    
    // Metodos
    // Constructores
    
    public clsComprobante(int id,int usuario,String fecha,int valor) {
        this.id= id;
        this.usuario=usuario;
        this.valor=valor;
        this.fecha=fecha;
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

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }


    


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
   
}
