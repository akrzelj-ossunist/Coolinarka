package oss.akrzelj.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import oss.akrzelj.dtos.recipe.RecipeDto;
import oss.akrzelj.dtos.recipe.RecipePageDto;
import oss.akrzelj.dtos.recipe.response.RecipeResponseDto;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;

import java.io.IOException;
import java.util.Map;

public interface RecipeController {

    ResponseEntity<Boolean> addNewRecipe(RecipeDto recipeDto, MultipartFile image) throws IOException, InvalidArgumentsException;

    ResponseEntity<Boolean> editRecipe(String recipeId, RecipeDto recipeDto);

    ResponseEntity<Boolean> deleteRecipe(String recipeId) throws ObjectDoesntExistException;

    ResponseEntity<RecipePageDto> listAll(Map<String, String> allParams);

    ResponseEntity<RecipeResponseDto> recipePage(String recipeId) throws ObjectDoesntExistException;

    ResponseEntity<RecipePageDto> userRecipePage(String id, Map<String, String> allParams);

    ResponseEntity<RecipePageDto> filterList(Map<String, String> allParams);

}
