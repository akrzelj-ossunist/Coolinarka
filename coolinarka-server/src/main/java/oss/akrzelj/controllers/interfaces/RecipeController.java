package oss.akrzelj.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.dtos.recipe.RecipeDto;
import oss.akrzelj.exceptions.InvalidArgumentsException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface RecipeController {

    ResponseEntity<RecipeResDto> addNewRecipe(RecipeDto recipeDto, MultipartFile image) throws IOException, InvalidArgumentsException;

    ResponseEntity<RecipeResDto> editRecipe(String recipeId, RecipeDto recipeDto);

    ResponseEntity<Boolean> deleteRecipe(String recipeId);

    ResponseEntity<List<RecipeResDto>> listAll(Map<String, String> allParams);

    ResponseEntity<RecipeResDto> recipePage(String recipeId);

    ResponseEntity<List<RecipeResDto>> filterList(Map<String, String> allParams);

}
