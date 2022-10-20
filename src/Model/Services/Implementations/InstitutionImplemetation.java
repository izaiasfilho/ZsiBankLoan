/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.InstitutionEntity;
import Model.Persistence.InstitutionPersistence;
import static Model.Persistence.InstitutionPersistence.getListInstitutionPersistence;
import Model.Services.Interfaces.InstitutionInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class InstitutionImplemetation implements InstitutionInterface {

    @Override
    public boolean inserInstitution(InstitutionEntity institutionEntity) {
        try {
            if (getInstitutioEntityByDescripition(institutionEntity.getDescription()) == null) {
                return InstitutionPersistence.insertInstitutionPersistence(institutionEntity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstitutionImplemetation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<InstitutionEntity> getListInstitution() {
        return getListInstitutionPersistence();
    }

    @Override
    public InstitutionEntity getInstitutioEntityByDescripition(String descripion) {
        return InstitutionPersistence.getInstitutionByDescriptionPersistence(descripion);
    }

}
