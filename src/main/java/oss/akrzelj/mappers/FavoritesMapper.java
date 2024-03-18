package oss.akrzelj.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oss.akrzelj.dtos.FavoritesDto;
import oss.akrzelj.models.Favorites;
import oss.akrzelj.services.interfaces.RecipeService;
import oss.akrzelj.services.interfaces.UserService;

@Mapper
public interface FavoritesMapper {

    @Mapping(target = "user", expression = "java(userService.findUserById(favoritesDto.getUser()))")
    @Mapping(target = "recipe", expression = "java(recipeService.filterById(favoritesDto.getRecipe()))")
    Favorites favoritesDtoToFavorites(FavoritesDto favoritesDto, UserService userService, RecipeService recipeService);
}
