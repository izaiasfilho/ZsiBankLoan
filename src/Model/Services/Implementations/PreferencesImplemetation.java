/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.PreferencesEntity;
import Model.Persistence.PreferencesPersistence;
import Model.Services.Interfaces.PreferenceslInterface;

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

    
}
