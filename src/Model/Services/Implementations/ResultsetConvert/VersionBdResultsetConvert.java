/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations.ResultsetConvert;

import Model.Entities.VersionBdEntity;
import Model.Enuns.TypetransactionsSql;
import Model.Persistence.VersionBdPersistence;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class VersionBdResultsetConvert {

    public static ArrayList<VersionBdEntity> getListVersionBdResultsetConvert() {
        ArrayList<VersionBdEntity> listVersionBd = new ArrayList();
        try {                                         
            ResultSet rs = VersionBdPersistence.getVersionsPersistence("SELECT * FROM tb_version;");
            if (rs != null) {
                while (rs.next()) {
                    VersionBdEntity versionBdEntity = new VersionBdEntity();

                    versionBdEntity.setId(rs.getInt("id"));
                    versionBdEntity.setVersion(rs.getInt("version"));
                    versionBdEntity.setTypeDml(TypetransactionsSql.getById(rs.getLong("id_typedml")));
                    versionBdEntity.setDescription(rs.getString("description"));

                    listVersionBd.add(versionBdEntity);
                }
            }
            return listVersionBd;
        } catch (SQLException ex) {
            Logger.getLogger(VersionBdResultsetConvert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean checkExistTbVersionResultsetConvert(){
        return VersionBdPersistence.getCheckExistVersionsPersistence("SELECT * FROM tb_version;");
    }
}
