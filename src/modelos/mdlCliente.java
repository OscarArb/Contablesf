/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.clsCliente;
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
public class mdlCliente {

    private LinkedList<clsEmpleado> empleados = new LinkedList<clsEmpleado>();
    dbConnection dbConnection;
    
        //Constructor
    public mdlCliente() {
        dbConnection = new dbConnection();
    }

    //Metodos CRUD Crear, Leer, Editar, Consult<r , eliminar
    public boolean Crear(clsCliente cliente) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
            //Generar insercion en tabla empleados
            String query = "INSERT INTO `clientes`( `nombres`, `cedula`, `telefono`, `celular`, `direccion`, `facturas`, `correo`, `fechaRegistro`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement  statementEmpleado = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementEmpleado.setString(1,cliente.getNombre()); 
            statementEmpleado.setString(2,cliente.getCedula());
            statementEmpleado.setString(3,cliente.getTelefono());
            statementEmpleado.setString(4,cliente.getCelular());
            statementEmpleado.setString(5,cliente.getDireccion());
            statementEmpleado.setInt(6,cliente.getFacturas());
            
            statementEmpleado.setString(7,cliente.getCorreo());
            statementEmpleado.setString(8,cliente.getFechaRegistro());
            
            
            int filasModificadas = statementEmpleado.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Objeto agregado a tabla clientes");
                
                   
            }
            
        return true;  
        } catch (Exception e) {
            String o = e.toString();
           
            String errorid= "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry "+"'"+cliente.getCedula()+"'"+" for key 'id_cliente'";
            System.out.println(o+"|"+errorid);
            if (o.equals(errorid)){
                JOptionPane.showMessageDialog(null, "Cedula ya se encuentra registrada");
            }
            System.out.println("False; La excepcion es: " + e);
            return false;
        }
    }
   
    //
    public clsCliente  Consultar(String cedula){
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT * FROM `clientes`  WHERE cedula =? OR id = ?";
            PreparedStatement statementPersona = connection.prepareStatement(query);
            statementPersona.setString(1, cedula);
            statementPersona.setString(2, cedula);
            ResultSet resultado = statementPersona.executeQuery();
            while (resultado.next()){
                int id = resultado.getInt(1);
                String nombre = resultado.getString(2);
                String cedul = resultado.getString(3);
                String telefono = resultado.getString(4);
                String celular = resultado.getString(5);
                String direccion = resultado.getString(6);
                int facturas = resultado.getInt(7);
                String correo = resultado.getString(8);
                String fechaRegistro = resultado.getString(9);
                clsCliente cliente = new clsCliente(id, nombre,  cedul,telefono, celular, direccion,facturas,correo,fechaRegistro);
              //  System.out.println("Se encontro el producto"+valorproducto+"  "+detalle+"  "+fechaRegistro);
                return cliente;
            }
            
            System.out.println("No se encontro el empleado");
           return null;
        }catch(Exception e){
            System.out.println("La excepcion es:!"+ e);
            return null;
        }
    }
    public clsCliente  Eliminar(String id){
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="DELETE FROM `clientes` WHERE id =? ";
           
            PreparedStatement statementPersona = connection.prepareStatement(query);
          
            statementPersona.setString(1, id);
           
            int resultado =  statementPersona.executeUpdate();
            clsCliente cliente = new clsCliente(0,null,null,null,null,null,0,null,null);
            if (resultado>0){
          
                return cliente;
           }else{
           return null;
           }
        }catch(Exception e){
            System.out.println("La excepcion es:!"+ e);
            return null;
        }
    }
    public boolean Actualizar(clsCliente cliente) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
           
            String query ="UPDATE `clientes` SET `nombres`=? ,`cedula`= ? ,`telefono`=? ,`celular`=? ,`direccion`= ? ,`correo`= ? WHERE id =?";
            PreparedStatement  statementPersona = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementPersona.setString(1,cliente.getNombre()); 
            statementPersona.setString(2,cliente.getCedula()); 
            statementPersona.setString(3,cliente.getTelefono()); 
            statementPersona.setString(4,cliente.getCelular()); 
            statementPersona.setString(5,cliente.getDireccion());
            statementPersona.setString(6,cliente.getCorreo()); 
          
            statementPersona.setInt(7,cliente.getId());
            int filasModificadas = statementPersona.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Se Actualizo en tabla Clientes");      
            }
            
        return true;  
        } catch (Exception e) {
            System.out.println("La excepcion es: " + e);
            return false;
        }
    }
     
}
