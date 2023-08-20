/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import clases.clsProducto;
import modelos.mdlProducto;

/**
 *
 * @author Usuario
 */
public class ctrProducto {
     mdlProducto mdlProducto = new mdlProducto();
     
     public ctrProducto(){
    
    }
    // metodos CRUD
    //Crear, editar,consultar, eliminar
     public boolean Crear(clsProducto producto){
        boolean s = false;
        try{
            if (producto.getValorproducto()> 0){
                //Llamado a la capa modelos.Modelo Empleado
                s= mdlProducto.Crear(producto);
            }
            return s;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            return false;
            
            }
    
}
     public clsProducto Consultar(String plu){
        
        try{
            
                //Llamado a la capa modelos.Modelo Empleado
                return mdlProducto.Consultar(plu);
               
         }catch(Exception e ){
            System.out.println("Este es el PLU: "+ plu);
            System.out.println("La exepcion es:: "+ e); 
            return null;
            
            }
    }
    public clsProducto Eliminar (String id){
        
        try{
            
                //Llamado a la capa modelos.Modelo Empleado
                return mdlProducto.Eliminar(id);
                
        
            
         }catch(Exception e ){
            System.out.println("Este es el id recibida en el controlador: "+ id);
            System.out.println("La exepcion es: TEXTO: "+ e); 
            return null;
            
            }
    }
    public  boolean Actualizar(clsProducto producto){
      
        try{
            if (producto.getId() > 0){
                //Llamado a la capa modelos.Modelo Empleado
                mdlProducto.Actualizar(producto);
            }else{System.out.println("Id 0 no actualizable"); }
            return true;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            
            return false;
            
            }
      
    }
}