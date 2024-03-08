package oss.akrzelj.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.FavoritesResDto;
import oss.akrzelj.repositories.FavoriteRepository;
import oss.akrzelj.services.interfaces.FavoriteService;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepository;

    @Override
    public FavoritesResDto addToFavorites(FavoritesDto favoritesDto) {
        return null;
    }

    @Override
    public List<FavoritesResDto> getAll(String userId) {
        return null;
    }

    @Override
    public void remove(String favoriteId) {

    }
}
