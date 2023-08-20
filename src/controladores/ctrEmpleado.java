/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.clsEmpleado;
import modelos.mdlEmpleado;

/**
 *
 * @author OscarArb
 */
public class ctrEmpleado {
    mdlEmpleado mdlEmpleado = new mdlEmpleado();
    
    public ctrEmpleado(){
    
    }
    // metodos CRUD
    //Crear, editar,consultar, eliminar
   public  boolean Crear(clsEmpleado empleado){
        
        boolean s = false;
        try{
            
                //Llamado a la capa modelos.Proveedor
                s = mdlEmpleado.Crear(empleado);
            
            return s;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            return false;
            
            }
      
    } 
   
     public clsEmpleado Consultar(String cedula){
        
        try{
            
                //Llamado a la capa modelos.Modelo Empleado
                return mdlEmpleado.Consultar(cedula);
               
         }catch(Exception e ){
            System.out.println("La exepcion es:: "+ e); 
            return null;
            
            }
    }
    public clsEmpleado Eliminar (String id){
        
        try{
            
                //Llamado a la capa modelos.Modelo Empleado
                return mdlEmpleado.Eliminar(id);
                
        
            
         }catch(Exception e ){
            System.out.println("Este es el id recibida en el controlador: "+ id);
            System.out.println("La exepcion es: TEXTO: "+ e); 
            return null;
            
            }
    }
    public  boolean Actualizar(clsEmpleado empleado){
      
        try{
            if (empleado.getId() > 0){
                //Llamado a la capa modelos.Modelo Empleado
                mdlEmpleado.Actualizar(empleado);
            }else{System.out.println("Valor 0 no actualizable");}
            return true;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            
            return false;
            
            }
      
    }
}
