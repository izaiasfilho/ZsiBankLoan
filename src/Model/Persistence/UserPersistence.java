/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Controller.AddressController;
import Model.Entities.AddressEntity;
import Model.Entities.CityEntity;
import Model.Entities.GenreEntity;
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
public class UserPersistence {

    public static boolean insertUserPersistence(UserEntity userEntity) throws SQLException {
        String query = "INSERT INTO tb_user (physicalPersonRegistration, Registration, name, spouse, issuingBody,"
                + "issuer, birthDate, naturalness, email, dad, mother, id_address, id_genre) VALUES (?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, userEntity.getPhysicalPersonRegistration());
            preparedStatement.setString(2, userEntity.getRegistration());
            preparedStatement.setString(3, userEntity.getName());
            preparedStatement.setString(4, userEntity.getSpouse());
            preparedStatement.setString(5, userEntity.getIssuingBody());
            preparedStatement.setString(6, userEntity.getIssuer());
            preparedStatement.setString(7, userEntity.getBirthDate());
            preparedStatement.setString(8, userEntity.getNaturalness());
            preparedStatement.setString(9, userEntity.getEmail());
            preparedStatement.setString(10, userEntity.getDad());
            preparedStatement.setString(11, userEntity.getMother());
            preparedStatement.setInt(12, userEntity.getAddressEntity().getId());
            preparedStatement.setInt(13, userEntity.getGenreEntity().getId());

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

    public static UserEntity getUserPersistence(UserEntity userEntity) {
        String query = "select \n"
                + "     u.id, u.physicalPersonRegistration, u.Registration, u.name, u.spouse, u.issuingBody,\n"
                + "     u.issuer, u.birthDate, u.naturalness, u.email, u.dad, u.mother,\n"
                + "     g.description,\n"
                + "     a.id, a.streetName, a.number, a.district, a.zipCode, a.complement,\n"
                + "     c.name,\n"
                + "     s.uf\n"
                + "FROM \n"
                + "     tb_user as u, tb_address as a, tb_city as c, tb_state as s, tb_genre g\n"
                + "where u.physicalPersonRegistration = ? \n"
                + "and (a.id_city = c.id\n"
                + "and c.id_state = s.id\n"
                + "and  g.id = u.id_genre)\n"
                + "group by u.id; ;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, userEntity.getPhysicalPersonRegistration());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                UserEntity user = new UserEntity();
                user.setId(rs.getInt(1));
                user.setPhysicalPersonRegistration(rs.getString(2));
                user.setRegistration(rs.getString(3));
                user.setName(rs.getString(4));
                user.setSpouse(rs.getString(5));
                user.setIssuingBody(rs.getString(6));
                user.setIssuer(rs.getString(7));
                user.setBirthDate(rs.getString(8));
                user.setNaturalness(rs.getString(9));
                user.setEmail(rs.getString(10));
                user.setDad(rs.getString(11));
                user.setMother(rs.getString(12));
                
                GenreEntity genre = new GenreEntity();
                genre.setDescription(rs.getString(13));
                user.setGenreEntity(genre);

                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setId(rs.getInt(14));
                addressEntity.setStreetName(rs.getString(15));
                addressEntity.setNumber(rs.getString(16));
                addressEntity.setDistrict(rs.getString(17));
                addressEntity.setZipCode(rs.getString(18));
                addressEntity.setComplement(rs.getString(19));
                
                CityEntity city = new CityEntity();
                city.setName(rs.getString(20));
                
                StateEntity state = new StateEntity();
                state.setUf(rs.getString(21));
                
                city.setStateEntity(state);
                addressEntity.setCityEntity(city);
                
                user.setAddressEntity(addressEntity);
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<UserEntity> getListUserPersistence() {
        String query = "SELECT * FROM tb_user;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            List<UserEntity> list = new ArrayList();
            while (rs.next()) {
                UserEntity user = new UserEntity();
                user.setPhysicalPersonRegistration(rs.getString(1));
                user.setRegistration(rs.getString(2));
                user.setName(rs.getString(3));
                user.setSpouse(rs.getString(4));
                user.setIssuingBody(rs.getString(5));
                user.setIssuer(rs.getString(6));
                user.setBirthDate(rs.getString(7));
                user.setNaturalness(rs.getString(8));
                user.setEmail(rs.getString(9));
                user.setDad(rs.getString(10));
                user.setMother(rs.getString(11));

                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setId(rs.getInt(12));

                user.setAddressEntity(addressEntity);
                
                GenreEntity genre = new GenreEntity();
                genre.setId(rs.getInt(13));
                user.setGenreEntity(genre);
                list.add(user);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(UserPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean updateUserPersistence(UserEntity userEntity) {
        String query = "UPDATE tb_user SET physicalPersonRegistration =? , Registration =? , name =?, "
                + "spouse =?, issuingBody =?, issuer =?, birthDate =?, naturalness =?, email =?, dad =?, "
                + "mother =?, id_address =?, id_genre =? where id = ?";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, userEntity.getPhysicalPersonRegistration());
            preparedStatement.setString(2, userEntity.getRegistration());
            preparedStatement.setString(3, userEntity.getName());
            preparedStatement.setString(4, userEntity.getSpouse());
            preparedStatement.setString(5, userEntity.getIssuingBody());
            preparedStatement.setString(6, userEntity.getIssuer());
            preparedStatement.setString(7, userEntity.getBirthDate());
            preparedStatement.setString(8, userEntity.getNaturalness());
            preparedStatement.setString(9, userEntity.getEmail());
            preparedStatement.setString(10, userEntity.getDad());
            preparedStatement.setString(11, userEntity.getMother());
            preparedStatement.setInt(12, userEntity.getAddressEntity().getId());
            preparedStatement.setInt(13, userEntity.getGenreEntity().getId());

            preparedStatement.setInt(14, userEntity.getId());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
