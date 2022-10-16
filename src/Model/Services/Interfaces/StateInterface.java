/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.StateEntity;
import java.util.List;

/**
 *
 * @author Izaias
 */
public interface StateInterface {
    public List<StateEntity> getListStateInterface();
    
    public StateEntity getStateEntityByUf(StateEntity stateEntity);
}
