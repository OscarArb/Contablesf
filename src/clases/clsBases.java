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
public class clsBases {
    // Atributos
    protected int id;
    protected int usuario;
    
    protected int baseInicial;
    protected int baseCierre;
    protected int cantidadRecogidas;
    
    protected String horaApertura;
    protected String horaCierre;
    protected String fecha;
    
    // Metodos
    // Constructores
    
    public clsBases(int id,int usuario,int baseInicial, int baseCierre,int cantidadRecogidas,String horaApertura,String horaCierre,String fecha) {
        this.id= id;
        this.usuario=usuario;
        this.baseInicial=baseInicial;
        this.baseCierre= baseCierre;
        this.cantidadRecogidas= cantidadRecogidas;
        this.horaApertura=horaApertura;
        this.horaCierre=horaCierre;
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

    public int getBaseInicial() {
        return baseInicial;
    }

    public void setBaseInicial(int baseInicial) {
        this.baseInicial = baseInicial;
    }

    public int getBaseCierre() {
        return baseCierre;
    }

    public void setBaseCierre(int baseCierre) {
        this.baseCierre = baseCierre;
    }

    public int getCantidadRecogidas() {
        return cantidadRecogidas;
    }

    public void setCantidadRecogidas(int cantidadRecogidas) {
        this.cantidadRecogidas = cantidadRecogidas;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }
    
    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
   
}
