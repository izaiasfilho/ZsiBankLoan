/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.zsiauthorization.Model.Utility;
import Model.zsiauthorization.Model.CompanySingleton.SingletonCompany;
import static Model.zsiauthorization.Model.Utility.Propriedades.getOS;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Izaias
 */
public class Utilities {
    private String DataHora;
    private String Data;
    
    public String getDataHora() {
        DateFormat formatodata = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date data = new Date();

        this.DataHora = (formatodata.format(data));
        return DataHora;
    }
    
    public String getdate() {
        DateFormat formatodata = new SimpleDateFormat("yyyy-MM-dd");
        Date data = new Date();

        this.Data = (formatodata.format(data));
        return Data;
    }
    
     public int verificaDiasVencimento(String dataFinal) {
        String dataInvertida[] = new String[3];

        dataInvertida = dataFinal.split("-");

        String ano = dataInvertida[0];
        String mes = dataInvertida[1];
        String dia = dataInvertida[2];

        int yyyy = Integer.parseInt(ano);
        int MM = Integer.parseInt(mes);
        int dd = Integer.parseInt(dia);

        int resultadoFinal = 0;
        // Cria um Objeto LocalDate com a data atual.
        LocalDate hoje = LocalDate.now();

        // Cria um Objeto LocalDate com a data 26/09/2020.
        LocalDate outraData = LocalDate.of(yyyy, MM, dd);

        // Calcula a diferença de dias entre as duas datas
        long diferencaEmDias = ChronoUnit.DAYS.between(hoje, outraData);

        resultadoFinal = (int) diferencaEmDias;

        return resultadoFinal;
    }

     public String desenverteDateData(String date) {
        String data = "01/01/2000";
        if (date != null) {
            String dataInvertida[] = new String[3];

            dataInvertida = date.split("-");

            String ano = dataInvertida[0];
            String mes = dataInvertida[1];
            String dia = dataInvertida[2];

            data = dia + "/" + mes + "/" + ano;
        } else {
            return data;
        }
        return data;
    }
     
     
    public static String gerarMD5(String valor) {
        String identificacaoMetodo = "Projeto: ZsiControleMEI"
                + "Pacote: Util%n"
                + "Classe: TransformaNumero %n"
                + "Metodo: GerarMD5()%n";

        String chavePrincipal = "bcf55/*1236*/BCF55";
        String senha = chavePrincipal + valor;
        String licenca = "";//tipoLicenca=3  / chave nomepc
        MessageDigest md;

        try {
            md = MessageDigest.getInstance("MD5");

            BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));

            licenca = hash.toString(10);
        } catch (NoSuchAlgorithmException ex) {
            log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
        return licenca;
    }

     public static void log(String log) {
        Utilities data = new Utilities();

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
