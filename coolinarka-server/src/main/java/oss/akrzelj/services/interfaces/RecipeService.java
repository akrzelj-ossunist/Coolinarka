package oss.akrzelj.services.interfaces;

import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.dtos.recipe.RecipeDto;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.models.Recipe;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface RecipeService {

    Boolean createRecipe(RecipeDto recipeDto) throws InvalidArgumentsException, IOException;

    RecipeResDto updateRecipe(String recipeId, RecipeDto recipeDto);

    void deleteRecipe(String recipeId);

    List<RecipeResDto> listAllRecipes(Map<String, String> allParams); //filter page, perPage and sort

    List<RecipeResDto> filterRecipes(Map<String, String> allParams); //all random shit and filter page, perPage and sort

    Recipe filterById(String recipeId);

    RecipeResDto filterByName(String recipeName);

    List<RecipeResDto> filterByUser(String userId, Map<String, String> allParams); //filter page, perPage and sort

    List<RecipeResDto> filterByIngredients(Map<String, String> allParams); // filter all ingredients and filter page, perPage and sort

}
