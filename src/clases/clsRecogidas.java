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
public class clsRecogidas {
    // Atributos
    protected int id;
    protected int usuario;
    
    protected int valor;
    
    protected String numeroRecogida;
    protected String hora;
    protected String fecha;
    
    // Metodos
    // Constructores
    
    public clsRecogidas(int id,int usuario,int valor, String numeroRecogida,String hora,String fecha) {
        this.id= id;
        this.usuario=usuario;
        this.valor=valor;
        this.numeroRecogida=numeroRecogida;
        this.hora=hora;
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
