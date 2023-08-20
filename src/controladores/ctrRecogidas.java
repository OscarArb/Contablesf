/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.clsBases;
import clases.clsCliente;
import clases.clsEmpleado;
import clases.clsRecogidas;
import modelos.mdlBases;
import modelos.mdlCliente;
import modelos.mdlRecogidas;
import static vistas.cacha.usuario;

/**
 *
 * @author OscarArb
 */
public class ctrRecogidas {
    mdlBases mdlBases = new mdlBases();
    mdlRecogidas mdlRecogidas = new mdlRecogidas();
    
    public ctrRecogidas(){
    
    }
    // metodos CRUD
    //Crear, editar,consultar, eliminar
   public  boolean Crear(clsRecogidas recogida){
        
        boolean s = false;
        try{
            
                //Llamado a la capa modelos.Proveedor
                s = mdlRecogidas.Crear(recogida);
            
            return s;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            return false;
            
            }
      
    } 
   
    public int ValorRecogidas(){
        
        return mdlRecogidas.ValorRecogidas();
        
        
    }
   public String NumeroRecogida(){
        
        return mdlRecogidas.NumeroRecogida();
        
        
    }
   
}
