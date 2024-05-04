package oss.akrzelj.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oss.akrzelj.dtos.RecipeDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.repositories.RecipeRepository;
import oss.akrzelj.services.interfaces.RecipeService;

import java.util.List;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    @Override
    public RecipeResDto createRecipe(RecipeDto recipeDto) {
        return null;
    }

    @Override
    public RecipeResDto updateRecipe(String recipeId, RecipeDto recipeDto) {
        return null;
    }

    @Override
    public void deleteRecipe(String recipeId) {

    }

    @Override
    public List<RecipeResDto> listAllRecipes(Map<String, String> allParams) {
        return null;
    }


    @Override
    public List<RecipeResDto> filterRecipes(Map<String, String> allParams) {
        return null;
    }

    @Override
    public Recipe filterById(String recipeId) {
        return null;
    }

    @Override
    public RecipeResDto filterByName(String recipeName) {
        return null;
    }

    @Override
    public List<RecipeResDto> filterByUser(String userId, Map<String, String> allParams) {
        return null;
    }

    @Override
    public List<RecipeResDto> filterByIngredients(Map<String, String> allParams) {
        return null;
    }


}
