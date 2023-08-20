/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.clsEmpleado;
import clases.clsUsuario;
import modelos.mdlEmpleado;
import modelos.mdlUsuario;

/**
 *
 * @author OscarArb
 */
public class ctrUsuario {
    mdlUsuario mdlUsuario = new mdlUsuario();
    
    public ctrUsuario(){
    
    }
    // metodos CRUD
    
   
     public clsUsuario Consultar(String cedula){
        
        try{
            
                //Llamado a la capa modelos.Modelo Empleado
                return mdlUsuario.Consultar(cedula);
               
         }catch(Exception e ){
            System.out.println("La exepcion es:: "+ e); 
            return null;
            
            }
    }
    public String consultaCajeros(){
        return mdlUsuario.consultaCajeros();
    }
}
