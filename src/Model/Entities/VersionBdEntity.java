/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import Model.Enuns.TypeDml;

/**
 *
 * @author Izaias
 */
public class VersionBdEntity {
    private int id;
    private int version;
    private TypeDml typeDml;
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

    public TypeDml getTypeDml() {
        return typeDml;
    }

    public void setTypeDml(TypeDml typeDml) {
        this.typeDml = typeDml;
    }

}
