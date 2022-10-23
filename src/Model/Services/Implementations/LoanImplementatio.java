/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.LoanEntity;
import Model.Persistence.LoanPersistence;
import static Model.Persistence.LoanPersistence.getListLoanPersistence;
import Model.Services.Interfaces.LoanInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class LoanImplementatio implements LoanInterface {

    @Override
    public boolean insertLoan(LoanEntity loanEntity) {
        try {
            if (getLoanByContactNumber(loanEntity.getContactNumber()) == null) {
                return LoanPersistence.insertLoanPersistence(loanEntity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoanImplementatio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateLoan(LoanEntity loanEntity) {
        loanEntity.setId(getLoanByContactNumber(loanEntity.getContactNumber()).getId());
        return LoanPersistence.updateLoanPersistence(loanEntity);
    }

    @Override
    public LoanEntity getLoanByUser(int idUser) {
        return LoanPersistence.getLoanByIdPersistence(idUser);
    }

    @Override
    public List<LoanEntity> getListLoanByUser(int idUser) {
        return LoanPersistence.getListLoanByUserPersistence(idUser);
    }

    @Override
    public List<LoanEntity> getListAllLoan() {
        return getListLoanPersistence();
    }

    @Override
    public LoanEntity getLoanByContactNumber(String contactNumber) {
        return LoanPersistence.getLoanByContactNumberPersistence(contactNumber);
    }

}
