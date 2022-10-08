/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Security;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Model.Security.InfoSis.enderecoMAC;
import static Model.Security.TransformaNumero.gerarMD5;
import javax.swing.JOptionPane;

/**
 *
 * @author Izaias
 */
public class Propriedades {
    public static String[] getCaminhoConexaoPropiedades(boolean conexaoMista) {

        Properties prop = new Properties();

        try {
            String OS = getOS();
            FileInputStream file = new FileInputStream(OS + File.separator + "Util" + File.separator + "properties" + File.separator + "propiedades.properties");

            prop.load(file);

            String ident = prop.getProperty("ident_database");
            String login = prop.getProperty("login");
            String tipoConexao = prop.getProperty("tipoConexao");
            String ip = prop.getProperty("url");
            String url;
            String urlNuvem = "jdbc:mysql://sql172.main-hosting.eu:";
            String urlLocal = "jdbc:mysql://:3306/";
            String urlRede = "jdbc:mysql://" + ip + ":3306/";

            if (tipoConexao.equals("0")) {
                url = urlNuvem;
                login = ident;
            } else {

                if (tipoConexao.equals("1")) {
                    url = urlLocal;
                } else {//2

                    if (tipoConexao.equals("2")) {
                        url = urlRede;
                    } else {
                        if (tipoConexao.equals("3")) {
                            if (conexaoMista == true) {
                                url = urlLocal;
                                login = "root";
                            } else {
                                url = urlRede;     
                            }
                        } else {
                            url = urlLocal; 
                            login = "root";
                        }
                    }
                }
            }

            String[] lista = new String[4];

            lista[0] = ident;
            lista[1] = login;
            lista[2] = tipoConexao;
            lista[3] = url;
            return lista;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Propriedades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Propriedades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void suesCompanySingleton() {

        try {
            String OS = getOS();
            Properties prop = new Properties();
            FileInputStream file = new FileInputStream(OS + File.separator + "Util" + File.separator + "properties" + File.separator + "propiedades.properties");

            prop.load(file);

            SingletonCompany.Company().setLicenca(prop.getProperty("licenca"));
            SingletonCompany.instancia.setData_aviso(prop.getProperty("dataAviso"));
            SingletonCompany.instancia.setData_vencimento(prop.getProperty("dataVencimentos"));
            SingletonCompany.instancia.setQtd_login(Integer.parseInt(prop.getProperty("qtd_login")));
            SingletonCompany.instancia.setRazao(prop.getProperty("razao_social"));
            SingletonCompany.instancia.setFantasia(prop.getProperty("fantasia"));
            SingletonCompany.instancia.setRua(prop.getProperty("rua"));
            SingletonCompany.instancia.setBairro(prop.getProperty("bairro"));
            SingletonCompany.instancia.setNumero(prop.getProperty("numero"));
            SingletonCompany.instancia.setFone1(prop.getProperty("fone1"));
            SingletonCompany.instancia.setFone2(prop.getProperty("fone2"));
            SingletonCompany.instancia.setCnpj(prop.getProperty("cnpj"));
            SingletonCompany.instancia.setInscricao(prop.getProperty("inscricao"));
            SingletonCompany.instancia.setIdent_database(prop.getProperty("ident_database"));
            SingletonCompany.instancia.setLogin(prop.getProperty("login"));
            SingletonCompany.instancia.setTipoConexao(prop.getProperty("tipoConexao"));
            SingletonCompany.instancia.setUrl(prop.getProperty("url"));
            SingletonCompany.instancia.setInscricao(prop.getProperty("inscricao"));
            SingletonCompany.instancia.setCep(prop.getProperty("cep"));
            SingletonCompany.instancia.setE_mail(prop.getProperty("email"));
            SingletonCompany.instancia.setEstado(prop.getProperty("estado"));
            SingletonCompany.instancia.setCidade(prop.getProperty("cidade"));
            SingletonCompany.instancia.setComplemento(prop.getProperty("complemento"));
            SingletonCompany.instancia.setId(Integer.parseInt(prop.getProperty("id")));
            SingletonCompany.instancia.setMeuIp(prop.getProperty("meuIp"));
            SingletonCompany.instancia.setTipoLicenca(Integer.parseInt(prop.getProperty("tipoLicenca")));
            SingletonCompany.instancia.setChave(prop.getProperty("chave"));
            SingletonCompany.instancia.setStatus(prop.getProperty("status"));
            SingletonCompany.instancia.setCaixa(prop.getProperty("caixa"));
            SingletonCompany.instancia.setOff(prop.getProperty("off"));
            SingletonCompany.instancia.setRede_social(prop.getProperty("redesocial"));
            SingletonCompany.instancia.setImpressoraCozinha_ip(prop.getProperty("impressoraCozinha_ip"));
            SingletonCompany.instancia.setImpressoraPrincipal_ip(prop.getProperty("impressoraPrincipal_ip"));
            SingletonCompany.instancia.setTipoImpressora_cozinha(prop.getProperty("tipoImpressora_cozinha"));
            SingletonCompany.instancia.setTipoImpressora_principal(prop.getProperty("tipoImpressora_principal"));
            SingletonCompany.instancia.setGuilhotinaCozinha(prop.getProperty("guilhotinaCozinha"));
            SingletonCompany.instancia.setGuilhotinaPrincipal(prop.getProperty("guilhotinaPrincipal"));
            SingletonCompany.instancia.setQtdCaixa(Integer.parseInt(prop.getProperty("qtdCaixa")));
            SingletonCompany.instancia.setApp(prop.getProperty("app"));
            SingletonCompany.instancia.setQrcode(prop.getProperty("qrcode"));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Propriedades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Propriedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void atualizaProperty(CompanyEntity empersa) {

        try {
            Properties props = new Properties();

            props.setProperty("id", convSTnullVazio(String.valueOf(SingletonCompany.instancia.getId())));
            props.setProperty("licenca", convSTnullVazio(SingletonCompany.instancia.getLicenca()));
            props.setProperty("dataAviso", convSTnullVazio(SingletonCompany.instancia.getData_aviso()));
            props.setProperty("dataVencimentos", convSTnullVazio(SingletonCompany.instancia.getData_vencimento()));
            props.setProperty("qtd_login", convSTnullVazio(String.valueOf(SingletonCompany.instancia.getQtd_login())));
            props.setProperty("razao_social", convSTnullVazio(SingletonCompany.instancia.getRazao()));
            props.setProperty("fantasia", convSTnullVazio(SingletonCompany.instancia.getFantasia()));
            props.setProperty("rua", convSTnullVazio(SingletonCompany.instancia.getRua()));
            props.setProperty("numero", convSTnullVazio(SingletonCompany.instancia.getNumero()));
            props.setProperty("bairro", convSTnullVazio(SingletonCompany.instancia.getBairro()));
            props.setProperty("cidade", convSTnullVazio(SingletonCompany.instancia.getCidade()));
            props.setProperty("estado", convSTnullVazio(SingletonCompany.instancia.getEstado()));
            props.setProperty("fone1", convSTnullVazio(SingletonCompany.instancia.getFone1()));
            props.setProperty("fone2", convSTnullVazio(SingletonCompany.instancia.getFone2()));
            props.setProperty("cep", convSTnullVazio(SingletonCompany.instancia.getCep()));
            props.setProperty("cnpj", convSTnullVazio(SingletonCompany.instancia.getCnpj()));
            props.setProperty("inscricao", convSTnullVazio(SingletonCompany.instancia.getInscricao()));
            props.setProperty("complemento", convSTnullVazio(SingletonCompany.instancia.getComplemento()));
            props.setProperty("email", convSTnullVazio(SingletonCompany.instancia.getE_mail()));
            props.setProperty("ident_database", convSTnullVazio(SingletonCompany.instancia.getIdent_database()));
            props.setProperty("login", convSTnullVazio(SingletonCompany.instancia.getLogin()));
            props.setProperty("tipoConexao", convSTnullVazio(SingletonCompany.instancia.getTipoConexao()));
            props.setProperty("url", convSTnullVazio(SingletonCompany.instancia.getUrl()));
            props.setProperty("caixa", convSTnullVazio(SingletonCompany.instancia.getCaixa()));
            props.setProperty("tipoLicenca", convSTnullVazio(String.valueOf(SingletonCompany.instancia.getTipoLicenca())));
            props.setProperty("chave", convSTnullVazio(SingletonCompany.instancia.getChave()));
            props.setProperty("status", convSTnullVazio(SingletonCompany.instancia.getStatus()));
            props.setProperty("off", convSTnullVazio(SingletonCompany.instancia.getOff()));
            props.setProperty("redesocial", convSTnullVazio(SingletonCompany.instancia.getRede_social()));

            props.setProperty("impressoraCozinha_ip", convSTnullVazio(empersa.getImpressoraCozinha_ip() != null 
                    ? empersa.getImpressoraCozinha_ip() 
                    : SingletonCompany.instancia.getImpressoraCozinha_ip()));
            props.setProperty("impressoraPrincipal_ip", convSTnullVazio(empersa.getImpressoraPrincipal_ip() != null
                    ? empersa.getImpressoraPrincipal_ip() 
                    : SingletonCompany.instancia.getImpressoraPrincipal_ip()));
            props.setProperty("tipoImpressora_cozinha", convSTnullVazio(empersa.getTipoImpressora_cozinha() != null 
                    ? empersa.getTipoImpressora_cozinha() 
                    : SingletonCompany.instancia.getTipoImpressora_cozinha()));
            props.setProperty("tipoImpressora_principal", convSTnullVazio(empersa.getTipoImpressora_principal() != null 
                    ? empersa.getTipoImpressora_principal() 
                    : SingletonCompany.instancia.getImpressoraPrincipal_ip()));
            props.setProperty("guilhotinaCozinha", convSTnullVazio(String.valueOf(empersa.getGuilhotinaCozinha() != null 
                    ? empersa.getGuilhotinaCozinha() 
                    : SingletonCompany.instancia.getGuilhotinaCozinha())));
            props.setProperty("guilhotinaPrincipal", convSTnullVazio(String.valueOf(empersa.getGuilhotinaPrincipal() != null 
                    ? empersa.getGuilhotinaPrincipal() 
                    : SingletonCompany.instancia.getGuilhotinaPrincipal())));
            props.setProperty("qtdCaixa", convSTnullVazio(String.valueOf(empersa.getQtdCaixa() == 0 
                    ? empersa.getQtdCaixa() 
                    : SingletonCompany.instancia.getQtdCaixa())));
            props.setProperty("app", convSTnullVazio(empersa.getApp() != null 
                    ? empersa.getApp() 
                    : SingletonCompany.instancia.getApp()));
            props.setProperty("qrcode", convSTnullVazio(empersa.getQrcode() != null 
                    ? empersa.getQrcode() 
                    : SingletonCompany.instancia.getQrcode()));

            String OS = getOS();//+ File.separator +
            FileOutputStream file = new FileOutputStream(
                    OS + File.separator + "Util" + File.separator + "properties" + File.separator + "propiedades.properties");
            props.store(file, "EMPRESA");

            file.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void setEmpresaPropiedades(CompanyEntity empersa) {

        try {
            Properties props = new Properties();

            String licenca = convSTnullVazio(empersa.getLicenca());
            String dataAviso = convSTnullVazio(empersa.getData_aviso());
            String dataVencimentos = convSTnullVazio(empersa.getData_vencimento());
            int qtd_login = empersa.getQtd_login();
            String razao_social = convSTnullVazio(empersa.getRazao());
            String fantasia = convSTnullVazio(empersa.getFantasia());
            String rua = convSTnullVazio(empersa.getRua());
            String numero = convSTnullVazio(empersa.getNumero());
            String bairro = convSTnullVazio(empersa.getBairro());
            String cidade = convSTnullVazio(empersa.getCidade());
            String estado = convSTnullVazio(empersa.getEstado());
            String fone1 = convSTnullVazio(empersa.getFone1());
            String fone2 = convSTnullVazio(empersa.getFone2());
            String cep = convSTnullVazio(empersa.getCep());
            String cnpj = convSTnullVazio(empersa.getCnpj());
            String inscricao = convSTnullVazio(empersa.getInscricao());
            String complemento = convSTnullVazio(empersa.getComplemento());
            String email = convSTnullVazio(empersa.getE_mail());
            String ident_database = convSTnullVazio(empersa.getIdent_database());
            String login = convSTnullVazio(empersa.getLogin());
            String tipoConexao = convSTnullVazio(empersa.getTipoConexao());
            String url = convSTnullVazio(empersa.getUrl());
            String meuIp = convSTnullVazio(empersa.getMeuIp());
            String caixa = convSTnullVazio(empersa.getCaixa());
            String impressoraCozinha_ip = convSTnullVazio(empersa.getImpressoraCozinha_ip());
            String impressoraPrincipal_ip = convSTnullVazio(empersa.getImpressoraPrincipal_ip());
            String tipoImpressora_cozinha = convSTnullVazio(empersa.getTipoImpressora_cozinha());
            String tipoImpressora_principal = convSTnullVazio(empersa.getTipoImpressora_principal());
            String guilhotinaCozinha = convSTnullVazio(empersa.getGuilhotinaCozinha());
            String guilhotinaPrincipal = convSTnullVazio(empersa.getGuilhotinaPrincipal());
            String app = convSTnullVazio(empersa.getApp());
            String qrcode = convSTnullVazio(empersa.getQrcode());

            if (impressoraCozinha_ip.equalsIgnoreCase("")) {
                impressoraCozinha_ip = SingletonCompany.instancia.getImpressoraCozinha_ip();    
            }

            if (impressoraPrincipal_ip.equalsIgnoreCase("")) {
                impressoraPrincipal_ip = SingletonCompany.instancia.getImpressoraPrincipal_ip();
            }

            if (tipoImpressora_cozinha.equalsIgnoreCase("")) {
                tipoImpressora_cozinha = SingletonCompany.instancia.getTipoImpressora_cozinha();
            }

            if (tipoImpressora_principal.equalsIgnoreCase("")) {
                tipoImpressora_principal = SingletonCompany.instancia.getTipoImpressora_principal();
            }

            if (guilhotinaCozinha.equalsIgnoreCase("")) {
                guilhotinaCozinha = String.valueOf(SingletonCompany.instancia.getGuilhotinaCozinha());
            }

            if (guilhotinaPrincipal.equalsIgnoreCase("")) {
                guilhotinaPrincipal = String.valueOf(SingletonCompany.instancia.getGuilhotinaPrincipal());
            }

            if (caixa == null) {
                caixa = "01";
            }

            if (caixa.equals("")) {
                caixa = "01";
            }

            int tipoLicenca = empersa.getTipoLicenca();
            String chave = geraMDHost();
            String status = empersa.getStatus();

            props.setProperty("id", convSTnullVazio(String.valueOf(empersa.getId())));
            props.setProperty("licenca", convSTnullVazio(licenca));
            props.setProperty("dataAviso", convSTnullVazio(dataAviso));
            props.setProperty("dataVencimentos", convSTnullVazio(dataVencimentos));
            props.setProperty("qtd_login", convSTnullVazio(String.valueOf(qtd_login)));
            props.setProperty("razao_social", convSTnullVazio(razao_social));
            props.setProperty("fantasia", convSTnullVazio(fantasia));
            props.setProperty("rua", convSTnullVazio(rua));
            props.setProperty("numero", convSTnullVazio(numero));
            props.setProperty("bairro", convSTnullVazio(bairro));
            props.setProperty("cidade", convSTnullVazio(cidade));
            props.setProperty("estado", convSTnullVazio(estado));
            props.setProperty("fone1", convSTnullVazio(fone1));
            props.setProperty("fone2", convSTnullVazio(fone2));
            props.setProperty("cep", convSTnullVazio(cep));
            props.setProperty("cnpj", convSTnullVazio(cnpj));
            props.setProperty("inscricao", convSTnullVazio(inscricao));
            props.setProperty("complemento", convSTnullVazio(complemento));
            props.setProperty("email", convSTnullVazio(email));
            props.setProperty("ident_database", convSTnullVazio(ident_database));
            props.setProperty("login", convSTnullVazio(login));
            props.setProperty("tipoConexao", convSTnullVazio(tipoConexao));
            props.setProperty("url", convSTnullVazio(url));
            props.setProperty("caixa", convSTnullVazio(caixa));
            props.setProperty("tipoLicenca", convSTnullVazio(String.valueOf(tipoLicenca)));
            props.setProperty("chave", convSTnullVazio(chave));
            props.setProperty("status", convSTnullVazio(status));
            props.setProperty("off", convSTnullVazio(empersa.getOff()));
            props.setProperty("email", convSTnullVazio(email));
            props.setProperty("redesocial", convSTnullVazio(empersa.getRede_social()));
            props.setProperty("impressoraCozinha_ip", convSTnullVazio(impressoraCozinha_ip));
            props.setProperty("impressoraPrincipal_ip", convSTnullVazio(impressoraPrincipal_ip));
            props.setProperty("tipoImpressora_cozinha", convSTnullVazio(tipoImpressora_cozinha));
            props.setProperty("tipoImpressora_principal", convSTnullVazio(tipoImpressora_principal));
            props.setProperty("guilhotinaCozinha", convSTnullVazio(String.valueOf(guilhotinaCozinha)));
            props.setProperty("guilhotinaPrincipal", convSTnullVazio(String.valueOf(guilhotinaPrincipal)));
            props.setProperty("qtdCaixa", convSTnullVazio(String.valueOf(empersa.getQtdCaixa())));
            props.setProperty("app", convSTnullVazio(app));
            props.setProperty("qrcode", qrcode);

            String OS = getOS();//+ File.separator +
            FileOutputStream file = new FileOutputStream(
                    OS + File.separator + "Util" + File.separator + "properties" + File.separator + "propiedades.properties");
            props.store(file, "EMPRESA");

            file.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static String geraMDHost() {
        String[] enderecosMac = new String[4];
        enderecosMac = enderecoMAC();
        String MAC = "";
        String licenca = "";
        boolean valido = false;

        if (!enderecosMac[0].equals("")) {

            if (enderecosMac[0] != null) {
                MAC = enderecosMac[0];
                valido = true;
            }
        }

        if (valido == false) {

            if (!enderecosMac[1].equals("")) {

                if (enderecosMac[1] != null) {
                    MAC = enderecosMac[1];
                    valido = true;
                }
            }
        }

        if (valido == false) {

            if (!enderecosMac[2].equals("")) {

                if (enderecosMac[2] != null) {
                    MAC = enderecosMac[2];
                    valido = true;
                }
            }
        }

        if (valido == false) {

            if (!enderecosMac[3].equals("")) {

                if (enderecosMac[3] != null) {
                    MAC = enderecosMac[3];
                    valido = true;
                }
            }
        }

        if (valido == true) {
            licenca = gerarMD5(MAC);
        } else {
            licenca = MAC;
        }
        return licenca;
    }

    public static void exibirPropiedades() {

        try {
            String OS = getOS();
            Properties prop = new Properties();
            FileInputStream file = new FileInputStream(OS + File.separator + "Util" + File.separator + "properties" + File.separator + "propiedades.properties");

            prop.load(file);

            System.out.println("Login = " + prop.getProperty("login"));
            System.out.println("Host = " + prop.getProperty("host"));
            System.out.println("Password = " + prop.getProperty("password"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Propriedades.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Propriedades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setProp() {

        try {
            String OS = getOS();
            Properties props = new Properties();

            props.setProperty("login", "izs");
            props.setProperty("host", "local");
            props.setProperty("password", "555");

            FileOutputStream file = new FileOutputStream(
                    OS + File.separator + "Util" + File.separator + "properties" + File.separator + "propiedades.properties");
            props.store(file, "PROPIEDADES DO SISTEMA");

            file.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    } 

    public static String getOS() {
        String os = System.getProperty("os.name");
        String Sistema = "C:";
        String user = "izaias";

        try {

            if (os.startsWith("Windows")) {
                Sistema = "C:";
            } else if (os.startsWith("Linux")) {
                Sistema = "//home" + File.separator + user;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "O.S não identificado");
        }

        return Sistema;
    }

    private static String convSTnullVazio(String valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
