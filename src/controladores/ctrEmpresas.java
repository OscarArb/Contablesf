/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.clsEmpresas;
import modelos.mdlEmpresas;

/**
 *
 * @author Usuario
 */
public class ctrEmpresas {
    mdlEmpresas mdlEmpresas = new mdlEmpresas();
    
    public ctrEmpresas(){
    
    }
    public boolean Crear(clsEmpresas empresas){
        
        try{
            
                mdlEmpresas.Crear(empresas);
            
            return true;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            return false;
            
            }
        
        
    }
    
}
