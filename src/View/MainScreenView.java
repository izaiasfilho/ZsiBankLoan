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
import Controller.PreferencesController;
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
import Model.Entities.PreferencesEntity;
import Model.Entities.StateEntity;
import Model.Entities.UserEntity;
import Model.Enuns.LoanStatusEnum;
import Model.Enuns.TransactionEnum;
import Model.Utility.Utilities;
import static Model.Utility.Utilities.converteCodicoCorEmInter;
import static Model.Utility.Utilities.converteDataDate;
import static Model.Utility.Utilities.converteStringDate;
import Model.Utility.ValidateCpf;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
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
        filePreferences();
        cleanAll();
        setListStateView();
        setListGenreView();
        setListInsitutionView();
        setListPlanView();
        buttonControl();
        buttonControlRegistration();

        botaoNovaProtosta.setVisible(false);
        fillLoanTable();
        setDateCalendar();
        getListBirthdaysOfTheMonth();
        mudarCor();
        campo_caminho_file.setEditable(false);
        tabs();
        combo_uf.setSelectedItem("PB");
        combo_uf2.setSelectedItem("PB");
        ajustartextarea();

        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        URL caminhoIncone = getClass().getResource("/Resources/icons/borboleta f.png");
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(caminhoIncone);
        this.setIconImage(iconeTitulo);

        //texto
        setTitle("ZsiMEI- " + " principal ");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void mudarCor() {
        String cor = "255,255,255";//216,237,243
        int codigoCor[] = new int[3];

        codigoCor = converteCodicoCorEmInter(cor);
        ArrayList<JComponent> componentes = new ArrayList();

        componentes.add(jButton8);
        componentes.add(jButton9);
        componentes.add(jButton7);
        componentes.add(jPanel8);
        componentes.add(jPanel4);
        componentes.add(jTabbedPane1);
        componentes.add(jPanel7);
        componentes.add(jPanel1);
        componentes.add(jPanel5);
        componentes.add(jPanel5);
        componentes.add(jButton1);
        componentes.add(botaoNovaProtosta);
        componentes.add(botaoSalvar);
        componentes.add(botaoAlterar);
        componentes.add(jButton3);
        componentes.add(jButton6);
        componentes.add(jPanel6);
        componentes.add(botaoAnexos);
        componentes.add(jPanel10);
        componentes.add(jPanel11);
        componentes.add(jPanel14);
        componentes.add(jPanel15);
        componentes.add(jPanel2);
        componentes.add(botaAdicionar);
        componentes.add(botaoAlterar1);
        componentes.add(jButton5);
        componentes.add(jPanel13);
        componentes.add(jCheckBox1);
        componentes.add(jCheckBox2);
        componentes.add(jCheckBox3);
        componentes.add(jCheckBox4);
        componentes.add(botaoSetPreferences);

        Utilities.mudarCor(cor, componentes);
    }

    public void ajustartextarea() {
        ArrayList<JTextArea> lista = new ArrayList<>();

        lista.add(campo_obs);
        Utilities.ajustartextarea(lista);
    }

    public void setDateCalendar() {
        java.util.Date now;
        now = new java.util.Date();
        jcalenar_data_nascimento.setDate(now);
        jcalenar_data_nascimento1.setDate(now);
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
        loanMovement.setOperator(utility.maiusculaConvertido(campo_digitador.getText()));
        loanMovement.setNote(utility.maiusculaConvertido(campo_obs.getText()));

        PreferencesController preferencesController = new PreferencesController();
        PreferencesEntity preferencesEntity = preferencesController.getPreferencesEntity();
        String fileDefault = "C:\\Util\\Usuarios\\";
        if (preferencesEntity != null) {
            if (preferencesEntity.getFiles() != null) {
                fileDefault = preferencesEntity.getFiles();
            }
        }

        if (campo_caminho_file.getText().equals("")) {
            String cpf = campo_cpf.getText().replace(".", "");
            String file = fileDefault + cpf.replace("-", "");
            loanMovement.setFiles(file);
        } else {
            loanMovement.setFiles(campo_caminho_file.getText());
        }
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

    public UserEntity getUserRegistrationView() {
        UserEntity user = new UserEntity();
        if (!jLabelIdUser1.getText().toUpperCase().equals("")) {
            int idUser = Integer.parseInt(jLabelIdUser1.getText().toUpperCase());
            user.setId(idUser);
        }
        user.setPhysicalPersonRegistration(campo_cpf1.getText().toUpperCase());
        user.setName(campo_nome1.getText().toUpperCase());
        user.setSpouse(campo_conjugue1.getText().toUpperCase());
        user.setRegistration(campo_rg1.getText().toUpperCase());
        user.setIssuingBody(campo_orgao_emissor1.getText().toUpperCase());
        user.setIssuer(campo_emissor1.getText().toUpperCase());

        String birthDate = converteDataDate(Utilities.transformaCalendarEmDate(jcalenar_data_nascimento1));

        user.setBirthDate(birthDate);
        user.setNaturalness(campo_naturalidade1.getText().toUpperCase());
        user.setEmail(campo_email1.getText().toUpperCase());
        user.setDad(campo_pai1.getText().toUpperCase());
        user.setMother(campo_mae1.getText().toUpperCase());
        user.setGenreEntity(getGenreRegistrationView());
        user.setAddressEntity(getAddressRegistrationView());
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

    public CityEntity getCityViewRegistration() {
        StateController stateController = new StateController();
        CityEntity city = new CityEntity();
        city.setName(campo_cidade2.getText().toUpperCase());

        StateEntity state = new StateEntity();
        state.setUf((String) combo_uf2.getSelectedItem());
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

    public AddressEntity getAddressRegistrationView() {
        AddressEntity address = new AddressEntity();

        if (!jLabelId2.getText().toUpperCase().equals("")) {
            address.setId(Integer.parseInt(jLabelId2.getText().toUpperCase()));
        }
        address.setZipCode(campo_cep2.getText().toUpperCase());
        address.setStreetName(campo_rua2.getText().toUpperCase());
        address.setNumber(campo_numero2.getText().toUpperCase());
        address.setDistrict(campo_bairro2.getText().toUpperCase());
        address.setComplement(campo_complemento2.getText().toUpperCase());
        address.setCityEntity(getCityViewRegistration());
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

    public GenreEntity getGenreRegistrationView() {
        GenreController genreController = new GenreController();
        GenreEntity genreEntity = new GenreEntity();
        String genre = (String) combobox_sexo1.getSelectedItem();
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

    public List<PhoneEntity> getListPhoneViewRegistration(UserEntity userEntity) {
        List<PhoneEntity> listPhone = new ArrayList();

        if (!campo_fone3.getText().equals("")) {
            PhoneEntity phone1 = new PhoneEntity();
            phone1.setUserEntity(userEntity);
            phone1.setNumber(campo_fone3.getText());
            phone1.setNumberType((String) combo_tipo_fone3.getSelectedItem());
            listPhone.add(phone1);
        }

        if (!campo_fone4.getText().equals("")) {
            PhoneEntity phone2 = new PhoneEntity();
            phone2.setUserEntity(userEntity);
            phone2.setNumber(campo_fone4.getText());
            phone2.setNumberType((String) combo_tipo_fone4.getSelectedItem());
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

    public void setListPhoneViewRegistration(UserEntity user) {
        PhoneController phoneController = new PhoneController();
        List<PhoneEntity> list = phoneController.listPhone(user.getId());

        if (list.size() > 0) {
            campo_fone3.setText(list.get(0).getNumber());
            combo_tipo_fone3.setSelectedItem(list.get(0).getNumberType());
        }

        if (list.size() > 1) {
            campo_fone4.setText(list.get(1).getNumber());
            combo_tipo_fone4.setSelectedItem(list.get(1).getNumberType());
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

    public void setUserRegistrationView(UserEntity userEntity) {
        UserController userController = new UserController();
        UserEntity user = userController.getUserController(userEntity);
        jLabelIdUser1.setText(String.valueOf(user.getId()));
        campo_cpf1.setText(user.getPhysicalPersonRegistration());
        campo_nome1.setText(user.getName());
        campo_conjugue1.setText(user.getSpouse());
        campo_rg1.setText(user.getRegistration());
        campo_orgao_emissor1.setText(user.getIssuingBody());
        campo_emissor1.setText(user.getIssuer());

        jcalenar_data_nascimento1.setDate(converteStringDate(user.getBirthDate()));
        campo_naturalidade1.setText(user.getNaturalness());
        campo_email1.setText(user.getEmail());
        campo_pai1.setText(user.getDad());
        campo_mae1.setText(user.getMother());

        setGenreView(userEntity.getGenreEntity());
        setAddressViewRegistration(user.getAddressEntity());
        setListPhoneViewRegistration(user);
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

    public void setAddressViewRegistration(AddressEntity address) {
        jLabelId2.setText(String.valueOf(address.getId()));
        campo_rua2.setText(address.getStreetName());
        campo_bairro2.setText(address.getDistrict());
        campo_numero2.setText(address.getNumber());
        campo_complemento2.setText(address.getComplement());
        campo_cidade2.setText(address.getCityEntity().getName());
        combo_uf2.setSelectedItem(address.getCityEntity().getStateEntity().getUf());
        campo_cep2.setText(address.getZipCode());
    }

    public void setGenreView(GenreEntity genreEntity) {
        combobox_sexo.setSelectedItem(genreEntity.getDescription());
        combobox_sexo1.setSelectedItem(genreEntity.getDescription());
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
            combo_uf2.addItem(uf.getUf());
        });
    }

    public void setListGenreView() {
        GenreController genreController = new GenreController();
        genreController.listGenre().stream().forEach(genre -> {
            combobox_sexo.addItem(genre.getDescription());
            combobox_sexo1.addItem(genre.getDescription());
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
        cleanPlanList();
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
        PreferencesController preferences = new PreferencesController();
        PreferencesEntity preferencesEntity = preferences.getPreferencesEntity();
        String fileDefault = "C:\\Util\\Usuarios";

        if (preferencesEntity != null) {
            if (preferencesEntity.getFiles() != null) {
                fileDefault = preferencesEntity.getFiles();
            }
        }
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
            String file = ListLoanMov.get(ListLoanMov.size() - 1).getFiles();
            campo_caminho_file.setText(file.equals("") ? fileDefault : file);
            botaoSetPreferences.setEnabled(false);
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
            userLoanTable(user.getId());
        }
    }

    public void getUserRegistrationByPhysicalPersonRegistration() {
        UserEntity user = new UserEntity();
        UserController userController = new UserController();

        user.setPhysicalPersonRegistration(campo_cpf1.getText().toUpperCase());
        user = userController.getUserController(user);
        if (user != null) {
            setUserRegistrationView(user);
        }
    }

    public void insertLoan() {
        if (ValidateCpf.isCPF(campo_cpf.getText()) == false) {
            JOptionPane.showMessageDialog(null, "CPF inválido!");
            campo_cpf.setText("");
        } else {
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
    }

    public void insertRegistrationUser() {
        if (ValidateCpf.isCPF(campo_cpf1.getText()) == false) {
            JOptionPane.showMessageDialog(null, "CPF inválido!");
            campo_cpf1.setText("");
        } else {
            if (fieldValidationRegistration()) {
                if (insertUserRegistration() != null) {
                    JOptionPane.showMessageDialog(null, "Usúario cadastrado com sucesso");
                    cleanAll();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os Campos obrigatorios!");
            }
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

    public boolean updateFilePreferences(PreferencesEntity preferencesEntity) {
        PreferencesController preferencesController = new PreferencesController();
        if (preferencesEntity != null) {
            if (!preferencesEntity.getFiles().equals(campo_caminho_file.getText())) {
                preferencesController.updatePreferences(campo_caminho_file.getText());
            }
        }
        return false;
    }

    public boolean insertFilePreferences(PreferencesEntity preferencesEntity) {
        PreferencesController preferencesController = new PreferencesController();
        if (preferencesEntity == null) {
            String file = "C:\\Util\\Usuarios\\";
            if (!campo_caminho_file.getText().equals("")) {
                file = campo_caminho_file.getText();
            }
            preferencesController.insertPreferences(file);
        }
        return false;
    }

    public void filePreferences() {
        if (!campo_caminho_file.getText().equals("")) {
            PreferencesController preferencesController = new PreferencesController();
            PreferencesEntity preferencesEntity = preferencesController.getPreferencesEntity();
            if (!insertFilePreferences(preferencesEntity)) {
                updateFilePreferences(preferencesEntity);
            }
        }
    }

    public PreferencesEntity getFilePreferencesEntity() {
        PreferencesController preferencesController = new PreferencesController();
        return preferencesController.getPreferencesEntity();
    }

    public UserEntity insertUserRegistration() {
        UserController userController = new UserController();
        userController.insertUserController(getUserRegistrationView());
        UserEntity user = userController.getUserController(getUserRegistrationView());

        insertPhone(getListPhoneViewRegistration(user));
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

    public UserEntity updateUserRegistration() {
        UserController userController = new UserController();
        if (userController.updateUserController(getUserRegistrationView())) {

            UserEntity user = userController.getUserController(getUserRegistrationView());
            PhoneController phoneController = new PhoneController();
            phoneController.updateListPhone(getListPhoneViewRegistration(user));

            return userController.getUserController(getUserRegistrationView());
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
        getListBirthdaysOfTheMonth();
        clearLoanCancTable();
        clearLoanTable();
        fillLoanTable();
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

        jLabelIdUser1.setText("");
        campo_cpf1.setText("");
        campo_nome1.setText("");
        campo_conjugue1.setText("");
        campo_rg1.setText("");
        campo_orgao_emissor1.setText("");
        campo_emissor1.setText("");
        campo_naturalidade1.setText("");
        campo_email1.setText("");
        campo_pai1.setText("");
        campo_mae1.setText("");
        campo_fone3.setText("");
        campo_fone4.setText("");
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

        jLabelId2.setText("");
        campo_rua2.setText("");
        campo_bairro2.setText("");
        campo_numero2.setText("");
        campo_complemento2.setText("");
        campo_cidade2.setText("");
        combo_uf2.setSelectedItem("");
        campo_cep2.setText("");
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
        campo_ADE.setText("");
        campo_numero_beneficio.setText("");
        campo_codigoespecie.setText("");
        campo_caminho_file.setText("");
        botaoSetPreferences.setEnabled(true);
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

    public void buttonControlRegistration() {
        enableSaveButtonRegistration();
        activateChangeButtonRegistration();
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

    public void enableSaveButtonRegistration() {
        if (jLabelIdUser1.getText().equals("")) {
            botaAdicionar.setEnabled(true);
            botaoAlterar1.setEnabled(false);
        } else {
            botaAdicionar.setEnabled(false);
            botaoAlterar.setEnabled(true);
        }
    }

    public void activateChangeButtonRegistration() {
        if (jLabelIdUser1.getText().equals("")) {
            botaoAlterar1.setEnabled(false);
            botaAdicionar.setEnabled(true);
        } else {
            botaoAlterar1.setEnabled(true);
            botaAdicionar.setEnabled(false);
        }
    }

    public void openNew() {
        editableLoan(true);
        cleanLoan();
        botaoAlterar1.setEnabled(false);
        botaoSalvar.setEnabled(true);
        botaAdicionar.setEnabled(true);
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
        if (validateVontractNumber() == true) {
            return false;
        }

        return true;
    }

    public boolean fieldValidationRegistration() {

        if (campo_nome1.getText().equals("")) {
            return false;
        }
        if (campo_cep2.getText().equals("")) {
            return false;
        }

        if (validateVontractNumber() == true) {
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

    public void checkCpfDigitsRegistration() {
        if (campo_cpf1.getText().length() > 3) {
            if (campo_cpf1.getText().contains(".")) {
                if (campo_cpf1.getText().length() == 14) {
                    isCpfRegistration();
                }
            } else {
                if (campo_cpf1.getText().length() == 11) {
                    isCpfRegistration();
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

    public void isCpfRegistration() {
        if (ValidateCpf.isCPF(campo_cpf1.getText()) == false) {
            JOptionPane.showMessageDialog(null, "CPF inválido!");
            campo_cpf1.setText("");
        } else {
            getUserRegistrationByPhysicalPersonRegistration();
            buttonControlRegistration();
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
                        loan.getContactNumber(), loan.getIssueDate(), loan.getChangeDate(),
                        loan.getInstitutionEntity().getDescription(),
                        listMov.get(listMov.size() - 1).getOperator(), listMov.get(listMov.size() - 1).getLoanStatutsEnum().name()};
                    dtm.addRow(dados);

                } else {

                    Object[] dadosCan = {loan.getUsrEntity().getPhysicalPersonRegistration(),
                        loan.getContactNumber(), loan.getIssueDate(), loan.getChangeDate(),
                        loan.getInstitutionEntity().getDescription(),
                        listMov.get(listMov.size() - 1).getOperator(), listMov.get(listMov.size() - 1).getLoanStatutsEnum().name()};
                    dtm2.addRow(dadosCan);
                }
            }
        });
    }

    public void userLoanTable(int idUser) {
        clearHistoryTable();
        DefaultTableModel dtm = (DefaultTableModel) tabelaHistoricoUsurio.getModel();

        LoanController loanController = new LoanController();
        LoanMovementController loanMovementController = new LoanMovementController();

        List<LoanEntity> list = Utilities.invertListloan(loanController.getListLoanByUser(idUser));
        list.stream().forEach(loan -> {
            List<LoanMovementEntity> listMov = loanMovementController.listLoanMovementByIdLoan(loan.getId());

            if (listMov.size() > 0) {
                Object[] dados = {loan.getUsrEntity().getPhysicalPersonRegistration(),
                    loan.getContactNumber(), loan.getIssueDate(), loan.getChangeDate(),
                    loan.getInstitutionEntity().getDescription(),
                    listMov.get(listMov.size() - 1).getOperator(), listMov.get(listMov.size() - 1).getLoanStatutsEnum().name()};
                dtm.addRow(dados);
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

    public void clearHistoryTable() {
        DefaultTableModel dfm = (DefaultTableModel) tabelaHistoricoUsurio.getModel();
        while (dfm.getRowCount() > 0) {//TabelaFormaPagamento
            dfm.removeRow(0);
        }
    }

    public boolean validateVontractNumber() {
        LoanController loanController = new LoanController();
        if (loanController.getLoanByContactNumber(campo_numeroContrato.getText()) != null) {
            JOptionPane.showMessageDialog(null, "Numero do contrato já utilizado!");
            return true;
        }
        return false;
    }

    public void getLocateZipCode() {
        if (campo_cep.getText().length() > 7) {
            List<String> zipCode = Utilities.locateZipCode(campo_cep.getText());
            if (zipCode != null) {
                campo_rua.setText(zipCode.get(1));
                campo_cidade.setText(zipCode.get(3));
                campo_bairro.setText(zipCode.get(2));
                combo_uf.setSelectedItem(zipCode.get(4));
            }
        }
    }

    public void getLocateZipCodeRegistration() {
        if (campo_cep2.getText().length() > 7) {
            List<String> zipCode = Utilities.locateZipCode(campo_cep2.getText());
            if (zipCode != null) {
                campo_rua2.setText(zipCode.get(1));
                campo_cidade2.setText(zipCode.get(3));
                campo_bairro2.setText(zipCode.get(2));
                combo_uf2.setSelectedItem(zipCode.get(4));
            }
        }
    }

    public void getListBirthdaysOfTheMonth() {
        UserController userController = new UserController();
        clearBirthdaysOfTheMonthTable();
        DefaultTableModel dtm = (DefaultTableModel) tabelaAniversariantes.getModel();

        userController.birthdaysOfTheMonth().stream().forEach(user -> {
            Object[] dados = {user.getId(), user.getName(), user.getPhysicalPersonRegistration(), user.getBirthDate()};
            dtm.addRow(dados);
        });
    }

    public void clearBirthdaysOfTheMonthTable() {
        DefaultTableModel dfm = (DefaultTableModel) tabelaAniversariantes.getModel();
        while (dfm.getRowCount() > 0) {
            dfm.removeRow(0);
        }
    }

    public void clearLoanTable() {
        DefaultTableModel dfm = (DefaultTableModel) tabelaLoan.getModel();
        while (dfm.getRowCount() > 0) {
            dfm.removeRow(0);
        }
    }

    public void clearLoanCancTable() {
        DefaultTableModel dfm = (DefaultTableModel) tabelaLoanCanc.getModel();
        while (dfm.getRowCount() > 0) {
            dfm.removeRow(0);
        }
    }

    public void tableEventBirthdaysOfTheMonth() {
        int lin = tabelaAniversariantes.getSelectedRow();
        if (lin != -1) {
            cleanAll();
            campo_cpf.setText((String) tabelaAniversariantes.getValueAt(lin, 2));
            checkCpfDigits();
            jTabbedPane1.setSelectedIndex(1);
        }
    }

    public void attachments() {
        if (!campo_cpf.getText().equals("")) {
            newFolderUser();
            openFolder();
        }
    }

    public void newFolderUser() {
        String files = "C:\\Util\\Usuarios\\";
        PreferencesController preferencesController = new PreferencesController();
        PreferencesEntity preferences = preferencesController.getPreferencesEntity();
        if (preferences != null) {
            if (!preferences.getClass().equals("")) {
                files = preferences.getFiles();
            }
        }
        String cpf = campo_cpf.getText().replace(".", "");
        String way = files + cpf.replace("-", "");
        if (!campo_caminho_file.getText().equals("")) {
            cpf = campo_cpf.getText().replace(".", "");
            way = campo_caminho_file.getText();
        }
        File file = new File(way);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void openFolder() {
        String os = System.getProperty("os.name");
        String cpf = campo_cpf.getText().replace(".", "");
        String way = campo_caminho_file.getText();
        if (campo_caminho_file.getText().equals("")) {
            way = "C:\\Util\\Usuarios\\";
        }
        if (os.startsWith("Win")) {
            try {
                Runtime.getRuntime().exec("explorer " + way);
            } catch (IOException ex) {
                Logger.getLogger(MainScreenView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void selectDestinationFolder() {
        JFileChooser file = new JFileChooser();
        file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int i = file.showSaveDialog(null);
        if (i == 1) {
            campo_caminho_file.setText("");
        } else {
            File arquivo = file.getSelectedFile();
            campo_caminho_file.setText(arquivo.getPath());
        }
    }

    public void tabs() {
        tabOne();
        tabTwo();
        tabThree();
        tabFour();
    }

    public void tabOne() {

        JLabel lbl = new JLabel("");
        Icon icon = new ImageIcon(getClass().getResource("/Resources/icons/birthday_cake_30px.png"));
        lbl.setIcon(icon);

        lbl.setIconTextGap(5);
        lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        jTabbedPane1.setTabComponentAt(0, lbl);
    }

    public void tabTwo() {

        JLabel lbl = new JLabel("");
        Icon icon = new ImageIcon(getClass().getResource("/Resources/icons/budget_30px.png"));
        lbl.setIcon(icon);

        lbl.setIconTextGap(5);
        lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        jTabbedPane1.setTabComponentAt(1, lbl);
    }

    public void tabThree() {

        JLabel lbl = new JLabel("");
        Icon icon = new ImageIcon(getClass().getResource("/Resources/icons/To Do_30px.png"));
        lbl.setIcon(icon);

        lbl.setIconTextGap(5);
        lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        jTabbedPane1.setTabComponentAt(2, lbl);
    }

    public void tabFour() {

        JLabel lbl = new JLabel("");
        Icon icon = new ImageIcon(getClass().getResource("/Resources/icons/add_user_group_man_man_30px.png"));
        lbl.setIcon(icon);

        lbl.setIconTextGap(5);
        lbl.setHorizontalTextPosition(SwingConstants.RIGHT);
        jTabbedPane1.setTabComponentAt(3, lbl);
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
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelaAniversariantes = new javax.swing.JTable();
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
        jcalenar_data_nascimento = new com.toedter.calendar.JDateChooser();
        jTabbedPane2 = new javax.swing.JTabbedPane();
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
        botaoAnexos = new javax.swing.JButton();
        botaoSetPreferences = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaHistoricoUsurio = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaLoan = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaLoanCanc = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        campo_cpf1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        campo_nome1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        campo_mae1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        campo_fone3 = new javax.swing.JTextField();
        combo_tipo_fone3 = new javax.swing.JComboBox<>();
        jLabel43 = new javax.swing.JLabel();
        campo_rg1 = new javax.swing.JTextField();
        jLabelIdUser1 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        campo_orgao_emissor1 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jcalenar_data_nascimento1 = new com.toedter.calendar.JDateChooser();
        jLabel49 = new javax.swing.JLabel();
        campo_pai1 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        campo_fone4 = new javax.swing.JTextField();
        combo_tipo_fone4 = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        campo_naturalidade1 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        campo_emissor1 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        campo_conjugue1 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        campo_email1 = new javax.swing.JTextField();
        combobox_sexo1 = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        jLabel62 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        campo_cep2 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        campo_rua2 = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        campo_numero2 = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        campo_complemento2 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        campo_bairro2 = new javax.swing.JTextField();
        combo_uf2 = new javax.swing.JComboBox<>();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        campo_cidade2 = new javax.swing.JTextField();
        jLabelId2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        botaAdicionar = new javax.swing.JButton();
        botaoAlterar1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        jPanel8.setToolTipText("ANIVERSARIANTES");

        tabelaAniversariantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nome", "CPF", "Data de nascimento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaAniversariantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaAniversariantesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tabelaAniversariantes);
        if (tabelaAniversariantes.getColumnModel().getColumnCount() > 0) {
            tabelaAniversariantes.getColumnModel().getColumn(0).setMinWidth(50);
            tabelaAniversariantes.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabelaAniversariantes.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelaAniversariantes.getColumnModel().getColumn(2).setMinWidth(300);
            tabelaAniversariantes.getColumnModel().getColumn(2).setPreferredWidth(300);
            tabelaAniversariantes.getColumnModel().getColumn(2).setMaxWidth(300);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel8);

        jPanel7.setToolTipText("NOVA PROPOSTA");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("CPF*:");

        campo_cpf.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_cpf.setText("101");
        campo_cpf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_cpfKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Nome*:");

        campo_nome.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_nome.setText("nome1");
        campo_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_nomeActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons/plus_20px.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        combobox_sexo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        campo_rg.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_rg.setText("00001");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("RG:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Orgão Emissor:");

        campo_orgao_emissor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_orgao_emissor.setText("orgao emisso");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Emissor:");

        campo_emissor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_emissor.setText("emissor");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Data Nasc:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Naturalidade:");

        campo_naturalidade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_naturalidade.setText("mamanguape");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel19.setText("Email:");

        campo_email.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_email.setText("@email");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Pai:");

        campo_pai.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_pai.setText("pai");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setText("Mãe:");

        campo_mae.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_mae.setText("mae");
        campo_mae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_maeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Cônjuge:");

        campo_conjugue.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_conjugue.setText("conjuge");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel20.setText("Fone:");

        campo_fone1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_fone1.setText("2222222");

        combo_tipo_fone1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_tipo_fone1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Celular", "Residencial", "Casa", "Trabalho" }));

        jLabel21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel21.setText("Fone:");

        campo_fone2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_fone2.setText("333333");

        combo_tipo_fone2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_tipo_fone2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Celular", "Residencial", "Casa", "Trabalho" }));

        jLabel40.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel40.setText("Status atual:");

        combo_status.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DIGITADO", "FINALIZADO", "CANCELADO" }));

        botaoNovaProtosta.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        botaoNovaProtosta.setText("Nova Proposta");
        botaoNovaProtosta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovaProtostaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.darkGray), "ENDEREÇO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("CEP*:");

        campo_cep.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_cep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_cepKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("Rua:");

        campo_rua.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_rua.setText("rua");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel14.setText("Numero:");

        campo_numero.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_numero.setText("numero");
        campo_numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_numeroActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel15.setText("Complemento:");

        campo_complemento.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_complemento.setText("complemento");

        jLabel16.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel16.setText("Bairro:");

        campo_bairro.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_bairro.setText("bairro");

        combo_uf.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_uf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_ufActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel17.setText("UF:");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel18.setText("Cidade:");

        campo_cidade.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
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
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campo_rua, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addComponent(campo_bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campo_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_uf, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(campo_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campo_complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(campo_rua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campo_complemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)
                        .addComponent(campo_numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)
                        .addComponent(campo_cep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
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

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.darkGray), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setText("Corretor:");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel24.setText("Banco Origem*:");

        jLabel25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel25.setText("Convênio*:");

        jLabel26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel26.setText("Data Emissão:");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel27.setText("Comissão:");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel28.setText("Nº Contrato*:");

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

        campo_corretor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_corretor.setText("corretor");

        campo_valorliquido.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_valorliquido.setText("1800");
        campo_valorliquido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_valorliquidoActionPerformed(evt);
            }
        });

        campo_qtd_parcelas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_qtd_parcelas.setText("10");

        campo_valorparcela.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_valorparcela.setText("200");

        campo_numeroContrato.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_numeroContrato.setText("222222");

        campo_numero_beneficio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
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
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
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

        campo_ADE.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_ADE.setText("333221");
        campo_ADE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_ADEActionPerformed(evt);
            }
        });

        campo_valorbruto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_valorbruto.setText("2000");

        campo_conta_ben.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_conta_ben.setText("220012");

        campo_codigoespecie.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_codigoespecie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_codigoespecieActionPerformed(evt);
            }
        });

        combo_operacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PORTABILIDADE", "REFINANCIAMENTO", "MARGEM_LIVRE", "CARTAO_RMC", "EMPRESTIMO_CARTAO", "CARTAO_BENEFICIARIO", "FGTS" }));

        campo_digitador.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_digitador.setText("digitador");

        campo_comissao.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_comissao.setText("10");

        campo_agencia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_agencia.setText("0000021");

        campo_obs.setColumns(20);
        campo_obs.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_obs.setRows(5);
        campo_obs.setText("obs");
        jScrollPane1.setViewportView(campo_obs);

        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botaoSalvar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jButton3.setText("Limpar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        botaoAlterar.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
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

        campo_caminho_file.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons/search_folder_20px.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons/plus_20px.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons/plus_20px.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons/plus_20px.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        botaoAnexos.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        botaoAnexos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons/attach_32px.png"))); // NOI18N
        botaoAnexos.setText("Anexos");
        botaoAnexos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAnexosActionPerformed(evt);
            }
        });

        botaoSetPreferences.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        botaoSetPreferences.setText("Definir Pasta");
        botaoSetPreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSetPreferencesActionPerformed(evt);
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(campo_dataemissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(72, 72, 72))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campo_corretor, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campo_comissao, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(combo_convenio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(combo_bancoOrigem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                                .addGap(13, 13, 13)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel35)))
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(campo_valorbruto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campo_valorliquido, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(campo_qtd_parcelas)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(campo_valorparcela, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163)
                        .addComponent(jLabel36))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campo_caminho_file)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(botaoAnexos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(botaoSetPreferences, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addGap(15, 15, 15))
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
                                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(combo_convenio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel32)
                                                .addComponent(campo_numero_beneficio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campo_caminho_file, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(botaoAnexos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(botaoSetPreferences, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(2, 2, 2))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48)
                        .addComponent(campo_ADE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campo_valorbruto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(campo_valorliquido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campo_qtd_parcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campo_valorparcela)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(combo_banco_beneficiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campo_agencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campo_conta_ben, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campo_codigoespecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(49, 49, 49))
        );

        jTabbedPane2.addTab("DADOS PARA LIBERAÇÃO", jPanel5);

        tabelaHistoricoUsurio.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelaHistoricoUsurio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaHistoricoUsurioMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelaHistoricoUsurio);
        if (tabelaHistoricoUsurio.getColumnModel().getColumnCount() > 0) {
            tabelaHistoricoUsurio.getColumnModel().getColumn(0).setHeaderValue("CPF");
            tabelaHistoricoUsurio.getColumnModel().getColumn(5).setHeaderValue("Operador");
            tabelaHistoricoUsurio.getColumnModel().getColumn(6).setHeaderValue("Status");
        }

        jTabbedPane2.addTab("         HISTÓRICO DO USUÁRIO", jScrollPane4);

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
                                .addComponent(campo_emissor, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(combobox_sexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(campo_conjugue))
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(combo_status, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(botaoNovaProtosta))))))))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTabbedPane2)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(75, 75, 75))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(combobox_sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campo_emissor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(campo_rg, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabelIdUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                            .addComponent(campo_orgao_emissor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(1, 1, 1))
                        .addComponent(campo_cpf, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcalenar_data_nascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campo_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campo_naturalidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campo_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo_mae, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo_pai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campo_conjugue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );

        jTabbedPane1.addTab("", jPanel7);

        jPanel4.setToolTipText("LISTA PROPOSTAS");

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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1297, Short.MAX_VALUE)
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

        jTabbedPane1.addTab("", jPanel4);

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DADOS PESSOAIS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("CPF*:");

        campo_cpf1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_cpf1.setText("101");
        campo_cpf1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_cpf1KeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel22.setText("Nome*:");

        campo_nome1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_nome1.setText("nome1");
        campo_nome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_nome1ActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel41.setText("Mãe:");

        campo_mae1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_mae1.setText("mae");
        campo_mae1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_mae1ActionPerformed(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel42.setText("Fone:");

        campo_fone3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_fone3.setText("2222222");

        combo_tipo_fone3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_tipo_fone3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Celular", "Residencial", "Casa", "Trabalho" }));

        jLabel43.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel43.setText("RG:");

        campo_rg1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_rg1.setText("00001");

        jLabel44.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("Orgão Emissor:");

        campo_orgao_emissor1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_orgao_emissor1.setText("orgao emisso");

        jLabel47.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel47.setText("Data Nasc:");

        jLabel49.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel49.setText("Pai:");

        campo_pai1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_pai1.setText("pai");
        campo_pai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_pai1ActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel50.setText("Fone:");

        campo_fone4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_fone4.setText("333333");

        combo_tipo_fone4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_tipo_fone4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Celular", "Residencial", "Casa", "Trabalho" }));
        combo_tipo_fone4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_tipo_fone4ActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel51.setText("Naturalidade:");

        campo_naturalidade1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_naturalidade1.setText("mamanguape");

        jLabel52.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel52.setText("Emissor:");

        campo_emissor1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_emissor1.setText("emissor");

        jLabel53.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel53.setText("Cônjuge:");

        campo_conjugue1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_conjugue1.setText("conjuge");

        jLabel54.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel54.setText("Email:");

        campo_email1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_email1.setText("@email");

        combobox_sexo1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/icons/plus_20px.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel62.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel62.setText("Sexo:");

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jCheckBox1.setText("Admin");

        jCheckBox2.setText("Gestão");

        jCheckBox3.setText("Funcionario");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.setText("Cliente");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(jCheckBox2)
                .addGap(74, 74, 74)
                .addComponent(jCheckBox3)
                .addGap(98, 98, 98)
                .addComponent(jCheckBox4)
                .addGap(28, 28, 28))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campo_orgao_emissor1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campo_rg1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campo_cpf1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                            .addComponent(campo_emissor1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabelIdUser1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(campo_fone4)
                            .addComponent(campo_fone3, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(combo_tipo_fone3, 0, 105, Short.MAX_VALUE)
                            .addComponent(combo_tipo_fone4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(campo_naturalidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel41))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                .addComponent(jcalenar_data_nascimento1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campo_nome1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(combobox_sexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(campo_mae1)
                            .addComponent(campo_pai1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campo_conjugue1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                            .addComponent(campo_email1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campo_cpf1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(campo_nome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campo_conjugue1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelIdUser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campo_rg1)
                                .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campo_pai1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jcalenar_data_nascimento1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campo_orgao_emissor1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(campo_naturalidade1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campo_emissor1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(combobox_sexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(campo_mae1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campo_email1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campo_fone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_tipo_fone3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campo_fone4)
                            .addComponent(combo_tipo_fone4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(98, 98, 98))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.darkGray), "ENDEREÇO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        jLabel63.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel63.setText("CEP*:");

        campo_cep2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_cep2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_cep2KeyReleased(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel64.setText("Rua:");

        campo_rua2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_rua2.setText("rua");

        jLabel65.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel65.setText("Numero:");

        campo_numero2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_numero2.setText("numero");
        campo_numero2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_numero2ActionPerformed(evt);
            }
        });

        jLabel66.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel66.setText("Complemento:");

        campo_complemento2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_complemento2.setText("complemento");

        jLabel67.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel67.setText("Bairro:");

        campo_bairro2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_bairro2.setText("bairro");

        combo_uf2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_uf2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_uf2ActionPerformed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel68.setText("UF:");

        jLabel69.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel69.setText("Cidade:");

        campo_cidade2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_cidade2.setText("cidade");
        campo_cidade2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_cidade2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campo_cep2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelId2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(campo_rua2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel65)))
                    .addComponent(campo_bairro2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(campo_cidade2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_uf2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(campo_numero2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campo_complemento2, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelId2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel64)
                        .addComponent(campo_rua2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(campo_complemento2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel65)
                        .addComponent(campo_numero2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel63)
                        .addComponent(campo_cep2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel67)
                        .addComponent(campo_bairro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel69)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combo_uf2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(campo_cidade2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "LOGIN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 412, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 157, Short.MAX_VALUE)
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        botaAdicionar.setText("ADICIONAR");
        botaAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaAdicionarActionPerformed(evt);
            }
        });

        botaoAlterar1.setText("ALTERAR");
        botaoAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterar1ActionPerformed(evt);
            }
        });

        jButton5.setText("LIMPAR TELA");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(botaAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoAlterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAlterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(185, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("", jPanel2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
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
                .addGap(0, 9, Short.MAX_VALUE))
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

        selectDestinationFolder();
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

    private void tabelaHistoricoUsurioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaHistoricoUsurioMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaHistoricoUsurioMouseClicked

    private void campo_cepKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_cepKeyReleased
        getLocateZipCode();
    }//GEN-LAST:event_campo_cepKeyReleased

    private void tabelaAniversariantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAniversariantesMouseClicked
        tableEventBirthdaysOfTheMonth();
    }//GEN-LAST:event_tabelaAniversariantesMouseClicked

    private void botaoAnexosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAnexosActionPerformed
        attachments();
    }//GEN-LAST:event_botaoAnexosActionPerformed

    private void campo_cpf1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_cpf1KeyReleased
        checkCpfDigitsRegistration();
    }//GEN-LAST:event_campo_cpf1KeyReleased

    private void campo_nome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_nome1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_nome1ActionPerformed

    private void campo_mae1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_mae1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_mae1ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        insertGenre();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void campo_pai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_pai1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_pai1ActionPerformed

    private void combo_tipo_fone4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_tipo_fone4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_tipo_fone4ActionPerformed

    private void campo_cep2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_cep2KeyReleased
        getLocateZipCodeRegistration();
    }//GEN-LAST:event_campo_cep2KeyReleased

    private void campo_numero2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_numero2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_numero2ActionPerformed

    private void combo_uf2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_uf2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_uf2ActionPerformed

    private void campo_cidade2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_cidade2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_cidade2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void botaAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaAdicionarActionPerformed
        insertRegistrationUser();
    }//GEN-LAST:event_botaAdicionarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        cleanAll();
        buttonControlRegistration();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void botaoAlterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterar1ActionPerformed
        if (updateUserRegistration() != null) {
            JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!");
        }
    }//GEN-LAST:event_botaoAlterar1ActionPerformed

    private void campo_valorliquidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_valorliquidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_valorliquidoActionPerformed

    private void botaoSetPreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSetPreferencesActionPerformed
        filePreferences();
    }//GEN-LAST:event_botaoSetPreferencesActionPerformed

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
    private javax.swing.JButton botaAdicionar;
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoAlterar1;
    private javax.swing.JButton botaoAnexos;
    private javax.swing.JButton botaoNovaProtosta;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton botaoSetPreferences;
    private javax.swing.JTextField campo_ADE;
    private javax.swing.JTextField campo_agencia;
    private javax.swing.JTextField campo_bairro;
    private javax.swing.JTextField campo_bairro2;
    private javax.swing.JTextField campo_caminho_file;
    private javax.swing.JTextField campo_cep;
    private javax.swing.JTextField campo_cep2;
    private javax.swing.JTextField campo_cidade;
    private javax.swing.JTextField campo_cidade2;
    private javax.swing.JTextField campo_codigoespecie;
    private javax.swing.JTextField campo_comissao;
    private javax.swing.JTextField campo_complemento;
    private javax.swing.JTextField campo_complemento2;
    private javax.swing.JTextField campo_conjugue;
    private javax.swing.JTextField campo_conjugue1;
    private javax.swing.JTextField campo_conta_ben;
    private javax.swing.JTextField campo_corretor;
    private javax.swing.JTextField campo_cpf;
    private javax.swing.JTextField campo_cpf1;
    private com.toedter.calendar.JDateChooser campo_dataemissao;
    private javax.swing.JTextField campo_digitador;
    private javax.swing.JTextField campo_email;
    private javax.swing.JTextField campo_email1;
    private javax.swing.JTextField campo_emissor;
    private javax.swing.JTextField campo_emissor1;
    private javax.swing.JTextField campo_fone1;
    private javax.swing.JTextField campo_fone2;
    private javax.swing.JTextField campo_fone3;
    private javax.swing.JTextField campo_fone4;
    private javax.swing.JTextField campo_mae;
    private javax.swing.JTextField campo_mae1;
    private javax.swing.JTextField campo_naturalidade;
    private javax.swing.JTextField campo_naturalidade1;
    private javax.swing.JTextField campo_nome;
    private javax.swing.JTextField campo_nome1;
    private javax.swing.JTextField campo_numero;
    private javax.swing.JTextField campo_numero2;
    private javax.swing.JTextField campo_numeroContrato;
    private javax.swing.JTextField campo_numero_beneficio;
    private javax.swing.JTextArea campo_obs;
    private javax.swing.JTextField campo_orgao_emissor;
    private javax.swing.JTextField campo_orgao_emissor1;
    private javax.swing.JTextField campo_pai;
    private javax.swing.JTextField campo_pai1;
    private javax.swing.JTextField campo_qtd_parcelas;
    private javax.swing.JTextField campo_rg;
    private javax.swing.JTextField campo_rg1;
    private javax.swing.JTextField campo_rua;
    private javax.swing.JTextField campo_rua2;
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
    private javax.swing.JComboBox<String> combo_tipo_fone3;
    private javax.swing.JComboBox<String> combo_tipo_fone4;
    private javax.swing.JComboBox<String> combo_uf;
    private javax.swing.JComboBox<String> combo_uf2;
    private javax.swing.JComboBox<String> combobox_sexo;
    private javax.swing.JComboBox<String> combobox_sexo1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelId2;
    private javax.swing.JLabel jLabelIdUser;
    private javax.swing.JLabel jLabelIdUser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private com.toedter.calendar.JDateChooser jcalenar_data_nascimento;
    private com.toedter.calendar.JDateChooser jcalenar_data_nascimento1;
    private javax.swing.JTable tabelaAniversariantes;
    private javax.swing.JTable tabelaHistoricoUsurio;
    private javax.swing.JTable tabelaLoan;
    private javax.swing.JTable tabelaLoanCanc;
    // End of variables declaration//GEN-END:variables

    private String getSelectedItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
