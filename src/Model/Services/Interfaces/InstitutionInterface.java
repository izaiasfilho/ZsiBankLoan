/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.InstitutionEntity;
import java.util.List;

/**
 *
 * @author Izaias
 */
public interface InstitutionInterface {
    
    public boolean inserInstitution(InstitutionEntity institutionEntity);
    
    public List<InstitutionEntity> getListInstitution();
    
    public InstitutionEntity getInstitutioEntityByDescripition(String descripion);
}
