/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.clsEmpleado;
import clases.clsProducto;
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
public class mdlEmpleado {

    private LinkedList<clsEmpleado> empleados = new LinkedList<clsEmpleado>();
    dbConnection dbConnection;
    
        //Constructor
    public mdlEmpleado() {
        dbConnection = new dbConnection();
    }

    //Metodos CRUD Crear, Leer, Editar, Consult<r , eliminar
    public boolean Crear(clsEmpleado empleado) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
            //Generar insercion en tabla empleados
            String query = "INSERT INTO `empleados`( `nombres`, `cedula`, `telefono`, `cargo`, `salario`, `tipoContrato`, `correo`, `banco`, `numeroCuenta`, `fechaVinculacion`, `finContrato`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement  statementEmpleado = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementEmpleado.setString(1,empleado.getNombre()); 
            statementEmpleado.setString(2,empleado.getCedula());
            statementEmpleado.setString(3,empleado.getTelefono());
            statementEmpleado.setString(4,empleado.getCargo());
            statementEmpleado.setInt(5,empleado.getValorquincena());
            statementEmpleado.setString(6,empleado.getTipoContrato());
            statementEmpleado.setString(7,empleado.getCorreo());
            statementEmpleado.setString(8,empleado.getBanco());
            statementEmpleado.setString(9,empleado.getCuentaCobro());
            statementEmpleado.setString(10,empleado.getFechaVinculacion());
            statementEmpleado.setString(11,empleado.getFechaFinContrato());
            
            
            int filasModificadas = statementEmpleado.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Objeto agregado a tabla empleado");
                
                   
            }
            
        return true;  
        } catch (Exception e) {
            String o = e.toString();
           
            String errorid= "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry "+"'"+empleado.getCedula()+"'"+" for key 'cedula'";
            System.out.println(o+"|"+errorid);
            if (o.equals(errorid)){
                JOptionPane.showMessageDialog(null, "Cedula ya se encuentra registrada");
            }
            System.out.println("False; La excepcion es: " + e);
            return false;
        }
    }
   
    //
    public clsEmpleado  Consultar(String cedula){
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT * FROM `empleados`  WHERE cedula =? OR id = ?";
            PreparedStatement statementPersona = connection.prepareStatement(query);
            
            
            statementPersona.setString(1, cedula);
            statementPersona.setString(2, cedula);
           
            ResultSet resultado = statementPersona.executeQuery();
            while (resultado.next()){
                int id = resultado.getInt(1);
                String nombre = resultado.getString(2);
                String cedul = resultado.getString(3);
                String telefono = resultado.getString(4);
                String cargo = resultado.getString(5);
                int valorquincena = resultado.getInt(6);
                String tipoContrato = resultado.getString(7);
                String correo = resultado.getString(8);
                String banco = resultado.getString(9);
                String cuentaCobro = resultado.getString(10);
                String fechaVinculacion= resultado.getString(11);
                String fechaFinContrato = resultado.getString(12);
                
              
      
                clsEmpleado empleado = new clsEmpleado( id, nombre,cedul,telefono,cargo, valorquincena,tipoContrato,correo, banco, cuentaCobro, fechaVinculacion, fechaFinContrato);
              //  System.out.println("Se encontro el producto"+valorproducto+"  "+detalle+"  "+fechaRegistro);
                return empleado;
            }
            
            System.out.println("No se encontro el empleado");
           return null;
        }catch(Exception e){
            System.out.println("La excepcion es:!"+ e);
            return null;
        }
    }
    public clsEmpleado  Eliminar(String id){
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="DELETE FROM `empleados` WHERE id =? ";
            //int numero = Integer.parseInt(nit);
            PreparedStatement statementPersona = connection.prepareStatement(query);
          
            statementPersona.setString(1, id);
           
            int resultado =  statementPersona.executeUpdate();
            int idd = 0;
            int salario = 0;
              clsEmpleado empleado = new clsEmpleado(idd, "eliminado","eliminado","eliminado","eliminado", salario,"eliminado","eliminado", "elimianado", "eliminado", "eliminado", "eliminado");
              if (resultado>0){
          
                return empleado;
           }else{
           return null;
           }
        }catch(Exception e){
            System.out.println("La excepcion es:!"+ e);
            return null;
        }
    }
    public boolean Actualizar(clsEmpleado empleado) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
           
            String query ="UPDATE `empleados` SET `nombres`=?,`cedula`=?,`telefono`=?,`cargo`=?,`salario`=?,`tipoContrato`=?,`correo`=?,`banco`=?,`numeroCuenta`=?,`finContrato`=? WHERE id =?";
            PreparedStatement  statementPersona = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementPersona.setString(1,empleado.getNombre()); 
            statementPersona.setString(2,empleado.getCedula()); 
            statementPersona.setString(3,empleado.getTelefono()); 
            statementPersona.setString(4,empleado.getCargo()); 
            statementPersona.setInt(5,empleado.getValorquincena()); 
            statementPersona.setString(6,empleado.getTipoContrato()); 
            statementPersona.setString(7,empleado.getCorreo());
            statementPersona.setString(8,empleado.getBanco()); 
            statementPersona.setString(9,empleado.getCuentaCobro());
            statementPersona.setString(10, empleado.getFechaFinContrato());
          
            statementPersona.setInt(11,empleado.getId());
            int filasModificadas = statementPersona.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Se Actualizo en tabla Proveedor");      
            }
            
        return true;  
        } catch (Exception e) {
            System.out.println("La excepcion es: " + e);
            return false;
        }
    }
     
}
