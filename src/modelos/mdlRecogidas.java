/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.clsBases;
import clases.clsCliente;
import clases.clsEmpleado;
import clases.clsProducto;
import clases.clsRecogidas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import vistas.cacha;

/**
 *
 * @author OscarArb
 */
public class mdlRecogidas {

    private LinkedList<clsEmpleado> empleados = new LinkedList<clsEmpleado>();
    dbConnection dbConnection;
    cacha cacha = new cacha();
    
        //Constructor
    public mdlRecogidas() {
        dbConnection = new dbConnection();
    }

    //Metodos CRUD Crear, Leer, Editar, Consult<r , eliminar
    public boolean Crear(clsRecogidas recogida) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
            //Generar insercion en tabla empleados
            String query = "INSERT INTO `recogidascaja`( `usuarioCajero`, `valor`, `numeroRecogida`, `hora`, `fecha`) VALUES (?,?,?,?,?)";
            PreparedStatement  statementEmpleado = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementEmpleado.setInt(1,recogida.getUsuario()); 
            statementEmpleado.setInt(2,recogida.getValor()); 
            statementEmpleado.setString(3,recogida.getNumeroRecogida());
            statementEmpleado.setString(4,recogida.getHora());
            statementEmpleado.setString(5,recogida.getFecha());
            
            
            int filasModificadas = statementEmpleado.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Objeto agregado a tabla recogidas");
                
                   
            }
            
        return true;  
        } catch (Exception e) {
            
            System.out.println("False; La excepcion es: " + e);
            return false;
        }
    }
   
    
    public String NumeroRecogida(){
        String recogida= "";
        String validacion = '"'+cacha.usuario()+'"';
        
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");       
        String fecha = '"'+sdf.format(todayDate)+'"';
        System.out.println("Valor de validacion::::: "+validacion+"Valor de fecha::: "+ fecha);
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT MAX(numeroRecogida) FROM `recogidascaja` WHERE usuarioCajero = "+validacion+ " AND fecha = " + fecha;
            System.out.println("Este es el query a ejecutar ( "+query+" )");
            PreparedStatement statementProducto = connection.prepareStatement(query);
            ResultSet resultado = statementProducto.executeQuery();
            
            while (resultado.next()){
                int cantidad = resultado.getInt(1);
                
                
                recogida = String.valueOf(cantidad);
                
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return recogida;
    }
     
    public int ValorRecogidas(){
        int VTRecogidas=0;
        String validacion = '"'+cacha.usuario()+'"';
        
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");       
        String fecha = '"'+sdf.format(todayDate)+'"';
        System.out.println("Valor de validacion::::: "+validacion+"Valor de fecha::: "+ fecha);
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT  SUM(`valor`) FROM `recogidascaja` WHERE  usuarioCajero= "+validacion+ " AND fecha = " + fecha;
            System.out.println("Este es el query a ejecutar ( "+query+" )");
            PreparedStatement statementProducto = connection.prepareStatement(query);
            ResultSet resultado = statementProducto.executeQuery();
            
            while (resultado.next()){
                int cantidad =+ resultado.getInt(1);
                VTRecogidas=VTRecogidas=cantidad;
                
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return VTRecogidas;
    }
}
