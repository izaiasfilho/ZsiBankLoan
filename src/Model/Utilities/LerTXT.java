/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Utilities;

import Model.Singletons.SingletonCompany;
import static Model.Utilities.InfoSis.getOS;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Izaias
 */
public class LerTXT {
    public static void log(String log) {
        Calendario_Aux data = new Calendario_Aux();

        String OS=getOS();//+ File.separator +
        String nomeArquivo = "Log_" + data.getDataHora();
        String caminho = OS+ File.separator + "Util" + File.separator + "log" + File.separator + nomeArquivo + ".txt";//recebe dos cath
        String dat = data.getDataHora() + "%n";
        String bb = SingletonCompany.instancia.getRazao();

        FileWriter arq;

        try {
            arq = new FileWriter(caminho);
            PrintWriter gravarArq = new PrintWriter(arq);//

            gravarArq.printf("           %n");//     --      

            gravarArq.printf("---------------------------%n");//--------
            gravarArq.printf("Razao social" + SingletonCompany.instancia.getRazao() + "%n");//razao
            gravarArq.printf("CNPJ" + SingletonCompany.instancia.getCnpj() + "%n");//cnpj
            gravarArq.printf("Data: " + dat);//data
            gravarArq.printf(log);//erro

            arq.close();

            JOptionPane.showMessageDialog(null, "Instabilidade na Conexão");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Inconsistência ao salvar o arquivo log! " + ex.toString());
        }
    }

}
