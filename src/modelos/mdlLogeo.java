/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.clsEmpleado;
import clases.clsLogeo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class mdlLogeo {
    private LinkedList<clsLogeo> usuarios = new LinkedList<clsLogeo>();
    dblogeo dblogeo;
    
       //Constructor
    public mdlLogeo() {
        dblogeo = new dblogeo();
    }
    
    String usuari ="";
    
   public clsLogeo  Logeo(String usuario){
        try(Connection connection = DriverManager.getConnection(dblogeo.getUrl(),dblogeo.getUser(),dblogeo.getPass())){
            String query ="SELECT * FROM `empresas`  WHERE usuario =?";
            PreparedStatement statementPersona = connection.prepareStatement(query);
            
            
            statementPersona.setString(1, usuario);
           
            ResultSet resultado = statementPersona.executeQuery();
            while (resultado.next()){
                
                String usuari = resultado.getString(3);
                
                String clave = resultado.getString(4);
                System.out.println(usuari+clave);
                
                
                
      
                clsLogeo usuarios = new clsLogeo(usuari,clave);
                System.out.println("Se encontro el producto");
                return usuarios;
            }
            
            System.out.println("No se encontro el empleado"+usuari);
           return null;
        }catch(Exception e){
            System.out.println("La excepcion es:!"+ e);
            return null;
        }
    }
}
