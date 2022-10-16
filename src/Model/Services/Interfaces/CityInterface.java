/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.CityEntity;
import java.util.List;

/**
 *
 * @author Izaias
 */
public interface CityInterface {
    public boolean insertCityInterface(CityEntity cityEntity);
    
    public boolean checkExistCityInterface(CityEntity cityEntity);
    
    public CityEntity getCityInterface(CityEntity cityEntity);
    
    public List<CityEntity> getListCityInterfacel();
    
    public boolean updateCityInterface(CityEntity cityEntity);
    
}
