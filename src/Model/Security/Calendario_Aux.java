/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 *
 * @author Izaias
 */
public class Calendario_Aux {
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

}
