/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.CityEntity;
import Model.Persistence.CityPersistence;
import static Model.Persistence.CityPersistence.getCityPersistence;
import static Model.Persistence.CityPersistence.getListCityPersistence;
import static Model.Persistence.CityPersistence.insertCityPersistence;
import Model.Services.Interfaces.CityInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class CityImplementation implements CityInterface {

    @Override
    public boolean insertCityInterface(CityEntity cityEntity) {
        try {
            return checkExistCityInterface(cityEntity) == false
                    ? insertCityPersistence(cityEntity) : false;
        } catch (SQLException ex) {
            Logger.getLogger(CityImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean checkExistCityInterface(CityEntity cityEntity) {
         return getCityInterface(cityEntity) != null ? true : false; 
    }

    @Override
    public CityEntity getCityInterface(CityEntity cityEntity) {
        return getCityPersistence(cityEntity);
    }

    @Override
    public List<CityEntity> getListCityInterfacel() {
        return getListCityPersistence();
    }

    @Override
    public boolean updateCityInterface(CityEntity cityEntity) {
        return CityPersistence.updateCityPersistence(cityEntity);
    }


}
