/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Security;

import Model.Entities.Host_Nuvem;
import static Model.Security.InfoSis.enderecoMAC;
import static Model.Security.Propriedades.suesCompanySingleton;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

        if (licenca_gerada.equals(SingletonCompany.instancia.getLicenca())) {
            checkExpiration();
        } else {
            lowLicense(true, null);
        }
    }

    public void checkExpiration() {
        Calendario_Aux data = new Calendario_Aux();
        if (SingletonCompany.instancia.getData_vencimento() != null) {
            if (diasVencimento()) {//nao esta vencido
                vencimentoFalse();
            } else {
                revalidaLicenca();
            }
        }
    }

    public boolean diasVencimento() {
        Calendario_Aux data = new Calendario_Aux();
        return data.verificaDiasVencimento(SingletonCompany.instancia.getData_vencimento()) >= 0;
    }

    public void revalidaLicenca() {
        lowLicense(true, null); //vencido
        if (diasVencimento()) {
            vencimentoFalse();
        } else {
            JOptionPane.showMessageDialog(null, "QR code vencido!");
        }
    }

    public void vencimentoFalse() {
        int diasAviso = verificaProximidadeAvisoVencimento();

        if (diasAviso <= 10) {
            if (diasAviso > 2) {
                lowLicense(true, null);
            }
        }
        int diasVen = verificaProximidadeVencimento();

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
        // SelectDaoEmpresa_PainelControle select = new SelectDaoEmpresa_PainelControle();

        //###########################################################################
        boolean hostValido = false;

        String[] listaEndLocal = new String[4];

        listaEndLocal = enderecoMAC();
        //String meuIp = "";; //verifica o mac da maquina

        if (listaEndLocal != null) {

            if (auto == true) {
                empresaSolicitada.setCnpj(SingletonCompany.instancia.getCnpj());
            } else {
                String cnpjInfo = JOptionPane.showInputDialog("Informe o CNPJ ");

                if (cnpjInfo.length() > 10) {
                    empresaSolicitada.setCnpj(cnpjInfo);
                } else {
                    empresaSolicitada.setCnpj(SingletonCompany.instancia.getCnpj());
                }
            }

            empresaRecebida = null; //--> select.selectEmpresasCNPJ(empresaSolicitada);//

            if (empresaRecebida != null) {//verifica se na nuvem esta cadastrado
                String rede = empresaRecebida.getRede_social();
                listaHoost = null;// -->select.selectHostEmpresasCNPJ(empresaRecebida);//lista todos os host da empresa  
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

                            // ClassEmail Email = new ClassEmail();
                            String senha = "mnd02666";
                        }
                        //  Email.EmailAnexo("Ouve uma alteração de Host na empresa: " + empresaRecebida.getFantasia(), "smtp.gmail.com", "465", empresaRecebida.getFantasia(), "izs.af@hotmail.com", "BLOQUEIO DA EMPRESA : " + empresaRecebida.getFantasia(), "izs.motog@gmail.com", senha);
//                        } else {//se nao, envia um email de alerta
//                            ClassEmail Email = new ClassEmail();
//                            String senha = "mnd02666";
//                            Email.EmailAnexo("Ouve uma alteração de Host na empresa: " + empresaRecebida.getFantasia(), "smtp.gmail.com", "465", empresaRecebida.getFantasia(), "izs.af@hotmail.com", "alteracao de ip da empresa: " + empresaRecebida.getFantasia(), "izs.motog@gmail.com", senha);
//                        }
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
        // -->UpdateDaoEmpresa_PainelControle update = new UpdateDaoEmpresa_PainelControle();

        try {
            // open
            empresa.setUltimoAcesso(data.getdate());
            empresa.setId(SingletonCompany.instancia.getId());

            // --> update.alterarEmpresa_InsereULTIMO_ACESSO(empresa);
            //fecha
        } catch (Exception ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
    }

    //-----###############--- SELECT ---###############-----
    public ArrayList<CompanyEntity> EmpresasTodas() {
        String identificacaoMetodo = "PROJETO: ZsiControlerMei%n"
                + "PACOTE: Controller / CLASSE: ControllerEmpresa%n"
                + "METODO: EmpresasTodas()%n"
                + "DAO: arrayEm = selectDao.selectTodasEmpresas() %n";

        // -->SelectDaoEmpresa_PainelControle selectDao = new SelectDaoEmpresa_PainelControle();
        ArrayList<CompanyEntity> arrayEm = new ArrayList();

        try {
            arrayEm = null; // -->selectDao.selectTodasEmpresas();

            if (arrayEm != null) {
                return arrayEm;
            }
        } catch (Exception ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
        return null;
    }

    //---------###---------- CNPJ ---------- ### ----------
    public boolean validaCNPJ(CompanyEntity empresa) {
        String identificacaoMetodo = "PROJETO: ZsiControlerMei%n"
                + "PACOTE: Controller / CLASSE: ControllerEmpresa%n"
                + "METODO: ValidaCNPJ(empresa)%n"
                + "DAO: boolean valida = selectDao.selectValidaCNPJEmpresa(empresa) %n";

        // --> SelectDaoEmpresa_PainelControle selectDao = new SelectDaoEmpresa_PainelControle();
        try {
            boolean valida = false; //  -->selectDao.selectValidaCNPJEmpresa(empresa);

            if (valida == true) {
                return true;//
            }
        } catch (Exception ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
        return false;
    }

    //**********************SEM USO NO LOGIN
    public static CompanyEntity EmpresaCNPJ(CompanyEntity empresa) {
        String identificacaoMetodo = "PROJETO: ZsiControlerMei%n"
                + "PACOTE: Controller / CLASSE: ControllerEmpresa%n"
                + "METODO: EmpresaCNPJ(empresa)%n"
                + "DAO: empr = selectDao.selectEmpresasCNPJ(empresa) %n";

        // -->  SelectDaoEmpresa_PainelControle selectDao = new SelectDaoEmpresa_PainelControle();
        CompanyEntity empr = new CompanyEntity();

        try {
            empr = null; //  -->selectDao.selectEmpresasCNPJ(empresa);

            if (empr != null) {
                SingletonCompany.instancia.setId(empr.getId());
                SingletonCompany.instancia.setRazao(empr.getRazao());
                SingletonCompany.instancia.setFantasia(empr.getFantasia());
                SingletonCompany.instancia.setCnpj(empr.getCnpj());
                SingletonCompany.instancia.setInscricao(empr.getInscricao());
                SingletonCompany.instancia.setRua(empr.getRua());
                SingletonCompany.instancia.setNumero(empr.getNumero());
                SingletonCompany.instancia.setCep(empr.getCep());
                SingletonCompany.instancia.setComplemento(empr.getComplemento());
                SingletonCompany.instancia.setBairro(empr.getBairro());
                SingletonCompany.instancia.setCidade(empr.getCidade());
                SingletonCompany.instancia.setEstado(empr.getEstado());
                SingletonCompany.instancia.setFone1(empr.getFone1());
                SingletonCompany.instancia.setFone2(empr.getFone2());
                SingletonCompany.instancia.setE_mail(empr.getE_mail());
                SingletonCompany.instancia.setTipo_pacote(empr.getTipo_pacote());
                SingletonCompany.instancia.setQtd_login(empr.getQtd_login());
                SingletonCompany.instancia.setData_aviso(empr.getData_aviso());
                SingletonCompany.instancia.setData_vencimento(empr.getData_vencimento());
                SingletonCompany.instancia.setLicenca(empr.getLicenca());
                return empr;
            }
        } catch (Exception ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
        return null;
    }

    public ArrayList<CompanyEntity> LocalizaEmpresaCNPJ(CompanyEntity empresa) {
        String identificacaoMetodo = "PROJETO: ZsiControlerMei%n"
                + "PACOTE: Controller / CLASSE: ControllerEmpresa%n"
                + "METODO: LocalizaEmpresaCNPJ(empresa)%n"
                + "DAO: empr = selectDao.selectEmpresasLOCALIZA(empresa) %n";

        //SelectDaoEmpresa_PainelControle selectDao = new SelectDaoEmpresa_PainelControle();
        ArrayList<CompanyEntity> empr = new ArrayList();

        try {
            empr = null; //--> selectDao.selectEmpresasLOCALIZA(empresa);

            if (empr != null) {
                return empr;
            }
        } catch (Exception ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
        return null;
    }

    public void alterarEmpresaiInsereIP(Host_Nuvem host) {
        String identificacaoMetodo = "PROJETO: ZsiControlerMei%n"
                + "PACOTE: Controller / CLASSE: ControllerEmpresa%n"
                + "METODO: alterarEmpresaiInsereIP(host)%n"
                + "DAO: update.alterarEmpresa_InsereIpNuvem(host); %n";

        // --> InsertDaoEmpresa_PainelControle update = new InsertDaoEmpresa_PainelControle();
        try {
            // open
            // --> update.alterarEmpresa_InsereIpNuvem(host);
            //fecha
        } catch (Exception ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
    }

    public void atualizaTodosHost(Host_Nuvem host) {
        String identificacaoMetodo = "PROJETO: ZsiControlerMei%n"
                + "PACOTE: Controller / CLASSE: ControllerEmpresa%n"
                + "METODO: atualizaTodosHost(host)%n"
                + "DAO: update.alterarEmpresa_atuliza_todos_host(host) %n";

        //--> UpdateDaoEmpresa_PainelControle update = new UpdateDaoEmpresa_PainelControle();
        try {
            // open
            //-->  update.alterarEmpresa_atuliza_todos_host(host);
            //fecha
        } catch (Exception ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        }
    }

}
