/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.Entities.AddressEntity;
import Model.Entities.CityEntity;
import Model.Entities.StateEntity;
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
public class AddressPersistence {

    public static boolean insertAddressPersistence(AddressEntity addressEntity) throws SQLException {
        String query = "INSERT INTO tb_address (id_city, streetName, number, district, zipCode, complement) "
                + "VALUES (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, addressEntity.getCityEntity().getId());
            preparedStatement.setString(2, addressEntity.getStreetName());
            preparedStatement.setString(3, addressEntity.getNumber());
            preparedStatement.setString(4, addressEntity.getDistrict());
            preparedStatement.setString(5, addressEntity.getZipCode());
            preparedStatement.setString(6, addressEntity.getComplement());

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

    //TODO montar join
    public static AddressEntity getAddressPersistence(AddressEntity addressEntity) {
        String query = "SELECT\n"
                + " a.id, a.streetName, a.number, a.district, a.zipCode, a.complement,\n"
                + " c.name, s.uf\n"
                + "FROM \n"
                + "tb_address as a, tb_city as c, tb_state as s\n"
                + "where a.id_city = c.id\n"
                + "and c.id_state = s.id\n"
                + "and a.zipCode = ?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, addressEntity.getZipCode());
            
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                AddressEntity address = new AddressEntity();
                address.setId(rs.getInt(1));
                address.setStreetName(rs.getString(2));
                address.setNumber(rs.getString(3));
                address.setDistrict(rs.getString(4));
                address.setZipCode(rs.getString(5));
                address.setComplement(rs.getString(6));

                CityEntity city = new CityEntity();
                city.setName(rs.getString(7));

                StateEntity stateEntity = new StateEntity();
                stateEntity.setUf(rs.getString(8));

                city.setStateEntity(stateEntity);
                address.setCityEntity(city);
                return address;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean updateUpdaatePersistence(AddressEntity addressEntity) {
        String query = "UPDATE tb_address SET id_city = ?, streetName = ?, "
                + "number = ?, district = ?, zipCode = ?, complement = ? where id = ?";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setInt(1, addressEntity.getCityEntity().getId());
            preparedStatement.setString(2, addressEntity.getStreetName());
            preparedStatement.setString(3, addressEntity.getNumber());
            preparedStatement.setString(4, addressEntity.getDistrict());
            preparedStatement.setString(5, addressEntity.getZipCode());
            preparedStatement.setString(6, addressEntity.getComplement());

            preparedStatement.setInt(7, addressEntity.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
