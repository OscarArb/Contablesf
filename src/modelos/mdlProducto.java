/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

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
 * @author Usuario
 */
public class mdlProducto {
    private LinkedList<clsProducto> empleados = new LinkedList<clsProducto>();
    dbConnection dbConnection;
    
        //Constructor
    public mdlProducto() {
        dbConnection = new dbConnection();
    }
    //Metodos CRUD Crear, Leer, Editar, Consult<r , eliminar
    public boolean Crear(clsProducto producto) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
            //Generar insercion en tabla persona
            String query = "INSERT INTO `productos`(`producto`, `plu`, `precio`, `cantidad`,`lote`,`fechaRegistrado`,`fechaVencimiento`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement  statementProducto = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementProducto.setString(1,producto.getDetalle()); 
            statementProducto.setString(2,producto.getPlu());
            statementProducto.setInt(3, producto.getValorproducto());
            statementProducto.setString(4,producto.getCantidad());
            statementProducto.setString(5,producto.getLote());
            statementProducto.setString(6,producto.getFechaRegistro());
            statementProducto.setString(7,producto.getFechaVencimiento());
            
            int filasModificadas = statementProducto.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Se creo un registro en tabla producto");
                
                   
            }
            
        return true;  
        } catch (Exception e) {
            System.out.println("La excepcion es: " + e);
            
            return false;
        }
    }
    public clsProducto  Consultar(String plu){
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT * FROM `productos`  WHERE plu =? OR id = ?";
            PreparedStatement statementPersona = connection.prepareStatement(query);
            
            
            statementPersona.setString(1, plu);
            statementPersona.setString(2, plu);
           
            ResultSet resultado = statementPersona.executeQuery();
            while (resultado.next()){
                int id = resultado.getInt(1);
                String detalle = resultado.getString(2);
                String codigo = resultado.getString(3);
                int valorproducto = resultado.getInt(4);
                String cantidad = resultado.getString(5);
                String lote = resultado.getString(6);
                String fechaRegistro = resultado.getString(7);
                String fechaVencimiento = resultado.getString(8);
                
              
      
                clsProducto empleado = new clsProducto(id,detalle,  codigo, valorproducto, cantidad,lote, fechaRegistro, fechaVencimiento);
                System.out.println("Se encontro el producto"+valorproducto+"  "+detalle+"  "+fechaRegistro);
                return empleado;
            }
            
            System.out.println("No se encontro el empleado");
           return null;
        }catch(Exception e){
            System.out.println("La excepcion es:!"+ e);
            return null;
        }
    }
    public clsProducto  Eliminar(String id){
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="DELETE FROM `productos` WHERE id =? ";
            //int numero = Integer.parseInt(nit);
            PreparedStatement statementPersona = connection.prepareStatement(query);
          
            statementPersona.setString(1, id);
           
           int resultado =  statementPersona.executeUpdate();
           int idd = 0;
           String detalle = "Eliminado";
           String codigo = "0";
           int valorproducto = 0;
           String cantidad = "Eliminado";
           String lote = "Eliminado";
           String fechaVencimiento = "Eliminado";
           String fechaRegistro = "Eliminado";
           clsProducto producto = new clsProducto(idd,detalle,  codigo, valorproducto, cantidad,lote, fechaRegistro, fechaVencimiento);
            if (resultado>0){
          
                return producto;
           }else{
           return null;
           }
        }catch(Exception e){
            System.out.println("La excepcion es:!"+ e);
            return null;
        }
    }
    public boolean Actualizar(clsProducto producto) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
           
            String query ="UPDATE `productos` SET `producto`=? ,`plu`=? ,`precio`=? ,`cantidad`=? ,`lote`= ? ,`fechaRegistrado`=? ,`fechaVencimiento`=? WHERE id =?";
            PreparedStatement  statementPersona = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementPersona.setString(1,producto.getDetalle()); 
            statementPersona.setString(2,producto.getPlu()); 
            statementPersona.setInt(3,producto.getValorproducto()); 
            statementPersona.setString(4,producto.getCantidad()); 
            statementPersona.setString(5,producto.getLote()); 
            statementPersona.setString(6,producto.getFechaRegistro()); 
            statementPersona.setString(7,producto.getFechaVencimiento());
          
            statementPersona.setInt(8,producto.getId());
            int filasModificadas = statementPersona.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Se Actualizo en tabla Producto");      
            }
            
        return true;  
        } catch (Exception e) {
            System.out.println("La excepcion es: " + e);
            return false;
        }
    }
}
