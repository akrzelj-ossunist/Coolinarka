package oss.akrzelj.services.interfaces;

import oss.akrzelj.dtos.RecipeDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.models.Recipe;

import java.util.List;
import java.util.Map;

public interface RecipeService {

    RecipeResDto createRecipe(RecipeDto recipeDto);

    RecipeResDto updateRecipe(String recipeId, RecipeDto recipeDto);

    void deleteRecipe(String recipeId);

    List<RecipeResDto> listAllRecipes(Map<String, String> allParams); //filter page, perPage and sort

    List<RecipeResDto> filterRecipes(Map<String, String> allParams); //all random shit and filter page, perPage and sort

    Recipe filterById(String recipeId);

    RecipeResDto filterByName(String recipeName);

    List<RecipeResDto> filterByUser(String userId, Map<String, String> allParams); //filter page, perPage and sort

    List<RecipeResDto> filterByIngredients(Map<String, String> allParams); // filter all ingredients and filter page, perPage and sort

}
