/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.Entities.CityEntity;
import Model.Entities.InstitutionEntity;
import Model.Entities.PhoneEntity;
import Model.Entities.StateEntity;
import Model.Entities.UserEntity;
import Model.Entities.UserInstitutionEntity;
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
public class UserInstitutionPersistence {

    public static boolean insertUserInstitutionEntityPersistence(UserInstitutionEntity userInstitutionEntity) throws SQLException {
        String query = "INSERT INTO tb_user_institution (id_user, id_institution, agency, account_number) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, userInstitutionEntity.getUserEntity().getId());
            preparedStatement.setInt(2, userInstitutionEntity.getBranchEntity().getId());
            preparedStatement.setString(3, userInstitutionEntity.getAgency());
            preparedStatement.setString(4, userInstitutionEntity.getAccountNumber());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserInstitutionPersistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConect();
        }
        return false;
    }

    public static List<UserInstitutionEntity> getListUserInstitutionByIdUserPersistence( int idUser) {
        String query = "SELECT * FROM tb_user_institution where id_user =?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            ResultSet rs = preparedStatement.executeQuery();

            List<UserInstitutionEntity> list = new ArrayList();
            while (rs.next()) {
                UserInstitutionEntity userInstitutionEntity = new UserInstitutionEntity();
                userInstitutionEntity.setId(rs.getInt("id"));
                
                UserEntity userEntity = new UserEntity();
                userEntity.setId(rs.getInt("id_user"));
                userInstitutionEntity.setUserEntity(userEntity);
                
                InstitutionEntity branchEntity = new InstitutionEntity();
                branchEntity.setId(rs.getInt("id_institution"));
                userInstitutionEntity.setBranchEntity(branchEntity);
                
                userInstitutionEntity.setAgency(rs.getString("agency"));
                userInstitutionEntity.setAccountNumber(rs.getString("account_number"));
                list.add(userInstitutionEntity);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserInstitutionPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static UserInstitutionEntity getUserInstitutionByInstitutionNumberPersistence( int id_institution) {
        String query = "SELECT * FROM tb_user_institution where id_institution =?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, id_institution);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                UserInstitutionEntity userInstitutionEntity = new UserInstitutionEntity();
                userInstitutionEntity.setId(rs.getInt("id"));
                
                UserEntity userEntity = new UserEntity();
                userEntity.setId(rs.getInt("id_user"));
                userInstitutionEntity.setUserEntity(userEntity);
                
                InstitutionEntity branchEntity = new InstitutionEntity();
                branchEntity.setId(rs.getInt("id_institution"));
                userInstitutionEntity.setBranchEntity(branchEntity);
                
                userInstitutionEntity.setAgency(rs.getString("agency"));
                userInstitutionEntity.setAccountNumber(rs.getString("account_number"));
                return userInstitutionEntity;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInstitutionPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
