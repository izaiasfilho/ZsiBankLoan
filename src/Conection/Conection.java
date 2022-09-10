/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Izaias
 */
public class Conection {
    
     private static Connection con = null;
    private boolean conection_on;

    
    public static Connection conect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (con == null) {
                String driver = "jdbc:mysql";
                String port = "://:3306/";
                String banck = "u323187073_zsibl";
                String ssl = "?autoReconnect=true&useSSL=false";
                String user = "root";
                String passwd = "mnd0266";
                
               String caminho = driver+port+banck+ssl;
                
                DriverManager.setLoginTimeout(10);
                
                con = DriverManager.getConnection(caminho, user, passwd);

                System.out.println("abril conexao");
                return con;
            }
        } catch (ClassNotFoundException | SQLException ex) {
           ex.printStackTrace();
        }
        return null;
    }
    public boolean isConect(){
        if (conect() != null) {
            conection_on = true;
        }
        return conection_on;
    }

    public void setKey_on(boolean conection_on) {
        this.conection_on = conection_on;
    }


    public static void closeConect() {

        try {

            if (con != null) {
                con.close();
                con = null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean Checks() {
        if (con != null) {
            return true;
        }
        return false;
    }
}
