/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Controller.AddressController;
import Model.Entities.AddressEntity;
import Model.Entities.UserEntity;
import Model.Persistence.UserPersistence;
import static Model.Persistence.UserPersistence.getListBirthdaysOfTheMonth;
import static Model.Persistence.UserPersistence.getListUserPersistence;
import Model.Services.Interfaces.UserInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class UserImplemetation implements UserInterface {

    @Override
    public boolean insertUser(UserEntity userEntity) {
        try {
            if (getUser(userEntity) == null) {
                AddressController addressController = new AddressController();
                addressController.insertAddressController(userEntity.getAddressEntity());

                AddressEntity address = addressController.getAddressController(userEntity.getAddressEntity());
                userEntity.setAddressEntity(address);
                return UserPersistence.insertUserPersistence(userEntity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImplemetation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateUser(UserEntity userEntity) {
        if (getUser(userEntity) != null) {
            AddressController addressController = new AddressController();
            AddressEntity address = addressController.getAddressController(userEntity.getAddressEntity());
            if (address != null) {
                addressController.updateAddressController(userEntity.getAddressEntity());
            } else {
               addressController.insertAddressController(userEntity.getAddressEntity());
            }
            address = addressController.getAddressController(userEntity.getAddressEntity());
            userEntity.setAddressEntity(address);
            return UserPersistence.updateUserPersistence(userEntity);
        }
        return false;
    }

    @Override
    public UserEntity getUser(UserEntity userEntity) {
        UserEntity user = UserPersistence.getUserPersistence(userEntity);
        if(user != null){
            return user;
        }else{
            String physicalPersonRegistration = userEntity.getPhysicalPersonRegistration().replace(".", "");
            physicalPersonRegistration = physicalPersonRegistration.replace("-", "");
            userEntity.setPhysicalPersonRegistration(physicalPersonRegistration);
        }
        return UserPersistence.getUserPersistence(userEntity);
    }

    @Override
    public List<UserEntity> listUser() {
        return getListUserPersistence();
    }

    @Override
    public boolean validateLoginAndPassword(String login, String password) {
        return UserPersistence.validatePoginAndPasswordPersistence(login, password);
    }

    @Override
    public List<UserEntity> birthdaysOfTheMonth() {
        return getListBirthdaysOfTheMonth();
    }

}
