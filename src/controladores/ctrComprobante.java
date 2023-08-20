/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.clsComprobante;
import modelos.mdlComprobante;

/**
 *
 * @author Usuario
 */
public class ctrComprobante {
    mdlComprobante mdlComprobante = new mdlComprobante();
    public ctrComprobante(){
    
    }
    
    public clsComprobante Consultar(int usuario, String fecha){
        
        try{
            
                //Llamado a la capa modelos.Modelo Empleado
                return mdlComprobante.Consultar(usuario,fecha);
               
         }catch(Exception e ){
            System.out.println("La exepcion es:: "+ e); 
            return null;
            
            }
    }
}
