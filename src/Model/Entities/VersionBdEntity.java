/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

import Model.TypeDml;

/**
 *
 * @author Izaias
 */
public class VersionBdEntity {
    private Long id;
    private Long version;
    private TypeDml typeDml;

    public TypeDml getTypeDml() {
        return typeDml;
    }

    public void setTypeDml(TypeDml typeDml) {
        this.typeDml = typeDml;
    }
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
