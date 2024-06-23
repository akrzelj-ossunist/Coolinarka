package oss.akrzelj.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
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

    public ResponseEntity<Boolean> editRecipe(@PathVariable String id, @RequestPart("recipe") RecipeDto recipeDto, @RequestPart("image") MultipartFile image) throws ObjectDoesntExistException, IOException, InvalidArgumentsException;

    ResponseEntity<Boolean> deleteRecipe(String recipeId) throws ObjectDoesntExistException;

    ResponseEntity<RecipePageDto> listAll(Map<String, String> allParams);

    ResponseEntity<RecipeResponseDto> recipePage(String recipeId) throws ObjectDoesntExistException;

    ResponseEntity<RecipePageDto> userRecipePage(String id, Map<String, String> allParams);

    ResponseEntity<RecipePageDto> filterList(Map<String, String> allParams);

}
