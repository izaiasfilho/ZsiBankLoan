/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.AddressEntity;
import Model.Services.Implementations.AddressImplemetation;
import Model.Services.Interfaces.AddressInterface;

/**
 *
 * @author Izaias
 */
public class AddressController {
    AddressInterface repository = new AddressImplemetation();
    
    public boolean insertAddressController(AddressEntity addressEntity){
        return repository.insertAddressInterface(addressEntity);
    }
    
    public boolean updateAddressController(AddressEntity addressEntity){
        return repository.updateAddressInterface(addressEntity);
    }
    
    public AddressEntity getAddressController(AddressEntity addressEntity){
        return repository.getAddressInterface(addressEntity);
    }
}
