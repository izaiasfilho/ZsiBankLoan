/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.UserEntity;
import Model.Entities.UserInstitutionEntity;
import java.util.List;

/**
 *
 * @author Izaias
 */
public interface UserInstitutionInterface {
 
    public boolean insertUserInstitution(UserInstitutionEntity userInstitutionEntity);
    
    public UserInstitutionEntity getUserInstitutionEntityByInstitution(String Institution);
    
    public List<UserInstitutionEntity> getListUserInstitutionByIdUser(int id_user);
    
    public boolean updateReseteMainUserInstitution(UserEntity userEntity);
    
    public boolean updateMainUserInstitution(int id_institution, int id_user, boolean valueMain);
    
}
