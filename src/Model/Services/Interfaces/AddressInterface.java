/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.AddressEntity;

/**
 *
 * @author Izaias
 */
public interface AddressInterface {
    
    public boolean insertAddressInterface(AddressEntity addressEntity);
    
    public boolean updateAddressInterface(AddressEntity addressEntity);
    
    public AddressEntity getAddressInterface(AddressEntity addressEntity);
    
}
