package oss.akrzelj.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.FavoritesResDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.dtos.recipe.response.RecipeResponseDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
import oss.akrzelj.mappers.FavoritesMapper;
import oss.akrzelj.mappers.RecipeMapper;
import oss.akrzelj.models.Favorites;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.models.User;
import oss.akrzelj.repositories.FavoriteRepository;
import oss.akrzelj.repositories.RecipeRepository;
import oss.akrzelj.services.interfaces.FavoriteService;
import oss.akrzelj.services.interfaces.RecipeService;
import oss.akrzelj.services.interfaces.UserService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final FavoritesMapper favoritesMapper;
    private final RecipeMapper recipeMapper;
    private final RecipeService recipeService;
    private final UserService userService;
    private final RecipeRepository recipeRepository;

    @Override
    public void addToFavorites(FavoritesDto favoritesDto) throws InvalidArgumentsException, AlreadyExistException, ObjectDoesntExistException {
        User user = userService.findUserById(favoritesDto.getUser());
        RecipeResponseDto recipeResponseDto = recipeService.filterById(favoritesDto.getRecipe());
        if(user == null) throw new ObjectDoesntExistException("User that want to add recipe in favorites doesnt exist in db");
        if(recipeResponseDto == null) throw new ObjectDoesntExistException("Recipe that want to be added in favorites doesnt exist in db");

        favoriteRepository.save(Favorites.builder().recipe(recipeRepository.findById(favoritesDto.getRecipe()).get()).user(user).build());
    }

    @Override
    public List<RecipeResponseDto> getAll(String userId) throws ObjectDoesntExistException, InvalidArgumentsException {
        List<Favorites> favorites = favoriteRepository.findByUserId(userId);
        return favorites.stream().map(favorite -> recipeService.recipeResponseMapper(favorite.getRecipe())).toList();
    }

    @Override
    public void remove(String favoriteId, String userId) throws ObjectDoesntExistException, InvalidArgumentsException {
        if(favoriteId == null) throw new InvalidArgumentsException("Sent arguments cannot be null!");

        Optional<Favorites> favorites = favoriteRepository.findByRecipeIdAndUserId(favoriteId, userId);

        if(favorites.isEmpty()) throw new ObjectDoesntExistException("Recipe you wanna delete from favorites doesn't exist!");

        favoriteRepository.delete(favorites.get());
    }

}
