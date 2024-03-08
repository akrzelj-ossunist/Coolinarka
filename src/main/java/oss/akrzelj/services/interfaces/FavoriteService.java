package oss.akrzelj.services.interfaces;

import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.FavoritesResDto;
import oss.akrzelj.models.Favorites;

import java.util.List;

public interface FavoriteService {

    FavoritesResDto addToFavorites(FavoritesDto favoritesDto);

    List<FavoritesResDto> getAll(String userId);

    void remove(String favoriteId);

}
