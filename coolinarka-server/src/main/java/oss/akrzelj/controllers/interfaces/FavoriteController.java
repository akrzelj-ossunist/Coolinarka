package oss.akrzelj.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;

import java.util.List;
import java.util.Map;

public interface FavoriteController {

    ResponseEntity<Boolean> addToFavorites(FavoritesDto favoritesDto) throws InvalidArgumentsException, ObjectDoesntExistException, AlreadyExistException;

    ResponseEntity<Boolean> removeFromFavorites(String favoritesId) throws ObjectDoesntExistException, InvalidArgumentsException;

    ResponseEntity<List<RecipeResDto>> getAll(String userId, Map<String, String> allParams) throws ObjectDoesntExistException, InvalidArgumentsException;

    public ResponseEntity<List<RecipeResDto>> listAllFavorites(String userId, Map<String, String> allParams);

}
