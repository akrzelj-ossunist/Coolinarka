package oss.akrzelj.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.dtos.recipe.response.RecipeResponseDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;

import java.util.List;
import java.util.Map;

public interface FavoriteController {

    ResponseEntity<Boolean> addToFavorites(FavoritesDto favoritesDto) throws InvalidArgumentsException, ObjectDoesntExistException, AlreadyExistException;

    ResponseEntity<Boolean> removeFromFavorites(String favoritesId, String userId) throws ObjectDoesntExistException, InvalidArgumentsException;

    ResponseEntity<List<RecipeResponseDto>> getAll(String userId) throws ObjectDoesntExistException, InvalidArgumentsException;


}
