package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import oss.akrzelj.controllers.interfaces.RecipeController;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.dtos.recipe.RecipeDto;
import oss.akrzelj.dtos.recipe.RecipePageDto;
import oss.akrzelj.dtos.recipe.response.RecipeResponseDto;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
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
            recipeService.createRecipe(recipeDto);

            return ResponseEntity.ok().build();

    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<RecipeResDto> editRecipe(@PathVariable String id, @RequestBody RecipeDto recipeDto) {
        //RecipeResDto recipe = recipeService.updateRecipe(recipeId, recipeDto);
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRecipe(@PathVariable String id) throws ObjectDoesntExistException {
        recipeService.deleteRecipe(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @GetMapping("/list")
    public ResponseEntity<RecipePageDto> listAll(@RequestParam Map<String, String> allParams) {
        RecipePageDto recipes = recipeService.listAllRecipes(allParams);

        return ResponseEntity.ok().body(recipes);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDto> recipePage(@PathVariable String id) throws ObjectDoesntExistException {
        RecipeResponseDto recipeResponseDto = recipeService.filterById(id);

        return ResponseEntity.ok().body(recipeResponseDto);
    }

    @Override
    @GetMapping("/user/{id}")
    public ResponseEntity<RecipePageDto> userRecipePage(@PathVariable String id, @RequestParam Map<String, String> allParams) {
        RecipePageDto recipePageDto = recipeService.filterByUser(id, allParams);

        return ResponseEntity.ok().body(recipePageDto);
    }

    @Override
    @GetMapping("/filter")
    public ResponseEntity<RecipePageDto> filterList(@RequestParam Map<String, String> allParams) {
        RecipePageDto recipePageDto = recipeService.filterRecipes(allParams);

        return ResponseEntity.ok().body(recipePageDto);
    }
}
