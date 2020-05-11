/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Asahel Ocaño
 */
public class conexion {

    public String driver = "com.mysql.jdbc.Driver";
    public String bd = "sql3339480";
    public String host = "sql3.freesqldatabase.com";
    public String port = "3306";
    public String user = "sql3339480";
    public String pass = "rpSgcK6fmM";
    public String url = "jdbc:mysql://" + host + ":" + port + "/" + bd;

    public Connection conexion() {
        System.out.println(url);
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url,user,pass);
            if (cn.isValid(10000)) {
                System.out.println("conectado");
            } else {
                System.out.println("ERRROR EN LA CONEXION");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cn;

    }
}