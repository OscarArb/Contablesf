/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


import clases.clsEmpleado;
import clases.clsFactura;
import clases.clsProducto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import vistas.cacha;

/**
 *
 * @author OscarArb
 */
public class mdlInicio {
    dbConnection dbConnection;
    cacha cacha = new cacha();
       //Constructor
    public mdlInicio() {
        dbConnection = new dbConnection();
        
    }
    
    public String ListarProducto(){
        System.out.println("Listado productos");
        String Lista ="";
        
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT `id`, `producto` FROM `productos`";
            PreparedStatement statementEmpleado = connection.prepareStatement(query);
            ResultSet resultado = statementEmpleado.executeQuery();
            while (resultado.next()){
                String nombre = resultado.getString(2);
                
        
                Lista = Lista + nombre +",";
                
                
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return Lista;
    }
    //Inicia Metodo lista el inventario
    public String ListarInventario(){
        
        String cantidades = "";
        
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT cantidad FROM `productos`";
            PreparedStatement statementProducto = connection.prepareStatement(query);
            ResultSet resultado = statementProducto.executeQuery();
            
            while (resultado.next()){
                String cantidad = resultado.getString(1);
                cantidades = cantidades+cantidad+",";
           
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return cantidades;
    }
    public String ListarId(){
        
        String id = "";
        
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT id FROM `productos`";
            PreparedStatement statementProducto = connection.prepareStatement(query);
            ResultSet resultado = statementProducto.executeQuery();
            
            while (resultado.next()){
                String identificador = resultado.getString(1);
                id = id+identificador+",";
                 System.out.println("ID:"+id);
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return id;
    }
    public String ListarPlu(){
        
        String plu = "";
        
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT plu FROM `productos`";
            PreparedStatement statementProducto = connection.prepareStatement(query);
            ResultSet resultado = statementProducto.executeQuery();
            
            while (resultado.next()){
                String plU = resultado.getString(1);
                plu = plu+plU+",";
           
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return plu;
    }
    public String ListarPrecio(){
        
        String precio = "";
        
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT precio FROM `productos`";
            PreparedStatement statementProducto = connection.prepareStatement(query);
            ResultSet resultado = statementProducto.executeQuery();
            
            while (resultado.next()){
                String precios= resultado.getString(1);
                precio = precio+precios+",";
               
           
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return precio;
    }
    
    //FIN METODO
    public int UltimaFactura(){
        System.out.println("Ultima Factura Registrada");
        int numFact = 0;
        
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT MAX(id) FROM `facturas`";
            PreparedStatement statementEmpleado = connection.prepareStatement(query);
            ResultSet resultado = statementEmpleado.executeQuery();
            while (resultado.next()){
                String nombre = resultado.getString(1);
                
        
                numFact = Integer.parseInt(nombre);
                
                
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return numFact;
    }
    public String ListarEmpleado(){
        System.out.println("Listado Empleado");
        String Lista ="";
        
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT `cedula` FROM `empleados`";
            PreparedStatement statementEmpleado = connection.prepareStatement(query);
            ResultSet resultado = statementEmpleado.executeQuery();
            while (resultado.next()){
                String nombre = resultado.getString(1);
                
        
                Lista = Lista + nombre +",";
                
                
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return Lista;
    }
    

     public boolean GuardarFactura(clsFactura factura) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
            //Generar insercion en tabla persona
            String query = "INSERT INTO `facturas`(`fecha`, `numerofactura`, `idcliente`, `total`, `nombrecliente`, `cedulaCajero`) VALUES(?,?,?,?,?,?)";
            PreparedStatement  statementPersona = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
           statementPersona.setString(1,factura.getFecha()); 
           statementPersona.setString(2,factura.getNumeroFactura());
           statementPersona.setString(3,factura.getIdCliente());
           statementPersona.setInt(4,factura.getTotal());
           statementPersona.setString(5,factura.getNombreCliente());
           statementPersona.setInt(6,factura.getCajero());
            int filasModificadas = statementPersona.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Se creo un registro en tabla facturas");
                
                   
            }
            
        return true;  
        } catch (Exception e) {
            System.out.println("La excepcion es: " + e);
            return false;
        }
    }
     
      public int TotalVenta(){
        int Ventas=0;
        String validacion = '"'+cacha.usuario()+'"';
        
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");       
        String fecha = '"'+sdf.format(todayDate)+'"';
        System.out.println("Valor de validacion::::: "+validacion+"Valor de fecha::: "+ fecha);
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT SUM(`total`)FROM `facturas`  WHERE  cedulaCajero= "+validacion+ " AND fecha = " + fecha;
            System.out.println("Este es el query a ejecutar ( "+query+" )");
            PreparedStatement statementProducto = connection.prepareStatement(query);
            ResultSet resultado = statementProducto.executeQuery();
            
            while (resultado.next()){
                Ventas = resultado.getInt(1);
                
                
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return Ventas;
    }
}
