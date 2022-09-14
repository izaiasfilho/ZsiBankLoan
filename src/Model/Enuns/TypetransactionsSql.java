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
public enum TypetransactionsSql {
    DELETE(1),
    UPDATE(2),
    INSERT(3),
    ALTER(4),
    CREATER(5),
    DROP(6);
    
    private int id;
    
    private TypetransactionsSql(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public static TypetransactionsSql getById(Long id){
        return Arrays.asList(TypetransactionsSql.values()).stream().filter(st -> 
                st.getId()==(id)).findFirst().orElse(null);
    }
}
