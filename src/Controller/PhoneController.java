/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.PhoneEntity;
import Model.Services.Implementations.PhoneImplemetation;
import Model.Services.Interfaces.PhoneInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class PhoneController {
    PhoneInterface repository = new PhoneImplemetation();
    
    public boolean insertPhone(PhoneEntity phoneEntity){
        return repository.insertPhone(phoneEntity);
    }
    
    public boolean updatePhone(PhoneEntity phoneEntity){
        return repository.updatePhone(phoneEntity);
    }
    
    public List<PhoneEntity> listPhone(int idUser){
        return repository.getListPhone(idUser);
    }
    
    
    public boolean updateListPhone(List<PhoneEntity> listPhone){
        return repository.updateListPhone(listPhone);
    }
}
