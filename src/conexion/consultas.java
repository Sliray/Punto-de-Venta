/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Asahel Ocaño
 */
public class consultas {
    conexion poss = new conexion();
    Connection conn = poss.conexion();
    

    public boolean insertar(String SQL) {
       
        
        
        boolean l = false;
        try {
            PreparedStatement ps;

            ps = conn.prepareStatement(SQL);
            int n = ps.executeUpdate();
            if (n > 0) {
                l = true;
                // JOptionPane.showMessageDialog(null,"Se realizo con exito la operacion");
            }
        } catch (SQLException ex) {
            //Logger.getLogger(ReportGeneratorImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "No se pudo realizar la operacion " + ex);
        }

        return l;
    }

    public boolean modificar(String s) {

        
        boolean l = false;

        try {
            PreparedStatement pst = conn.prepareStatement(s);
            pst.executeUpdate();

            //JOptionPane.showMessageDialog(null,"Se modifico correctamente el registro");
            l = true;

        } catch (SQLException e) {
            System.out.println("error:" + e.getErrorCode());
            System.out.println("mensaje:" + e.getMessage());
//            JOptionPane.showMessageDialog(null, "Error");
            JOptionPane.showMessageDialog(null, "No se pudo realizar la modificacion " + e.getMessage());
        }
        return l;

    }

    public void eliminar(String SQL) {
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/rsz_eliminar.png"));
        try {
            PreparedStatement pst = conn.prepareStatement(SQL);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se realizo con exito la operacion", "Eliminar",
                     JOptionPane.INFORMATION_MESSAGE, icon);

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error no se pudo realizar la operacion  " + e);
        }
        System.out.println(SQL);

    }

    public static int contarPalabras(String s) {
        int contador = 1, pos;
        s = s.trim(); //eliminar los posibles espacios en blanco al principio y al final
        if (s.isEmpty()) { //si la cadena está vacía
            contador = 0;
        } else {
            pos = s.indexOf(" "); //se busca el primer espacio en blanco
            while (pos != -1) { //mientras que se encuentre un espacio en blanco
                contador++; //se cuenta una palabra
                pos = s.indexOf(" ", pos + 1); //se busca el siguiente espacio en blanco
            }                                               //a continuación del actual
        }
        return contador;
    }
    
}
