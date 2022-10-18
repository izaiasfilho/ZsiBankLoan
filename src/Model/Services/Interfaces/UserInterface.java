/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.UserEntity;
import java.util.List;

/**
 *
 * @author Izaias
 */
public interface UserInterface {
    
    public boolean insertUser(UserEntity userEntity);
    
    public boolean updateUser(UserEntity userEntity);
    
    public UserEntity getUser(UserEntity userEntity);
    
    public List<UserEntity> listUser();
}
