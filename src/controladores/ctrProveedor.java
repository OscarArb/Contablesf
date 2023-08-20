/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import clases.clsProveedor;
import modelos.mdlProveedor;

/**
 *
 * @author Usuario
 */
public class ctrProveedor {
    mdlProveedor mdlProveedor = new mdlProveedor();
    public ctrProveedor(){
    
    }
    public  boolean Crear(clsProveedor proveedor){
        //si se crea el objeto al nivel del modelo de datos(base de datos,lista)
        //true
        //false
        boolean s = false;
        try{
            
                //Llamado a la capa modelos.Proveedor
                s = mdlProveedor.Crear(proveedor);
            
            return s;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            return false;
            
            }
      
    }
    public clsProveedor Consultar(String nit){
        
        try{
            
                return mdlProveedor.Consultar(nit);
      
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            return null;
            
            }
    }
    public String Listar(){
        return mdlProveedor.Listar();
    }
     public clsProveedor Eliminar (String nit){
        
        try{
            
                //Llamado a la capa modelos.Modelo Empleado
                return mdlProveedor.Eliminar(nit);
                
        
            
         }catch(Exception e ){
            System.out.println("Este es el id recibida en el controlador: "+ nit);
            System.out.println("La exepcion es: TEXTO: "+ e); 
            return null;
            
            }
    }
    public  boolean Actualizar(clsProveedor proveedor){
      
        try{
            if (proveedor.getId() > 0){
                //Llamado a la capa modelos.Modelo Empleado
                mdlProveedor.Actualizar(proveedor);
            }else{System.out.println("Valor 0 no actualizable");}
            return true;
            
         }catch(Exception e ){
            System.out.println("La exepcion es: "+ e); 
            
            return false;
            
            }
      
    }
}
