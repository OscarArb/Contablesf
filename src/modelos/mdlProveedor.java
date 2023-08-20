/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import clases.clsProveedor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class mdlProveedor {
    
    private LinkedList<clsProveedor> proveedores = new LinkedList<clsProveedor>();
    dbConnection dbConnection;
    
       //Constructor
    public mdlProveedor() {
        dbConnection = new dbConnection();
    }
     public boolean Crear(clsProveedor proveedor) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
            //Generar insercion en tabla persona
            String query = "INSERT INTO `proveedor`(`nombre`, `nit`, `telefono`, `direccion`, `correo`, `gerente`, `telgerente`, `correogerente`, `fecharegistrado`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement  statementPersona = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementPersona.setString(1,proveedor.getNombre()); 
            statementPersona.setString(2,proveedor.getNit());
            statementPersona.setString(3,proveedor.getTelefono());
            statementPersona.setString(4,proveedor.getDireccion());
            statementPersona.setString(5,proveedor.getCorreo());
            statementPersona.setString(6,proveedor.getGerente());
            statementPersona.setString(7,proveedor.getTelGerente());
            statementPersona.setString(8,proveedor.getCorreoGerente());
            statementPersona.setString(9,proveedor.getFechaRegistrado());
            
            
            
            int filasModificadas = statementPersona.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Objeto agregado a tabla proveedor");
                
                   
            }
            
        return true;  
        } catch (Exception e) {
            String o = e.toString();
           
            String errorid= "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry "+"'"+proveedor.getNit()+"'"+" for key 'nit'";
            String errornombre= "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry "+"'"+proveedor.getNombre()+"'"+" for key 'nombre_2'";
            System.out.println(o+"|"+errorid);
            System.out.println("------"+errornombre);
            if (o.equals(errorid)){
                JOptionPane.showMessageDialog(null, "Nit ya se encuentra registrado");
            }else if(o.equals(errornombre)){
                JOptionPane.showMessageDialog(null, "Nombre de proveedor ya se encuentra registrado");
            }
            System.out.println("False; La excepcion es: " + e);
            return false;
        }
    }
    public clsProveedor  Consultar(String nit){
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT * FROM `proveedor`  WHERE nombre  = ? ";
            PreparedStatement statementPersona = connection.prepareStatement(query);
            statementPersona.setString(1,nit);
            ResultSet resultado = statementPersona.executeQuery();
            while (resultado.next()){
                int id = resultado.getInt(1);
                String nombre = resultado.getString(2);
                String nitt = resultado.getString(3);
                String telefono = resultado.getString(4);
                String direccion = resultado.getString(5);
                String correo = resultado.getString(6);
                String gerente = resultado.getString(7);
                String telGerente = resultado.getString(8);
                String correoGerente = resultado.getString(9);
                String fechaRegistro = resultado.getString(10);
                
                
                System.out.println("este es detalle consultado**"+nombre);
 
                //nombre, cedula, telefono, cargo, valorquincena
                clsProveedor proveedor = new clsProveedor(id, nombre,  nitt, telefono,direccion, correo, gerente,telGerente,correoGerente, fechaRegistro);
                System.out.println("Se encontro el ingreso");
                return proveedor;
            }
            
            System.out.println("No se encontro el empleado");
           return null;
        }catch(Exception e){
            System.out.println("La excepcion es:"+ e);
            return null;
        }
    }
    public String Listar(){
        String Lista ="";
        
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT `nombre`, `nit` FROM `proveedor`";
            PreparedStatement statementEmpleado = connection.prepareStatement(query);
            ResultSet resultado = statementEmpleado.executeQuery();
            while (resultado.next()){
                
                String nombre = resultado.getString(1);
                Lista = Lista +nombre+",";
               
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return Lista;
    }
   public clsProveedor  Eliminar(String nit){
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="DELETE FROM `proveedor` WHERE id =? ";
            //int numero = Integer.parseInt(nit);
            PreparedStatement statementPersona = connection.prepareStatement(query);
          
            statementPersona.setString(1, nit);
           
           int resultado =  statementPersona.executeUpdate();
           int id = 0;
           String nombre = "Eliminado";
           String nitt = "0";
           String telefono = "Eliminado";
           String direccion = "Eliminado";
           String correo = "Eliminado";
           String gerente = "Eliminado";
           String telGerente = "Eliminado";
           String correoGerente = "Eliminado";
           String fechaRegistro = "Eliminado";
           
           clsProveedor proveedor = new clsProveedor(id,nombre,  nitt, telefono,direccion, correo, gerente,telGerente,correoGerente, fechaRegistro);
           if (resultado>0){
          
                return proveedor;
           }else{
           return null;
           }
        }catch(Exception e){
            System.out.println("La excepcion es:!"+ e);
            return null;
        }
    }
    public boolean Actualizar(clsProveedor proveedor) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
           
            String query ="UPDATE `proveedor` SET `nombre`=?,`nit`=?,`telefono`=?,`direccion`=?,`correo`=?,`gerente`=?,`telgerente`=?,`correogerente`=?,`fecharegistrado`=? WHERE id =?";
            PreparedStatement  statementPersona = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementPersona.setString(1,proveedor.getNombre()); 
            statementPersona.setString(2,proveedor.getNit()); 
            statementPersona.setString(3,proveedor.getTelefono()); 
            statementPersona.setString(4,proveedor.getDireccion()); 
            statementPersona.setString(5,proveedor.getCorreo()); 
            statementPersona.setString(6,proveedor.getGerente()); 
            statementPersona.setString(7,proveedor.getTelGerente());
            statementPersona.setString(8,proveedor.getCorreoGerente()); 
            statementPersona.setString(9,proveedor.getFechaRegistrado()); 
          
            statementPersona.setInt(10,proveedor.getId());
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
