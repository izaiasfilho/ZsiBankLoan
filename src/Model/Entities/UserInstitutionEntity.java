/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

/**
 *
 * @author Izaias
 */

public class UserInstitutionEntity {
    
    private int id;
    private UserEntity userEntity;
    private InstitutionEntity institutionUser;
    private String agency;
    private String accountNumber;

    public InstitutionEntity getInstitutionUser() {
        return institutionUser;
    }

    public void setInstitutionUser(InstitutionEntity institutionUser) {
        this.institutionUser = institutionUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
}
