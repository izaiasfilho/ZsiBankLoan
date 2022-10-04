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
public enum TransactionEnum {
    PORTABILIDADE(1),
    REFINANCIAMENTO(2),
    MARGEM_LIVRE(3),
    CARTAO_RMC(4),
    EMPRESTIMO_CARTAO(5),
    CARTAO_BENEFICIARIO(6),
    FGTS(7);

   
    private int id;
    
    private TransactionEnum(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public static TransactionEnum getById(Long id){
        return Arrays.asList(TransactionEnum.values()).stream().filter(st -> 
                st.getId()==(id)).findFirst().orElse(null);
    }
}
