/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.PlanEntity;
import Model.Services.Implementations.PlanImplemetation;
import Model.Services.Interfaces.PlanInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class PlanController {
    PlanInterface repository = new PlanImplemetation();
    
    public boolean inserPlan(PlanEntity planEntity){
        return repository.inserPlan(planEntity);
    }
    
    public List<PlanEntity> getListinserPlan(){
        return repository.getListinserPlan();
    }
    
    public PlanEntity getinserPlanEntityByDescripition(String descripion){
        return repository.getPlanEntityByDescripition(descripion);
    }
}
