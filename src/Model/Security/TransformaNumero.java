/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Izaias
 */
public class TransformaNumero {

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
 
}
