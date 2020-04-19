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

    public String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public String bd = "punto_venta";
    public String host = "puntoventa.c58jsqn1ty9x.us-east-1.rds.amazonaws.com";
    public String port = "1433";
    public String user = "admin";
    public String pass = "admin123";
    public String url = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + bd + ";user=" + user + ";password=" + pass;

    public Connection conexion() {
        Connection cn = null;
        try {
            Class.forName(driver);
            cn = DriverManager.getConnection(url);
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
