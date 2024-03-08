package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oss.akrzelj.controllers.interfaces.FavoriteController;
import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.FavoritesResDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.mappers.FavoritesMapper;
import oss.akrzelj.services.interfaces.FavoriteService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/favorite")
@CrossOrigin
public class FavoriteControllerImpl implements FavoriteController {
    private final FavoriteService favoriteService;

    @Autowired
    public FavoriteControllerImpl(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @Override
    public ResponseEntity<Boolean> addToFavorites(FavoritesDto favoritesDto) {

        FavoritesResDto favorited = favoriteService.addToFavorites(favoritesDto);
        return null;
    }

    @Override
    public ResponseEntity<Boolean> removeFromFavorites(String favoritesId) {
        favoriteService.remove(favoritesId);
        return null;
    }

    @Override
    public ResponseEntity<RecipeResDto> listAllFavorites(String userId, Map<String, String> allParams) {
        return null;
    }
}
