package oss.akrzelj.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.dtos.FavoritesResDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
import oss.akrzelj.mappers.FavoritesMapper;
import oss.akrzelj.mappers.RecipeMapper;
import oss.akrzelj.models.Favorites;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.models.User;
import oss.akrzelj.repositories.FavoriteRepository;
import oss.akrzelj.services.interfaces.FavoriteService;
import oss.akrzelj.services.interfaces.RecipeService;
import oss.akrzelj.services.interfaces.UserService;

import java.util.*;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final FavoritesMapper favoritesMapper;
    private final RecipeMapper recipeMapper;
    private final RecipeService recipeService;
    private final UserService userService;

    @Autowired
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, FavoritesMapper favoritesMapper, RecipeMapper recipeMapper, RecipeService recipeService, UserService userService) {
        this.favoriteRepository = favoriteRepository;
        this.favoritesMapper = favoritesMapper;
        this.recipeMapper = recipeMapper;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @Override
    public void addToFavorites(FavoritesDto favoritesDto) throws InvalidArgumentsException, AlreadyExistException, ObjectDoesntExistException {
        if(favoritesDto == null) throw new InvalidArgumentsException("Recipe you wanna add to favorites cannot be null!");

        if(findByRecipeId(favoritesDto.getRecipe()) != null) throw new AlreadyExistException("Recipe you wanna add in favorites is already in favorites!");

        if(recipeService.filterById(favoritesDto.getRecipe()) == null) throw new ObjectDoesntExistException("Recipe you wanna add in favorites doesn't exist!");

        favoriteRepository.save(favoritesMapper.favoritesDtoToFavorites(favoritesDto, userService, recipeService));
    }

    @Override
    public List<RecipeResDto> getAll(String userId, int page, int size) {
        User user = userService.findUserById(userId);
        List<Favorites> favorites = favoriteRepository.findByUser(user, PageRequest.of(page, size));
        List<Recipe> recipes = favorites.stream().map(Favorites::getRecipe).toList();
        return recipeMapper.recipeListToRecipeDtoList(recipes);
    }

    @Override
    public List<RecipeResDto> filterByParams(String userId, Map<String, String> allParams) {

        List<RecipeResDto> filteredRecipes = recipeService.filterRecipes(allParams);


        return null;
    }

    @Override
    public void remove(String favoriteId) throws ObjectDoesntExistException, InvalidArgumentsException {
        if(favoriteId == null) throw new InvalidArgumentsException("Sent arguments cannot be null!");

        Optional<Favorites> favorites = favoriteRepository.findById(favoriteId);

        if(favorites.isEmpty()) throw new ObjectDoesntExistException("Recipe you wanna delete from favorites doesn't exist!");

        favoriteRepository.delete(favorites.get());
    }

    @Override
    public Favorites findByRecipeId(String recipeId) {
        Recipe recipe = recipeService.filterById(recipeId);
        return favoriteRepository.findByRecipe(recipe);
    }
}
