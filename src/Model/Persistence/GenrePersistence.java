/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Persistence;

import Model.Entities.GenreEntity;
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
public class GenrePersistence {
    
     public static GenreEntity getGenreByDescriptionPersistence(GenreEntity genreEntity) {
        String query = "SELECT * FROM tb_genre where description =?;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, genreEntity.getDescription());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                GenreEntity genre = new GenreEntity();
                genre.setId(rs.getInt("id"));
                genre.setDescription(rs.getString("description"));
                return genre;                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
     public static List<GenreEntity> getListGenrePersistence() {
        String query = "SELECT * FROM tb_genre;";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            List<GenreEntity> list = new ArrayList();
            while (rs.next()) {
                GenreEntity genre = new GenreEntity();
                genre.setId(rs.getInt("id"));
                genre.setDescription(rs.getString("description"));
                list.add(genre);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CityPersistence.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
      public static boolean insertGenrePersistence(GenreEntity genreEntity) throws SQLException {
        String query = "INSERT INTO tb_genre (description) VALUES (?)";
        PreparedStatement preparedStatement = null;
        if (Checks()) {
            closeConect();
        }
        try {
            preparedStatement = conect().prepareStatement(query);
            preparedStatement.setString(1, genreEntity.getDescription());

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
