/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.VersionBdEntity;
import Model.Persistence.VersionBdPersistence;
import static Model.Services.Implementations.ResultsetConvert.VersionBdResultsetConvert.checkExistTbVersionResultsetConvert;
import static Model.Services.Implementations.ResultsetConvert.VersionBdResultsetConvert.getListVersionBdResultsetConvert;
import Model.Services.Interfaces.VersionBdInterface;
import static Resources.BD.QuerySequency.listQueryVersion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Izaias
 */

public class VersionBdImp implements VersionBdInterface{
    
    @Override
    public void updateBankVersionImp(){
         try {   
            VersionBdPersistence.updateBankVersionPersistence(listQueryVersion());
        } catch (SQLException ex) {
            Logger.getLogger(VersionBdImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<VersionBdEntity> getListVersionBdImp() {
        return getListVersionBdResultsetConvert();
    }

    @Override
    public boolean checkExistTbVersionImp() {
       return checkExistTbVersionResultsetConvert();
    }

    

        
}
