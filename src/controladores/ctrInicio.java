/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;



import clases.clsFactura;
import modelos.mdlInicio;

/**
 *
 * @author OscarArb
 */
public class ctrInicio {
    mdlInicio mdlInicio = new mdlInicio();
    public ctrInicio(){
    
    }
    public String ListarProducto(){
        return mdlInicio.ListarProducto();
    }
    public int UltimaFactura(){
        return mdlInicio.UltimaFactura();
    }
    public String ListarEmpleado(){
        return mdlInicio.ListarEmpleado();
    }
    public String ListarInventario(){
        
        return mdlInicio.ListarInventario();
        
    }
    public String ListarId(){
        return mdlInicio.ListarId();
    }
     public String ListarPlu(){
        return mdlInicio.ListarPlu();
    }
    public String ListarPrecio(){
        return mdlInicio.ListarPrecio();
    }
    
    
    public boolean GuardarFactura(clsFactura factura){
       
        try{
            if (factura.getTotal()> 0){
                //Llamado a la capa modelos.Modelo Empleado
               // mdlEmpleado.Crear(empleado);
               mdlInicio.GuardarFactura(factura);
            }
            return true;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            return false;
            
            }
        
        
    }
    public int TotalVenta(){
        
        return mdlInicio.TotalVenta();
        
        
    }
}
