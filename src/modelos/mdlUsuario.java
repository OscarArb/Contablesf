/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.clsEmpleado;
import clases.clsProducto;
import clases.clsUsuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author OscarArb
 */
public class mdlUsuario {

    dbConnection dbConnection;
    
        //Constructor
    public mdlUsuario() {
        dbConnection = new dbConnection();
    }

    
   
    //
    public clsUsuario  Consultar(String cedula){
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT * FROM `usuarios` WHERE cedulaEmpleado= ?";
            PreparedStatement statementPersona = connection.prepareStatement(query);
            
            
            statementPersona.setString(1, cedula);
           
            ResultSet resultado = statementPersona.executeQuery();
            while (resultado.next()){
                int id = resultado.getInt(1);
                int cedul = resultado.getInt(2);
                String empleado = resultado.getString(3);
                String cajero = resultado.getString(4);
                String administrador= resultado.getString(5);
                String ps= resultado.getString(6);
                
              
      
                clsUsuario usuario = new clsUsuario( id,cedul,empleado,cajero, administrador,ps);
                return usuario;
            }
            
           return null;
        }catch(Exception e){
            System.out.println("La excepcion es:!2."+ e);
            return null;
        }
    }
    public String consultaCajeros(){
        System.out.println("Listado Empleado");
        String Lista ="";
        
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT `cedulaEmpleado` FROM `usuarios` WHERE cajero ='si'";
            PreparedStatement statementEmpleado = connection.prepareStatement(query);
            ResultSet resultado = statementEmpleado.executeQuery();
            while (resultado.next()){
                String cajero= resultado.getString(1);
                
        
                Lista = Lista + cajero +",";
                
                
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return Lista;
    }
    
     
}
