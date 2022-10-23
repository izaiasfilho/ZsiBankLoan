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
public class LoanEntity {
    
    private int id;
    private UserEntity usrEntity;
    private String issueDate;
    private String changeDate;
    private String contactNumber;
    private UserInstitutionEntity userInstitutionEntity;

    public UserInstitutionEntity getUserInstitutionEntity() {
        return userInstitutionEntity;
    }

    public void setUserInstitutionEntity(UserInstitutionEntity userInstitutionEntity) {
        this.userInstitutionEntity = userInstitutionEntity;
    }
    

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserEntity getUsrEntity() {
        return usrEntity;
    }

    public void setUsrEntity(UserEntity usrEntity) {
        this.usrEntity = usrEntity;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    
}
