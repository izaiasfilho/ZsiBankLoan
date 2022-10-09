/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Security;

import Model.Entities.Host_Nuvem;
import static Model.Security.InfoSis.enderecoMAC;
import static Model.Security.Propriedades.suesCompanySingleton;
import java.awt.Font;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Izaias
 */
public class CompanyImplemetation implements CompanyInterface {

    String licenca = "";//tipoLicenca=3  / chave nomepc

    @Override
    public void validateLicenseImplemetation() {
        suesCompanySingleton();// carrega o singleton

        Calendario_Aux data = new Calendario_Aux();
        String licenca_gerada = generateLicense(SingletonCompany.instancia.getCnpj(),
                data.desenverteDateData(SingletonCompany.instancia.getData_vencimento()),
                SingletonCompany.instancia.getStatus(), String.valueOf(SingletonCompany.instancia.getTipoLicenca()));

        //valida se a licença é valida
        if (licenca_gerada.equals(SingletonCompany.instancia.getLicenca())) {
            checkExpiration();//se for, verifica
        } else {
            lowLicense(true, null);//se nao for baixa
            checkExpiration();//se for, verifica
        }
    }

    public void checkExpiration() {
        Calendario_Aux data = new Calendario_Aux();
        if (SingletonCompany.instancia.getData_vencimento() != null) {
            if (diasVencimento()) {//nao esta vencido
                vencimentoFalse();
            } else {//esta vencido
                revalidaLicenca();
            }
        }
    }

    public boolean diasVencimento() {
        Calendario_Aux data = new Calendario_Aux();
        boolean valido = data.verificaDiasVencimento(SingletonCompany.instancia.getData_vencimento()) >= 0;
        return valido;
    }

    public void revalidaLicenca() {
        lowLicense(true, null); //vencido
        suesCompanySingleton();// carrega o singleton
        if (diasVencimento()) {
            vencimentoFalse();
        } else {
            chavaPix();
        }
    }

    public void vencimentoFalse() {
        int diasAviso = verificaProximidadeAvisoVencimento();

        if (diasAviso <= 10) {
                lowLicense(true, null);
        }

    }

    public void chavaPix() {
        JLabel label = new JLabel("");
        label.setFont(new Font("Arial", Font.BOLD, 100));

        JOptionPane.showMessageDialog(null, label, "LIVENÇA VENCIDA - Efetue o pagamento para liberação do sistema",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon("C:\\Util\\Logo\\QRcode2.JPEG"));
        System.exit(0);
    }

    public void exibirCobrancaQRcode() {
        int diasVen = verificaProximidadeVencimento();
        chavaPix();
//        System.exit(0);
    }

//---------###---------- LICENÇA ---------- ### ----------
    public String generateLicense(String cnpj, String data_vencimento, String status, String tipoLicenca) {
        String identificacaoMetodo = "PROJETO: ZsiControlerMei%n"
                + "PACOTE: Controller / CLASSE: ControllerEmpresa %n"
                + "METODO: gerarLicença(cnpj, data_vencimento, status, tipoLicenca)%n";

        String[] enderecosMac = new String[4];

        enderecosMac = enderecoMAC();
        String MAC = "";
        boolean valido = false;

        if (!enderecosMac[0].equals("")) {// if host num for vazio

            if (enderecosMac[0] != null) {// if host num for nulo
                MAC = enderecosMac[0];
                valido = true;
            }
        }

        if (valido == false) {

            if (!enderecosMac[1].equals("")) {// if CPU num for vazio

                if (enderecosMac[1] != null) {// if CPU num for nulo
                    MAC = enderecosMac[1];
                    valido = true;
                }
            }
        }

        if (valido == false) {

            if (!enderecosMac[2].equals("")) {// if HD num for vazio

                if (enderecosMac[2] != null) {// if HD num for nulo
                    MAC = enderecosMac[2];
                    valido = true;
                }
            }
        }

        if (valido == false) {

            if (!enderecosMac[3].equals("")) {// if mac num for vazio

                if (enderecosMac[3] != null) {// if CPU mac for nulo
                    MAC = enderecosMac[3];
                    valido = true;
                }
            }
        }

        if (valido == true) {

            String chavePrincipal = "bcf55/*1236*/BCF55";
            String senha = chavePrincipal + cnpj + data_vencimento + status + tipoLicenca + MAC;
            MessageDigest md;

            try {
                md = MessageDigest.getInstance("MD5");

                BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));

                licenca = hash.toString(10);
            } catch (NoSuchAlgorithmException ex) {
                LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
            }
        }
        return licenca;
    }

    ///******************validacao da licença local
    public boolean lowLicense(boolean auto, String url) {
        boolean retorno = true;

        CompanyEntity empresaSolicitada = new CompanyEntity();
        CompanyEntity empresaRecebida = new CompanyEntity();
        ArrayList<Host_Nuvem> listaHoost = new ArrayList();
        Host_Nuvem hostNuvem = new Host_Nuvem();
        Host_Nuvem novosHostInsrir = new Host_Nuvem();
        Calendario_Aux data = new Calendario_Aux();
        PersistenceCloud select = new PersistenceCloud();

        //###########################################################################
        boolean hostValido = false;

        String[] listaEndLocal = new String[4];

        listaEndLocal = enderecoMAC();
        //String meuIp = "";; //verifica o mac da maquina

        if (listaEndLocal != null) {
            empresaSolicitada.setCnpj(SingletonCompany.instancia.getCnpj());
            empresaRecebida = select.selectEmpresasCNPJ(empresaSolicitada);//

            if (empresaRecebida != null) {//verifica se na nuvem esta cadastrado
                String rede = empresaRecebida.getRede_social();
                listaHoost = select.selectHostEmpresasCNPJ(empresaRecebida);//lista todos os host da empresa  
                //verifica se todos os endereços estao preenchidos, se nao tiver preenche

                if (listaHoost == null || listaHoost.size() == 0) { //se nao tiver nenhum-------> insere todos 
                    hostNuvem.setId_empresa(empresaRecebida.getId());
                    hostNuvem.setHost(listaEndLocal[0]);
                    hostNuvem.setCPU(listaEndLocal[1]);
                    hostNuvem.setHD(listaEndLocal[2]);
                    hostNuvem.setMac(listaEndLocal[3]);
                    alterarEmpresaiInsereIP(hostNuvem);
                } else {// se tiver----------> retorna e compara com o atual
                    //

                    for (Host_Nuvem host_Nuvem : listaHoost) {//se nao tiver tudo completo, completa o  que falta

                        if (host_Nuvem.getMac() == null) {
                            novosHostInsrir.setHost(listaEndLocal[0]);
                            novosHostInsrir.setCPU(listaEndLocal[1]);
                            novosHostInsrir.setHD(listaEndLocal[2]);
                            novosHostInsrir.setMac(listaEndLocal[3]);
                            novosHostInsrir.setId_host(host_Nuvem.getId_host());
                            atualizaTodosHost(novosHostInsrir);
                        }
                    }//

                    for (Host_Nuvem host : listaHoost) {//compara todos os endereços

                        if (hostValido == false) {

                            if (host.getHost().equalsIgnoreCase(listaEndLocal[0])) {
                                hostValido = true;
                            } else {

                                if (host.getCPU().equalsIgnoreCase(listaEndLocal[1])) {
                                    hostValido = true;
                                } else {

                                    if (host.getHD().equalsIgnoreCase(listaEndLocal[2])) {
                                        hostValido = true;
                                    } else {

                                        if (host.getMac().equalsIgnoreCase(listaEndLocal[3])) {
                                            hostValido = true;
                                        } else {
                                            hostValido = false;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (hostValido == false) {

                        try {

                            if (!empresaRecebida.getMeuIp().equals("")) {
                                int qtdIp = Integer.parseInt(empresaRecebida.getMeuIp());  // verifica quantidade de ip,

                                if (qtdIp > listaHoost.size()) {//se a quantidade de pc for maior que o cadastrado, insere, se nao bloqueira
                                    hostNuvem.setId_empresa(empresaRecebida.getId());
                                    hostNuvem.setHost(listaEndLocal[0]);
                                    hostNuvem.setCPU(listaEndLocal[1]);
                                    hostNuvem.setHD(listaEndLocal[2]);
                                    hostNuvem.setMac(listaEndLocal[3]);
                                    alterarEmpresaiInsereIP(hostNuvem);
                                    hostValido = true;
                                }
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Instábilidade com a quantidade de host virtual");
                        }
                    }

                    if (hostValido == true) {
                        //se for igual-----> baixa
                        String cnpj = empresaRecebida.getCnpj();
                        int qtd_loign = empresaRecebida.getQtd_login();
                        String status = empresaRecebida.getStatus();
                        String tipoLicenca = String.valueOf(empresaRecebida.getTipoLicenca());

                        if (status.equalsIgnoreCase("inativo")) {
                            cnpj = "INATIVO";
                        }

                        // tratar aqui ip local ou ip em rede
                        String vencimento = data.desenverteDateData(empresaRecebida.getData_vencimento());

                        empresaRecebida.setLicenca(generateLicense(cnpj, vencimento, status, tipoLicenca));
                        empresaRecebida.setIdent_database(empresaRecebida.getBanco());
                        empresaRecebida.setTipoConexao(SingletonCompany.instancia.getTipoConexao());
                        empresaRecebida.setUrl(url != null ? url : SingletonCompany.instancia.getUrl());
                        empresaRecebida.setLogin(SingletonCompany.instancia.getLogin());
                        empresaRecebida.setCaixa(SingletonCompany.instancia.getCaixa());
                        empresaRecebida.setQtdCaixa(SingletonCompany.instancia.getQtdCaixa());
                        empresaRecebida.setApp(SingletonCompany.instancia.getApp());
                        empresaRecebida.setOff(SingletonCompany.instancia.getOff());

                        Propriedades.setEmpresaPropiedades(empresaRecebida);

                    } else {// se nao for igual----> "COMPUTADOR NAO HABILITADO" NAO BAIXA

                        if (empresaRecebida.getBloqueio_auto() == 1) {//verifica se deve bloquear automaticamente ou nao
                            // se sim.... bloqueia
                            String cnpj = "HOST BLOQUEADO";
                            int qtd_loign = empresaRecebida.getQtd_login();
                            String status = "INATIVO";

                            if (status.equalsIgnoreCase("inativo")) {
                                cnpj = "INATIVO";
                            } //baixa e bloqueia.... host nao abilitado

                            String vencimento = data.desenverteDateData(empresaRecebida.getData_vencimento());
                            empresaRecebida.setLicenca(generateLicense(cnpj, vencimento, "INATIVO", "1"));
                            empresaRecebida.setIdent_database(empresaRecebida.getBanco());
                            empresaRecebida.setTipoConexao(empresaRecebida.getTipoConexao());
                            empresaRecebida.setUrl("HOST BLOQUEADO");
                            Propriedades.setEmpresaPropiedades(empresaRecebida);
                        }
                    }
                }//lista de host da nuvem
            } else {// final do verifica se a empresa existe
                retorno = false;
            }
        }// final do verifica lista de host
        return retorno;
    }//fehca o metodo

    //---------###---------- VENCIMENTO ---------- ### ----------
    public int verificaProximidadeAvisoVencimento() {
        Calendario_Aux data = new Calendario_Aux();

        int diasVencimento = 0;

        if (SingletonCompany.instancia.getData_vencimento() != null) {
            diasVencimento = data.verificaDiasVencimento(SingletonCompany.instancia.getData_aviso());

            if (diasVencimento > 0) {
                return diasVencimento;//retorna a quantidade de dias
            }
        }
        return diasVencimento;
    }

    public int verificaProximidadeVencimento() {
        Calendario_Aux data = new Calendario_Aux();

        int diasVencimento = 0;

        if (SingletonCompany.instancia.getData_vencimento() != null) {
            diasVencimento = data.verificaDiasVencimento(SingletonCompany.instancia.getData_vencimento());

            if (diasVencimento > 0) {
                return diasVencimento;//quantidade de dias para o venciemento
            }
        }
        return diasVencimento;
    }

    public static void alterarEmpresaInsereUltimoAcesso() {
        String identificacaoMetodo = "PROJETO: ZsiControlerMei%n"
                + "PACOTE: Controller / CLASSE: ControllerEmpresa%n"
                + "METODO: alterarEmpresaiInsereUltimoAcesso()%n"
                + "DAO: empresa.setUltimoAcesso(data.getdate() \n "
                + "     empresa.setId(SingletonEmpresa.getEmpresa().getId()) \n"
                + "     update.alterarEmpresa_InsereULTIMO_ACESSO(empresa); %n";

        CompanyEntity empresa = new CompanyEntity();
        Calendario_Aux data = new Calendario_Aux();
        PersistenceCloud update = new PersistenceCloud();

        try {
            empresa.setUltimoAcesso(data.getdate());
            empresa.setId(SingletonCompany.instancia.getId());

            update.alterarEmpresa_InsereULTIMO_ACESSO(empresa);
        } catch (Exception ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
    }

    public void alterarEmpresaiInsereIP(Host_Nuvem host) {
        String identificacaoMetodo = "PROJETO: ZsiControlerMei%n"
                + "PACOTE: Controller / CLASSE: ControllerEmpresa%n"
                + "METODO: alterarEmpresaiInsereIP(host)%n"
                + "DAO: update.alterarEmpresa_InsereIpNuvem(host); %n";

        PersistenceCloud update = new PersistenceCloud();
        try {
            update.alterarEmpresa_InsereIpNuvem(host);
        } catch (Exception ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
    }

    public void atualizaTodosHost(Host_Nuvem host) {
        String identificacaoMetodo = "PROJETO: ZsiControlerMei%n"
                + "PACOTE: Controller / CLASSE: ControllerEmpresa%n"
                + "METODO: atualizaTodosHost(host)%n"
                + "DAO: update.alterarEmpresa_atuliza_todos_host(host) %n";

        PersistenceCloud update = new PersistenceCloud();
        try {
            update.alterarEmpresa_atuliza_todos_host(host);
        } catch (Exception ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
    }

}
