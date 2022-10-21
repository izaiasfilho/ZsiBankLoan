/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.UserInstitutionEntity;
import Model.Services.Implementations.UserInstitutionImplemetation;
import Model.Services.Interfaces.UserInstitutionInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class UserInstitutionController {
    UserInstitutionInterface repository = new UserInstitutionImplemetation();
    
    
    public boolean insertUserInstitution(UserInstitutionEntity userInstitutionEntity){
        return repository.insertUserInstitution(userInstitutionEntity);
    }
    
    public UserInstitutionEntity getUserInstitutionEntityByInstitution(String Institution){
        return repository.getUserInstitutionEntityByInstitution(Institution);
    }
    
    public List<UserInstitutionEntity> getListUserInstitutionByIdUser(int id_user){
        return repository.getListUserInstitutionByIdUser(id_user);
    }
}