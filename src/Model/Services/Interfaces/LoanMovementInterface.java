/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.LoanMovementEntity;
import java.util.List;

/**
 *
 * @author Izaias
 */
public interface LoanMovementInterface {
    
    public boolean insertLoanMovement(LoanMovementEntity loanMovementEntity);
    
    public LoanMovementEntity getLoanMovementByIdLoan(int idLoan);
    
    public List<LoanMovementEntity> listLoanMovementByIdLoan(int idLoan);
}
