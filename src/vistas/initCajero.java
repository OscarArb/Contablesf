/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.clsBases;
import clases.clsCliente;
import clases.clsEmpleado;
import clases.clsFactura;
import clases.clsProducto;
import clases.clsProveedor;
import clases.clsRecogidas;
import clases.clsUsuario;
import clases.place;
import com.itextpdf.text.BaseColor;
import controladores.ctrEmpleado;
import controladores.ctrInicio;
import controladores.ctrProducto;
import static java.awt.Color.blue;
import static java.awt.Color.white;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelos.mdlProducto;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPCell;
import controladores.ctrBases;
import controladores.ctrCliente;
import controladores.ctrProveedor;
import controladores.ctrRecogidas;
import controladores.ctrUsuario;

import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import static org.omg.CORBA.ORB.init;

/**
 *
 * @author Usuario
 */
public class initCajero extends javax.swing.JFrame {

    fondoPanel fondo = new fondoPanel();
     //Creamos un DefaulTablemode para dar un modelo a nuestra tabla
    DefaultTableModel dtm = new DefaultTableModel();
    DefaultTableModel dtm2 =new DefaultTableModel();
    DefaultTableModel tabCaja =new DefaultTableModel();
    DefaultTableModel tabCierre =new DefaultTableModel();
    DefaultTableModel tabRecogida =new DefaultTableModel();
    ctrInicio ctrInicio = new ctrInicio();
    cacha cacha = new cacha();
    ctrEmpleado ctrEmpleado = new ctrEmpleado();
    ctrProducto ctrProducto = new ctrProducto();
    mdlProducto mdlProducto = new mdlProducto();
    ctrProveedor ctrProveedor = new ctrProveedor();
    ctrCliente ctrCliente = new ctrCliente();
    ctrBases ctrBases = new ctrBases();
    ctrRecogidas ctrRecogidas = new ctrRecogidas();
    ctrUsuario ctrUsuario = new ctrUsuario();
    public static String  bloq_num = "";
    public static int valor1=0;
    public static int mostar;
    public static int valor2=0;
    public static String operador = "";
    public initCajero() {
        this.setContentPane(fondo);
        
        initComponents();
         //realizo un nuevo objeto que contiene la imagen icono
        setIconImage(new ImageIcon(getClass().getResource("/multimedia/cacha.jpg")).getImage());
        this.setLocationRelativeTo(null);
        //Realizo la cabecera de la tabla
        String[] titulo = new String[]{"Cantidad","Producto","V.Unit","V.Total"};
        String[] titulotabCaja = new String[]{"Denominacion","Cantidad","V.Total"};
        String[] titulotabRecogida = new String[]{"Id","N° Recogida","V.Total","Fecha","Hora"};
        String[] titulo2 = new String[]{"ID","PRODUCTO","PRECIO","PLU","CANTIDAD"};
        tabCaja.setColumnIdentifiers(titulotabCaja);
        tabCierre.setColumnIdentifiers(titulotabCaja);
        tabRecogida.setColumnIdentifiers(titulotabRecogida);
        dtm.setColumnIdentifiers(titulo);
        dtm2.setColumnIdentifiers(titulo2);
        tabla_productos.setModel(dtm);
        tablaInventario.setModel(dtm2);
        tablaCajaApertura.setModel(tabCaja);
        tablaCierre.setModel(tabCierre);
        tablaCajaRecogida.setModel(tabRecogida);
        ocultarPanelesRegistros();
        ocultarPanelesCaja();
        placeholder();
        perzonalizarbotones();
        miNomina();
        
        
        fechaHoy.setText(fecha);
       
        
       // ingreso.setVisible(false);
       // gasto.setVisible(false);
        String validacion = cacha.usuario();
       
        jlabelTitle.setText(validacion);
    }
    //Metodo para llenar lista Proveedor
    
    Date todayDate = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");       
    String fecha = sdf.format(todayDate);
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();
   String hora =dateFormat.format(date);
    void ActualizarLista(){
        pBuscar.removeAllItems();
        String modelo = ctrProveedor.Listar();
        
        
        String[] filas = modelo.split(",");
        for (int i = 0; i < filas.length; i++){

            pBuscar.insertItemAt(filas[i], i);
        }
    }
     // Metodos para agregar y eliminar productos de la factura
    void agregarProd_tabla(){
        dtm.addRow(new Object[]{
            txt_cantidad.getText(),txt_nameProd.getText(),txt_precios.getText(),txt_total.getText()
            
                
        });
    }
    void miNomina(){
        try {
            clsEmpleado empleadoAuxiliar = null;

            String empleadoConsultado = cacha.usuario();
            empleadoAuxiliar = ctrEmpleado.Consultar(empleadoConsultado);

            if (empleadoAuxiliar != null) {
                editId1.setText(String.valueOf(empleadoAuxiliar.getId()));
                editCedula1.setText(empleadoAuxiliar.getCedula());
                editNombres1.setText(empleadoAuxiliar.getNombre());
                editTelefono1.setText(empleadoAuxiliar.getTelefono());
                if (empleadoAuxiliar.getTipoContrato().equals("1")) {
                    contrato.setText("Indefinido");
                }else if (empleadoAuxiliar.getTipoContrato().equals("2")) {
                    contrato.setText("Term. Fijo");
                }else if (empleadoAuxiliar.getTipoContrato().equals("3")) {
                    contrato.setText("Prest. Serv.");
                }
            editCorreo1.setText(empleadoAuxiliar.getCorreo());
            editCargo1.setText(empleadoAuxiliar.getCargo());
            editBanco1.setSelectedItem(empleadoAuxiliar.getBanco());
            editSalario1.setText(String.valueOf(empleadoAuxiliar.getValorquincena()));  
            editCuenta1.setText(empleadoAuxiliar.getCuentaCobro());
            editFechaVinculacion1.setText(empleadoAuxiliar.getFechaVinculacion());
            editFechaFin1.setText(empleadoAuxiliar.getFechaFinContrato());
              
            } else {
                JOptionPane.showMessageDialog(this, "He tenido errores al consultar tus datos");
            }

        } catch (Exception e) {
            
        }
    }
   
    
    void perzonalizarbotones(){
    bntMinimizar.setOpaque(false);
    bntMinimizar.setContentAreaFilled(false);
    bntMinimizar.setBorderPainted(false);
    
    btnCerrar.setOpaque(false);
    btnCerrar.setContentAreaFilled(false);
    btnCerrar.setBorderPainted(false);
    }
    void ocultarPanelesRegistros(){
        panelCliente.setVisible(false);
        panelProveedor.setVisible(false);
        panelProducto.setVisible(false);
        }
    void ocultarPanelesCaja(){
        panelApertura.setVisible(false);
        panelRecogida.setVisible(false);
        panelCierre.setVisible(false);
    }
    void placeholder(){
        place cedulaFactura = new place("Ingrese cedula o id del cliente", clienteBuscado);
        place pluArticulos  = new place("Ingrese plu o codigo del producto", pluBuscado);
        place  codigoProducto = new place("Ingrese plu o codigo del producto", pCodigo);
        place  codigoCliente = new place("Ingrese cedula o codigo del Cliente", cBuscado);
        
    }   
       
    
    
    void limpiaProveedor(){
        nId.setText("0");
        nEmpresa.setText("");
        nNit.setText("");
        nCorreo.setText("");
        nGerente.setText("");
        nTelGerente.setText("");
        nCorreoGerente.setText("");
        nDireccion.setText("");
        
        nFechaRegistro.setText(fecha);
        nTelefono.setText("");
    }
    void limpiarCliente(){
                cId.setText("0");
                cNombres.setText("");
                cCedula.setText("");
                cTelefono.setText("");
                cCelular.setText("");
                cDireccion.setText("");
                cFacturas.setText("");
                cCorreo.setText("");
                cFechaRegistro.setText(fecha);
    }
    void eliminarProd(){
        dtm.removeRow(ERROR);
    }
    
    void crearDocumento(){
         Document documento = new Document();
        try{
            String numero = numerofactura.getText();
            String ruta =System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta +"/Desktop/"+numero+".pdf"));
            String fecha=txt_fecha.getText();         
            String cliente = txt_NombreCliente.getText();
            String cedula = clienteBuscado.getText();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN,12, Font.BOLD, BaseColor.BLACK);         
            documento.open();    
            
           PdfPTable tabla = new PdfPTable(4);
           tabla.setWidthPercentage(100);
           tabla.getDefaultCell().setBorder(0);
           tabla.setHorizontalAlignment(Element.ALIGN_LEFT);
           PdfPCell pro1 =new PdfPCell(new Phrase("Cant.", negrita));
           PdfPCell pro2 =new PdfPCell(new Phrase("Detalles.",negrita));
           PdfPCell pro3 =new PdfPCell(new Phrase("V.Unitario.",negrita));
           PdfPCell pro4 =new PdfPCell(new Phrase("Total.",negrita));
           pro1.setBorder(0);
           pro2.setBorder(0);
           pro3.setBorder(0);
           pro4.setBorder(0);
           pro1.setBackgroundColor(BaseColor.GRAY);
           pro2.setBackgroundColor(BaseColor.GRAY);
           pro3.setBackgroundColor(BaseColor.GRAY);
           pro4.setBackgroundColor(BaseColor.GRAY);
           tabla.addCell(pro1);
           tabla.addCell(pro2);
           tabla.addCell(pro3);
           tabla.addCell(pro4);
            for (int i = 0; i < tabla_productos.getRowCount(); i++) {
                String cantidad =tabla_productos.getValueAt(i, 0).toString();
                String detalles =tabla_productos.getValueAt(i, 1).toString();
                String VUnitario =tabla_productos.getValueAt(i, 2).toString();
                String Vtotal =tabla_productos.getValueAt(i, 3).toString();
                tabla.addCell(cantidad);
                tabla.addCell(detalles);
                tabla.addCell(VUnitario);
                tabla.addCell(Vtotal);
                
            }
           PdfPTable Total = new PdfPTable(1);
           Total.setWidthPercentage(100);
          
           Total.getDefaultCell().setBorder(0);
           Total.setHorizontalAlignment(Element.ALIGN_RIGHT);
           PdfPCell tot =new PdfPCell(new Phrase("TOTAL", negrita));
           tot.setBorder(0);
           tot.setBackgroundColor(BaseColor.CYAN);
           Total.addCell(tot);
          String totalizada= txt_totalApagar.getText();
          Total.addCell(totalizada);
          
            Paragraph parrafo = new Paragraph("FACTURA DE VENTA\r\n \r\n");
            
            parrafo.setAlignment(1);
            documento.add(parrafo);
            documento.add(new Paragraph("Fecha: "+fecha+"                                                                                           "+"N° FACTURA: "+numero));
            documento.add(new Paragraph("Nombre Cliente: \r\n"+cliente+"       \r\n \r\n"+"N° Cedula: \r\n"+cedula+"\r\n \r\n" ));
          
          
            
           documento.add(tabla);
           documento.add(Total);
            
            Paragraph parrafo2 = new Paragraph("\r\n \r\n Factura Electronica De Uso Exclusivo Comercial.");
            parrafo2.setAlignment(1);
            documento.add(parrafo2);
            documento.close();
            JOptionPane.showMessageDialog(null, "Factura creada");
             
            
            
           
        }catch(DocumentException | HeadlessException | FileNotFoundException e){
            
        }
    }
    void limpiarTablaInv(){
        dtm2.removeRow(ERROR);
    }
    void sumarTotal(){
     int fila =0;
    int total = 0;
    for (int i = 0; i < dtm.getRowCount(); i++) {
        fila = (int) Double.parseDouble(dtm.getValueAt(i,3).toString());
        total+=fila;
        
    }
    
    txt_totalApagar.setText(String.valueOf(total));
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        chekNuevaFactura = new javax.swing.JCheckBox();
        btn_buscarcliente = new javax.swing.JButton();
        clienteBuscado = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_NombreCliente = new javax.swing.JTextField();
        telefonoCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_nameProd = new javax.swing.JTextField();
        btn_consulta = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        pluBuscado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_cantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_precios = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        btn_calcular = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        numerofactura = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        txt_fecha = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_productos = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_totalApagar = new javax.swing.JButton();
        txt_cant_pago = new javax.swing.JTextField();
        txt_devuelta = new javax.swing.JButton();
        btn_eliminarF = new javax.swing.JButton();
        btn_registrarF = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jButton17 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        celularFactura = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        direccionFactura = new javax.swing.JTextField();
        cedulaFactura = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        miNomina = new javax.swing.JLayeredPane();
        panelEmpleado2 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        editCedula1 = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        editTelefono1 = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        editNombres1 = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        editSalario1 = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        editFechaFin1 = new javax.swing.JTextField();
        editBanco1 = new javax.swing.JComboBox<>();
        jButton16 = new javax.swing.JButton();
        editCorreo1 = new javax.swing.JTextField();
        editCuenta1 = new javax.swing.JTextField();
        editCargo1 = new javax.swing.JTextField();
        editFechaVinculacion1 = new javax.swing.JTextField();
        jButton37 = new javax.swing.JButton();
        jLabel101 = new javax.swing.JLabel();
        editId1 = new javax.swing.JTextField();
        jLabel102 = new javax.swing.JLabel();
        contrato = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        btn_editarArticulo1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jButton5 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        panelApertura = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        valorInicial = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCajaApertura = new javax.swing.JTable();
        TotalBase = new javax.swing.JLabel();
        totalBaseHoy = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        fechaHoy = new javax.swing.JTextField();
        btnIniciar = new javax.swing.JButton();
        panelCierre = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        valorInicial1 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCierre = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        tCierre = new javax.swing.JTextField();
        jButton22 = new javax.swing.JButton();
        fechaHoy1 = new javax.swing.JTextField();
        btnIniciar1 = new javax.swing.JButton();
        panelRecogida = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        numeroUltimaRecogida = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaCajaRecogida = new javax.swing.JTable();
        valorNuevaRecogida = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        fechaHoy2 = new javax.swing.JTextField();
        btnIniciar2 = new javax.swing.JButton();
        recogidaAregistrar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        panelProducto = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        pLote = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        pPlu = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        pCodigo = new javax.swing.JTextField();
        buscarProducto = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        pPrecio = new javax.swing.JTextField();
        pRegistro = new javax.swing.JTextField();
        pNombre = new javax.swing.JTextField();
        pCantidad = new javax.swing.JTextField();
        pVence = new javax.swing.JTextField();
        pId = new javax.swing.JTextField();
        jLabel74 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        panelProveedor = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        nGerente = new javax.swing.JTextField();
        jButton25 = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        nNit = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        nTelefono = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        nCorreo = new javax.swing.JTextField();
        nEmpresa = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        nTelGerente = new javax.swing.JTextField();
        nFechaRegistro = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        buscarProveedor = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        nDireccion = new javax.swing.JTextField();
        nCorreoGerente = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        pBuscar = new javax.swing.JComboBox<>();
        jLabel72 = new javax.swing.JLabel();
        nId = new javax.swing.JTextField();
        e = new javax.swing.JLabel();
        panelCliente = new javax.swing.JPanel();
        panelProveedor1 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        cFacturas = new javax.swing.JTextField();
        jButton27 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        cCedula = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        cTelefono = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        cDireccion = new javax.swing.JTextField();
        cNombres = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        cFechaRegistro = new javax.swing.JTextField();
        cId = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        cBuscado = new javax.swing.JTextField();
        buscarCliente = new javax.swing.JButton();
        jLabel70 = new javax.swing.JLabel();
        cCelular = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        cCorreo = new javax.swing.JTextField();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        cProducto = new javax.swing.JCheckBox();
        cCliente = new javax.swing.JCheckBox();
        cProveedor = new javax.swing.JCheckBox();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        tipoNovedad = new javax.swing.JLabel();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        jLabel25 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        jlabelTitle = new javax.swing.JLabel();
        bntMinimizar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jTabbedPane2.setBackground(new java.awt.Color(102, 102, 255));
        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTabbedPane2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTabbedPane2PropertyChange(evt);
            }
        });

        jLayeredPane1.setBackground(new java.awt.Color(51, 51, 51));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLayeredPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLayeredPane1MouseClicked(evt);
            }
        });

        chekNuevaFactura.setBackground(new java.awt.Color(0, 153, 204));
        chekNuevaFactura.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chekNuevaFactura.setText("Nueva Factura");
        chekNuevaFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chekNuevaFacturaActionPerformed(evt);
            }
        });

        btn_buscarcliente.setBackground(new java.awt.Color(204, 255, 204));
        btn_buscarcliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_buscarcliente.setText("Buscar");
        btn_buscarcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarclienteActionPerformed(evt);
            }
        });

        clienteBuscado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clienteBuscado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        clienteBuscado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        clienteBuscado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clienteBuscadoKeyTyped(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Nombres :");

        txt_NombreCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_NombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_NombreCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        telefonoCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        telefonoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        telefonoCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel3.setText("Producto");

        txt_nameProd.setBackground(new java.awt.Color(204, 255, 204));
        txt_nameProd.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txt_nameProd.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nameProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nameProdActionPerformed(evt);
            }
        });

        btn_consulta.setBackground(new java.awt.Color(0, 153, 204));
        btn_consulta.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btn_consulta.setText("Consulta");
        btn_consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Detalles Del Producto A Facturar:");

        pluBuscado.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        pluBuscado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pluBuscado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pluBuscadoActionPerformed(evt);
            }
        });
        pluBuscado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pluBuscadoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel5.setText("Cantidad :");

        txt_cantidad.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txt_cantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cantidadKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel6.setText("Precio :");

        txt_precios.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txt_precios.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_precios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_preciosActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel7.setText("Total :");

        txt_total.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        txt_total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_totalActionPerformed(evt);
            }
        });

        btn_calcular.setBackground(new java.awt.Color(0, 153, 204));
        btn_calcular.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_calcular.setText("Calcular");
        btn_calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calcularActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 153, 204));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        numerofactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Factura Nº :");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("Venta:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Local", "Vendedor1", "Vendedor2", "Vendedor3" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("Fecha :");

        txt_fecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fechaActionPerformed(evt);
            }
        });

        tabla_productos.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        tabla_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cantidad", "Detalles", "V.Unitario", "V.Total"
            }
        ));
        jScrollPane5.setViewportView(tabla_productos);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Total A Pagar:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Cant. Pago:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Devuelta:");

        txt_totalApagar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        txt_cant_pago.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_cant_pago.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_cant_pagoKeyTyped(evt);
            }
        });

        txt_devuelta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btn_eliminarF.setBackground(new java.awt.Color(0, 153, 204));
        btn_eliminarF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_eliminarF.setText("Eliminar");
        btn_eliminarF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarFActionPerformed(evt);
            }
        });

        btn_registrarF.setBackground(new java.awt.Color(0, 153, 204));
        btn_registrarF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_registrarF.setText("Efectivo");
        btn_registrarF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarFActionPerformed(evt);
            }
        });
        btn_registrarF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btn_registrarFKeyTyped(evt);
            }
        });

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/cedula.png"))); // NOI18N

        jButton17.setText("Refrescar");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setToolTipText("");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Telefono :");

        celularFactura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        celularFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        celularFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Celular :");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Direccion :");

        direccionFactura.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        direccionFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        direccionFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cedulaFactura.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cedulaFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cedulaFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel75.setText("Cedula :");

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLayeredPane1.setLayer(chekNuevaFactura, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_buscarcliente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(clienteBuscado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_NombreCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(telefonoCliente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_nameProd, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_consulta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(pluBuscado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_cantidad, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_precios, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_total, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_calcular, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(numerofactura, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jComboBox1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_fecha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_totalApagar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_cant_pago, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txt_devuelta, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_eliminarF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btn_registrarF, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(celularFactura, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel33, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(direccionFactura, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cedulaFactura, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel75, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jSeparator2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(celularFactura))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(clienteBuscado)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(chekNuevaFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(113, 113, 113)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_buscarcliente)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(txt_NombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(cedulaFactura)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(direccionFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(telefonoCliente)))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_precios)
                                            .addComponent(txt_cantidad)
                                            .addComponent(txt_total)))
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                        .addComponent(txt_nameProd, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(pluBuscado, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_consulta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(btn_calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(jButton17)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel10))
                                        .addGap(18, 18, 18)
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                                .addComponent(btn_eliminarF, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btn_registrarF, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txt_devuelta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_cant_pago)
                                            .addComponent(txt_totalApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addGap(366, 366, 366)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel1))
                                .addGap(36, 36, 36)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(numerofactura, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_fecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(218, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(chekNuevaFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clienteBuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscarcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_NombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(celularFactura)
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cedulaFactura)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(direccionFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(26, 26, 26)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(numerofactura, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(txt_totalApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)))))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_cant_pago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                                .addComponent(jLabel10)
                                .addGap(80, 80, 80))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_devuelta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_eliminarF, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_registrarF, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pluBuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_consulta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_nameProd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_precios, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(jButton17)
                        .addGap(51, 51, 51))))
        );

        jTabbedPane2.addTab("Facturar", jLayeredPane1);

        panelEmpleado2.setToolTipText("");

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel104.setText("Nombre y Apellido:");

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel105.setText("Cedula:");

        editCedula1.setEditable(false);
        editCedula1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        editCedula1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editCedula1.setToolTipText("");
        editCedula1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel106.setText("Telefono:");

        editTelefono1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        editTelefono1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTelefono1.setToolTipText("");
        editTelefono1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel107.setText("Correo:");

        editNombres1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        editNombres1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNombres1.setToolTipText("");
        editNombres1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel108.setText("Cargo:");

        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel109.setText("Salario:");

        editSalario1.setEditable(false);
        editSalario1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        editSalario1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editSalario1.setToolTipText("");
        editSalario1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel110.setText("Vinculado A partir De: ");

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel111.setText("Tipo de contratacion:");

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel112.setText("Banco Registrado:");

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel113.setText("Cuenta de Cobro:");

        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel114.setText("Fecha fin contrato:");

        editFechaFin1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        editFechaFin1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editFechaFin1.setToolTipText("");
        editFechaFin1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        editBanco1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bancolombia", "Davivienda", "Efectivo", "Cheque" }));
        editBanco1.setToolTipText("");
        editBanco1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton16.setText("Actualizar");
        jButton16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        editCorreo1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        editCorreo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editCorreo1.setToolTipText("");
        editCorreo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        editCuenta1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        editCargo1.setEditable(false);
        editCargo1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        editCargo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editCargo1.setToolTipText("");
        editCargo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        editFechaVinculacion1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        editFechaVinculacion1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editFechaVinculacion1.setToolTipText("");
        editFechaVinculacion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton37.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton37.setText("Comprobantes");
        jButton37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel101.setText("Identificador Unico De Empleado:");

        editId1.setEditable(false);
        editId1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        editId1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editId1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("Datos Personales:");

        contrato.setEditable(false);
        contrato.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        contrato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        contrato.setToolTipText("");
        contrato.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panelEmpleado2Layout = new javax.swing.GroupLayout(panelEmpleado2);
        panelEmpleado2.setLayout(panelEmpleado2Layout);
        panelEmpleado2Layout.setHorizontalGroup(
            panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleado2Layout.createSequentialGroup()
                .addGap(421, 421, 421)
                .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmpleado2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel111)
                    .addComponent(jLabel104, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                    .addComponent(jLabel105, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editTelefono1)
                    .addComponent(editCedula1)
                    .addComponent(editNombres1)
                    .addComponent(jLabel106, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contrato))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 396, Short.MAX_VALUE)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelEmpleado2Layout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addGap(30, 30, 30)
                        .addComponent(editId1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelEmpleado2Layout.createSequentialGroup()
                        .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editSalario1)
                            .addComponent(jLabel112, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editBanco1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelEmpleado2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel108, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editCorreo1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(editCargo1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(180, 180, 180)
                        .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(editFechaFin1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel114, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel110, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel113, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editCuenta1)
                            .addComponent(editFechaVinculacion1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEmpleado2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(428, 428, 428))
        );
        panelEmpleado2Layout.setVerticalGroup(
            panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleado2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editId1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104)
                    .addComponent(jLabel107)
                    .addComponent(jLabel113))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editNombres1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editCorreo1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(editCuenta1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel110)
                    .addComponent(jLabel108)
                    .addComponent(jLabel105))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editCedula1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editCargo1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editFechaVinculacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel109)
                    .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel106)
                        .addComponent(jLabel114)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editSalario1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(editFechaFin1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel111)
                    .addComponent(jLabel112))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editBanco1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contrato, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelEmpleado2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton13.setText("Mis Datos");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        miNomina.setLayer(panelEmpleado2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        miNomina.setLayer(jButton13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout miNominaLayout = new javax.swing.GroupLayout(miNomina);
        miNomina.setLayout(miNominaLayout);
        miNominaLayout.setHorizontalGroup(
            miNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(miNominaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(miNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEmpleado2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(miNominaLayout.createSequentialGroup()
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        miNominaLayout.setVerticalGroup(
            miNominaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(miNominaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(panelEmpleado2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Mi Nomina", miNomina);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setText("Inventario De Productos");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton3.setText("Consultar Ventas");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton4.setText("Creditos");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton6.setText("Transacciones");
        jButton6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tablaInventario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaInventario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tablaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Producto", "Precio", "Plu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaInventario);

        btn_editarArticulo1.setBackground(new java.awt.Color(0, 153, 204));
        btn_editarArticulo1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btn_editarArticulo1.setText("Actualizar");
        btn_editarArticulo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarArticulo1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/imprimir.png"))); // NOI18N

        jLayeredPane3.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(btn_editarArticulo1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addGap(528, 528, 528)
                                .addComponent(btn_editarArticulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 518, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(486, 486, 486))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarArticulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(250, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Inventario", jLayeredPane3);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton5.setText("Apertura Bases");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton10.setText("Recojida");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jButton11.setText("Cierre De Caja");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Conteo Inicial:");

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel14.setText("Base $:");

        valorInicial.setEditable(false);
        valorInicial.setBackground(new java.awt.Color(153, 153, 255));
        valorInicial.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        valorInicial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valorInicial.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton12.setBackground(new java.awt.Color(255, 102, 102));
        jButton12.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton12.setText("Borrar");
        jButton12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        tablaCajaApertura.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaCajaApertura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Denominacion", "Cantidad", "Total"
            }
        ));
        jScrollPane1.setViewportView(tablaCajaApertura);

        TotalBase.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        TotalBase.setText("Total Hoy:");

        totalBaseHoy.setEditable(false);
        totalBaseHoy.setBackground(new java.awt.Color(153, 153, 255));
        totalBaseHoy.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        totalBaseHoy.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalBaseHoy.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton24.setBackground(new java.awt.Color(255, 255, 153));
        jButton24.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton24.setText("Nueva Base");
        jButton24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valorInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TotalBase, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(totalBaseHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valorInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TotalBase, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalBaseHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        fechaHoy.setEditable(false);
        fechaHoy.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        fechaHoy.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaHoy.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnIniciar.setBackground(new java.awt.Color(255, 255, 153));
        btnIniciar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnIniciar.setText("Iniciar Caja");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAperturaLayout = new javax.swing.GroupLayout(panelApertura);
        panelApertura.setLayout(panelAperturaLayout);
        panelAperturaLayout.setHorizontalGroup(
            panelAperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAperturaLayout.createSequentialGroup()
                .addGroup(panelAperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelAperturaLayout.createSequentialGroup()
                            .addGap(499, 499, 499)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelAperturaLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelAperturaLayout.createSequentialGroup()
                        .addGap(512, 512, 512)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        panelAperturaLayout.setVerticalGroup(
            panelAperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAperturaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelAperturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Conteo Final:");

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel30.setText("Base $:");

        valorInicial1.setEditable(false);
        valorInicial1.setBackground(new java.awt.Color(153, 153, 255));
        valorInicial1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        valorInicial1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valorInicial1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton15.setBackground(new java.awt.Color(255, 102, 102));
        jButton15.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton15.setText("Borrar");
        jButton15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        tablaCierre.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaCierre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Denominacion", "Cantidad", "Total"
            }
        ));
        jScrollPane2.setViewportView(tablaCierre);

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel31.setText("Cierre:");

        tCierre.setEditable(false);
        tCierre.setBackground(new java.awt.Color(153, 153, 255));
        tCierre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tCierre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tCierre.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton22.setBackground(new java.awt.Color(255, 255, 153));
        jButton22.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton22.setText("Conteo final");
        jButton22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valorInicial1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valorInicial1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tCierre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        fechaHoy1.setEditable(false);
        fechaHoy1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        fechaHoy1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaHoy1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnIniciar1.setBackground(new java.awt.Color(255, 255, 153));
        btnIniciar1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnIniciar1.setText("Iniciar Caja");

        javax.swing.GroupLayout panelCierreLayout = new javax.swing.GroupLayout(panelCierre);
        panelCierre.setLayout(panelCierreLayout);
        panelCierreLayout.setHorizontalGroup(
            panelCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCierreLayout.createSequentialGroup()
                .addGroup(panelCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelCierreLayout.createSequentialGroup()
                            .addGap(499, 499, 499)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaHoy1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelCierreLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelCierreLayout.createSequentialGroup()
                        .addGap(512, 512, 512)
                        .addComponent(btnIniciar1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        panelCierreLayout.setVerticalGroup(
            panelCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCierreLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelCierreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaHoy1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnIniciar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("Recogidas:");

        jPanel5.setBackground(new java.awt.Color(255, 255, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel82.setText("N° Ultima Recogida: ");

        numeroUltimaRecogida.setEditable(false);
        numeroUltimaRecogida.setBackground(new java.awt.Color(153, 153, 255));
        numeroUltimaRecogida.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        numeroUltimaRecogida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numeroUltimaRecogida.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jButton18.setBackground(new java.awt.Color(255, 102, 102));
        jButton18.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton18.setText("Nueva Recogida");
        jButton18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        tablaCajaRecogida.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tablaCajaRecogida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Denominacion", "Cantidad", "Total"
            }
        ));
        jScrollPane6.setViewportView(tablaCajaRecogida);

        valorNuevaRecogida.setEditable(false);
        valorNuevaRecogida.setBackground(new java.awt.Color(153, 153, 255));
        valorNuevaRecogida.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        valorNuevaRecogida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valorNuevaRecogida.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel95.setText("Valor Nueva Recogida:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valorNuevaRecogida, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numeroUltimaRecogida, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroUltimaRecogida, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valorNuevaRecogida, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
        );

        fechaHoy2.setEditable(false);
        fechaHoy2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        fechaHoy2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fechaHoy2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnIniciar2.setBackground(new java.awt.Color(255, 255, 153));
        btnIniciar2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnIniciar2.setText("Finalizar Recogida ");
        btnIniciar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciar2ActionPerformed(evt);
            }
        });

        recogidaAregistrar.setEditable(false);
        recogidaAregistrar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        recogidaAregistrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        recogidaAregistrar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        javax.swing.GroupLayout panelRecogidaLayout = new javax.swing.GroupLayout(panelRecogida);
        panelRecogida.setLayout(panelRecogidaLayout);
        panelRecogidaLayout.setHorizontalGroup(
            panelRecogidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecogidaLayout.createSequentialGroup()
                .addGroup(panelRecogidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRecogidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelRecogidaLayout.createSequentialGroup()
                            .addGap(499, 499, 499)
                            .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaHoy2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelRecogidaLayout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRecogidaLayout.createSequentialGroup()
                        .addGap(494, 494, 494)
                        .addComponent(btnIniciar2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(recogidaAregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        panelRecogidaLayout.setVerticalGroup(
            panelRecogidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRecogidaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelRecogidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fechaHoy2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(panelRecogidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recogidaAregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jLayeredPane5.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jButton10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jButton11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(panelApertura, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(panelCierre, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(panelRecogida, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelApertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
            .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                    .addContainerGap(47, Short.MAX_VALUE)
                    .addComponent(panelCierre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)))
            .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                    .addContainerGap(57, Short.MAX_VALUE)
                    .addComponent(panelRecogida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelApertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(240, Short.MAX_VALUE))
            .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                    .addContainerGap(358, Short.MAX_VALUE)
                    .addComponent(panelCierre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)))
            .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane5Layout.createSequentialGroup()
                    .addContainerGap(357, Short.MAX_VALUE)
                    .addComponent(panelRecogida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jTabbedPane2.addTab("Caja", jLayeredPane5);

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel34.setText("Nombre Producto:");

        pLote.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pLote.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pLote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton21.setText("Registrar");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel35.setText("Plu:");

        pPlu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pPlu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pPlu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel36.setText("Precio:");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel37.setText("Existencias:");

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel38.setText("Lote:");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel39.setText("Fecha Registro:");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel40.setText("Fecha Vencimiento:");

        pCodigo.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        pCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pCodigoKeyTyped(evt);
            }
        });

        buscarProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        buscarProducto.setText("Buscar");
        buscarProducto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProductoActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton19.setText("Eliminar");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton20.setText("Actualizar");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        pPrecio.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pRegistro.setEditable(false);
        pRegistro.setBackground(new java.awt.Color(153, 153, 255));
        pRegistro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pCantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pVence.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pVence.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pVence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pId.setEditable(false);
        pId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel74.setText("Codigo Unico:");

        jButton7.setText("R");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProductoLayout = new javax.swing.GroupLayout(panelProducto);
        panelProducto.setLayout(panelProductoLayout);
        panelProductoLayout.setHorizontalGroup(
            panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton21)
                .addGap(48, 48, 48)
                .addComponent(jButton19)
                .addGap(37, 37, 37)
                .addComponent(jButton20)
                .addGap(355, 355, 355))
            .addGroup(panelProductoLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProductoLayout.createSequentialGroup()
                        .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pPrecio)
                            .addComponent(pNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(337, 337, 337)
                        .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pLote)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
                            .addComponent(pRegistro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProductoLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addComponent(jLabel74)
                                .addGap(27, 27, 27)
                                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pVence, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                    .addComponent(pId, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)))
                            .addGroup(panelProductoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pPlu, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelProductoLayout.createSequentialGroup()
                        .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 173, Short.MAX_VALUE))
        );
        panelProductoLayout.setVerticalGroup(
            panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProductoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(pId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pCodigo))
                .addGap(18, 18, 18)
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jLabel37)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pVence, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pPlu, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(pLote))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel39))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(89, 89, 89)
                .addGroup(panelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21)
                    .addComponent(jButton19)
                    .addComponent(jButton20))
                .addGap(52, 52, 52))
        );

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel50.setText("Empresa:");

        nGerente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nGerente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nGerente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton25.setText("Registrar");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel51.setText("Nit:");

        nNit.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nNit.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nNit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel52.setText("Telefono:");

        nTelefono.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel53.setText("Correo:");

        nCorreo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nEmpresa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nEmpresa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel54.setText("Propietario/Gerente:");

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel55.setText("Telefono Gerente:");

        nTelGerente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nTelGerente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nTelGerente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nFechaRegistro.setEditable(false);
        nFechaRegistro.setBackground(new java.awt.Color(153, 153, 255));
        nFechaRegistro.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nFechaRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nFechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel56.setText("Fecha Registro:");

        buscarProveedor.setText("Buscar");
        buscarProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        buscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProveedorActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel68.setText("Direccion:");

        nDireccion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nCorreoGerente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        nCorreoGerente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nCorreoGerente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel69.setText("Correo Gerente:");

        jButton32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton32.setText("Eliminar");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton33.setText("Actualizar");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        pBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1001746848" }));
        pBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pBuscarKeyTyped(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel72.setText("Seleccione el Nit:");

        nId.setEditable(false);
        nId.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        nId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        e.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        e.setText("Identificador:");

        javax.swing.GroupLayout panelProveedorLayout = new javax.swing.GroupLayout(panelProveedor);
        panelProveedor.setLayout(panelProveedorLayout);
        panelProveedorLayout.setHorizontalGroup(
            panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedorLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProveedorLayout.createSequentialGroup()
                        .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(nDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                .addComponent(jLabel68, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(nEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(271, 271, 271)
                        .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProveedorLayout.createSequentialGroup()
                                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedorLayout.createSequentialGroup()
                                        .addComponent(e)
                                        .addGap(50, 50, 50))
                                    .addGroup(panelProveedorLayout.createSequentialGroup()
                                        .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(nCorreo)
                                            .addComponent(nGerente)
                                            .addComponent(nTelGerente)
                                            .addComponent(nCorreoGerente)
                                            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                            .addComponent(jLabel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(166, 166, 166)))
                                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nFechaRegistro)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                                    .addComponent(nId)))
                            .addGroup(panelProveedorLayout.createSequentialGroup()
                                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelProveedorLayout.createSequentialGroup()
                        .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nNit, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelProveedorLayout.createSequentialGroup()
                                .addComponent(jLabel72)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton25)
                .addGap(39, 39, 39)
                .addComponent(jButton32)
                .addGap(36, 36, 36)
                .addComponent(jButton33)
                .addGap(359, 359, 359))
        );
        panelProveedorLayout.setVerticalGroup(
            panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buscarProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(e))
                    .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel53)
                    .addComponent(jLabel56))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nEmpresa))
                .addGap(18, 18, 18)
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nNit, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(nGerente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jLabel55))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedorLayout.createSequentialGroup()
                        .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nTelGerente, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel68)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedorLayout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addGap(3, 3, 3)))
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(nCorreoGerente))
                .addGap(26, 26, 26)
                .addGroup(panelProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton25)
                    .addComponent(jButton32)
                    .addComponent(jButton33))
                .addGap(52, 52, 52))
        );

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel57.setText("Nombre Cliente:");

        cFacturas.setEditable(false);
        cFacturas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cFacturas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cFacturas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton27.setText("Registrar");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel58.setText("Cedula:");

        cCedula.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cCedula.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel59.setText("Telefono:");

        cTelefono.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel60.setText("Direccion:");

        cDireccion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cDireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cDireccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cNombres.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cNombres.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cNombres.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel61.setText("Facturas:");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel62.setText("Fecha Registro:");

        cFechaRegistro.setEditable(false);
        cFechaRegistro.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cFechaRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cFechaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cId.setEditable(false);
        cId.setBackground(new java.awt.Color(153, 153, 255));
        cId.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cId.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cId.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel63.setText("Codigo Unico:");

        cBuscado.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cBuscado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cBuscado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cBuscado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cBuscadoKeyTyped(evt);
            }
        });

        buscarCliente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        buscarCliente.setText("Buscar");
        buscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarClienteActionPerformed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setText("Celular:");

        cCelular.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cCelular.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cCelular.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel71.setText("Correo:");

        cCorreo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton34.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton34.setText("Eliminar");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton35.setText("Actualizar");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProveedor1Layout = new javax.swing.GroupLayout(panelProveedor1);
        panelProveedor1.setLayout(panelProveedor1Layout);
        panelProveedor1Layout.setHorizontalGroup(
            panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedor1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProveedor1Layout.createSequentialGroup()
                        .addComponent(cBuscado, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(cId, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                        .addGap(119, 119, 119))
                    .addGroup(panelProveedor1Layout.createSequentialGroup()
                        .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cCedula, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cTelefono)
                            .addComponent(cCelular)
                            .addComponent(cNombres)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(200, 200, 200)
                        .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cDireccion)
                            .addComponent(cFechaRegistro)
                            .addComponent(cCorreo)
                            .addComponent(cFacturas))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(panelProveedor1Layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jButton27)
                .addGap(33, 33, 33)
                .addComponent(jButton34)
                .addGap(45, 45, 45)
                .addComponent(jButton35)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelProveedor1Layout.setVerticalGroup(
            panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProveedor1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cBuscado)
                    .addGroup(panelProveedor1Layout.createSequentialGroup()
                        .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedor1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProveedor1Layout.createSequentialGroup()
                        .addComponent(cNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cDireccion))
                .addGap(18, 18, 18)
                .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(jLabel61))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel62))
                .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProveedor1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel71)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelProveedor1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel70)
                        .addGap(18, 18, 18)
                        .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(panelProveedor1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton27)
                            .addComponent(jButton34)
                            .addComponent(jButton35))
                        .addGap(30, 30, 30))))
        );

        javax.swing.GroupLayout panelClienteLayout = new javax.swing.GroupLayout(panelCliente);
        panelCliente.setLayout(panelClienteLayout);
        panelClienteLayout.setHorizontalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1303, Short.MAX_VALUE)
            .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelClienteLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(panelProveedor1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
        );
        panelClienteLayout.setVerticalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
            .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelClienteLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(8, Short.MAX_VALUE)))
        );

        cProducto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cProducto.setText("Producto");
        cProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cProductoActionPerformed(evt);
            }
        });

        cCliente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cCliente.setText("Cliente");
        cCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cClienteActionPerformed(evt);
            }
        });

        cProveedor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cProveedor.setText("Provedor");
        cProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cProveedorActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText("Señor usuario seleccione la casilla de acuerdo a su novedad para visualizar las opciones!");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("Si su novedad es actualizar o eliminar, debera realizar una consulta del objeto a modificar.");

        tipoNovedad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tipoNovedad.setText("Tipo Novedad: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoNovedad)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cProducto)
                        .addGap(44, 44, 44)
                        .addComponent(cCliente)
                        .addGap(53, 53, 53)
                        .addComponent(cProveedor)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(panelProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(3, 3, 3)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cProducto)
                    .addComponent(cCliente)
                    .addComponent(cProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipoNovedad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                .addComponent(panelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 385, Short.MAX_VALUE)
                    .addComponent(panelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 419, Short.MAX_VALUE)
                    .addComponent(panelProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane2.addTab("Registros", jPanel1);

        jLabel25.setFont(new java.awt.Font("Vivaldi", 3, 48)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Que deseas hacer hoy!");

        jButton8.setText("Cerrar sesion");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Cambio de clave");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/oscar.jpg"))); // NOI18N

        jButton30.setText("Cambiar foto");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jLayeredPane6.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jButton8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jButton9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jButton30, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane6Layout = new javax.swing.GroupLayout(jLayeredPane6);
        jLayeredPane6.setLayout(jLayeredPane6Layout);
        jLayeredPane6Layout.setHorizontalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25)
                .addGap(301, 301, 301))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(694, 899, Short.MAX_VALUE)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111))
        );
        jLayeredPane6Layout.setVerticalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jButton30)
                .addContainerGap(564, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Otros", jLayeredPane6);

        jlabelTitle.setBackground(new java.awt.Color(0, 255, 255));
        jlabelTitle.setFont(new java.awt.Font("Sylfaen", 1, 36)); // NOI18N
        jlabelTitle.setForeground(new java.awt.Color(102, 0, 255));
        jlabelTitle.setText(" ");
        jlabelTitle.setBorder(new javax.swing.border.MatteBorder(null));
        jlabelTitle.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jlabelTitleMouseDragged(evt);
            }
        });
        jlabelTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jlabelTitleMousePressed(evt);
            }
        });

        bntMinimizar.setBackground(new java.awt.Color(204, 204, 255));
        bntMinimizar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bntMinimizar.setForeground(new java.awt.Color(255, 255, 255));
        bntMinimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/minimizar.png"))); // NOI18N
        bntMinimizar.setBorder(null);
        bntMinimizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntMinimizarActionPerformed(evt);
            }
        });

        btnCerrar.setBackground(new java.awt.Color(204, 204, 255));
        btnCerrar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/cerrar.jpg"))); // NOI18N
        btnCerrar.setBorder(null);
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jlabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bntMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(bntMinimizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
int xx,xy;
int base=0;
int recogida =0;


String f = "0";
    private void jlabelTitleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlabelTitleMouseDragged
        int x =evt.getXOnScreen();
        int y =evt.getYOnScreen();
        this.setLocation(x -xx, y -xy);
    }//GEN-LAST:event_jlabelTitleMouseDragged

    private void jlabelTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlabelTitleMousePressed
        // TODO add your handling code here:

        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jlabelTitleMousePressed

    private void bntMinimizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntMinimizarActionPerformed
        this.setExtendedState(1);
    }//GEN-LAST:event_bntMinimizarActionPerformed
boolean maximizado= false ;
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        System.exit(WIDTH);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void jTabbedPane2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2PropertyChange

    private void cProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cProveedorActionPerformed
        //oculta paneles
        panelProveedor.setVisible(true);
        panelCliente.setVisible(false);
        panelProducto.setVisible(false);
        // desavilita opciones cliente,producto empleado
        cProducto.setSelected(false);
        cCliente.setSelected(false);
        //Notifica en pantalla el tipo de novedad
        tipoNovedad.setText("Tipo Novedad: "+"PROVEEDOR");
        //Agrego la fecha a la caja de texto fecha de registro
        
        nFechaRegistro.setText(fecha);
        //Metodo para Llenar la lista desplegable
        ActualizarLista();
        limpiaProveedor();
        
    }//GEN-LAST:event_cProveedorActionPerformed

    private void cClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cClienteActionPerformed
        //oculta paneles
        panelCliente.setVisible(true);
        panelProducto.setVisible(false);
        panelProveedor.setVisible(false);
        // desavilita opciones empleado,producto proveedor
        cProducto.setSelected(false);
        cProveedor.setSelected(false);
        //Notifica en pantalla el tipo de novedad
        tipoNovedad.setText("Tipo Novedad: "+"CLIENTE");
        limpiarCliente();
    }//GEN-LAST:event_cClienteActionPerformed

    private void cProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cProductoActionPerformed
        //oculta paneles
        panelProducto.setVisible(true);
        panelProveedor.setVisible(false);
        panelCliente.setVisible(false);
        // desavilita opciones cliente,empleado proveedor
        cCliente.setSelected(false);
        cProveedor.setSelected(false);
        //Notifica en pantalla el tipo de novedad
        tipoNovedad.setText("Tipo Novedad: "+"PRODUCTO");
        
        pRegistro.setText(fecha);
        pVence.setText("AAAA/MM/DD");
    }//GEN-LAST:event_cProductoActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        boolean estado;
        try {          
            if(cCliente.isSelected()){
            int id = 0;
            String nombre = cNombres.getText();
            String cedula = cCedula.getText();
            String telefono =cTelefono.getText();
            String celular =cCelular.getText();
            String correo =cCorreo.getText();
            int facturas = 0;
            String direccion =cDireccion.getText();
            String fechaRegistro =cFechaRegistro.getText();

            //Crear obteto
            clsCliente cliente = new clsCliente(id, nombre,  cedula,telefono, celular, direccion,facturas,correo,fechaRegistro);
            estado = ctrCliente.Crear(cliente);
                if (estado==true){
                    
                    JOptionPane.showMessageDialog(this,"Nuevo cliente agrego correctamente");
                    limpiarCliente();

                }else{
                    JOptionPane.showMessageDialog(this,"Error! Valide la informacion.");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        boolean estado;
        try {
        int id = Integer.parseInt(nId.getText());
        String nombre = nEmpresa.getText();
        String nit = nNit.getText();
        String telefono =nTelefono.getText();
        String direccion =nDireccion.getText();
        String correo =nCorreo.getText();
        String gerente =nGerente.getText();
        String telGerente =nTelGerente.getText();
        String correoGerente =nCorreoGerente.getText();
        String fechaRegistro =nFechaRegistro.getText();

        //Para acualizar obteto
        clsProveedor proveedor = new clsProveedor(id,nombre,  nit, telefono,direccion, correo, gerente,telGerente,correoGerente, fechaRegistro);
        estado = ctrProveedor.Actualizar(proveedor);
        if (estado){
            //System.out.println(empleado.getNombre());
            JOptionPane.showMessageDialog(this,"El objeto se Actualizo correctamente");
        }else{
            JOptionPane.showMessageDialog(this,"ERROR");
        }
        } catch (HeadlessException | NumberFormatException e) {
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        // elimianr un objeto
        try {
            clsProveedor ingresoAuxiliar = null;

            String id = nId.getText();
            int confirmar = JOptionPane.showConfirmDialog(null, "Al confirmar se eliminara el Proveedor y no sera posible restaurarlo\n"+"¿Esta seguro?",//<- EL MENSAJE
                "Alerta!"/*<- El título de la ventana*/, JOptionPane.YES_NO_OPTION/*Las opciones (si o no)*/, JOptionPane.WARNING_MESSAGE/*El tipo de ventana, en este caso WARNING*/);
            //Si la respuesta es sí(YES_OPTION)
            if (confirmar == 0){
                ingresoAuxiliar = ctrProveedor.Eliminar(id);
                if (ingresoAuxiliar != null) {
                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                    limpiaProveedor();
                    ActualizarLista();
                } else {

                    JOptionPane.showMessageDialog(this, "Error valide la informacion.");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Hay Datos vacios o erroneos");
            System.out.println("Hay datos sin diligenciar o erroneos");
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void buscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarProveedorActionPerformed
        try {
            clsProveedor proveedorAuxiliar = null;

            String nit =(String) pBuscar.getSelectedItem();
            System.out.println("Objeto a buscar:: "+nit);
            proveedorAuxiliar = ctrProveedor.Consultar(nit);

            if (proveedorAuxiliar != null) {
                nId.setText(String.valueOf(proveedorAuxiliar.getId()));
                nEmpresa.setText(proveedorAuxiliar.getNombre());
                nNit.setText(proveedorAuxiliar.getNit());
                nTelefono.setText(proveedorAuxiliar.getTelefono());
                nCorreo.setText(proveedorAuxiliar.getCorreo());
                nGerente.setText(proveedorAuxiliar.getGerente());
                nCorreoGerente.setText(proveedorAuxiliar.getCorreoGerente());
                nTelGerente.setText(proveedorAuxiliar.getTelGerente());
                nDireccion.setText(proveedorAuxiliar.getDireccion());
                nFechaRegistro.setText(proveedorAuxiliar.getFechaRegistrado());

            } else {
                JOptionPane.showMessageDialog(this, "Proveedor no encontrado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Hay Datos vacios o erroneos");
            System.out.println("Hay datos sin diligenciar o erroneos");
        }
    }//GEN-LAST:event_buscarProveedorActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        boolean estado;
        try {          
            if(cProveedor.isSelected()){
            int id = 0;
            String nombre = nEmpresa.getText();
            String nit = nNit.getText();
            String telefono =nTelefono.getText();
            String direccion =nDireccion.getText();
            String correo =nCorreo.getText();
            String gerente =nGerente.getText();
            String telGerente =nTelGerente.getText();
            String correoGerente =nCorreoGerente.getText();
            String fechaRegistro =nFechaRegistro.getText();

            //Crear obteto
            clsProveedor proveedor = new clsProveedor(id,nombre,  nit, telefono,direccion, correo, gerente,telGerente,correoGerente, fechaRegistro);
            estado = ctrProveedor.Crear(proveedor);
                if (estado==true){
                    //System.out.println(empleado.getNombre());
                    JOptionPane.showMessageDialog(this,"Nuevo proveedor se agrego correctamente");
                    limpiaProveedor();
                    ActualizarLista();

                }else{
                    JOptionPane.showMessageDialog(this,"Error! Valide la informacion.");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        FileNameExtensionFilter imgFilter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
        fileChooser.setFileFilter(imgFilter);

        int result = fileChooser.showOpenDialog(this);

        if (result != JFileChooser.CANCEL_OPTION) {

            File fileName = fileChooser.getSelectedFile();

        }
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        int confirmar = JOptionPane.showConfirmDialog(null, "Al confirmar se realizara el cierre de sesion\n"+"¿Esta seguro?",//<- EL MENSAJE
            "Alerta!"/*<- El título de la ventana*/, JOptionPane.YES_NO_OPTION/*Las opciones (si o no)*/, JOptionPane.WARNING_MESSAGE/*El tipo de ventana, en este caso WARNING*/);
        //Si la respuesta es sí(YES_OPTION)
        System.out.println("VALOR DE CONFIRMAR: ("+confirmar+")");

        if (confirmar == 0){
            cacha login = new cacha();
            login.setVisible(true);
            this.setVisible(false);

        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void btn_editarArticulo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarArticulo1ActionPerformed
        int fila=tablaInventario.getRowCount();
        for (int i = fila-1; i>0; i--){

            dtm2.removeRow(i);

        }
        if (dtm2.getRowCount()>0){
            dtm2.removeRow(0);
        }

        String id = ctrInicio.ListarId();
        String producto = ctrInicio.ListarProducto();
        String plu = ctrInicio.ListarPlu();
        String precio = ctrInicio.ListarPrecio();
        String cant = ctrInicio.ListarInventario();
        String[] filasProducto = producto.split(",");
        for (int i = 0; i < filasProducto.length; i++){

        }
        String[] filasId = id.split(",");
        for (int i = 0; i < filasId.length; i++){

        }
        String[] filasPlu = plu.split(",");
        for (int i = 0; i < filasPlu.length; i++){

        }
        String[] filasPrecio = precio.split(",");
        for (int i = 0; i < filasPrecio.length; i++){

        }

        String[] filas = cant.split(",");
        for (int i = 0; i < filas.length; i++){

            dtm2.addRow(new Object[]{
                filasId[i],filasProducto[i],filasPrecio[i],filasPlu[i],filas[i]
            });
            

        }
    }//GEN-LAST:event_btn_editarArticulo1ActionPerformed

    private void jLayeredPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLayeredPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLayeredPane1MouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        
    }//GEN-LAST:event_jButton17ActionPerformed

    private void btn_registrarFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_registrarFKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_registrarFKeyTyped

    private void btn_registrarFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarFActionPerformed

        // realiza el pago en efectivo
        try {

            int totalFactura;
            int pago_cliente;

            pago_cliente =Integer.parseInt(txt_cant_pago.getText());
            totalFactura= Integer.parseInt(txt_totalApagar.getText());
            int totalizado = totalFactura - pago_cliente;
            if (pago_cliente < totalFactura){
                JOptionPane.showMessageDialog(this, "Aun faltan "+totalizado+ " para pagar la factura");

            }else{
                int tpago = Integer.parseInt(txt_totalApagar.getText());
                int cant_pago = Integer.parseInt(txt_cant_pago.getText());
                int devuelta = cant_pago - tpago ;
                txt_devuelta.setText(String.valueOf(devuelta));
                JOptionPane.showMessageDialog(this, "Devuelves: "+String.valueOf(devuelta));
                JOptionPane.showConfirmDialog(this, "Imprimir Facturar");
                boolean estado;
                String fecha=txt_fecha.getText();
                String numero = numerofactura.getText();
                String Ncliente = txt_NombreCliente.getText();
                String cedula = clienteBuscado.getText();
                int cajero = Integer.parseInt(cacha.usuario());
                int id = 0;
                int total = Integer.parseInt(txt_totalApagar.getText());
                clsFactura factura = new clsFactura(id,fecha, numero, cedula,total, Ncliente, cajero);
               estado = ctrInicio.GuardarFactura(factura);
               // if (estado){
                    //System.out.println(empleado.getNombre());
                    crearDocumento();
                    //UPDATE `productos` SET `cantidad`='24' WHERE plu="1072"
               // }else{
                    JOptionPane.showMessageDialog(this,"El objeto NO se agrego correctamente");
                //}

            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error Digitacion");
        }
    }//GEN-LAST:event_btn_registrarFActionPerformed

    private void btn_eliminarFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarFActionPerformed
        // TODO add your handling code here:}
        int fila=tabla_productos.getSelectedRow();
        if(fila>=0){
            dtm.removeRow(fila);
            sumarTotal();
        }else{
            JOptionPane.showMessageDialog(null, "No ha seleccionado un producto de la factura");
        }
    }//GEN-LAST:event_btn_eliminarFActionPerformed

    private void txt_cant_pagoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cant_pagoKeyTyped
        // Ejecuta bnt_registrarF de forma auntomatica cuando ejecuto un ENTER
        char cTeclaPresionada=evt.getKeyChar();

        if (cTeclaPresionada == KeyEvent.VK_ENTER){
            btn_registrarF.doClick();
        }
    }//GEN-LAST:event_txt_cant_pagoKeyTyped

    private void txt_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fechaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:ç
        double totalApagar = Double.parseDouble(txt_totalApagar.getText());
        try {
            double totall = Double.parseDouble( txt_total.getText());

            if(totall > 0){
                agregarProd_tabla();
                totalApagar = totalApagar + totall;
                sumarTotal();
                txt_nameProd.setText("");
                txt_total.setText("");
                txt_precios.setText("");
                txt_cantidad.setText("");

            }else{

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"¡Es posible que tengas campos vacios o sin calcular!");
            System.out.println("Campos Vacios error  ");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calcularActionPerformed

        try {
            double cantidad = Double.parseDouble( txt_cantidad.getText().replace(",", "."));

            if(cantidad > 0){
                int valorProducto = Integer.parseInt( txt_precios.getText());
                double total = valorProducto * cantidad;
                //convierto el valor total de entero a string para mostrarlo en la interfaz de usuario
                String valoramostrar = String.valueOf(total);
                txt_total.setText(valoramostrar);
            }else{
                System.out.println("Cantidad no esta asignada");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Hay Datos vacios o erroneos");
            System.out.println("Hay datos sin diligenciar o erroneos");
        }
    }//GEN-LAST:event_btn_calcularActionPerformed

    private void txt_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalActionPerformed

    private void txt_preciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_preciosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_preciosActionPerformed

    private void txt_cantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cantidadKeyTyped
        char cTeclaPresionada=evt.getKeyChar();

        if (cTeclaPresionada == KeyEvent.VK_ENTER){
            btn_calcular.doClick();
        }
    }//GEN-LAST:event_txt_cantidadKeyTyped

    private void pluBuscadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pluBuscadoKeyTyped
        char cTeclaPresionada=evt.getKeyChar();
        try {

            if (cTeclaPresionada == KeyEvent.VK_ENTER){
                btn_consulta.doClick();
                if(f.equals("0")){
                    txt_cantidad.setText(JOptionPane.showInputDialog("Cantidad"));
                    btn_calcular.doClick();
                    int confirmar = JOptionPane.showConfirmDialog(null, "Desea Agregar a la Facttura",//<- EL MENSAJE
                        "Alerta!"/*<- El título de la ventana*/, JOptionPane.YES_NO_OPTION/*Las opciones (si o no)*/, JOptionPane.WARNING_MESSAGE/*El tipo de ventana, en este caso WARNING*/);
                    if(confirmar == 0){
                        jButton1.doClick();
                    }
                }

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_pluBuscadoKeyTyped

    private void pluBuscadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pluBuscadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pluBuscadoActionPerformed

    private void btn_consultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultaActionPerformed

        try {
            clsProducto empleadoAuxiliar = null;

            String plu = pluBuscado.getText();
            System.out.println("ACA VOY BIEN  EN INICIO PLUE ES:" + plu);

            empleadoAuxiliar = ctrProducto.Consultar(plu);
            if (empleadoAuxiliar != null) {
                int valor = empleadoAuxiliar.getValorproducto();
                String valo = String.valueOf(valor);
                String detalles = empleadoAuxiliar.getDetalle();

                txt_precios.setText(valo);
                System.out.println("Este es el producto retornado "+ detalles);
                txt_nameProd.setText(detalles);
                f = "0";

            } else {

                JOptionPane.showMessageDialog(this, "Es posible que el producto buscado, no este registrado");
                f = null;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Hay Datos vacios o erroneos");
            System.out.println("Hay datos sin diligenciar o erroneos");
        }
    }//GEN-LAST:event_btn_consultaActionPerformed

    private void txt_nameProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nameProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nameProdActionPerformed

    private void clienteBuscadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clienteBuscadoKeyTyped
        char cTeclaPresionada=evt.getKeyChar();

        if (cTeclaPresionada == KeyEvent.VK_ENTER){
            btn_buscarcliente.doClick();
            pluBuscado.requestFocus();
        }
    }//GEN-LAST:event_clienteBuscadoKeyTyped

    private void btn_buscarclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarclienteActionPerformed

        try {
            clsCliente clienteAuxiliar = null;
            String identificacion = clienteBuscado.getText();

            clienteAuxiliar = ctrCliente.Consultar(identificacion);
            if (clienteAuxiliar != null) {
                System.out.println(clienteAuxiliar.getNombre());
                txt_NombreCliente.setText(clienteAuxiliar.getNombre());
                telefonoCliente.setText(clienteAuxiliar.getTelefono());
                celularFactura.setText(clienteAuxiliar.getCelular());
                direccionFactura.setText(clienteAuxiliar.getDireccion());
                cedulaFactura.setText(clienteAuxiliar.getCedula());

            } else {
                JOptionPane.showMessageDialog(this, "Es posible que el cliente buscado, no este registrado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Hay Datos vacios o erroneos");
            System.out.println("Hay datos sin diligenciar o erroneos");
        }
    }//GEN-LAST:event_btn_buscarclienteActionPerformed

    private void chekNuevaFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chekNuevaFacturaActionPerformed
        // TODO add your handling code here:

        telefonoCliente.setText("");
        txt_precios.setText("");
        txt_cantidad.setText("");
        txt_total.setText("");
        clienteBuscado.setText("");
        txt_NombreCliente.setText("");
        txt_totalApagar.setText("0");
        txt_cant_pago.setText("");
        txt_devuelta.setText("0");


        String fechaActual = sdf.format(todayDate);
        txt_fecha.setText(fechaActual);

        String modelo = ctrInicio.ListarProducto();
        System.out.println(modelo);
        String[] filas = modelo.split(",");
        for (int i = 0; i < filas.length; i++){

        }

        int ultima = ctrInicio.UltimaFactura();
        System.out.println(ultima);
        ultima = ultima +1;
        numerofactura.setText(String.valueOf(ultima));

        int fila=tabla_productos.getRowCount();

        for (int i = fila-1; i>0; i--){

            dtm.removeRow(i);

        }
        if (dtm.getRowCount()>0){
            dtm.removeRow(0);
        }
    }//GEN-LAST:event_chekNuevaFacturaActionPerformed

    private void buscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarProductoActionPerformed
        try {
            clsProducto productoAuxiliar = null;

            String codigo =pCodigo.getText();
            System.out.println("Objeto a buscar:: "+ codigo);
            productoAuxiliar = ctrProducto.Consultar(codigo);

            if (productoAuxiliar != null) {
                pId.setText(String.valueOf(productoAuxiliar.getId()));
                pPlu.setText(productoAuxiliar.getPlu());
                pNombre.setText(productoAuxiliar.getDetalle());
                pPrecio.setText(String.valueOf(productoAuxiliar.getValorproducto()));
                pCantidad.setText(productoAuxiliar.getCantidad());
                pLote.setText(productoAuxiliar.getLote());
                pRegistro.setText(productoAuxiliar.getFechaRegistro());
                pVence.setText(productoAuxiliar.getFechaVencimiento());
                System.out.println("registo:::"+productoAuxiliar.getFechaRegistro());
                
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Hay Datos vacios o erroneos");
            System.out.println("Hay datos sin diligenciar o erroneos");
        }
        
    }//GEN-LAST:event_buscarProductoActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        boolean estado;
        try {
         
            if(cProducto.isSelected()){
            int id = 0;
            String detalle = pNombre.getText();
            String codigo = pPlu.getText();
            int valorproducto =Integer.parseInt(pPrecio.getText());
            String cantidad =pCantidad.getText();
            String lote =pLote.getText();
            String fechaRegistro =pRegistro.getText();
            String fechaVencimiento =pVence.getText();

            //Crear obteto
            clsProducto producto = new clsProducto(id,detalle,  codigo, valorproducto, cantidad,lote, fechaRegistro, fechaVencimiento);
            estado = ctrProducto.Crear(producto);
                if (estado==true){
                    //System.out.println(empleado.getNombre());
                    JOptionPane.showMessageDialog(this,"Nuevo producto se agrego correctamente");
                    limpiaProveedor();
                    ActualizarLista();

                }else{
                    JOptionPane.showMessageDialog(this,"Error! Valide la informacion.");
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        try {
          
            boolean estado;
            int id = Integer.parseInt(pCodigo.getText());
            String detalle = pNombre.getText();
            String plu = pPlu.getText();
            int valorproducto =Integer.parseInt(pPrecio.getText());
            String cantidad =pCantidad.getText();
            String lote =pLote.getText();
            String fechaVencimiento =pVence.getText();
            String fechaRegistro =pRegistro.getText();

            //Para acualizar obteto
            clsProducto producto = new clsProducto(id,detalle,  plu, valorproducto, cantidad,lote, fechaRegistro, fechaVencimiento);
            estado = ctrProducto.Actualizar(producto);
            if (estado){
                //System.out.println(empleado.getNombre());
                JOptionPane.showMessageDialog(this,"El objeto se Actualizo correctamente");
            }else{
                JOptionPane.showMessageDialog(this,"ERROR");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String code = pCodigo.getText();
        if(code.equals("")){
            pRegistro.setText(fecha);
            
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void buscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarClienteActionPerformed
        try {
            clsCliente clienteAuxiliar = null;

            String cedula =cBuscado.getText();
            clienteAuxiliar = ctrCliente.Consultar(cedula);

            if (clienteAuxiliar != null) {
                cId.setText(String.valueOf(clienteAuxiliar.getId()));
                cNombres.setText(clienteAuxiliar.getNombre());
                cCedula.setText(clienteAuxiliar.getCedula());
                cTelefono.setText(clienteAuxiliar.getTelefono());
                cCelular.setText(clienteAuxiliar.getCelular());
                cDireccion.setText(clienteAuxiliar.getDireccion());
                cFacturas.setText(String.valueOf(clienteAuxiliar.getFacturas()));
                cCorreo.setText(clienteAuxiliar.getCorreo());
                cFechaRegistro.setText(clienteAuxiliar.getFechaRegistro());

            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado");
                limpiarCliente();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Hay Datos vacios o erroneos");
            System.out.println("Hay datos sin diligenciar o erroneos");
        }
    }//GEN-LAST:event_buscarClienteActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        try {
            clsCliente clienteAuxiliar = null;

            String id = cId.getText();
            int confirmar = JOptionPane.showConfirmDialog(null, "Al confirmar se eliminara el cliente y no sera posible restaurarlo\n"+"¿Esta seguro?",//<- EL MENSAJE
                "Alerta!"/*<- El título de la ventana*/, JOptionPane.YES_NO_OPTION/*Las opciones (si o no)*/, JOptionPane.WARNING_MESSAGE/*El tipo de ventana, en este caso WARNING*/);
            //Si la respuesta es sí(YES_OPTION)
            if (confirmar == 0){
                clienteAuxiliar = ctrCliente.Eliminar(id);
                if (clienteAuxiliar != null) {
                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                    limpiarCliente();
                    
                } else {

                    JOptionPane.showMessageDialog(this, "Error valide la informacion.");
                }
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this,"Hay Datos vacios o erroneos");
            System.out.println("Hay datos sin diligenciar o erroneos");
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        boolean estado;
        try {
            int id = Integer.parseInt(cId.getText());
            String nombre = cNombres.getText();
            String cedula = cCedula.getText();
            String telefono =cTelefono.getText();
            String celular =cCelular.getText();
            String correo =cCorreo.getText();
            int facturas = Integer.parseInt(cFacturas.getText());
           
            String direccion =cDireccion.getText();
            String fechaRegistro=cFechaRegistro.getText();

            //Para acualizar obteto
            clsCliente cliente = new clsCliente(id, nombre,  cedula,telefono, celular, direccion,facturas,correo,fechaRegistro);
            estado = ctrCliente.Actualizar(cliente);
            if (estado){
                //System.out.println(empleado.getNombre());
                JOptionPane.showMessageDialog(this,"Datos Actualizados");
            }else{
                JOptionPane.showMessageDialog(this,"ERROR");
            }
        } catch (HeadlessException | NumberFormatException e) {
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void pCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pCodigoKeyTyped
        char cTeclaPresionada=evt.getKeyChar();
        try {

            if (cTeclaPresionada == KeyEvent.VK_ENTER){
                buscarProducto.doClick();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_pCodigoKeyTyped

    private void pBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pBuscarKeyTyped
        char cTeclaPresionada=evt.getKeyChar();
        try {

            if (cTeclaPresionada == KeyEvent.VK_ENTER){
                buscarProveedor.doClick();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_pBuscarKeyTyped

    private void cBuscadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cBuscadoKeyTyped
        // Este evento activa el boton buscar al dar enter
        char cTeclaPresionada=evt.getKeyChar();
        try {

            if (cTeclaPresionada == KeyEvent.VK_ENTER){
                buscarCliente.doClick();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cBuscadoKeyTyped

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        int x =evt.getXOnScreen();
        int y =evt.getYOnScreen();
        this.setLocation(x -xx, y -xy);
    }//GEN-LAST:event_formMouseDragged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       panelApertura.setVisible(true);
       panelRecogida.setVisible(false);
       panelCierre.setVisible(false);
       String totalBase = ctrBases.totalBaseHoy();
       totalBaseHoy.setText(totalBase);
       
       
       
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        panelApertura.setVisible(false);
        panelRecogida.setVisible(true);
        panelCierre.setVisible(false);
        String ultimaRecogida = ctrRecogidas.NumeroRecogida();
        numeroUltimaRecogida.setText(ultimaRecogida);
        
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        panelApertura.setVisible(false);
        panelRecogida.setVisible(false);
        panelCierre.setVisible(true);
        
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int fila=tablaCajaApertura.getRowCount();

        for (int i = fila-1; i>0; i--){

            tabCaja.removeRow(i);

        }
        if (tabCaja.getRowCount()>0){
            tabCaja.removeRow(0);
        }
        base=0;
        valorInicial.setText(String.valueOf(base));
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        
        String ultimaRecogida = ctrRecogidas.NumeroRecogida();
        try {
            recogida= Integer.parseInt(JOptionPane.showInputDialog("Ingrese valor de la recojida"));
            int actualRecogida = Integer.parseInt(ultimaRecogida)+1;
            recogidaAregistrar.setText(String.valueOf(actualRecogida));
            valorNuevaRecogida.setText(String.valueOf(recogida));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Valide Informacion");
        }
        
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
    try {
                    String i =JOptionPane.showInputDialog("Ingrese monedas de 50");
                    if(i.equals("")){
                        i="0";
                    }
                    int c50 =Integer.parseInt(i);
                    int cincuenta= 50*c50;
                    tabCierre.addRow(new Object[]{
                    "50",c50,cincuenta});
                    
                    String i2 =JOptionPane.showInputDialog("Ingrese monedas de 100");
                    if(i2.equals("")){
                        i2="0";
                    }
                    int c100=Integer.parseInt(i2);
                    int cien = 100*c100;
                    tabCierre.addRow(new Object[]{
                    "100",c100,cien});
                    
                    String i3 =JOptionPane.showInputDialog("Ingrese monedas de 200");
                    if(i3.equals("")){
                        i3="0";
                    }
                    int c200=Integer.parseInt(i3);
                    int doscientos = 200*c200;
                    tabCierre.addRow(new Object[]{
                    "200",c200,doscientos});
                    
                    String i4 =JOptionPane.showInputDialog("Ingrese monedas de 500");
                    if(i4.equals("")){
                        i4="0";
                    }
                    int c500=Integer.parseInt(i4);
                    int quinientos = 500*c500;
                    tabCierre.addRow(new Object[]{
                    "500",c500,quinientos});
                    
                    String i5=JOptionPane.showInputDialog("Ingrese monedas de 1000");
                    if(i5.equals("")){
                        i5="0";
                    }
                    int c1000=Integer.parseInt(i5);
                    int mil = 1000*c1000;
                    tabCierre.addRow(new Object[]{
                    "1000",c1000,mil});
                    
                    String i6=JOptionPane.showInputDialog("Ingrese billetes de 2000");
                    if(i6.equals("")){
                        i6="0";
                    }
                    int c2000=Integer.parseInt(i6);
                    int dosmil = 2000*c2000;
                    tabCierre.addRow(new Object[]{
                    "2000",c2000,dosmil});
                    
                    String i7=JOptionPane.showInputDialog("Ingrese billetes de 5000");
                    if(i7.equals("")){
                        i7="0";
                    }
                    int c5000=Integer.parseInt(i7);
                    int cincomil = 5000*c5000;
                    tabCierre.addRow(new Object[]{
                    "5000",c5000,cincomil});
                    
                    String i8 =JOptionPane.showInputDialog("Ingrese billetes de 10000");
                    if(i8.equals("")){
                        i8="0";
                    }
                    int c10000=Integer.parseInt(i8);
                    int diezmil = 10000*c10000;
                    tabCierre.addRow(new Object[]{
                    "10000",c10000,diezmil});
                    
                    String i9 =JOptionPane.showInputDialog("Ingrese billetes de 20000");
                    if(i9.equals("")){
                        i9="0";
                    }
                    int c20000=Integer.parseInt(i9);
                    int veintemil = 20000*c20000;
                    tabCierre.addRow(new Object[]{
                    "20000",c20000,veintemil});
                    
                    String i10 =JOptionPane.showInputDialog("Ingrese billetes de 50000");
                    if(i10.equals("")){
                        i10="0";
                    }
                    int c50000=Integer.parseInt(i10);
                    int cincuentamil = 50000*c50000;
                    tabCierre.addRow(new Object[]{
                    "50000",c50000,cincuentamil});
                    
                    String i11=JOptionPane.showInputDialog("Ingrese billetes de 100000");
                    if(i11.equals("")){
                        i11="0";
                    }
                    int c100000=Integer.parseInt(i11);
                    int cienmil = 100000*c100000;
                    tabCierre.addRow(new Object[]{
                    "100000",c100000,cienmil});
                    
                    int cierreCaja = cincuenta+cien+doscientos+quinientos+mil+dosmil+cincomil+diezmil+veintemil+cincuentamil+cienmil;
                    tCierre.setText(String.valueOf(cierreCaja));

                } catch (Exception e) {
                    System.out.println("valide los datos numericos");
                    }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        String passwordExample = "0";
       valorInicial.setText("0");
       
       String password = JOptionPane.showInputDialog("Ingrese su contraseña");
        
        if (password.equals(passwordExample)){
            int option = JOptionPane.showConfirmDialog(null, "Desea hacer conteo por denominacion.?",//<- EL MENSAJE
                "Registrar Base"/*<- El título de la ventana*/, JOptionPane.YES_NO_OPTION/*Las opciones (si o no)*/, JOptionPane.WARNING_MESSAGE/*El tipo de ventana, en este caso WARNING*/);
            
            if(option==0){
                try {
                    String i =JOptionPane.showInputDialog("Ingrese monedas de 50");
                    if(i.equals("")){
                        i="0";
                    }
                    int c50 =Integer.parseInt(i);
                    int cincuenta= 50*c50;
                    tabCaja.addRow(new Object[]{
                    "50",c50,cincuenta});
                    
                    String i2 =JOptionPane.showInputDialog("Ingrese monedas de 100");
                    if(i2.equals("")){
                        i2="0";
                    }
                    int c100=Integer.parseInt(i2);
                    int cien = 100*c100;
                    tabCaja.addRow(new Object[]{
                    "100",c100,cien});
                    
                    String i3 =JOptionPane.showInputDialog("Ingrese monedas de 200");
                    if(i3.equals("")){
                        i3="0";
                    }
                    int c200=Integer.parseInt(i3);
                    int doscientos = 200*c200;
                    tabCaja.addRow(new Object[]{
                    "200",c200,doscientos});
                    
                    String i4 =JOptionPane.showInputDialog("Ingrese monedas de 500");
                    if(i4.equals("")){
                        i4="0";
                    }
                    int c500=Integer.parseInt(i4);
                    int quinientos = 500*c500;
                    tabCaja.addRow(new Object[]{
                    "500",c500,quinientos});
                    
                    String i5=JOptionPane.showInputDialog("Ingrese monedas de 1000");
                    if(i5.equals("")){
                        i5="0";
                    }
                    int c1000=Integer.parseInt(i5);
                    int mil = 1000*c1000;
                    tabCaja.addRow(new Object[]{
                    "1000",c1000,mil});
                    
                    String i6=JOptionPane.showInputDialog("Ingrese billetes de 2000");
                    if(i6.equals("")){
                        i6="0";
                    }
                    int c2000=Integer.parseInt(i6);
                    int dosmil = 2000*c2000;
                    tabCaja.addRow(new Object[]{
                    "2000",c2000,dosmil});
                    
                    String i7=JOptionPane.showInputDialog("Ingrese billetes de 5000");
                    if(i7.equals("")){
                        i7="0";
                    }
                    int c5000=Integer.parseInt(i7);
                    int cincomil = 5000*c5000;
                    tabCaja.addRow(new Object[]{
                    "5000",c5000,cincomil});
                    
                    String i8 =JOptionPane.showInputDialog("Ingrese billetes de 10000");
                    if(i8.equals("")){
                        i8="0";
                    }
                    int c10000=Integer.parseInt(i8);
                    int diezmil = 10000*c10000;
                    tabCaja.addRow(new Object[]{
                    "10000",c10000,diezmil});
                    
                    String i9 =JOptionPane.showInputDialog("Ingrese billetes de 20000");
                    if(i9.equals("")){
                        i9="0";
                    }
                    int c20000=Integer.parseInt(i9);
                    int veintemil = 20000*c20000;
                    tabCaja.addRow(new Object[]{
                    "20000",c20000,veintemil});
                    
                    String i10 =JOptionPane.showInputDialog("Ingrese billetes de 50000");
                    if(i10.equals("")){
                        i10="0";
                    }
                    int c50000=Integer.parseInt(i10);
                    int cincuentamil = 50000*c50000;
                    tabCaja.addRow(new Object[]{
                    "50000",c50000,cincuentamil});
                    
                    String i11=JOptionPane.showInputDialog("Ingrese billetes de 100000");
                    if(i11.equals("")){
                        i11="0";
                    }
                    int c100000=Integer.parseInt(i11);
                    int cienmil = 100000*c100000;
                    tabCaja.addRow(new Object[]{
                    "100000",c100000,cienmil});
                    
                    int baseAuxiliar = cincuenta+cien+doscientos+quinientos+mil+dosmil+cincomil+diezmil+veintemil+cincuentamil+cienmil;
                    base+= baseAuxiliar;

                } catch (Exception e) {
                    System.out.println("valide los datos numericos");
                    }
            }else if(option==1){
                try {
                    String i1=JOptionPane.showInputDialog("Ingrese el Valor de su base inicial:");
                    if(i1.equals("")){
                        i1="0";
                    }
                    int baseAuxiliar =Integer.parseInt(i1);
                    
                    tabCaja.addRow(new Object[]{
                    "Todas","No Aplica",baseAuxiliar});
                    base+= baseAuxiliar;
                } catch (Exception e) {
                }
                
                
            }
            JOptionPane.showMessageDialog(this, "Su base inicial es: "+String.valueOf(base));
            valorInicial.setText(String.valueOf(base));
        }else{
            JOptionPane.showMessageDialog(this, "Contraseña Incorrecta");
            jButton5.doClick();
            
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        boolean estado;
        try {          
            if(base>0){
                int id = 0;
                int usuario = Integer.parseInt(cacha.usuario());
                
                int baseInicial = base;
                int baseCierre= 0;
                int cantidadRecogidas=0;
                String horaApertura =hora;
                String horaCierre =hora;
                String fecha =fechaHoy.getText();

                //Crear obteto(int id,int usuario,int baseInicial, int baseCierre,int cantidadRecogidas,String horaApertura,String horaCierre,String fecha)
                clsBases base = new clsBases(id, usuario,  baseInicial,baseCierre, cantidadRecogidas, horaApertura,horaCierre,fecha);
                estado = ctrBases.Crear(base);
                
                    if (estado==true){

                        JOptionPane.showMessageDialog(this,"Agregaste una nueva base");
                        limpiarCliente();

                    }else{
                        JOptionPane.showMessageDialog(this,"Error! Valide la informacion.");
                    }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error 500");
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnIniciar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciar2ActionPerformed
        boolean estado;
        try {          
            if(recogida>0){
                int id = 0;
                int usuario = Integer.parseInt(cacha.usuario());
                
                int valor = recogida;
                String numeroRecogida =recogidaAregistrar.getText();
                String horaRecogida =hora;
                String fecha =fechaHoy.getText();

                //Crear obteto(int id,int usuario,int baseInicial, int baseCierre,int cantidadRecogidas,String horaApertura,String horaCierre,String fecha)
                clsRecogidas recogidas = new clsRecogidas(id, usuario,  valor,numeroRecogida, horaRecogida,fecha);
                estado = ctrRecogidas.Crear(recogidas);
                
                    if (estado==true){

                        JOptionPane.showMessageDialog(this,"Agregaste una nueva recogidad");
                        

                    }else{
                        JOptionPane.showMessageDialog(this,"Error! Valide la informacion.");
                    }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error 500");
        }
    }//GEN-LAST:event_btnIniciar2ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        boolean estado;
        try {
            int id = Integer.parseInt(editId1.getText());
            String nombre = editNombres1.getText();
            String cedula = editCedula1.getText();
            String telefono =editTelefono1.getText();
            String cargo =editCargo1.getText();
            String correo =editCorreo1.getText();
            int valorquincena = Integer.parseInt(editSalario1.getText());
            String tipoContrato =contrato.getText();
            
            String banco =(String )editBanco1.getSelectedItem();
            String cuentaCobro =editCuenta1.getText();
            String fechaVinculacion =editFechaVinculacion1.getText();
            String fechaFinContrato =editFechaFin1.getText();

            //Para acualizar obteto
             clsEmpleado empleado = new clsEmpleado( id, nombre,cedula,telefono,cargo, valorquincena,tipoContrato,correo, banco, cuentaCobro, fechaVinculacion, fechaFinContrato);
             estado = ctrEmpleado.Actualizar(empleado);
            if (estado){
                //System.out.println(empleado.getNombre());
                JOptionPane.showMessageDialog(this,"El objeto se Actualizo correctamente");
            }else{
                JOptionPane.showMessageDialog(this,"ERROR");
            }
        } catch (HeadlessException | NumberFormatException e) {
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        miNomina ventana = new miNomina();
        ventana.setVisible(true);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        panelEmpleado2.setVisible(true);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Ventas venta = new Ventas();
        venta.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // elimianr un objeto
        try {
            clsProducto productoAuxiliar = null;

            String id = pId.getText();
            int confirmar = JOptionPane.showConfirmDialog(null, "Al confirmar se eliminara el Proveedor y no sera posible restaurarlo\n"+"¿Esta seguro?",//<- EL MENSAJE
                "Alerta!"/*<- El título de la ventana*/, JOptionPane.YES_NO_OPTION/*Las opciones (si o no)*/, JOptionPane.WARNING_MESSAGE/*El tipo de ventana, en este caso WARNING*/);
            //Si la respuesta es sí(YES_OPTION)
            if (confirmar == 0){
                System.out.println("entro:::");
                System.out.println(productoAuxiliar = ctrProducto.Eliminar(id));
                if (productoAuxiliar != null) {

                    JOptionPane.showMessageDialog(this, "Datos eliminados");
                    limpiaProveedor();
                    ActualizarLista();
                } else {

                    JOptionPane.showMessageDialog(this, "Error valide la informacion.");
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Hay Datos vacios o erroneos");
            System.out.println("Hay datos sin diligenciar o erroneos");
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(initCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(initCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(initCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(initCajero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new initCajero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TotalBase;
    private javax.swing.JButton bntMinimizar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnIniciar1;
    private javax.swing.JButton btnIniciar2;
    private javax.swing.JButton btn_buscarcliente;
    private javax.swing.JButton btn_calcular;
    private javax.swing.JButton btn_consulta;
    private javax.swing.JButton btn_editarArticulo1;
    private javax.swing.JButton btn_eliminarF;
    private javax.swing.JButton btn_registrarF;
    private javax.swing.JButton buscarCliente;
    private javax.swing.JButton buscarProducto;
    private javax.swing.JButton buscarProveedor;
    private javax.swing.JTextField cBuscado;
    private javax.swing.JTextField cCedula;
    private javax.swing.JTextField cCelular;
    private javax.swing.JCheckBox cCliente;
    private javax.swing.JTextField cCorreo;
    private javax.swing.JTextField cDireccion;
    private javax.swing.JTextField cFacturas;
    private javax.swing.JTextField cFechaRegistro;
    private javax.swing.JTextField cId;
    private javax.swing.JTextField cNombres;
    private javax.swing.JCheckBox cProducto;
    private javax.swing.JCheckBox cProveedor;
    private javax.swing.JTextField cTelefono;
    private javax.swing.JTextField cedulaFactura;
    private javax.swing.JTextField celularFactura;
    private javax.swing.JCheckBox chekNuevaFactura;
    private javax.swing.JTextField clienteBuscado;
    private javax.swing.JTextField contrato;
    private javax.swing.JTextField direccionFactura;
    private javax.swing.JLabel e;
    private javax.swing.JComboBox<String> editBanco1;
    private javax.swing.JTextField editCargo1;
    private javax.swing.JTextField editCedula1;
    private javax.swing.JTextField editCorreo1;
    private javax.swing.JTextField editCuenta1;
    private javax.swing.JTextField editFechaFin1;
    private javax.swing.JTextField editFechaVinculacion1;
    private javax.swing.JTextField editId1;
    private javax.swing.JTextField editNombres1;
    private javax.swing.JTextField editSalario1;
    private javax.swing.JTextField editTelefono1;
    private javax.swing.JTextField fechaHoy;
    private javax.swing.JTextField fechaHoy1;
    private javax.swing.JTextField fechaHoy2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel jlabelTitle;
    private javax.swing.JLayeredPane miNomina;
    private javax.swing.JTextField nCorreo;
    private javax.swing.JTextField nCorreoGerente;
    private javax.swing.JTextField nDireccion;
    private javax.swing.JTextField nEmpresa;
    private javax.swing.JTextField nFechaRegistro;
    private javax.swing.JTextField nGerente;
    private javax.swing.JTextField nId;
    private javax.swing.JTextField nNit;
    private javax.swing.JTextField nTelGerente;
    private javax.swing.JTextField nTelefono;
    private javax.swing.JTextField numeroUltimaRecogida;
    private javax.swing.JButton numerofactura;
    private javax.swing.JComboBox<String> pBuscar;
    private javax.swing.JTextField pCantidad;
    private javax.swing.JTextField pCodigo;
    private javax.swing.JTextField pId;
    private javax.swing.JTextField pLote;
    private javax.swing.JTextField pNombre;
    private javax.swing.JTextField pPlu;
    private javax.swing.JTextField pPrecio;
    private javax.swing.JTextField pRegistro;
    private javax.swing.JTextField pVence;
    private javax.swing.JPanel panelApertura;
    private javax.swing.JPanel panelCierre;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JPanel panelEmpleado2;
    private javax.swing.JPanel panelProducto;
    private javax.swing.JPanel panelProveedor;
    private javax.swing.JPanel panelProveedor1;
    private javax.swing.JPanel panelRecogida;
    private javax.swing.JTextField pluBuscado;
    private javax.swing.JTextField recogidaAregistrar;
    private javax.swing.JTextField tCierre;
    private javax.swing.JTable tablaCajaApertura;
    private javax.swing.JTable tablaCajaRecogida;
    private javax.swing.JTable tablaCierre;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JTable tabla_productos;
    private javax.swing.JTextField telefonoCliente;
    private javax.swing.JLabel tipoNovedad;
    private javax.swing.JTextField totalBaseHoy;
    private javax.swing.JTextField txt_NombreCliente;
    private javax.swing.JTextField txt_cant_pago;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JButton txt_devuelta;
    private javax.swing.JButton txt_fecha;
    private javax.swing.JTextField txt_nameProd;
    private javax.swing.JTextField txt_precios;
    private javax.swing.JTextField txt_total;
    private javax.swing.JButton txt_totalApagar;
    private javax.swing.JTextField valorInicial;
    private javax.swing.JTextField valorInicial1;
    private javax.swing.JTextField valorNuevaRecogida;
    // End of variables declaration//GEN-END:variables

class fondoPanel extends JPanel{
        private Image imagen;
        @Override
        public void paint(Graphics g){
            imagen = new ImageIcon(getClass().getResource("fondo.jpg")).getImage();
            g.drawImage(imagen,0,0, getWidth(),getHeight(),this);
            setOpaque(false);
            super.paint(g);
        }
        
    }}
