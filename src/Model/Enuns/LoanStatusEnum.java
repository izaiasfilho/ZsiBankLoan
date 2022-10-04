/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Enuns;

import java.util.Arrays;

/**
 *
 * @author FABIO COSTA
 */


public enum LoanStatusEnum {
    FINALIZADO(1),
    CANCELADO(2),
    DIGITADO(3);
   
    
    private int id;
    
    private LoanStatusEnum(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public static LoanStatusEnum getById(Long id){
        return Arrays.asList(LoanStatusEnum.values()).stream().filter(st -> 
                st.getId()==(id)).findFirst().orElse(null);
    }
}
