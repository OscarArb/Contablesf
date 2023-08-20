/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.clsCliente;
import clases.clsEmpleado;
import modelos.mdlCliente;
import modelos.mdlEmpleado;

/**
 *
 * @author OscarArb
 */
public class ctrCliente {
    mdlCliente mdlCliente = new mdlCliente();
    
    public ctrCliente(){
    
    }
    // metodos CRUD
    //Crear, editar,consultar, eliminar
   public  boolean Crear(clsCliente cliente){
        
        boolean s = false;
        try{
            
                //Llamado a la capa modelos.Proveedor
                s = mdlCliente.Crear(cliente);
            
            return s;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            return false;
            
            }
      
    } 
   
     public clsCliente Consultar(String cedula){
        
        try{
            
                //Llamado a la capa modelos.Modelo cliente
                return mdlCliente.Consultar(cedula);
               
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
}
