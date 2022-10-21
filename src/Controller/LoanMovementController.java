/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.LoanMovementEntity;
import Model.Services.Implementations.LoanMovementImplemetation;
import Model.Services.Interfaces.LoanMovementInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class LoanMovementController {
    LoanMovementInterface repository = new LoanMovementImplemetation();
    
    public boolean insertLoanMovement(LoanMovementEntity loanMovementEntity){
        return repository.insertLoanMovement(loanMovementEntity);
    }
    
    public LoanMovementEntity getLoanMovementByIdLoan(int idLoan){
        return repository.getLoanMovementByIdLoan(idLoan);
    }
    
    public List<LoanMovementEntity> listLoanMovementByIdLoan(int idLoan){
        return repository.listLoanMovementByIdLoan(idLoan);
    }
}
