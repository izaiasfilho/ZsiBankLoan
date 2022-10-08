/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Izaias
 */
public class ConectionCloud {
    
    private static Connection con = null; //Connection CLASSE PRONTA DE CONECCAO
    private boolean nuvem_on;

    public boolean isNuvem_on() {

        if (conectabddEmpresa() != null) {
            closebdEmpresa();
            nuvem_on = true;//existe conexao
        }
        return nuvem_on;
    }

    public void setNuvem_on(boolean nuvem_on) {
        this.nuvem_on = nuvem_on;
    }

    public static Connection conectabddEmpresa() {
        String identificacaoMetodo = "Projeto: ZsiControleMEI %n"
                + "Pacote: ConexaoDao / Classe: ConexaoEmpresa%n"
                + "Metodo: conectabddEmpresa()%n";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            if (con == null) {// se tiver fechado
                String caminho = "jdbc:mysql://sql172.main-hosting.eu:3306/u323187073_empre";
                String user = "u323187073_izs";
                String passwd = "mnd0266";
                DriverManager.setLoginTimeout(10);//determina o tempo de espera pra conectar ao servidor
                con = DriverManager.getConnection(caminho, user, passwd);//nuvem

                System.out.println("abril cloud empreza");
                return con;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
        return null;
    }

    public static Connection conectaPDVnuvem() {
        String identificacaoMetodo = "Projeto: ZsiControleMEI %n"
                + "Pacote: ConexaoDao / Classe: ConexaoEmpresa%n"
                + "Metodo: conectaPDVnuvem()%n";

        try {
            String[] url = Propriedades.getCaminhoConexaoPropiedades(false);
            Class.forName("com.mysql.jdbc.Driver");
            con = null;

            if (con == null) {
                String caminho = "jdbc:mysql://sql172.main-hosting.eu:3306/" + url[0].toLowerCase();
                String user = url[0].toLowerCase();
                String passwd = "Mnd02661";
                DriverManager.setLoginTimeout(10);//determina o tempo de espera pra conectar ao servidor
                con = DriverManager.getConnection(caminho, user, passwd);//nuvem

                System.out.println("abril a cloud empresa ");
                return con;
            }
        } catch (ClassNotFoundException | SQLException ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
        return null;
    }

    public static void closebdEmpresa() {
        String identificacaoMetodo = "Projeto: ZsiControleMEI %n"
                + "Pacote: ConexaoDao / Classe: ConexaoEmpresa %n"
                + "Metodo: closebdEmpresa() %n";

        try {

            if (con != null) {
                //se tirver aberto
                con.close();
                con = null;
                System.out.println("fechou cloud empresa");
            }
        } catch (SQLException ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
    }

    public static boolean verificaConxaodEmpresa() {

        if (con != null) {//se tiver aberto
            return true;//sim
        }
        return false;//nao
    }

}
