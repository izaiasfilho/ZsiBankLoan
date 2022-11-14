/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;
import Model.Entities.PreferencesEntity;
import Model.Entities.UserEntity;
import static Resources.BD.Conection.Checks;
import static Resources.BD.Conection.closeConect;
import static Resources.BD.Conection.conect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class PreferencesPersistence {
    
    
     public static PreferencesEntity getPreferencesEntityPersistence() {
        String query = "SELECT * FROM tb_preferences;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                PreferencesEntity preferences = new PreferencesEntity();
                preferences.setId(rs.getInt(1));
                preferences.setFiles(rs.getString(2));
                return preferences;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
      
       public static boolean updateFilesPreferencesEntityPersistence(String file) {
        String query = "UPDATE tb_preferences SET files =? where id = 1";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, file);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PlanPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
