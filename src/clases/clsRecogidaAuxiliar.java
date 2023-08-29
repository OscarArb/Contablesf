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
public class clsRecogidaAuxiliar {
    // Atributos
    protected String id;
    
    protected String valor;
    
    protected String numeroRecogida;
    protected String hora;
    protected String fecha;
    
    // Metodos
    // Constructores
    
    public clsRecogidaAuxiliar(String id,String valor, String numeroRecogida,String hora,String fecha) {
        this.id= id;
        this.valor=valor;
        this.numeroRecogida=numeroRecogida;
        this.hora=hora;
        this.fecha=fecha;
    }

    

    
    //Setter y Getter
        /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
   

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNumeroRecogida() {
        return numeroRecogida;
    }

    public void setNumeroRecogida(String numeroRecogida) {
        this.numeroRecogida = numeroRecogida;
    }

    

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
   
}
