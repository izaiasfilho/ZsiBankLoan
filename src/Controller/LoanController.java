/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.LoanEntity;
import Model.Services.Implementations.LoanImplementatio;
import Model.Services.Interfaces.LoanInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class LoanController {
    LoanInterface repository = new LoanImplementatio();
    
    
    public boolean insertLoan(LoanEntity loanEntity){
        return repository.insertLoan(loanEntity);
    }
    
    public boolean updateLoan(LoanEntity loanEntity){
        return repository.updateLoan(loanEntity);
    }
    
    public LoanEntity getLoanByUser(int idUser){
        return repository.getLoanByUser(idUser);
    }
    
    public List<LoanEntity> getListLoanByUser(int idUser){
        return repository.getListLoanByUser(idUser);
    }
    
    public List<LoanEntity> getListAllLoan(){
        return repository.getListAllLoan();
    }
    
    public LoanEntity getLoanByContactNumber(String contactNumber){
        return repository.getLoanByContactNumber(contactNumber);
    }
}
