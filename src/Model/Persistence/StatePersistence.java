/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.Entities.StateEntity;
import static Resources.BD.Conection.Checks;
import static Resources.BD.Conection.closeConect;
import static Resources.BD.Conection.conect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class StatePersistence {
    
    
     public static StateEntity getStateByUfPersistence(StateEntity stateEntity) {
        String query = "SELECT * FROM tb_state where uf =?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, stateEntity.getUf());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                StateEntity state = new StateEntity();
                state.setId(rs.getInt("id"));
                state.setUf(rs.getString("uf"));
                state.setDescription(rs.getString("descripition"));
                state.setCounty(rs.getString("county"));
                return state;                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public static List<StateEntity> getListStatePersistence() {
        String query = "SELECT * FROM tb_state;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            List<StateEntity> list = new ArrayList();
            while (rs.next()) {
                StateEntity state = new StateEntity();
                state.setId(rs.getInt("id"));
                state.setUf(rs.getString("uf"));
                state.setDescription(rs.getString("descripition"));
                state.setCounty(rs.getString("county"));
                list.add(state);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
