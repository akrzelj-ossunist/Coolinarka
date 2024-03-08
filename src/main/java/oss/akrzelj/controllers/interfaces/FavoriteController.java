package oss.akrzelj.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.RecipeResDto;

import java.util.Map;

public interface FavoriteController {

    ResponseEntity<Boolean> addToFavorites(FavoritesDto favoritesDto);

    ResponseEntity<Boolean> removeFromFavorites(String favoritesId);

    ResponseEntity<RecipeResDto> listAllFavorites(String userId, Map<String, String> allParams);
}
