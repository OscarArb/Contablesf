/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import clases.clsBases;
import clases.clsBasesAuxiliar;
import clases.clsCliente;
import clases.clsEmpleado;
import clases.clsProducto;
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
public class mdlBases {

    private LinkedList<clsEmpleado> empleados = new LinkedList<clsEmpleado>();
    dbConnection dbConnection;
    cacha cacha = new cacha();
    
        //Constructor
    public mdlBases() {
        dbConnection = new dbConnection();
    }

    //Metodos CRUD Crear, Leer, Editar, Consult<r , eliminar
    public boolean Crear(clsBases base) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
            //Generar insercion en tabla empleados
            String query = "INSERT INTO `basecajero`( `usuario`, `baseinical`,`horaApertura`,`fecha`) VALUES (?,?,?,?)";
            PreparedStatement  statementEmpleado = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementEmpleado.setInt(1,base.getUsuario()); 
            statementEmpleado.setInt(2,base.getBaseInicial()); 
            statementEmpleado.setString(3,base.getHoraApertura());
            statementEmpleado.setString(4,base.getFecha());
            
            
            int filasModificadas = statementEmpleado.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Objeto agregado a tabla baseCajero");
                
                   
            }
            
        return true;  
        } catch (Exception e) {
            
            System.out.println("False; La excepcion es: " + e);
            return false;
        }
    }
    public boolean GenerarCierre(clsBases cierre) {
        try (Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            System.out.println("Conexion exitosa ");
            //Generar insercion en tabla empleados
            String query = "INSERT INTO `basecajero`( `usuario`, `basecierre`,`horaCierre`,`fecha`) VALUES (?,?,?,?)";
            PreparedStatement  statementEmpleado = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statementEmpleado.setInt(1,cierre.getUsuario()); 
            statementEmpleado.setInt(2,cierre.getBaseCierre()); 
            statementEmpleado.setString(3,cierre.getHoraCierre());
            statementEmpleado.setString(4,cierre.getFecha());
            
            
            int filasModificadas = statementEmpleado.executeUpdate();
            if(filasModificadas > 0){
                System.out.println("Objeto agregado a tabla baseCajero");
                
                   
            }
            
        return true;  
        } catch (Exception e) {
            
            System.out.println("False; La excepcion es: " + e);
            return false;
        }
    }
   
    //
    
    public clsBasesAuxiliar  Consultar(String fecha){
        String usuario = cacha.usuario();
        String id="";
        String baseInicial = "";
        String  baseCierre = "";
        String  cantidadRecogidas = "";
        String horaCierre = "";
        String apertura = "";
        String fecha2 = "";
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT `id`, `baseinical`,  `cantidadrecojidas`, `horaApertura`,  `fecha` FROM `basecajero` WHERE usuario = '"+usuario+"'"+"AND fecha = '"+fecha+"'";
            //PreparedStatement statementPersona = connection.prepareStatement(query);
            
            //ResultSet resultado = statementPersona.executeQuery();
            
            PreparedStatement statementEmpleado = connection.prepareStatement(query);
            ResultSet resultado = statementEmpleado.executeQuery();
            while (resultado.next()){
                String idAuxiliar = resultado.getString(1);
                String baseAuxiliar=resultado.getString(2);
                String cantidadAuxiliar=resultado.getString(3);
                String horaAuxiliar=resultado.getString(4);
                String fechaAuxiliar=resultado.getString(5);
                
                id = id + idAuxiliar +",";
                baseInicial = baseInicial + baseAuxiliar +",";
                cantidadRecogidas = cantidadRecogidas + cantidadAuxiliar +",";
                apertura = apertura + horaAuxiliar +",";
                fecha2 = fecha2 + fechaAuxiliar +",";
                
                
               
               
                
            }
            
             clsBasesAuxiliar datos = new clsBasesAuxiliar(id,baseInicial, baseCierre,cantidadRecogidas,apertura, horaCierre,fecha2);
              //  System.out.println("Se encontro el producto"+valorproducto+"  "+detalle+"  "+fechaRegistro);
                return datos;
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
    
    public String totalBaseHoy(){
        String valor= "";
        int cantidades = 0;
        String validacion = '"'+cacha.usuario()+'"';
        
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");       
        String fecha = '"'+sdf.format(todayDate)+'"';
        System.out.println("Valor de validacion::::: "+validacion+"Valor de fecha::: "+ fecha);
        try(Connection connection = DriverManager.getConnection(dbConnection.getUrl(),dbConnection.getUser(),dbConnection.getPass())){
            String query ="SELECT `baseinical` FROM `basecajero` WHERE usuario= "+validacion+ " AND fecha = " + fecha;
            System.out.println("Este es el query a ejecutar ( "+query+" )");
            PreparedStatement statementProducto = connection.prepareStatement(query);
            ResultSet resultado = statementProducto.executeQuery();
            
            while (resultado.next()){
                int cantidad = resultado.getInt(1);
                
                cantidades = cantidades+cantidad;
                valor = String.valueOf(cantidades);
                
            }
   
        }catch (Exception e){
            System.out.println("La excepcion es: "+ e);
        }
        return valor;
    }
    
    //debo pasar este codigo para mdl recogidas
    
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
     
}
