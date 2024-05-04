package oss.akrzelj.services.interfaces;

import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
import oss.akrzelj.models.Favorites;

import java.util.List;
import java.util.Map;

public interface FavoriteService {

    void addToFavorites(FavoritesDto favoritesDto) throws InvalidArgumentsException, AlreadyExistException, ObjectDoesntExistException;

    List<RecipeResDto> getAll(String userId, int page, int size);

    List<RecipeResDto> filterByParams(String userId, Map<String, String> allParams);

    void remove(String favoriteId) throws ObjectDoesntExistException, InvalidArgumentsException;

    Favorites findByRecipeId(String recipeId);

}
