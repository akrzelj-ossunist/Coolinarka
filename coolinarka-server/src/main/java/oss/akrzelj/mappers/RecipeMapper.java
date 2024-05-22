package oss.akrzelj.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oss.akrzelj.dtos.RecipeDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.services.interfaces.UserService;

import java.util.List;

@Mapper
public interface RecipeMapper {

    @Mapping(target = "user", expression = "java(userService.findUserById(recipeDto.getUser()))")
    Recipe recipeDtoToRecipe(RecipeDto recipeDto, UserService userService) throws InvalidArgumentsException, ObjectDoesntExistException;

    RecipeResDto recipeToRecipeDto(Recipe recipe);

    List<RecipeResDto> recipeListToRecipeDtoList(List<Recipe> recipeList);
}
