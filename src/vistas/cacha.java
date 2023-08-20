/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import clases.clsLogeo;
import clases.clsUsuario;
import clases.place;
import controladores.ctrLogeo;
import controladores.ctrUsuario;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author OscarArb
 */
public class cacha extends javax.swing.JFrame {
        ctrLogeo ctrLogeo = new ctrLogeo();
        ctrUsuario ctrUsuario = new ctrUsuario();
         fondoPanel fondo = new fondoPanel();
        
    public cacha() {
        // this.setContentPane(fondo);
        
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/multimedia/cacha.jpg")).getImage());
        
   
        this.setLocationRelativeTo(null);
        place usuario= new place("Usuario", txt_user);
        place contrasena  = new place("Password", txt_pass);
        txtCargando.setVisible(false);
        
        
       
        
    }
 public static String usuario= "";
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_user = new javax.swing.JTextField();
        txt_pass = new javax.swing.JPasswordField();
        txtCargando = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ContableSF V 1.0.0");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText(" ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 43, -1, -1));

        txt_user.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_user.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_user.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_userActionPerformed(evt);
            }
        });
        txt_user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_userKeyTyped(evt);
            }
        });
        getContentPane().add(txt_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 89, 200, 40));

        txt_pass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_pass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_pass.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_pass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_passMouseClicked(evt);
            }
        });
        txt_pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_passKeyTyped(evt);
            }
        });
        getContentPane().add(txt_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 150, 200, 40));

        txtCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/2.gif"))); // NOI18N
        txtCargando.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        getContentPane().add(txtCargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 170, 40));

        btnIngresar.setBackground(new java.awt.Color(204, 204, 255));
        btnIngresar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnIngresar.setText("INGRESAR");
        btnIngresar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.gray, java.awt.Color.black, java.awt.Color.pink, java.awt.Color.darkGray));
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/Cargando.gif"))); // NOI18N
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIngresarMouseClicked(evt);
            }
        });
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, 170, 40));

        jButton10.setBackground(new java.awt.Color(204, 204, 255));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/cerrar.jpg"))); // NOI18N
        jButton10.setBorder(null);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 50, 40));

        jButton2.setBackground(new java.awt.Color(204, 204, 255));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Registrarme");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.gray, java.awt.Color.black, java.awt.Color.pink, java.awt.Color.darkGray));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 150, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/cacha.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 200, 200));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 204, 255));
        jLabel3.setText("Login");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, -1));

        jLabel5.setBackground(new java.awt.Color(102, 102, 102));
        jLabel5.setForeground(new java.awt.Color(255, 153, 153));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/fondoLabel.jpg"))); // NOI18N
        jLabel5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel5MouseDragged(evt);
            }
        });
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 500, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        registro registro = new registro();
        registro.setVisible(true);
        this.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel5MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseDragged
        int x =evt.getXOnScreen();
        int y =evt.getYOnScreen();
        this.setLocation(x -xx, y -xy);
    }//GEN-LAST:event_jLabel5MouseDragged

    private void txt_passKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_passKeyTyped
        // TODO add your handling code here:
        char cTeclaPresionada=evt.getKeyChar();

        if (cTeclaPresionada == KeyEvent.VK_ENTER){
            btnIngresar.doClick();
        }
    }//GEN-LAST:event_txt_passKeyTyped
   
    void logeo(){
        titulo = txt_user.getText();
        
        String usuario = txt_user.getText();
        String clave = txt_pass.getText();
        if (usuario.equals("") || clave.equals("")){
            
            txtCargando.setVisible(false);
            JOptionPane.showMessageDialog(this,"Campos vacios *");

            txt_user.setText("");
            txt_pass.setText("");
            
        }else{
            
            try {
                
                clsLogeo empleadoAuxiliar = null;
                empleadoAuxiliar = ctrLogeo.logeo(usuario);
                clsUsuario usuario2 = null;
                usuario2 = ctrUsuario.Consultar(usuario);

                if (empleadoAuxiliar != null) {
                    String usr =empleadoAuxiliar.getUsuario();
                    String clv =empleadoAuxiliar.getClave();
                    if (usr.equals(usuario) && clv.equals(clave)){

                        //init init = new init();
                        //init.setVisible(true);
                        //this.setVisible(false);
                        System.out.println("Logeado Correctamente");
                        if(usuario2.getAdministrador().equals("si")){

                            init init = new init();
                            init.setVisible(true);
                            this.setVisible(false);
                        }else if(usuario2.getCajero().equals("si")){
                            initCajero initCajero = new initCajero();
                            initCajero.setVisible(true);
                            this.setVisible(false);

                        }else{
                            System.out.println("Empleado");
                            initEmpleado initEmpleado = new initEmpleado();
                            initEmpleado.setVisible(true);
                            this.setVisible(false);

                        }   
                    }else if(usr != usuario || clv != clave){
                        JOptionPane.showMessageDialog(this,"Usuario o contraseña invalidos");
                        txt_user.setText("");
                        txt_pass.setText("");
                        System.out.println(empleadoAuxiliar);
                    }
                    else{
                        

                    }

                } else {
                    txtCargando.setVisible(false);
                    JOptionPane.showMessageDialog(this, "Datos incorrectos * ");

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"Error 500");
                System.out.println("Error 500 'Es posible que no tengas conexion'");
            }
        }
    }
    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        new Thread(){
            @Override
            public void run(){
              txtCargando.setVisible(true);
              logeo();
              txtCargando.setVisible(false);
            }
        }.start();
       
        

    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txt_userKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_userKeyTyped
        char cTeclaPresionada=evt.getKeyChar();

        if (cTeclaPresionada == KeyEvent.VK_ENTER){
            btnIngresar.doClick();
        }
    }//GEN-LAST:event_txt_userKeyTyped

    private void txt_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_userActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_userActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        System.exit(WIDTH);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseClicked
        
    }//GEN-LAST:event_btnIngresarMouseClicked

    private void txt_passMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_passMouseClicked
        System.out.println("Se va a empezar a escribir aqui");
    }//GEN-LAST:event_txt_passMouseClicked
 public static String titulo ="";public  String usuario(){
    System.out.println(titulo);
     return titulo;
}int xx,xy; 
    /**s
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
            java.util.logging.Logger.getLogger(cacha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cacha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cacha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cacha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cacha().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel txtCargando;
    private javax.swing.JPasswordField txt_pass;
    private javax.swing.JTextField txt_user;
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
        
    }
}
