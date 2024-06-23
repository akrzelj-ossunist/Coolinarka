package oss.akrzelj.services.interfaces;

import oss.akrzelj.dtos.recipe.RecipeDto;
import oss.akrzelj.dtos.recipe.RecipePageDto;
import oss.akrzelj.dtos.recipe.response.RecipeResponseDto;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
import oss.akrzelj.models.Recipe;

import java.io.IOException;
import java.util.Map;

public interface RecipeService {

    Boolean createRecipe(RecipeDto recipeDto) throws InvalidArgumentsException, IOException;

    RecipeResponseDto updateRecipe(String recipeId, RecipeDto recipeDto) throws ObjectDoesntExistException, IOException, InvalidArgumentsException;

    void deleteRecipe(String recipeId) throws ObjectDoesntExistException;
    RecipeResponseDto recipeResponseMapper(Recipe recipe);
    RecipePageDto listAllRecipes(Map<String, String> allParams); //filter page, perPage and sort

    RecipePageDto filterRecipes(Map<String, String> allParams); //all random shit and filter page, perPage and sort

    RecipeResponseDto filterById(String recipeId) throws ObjectDoesntExistException;

    RecipePageDto filterByUser(String userId, Map<String, String> allParams); //filter page, perPage and sort

}
