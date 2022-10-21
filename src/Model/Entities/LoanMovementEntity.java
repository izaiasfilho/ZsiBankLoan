/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import Model.Enuns.LoanStatusEnum;
import Model.Enuns.TransactionEnum;

/**
 *
 * @author Izaias
 */
public class LoanMovementEntity {
 
    
    private int id;
    private LoanEntity loanEntity;
    private String date;
    private InstitutionEntity institutionEntity;
    private PlanEntity planEntity;
    private TransactionEnum transactionEnum;
    private UserInstitutionEntity userInstitutionEntity;
    private LoanStatusEnum loanStatutsEnum;
    private String broker;
    private String commission;
    private double grossValue;
    private double netValue;
    private double amountOfInstallments;
    private double valueInstallments;
    private String operator;
    private String note;
    private String files;
    private String numberADE;
    private String benefitNumber;
    private String speciesCode;
    private String typist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LoanEntity getLoanEntity() {
        return loanEntity;
    }

    public void setLoanEntity(LoanEntity loanEntity) {
        this.loanEntity = loanEntity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public InstitutionEntity getInstitutionEntity() {
        return institutionEntity;
    }

    public void setInstitutionEntity(InstitutionEntity institutionEntity) {
        this.institutionEntity = institutionEntity;
    }

    public PlanEntity getPlanEntity() {
        return planEntity;
    }

    public void setPlanEntity(PlanEntity planEntity) {
        this.planEntity = planEntity;
    }

    public TransactionEnum getTransactionEnum() {
        return transactionEnum;
    }

    public void setTransactionEnum(TransactionEnum transactionEnum) {
        this.transactionEnum = transactionEnum;
    }

    public UserInstitutionEntity getUserInstitutionEntity() {
        return userInstitutionEntity;
    }

    public void setUserInstitutionEntity(UserInstitutionEntity userInstitutionEntity) {
        this.userInstitutionEntity = userInstitutionEntity;
    }

    public LoanStatusEnum getLoanStatutsEnum() {
        return loanStatutsEnum;
    }

    public void setLoanStatutsEnum(LoanStatusEnum loanStatutsEnum) {
        this.loanStatutsEnum = loanStatutsEnum;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public double getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(double grossValue) {
        this.grossValue = grossValue;
    }

    public double getNetValue() {
        return netValue;
    }

    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

    public double getAmountOfInstallments() {
        return amountOfInstallments;
    }

    public void setAmountOfInstallments(double amountOfInstallments) {
        this.amountOfInstallments = amountOfInstallments;
    }

    public double getValueInstallments() {
        return valueInstallments;
    }

    public void setValueInstallments(double valueInstallments) {
        this.valueInstallments = valueInstallments;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getNumberADE() {
        return numberADE;
    }

    public void setNumberADE(String numberADE) {
        this.numberADE = numberADE;
    }

    public String getBenefitNumber() {
        return benefitNumber;
    }

    public void setBenefitNumber(String benefitNumber) {
        this.benefitNumber = benefitNumber;
    }

    public String getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(String speciesCode) {
        this.speciesCode = speciesCode;
    }

    public String getTypist() {
        return typist;
    }

    public void setTypist(String typist) {
        this.typist = typist;
    }
    
}
