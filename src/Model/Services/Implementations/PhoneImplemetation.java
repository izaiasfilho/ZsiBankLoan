/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.PhoneEntity;
import Model.Persistence.PhonePersistence;
import static Model.Persistence.PhonePersistence.getListPhonePersistence;
import Model.Services.Interfaces.PhoneInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class PhoneImplemetation implements PhoneInterface {

    @Override
    public boolean insertPhone(PhoneEntity phoneEntity) {
        try {
            return PhonePersistence.insertPhonePersistence(phoneEntity);
        } catch (SQLException ex) {
            Logger.getLogger(PhoneImplemetation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updatePhone(PhoneEntity phoneEntity) {
        return PhonePersistence.updatePhonePersistence(phoneEntity);
    }

    @Override
    public List<PhoneEntity> getListPhone(int idUser) {
        return getListPhonePersistence(idUser);
    }

    @Override
    public boolean deletPhone(int idUser) {
        return PhonePersistence.deletePhonePersistence(idUser);
    }

    @Override
    public boolean updateListPhone(List<PhoneEntity> listPhone) {
        boolean localReturn = false;
        if (listPhone.size() >0) {
            deletPhone(listPhone.get(0).getUserEntity().getId());
        }
        for (PhoneEntity phoneEntity : listPhone) {
            localReturn = insertPhone(phoneEntity);
        }
        return localReturn;
    }

    
}
