/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.VersionBdEntity;
import Model.Services.Implementations.VersionBdImp;
import java.util.ArrayList;

/**
 *
 * @author Izaias
 */
public class VersionBdController {

    public boolean checkLatestBankVersionController() {
        VersionBdImp imp = new VersionBdImp();
        return imp.checkExistTbVersionImp();
    }

    public void updateBankVersion() {
            VersionBdImp imp = new VersionBdImp();
            imp.updateBankVersionImp();
    }
    
    public ArrayList<VersionBdEntity> listVersionsBd(){
        VersionBdImp imp = new VersionBdImp();
        return imp.getListVersionBdImp();
    }
    
    
}
