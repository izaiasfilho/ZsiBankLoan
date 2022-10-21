/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.LoanMovementEntity;
import Model.Persistence.LoanMovementPersistence;
import Model.Services.Interfaces.LoanMovementInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class LoanMovementImplemetation implements LoanMovementInterface{

    @Override
    public boolean insertLoanMovement(LoanMovementEntity loanMovementEntity) {
        try {
            return LoanMovementPersistence.insertLoanMovementPersistence(loanMovementEntity);
        } catch (SQLException ex) {
            Logger.getLogger(LoanMovementImplemetation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public LoanMovementEntity getLoanMovementByIdLoan(int idLoan) {
        return LoanMovementPersistence.getLoanloanMovementByIdLoanPersistence(idLoan);
    }

    @Override
    public List<LoanMovementEntity> listLoanMovementByIdLoan(int idLoan) {
        return LoanMovementPersistence.getListLoanByUserPersistence(idLoan);
    }
    
}
