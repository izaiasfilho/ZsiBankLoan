/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import Model.Enuns.TypetransactionsSql;

/**
 *
 * @author Izaias
 */
public class VersionBdEntity {
    private int id;
    private int version;
    private TypetransactionsSql typeDml;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public TypetransactionsSql getTypeDml() {
        return typeDml;
    }

    public void setTypeDml(TypetransactionsSql typeDml) {
        this.typeDml = typeDml;
    }

}
