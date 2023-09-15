/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.clsBases;
import clases.clsBasesAuxiliar;
import clases.clsCliente;
import clases.clsEmpleado;
import modelos.mdlBases;
import modelos.mdlCliente;
import modelos.mdlEmpleado;
import static vistas.cacha.usuario;

/**
 *
 * @author OscarArb
 */
public class ctrBases {
    mdlBases mdlBases = new mdlBases();
    mdlCliente mdlCliente = new mdlCliente();
    
    public ctrBases(){
    
    }
    // metodos CRUD
    //Crear, editar,consultar, eliminar
   public  boolean Crear(clsBases base){
        
        boolean s = false;
        try{
            
                //Llamado a la capa modelos.Proveedor
                s = mdlBases.Crear(base);
            
            return s;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            return false;
            
            }
      
    } 
   
    public  boolean GenerarCierre(clsBases cierre){
        
        boolean s = false;
        try{
            
                //Llamado a la capa modelos.Proveedor
                s = mdlBases.GenerarCierre(cierre);
            
            return s;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            return false;
            
            }
      
    }
    
   
     public clsBasesAuxiliar Consultar(String fecha){
        
        try{
            
                //Llamado a la capa modelos.Modelo cliente
                return mdlBases.Consultar(fecha);
               
         }catch(Exception e ){
            System.out.println("La exepcion es:: "+ e); 
            return null;
            
            }
    }
     public clsBasesAuxiliar ConsultarCierre(String user){
        
        try{
            
                //Llamado a la capa modelos.Modelo
                return mdlBases.ConsultarCierre(user);
               
         }catch(Exception e ){
            System.out.println("La exepcion es:: "+ e); 
            return null;
            
            }
    }
    public clsCliente Eliminar (String id){
        
        try{
            
                //Llamado a la capa modelos.Modelo cliente
                return mdlCliente.Eliminar(id);
                
        
            
         }catch(Exception e ){
            System.out.println("Este es el id recibida en el controlador: "+ id);
            System.out.println("La exepcion es: TEXTO: "+ e); 
            return null;
            
            }
    }
    public  boolean Actualizar(clsCliente cliente){
      
        try{
            if (cliente.getId() > 0){
                //Llamado a la capa modelos.Modelo cliente
                mdlCliente.Actualizar(cliente);
            }else{System.out.println("Valor 0 no actualizable");}
            return true;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            
            return false;
            
            }
      
    }
    public String totalBaseHoy(){
        
        return mdlBases.totalBaseHoy();
        
        
    }
    
    
}
