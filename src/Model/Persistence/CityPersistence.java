/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.Entities.CityEntity;
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
public class CityPersistence {

    public static boolean insertCityPersistence(CityEntity cityEntity) throws SQLException {
        String query = "INSERT INTO tb_city (name, id_state) VALUES (?,?)";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, cityEntity.getName());
            preparedStatement.setInt(2, cityEntity.getStateEntity().getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConect();
        }
        return false;
    }

    public static CityEntity getCityPersistence(CityEntity cityEntity) {
        String query = "SELECT * FROM tb_city where name =? and id_state =? ;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, cityEntity.getName());
            preparedStatement.setInt(2, cityEntity.getStateEntity().getId());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CityEntity city = new CityEntity();
                city.setId(rs.getInt("id"));

                city.setName(rs.getString("name"));

                StateEntity stateEntity = new StateEntity();
                stateEntity.setId(rs.getInt("id_state"));

                city.setStateEntity(stateEntity);
                return city;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<CityEntity> getListCityPersistence() {
        String query = "SELECT * FROM tb_city;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            List<CityEntity> list = new ArrayList();
            while (rs.next()) {
                CityEntity city = new CityEntity();
                city.setId(rs.getInt("id"));

                city.setName(rs.getString("name"));

                StateEntity stateEntity = new StateEntity();
                stateEntity.setId(rs.getInt("id_state"));

                city.setStateEntity(stateEntity);
                list.add(city);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean updateCityPersistence(CityEntity cityEntity) {
        String query = "UPDATE tb_city SET name = ?, id_state = ? where id = ?";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, cityEntity.getName());
            preparedStatement.setInt(2, cityEntity.getStateEntity().getId());
            preparedStatement.setInt(3, cityEntity.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
