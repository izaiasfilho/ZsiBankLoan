/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.PreferencesEntity;

/**
 *
 * @author Izaias
 */
public interface PreferenceslInterface {
    
    public boolean updatePreferences(String file);
    
    public PreferencesEntity getPreferencesEntity();
    
    public boolean insertPreferences(String file);
}
