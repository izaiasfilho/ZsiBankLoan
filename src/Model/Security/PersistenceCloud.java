/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Security;

import Model.Entities.Host_Nuvem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Izaias
 */
public class PersistenceCloud {
    
    private final String SQL_SELECT_EMPRESA_CNPJ = "select id, razao, fantasia, cnpj, inscricao, rua, numero, cep,complemento, bairro, cidade, estado, fone1, fone2, e_mail, banco, qtd_login, data_aviso, data_vencimento,status, atividade, login, tipoconexao, tipo_pacote, url, ip, bloqueio_auto, tipoLicenca, rede_social, off from empresa where cnpj=?";

    public CompanyEntity selectEmpresasCNPJ(CompanyEntity empresa) {
        String identificacaoMetodo = "PROJETO: ZSICONTROLEMEI %n "
                + "PACOTE: Dao / CLASSE: SelectDaoEmpresa_PainelControle%n"
                + "METODO: selectEmpresasCNPJ(empresa)%n"
                + "TABELA: select id, razao, fantasia, cnpj, inscricao, rua, numero, cep, complemento, bairro, cidade,"
                + " estado, fone1, fone2, e_mail, banco, qtd_login, data_aviso, data_vencimento, status, atividade, login,"
                + " tipoconexao, tipo_pacote, url, ip, bloqueio_auto, tipoLicenca, rede_social, off from empresa%n";

        //selectEmpresasCNPJ()
        ConectionCloud co = new ConectionCloud();

        PreparedStatement preparedStatement = null;

        if (ConectionCloud.verificaConxaodEmpresa() == true) {
            ConectionCloud.closebdEmpresa();
        }

        if (co.isNuvem_on() == true) {

            try {
                preparedStatement = ConectionCloud.conectabddEmpresa().prepareStatement(SQL_SELECT_EMPRESA_CNPJ);
                preparedStatement.setString(1, empresa.getCnpj());

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    CompanyEntity empre = new CompanyEntity();

                    empre.setId(rs.getInt(1));
                    empre.setRazao(rs.getString(2));
                    empre.setFantasia(rs.getString(3));
                    empre.setCnpj(rs.getString(4));
                    empre.setInscricao(rs.getString(5));
                    empre.setRua(rs.getString(6));
                    empre.setNumero(rs.getString(7));
                    empre.setCep(rs.getString(8));
                    empre.setComplemento(rs.getString(9));
                    empre.setBairro(rs.getString(10));
                    empre.setCidade(rs.getString(11));
                    empre.setEstado(rs.getString(12));
                    empre.setFone1(rs.getString(13));
                    empre.setFone2(rs.getString(14));
                    empre.setE_mail(rs.getString(15));
                    empre.setBanco(rs.getString(16));
                    empre.setQtd_login(rs.getInt(17));
                    empre.setData_aviso(rs.getString(18));
                    empre.setData_vencimento(rs.getString(19));
                    empre.setStatus(rs.getString(20));
                    empre.setAtividade(rs.getString(21));
                    empre.setLogin(rs.getString(22));//login,
                    empre.setTipoConexao(rs.getString(23));//tipoconexao,tipo_pacote,url
                    empre.setTipo_pacote(rs.getString(24));
                    empre.setUrl(rs.getString(25));
                    empre.setMeuIp(rs.getString(26));
                    empre.setBloqueio_auto(rs.getInt(27));
                    empre.setTipoLicenca(rs.getInt(28));
                    empre.setRede_social(rs.getString(29));
                    empre.setOff(rs.getString(30));

                    return empre;
                }
            } catch (SQLException ex) {
                LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
                return null;
            } finally {
                ConectionCloud.closebdEmpresa();
            }
        }
        return null;
    }

     private final String SQL_SELECT_HOST_EMPRESA_CNPJ = "select id_host, id_empresa, host, CPU, HD, mac "
            + "from host where id_empresa=?";

    public ArrayList<Host_Nuvem> selectHostEmpresasCNPJ(CompanyEntity empresa) {
        String identificacaoMetodo = "PROJETO: ZSICONTROLEMEI %n "
                + "PACOTE: Dao / CLASSE: SelectDaoEmpresa_PainelControle%n"
                + "METODO: selectHostEmpresasCNPJ(empresa)%n"
                + "TABELA: select id_host, id_empresa, host, CPU, HD, mac from host %n";

        ConectionCloud co = new ConectionCloud();
        PreparedStatement preparedStatement = null;
        ArrayList<Host_Nuvem> arrayHost = new ArrayList();

        if (ConectionCloud.verificaConxaodEmpresa() == true) {
            ConectionCloud.closebdEmpresa();
        }

        if (co.isNuvem_on() == true) {

            try {
                preparedStatement = ConectionCloud.conectabddEmpresa().prepareStatement(SQL_SELECT_HOST_EMPRESA_CNPJ);
                preparedStatement.setInt(1, empresa.getId());

                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    Host_Nuvem host = new Host_Nuvem();

                    host.setId_host(rs.getInt(1));
                    host.setId_empresa(rs.getInt(2));
                    host.setHost(rs.getString(3));
                    host.setCPU(rs.getString(4));
                    host.setHD(rs.getString(5));
                    host.setMac(rs.getString(6));

                    arrayHost.add(host);
                }
                return arrayHost;
            } catch (SQLException ex) {
                LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
                return null;
            } finally {
                ConectionCloud.closebdEmpresa();
            }
        }
        return null;
    }

    private final String SQL_EMPRESA_ALTERAR_INSERE_ULTIMO_ACESSO = "UPDATE empresa set ultimoAcesso=? where id = ?";

    public boolean alterarEmpresa_InsereULTIMO_ACESSO(CompanyEntity empresa) throws SQLException {
        String identificacaoMetodo = "PROJETO: ZSICONTROLEMEI %n "
                + "PACOTE: Dao / CLASSE: UpdateDaoEmpresa_PainelControle %n"
                + "METODO: alterarEmpresa_InsereULTIMO_ACESSO(empresa) %n"
                + "TABELA: UPDATE empresa set ultimoAcesso=? %n";

        PreparedStatement preparedStatement = null;

        try {

            if (ConectionCloud.verificaConxaodEmpresa() == true) {
                ConectionCloud.closebdEmpresa();
            }

            preparedStatement = ConectionCloud.conectabddEmpresa().prepareStatement(SQL_EMPRESA_ALTERAR_INSERE_ULTIMO_ACESSO);
            preparedStatement.setString(1, empresa.getUltimoAcesso());
            preparedStatement.setInt(2, empresa.getId());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        } finally {
            ConectionCloud.closebdEmpresa();
        }
        return false;
    }

    
     private final String SQL_EMPRESA_ALTERAR_INSERE_IP = "INSERT INTO host (id_empresa,host,CPU,HD,mac) VALUES (?,?,?,?,?)";

    public boolean alterarEmpresa_InsereIpNuvem(Host_Nuvem hostNuvem) throws SQLException {
        String identificacaoMetodo = "PROJETO: ZSICONTROLEMEI %n "
                + "PACOTE: Dao / CLASSE: UpdateDaoEmpresa_PainelControle%n"
                + "METODO: AlterarEmpresa_InsereIpNuvem(hostNuvem)%n"
                + "TABELA: INSERT INTO host (id_empresa,host,CPU,HD,mac) %n";

        PreparedStatement preparedStatement = null;

        try {

            if (ConectionCloud.verificaConxaodEmpresa() == true) {
                ConectionCloud.closebdEmpresa();
            }

            preparedStatement = ConectionCloud.conectabddEmpresa().prepareStatement(SQL_EMPRESA_ALTERAR_INSERE_IP);
            preparedStatement.setInt(1, hostNuvem.getId_empresa());
            preparedStatement.setString(2, hostNuvem.getHost());
            preparedStatement.setString(3, hostNuvem.getCPU());
            preparedStatement.setString(4, hostNuvem.getHD());
            preparedStatement.setString(5, hostNuvem.getMac());

            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        } finally {
            ConectionCloud.closebdEmpresa();
        }
        return false;
    }

     private final String SQL_EMPRESA_ATUALIZA_TODOS_HOST = "UPDATE host set host = ?,CPU = ?, HD = ?, mac = ? where id_host = ?";

    public boolean alterarEmpresa_atuliza_todos_host(Host_Nuvem host) throws SQLException {
        String identificacaoMetodo = "PROJETO: ZSICONTROLEMEI %n "
                + "PACOTE: Dao / CLASSE: UpdateDaoEmpresa_PainelControle%n"
                + "METODO: alterarEmpresa_atuliza_todos_host(host) %n"
                + "Tabela: UPDATE host set host = ?,CPU = ?, HD = ?, mac = ? %n";

        PreparedStatement preparedStatement = null;

        try {

            if (ConectionCloud.verificaConxaodEmpresa() == true) {
                ConectionCloud.closebdEmpresa();
            }

            preparedStatement = ConectionCloud.conectabddEmpresa().prepareStatement(SQL_EMPRESA_ATUALIZA_TODOS_HOST);
            preparedStatement.setString(1, host.getHost());
            preparedStatement.setString(2, host.getCPU());
            preparedStatement.setString(3, host.getHD());
            preparedStatement.setString(4, host.getMac());
            preparedStatement.setInt(5, host.getId_host());
            preparedStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            LerTXT.log(identificacaoMetodo + "Erro: " + ex.getMessage());
        } finally {
            ConectionCloud.closebdEmpresa();
        }
        return false;
    }

}
