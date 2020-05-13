/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package punto.de.venta.java;

import conexion.conexion;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author sebas
 */
public class Interfaz extends javax.swing.JFrame {

    private String arre[] = new String[4];
    DefaultTableModel model;
    String id = "", nombre = "", marca = "", descripcion = "", precio = "", cantidad = "";
    conexion poss = new conexion();
    Connection conn = poss.conexion();
    ResultSet resultSet = null;
    String pago = null;
    int seleccion = -1;

    public Interfaz() {
        initComponents();
        //tabla.setBackground(Color.white);
        Fecha.start();
//        this.setResizable(false);
        model = (DefaultTableModel) tabla.getModel();
        Pagar.setBackground(Color.BLACK);
    }

    private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    
    private void calculaTotal() {
        Double aux = 0.0;
        int col = tabla.getRowCount();
        for (int i = 0; i < col; i++) {
            aux = aux + (Double.parseDouble((String) tabla.getValueAt(i, 4)) * Double.parseDouble((String) tabla.getValueAt(i, 5)));
        }
        tot.setText("$" + aux + "");
    }
    private boolean buscarID(int fol){
        boolean bandera = false;
        String query = "select id from producto";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (rs.getInt(1) == fol) {
                    bandera = true;
                    break;
                }
            }
        } catch (SQLException ex) {
           System.out.println("mensaje:" + ex.getMessage());
           bandera = false;
        }
        return bandera;
    }

    private int cantidad(int id) {
        int cant = 0;
        int col = 0;
        int columnas = tabla.getRowCount();
        for (int i = 0; i < columnas; i++) {
            col = Integer.parseInt((String) tabla.getValueAt(i, 0));
            if (col == id) {
                cant = cant + Integer.parseInt((String) tabla.getValueAt(i, 5));
            }
        }
        return cant;
    }

    private int buscarExistencia(String fol) {
        int f = 0;
        String query = "select existencia from producto where id = " + fol;
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            f = rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }

        return f;
    }
    private void limpiar(){
        int colum = model.getRowCount() - 1;
        for (int i = colum; i >= 0; i--) {
            model.removeRow(i);
        }
        tot.setText("$0");
        Entrada.setText("");
        seleccion = -1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tiempo = new javax.swing.JLabel();
        eliminar = new javax.swing.JButton();
        Titulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        Entrada = new javax.swing.JTextField();
        cancelar = new javax.swing.JButton();
        Tot = new javax.swing.JLabel();
        Date = new javax.swing.JLabel();
        tot = new javax.swing.JLabel();
        Pagar = new javax.swing.JButton();
        fondoInterfaz = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tiempo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tiempo.setForeground(new java.awt.Color(255, 255, 255));
        tiempo.setText("00:00:00");
        getContentPane().add(tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 70, -1));

        eliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        eliminar.setForeground(new java.awt.Color(255, 51, 51));
        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        getContentPane().add(eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 660, 90, 30));

        Titulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setText("Sistema Punto de Venta");
        getContentPane().add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 350, -1));

        tabla.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Producto", "Marca", "Descripcion", "Precio", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tabla.setInheritsPopupMenu(true);
        tabla.setSelectionBackground(new java.awt.Color(255, 51, 51));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 640, 500));

        Entrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EntradaKeyPressed(evt);
            }
        });
        getContentPane().add(Entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 385, 35));

        cancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cancelar.setForeground(new java.awt.Color(255, 51, 51));
        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });
        getContentPane().add(cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 660, -1, 30));

        Tot.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Tot.setText("Total=");
        getContentPane().add(Tot, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 620, 60, -1));

        Date.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Date.setForeground(new java.awt.Color(255, 255, 255));
        Date.setText("DD:MM:YY");
        getContentPane().add(Date, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, -1, -1));

        tot.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tot.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(tot, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 620, 150, 20));

        Pagar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Pagar.setForeground(new java.awt.Color(255, 51, 51));
        Pagar.setText("Pagar");
        Pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PagarActionPerformed(evt);
            }
        });
        getContentPane().add(Pagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, 90, 30));

        fondoInterfaz.setIcon(new javax.swing.ImageIcon("C:\\Users\\Asahel Ocaño\\Documents\\NetBeansProjects\\Punto-de-Venta\\src\\Imagenes\\Fondo.jpg")); // NOI18N
        fondoInterfaz.setText("jLabel1");
        getContentPane().add(fondoInterfaz, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4, 640, 760));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EntradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EntradaKeyPressed

        int key = evt.getKeyCode();
        if (key == KeyEvent.VK_ENTER) {
            String prod = Entrada.getText();
            if (prod.contains("*")) {
                String arre[] = prod.split("\\*");
                if (isNumeric(arre[0]) && isNumeric(arre[1])) {
                    if (buscarID(Integer.parseInt(arre[1]))) {
                        try {
                      
                        String query = "select id, nombre, marca, descripcion, precio\n"
                                + "from producto\n"
                                + "where id = " + arre[1];

                        String SQL1 = "SELECT * FROM producto where id = " + arre[1];
                        String[] datos = new String[6];
                        PreparedStatement pst = conn.prepareStatement(SQL1);
                        resultSet = pst.executeQuery();

                        while (resultSet.next()) {

                            datos[5] = resultSet.getString(6);
//                            System.out.println(datos[5]);

                        }
                        int productos = cantidad(Integer.parseInt(arre[1]));
                        if (Integer.parseInt(datos[5]) < (Integer.parseInt(arre[0])) + productos) {
                            JOptionPane.showMessageDialog(null, "No hay suficientes unidades de este producto");
                        } else {

                            try {
                                Statement st = conn.createStatement();
                                ResultSet rs = st.executeQuery(query);
                                rs.next();
                                id = rs.getInt(1) + "";
                                nombre = rs.getString(2);
                                marca = rs.getString(3);
                                descripcion = rs.getString(4);
                                precio = rs.getFloat(5) + "";
                                cantidad = arre[0];
                                model.addRow(new Object[]{id, nombre, marca, descripcion, precio, cantidad});
                                calculaTotal();
                                Entrada.setText("");
//                            String SQL = "UPDATE producto SET id=?,nombre=?,descripcion=?,marca=?,precio=?,existencia=? where id = "+ arre[1];
//                            PreparedStatement pst1 = conn.prepareStatement(SQL);
//                            pst1 = conn.prepareStatement(SQL);
//                            pst1.setInt(1, Integer.parseInt(arre[1]));
//                            pst1.setString(2, nombre);
//                            pst1.setString(3, descripcion);
//                            pst1.setString(4, marca);
//                            pst1.setDouble(5, Double.parseDouble(precio));
//                            pst1.setInt(6, (Integer.parseInt(datos[5]) - Integer.parseInt(cantidad)));
//                            pst1.executeUpdate();

                                System.out.println("Registro exitoso");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, ex);
                            }
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    }else{
                      JOptionPane.showMessageDialog(null, "El producto que introdujo no existe", "Error", JOptionPane.ERROR_MESSAGE);  
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Entrada no válida, no introdujo cantidad de producto y/o código de producto");
                }
            } else if (isNumeric(prod)) {
                if (buscarID(Integer.parseInt(prod))) {
                    try {
                    String query = "select id, nombre, marca, descripcion, precio\n"
                            + "from producto\n"
                            + "where id = " + prod;

                    String SQL1 = "SELECT * FROM producto where id = " + prod;
                    String[] datos = new String[6];
                    PreparedStatement pst = conn.prepareStatement(SQL1);
                    resultSet = pst.executeQuery();
                    while (resultSet.next()) {

                        datos[5] = resultSet.getString(6);
                        System.out.println(datos[5]);

                    }
                    int productos = cantidad(Integer.parseInt(prod));
                    if (Integer.parseInt(datos[5]) < (productos + 1)) {
                        JOptionPane.showMessageDialog(null, "No hay mas unidades de este producto");
                    } else {

                        System.out.println("Registro exitoso");
                        try {
                            Statement st = conn.createStatement();
                            ResultSet rs = st.executeQuery(query);
                            rs.next();
                            id = rs.getInt(1) + "";
                            nombre = rs.getString(2);
                            marca = rs.getString(3);
                            descripcion = rs.getString(4);
                            precio = rs.getFloat(5) + "";
                            cantidad = "1";
                            model.addRow(new Object[]{id, nombre, marca, descripcion, precio, cantidad});
                            calculaTotal();
                            Entrada.setText("");
//                        String SQL = "UPDATE producto SET id=?,nombre=?,descripcion=?,marca=?,precio=?,existencia=? where id = "+ prod;
//                        PreparedStatement pst1 = conn.prepareStatement(SQL);
//                        pst1 = conn.prepareStatement(SQL);
//                        pst1.setInt(1, Integer.parseInt(prod));
//                        pst1.setString(2, nombre);
//                        pst1.setString(3, descripcion);
//                        pst1.setString(4, marca);
//                        pst1.setDouble(5, Double.parseDouble(precio));
//                        pst1.setInt(6,(Integer.parseInt(datos[5])- Integer.parseInt(cantidad)));
//                        pst1.executeUpdate();
//                        System.out.println("Registro exitoso");

                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
                }else{
                    JOptionPane.showMessageDialog(null, "El producto que introdujo no existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
                

            } else {
                JOptionPane.showMessageDialog(null, "Entrada no válida");
            }
        }
    }//GEN-LAST:event_EntradaKeyPressed

    private void PagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PagarActionPerformed
        // TODO add your handling code here:
        int filas = model.getRowCount();
        String fol = "";
        int cant = 0;
        int c = 0;
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No ha realizado ninguna compra");
        } else {

            for (int i = 0; i < filas; i++) {
                fol = (String) tabla.getValueAt(i, 0);

                cant = Integer.parseInt((String) tabla.getValueAt(i, 5));
                c = buscarExistencia(fol) - cant;
                String SQL = "UPDATE producto SET existencia=? where id = " + fol;

                try {
                    PreparedStatement pst1 = conn.prepareStatement(SQL);
                    pst1.setInt(1, c);
                    pst1.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            limpiar();
            JOptionPane.showMessageDialog(null, "Se realizo el pago");
        }

    }//GEN-LAST:event_PagarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if (seleccion > -1) {
            model.removeRow(seleccion);
            seleccion = -1;
            calculaTotal();
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningún producto", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        int colum = model.getRowCount() - 1;
        for (int i = colum; i >= 0; i--) {
            model.removeRow(i);
        }
        tot.setText("$0");
    }//GEN-LAST:event_cancelarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        seleccion = tabla.rowAtPoint(evt.getPoint());
        System.out.println(seleccion);
    }//GEN-LAST:event_tablaMouseClicked

    /**
     * @param args the command line arguments
     */
    SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/YYYY");
    SimpleDateFormat tiempo1 = new SimpleDateFormat("H:mm:ss");

    Timer Fecha = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tiempo.setText(tiempo1.format(new Date()));
            Date.setText(date1.format(new Date()));
        }
    });

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
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Date;
    private javax.swing.JTextField Entrada;
    private javax.swing.JButton Pagar;
    private javax.swing.JLabel Titulo;
    private javax.swing.JLabel Tot;
    private javax.swing.JButton cancelar;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel fondoInterfaz;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel tiempo;
    private javax.swing.JLabel tot;
    // End of variables declaration//GEN-END:variables

}
