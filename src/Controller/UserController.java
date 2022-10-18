/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.UserEntity;
import Model.Services.Implementations.UserImplemetation;
import Model.Services.Interfaces.UserInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class UserController {
    
    UserInterface repository = new UserImplemetation();
    
    public boolean insertUserController(UserEntity userEntity){
        return repository.insertUser(userEntity);
    }
    
    public boolean updateUserController(UserEntity userEntity){
       return repository.updateUser(userEntity);
    }
    
    public UserEntity getUserController(UserEntity userEntity){
        return repository.getUser(userEntity);
    }
    
    public List<UserEntity> listUserController(){
        return repository.listUser();
    }
}
