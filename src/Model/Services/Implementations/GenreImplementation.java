/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.GenreEntity;
import Model.Persistence.GenrePersistence;
import static Model.Persistence.GenrePersistence.getListGenrePersistence;
import Model.Services.Interfaces.GenreInterface;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class GenreImplementation implements GenreInterface{

    @Override
    public boolean insetGenre(GenreEntity genreEntity) {
        try {
            return getGenre(genreEntity) == null 
                    ? GenrePersistence.insertGenrePersistence(genreEntity) : false ;
        } catch (SQLException ex) {
            Logger.getLogger(GenreImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public GenreEntity getGenre(GenreEntity genreEntity) {
        return GenrePersistence.getGenreByDescriptionPersistence(genreEntity);
    }

    @Override
    public List<GenreEntity> listGenre() {
        return getListGenrePersistence();
    }
    
}
