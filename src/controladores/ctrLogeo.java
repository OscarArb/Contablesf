/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.clsLogeo;
import modelos.mdlLogeo;

/**
 *
 * @author Usuario
 */
public class ctrLogeo {
    //mdlEmpleado mdlEmpleado = new mdlEmpleado();
    mdlLogeo mdlLogeo = new mdlLogeo();
    public ctrLogeo(){
    
    }
    
    
    //public boolean Logeo(clsEmpleado empleado){
    
    
    public clsLogeo logeo(String usuario){
        
        try{
            
                //Llamado a la capa modelos.Modelo Empleado
                return mdlLogeo.Logeo(usuario);
                
            
            
            
            
         }catch(Exception e ){
            System.out.println("Este es el usuario: "+ usuario);
            System.out.println("La exepcion es:: "+ e); 
            return null;
            
            }
    }
    
}
