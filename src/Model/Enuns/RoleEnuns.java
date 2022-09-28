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
public enum RoleEnuns {
    ADMIN(1),
    GR(2),
    FUNCIONARIO(3),
    CONSULTOR(4),
    CLIENTE(5);
    
    private int id;
    
    private RoleEnuns(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public static RoleEnuns getById(Long id){
        return Arrays.asList(RoleEnuns.values()).stream().filter(st -> 
                st.getId()==(id)).findFirst().orElse(null);
    }
}
