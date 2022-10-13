/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.VersionBdEntity;
import Model.Services.Implementations.VersionBdImplemetatiion;
import Model.Services.Interfaces.VersionBdInterface;
import java.util.ArrayList;

/**
 *
 * @author Izaias
 */
public class VersionBdController {
    VersionBdInterface versionInterface = new VersionBdImplemetatiion();
    
    public boolean checkLatestBankVersionController() {
        return versionInterface.checkExistTbVersionImp();
    }

    public void updateBankVersion() {
            versionInterface.updateBankVersionImp();
    }
    
    public ArrayList<VersionBdEntity> listVersionsBd(){
        return versionInterface.getListVersionBdImp();
    }
    
    
}
