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
public enum RoleEnum {
    ADMINISTRADOR(1),
    GERENTE(2),
    CLIENTE(3),
    FUNCIONARIO(4),
    CORRETOR(5);
    
   
    private int id;
    
    private RoleEnum(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public static RoleEnum getById(Long id){
        return Arrays.asList(RoleEnum.values()).stream().filter(st -> 
                st.getId()==(id)).findFirst().orElse(null);
    }
}
