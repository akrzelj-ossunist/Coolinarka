package oss.akrzelj.services.interfaces;

import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.recipe.response.RecipeResponseDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;

import java.util.List;

public interface FavoriteService {

    void addToFavorites(FavoritesDto favoritesDto) throws InvalidArgumentsException, AlreadyExistException, ObjectDoesntExistException;

    List<RecipeResponseDto> getAll(String userId) throws ObjectDoesntExistException, InvalidArgumentsException;

    void remove(String favoriteId, String userId) throws ObjectDoesntExistException, InvalidArgumentsException;

}
