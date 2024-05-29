package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import oss.akrzelj.controllers.interfaces.RecipeController;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.dtos.recipe.RecipeDto;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.services.interfaces.RecipeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<RecipeResDto> addNewRecipe(@RequestPart("recipe") RecipeDto recipeDto, @RequestPart("image") MultipartFile image) throws IOException, InvalidArgumentsException {

            recipeDto.setImage(image);
            var recipe = recipeService.createRecipe(recipeDto);

            return ResponseEntity.ok().build();

    }

    @Override
    public ResponseEntity<RecipeResDto> editRecipe(String recipeId, RecipeDto recipeDto) {
        //RecipeResDto recipe = recipeService.updateRecipe(recipeId, recipeDto);
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
