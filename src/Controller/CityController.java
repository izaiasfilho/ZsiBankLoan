/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.CityEntity;
import Model.Services.Implementations.CityImplementation;
import Model.Services.Interfaces.CityInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class CityController {
    CityInterface repository = new CityImplementation();
    
    public boolean insertCity(CityEntity cityEntity){
        return repository.insertCityInterface(cityEntity);
    }
    
    public  boolean checkExistCityController(CityEntity cityEntity){
        return repository.checkExistCityInterface(cityEntity);
    }
    
    public CityEntity getCityController(CityEntity cityEntity){
        return repository.getCityInterface(cityEntity);
    }
    
    public List<CityEntity>getListCityController(){
     return repository.getListCityInterfacel();
    }
    
    public boolean updateCityController(CityEntity cityEntity){
       return repository.updateCityInterface(cityEntity);
    }
}
