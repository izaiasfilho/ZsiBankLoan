/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.InstitutionEntity;
import Model.Services.Implementations.InstitutionImplemetation;
import Model.Services.Interfaces.InstitutionInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class InstitutionController {
    
    InstitutionInterface repository = new InstitutionImplemetation();
    
    public boolean inserInstitution(InstitutionEntity institutionEntity){
        return repository.inserInstitution(institutionEntity);
    }
    
    public List<InstitutionEntity> getListInstitution(){
       return repository.getListInstitution();
    }
    
    public InstitutionEntity getInstitutionByDescription(String description){
       return repository.getInstitutioEntityByDescripition(description);
    }
}
