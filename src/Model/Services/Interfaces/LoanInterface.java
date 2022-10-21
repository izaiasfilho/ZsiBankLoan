/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.LoanEntity;
import java.util.List;

/**
 *
 * @author Izaias
 */
public interface LoanInterface {
    
    public boolean insertLoan(LoanEntity loanEntity);
    
    public boolean updateLoan(LoanEntity loanEntity);
    
    public LoanEntity getLoanByUser(int idUser);
    
    public List<LoanEntity> getListLoanByUser(int idUser);
    
    public List<LoanEntity> getListAllLoan();
    
    public LoanEntity getLoanByContactNumber(String contactNumber);
}
