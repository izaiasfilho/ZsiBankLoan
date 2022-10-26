/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.Entities.InstitutionEntity;
import Model.Entities.LoanEntity;
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
public class LoanPersistence {

    public static LoanEntity getLoanByIdPersistence(int idUser) {
        String query = "SELECT lo.id, lo.id_user, lo.contactNumber, lo.issueDate, lo.changeDate, ins.description,\n"
                + "          lo.agency, lo.account_number\n"
                + "          FROM tb_loan lo, tb_institution ins, tb_user u\n"
                + "          where lo.id_inst_user = ins.id\n"
                + "          and lo.id_user = u.id\n"
                + "          and lo.id_user = ?\n"
                + "          order by lo.id";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                LoanEntity loan = new LoanEntity();
                loan.setId(rs.getInt("id"));
                UserEntity user = new UserEntity();
                user.setId(rs.getInt("id_user"));
                loan.setUsrEntity(user);
                loan.setContactNumber(rs.getString("contactNumber"));
                loan.setIssueDate(rs.getString("issueDate"));
                loan.setChangeDate(rs.getString("changeDate"));

                InstitutionEntity institutionEntity = new InstitutionEntity();
                institutionEntity.setDescription(rs.getString("description"));
                loan.setInstitutionEntity(institutionEntity);

                loan.setAgency(rs.getString("agency"));
                loan.setAccount_number(rs.getString("account_number"));
                return loan;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static LoanEntity getLoanByContactNumberPersistence(String contactNumber) {
        
        String query = "SELECT lo.id, lo.id_user, lo.contactNumber, lo.issueDate, lo.changeDate, ins.description,\n"
                + "          lo.agency, lo.account_number\n"
                + "          FROM tb_loan lo, tb_institution ins, tb_user u\n"
                + "          where lo.id_inst_user = ins.id\n"
                + "          and lo.id_user = u.id\n"
                + "          and lo.contactNumber = ?\n"
                + "          order by lo.id";

        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, contactNumber);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                LoanEntity loan = new LoanEntity();
                loan.setId(rs.getInt("id"));
                UserEntity user = new UserEntity();
                user.setId(rs.getInt("id_user"));
                loan.setUsrEntity(user);
                loan.setContactNumber(rs.getString("contactNumber"));
                loan.setIssueDate(rs.getString("issueDate"));
                loan.setChangeDate(rs.getString("changeDate"));

                InstitutionEntity institutionEntity = new InstitutionEntity();
                institutionEntity.setDescription(rs.getString("description"));
                loan.setInstitutionEntity(institutionEntity);

                loan.setAgency(rs.getString("agency"));
                loan.setAccount_number(rs.getString("account_number"));
                return loan;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<LoanEntity> getListLoanPersistence() {
        String query = "SELECT lo.id, lo.id_user, lo.contactNumber, lo.issueDate, lo.changeDate, ins.description,\n"
                + "          lo.agency, lo.account_number\n"
                + "          FROM tb_loan lo, tb_institution ins, tb_user u\n"
                + "          where lo.id_inst_user = ins.id\n"
                + "          and lo.id_user = u.id\n"
                + "          order by lo.id";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            List<LoanEntity> list = new ArrayList();
            while (rs.next()) {
                LoanEntity loan = new LoanEntity();
                loan.setId(rs.getInt("id"));
                UserEntity user = new UserEntity();
                user.setId(rs.getInt("id_user"));
                loan.setUsrEntity(user);
                loan.setContactNumber(rs.getString("contactNumber"));
                loan.setIssueDate(rs.getString("issueDate"));
                loan.setChangeDate(rs.getString("changeDate"));

                InstitutionEntity institutionEntity = new InstitutionEntity();
                institutionEntity.setDescription(rs.getString("description"));
                loan.setInstitutionEntity(institutionEntity);

                loan.setAgency(rs.getString("agency"));
                loan.setAccount_number(rs.getString("account_number"));
                list.add(loan);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<LoanEntity> getListLoanByUserPersistence(int idUser) {
        String query = "SELECT lo.id, lo.id_user, lo.contactNumber, lo.issueDate, lo.changeDate, ins.description,\n"
                + "          lo.agency, lo.account_number\n"
                + "          FROM tb_loan lo, tb_institution ins, tb_user u\n"
                + "          where lo.id_inst_user = ins.id\n"
                + "          and lo.id_user = u.id\n"
                + "          and lo.id_user = ?\n"
                + "          order by lo.id";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, idUser);
            ResultSet rs = preparedStatement.executeQuery();

            List<LoanEntity> list = new ArrayList();
            while (rs.next()) {
                LoanEntity loan = new LoanEntity();
                loan.setId(rs.getInt("id"));
                UserEntity user = new UserEntity();
                user.setId(rs.getInt("id_user"));
                loan.setUsrEntity(user);
                loan.setContactNumber(rs.getString("contactNumber"));
                loan.setIssueDate(rs.getString("issueDate"));
                loan.setChangeDate(rs.getString("changeDate"));

                InstitutionEntity institutionEntity = new InstitutionEntity();
                institutionEntity.setDescription(rs.getString("description"));
                loan.setInstitutionEntity(institutionEntity);

                loan.setAgency(rs.getString("agency"));
                loan.setAccount_number(rs.getString("account_number"));
                list.add(loan);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean insertLoanPersistence(LoanEntity loanEntity) throws SQLException {
        String query = "INSERT INTO tb_loan (id_user, contactNumber, issueDate, changeDate,"
                + " agency, account_number, id_inst_user) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, loanEntity.getUsrEntity().getId());
            preparedStatement.setString(2, loanEntity.getContactNumber());
            preparedStatement.setString(3, loanEntity.getIssueDate());
            preparedStatement.setString(4, loanEntity.getChangeDate());
            preparedStatement.setString(5, loanEntity.getAgency());
            preparedStatement.setString(6, loanEntity.getAccount_number());
            preparedStatement.setInt(7, loanEntity.getInstitutionEntity().getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserPersistence.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConect();
        }
        return false;
    }

    public static boolean updateLoanPersistence(LoanEntity loanEntity) {
        String query = "UPDATE tb_loan SET id_user =?, contactNumber =?,"
                + " issueDate =?, changeDate =?, id_inst_user =? where id = ?";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, loanEntity.getUsrEntity().getId());
            preparedStatement.setString(2, loanEntity.getContactNumber());
            preparedStatement.setString(3, loanEntity.getIssueDate());
            preparedStatement.setString(4, loanEntity.getChangeDate());
            preparedStatement.setInt(5, loanEntity.getInstitutionEntity().getId());

            preparedStatement.setInt(6, loanEntity.getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
