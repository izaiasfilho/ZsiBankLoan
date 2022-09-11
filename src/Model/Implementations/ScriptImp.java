/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Implementations;

import Persistence.VersionBdPersistence;
import static Resources.BD.QuerySequency.listQueryVersion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Izaias
 */

public class ScriptImp {
    
    public static void updateBankVersionImp(){
         try {   
            VersionBdPersistence.updateBankVersionPersistence(listQueryVersion());
        } catch (SQLException ex) {
            Logger.getLogger(ScriptImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
