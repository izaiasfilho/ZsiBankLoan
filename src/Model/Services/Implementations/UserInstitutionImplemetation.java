/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Controller.InstitutionController;
import Model.Entities.InstitutionEntity;
import Model.Entities.UserEntity;
import Model.Entities.UserInstitutionEntity;
import Model.Persistence.UserInstitutionPersistence;
import static Model.Persistence.UserInstitutionPersistence.getUserInstitutionByInstitutionNumberPersistence;
import Model.Services.Interfaces.UserInstitutionInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class UserInstitutionImplemetation implements UserInstitutionInterface{

    @Override
    public boolean insertUserInstitution(UserInstitutionEntity userInstitutionEntity) {
        try {
            if(getUserInstitutionEntityByInstitution(userInstitutionEntity.getInstitutionUser().getDescription()) == null)
            return UserInstitutionPersistence.insertUserInstitutionEntityPersistence(userInstitutionEntity);
        } catch (SQLException ex) {
            Logger.getLogger(UserInstitutionImplemetation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public UserInstitutionEntity getUserInstitutionEntityByInstitution(String Institution) {
        InstitutionController institutionController = new InstitutionController();
        InstitutionEntity institutionEntity = institutionController.getInstitutionByDescription(Institution);
        return getUserInstitutionByInstitutionNumberPersistence(institutionEntity .getId());
    }

    @Override
    public List<UserInstitutionEntity> getListUserInstitutionByIdUser(int id_user) {
        return UserInstitutionPersistence.getListUserInstitutionByIdUserPersistence(id_user);
    }
    
    @Override
    public boolean updateReseteMainUserInstitution(UserEntity userEntity) {
        return UserInstitutionPersistence.updateReseteMainUserInstitutionPersistence(userEntity);
    }

    @Override
    public boolean updateMainUserInstitution(int id_institution, int id_user, boolean valueMain){
        return UserInstitutionPersistence.updateMainUserInstitutionPersistence(id_institution, id_user, valueMain);
    }
    
}
