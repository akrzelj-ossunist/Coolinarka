package oss.akrzelj.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import oss.akrzelj.dtos.RecipeDto;
import oss.akrzelj.dtos.RecipeResDto;

import java.util.List;
import java.util.Map;

public interface RecipeController {

    ResponseEntity<RecipeResDto> addNewRecipe(RecipeDto recipeDto);

    ResponseEntity<RecipeResDto> editRecipe(String recipeId, RecipeDto recipeDto);

    ResponseEntity<Boolean> deleteRecipe(String recipeId);

    ResponseEntity<List<RecipeResDto>> listAll(Map<String, String> allParams);

    ResponseEntity<RecipeResDto> recipePage(String recipeId);

    ResponseEntity<List<RecipeResDto>> filterList(Map<String, String> allParams);

}
