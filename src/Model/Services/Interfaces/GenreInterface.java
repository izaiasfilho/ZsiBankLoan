/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Interfaces;

import Model.Entities.GenreEntity;
import java.util.List;

/**
 *
 * @author Izaias
 */
public interface GenreInterface {
    
    public boolean insetGenre(GenreEntity genreEntity);
    
    public GenreEntity getGenre(GenreEntity genreEntity);
    
    public List<GenreEntity> listGenre();
}
