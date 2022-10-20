/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.Entities.InstitutionEntity;
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
public class InstitutionPersistence {

    public static boolean insertInstitutionPersistence(InstitutionEntity institutionEntity) throws SQLException {
        String query = "INSERT INTO tb_institution (description, fee) VALUES (?,?)";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, institutionEntity.getDescription());
            preparedStatement.setDouble(2, institutionEntity.getFee());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(InstitutionPersistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConect();
        }
        return false;
    }

    public static List<InstitutionEntity> getListInstitutionPersistence() {
        String query = "SELECT * FROM tb_institution;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            List<InstitutionEntity> list = new ArrayList();
            while (rs.next()) {
                InstitutionEntity inst = new InstitutionEntity();
                inst.setId(rs.getInt("id"));
                inst.setDescription(rs.getString("description"));
                inst.setFee(rs.getDouble("fee"));
                list.add(inst);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(InstitutionPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean updateInstitutionPersistence(InstitutionEntity InstitutionEntity) {
        String query = "UPDATE tb_institution SET description =? , fee =?  where id = ?";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, InstitutionEntity.getDescription());
            preparedStatement.setDouble(2, InstitutionEntity.getFee());
            preparedStatement.setInt(3, InstitutionEntity.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(InstitutionPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
public static InstitutionEntity getInstitutionByDescriptionPersistence(String description) {
        String query = "SELECT * FROM tb_institution where description = ?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, description);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                InstitutionEntity inst = new InstitutionEntity();
                inst.setId(rs.getInt("id"));
                inst.setDescription(rs.getString("description"));
                inst.setFee(rs.getDouble("fee"));
                return inst;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InstitutionPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
