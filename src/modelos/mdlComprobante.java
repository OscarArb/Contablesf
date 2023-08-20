/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.clsComprobante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Usuario
 */
public class mdlComprobante {
    dbConnection dbConnection;
    
        //Constructor
    public mdlComprobante() {
        dbConnection = new dbConnection();
    }
    
     public clsComprobante  Consultar(int usuario, String fecha){
        
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT * FROM `comprobantes` WHERE cedula = "+usuario+" AND fecha= '"+fecha+"'";
            System.out.println("Qwuery:  "+query);
            PreparedStatement statementPersona = connection.prepareStatement(query);
            
           
            ResultSet resultado = statementPersona.executeQuery();
            while (resultado.next()){
                int id = resultado.getInt(1);
                int cedula = resultado.getInt(2);
                String fech = resultado.getString(3);
                
                int valor = resultado.getInt(4);
                
                
              
      
                clsComprobante  comprobante= new clsComprobante( id,cedula,fech,valor);
              //  System.out.println("Se encontro el producto"+valorproducto+"  "+detalle+"  "+fechaRegistro);
                return comprobante;
            }
            
            System.out.println("No se encontro el empleado");
           return null;
        }catch(Exception e){
            System.out.println("La excepcion es:!"+ e);
            return null;
        }
    }
    
}
