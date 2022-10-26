/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.GenreController;
import Controller.InstitutionController;
import Controller.LoanController;
import Controller.LoanMovementController;
import Controller.PhoneController;
import Controller.PlanController;
import Controller.StateController;
import Controller.UserController;
import Model.Entities.AddressEntity;
import Model.Entities.CityEntity;
import Model.Entities.GenreEntity;
import Model.Entities.InstitutionEntity;
import Model.Entities.LoanEntity;
import Model.Entities.LoanMovementEntity;
import Model.Entities.PhoneEntity;
import Model.Entities.PlanEntity;
import Model.Entities.StateEntity;
import Model.Entities.UserEntity;
import Model.Enuns.LoanStatusEnum;
import Model.Enuns.TransactionEnum;
import Model.Utility.Utilities;
import static Model.Utility.Utilities.converteDataDate;
import static Model.Utility.Utilities.converteStringDate;
import Model.Utility.ValidateCpf;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Izaias
 */
public final class MainScreenView extends javax.swing.JDialog {

    /**
     * Creates new form MainScreenView
     *
     * @param parent
     * @param modal
     */
    public MainScreenView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        resolucaoTela();
        // cleanAll();
        setListStateView();
        setListGenreView();
        setListInsitutionView();
        setListPlanView();
        buttonControl();
        botaoNovaProtosta.setVisible(false);
        fillLoanTable();
        setDateCalendar();
    }

    public void setDateCalendar() {
        java.util.Date now;
        now = new java.util.Date();
        jcalenar_data_nascimento.setDate(now);
        campo_dataemissao.setDate(now);
    }

    public LoanEntity getLoanView(UserEntity userEntity) {
        Utilities util = new Utilities();
        LoanEntity loan = new LoanEntity();

        loan.setUsrEntity(userEntity);
        loan.setContactNumber(campo_numeroContrato.getText());
        
        String issueDate = converteDataDate(Utilities.transformaCalendarEmDate(campo_dataemissao));
        loan.setIssueDate(issueDate);
        loan.setChangeDate(util.getdate());

        InstitutionController institutionController = new InstitutionController();
        InstitutionEntity institutionEntity
                = institutionController.getInstitutionByDescription((String) combo_banco_beneficiario.getSelectedItem());
        institutionEntity.setId(institutionEntity.getId());

        loan.setAgency(campo_agencia.getText());
        loan.setAccount_number(campo_conta_ben.getText());
        loan.setInstitutionEntity(institutionEntity);
        return loan;
    }

    public LoanMovementEntity getLoanMovementView(LoanEntity loanEntity) {
        Utilities utility = new Utilities();
        LoanMovementEntity loanMovement = new LoanMovementEntity();

        loanMovement.setLoanEntity(loanEntity);
        loanMovement.setDate(utility.getdate());

        InstitutionController institutionController = new InstitutionController();
        InstitutionEntity institutionEntity
                = institutionController.getInstitutionByDescription((String) combo_bancoOrigem.getSelectedItem());

        loanMovement.setInstitutionEntity(institutionEntity);

        PlanController planController = new PlanController();
        PlanEntity planEntity = planController.getinserPlanEntityByDescripition((String) combo_convenio.getSelectedItem());
        loanMovement.setPlanEntity(planEntity);

        loanMovement.setTransactionEnum(TransactionEnum.valueOf((String) combo_operacao.getSelectedItem()));
        loanMovement.setLoanStatutsEnum(LoanStatusEnum.valueOf((String) combo_status.getSelectedItem()));
        loanMovement.setBroker(campo_corretor.getText());
        loanMovement.setCommission(campo_comissao.getText());
        loanMovement.setGrossValue(Double.parseDouble(campo_valorbruto.getText()));
        loanMovement.setNetValue(Double.parseDouble(campo_valorliquido.getText()));
        loanMovement.setAmountOfInstallments(Double.parseDouble(campo_qtd_parcelas.getText()));
        loanMovement.setValueInstallments(Double.parseDouble(campo_valorparcela.getText()));
        loanMovement.setOperator(campo_digitador.getText());
        loanMovement.setNote(campo_obs.getText());
        loanMovement.setFiles(campo_caminho_file.getText());
        loanMovement.setNumberADE(campo_ADE.getText());
        loanMovement.setBenefitNumber(campo_numero_beneficio.getText());
        loanMovement.setSpeciesCode(campo_codigoespecie.getText());

        return loanMovement;
    }

    public UserEntity getUserView() {
        UserEntity user = new UserEntity();
        if (!jLabelIdUser.getText().toUpperCase().equals("")) {
            int idUser = Integer.parseInt(jLabelIdUser.getText().toUpperCase());
            user.setId(idUser);
        }
        user.setPhysicalPersonRegistration(campo_cpf.getText().toUpperCase());
        user.setName(campo_nome.getText().toUpperCase());
        user.setSpouse(campo_conjugue.getText().toUpperCase());
        user.setRegistration(campo_rg.getText().toUpperCase());
        user.setIssuingBody(campo_orgao_emissor.getText().toUpperCase());
        user.setIssuer(campo_emissor.getText().toUpperCase());

        String birthDate = converteDataDate(Utilities.transformaCalendarEmDate(jcalenar_data_nascimento));

        user.setBirthDate(birthDate);
        user.setNaturalness(campo_naturalidade.getText().toUpperCase());
        user.setEmail(campo_email.getText().toUpperCase());
        user.setDad(campo_pai.getText().toUpperCase());
        user.setMother(campo_mae.getText().toUpperCase());
        user.setGenreEntity(getGenreView());
        user.setAddressEntity(getAddressView());
        return user;
    }

    public CityEntity getCityView() {
        StateController stateController = new StateController();
        CityEntity city = new CityEntity();
        city.setName(campo_cidade.getText().toUpperCase());

        StateEntity state = new StateEntity();
        state.setUf((String) combo_uf.getSelectedItem());
        state = stateController.getStateByUf(state);
        city.setStateEntity(state);
        return city;
    }

    public AddressEntity getAddressView() {
        AddressEntity address = new AddressEntity();

        if (!jLabelId.getText().toUpperCase().equals("")) {
            address.setId(Integer.parseInt(jLabelId.getText().toUpperCase()));
        }
        address.setZipCode(campo_cep.getText().toUpperCase());
        address.setStreetName(campo_rua.getText().toUpperCase());
        address.setNumber(campo_numero.getText().toUpperCase());
        address.setDistrict(campo_bairro.getText().toUpperCase());
        address.setComplement(campo_complemento.getText().toUpperCase());
        address.setCityEntity(getCityView());
        return address;
    }

    public GenreEntity getGenreView() {
        GenreController genreController = new GenreController();
        GenreEntity genreEntity = new GenreEntity();
        String genre = (String) combobox_sexo.getSelectedItem();
        genreEntity.setDescription(genre);
        genreEntity = genreController.getGenreController(genreEntity);
        return genreEntity;
    }

    public List<PhoneEntity> getListPhoneView(UserEntity userEntity) {
        List<PhoneEntity> listPhone = new ArrayList();

        if (!campo_fone1.getText().equals("")) {
            PhoneEntity phone1 = new PhoneEntity();
            phone1.setUserEntity(userEntity);
            phone1.setNumber(campo_fone1.getText());
            phone1.setNumberType((String) combo_tipo_fone1.getSelectedItem());
            listPhone.add(phone1);
        }

        if (!campo_fone2.getText().equals("")) {
            PhoneEntity phone2 = new PhoneEntity();
            phone2.setUserEntity(userEntity);
            phone2.setNumber(campo_fone2.getText());
            phone2.setNumberType((String) combo_tipo_fone2.getSelectedItem());
            listPhone.add(phone2);
        }
        return listPhone;
    }

    public InstitutionEntity getInsertInstitutionView() {

        InstitutionController institutionController = new InstitutionController();
        InstitutionEntity institutionEntity = new InstitutionEntity();
        String description = JOptionPane.showInputDialog("Infome a Instituição!");

        if (description != null) {
            if (!description.equals("")) {
                institutionEntity.setDescription(description.toUpperCase());
                institutionEntity.setFee(0.0);
                institutionController.inserInstitution(institutionEntity);
                cleanInstitutionist();
                setListInsitutionView();
            }
        }
        return institutionEntity;
    }

    public PlanEntity getInsertPlanEnityView() {

        PlanController planController = new PlanController();
        PlanEntity planEntity = new PlanEntity();
        String description = JOptionPane.showInputDialog("Infome a Instituição!");

        if (description != null) {
            if (!description.equals("")) {
                planEntity.setDescription(description.toUpperCase());
                planController.inserPlan(planEntity);
                cleanPlanList();
                setListPlanView();
            }
        }
        return planEntity;
    }

    public void setListPhoneView(UserEntity user) {
        PhoneController phoneController = new PhoneController();
        List<PhoneEntity> list = phoneController.listPhone(user.getId());

        if (list.size() > 0) {
            campo_fone1.setText(list.get(0).getNumber());
            combo_tipo_fone1.setSelectedItem(list.get(0).getNumberType());
        }

        if (list.size() > 1) {
            campo_fone2.setText(list.get(1).getNumber());
            combo_tipo_fone2.setSelectedItem(list.get(1).getNumberType());
        }
    }

    public void setUserView(UserEntity userEntity) {
        UserController userController = new UserController();
        UserEntity user = userController.getUserController(userEntity);
        jLabelIdUser.setText(String.valueOf(user.getId()));
        campo_cpf.setText(user.getPhysicalPersonRegistration());
        campo_nome.setText(user.getName());
        campo_conjugue.setText(user.getSpouse());
        campo_rg.setText(user.getRegistration());
        campo_orgao_emissor.setText(user.getIssuingBody());
        campo_emissor.setText(user.getIssuer());

        jcalenar_data_nascimento.setDate(converteStringDate(user.getBirthDate()));
        campo_naturalidade.setText(user.getNaturalness());
        campo_email.setText(user.getEmail());
        campo_pai.setText(user.getDad());
        campo_mae.setText(user.getMother());

        setGenreView(userEntity.getGenreEntity());
        setAddressView(user.getAddressEntity());
        setListPhoneView(user);
    }

    public void setAddressView(AddressEntity address) {
        jLabelId.setText(String.valueOf(address.getId()));
        campo_rua.setText(address.getStreetName());
        campo_bairro.setText(address.getDistrict());
        campo_numero.setText(address.getNumber());
        campo_complemento.setText(address.getComplement());
        campo_cidade.setText(address.getCityEntity().getName());
        combo_uf.setSelectedItem(address.getCityEntity().getStateEntity().getUf());
        campo_cep.setText(address.getZipCode());
    }

    public void setGenreView(GenreEntity genreEntity) {
        combobox_sexo.setSelectedItem(genreEntity.getDescription());
    }

    public void resolucaoTela() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) size.getWidth(), (int) size.getHeight());
        this.setLocationRelativeTo(null);
    }

    public void setListStateView() {
        StateController controller = new StateController();
        controller.getListStateController().stream().forEach(uf -> {
            combo_uf.addItem(uf.getUf());
        });
    }

    public void setListGenreView() {
        GenreController genreController = new GenreController();
        genreController.listGenre().stream().forEach(genre -> {
            combobox_sexo.addItem(genre.getDescription());
        });
    }

    public void setListInsitutionView() {
        cleanInstitutionUser();
        cleanInstitutionist();
        InstitutionController institutionController = new InstitutionController();
        institutionController.getListInstitution().stream().forEach(inst -> {
            combo_bancoOrigem.addItem(inst.getDescription());
            combo_banco_beneficiario.addItem(inst.getDescription());
        });
    }

    public void setListPlanView() {
        PlanController planController = new PlanController();
        planController.getListinserPlan().stream().forEach(inst -> {
            combo_convenio.addItem(inst.getDescription());
        });
    }

    public void setListInstitutionView() {
        InstitutionController institutionController = new InstitutionController();
        institutionController.getListInstitution().stream().forEach(inst -> {
            combobox_sexo.addItem(inst.getDescription());
        });
    }

    public void setLaonView(UserEntity userEntity) {
        LoanController loanController = new LoanController();
        List<LoanEntity> listLoan = loanController.getListLoanByUser(userEntity.getId());
        if (listLoan.size() > 0) {
            campo_numeroContrato.setText(listLoan.get(listLoan.size() - 1).getContactNumber());
            campo_dataemissao.setDate(converteStringDate(listLoan.get(listLoan.size() - 1).getIssueDate()));

            combo_banco_beneficiario.setSelectedItem(listLoan.get(listLoan.size() - 1)
                    .getInstitutionEntity().getDescription());
            campo_agencia.setText(listLoan.get(listLoan.size() - 1).getAgency());
            campo_conta_ben.setText(listLoan.get(listLoan.size() - 1).getAccount_number());

            setLoanMovementeView(listLoan.get(listLoan.size() - 1));
        }
    }

    public void setLoanMovementeView(LoanEntity loanEntity) {
        LoanMovementController loanMovementController = new LoanMovementController();
        List<LoanMovementEntity> ListLoanMov = loanMovementController.listLoanMovementByIdLoan(loanEntity.getId());

        if (ListLoanMov.size() > 0) {
            combo_bancoOrigem.setSelectedItem(ListLoanMov.get(ListLoanMov.size() - 1).getInstitutionEntity().getDescription());
            combo_convenio.setSelectedItem(ListLoanMov.get(ListLoanMov.size() - 1).getPlanEntity().getDescription());
            combo_operacao.setSelectedItem(ListLoanMov.get(ListLoanMov.size() - 1).getTransactionEnum().name());
            combo_status.setSelectedItem(ListLoanMov.get(ListLoanMov.size() - 1).getLoanStatutsEnum().name());
            campo_corretor.setText(ListLoanMov.get(ListLoanMov.size() - 1).getBroker());
            campo_comissao.setText(ListLoanMov.get(ListLoanMov.size() - 1).getCommission());
            campo_valorbruto.setText(String.valueOf(ListLoanMov.get(ListLoanMov.size() - 1).getGrossValue()));
            campo_valorliquido.setText(String.valueOf(ListLoanMov.get(ListLoanMov.size() - 1).getNetValue()));
            campo_qtd_parcelas.setText(String.valueOf(ListLoanMov.get(ListLoanMov.size() - 1).getAmountOfInstallments()));
            campo_valorparcela.setText(String.valueOf(ListLoanMov.get(ListLoanMov.size() - 1).getValueInstallments()));
            campo_digitador.setText(ListLoanMov.get(ListLoanMov.size() - 1).getOperator());
            campo_obs.setText(ListLoanMov.get(ListLoanMov.size() - 1).getNote());
            campo_caminho_file.setText(ListLoanMov.get(ListLoanMov.size() - 1).getFiles());
            campo_ADE.setText(ListLoanMov.get(ListLoanMov.size() - 1).getNumberADE());
            campo_numero_beneficio.setText(ListLoanMov.get(ListLoanMov.size() - 1).getBenefitNumber());
            campo_codigoespecie.setText(ListLoanMov.get(ListLoanMov.size() - 1).getSpeciesCode());
            controllValues(ListLoanMov.get(ListLoanMov.size() - 1));
        }
    }

    public void controllValues(LoanMovementEntity loanMovementEntity) {
        if (!loanMovementEntity.getLoanStatutsEnum().equals(LoanStatusEnum.DIGITADO)) {
            editableLoan(false);
            botaoNovaProtosta.setVisible(true);
        } else {
            botaoNovaProtosta.setVisible(false);
        }
    }

    public void getUserByPhysicalPersonRegistration() {
        UserEntity user = new UserEntity();
        UserController userController = new UserController();

        user.setPhysicalPersonRegistration(campo_cpf.getText().toUpperCase());
        user = userController.getUserController(user);
        if (user != null) {
            setUserView(user);
            setLaonView(user);
        }
    }

    public void insertLoan() {
        if (fieldValidation()) {
            LoanController loanController = new LoanController();

            loanController.insertLoan(getLoanView(insertUser()));
            LoanEntity loan = loanController.getLoanByContactNumber(campo_numeroContrato.getText());
            if (loan != null) {
                insertLoanMoviment(loan);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preencha os Campos obrigatorios!");
        }
    }

    public void UpdateLoan() {

        LoanController loanController = new LoanController();

        loanController.updateLoan(getLoanView(updateUser()));
        LoanEntity loan = loanController.getLoanByContactNumber(campo_numeroContrato.getText());

        insertLoanMoviment(loan);
    }

    public void insertLoanMoviment(LoanEntity loan) {

        LoanMovementController loanMovementController = new LoanMovementController();
        LoanMovementEntity loanMovementEntity = getLoanMovementView(loan);
        if (loanMovementController.insertLoanMovement(loanMovementEntity)) {
            JOptionPane.showMessageDialog(null, "Proposta Efetuada com sucesso!");
            cleanAll();
        } else {
            JOptionPane.showMessageDialog(null, "Falha ao efetivar a Proposta!");
        }
    }

    public UserEntity insertUser() {
        UserController userController = new UserController();
        userController.insertUserController(getUserView());
        UserEntity user = userController.getUserController(getUserView());

        insertPhone(getListPhoneView(user));
        return user;
    }

    public void insertPhone(List<PhoneEntity> listPhone) {
        PhoneController phoneController = new PhoneController();
        if (listPhone.size() > 0) {
            listPhone.stream().forEach(phone -> {
                phoneController.insertPhone(phone);
            });
        }
    }

    public UserEntity updateUser() {
        UserController userController = new UserController();
        if (userController.updateUserController(getUserView())) {

            UserEntity user = userController.getUserController(getUserView());
            PhoneController phoneController = new PhoneController();
            phoneController.updateListPhone(getListPhoneView(user));

            return userController.getUserController(getUserView());
        }
        return null;
    }

    public void insertGenre() {
        GenreController genreController = new GenreController();
        GenreEntity genreEntity = new GenreEntity();
        String descript = JOptionPane.showInputDialog("Novo Gênero");
        if (descript != null) {
            if (!descript.equals("")) {
                genreEntity.setDescription(descript.toUpperCase());
                genreController.insertGenreController(genreEntity);
                cleanGenreList();
                setListGenreView();
            }
        }
    }

    public void cleanGenreList() {
        combobox_sexo.removeAllItems();
    }

    public void cleanInstitutionist() {
        combo_bancoOrigem.removeAllItems();
    }

    public void cleanInstitutionUser() {
        combo_banco_beneficiario.removeAllItems();
    }

    public void cleanPlanList() {
        combo_convenio.removeAllItems();
    }

    /**
     * ############################## CLEAN
     */
    public void cleanAll() {
        openNew();
        cleanUser();
        cleanAddress();
        cleanLoan();
    }

    public void cleanUser() {
        jLabelIdUser.setText("");
        campo_cpf.setText("");
        campo_nome.setText("");
        campo_conjugue.setText("");
        campo_rg.setText("");
        campo_orgao_emissor.setText("");
        campo_emissor.setText("");
        campo_naturalidade.setText("");
        campo_email.setText("");
        campo_pai.setText("");
        campo_mae.setText("");
        campo_fone1.setText("");
        campo_fone2.setText("");
        setDateCalendar();
    }

    public void cleanAddress() {
        jLabelId.setText("");
        campo_rua.setText("");
        campo_bairro.setText("");
        campo_numero.setText("");
        campo_complemento.setText("");
        campo_cidade.setText("");
        combo_uf.setSelectedItem("");
        campo_cep.setText("");
    }

    public void cleanLoan() {
        campo_numeroContrato.setText("");
        campo_agencia.setText("");
        campo_conta_ben.setText("");
        combo_status.setSelectedItem("DIGITADO");
        campo_corretor.setText("");
        campo_comissao.setText("");
        campo_valorbruto.setText("");
        campo_valorliquido.setText("");
        campo_qtd_parcelas.setText("");
        campo_valorparcela.setText("");
        campo_digitador.setText("");
        campo_obs.setText("");
        campo_caminho_file.setText("");
        campo_ADE.setText("");
        campo_numero_beneficio.setText("");
        campo_codigoespecie.setText("");
        setListInsitutionView();
        setListPlanView();
        setDateCalendar();
    }

    public void editableLoan(boolean editable) {
        campo_numeroContrato.setEditable(editable);
        campo_dataemissao.setEnabled(editable);
        combo_banco_beneficiario.setEnabled(editable);
        campo_agencia.setEditable(editable);
        campo_conta_ben.setEditable(editable);

        combo_bancoOrigem.setEnabled(editable);
        combo_convenio.setEnabled(editable);
        combo_operacao.setEnabled(editable);;
        combo_status.setEnabled(editable);
        campo_corretor.setEditable(editable);
        campo_comissao.setEditable(editable);
        campo_valorbruto.setEditable(editable);
        campo_valorliquido.setEditable(editable);
        campo_qtd_parcelas.setEditable(editable);
        campo_valorparcela.setEditable(editable);
        campo_digitador.setEditable(editable);
        campo_obs.setEditable(editable);
        campo_caminho_file.setEditable(editable);
        campo_ADE.setEditable(editable);
        campo_numero_beneficio.setEditable(editable);
        campo_codigoespecie.setEditable(editable);
    }

    /**
     * #################### validation
     *
     */
    public void buttonControl() {
        enableSaveButton();
        activateChangeButton();
    }

    public void enableSaveButton() {
        if (jLabelIdUser.getText().equals("")) {
            botaoSalvar.setEnabled(true);
            botaoAlterar.setEnabled(false);
        } else {
            botaoSalvar.setEnabled(false);
            botaoAlterar.setEnabled(true);
        }
    }

    public void activateChangeButton() {
        if (jLabelIdUser.getText().equals("")) {
            botaoAlterar.setEnabled(false);
            botaoSalvar.setEnabled(true);
        } else {
            botaoAlterar.setEnabled(true);
            botaoSalvar.setEnabled(false);
        }
    }

    public void openNew() {
        editableLoan(true);
        cleanLoan();
        botaoAlterar.setEnabled(false);
        botaoSalvar.setEnabled(true);
    }

    public boolean fieldValidation() {
        String bancoOrigem = (String) combo_bancoOrigem.getSelectedItem();
        String convenio = (String) combo_convenio.getSelectedItem();
        String bancoBene = (String) combo_banco_beneficiario.getSelectedItem();
        if (campo_numeroContrato.getText().equals("")) {
            return false;
        }
        if (campo_nome.getText().equals("")) {
            return false;
        }
        if (bancoOrigem == null) {
            return false;
        }
        if (convenio == null) {
            return false;
        }
        if (bancoBene == null) {
            return false;
        }
        if (campo_digitador.getText().equals("")) {
            return false;
        }

        return true;
    }

    public void checkCpfDigits() {
        if (campo_cpf.getText().length() > 3) {
            if (campo_cpf.getText().contains(".")) {
                if (campo_cpf.getText().length() == 14) {
                    isCpf();
                }
            } else {
                if (campo_cpf.getText().length() == 11) {
                    isCpf();
                }
            }
        }
    }

    public void isCpf() {
        if (ValidateCpf.isCPF(campo_cpf.getText()) == false) {
            JOptionPane.showMessageDialog(null, "CPF inválido!");
            campo_cpf.setText("");
        } else {
            getUserByPhysicalPersonRegistration();
            buttonControl();
        }
    }

    public void fillLoanTable() {
        DefaultTableModel dtm = (DefaultTableModel) tabelaLoan.getModel();

        DefaultTableModel dtm2 = (DefaultTableModel) tabelaLoanCanc.getModel();

        LoanController loanController = new LoanController();
        LoanMovementController loanMovementController = new LoanMovementController();

        loanController.getListAllLoan().stream().forEach(loan -> {
            List<LoanMovementEntity> listMov = loanMovementController.listLoanMovementByIdLoan(loan.getId());

            if (listMov.size() > 0) {
                if (listMov.get(listMov.size() - 1).getLoanStatutsEnum().name().equals("DIGITADO")) {
                    Object[] dados = {loan.getUsrEntity().getPhysicalPersonRegistration(),
                        loan.getAccount_number(), loan.getIssueDate(), loan.getChangeDate(),
                        loan.getInstitutionEntity().getDescription(),
                        listMov.get(listMov.size() - 1).getOperator(), listMov.get(listMov.size() - 1).getLoanStatutsEnum().name()};
                    dtm.addRow(dados);

                } else {

                    Object[] dadosCan = {loan.getUsrEntity().getPhysicalPersonRegistration(),
                        loan.getAccount_number(), loan.getIssueDate(), loan.getChangeDate(),
                        loan.getInstitutionEntity().getDescription(),
                        listMov.get(listMov.size() - 1).getOperator(), listMov.get(listMov.size() - 1).getLoanStatutsEnum().name()};
                    dtm2.addRow(dadosCan);
                }
            }
        });
    }

    public void tableEventLoanTyped() {
        int lin = tabelaLoan.getSelectedRow();
        if (lin != -1) {
            cleanAll();
            campo_cpf.setText((String) tabelaLoan.getValueAt(lin, 0));
            checkCpfDigits();
            jTabbedPane1.setSelectedIndex(1);
        }
    }

    public void tableEventLoanFinish() {
        int lin = tabelaLoanCanc.getSelectedRow();
        if (lin != -1) {
            cleanAll();
            campo_cpf.setText((String) tabelaLoanCanc.getValueAt(lin, 0));
            checkCpfDigits();
            jTabbedPane1.setSelectedIndex(1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campo_cpf = new javax.swing.JTextField();
        jLabelIdUser = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campo_nome = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        combobox_sexo = new javax.swing.JComboBox<>();
        campo_rg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        campo_orgao_emissor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        campo_emissor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        campo_naturalidade = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        campo_email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        campo_pai = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        campo_mae = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        campo_conjugue = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        campo_fone1 = new javax.swing.JTextField();
        combo_tipo_fone1 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        campo_fone2 = new javax.swing.JTextField();
        combo_tipo_fone2 = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        combo_status = new javax.swing.JComboBox<>();
        botaoNovaProtosta = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        campo_cep = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        campo_rua = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        campo_numero = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        campo_complemento = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        campo_bairro = new javax.swing.JTextField();
        combo_uf = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        campo_cidade = new javax.swing.JTextField();
        jLabelId = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        campo_corretor = new javax.swing.JTextField();
        combo_bancoOrigem = new javax.swing.JComboBox<>();
        combo_convenio = new javax.swing.JComboBox<>();
        campo_valorliquido = new javax.swing.JTextField();
        campo_qtd_parcelas = new javax.swing.JTextField();
        campo_valorparcela = new javax.swing.JTextField();
        campo_numeroContrato = new javax.swing.JTextField();
        campo_numero_beneficio = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        campo_ADE = new javax.swing.JTextField();
        campo_valorbruto = new javax.swing.JTextField();
        campo_conta_ben = new javax.swing.JTextField();
        campo_codigoespecie = new javax.swing.JTextField();
        combo_operacao = new javax.swing.JComboBox<>();
        campo_digitador = new javax.swing.JTextField();
        campo_comissao = new javax.swing.JTextField();
        campo_agencia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        campo_obs = new javax.swing.JTextArea();
        jPanel9 = new javax.swing.JPanel();
        botaoSalvar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        campo_caminho_file = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        combo_banco_beneficiario = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        campo_dataemissao = new com.toedter.calendar.JDateChooser();
        jcalenar_data_nascimento = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaLoan = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaLoanCanc = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1318, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 735, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Aniversariantes", jPanel8);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("CPF:");

        campo_cpf.setText("101");
        campo_cpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_cpfKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Nome:");

        campo_nome.setText("nome1");
        campo_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_nomeActionPerformed(evt);
            }
        });

        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        campo_rg.setText("00001");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("RG:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Orgão Emissor:");

        campo_orgao_emissor.setText("orgao emisso");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Emissor:");

        campo_emissor.setText("emissor");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Data Nasc:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Naturalidade:");

        campo_naturalidade.setText("mamanguape");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setText("STATUS PROPOSTA");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel19.setText("Email:");

        campo_email.setText("@email");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Pai:");

        campo_pai.setText("pai");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Mãe:");

        campo_mae.setText("mae");
        campo_mae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_maeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Cônjuge:");

        campo_conjugue.setText("conjuge");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText("Fone:");

        campo_fone1.setText("2222222");

        combo_tipo_fone1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Celular", "Residencial", "Casa", "Trabalho" }));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel21.setText("Fone:");

        campo_fone2.setText("333333");

        combo_tipo_fone2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Celular", "Residencial", "Casa", "Trabalho" }));

        jLabel40.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel40.setText("Status atual:");

        combo_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DIGITADO", "FINALIZADO", "CANCELADO" }));

        botaoNovaProtosta.setText("Nova Proposta");
        botaoNovaProtosta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovaProtostaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.darkGray), "ENDEREÇO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel12.setText("CEP:");

        campo_cep.setText("58068360");

        jLabel13.setText("Rua:");

        campo_rua.setText("rua");

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Numero:");

        campo_numero.setText("numero");
        campo_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_numeroActionPerformed(evt);
            }
        });

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("Complemento:");

        campo_complemento.setText("complemento");

        jLabel16.setText("Bairro:");

        campo_bairro.setText("bairro");

        combo_uf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_ufActionPerformed(evt);
            }
        });

        jLabel17.setText("UF:");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("Cidade:");

        campo_cidade.setText("cidade");
        campo_cidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_cidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campo_cep, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campo_rua, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)))
                    .addComponent(campo_bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campo_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campo_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_uf, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo_complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(campo_rua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campo_complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(campo_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(campo_cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(campo_bairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combo_uf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(campo_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.darkGray), "DADOS PARA LIBERAÇÃO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setText("Corretor:");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel24.setText("Banco Origem:");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel25.setText("Convênio:");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel26.setText("Data Emissão:");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel27.setText("Comissão:");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel28.setText("Nº Contrato:");

        jLabel29.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel29.setText("Valor Liquido:");

        jLabel30.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel30.setText("Qtd Parcelas:");

        jLabel31.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel31.setText("Valor Parcela:");

        jLabel32.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel32.setText("Nº Beneficio:");

        jLabel33.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel33.setText("Valor Bruto:");

        jLabel34.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel34.setText("Digitador:");

        campo_corretor.setText("corretor");

        campo_valorliquido.setText("1800");

        campo_qtd_parcelas.setText("10");

        campo_valorparcela.setText("200");

        campo_numeroContrato.setText("222222");

        campo_numero_beneficio.setText("212121");
        campo_numero_beneficio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_numero_beneficioActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel35.setText("Operação:");

        jLabel37.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel37.setText("Agencia Beneficiado:");

        jLabel38.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel38.setText("Banco beneficiado:");

        jLabel39.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel39.setText("Conta Beneficiado:");

        jLabel45.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel45.setText("Código Especie:");

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel46.setText("Observação:");

        jLabel48.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel48.setText("Nº ADE:");

        campo_ADE.setText("333221");
        campo_ADE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_ADEActionPerformed(evt);
            }
        });

        campo_valorbruto.setText("2000");

        campo_conta_ben.setText("220012");

        campo_codigoespecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_codigoespecieActionPerformed(evt);
            }
        });

        combo_operacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PORTABILIDADE", "REFINANCIAMENTO", "MARGEM_LIVRE", "CARTAO_RMC", "EMPRESTIMO_CARTAO", "CARTAO_BENEFICIARIO", "FGTS" }));

        campo_digitador.setText("digitador");

        campo_comissao.setText("10");

        campo_agencia.setText("0000021");

        campo_obs.setColumns(20);
        campo_obs.setRows(5);
        campo_obs.setText("obs");
        jScrollPane1.setViewportView(campo_obs);

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        jButton3.setText("Limpar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        jLabel36.setText("Caminho Anexo:");

        campo_caminho_file.setText("caminho");

        jButton1.setText("Localizar:");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton7.setText("+");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("+");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("+");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                        .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campo_corretor, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campo_comissao, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(combo_convenio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(combo_bancoOrigem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton9)
                                    .addComponent(jButton8)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(campo_dataemissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(72, 72, 72)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel35)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campo_valorbruto)
                            .addComponent(campo_valorliquido, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campo_qtd_parcelas, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campo_valorparcela, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(campo_numeroContrato, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_numero_beneficio, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo_operacao, javax.swing.GroupLayout.Alignment.LEADING, 0, 194, Short.MAX_VALUE)
                    .addComponent(campo_digitador, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_agencia, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_conta_ben, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_ADE, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campo_codigoespecie, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(combo_banco_beneficiario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163)
                        .addComponent(jLabel36))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campo_caminho_file)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campo_corretor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel34)
                                    .addComponent(campo_digitador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46)
                                    .addComponent(jLabel36))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(combo_bancoOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton9))
                                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(combo_convenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel32)
                                                .addComponent(campo_numero_beneficio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton8))
                                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel28)
                                        .addComponent(campo_numeroContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel35)
                                                .addComponent(combo_operacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(10, 10, 10))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(campo_dataemissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campo_comissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(campo_caminho_file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(campo_ADE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(campo_valorliquido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campo_valorbruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel33))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campo_qtd_parcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campo_valorparcela)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(combo_banco_beneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7)
                                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campo_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campo_conta_ben, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(campo_codigoespecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(49, 49, 49))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(635, 635, 635))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(campo_fone1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(combo_tipo_fone1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(campo_mae)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(campo_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelIdUser, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(campo_nome))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(campo_rg, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(campo_orgao_emissor, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(campo_emissor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(combobox_sexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jcalenar_data_nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(campo_naturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(campo_email, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                        .addComponent(campo_fone2)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(combo_tipo_fone2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(campo_pai, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(17, 17, 17)
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(campo_conjugue, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(combo_status, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(botaoNovaProtosta, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGap(18, 18, 18)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(54, 54, 54))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combobox_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campo_emissor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campo_rg, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelIdUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(campo_orgao_emissor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1))
                            .addComponent(campo_cpf, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campo_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campo_naturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campo_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jcalenar_data_nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo_mae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo_pai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo_conjugue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campo_fone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combo_tipo_fone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combo_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoNovaProtosta))
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campo_fone2)
                    .addComponent(combo_tipo_fone2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );

        jTabbedPane1.addTab("Nova Proposta   ", jPanel7);

        tabelaLoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nº Contrato", "Data Solicitação", "Data Alteração", "Instituição", "Operador", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaLoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaLoanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaLoan);

        tabelaLoanCanc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nº Contrato", "Data Solicitação", "Data Alteração", "Instituição", "Operador", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaLoanCanc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaLoanCancMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaLoanCanc);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1298, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Propostas            ", jPanel4);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        insertGenre();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void botaoNovaProtostaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovaProtostaActionPerformed
        openNew();
    }//GEN-LAST:event_botaoNovaProtostaActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        getInsertInstitutionView();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        getInsertPlanEnityView();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        campo_agencia.setText("");
        campo_conta_ben.setText("");
        setListInsitutionView();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        UpdateLoan();
    }//GEN-LAST:event_botaoAlterarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        cleanAll();
        buttonControl();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        insertLoan();
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void campo_codigoespecieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_codigoespecieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_codigoespecieActionPerformed

    private void campo_ADEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_ADEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_ADEActionPerformed

    private void campo_numero_beneficioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_numero_beneficioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_numero_beneficioActionPerformed

    private void campo_cidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_cidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_cidadeActionPerformed

    private void combo_ufActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_ufActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_ufActionPerformed

    private void campo_numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_numeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_numeroActionPerformed

    private void campo_maeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_maeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_maeActionPerformed

    private void campo_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_nomeActionPerformed

    private void campo_cpfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_cpfKeyReleased
        checkCpfDigits();
    }//GEN-LAST:event_campo_cpfKeyReleased

    private void tabelaLoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaLoanMouseClicked
        tableEventLoanTyped();
    }//GEN-LAST:event_tabelaLoanMouseClicked

    private void tabelaLoanCancMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaLoanCancMouseClicked
        tableEventLoanFinish();
    }//GEN-LAST:event_tabelaLoanCancMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreenView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainScreenView dialog = new MainScreenView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoNovaProtosta;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextField campo_ADE;
    private javax.swing.JTextField campo_agencia;
    private javax.swing.JTextField campo_bairro;
    private javax.swing.JTextField campo_caminho_file;
    private javax.swing.JTextField campo_cep;
    private javax.swing.JTextField campo_cidade;
    private javax.swing.JTextField campo_codigoespecie;
    private javax.swing.JTextField campo_comissao;
    private javax.swing.JTextField campo_complemento;
    private javax.swing.JTextField campo_conjugue;
    private javax.swing.JTextField campo_conta_ben;
    private javax.swing.JTextField campo_corretor;
    private javax.swing.JTextField campo_cpf;
    private com.toedter.calendar.JDateChooser campo_dataemissao;
    private javax.swing.JTextField campo_digitador;
    private javax.swing.JTextField campo_email;
    private javax.swing.JTextField campo_emissor;
    private javax.swing.JTextField campo_fone1;
    private javax.swing.JTextField campo_fone2;
    private javax.swing.JTextField campo_mae;
    private javax.swing.JTextField campo_naturalidade;
    private javax.swing.JTextField campo_nome;
    private javax.swing.JTextField campo_numero;
    private javax.swing.JTextField campo_numeroContrato;
    private javax.swing.JTextField campo_numero_beneficio;
    private javax.swing.JTextArea campo_obs;
    private javax.swing.JTextField campo_orgao_emissor;
    private javax.swing.JTextField campo_pai;
    private javax.swing.JTextField campo_qtd_parcelas;
    private javax.swing.JTextField campo_rg;
    private javax.swing.JTextField campo_rua;
    private javax.swing.JTextField campo_valorbruto;
    private javax.swing.JTextField campo_valorliquido;
    private javax.swing.JTextField campo_valorparcela;
    private javax.swing.JComboBox<String> combo_bancoOrigem;
    private javax.swing.JComboBox<String> combo_banco_beneficiario;
    private javax.swing.JComboBox<String> combo_convenio;
    private javax.swing.JComboBox<String> combo_operacao;
    private javax.swing.JComboBox<String> combo_status;
    private javax.swing.JComboBox<String> combo_tipo_fone1;
    private javax.swing.JComboBox<String> combo_tipo_fone2;
    private javax.swing.JComboBox<String> combo_uf;
    private javax.swing.JComboBox<String> combobox_sexo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelIdUser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JDateChooser jcalenar_data_nascimento;
    private javax.swing.JTable tabelaLoan;
    private javax.swing.JTable tabelaLoanCanc;
    // End of variables declaration//GEN-END:variables

    private String getSelectedItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
