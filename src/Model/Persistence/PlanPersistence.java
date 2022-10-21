/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.Entities.PlanEntity;
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
public class PlanPersistence {

    public static boolean insertPlanPersistence(PlanEntity planEntity) throws SQLException {
        String query = "INSERT INTO tb_plan (description) VALUES (?)";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, planEntity.getDescription());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PlanPersistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConect();
        }
        return false;
    }

    public static List<PlanEntity> getListPlanPersistence() {
        String query = "SELECT * FROM tb_plan;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            List<PlanEntity> list = new ArrayList();
            while (rs.next()) {
                PlanEntity plan = new PlanEntity();
                plan.setId(rs.getInt("id"));
                plan.setDescription(rs.getString("description"));
                list.add(plan);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(PlanPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean updatePlanPersistence(PlanEntity planEntity) {
        String query = "UPDATE tb_plan SET description =? where id = ?";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, planEntity.getDescription());
            preparedStatement.setInt(2, planEntity.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PlanPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
public static PlanEntity getPlanByDescriptionPersistence(String description) {
        String query = "SELECT * FROM tb_plan where description = ?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, description);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                PlanEntity plan = new PlanEntity();
                plan.setId(rs.getInt("id"));
                plan.setDescription(rs.getString("description"));
                return plan;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    
}
