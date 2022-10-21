/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.Entities.InstitutionEntity;
import Model.Entities.LoanEntity;
import Model.Entities.LoanMovementEntity;
import Model.Entities.PlanEntity;
import Model.Entities.UserEntity;
import Model.Entities.UserInstitutionEntity;
import Model.Enuns.LoanStatusEnum;
import Model.Enuns.TransactionEnum;
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
public class LoanMovementPersistence {

    public static LoanMovementEntity getLoanloanMovementByIdLoanPersistence(int id_loan) {
        String query = "SELECT * FROM tb_loanMovement where id_loan =?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, id_loan);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                LoanMovementEntity loanMov = new LoanMovementEntity();
                loanMov.setId(rs.getInt("id"));

                LoanEntity loanEntity = new LoanEntity();
                loanEntity.setId(rs.getInt("id_loan"));
                loanMov.setLoanEntity(loanEntity);

                loanMov.setDate(rs.getString("date"));

                InstitutionEntity institutionEntity = new InstitutionEntity();
                institutionEntity.setId(rs.getInt("id_institution"));
                loanMov.setInstitutionEntity(institutionEntity);

                PlanEntity planEntity = new PlanEntity();
                planEntity.setId(rs.getInt("plan"));
                loanMov.setPlanEntity(planEntity);

                loanMov.setTransactionEnum(TransactionEnum.getById(rs.getLong("id_TransactionEnut")));
                UserInstitutionEntity userInstitutionEntity = new UserInstitutionEntity();
                userInstitutionEntity.setId(rs.getInt("id_user_institution"));
                loanMov.setInstitutionEntity(institutionEntity);
                loanMov.setLoanStatutsEnum(LoanStatusEnum.getById(rs.getLong("id_loanStatusEnum")));
                loanMov.setBroker(rs.getString("broker"));
                loanMov.setCommission(rs.getString("comission"));
                loanMov.setGrossValue(rs.getDouble("grossValue"));
                loanMov.setNetValue(rs.getDouble("netValue"));
                loanMov.setAmountOfInstallments(rs.getDouble("amountOfInstallments"));
                loanMov.setValueInstallments(rs.getDouble("valueInstallments"));
                loanMov.setOperator(rs.getString("operator"));
                loanMov.setNote(rs.getString("note"));
                loanMov.setFiles(rs.getString("files"));
                loanMov.setNumberADE(rs.getString("numberADE"));
                loanMov.setBenefitNumber(rs.getString("benefitNumber"));
                loanMov.setSpeciesCode(rs.getString("speciesCode"));
                loanMov.setTypist(rs.getString("typist"));
                
                return loanMov;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static LoanMovementEntity getLoanMovementByContactNumberPersistence(String contactNumber) {
        String query = "SELECT * FROM tb_loanMovement where contactNumber =?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, contactNumber);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                LoanMovementEntity loanMov = new LoanMovementEntity();
                loanMov.setId(rs.getInt("id"));

                LoanEntity loanEntity = new LoanEntity();
                loanEntity.setId(rs.getInt("id_loan"));
                loanMov.setLoanEntity(loanEntity);

                loanMov.setDate(rs.getString("date"));

                InstitutionEntity institutionEntity = new InstitutionEntity();
                institutionEntity.setId(rs.getInt("id_institution"));
                loanMov.setInstitutionEntity(institutionEntity);

                PlanEntity planEntity = new PlanEntity();
                planEntity.setId(rs.getInt("plan"));
                loanMov.setPlanEntity(planEntity);

                loanMov.setTransactionEnum(TransactionEnum.getById(rs.getLong("id_TransactionEnut")));
                UserInstitutionEntity userInstitutionEntity = new UserInstitutionEntity();
                userInstitutionEntity.setId(rs.getInt("id_user_institution"));
                loanMov.setInstitutionEntity(institutionEntity);
                loanMov.setLoanStatutsEnum(LoanStatusEnum.getById(rs.getLong("id_loanStatusEnum")));
                loanMov.setBroker(rs.getString("broker"));
                loanMov.setCommission(rs.getString("comission"));
                loanMov.setGrossValue(rs.getDouble("grossValue"));
                loanMov.setNetValue(rs.getDouble("netValue"));
                loanMov.setAmountOfInstallments(rs.getDouble("amountOfInstallments"));
                loanMov.setValueInstallments(rs.getDouble("valueInstallments"));
                loanMov.setOperator(rs.getString("operator"));
                loanMov.setNote(rs.getString("note"));
                loanMov.setFiles(rs.getString("files"));
                loanMov.setNumberADE(rs.getString("numberADE"));
                loanMov.setBenefitNumber(rs.getString("benefitNumber"));
                loanMov.setSpeciesCode(rs.getString("speciesCode"));
                loanMov.setTypist(rs.getString("typist"));
                
                return loanMov;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<LoanMovementEntity> getListLoanPersistence() {
        String query = "SELECT * FROM tb_loanMovement;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<LoanMovementEntity> list = new ArrayList();
            while (rs.next()) {
                LoanMovementEntity loanMov = new LoanMovementEntity();
                loanMov.setId(rs.getInt("id"));

                LoanEntity loanEntity = new LoanEntity();
                loanEntity.setId(rs.getInt("id_loan"));
                loanMov.setLoanEntity(loanEntity);

                loanMov.setDate(rs.getString("date"));

                InstitutionEntity institutionEntity = new InstitutionEntity();
                institutionEntity.setId(rs.getInt("id_institution"));
                loanMov.setInstitutionEntity(institutionEntity);

                PlanEntity planEntity = new PlanEntity();
                planEntity.setId(rs.getInt("plan"));
                loanMov.setPlanEntity(planEntity);

                loanMov.setTransactionEnum(TransactionEnum.getById(rs.getLong("id_TransactionEnut")));
                UserInstitutionEntity userInstitutionEntity = new UserInstitutionEntity();
                userInstitutionEntity.setId(rs.getInt("id_user_institution"));
                loanMov.setInstitutionEntity(institutionEntity);
                loanMov.setLoanStatutsEnum(LoanStatusEnum.getById(rs.getLong("id_loanStatusEnum")));
                loanMov.setBroker(rs.getString("broker"));
                loanMov.setCommission(rs.getString("comission"));
                loanMov.setGrossValue(rs.getDouble("grossValue"));
                loanMov.setNetValue(rs.getDouble("netValue"));
                loanMov.setAmountOfInstallments(rs.getDouble("amountOfInstallments"));
                loanMov.setValueInstallments(rs.getDouble("valueInstallments"));
                loanMov.setOperator(rs.getString("operator"));
                loanMov.setNote(rs.getString("note"));
                loanMov.setFiles(rs.getString("files"));
                loanMov.setNumberADE(rs.getString("numberADE"));
                loanMov.setBenefitNumber(rs.getString("benefitNumber"));
                loanMov.setSpeciesCode(rs.getString("speciesCode"));
                loanMov.setTypist(rs.getString("typist"));
                
                list.add(loanMov);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<LoanMovementEntity> getListLoanByUserPersistence(int id_loan) {
        String query = "SELECT * FROM tb_loanMovement where id_loan =?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, id_loan);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<LoanMovementEntity> list = new ArrayList();
            while (rs.next()) {
                LoanMovementEntity loanMov = new LoanMovementEntity();
                loanMov.setId(rs.getInt("id"));

                LoanEntity loanEntity = new LoanEntity();
                loanEntity.setId(rs.getInt("id_loan"));
                loanMov.setLoanEntity(loanEntity);

                loanMov.setDate(rs.getString("date"));

                InstitutionEntity institutionEntity = new InstitutionEntity();
                institutionEntity.setId(rs.getInt("id_institution"));
                loanMov.setInstitutionEntity(institutionEntity);

                PlanEntity planEntity = new PlanEntity();
                planEntity.setId(rs.getInt("plan"));
                loanMov.setPlanEntity(planEntity);

                loanMov.setTransactionEnum(TransactionEnum.getById(rs.getLong("id_TransactionEnut")));
                UserInstitutionEntity userInstitutionEntity = new UserInstitutionEntity();
                userInstitutionEntity.setId(rs.getInt("id_user_institution"));
                loanMov.setInstitutionEntity(institutionEntity);
                loanMov.setLoanStatutsEnum(LoanStatusEnum.getById(rs.getLong("id_loanStatusEnum")));
                loanMov.setBroker(rs.getString("broker"));
                loanMov.setCommission(rs.getString("comission"));
                loanMov.setGrossValue(rs.getDouble("grossValue"));
                loanMov.setNetValue(rs.getDouble("netValue"));
                loanMov.setAmountOfInstallments(rs.getDouble("amountOfInstallments"));
                loanMov.setValueInstallments(rs.getDouble("valueInstallments"));
                loanMov.setOperator(rs.getString("operator"));
                loanMov.setNote(rs.getString("note"));
                loanMov.setFiles(rs.getString("files"));
                loanMov.setNumberADE(rs.getString("numberADE"));
                loanMov.setBenefitNumber(rs.getString("benefitNumber"));
                loanMov.setSpeciesCode(rs.getString("speciesCode"));
                loanMov.setTypist(rs.getString("typist"));
                
                list.add(loanMov);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    public static boolean insertLoanMovementPersistence(LoanMovementEntity loanMovementEntity) throws SQLException {
        String query = "INSERT INTO tb_loanMovement (id_loan, id_institution, id_plan, id_transaction,"
                + " id_user_institution, id_loan_status, broker, date, commission,\n"
                + "grossValue, netValue, amountOfInstallments, valueInstallments, operator, typist, \n"
                + "note, files, numberADE, benefitNumber, speciesCode) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, loanMovementEntity.getLoanEntity().getId());
            preparedStatement.setInt(2, loanMovementEntity.getInstitutionEntity().getId());
            preparedStatement.setInt(3, loanMovementEntity.getPlanEntity().getId());
            preparedStatement.setInt(4, loanMovementEntity.getTransactionEnum().getId());
            preparedStatement.setInt(5, loanMovementEntity.getUserInstitutionEntity().getId());
            preparedStatement.setInt(6, loanMovementEntity.getLoanStatutsEnum().getId());
            preparedStatement.setString(7, loanMovementEntity.getBroker());
            preparedStatement.setString(8, loanMovementEntity.getDate());
            preparedStatement.setString(9, loanMovementEntity.getCommission());
            preparedStatement.setDouble(10, loanMovementEntity.getGrossValue());
            preparedStatement.setDouble(11, loanMovementEntity.getNetValue());
            preparedStatement.setDouble(12, loanMovementEntity.getAmountOfInstallments());
            preparedStatement.setDouble(13, loanMovementEntity.getValueInstallments());
            preparedStatement.setString(14, loanMovementEntity.getOperator());
            preparedStatement.setString(15, loanMovementEntity.getTypist());
            preparedStatement.setString(16, loanMovementEntity.getNote());
            preparedStatement.setString(17, loanMovementEntity.getFiles());
            preparedStatement.setString(18, loanMovementEntity.getNumberADE());
            preparedStatement.setString(19, loanMovementEntity.getBenefitNumber());
            preparedStatement.setString(20, loanMovementEntity.getSpeciesCode());

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

}
