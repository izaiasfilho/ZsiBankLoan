/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.StateEntity;
import Model.Persistence.StatePersistence;
import static Model.Persistence.StatePersistence.getListStatePersistence;
import Model.Services.Interfaces.StateInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class StateImplemetation implements StateInterface{

    @Override
    public List<StateEntity> getListStateInterface() {
        return getListStatePersistence();
    }

    @Override
    public StateEntity getStateEntityByUf(StateEntity stateEntity) {
        return StatePersistence.getStateByUfPersistence(stateEntity);
    }
    
}
