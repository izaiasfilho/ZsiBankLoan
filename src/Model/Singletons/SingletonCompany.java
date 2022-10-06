/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Singletons;

import Model.Entities.CompanyEntity;

/**
 *
 * @author Izaias
 */
public class SingletonCompany {
         
    public static CompanyEntity instancia;
    
    private SingletonCompany(){
    }
    
    public static CompanyEntity Company(){
        
        if(instancia==null){
            instancia=new CompanyEntity();
        }
        return instancia;
    }
}
