/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.Entities.CityEntity;
import Model.Entities.PhoneEntity;
import Model.Entities.StateEntity;
import Model.Entities.UserEntity;
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
public class PhonePersistence {

    public static boolean insertPhonePersistence(PhoneEntity phoneEntity) throws SQLException {
        String query = "INSERT INTO tb_phone (id_user,number, numberType) VALUES (?,?,?)";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, phoneEntity.getUserEntity().getId());
            preparedStatement.setString(2, phoneEntity.getNumber());
            preparedStatement.setString(3, phoneEntity.getNumberType());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PhonePersistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConect();
        }
        return false;
    }

    public static List<PhoneEntity> getListPhonePersistence( int idUser) {
        String query = "SELECT * FROM tb_phone where id_user =?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            ResultSet rs = preparedStatement.executeQuery();

            List<PhoneEntity> list = new ArrayList();
            while (rs.next()) {
                PhoneEntity phone = new PhoneEntity();
                phone.setId(rs.getInt("id"));
                phone.setNumberType(rs.getString("numberType"));
                
                UserEntity userEntity = new UserEntity();
                userEntity.setId(rs.getInt("id_user"));
                phone.setUserEntity(userEntity);
                phone.setNumber(rs.getString("number"));
                list.add(phone);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PhonePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean updatePhonePersistence(PhoneEntity phoneEntity) {
        String query = "UPDATE tb_phone SET number =? , numberType =?  where id_user= ?";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, phoneEntity.getNumber());
            preparedStatement.setString(2, phoneEntity.getNumberType());
            preparedStatement.setInt(3, phoneEntity.getUserEntity().getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PhonePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deletePhonePersistence(int idUser) {
        String query = "DELETE FROM `tb_phone` where id_user= ?";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PhonePersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
