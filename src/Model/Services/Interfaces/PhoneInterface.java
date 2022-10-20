/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.PhoneEntity;
import java.util.List;

/**
 *
 * @author Izaias
 */
public interface PhoneInterface {
    
    public boolean insertPhone(PhoneEntity phoneEntity);
    
    public boolean updatePhone(PhoneEntity phoneEntity);
    
    public boolean updateListPhone(List<PhoneEntity> listPhone);
    
    public List<PhoneEntity> getListPhone(int idUser);
    
    public boolean deletPhone(int idUser);
}
