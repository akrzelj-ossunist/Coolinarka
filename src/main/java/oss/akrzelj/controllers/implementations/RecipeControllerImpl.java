package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oss.akrzelj.controllers.interfaces.RecipeController;
import oss.akrzelj.dtos.RecipeDto;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.mappers.RecipeMapper;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.services.interfaces.RecipeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/recipe")
@CrossOrigin
public class RecipeControllerImpl implements RecipeController {
    private final RecipeService recipeService;

    @Autowired
    public RecipeControllerImpl(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public ResponseEntity<RecipeResDto> addNewRecipe(RecipeDto recipeDto) {
        RecipeResDto recipe = recipeService.createRecipe(recipeDto);
        return null;
    }

    @Override
    public ResponseEntity<RecipeResDto> editRecipe(String recipeId, RecipeDto recipeDto) {
        RecipeResDto recipe = recipeService.updateRecipe(recipeId, recipeDto);
        return null;
    }

    @Override
    public ResponseEntity<Boolean> deleteRecipe(String recipeId) {
        recipeService.deleteRecipe(recipeId);
        return null;
    }

    @Override
    public ResponseEntity<List<RecipeResDto>> listAll(Map<String, String> allParams) {
        List<RecipeResDto> recipeList = recipeService.listAllRecipes(allParams);
        return null;
    }

    @Override
    public ResponseEntity<RecipeResDto> recipePage(String recipeId) {
        Recipe recipe = recipeService.filterById(recipeId);
        return null;
    }

    @Override
    public ResponseEntity<List<RecipeResDto>> filterList(Map<String, String> allParams) {
        List<RecipeResDto> recipeList = recipeService.filterRecipes(allParams);
        return null;
    }
}
