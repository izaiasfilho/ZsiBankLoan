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
public enum TypeDml {
    DELETE(1),
    UPDATE(2),
    INSERT(3);
    
    private int id;
    
    private TypeDml(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public static TypeDml getById(Long id){
        return Arrays.asList(TypeDml.values()).stream().filter(st -> 
                st.getId()==(id)).findFirst().orElse(null);
    }
}
