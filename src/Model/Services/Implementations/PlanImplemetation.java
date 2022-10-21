/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.InstitutionEntity;
import Model.Entities.PlanEntity;
import Model.Persistence.InstitutionPersistence;
import static Model.Persistence.InstitutionPersistence.getListInstitutionPersistence;
import Model.Persistence.PlanPersistence;
import static Model.Persistence.PlanPersistence.getListPlanPersistence;
import Model.Services.Interfaces.InstitutionInterface;
import Model.Services.Interfaces.PlanInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class PlanImplemetation implements PlanInterface {

    @Override
    public boolean inserPlan(PlanEntity planEntity) {
        try {
            if (getPlanEntityByDescripition(planEntity.getDescription()) == null) {
                return PlanPersistence.insertPlanPersistence(planEntity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanImplemetation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<PlanEntity> getListinserPlan() {
        return getListPlanPersistence();
    }

    @Override
    public PlanEntity getPlanEntityByDescripition(String descripion) {
        return PlanPersistence.getPlanByDescriptionPersistence(descripion);
    }

}
