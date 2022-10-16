/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.StateEntity;
import Model.Services.Implementations.StateImplemetation;
import Model.Services.Interfaces.StateInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class StateController {
    StateInterface repository = new StateImplemetation();
    
    public List<StateEntity> getListStateController(){
        return repository.getListStateInterface();
    }
    
    public StateEntity getStateByUf(StateEntity stateEntity){
       return repository.getStateEntityByUf(stateEntity);
    }
}
