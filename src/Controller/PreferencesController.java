/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.PreferencesEntity;
import Model.Services.Implementations.PreferencesImplemetation;
import Model.Services.Interfaces.PreferenceslInterface;

/**
 *
 * @author Izaias
 */
public class PreferencesController {
    PreferenceslInterface repository = new PreferencesImplemetation();
    
   
    public boolean updatePreferences(String file){
        return repository.updatePreferences(file);
    }
    
    public PreferencesEntity getPreferencesEntity(){
        return repository.getPreferencesEntity();
    }
}
