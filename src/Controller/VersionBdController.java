/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.VersionBdEntity;
import Model.Services.Implementations.VersionBdImplemetatiion;
import java.util.ArrayList;

/**
 *
 * @author Izaias
 */
public class VersionBdController {

    public boolean checkLatestBankVersionController() {
        VersionBdImplemetatiion imp = new VersionBdImplemetatiion();
        return imp.checkExistTbVersionImp();
    }

    public void updateBankVersion() {
            VersionBdImplemetatiion imp = new VersionBdImplemetatiion();
            imp.updateBankVersionImp();
    }
    
    public ArrayList<VersionBdEntity> listVersionsBd(){
        VersionBdImplemetatiion imp = new VersionBdImplemetatiion();
        return imp.getListVersionBdImp();
    }
    
    
}
