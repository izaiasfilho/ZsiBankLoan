/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Utility;

import Model.Entities.LoanEntity;
import com.toedter.calendar.JDateChooser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
     
     public static List<LoanEntity> invertListloan(List<LoanEntity> list){
         int size = list.size();
         List<LoanEntity> newList = new ArrayList();
         for(int x = size; x > 0; x--){
             newList.add(list.get(x-1));
         }
       return newList;  
     }
     
     public static List<String> locateZipCode(String cep) {
         //chamar
         //tratar simbolos
         //aplicar
        String[] cepEncontrado = new String[5];
        List<String> list = new ArrayList();

        cepEncontrado = buscarCep(cep);
        if (cepEncontrado != null) {
            list.add(cepEncontrado[0]);
            list.add(cepEncontrado[1]);
            list.add(cepEncontrado[2]);
            list.add(cepEncontrado[3]);
            list.add(cepEncontrado[4]);
            return list;
        }
          return null;
    }
     
     public static String[] buscarCep(String cep) {
        String[] cepEncontrado = new String[5];
        if (cep.length() == 8) {
            String json;

            try {
                URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
                URLConnection urlConnection = url.openConnection();
                InputStream is = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder jsonSb = new StringBuilder();

                br.lines().forEach(l -> jsonSb.append(l.trim()));
                json = jsonSb.toString();

                json = json.replaceAll("[{},:]", "");
                json = json.replaceAll("\"", "\n");
                String array[] = new String[30];
                array = json.split("\n");

                cepEncontrado[0] = cep;
                cepEncontrado[1] = array[7];
                cepEncontrado[2] = array[15];
                cepEncontrado[3] = array[19];
                cepEncontrado[4] = array[23];
                return cepEncontrado;
            } catch (Exception e) {
            }
        }
      return null;
    }


}
