/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author Izaias
 */
public class TransformaNumero {
   
    public static String passwordJOPtion() {
        //Criar a mensagem sera exibida
        JLabel label = new JLabel("Digite a senha do Administrador!:");
        //criar o componente grafico que recebera o que for digitado
        JPasswordField jpf = new JPasswordField();
        //Exibir a janela para o usuario
        JOptionPane.showConfirmDialog(null,
                new Object[]{label, jpf}, "Senha:",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        //Pegamos o que foi digitado e comparamos com a senha correta
        String senhaDigitada = new String(jpf.getPassword());

        return senhaDigitada;
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
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
        return licenca;
    }

    public static String transformaIntString(int numero) {
        String numeroConvertido = String.valueOf(numero);

        return numeroConvertido;
    }

    public static int transformaStringInt(String numero) {
        int numeroConvertido = Integer.parseInt(numero);

        return numeroConvertido;
    }

    public static double transformaStringDouble(String numero) {
        double numeroConvertido = 0;

        if (!numero.equals("")) {
            numeroConvertido = Double.parseDouble(numero.replace(",", "."));
        }
        return numeroConvertido;
    }

    public static String transformaDoubleString(double numero) {
        String numeroConvertido = String.valueOf(numero);

        return numeroConvertido;
    }

    public static String tranformaFoneCpf(String fone) {
        char um = fone.charAt(1);
        char dois = fone.charAt(2);
        char tres = fone.charAt(4);
        char qua = fone.charAt(5);
        char cin = fone.charAt(6);
        char seis = fone.charAt(7);
        char set = fone.charAt(8);
        char oito = fone.charAt(10);
        char nov = fone.charAt(11);
        char dez = fone.charAt(12);
        char onz = fone.charAt(13);

        String a = String.valueOf(um);
        String b = String.valueOf(dois);
        String c = String.valueOf(tres);
        String d = String.valueOf(qua);
        String e = String.valueOf(cin);
        String f = String.valueOf(seis);
        String g = String.valueOf(set);
        String h = String.valueOf(oito);
        String i = String.valueOf(nov);
        String j = String.valueOf(dez);
        String k = String.valueOf(onz);

        String cpf = a + b + c + "." + d + e + f + "." + g + h + i + "-" + j + k;

        int tam = cpf.length();

        if (tam < 11) {

            for (int x = 11; x >= tam; x--) {
                cpf += "0";
            }
        }
        return cpf;
    }
 
}
