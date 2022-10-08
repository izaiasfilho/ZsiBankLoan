/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Security;

/**
 *
 * @author Izaias
 */
public class CompanyEntity {
  
    private int id;
    private int qtd_login;
    private int bloqueio_auto;
    private int tipoLicenca;

    private String razao;
    private String fantasia;
    private String cnpj;
    private String inscricao;
    private String rua;
    private String numero;
    private String cep;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String fone1;
    private String fone2;
    private String e_mail;
    private String tipo_pacote;
    private String data_aviso;
    private String data_vencimento;
    private String licenca;
    private String ident_database;
    private String login;
    private String tipoConexao;
    private String url;
    private String banco;
    private String status;
    private String atividade;
    private String vendedor;
    private String meuIp;
    private String chave;
    private String ultimoAcesso;
    private String caixa;
    private String off;
    private String rede_social;
    private String versao;
    private String qrcode;
    // impressora na licença
    private String guilhotinaPrincipal;
    private String guilhotinaCozinha;
    private String impressoraPrincipal_ip;
    private String impressoraCozinha_ip;
    private String TipoImpressora_cozinha;
    private String TipoImpressora_principal;
    private int qtdCaixa;
    // aplicativo
    private String app;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public int getQtdCaixa() {
        return qtdCaixa;
    }

    public void setQtdCaixa(int qtdCaixa) {
        this.qtdCaixa = qtdCaixa;
    }

    public String getGuilhotinaPrincipal() {
        return guilhotinaPrincipal;
    }

    public void setGuilhotinaPrincipal(String guilhotinaPrincipal) {
        this.guilhotinaPrincipal = guilhotinaPrincipal;
    }

    public String getGuilhotinaCozinha() {
        return guilhotinaCozinha;
    }

    public void setGuilhotinaCozinha(String guilhotinaCozinha) {
        this.guilhotinaCozinha = guilhotinaCozinha;
    }

    public String getImpressoraPrincipal_ip() {
        return impressoraPrincipal_ip;
    }

    public void setImpressoraPrincipal_ip(String impressoraPrincipal_ip) {
        this.impressoraPrincipal_ip = impressoraPrincipal_ip;
    }

    public String getImpressoraCozinha_ip() {
        return impressoraCozinha_ip;
    }

    public void setImpressoraCozinha_ip(String impressoraCozinha_ip) {
        this.impressoraCozinha_ip = impressoraCozinha_ip;
    }

    public String getTipoImpressora_cozinha() {
        return TipoImpressora_cozinha;
    }

    public void setTipoImpressora_cozinha(String TipoImpressora_cozinha) {
        this.TipoImpressora_cozinha = TipoImpressora_cozinha;
    }

    public String getTipoImpressora_principal() {
        return TipoImpressora_principal;
    }

    public void setTipoImpressora_principal(String TipoImpressora_principal) {
        this.TipoImpressora_principal = TipoImpressora_principal;
    }

    public String getRede_social() {
        return rede_social;
    }

    public void setRede_social(String rede_social) {
        this.rede_social = rede_social;
    }

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    public String getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(String ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public int getTipoLicenca() {
        return tipoLicenca;
    }

    public void setTipoLicenca(int tipoLicenca) {
        this.tipoLicenca = tipoLicenca;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public int getBloqueio_auto() {
        return bloqueio_auto;
    }

    public void setBloqueio_auto(int bloqueio_auto) {
        this.bloqueio_auto = bloqueio_auto;
    }

    public String getMeuIp() {
        return meuIp;
    }

    public void setMeuIp(String meuIp) {
        this.meuIp = meuIp;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAtividade() {
        return atividade;
    }

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    public String getTipoConexao() {
        return tipoConexao;
    }

    public void setTipoConexao(String tipoConexao) {
        this.tipoConexao = tipoConexao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdent_database() {
        return ident_database;
    }

    public void setIdent_database(String ident_database) {
        this.ident_database = ident_database;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricao() {
        return inscricao;
    }

    public void setInscricao(String inscricao) {
        this.inscricao = inscricao;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFone1() {
        return fone1;
    }

    public void setFone1(String fone1) {
        this.fone1 = fone1;
    }

    public String getFone2() {
        return fone2;
    }

    public void setFone2(String fone2) {
        this.fone2 = fone2;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getTipo_pacote() {
        return tipo_pacote;
    }

    public void setTipo_pacote(String tipo_pacote) {
        this.tipo_pacote = tipo_pacote;
    }

    public int getQtd_login() {
        return qtd_login;
    }

    public void setQtd_login(int qtd_login) {
        this.qtd_login = qtd_login;
    }

    public String getData_aviso() {
        return data_aviso;
    }

    public void setData_aviso(String data_aviso) {
        this.data_aviso = data_aviso;
    }

    public String getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(String data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }  
}
