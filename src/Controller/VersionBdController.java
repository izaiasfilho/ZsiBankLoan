/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Model.Services.Implementations.ScriptImp.updateBankVersionImp;

/**
 *
 * @author Izaias
 */
public class VersionBdController {
    
    public static int checkLatestBankVersion(){
        return 0;
    }
    
    public static void updateBankVersion(){
        updateBankVersionImp();
    }
    
}
