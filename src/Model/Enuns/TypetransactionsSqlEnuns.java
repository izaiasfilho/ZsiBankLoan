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
public enum TypetransactionsSqlEnuns {
    DELETE(1),
    UPDATE(2),
    INSERT(3),
    ALTER(4),
    CREATE(5),
    DROP(6);
    
    private int id;
    
    private TypetransactionsSqlEnuns(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public static TypetransactionsSqlEnuns getById(Long id){
        return Arrays.asList(TypetransactionsSqlEnuns.values()).stream().filter(st -> 
                st.getId()==(id)).findFirst().orElse(null);
    }
}
