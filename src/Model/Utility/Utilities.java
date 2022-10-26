/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Utility;

import com.toedter.calendar.JDateChooser;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
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
    
     public static String convertPasswordMd5(String password){
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));

               return  hash.toString(10);
            } catch (NoSuchAlgorithmException ex) {
            }
        return null;
     }
//########################################     
     public static String converteDataDate(String data) {
        String dataInvertida[] = new String[3];

        dataInvertida = data.split("/");
        String dia = "";
        String mes = "";
        String ano = "";

        try {
            dia = dataInvertida[0];
            mes = dataInvertida[1];
            ano = dataInvertida[2];
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato da data não reconhecido!");
        }

        String date = ano + "-" + mes + "-" + dia;

        return date;
    }
     
     public static String transformaCalendarEmDate(JDateChooser choose) {
        DateFormat formatodata = new SimpleDateFormat("dd/MM/yyyy");

        Date data = choose.getDate();
        String dataConvertida = "";

        try {
            dataConvertida = formatodata.format(data);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data inválida!");
        }

        return dataConvertida;//vai retornar vazio
    }

     public static Date converteStringDate(String dataRecebida) {

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println(" erro aqui -> " + dataRecebida);
            Date date = (Date) formatter.parse(dataRecebida);
            return date;
        } catch (ParseException ex) {
        }
        return null;
    }

}
