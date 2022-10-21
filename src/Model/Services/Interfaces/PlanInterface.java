/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.PlanEntity;
import java.util.List;

/**
 *
 * @author Izaias
 */
public interface PlanInterface {
    
    public boolean inserPlan(PlanEntity planEntity);
    
    public List<PlanEntity> getListinserPlan();
    
    public PlanEntity getPlanEntityByDescripition(String descripion);
}
