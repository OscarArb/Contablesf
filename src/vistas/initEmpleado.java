/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.clsBases;
import clases.clsCliente;
import clases.clsEmpleado;
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
public class initEmpleado extends javax.swing.JFrame {

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
    public initEmpleado() {
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
        tablaInventario.setModel(dtm2);
        ocultarPanelesRegistros();
        placeholder();
        perzonalizarbotones();
        miNomina();
        
       
        
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
        }
    
    void placeholder(){
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
    
    
    void limpiarTablaInv(){
        dtm2.removeRow(ERROR);
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
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaInventario = new javax.swing.JTable();
        btn_editarArticulo1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
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
                        .addGap(528, 528, 528)
                        .addComponent(btn_editarArticulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(85, 85, 85)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_editarArticulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Inventario", jLayeredPane3);

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
                        .addComponent(cCliente)
                        .addGap(29, 29, 29)
                        .addComponent(cProveedor)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(cCliente)
                    .addComponent(cProveedor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tipoNovedad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(panelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        panelProveedor.setVisible(false);
        cProveedor.setSelected(false);
        //Notifica en pantalla el tipo de novedad
        tipoNovedad.setText("Tipo Novedad: "+"CLIENTE");
        limpiarCliente();
    }//GEN-LAST:event_cClienteActionPerformed

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
            java.util.logging.Logger.getLogger(initEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(initEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(initEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(initEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new initEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntMinimizar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btn_editarArticulo1;
    private javax.swing.JButton buscarCliente;
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
    private javax.swing.JCheckBox cProveedor;
    private javax.swing.JTextField cTelefono;
    private javax.swing.JTextField contrato;
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
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
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
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JComboBox<String> pBuscar;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JPanel panelEmpleado2;
    private javax.swing.JPanel panelProveedor;
    private javax.swing.JPanel panelProveedor1;
    private javax.swing.JTable tablaInventario;
    private javax.swing.JLabel tipoNovedad;
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
