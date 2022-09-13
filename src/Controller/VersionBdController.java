/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Services.Implementations.VersionBdImp;


/**
 *
 * @author Izaias
 */
public class VersionBdController {
    
    
    public static int checkLatestBankVersion(){
        return 0;
    }
    
    public void updateBankVersion(){
        VersionBdImp imp = new VersionBdImp();
        imp.updateBankVersionImp();
    }
    
}
