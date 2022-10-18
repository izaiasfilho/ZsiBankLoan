/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Controller.CityController;
import Model.Entities.AddressEntity;
import Model.Entities.CityEntity;
import Model.Persistence.AddressPersistence;
import Model.Services.Interfaces.AddressInterface;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class AddressImplemetation implements AddressInterface {

    @Override
    public boolean insertAddressInterface(AddressEntity addressEntity) {
        try {
            CityController cityController = new CityController();
            CityEntity city = cityController.getCityController(addressEntity.getCityEntity());
            if (city != null) {
                addressEntity.setCityEntity(city);
            } else {
                return insertAddressAndCity(addressEntity);
            }
            return AddressPersistence.insertAddressPersistence(addressEntity);
        } catch (SQLException ex) {
            Logger.getLogger(AddressImplemetation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateAddressInterface(AddressEntity addressEntity) {
            CityController cityController = new CityController();
            CityEntity city = cityController.getCityController(addressEntity.getCityEntity());
            if (city != null) {
                cityController.updateCityController(city);
                addressEntity.setCityEntity(city);
            } else {
                return updateAddressAndInsertCity(addressEntity);
            }
            return AddressPersistence.updateUpdaatePersistence(addressEntity);
    }

    @Override
    public AddressEntity getAddressInterface(AddressEntity addressEntity) {
        return AddressPersistence.getAddressPersistence(addressEntity);
    }

    public boolean insertAddressAndCity(AddressEntity addressEntity) {
        try {
            CityController cityController = new CityController();
            if (cityController.insertCity(addressEntity.getCityEntity())) {
                CityEntity city = cityController.getCityController(addressEntity.getCityEntity());
                if (city != null) {
                    addressEntity.setCityEntity(city);
                }
                return AddressPersistence.insertAddressPersistence(addressEntity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressImplemetation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateAddressAndInsertCity(AddressEntity addressEntity) {
            CityController cityController = new CityController();
            if (cityController.insertCity(addressEntity.getCityEntity())) {
                CityEntity city = cityController.getCityController(addressEntity.getCityEntity());
                if (city != null) {
                    addressEntity.setCityEntity(city);
                }
                return AddressPersistence.updateUpdaatePersistence(addressEntity);
            }
        return false;
    }

}
