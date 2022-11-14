/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.PreferencesEntity;
import Model.Persistence.PreferencesPersistence;
import Model.Services.Interfaces.PreferenceslInterface;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class PreferencesImplemetation implements PreferenceslInterface{

    @Override
    public boolean updatePreferences(String file) {
        return PreferencesPersistence.updateFilesPreferencesEntityPersistence(file);
    }

    @Override
    public PreferencesEntity getPreferencesEntity() {
        return PreferencesPersistence.getPreferencesEntityPersistence();
    }

    @Override
    public boolean insertPreferences(String file) {
        try {
            return PreferencesPersistence.insertPreferencesPersistence(file);
        } catch (SQLException ex) {
            Logger.getLogger(PreferencesImplemetation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
}
