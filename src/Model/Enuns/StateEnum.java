/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Enuns;

import java.util.Arrays;

/**
 *
 * @author Izaias
 */
public enum StateEnum {
     UF (1),
     COUNTY (2),
    DESCRIPTION (3);
       
    
   
    private int id;
    
    private StateEnum(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public static StateEnum getById(Long id){
        return Arrays.asList(StateEnum.values()).stream().filter(st -> 
                st.getId()==(id)).findFirst().orElse(null);
    }
}
