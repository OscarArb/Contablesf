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
public class clsBasesAuxiliar {
    // Atributos
    protected String id;
    protected String baseInicial;
    protected String baseCierre;
    protected String cantidadRecogidas;
    
    protected String horaApertura;
    protected String horaCierre;
    protected String fecha;
    
    // Metodos
    // Constructores
    
    public clsBasesAuxiliar(String id, String baseInicial, String baseCierre,String cantidadRecogidas,String horaApertura,String horaCierre,String fecha) {
        this.id=id;
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
    
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBaseInicial() {
        return baseInicial;
    }

    public void setBaseInicial(String baseInicial) {
        this.baseInicial = baseInicial;
    }

    public String getBaseCierre() {
        return baseCierre;
    }

    public void setBaseCierre(String baseCierre) {
        this.baseCierre = baseCierre;
    }

    public String getCantidadRecogidas() {
        return cantidadRecogidas;
    }

    public void setCantidadRecogidas(String cantidadRecogidas) {
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
