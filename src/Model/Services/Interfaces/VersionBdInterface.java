/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.VersionBdEntity;
import Model.Enuns.TypetransactionsSql;
import java.util.ArrayList;

/**
 *
 * @author Izaias
 */
public interface VersionBdInterface {
    
    public void updateBankVersionImp();
    
    public ArrayList<VersionBdEntity> getListVersionBdImp();
    
    public boolean checkExistTbVersionImp();
        
    public int lastVersionBd();
    
    public void versionControl();
    
    public void updateVersion(int i);
    
    public TypetransactionsSql convertStringToTypeTransactions(String type);
    
}
