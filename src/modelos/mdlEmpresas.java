/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.clsEmpresas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import static vistas.registro.empresa;

/**
 *
 * @author Usuario
 */
public class mdlEmpresas {
     private LinkedList<clsEmpresas> empresas = new LinkedList<clsEmpresas>();
    dblogeo dblogeo;
    
      //Constructor
    public mdlEmpresas() {
        dblogeo = new dblogeo();
    }
    
    //Metodo crud CREAR
    public boolean Crear(clsEmpresas empresas) {
        try (Connection connection = DriverManager.getConnection(dblogeo.getUrl(),dblogeo.getUser(),dblogeo.getPass())){
            System.out.println("Conexion exitosa ");
            //Generar insercion en tabla persona
            String query = "INSERT INTO `empresas`(`empresa`, `usuario`, `clave`, `nit`, `direccion`, `telefono`, `correo`, `fechaVinculacion`, `fechaVencimiento`) VALUES (?,?,?,?,?,?,?,?,?)";
            //String query2 = "CREATE DATABASE "+empresas.getEmpresa();
            
            PreparedStatement  statementPersona = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
           // PreparedStatement  statementBasedatos = connection.prepareStatement(query2,Statement.RETURN_GENERATED_KEYS);
            //statementBasedatos.setString(1,empresas.getEmpresa()); 
            Date todayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat year = new SimpleDateFormat("yyyy");
            SimpleDateFormat mes = new SimpleDateFormat("MM/dd");
            String fechaActual = sdf.format(todayDate);
            String anno =year.format(todayDate);
            String mes_dia = mes.format(todayDate);
            //secuencia que usare pra crear tablas en la base de datos
            /*CREATE TABLE usuarios (
                id int AUTO_INCREMENT PRIMARY KEY,
                nombre Varchar(30),
                apellido Varchar(30),
                 identificacion VARCHAR(20) UNIQUE,
                  telefono VARCHAR(150),
                  email VARCHAR(100)
     
                  );*/
            
            int anovencimiento = Integer.parseInt(anno)+1;
            
            String fechaVencimiento = String.valueOf(anovencimiento) +"/"+ mes_dia;
            
            System.out.println("Fecha atual: "+fechaActual);
            System.out.println("Fecha de vencimiento: "+fechaVencimiento);
            statementPersona.setString(1,empresas.getEmpresa()); 
            statementPersona.setString(2,empresas.getUsuario());
            statementPersona.setString(3,empresas.getClave());
            statementPersona.setString(4,empresas.getNit());
            statementPersona.setString(5,empresas.getDireccion());
            statementPersona.setString(6,empresas.getTelefono());
            statementPersona.setString(7,empresas.getEmail());
            statementPersona.setString(8, fechaActual);
            statementPersona.setString(9, fechaVencimiento);
            
            //int filasModificada = statementBasedatos.executeUpdate();
            int filasModificadas = statementPersona.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Se creo un registro en tabla empresas");
                
                
                   
            }
            
        return true;  
        } catch (Exception e) {
            System.out.println("La excepcion es: " + e);
            return false;
        }
    }
    
}
