package oss.akrzelj.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oss.akrzelj.dtos.recipe.RecipeIngredientDto;
import oss.akrzelj.dtos.recipe.response.RecipeIngredientResponseDto;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.models.RecipeIngredient;
import oss.akrzelj.services.interfaces.RecipeService;
import oss.akrzelj.services.interfaces.UserService;

@Mapper(componentModel = "spring")
public interface RecipeIngredientMapper {

    @Mapping(target = "recipe", ignore = true)
    RecipeIngredient recipeIngredientDtoToRecipeIngredient(RecipeIngredientDto recipeIngredientDto);

    RecipeIngredientResponseDto recipeIngredientToRecipeIngredientDto(RecipeIngredient recipeIngredient);
}
