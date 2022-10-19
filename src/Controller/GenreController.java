/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entities.GenreEntity;
import Model.Services.Implementations.GenreImplementation;
import Model.Services.Interfaces.GenreInterface;
import java.util.List;

/**
 *
 * @author Izaias
 */
public class GenreController {
    GenreInterface repository = new GenreImplementation();
    
    public boolean insertGenreController(GenreEntity genreEntity){
        return repository.insetGenre(genreEntity);
    }
    
    public GenreEntity getGenreController(GenreEntity genreEntity){
        return repository.getGenre(genreEntity);
    }
    
    public List<GenreEntity> listGenre(){
        return repository.listGenre();
    }
}
