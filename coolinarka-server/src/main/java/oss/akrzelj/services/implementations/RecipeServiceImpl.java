package oss.akrzelj.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.dtos.recipe.RecipeDto;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.mappers.RecipeIngredientMapper;
import oss.akrzelj.mappers.RecipeMapper;
import oss.akrzelj.mappers.RecipePhaseMapper;
import oss.akrzelj.models.*;
import oss.akrzelj.repositories.*;
import oss.akrzelj.services.interfaces.RecipeService;
import oss.akrzelj.utils.SaveImage;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipePhaseRepository recipePhaseRepository;
    private final RecipeMealGroupRepository recipeMealGroupRepository;
    private final RecipeEventRepository recipeEventRepository;
    private final RecipeIngredientMapper recipeIngredientMapper;
    private final RecipePhaseMapper recipePhaseMapper;
    private final RecipeMapper recipeMapper;
    private final SaveImage saveImage;

    @Override
    public Boolean createRecipe(RecipeDto recipeDto) throws InvalidArgumentsException, IOException {

        if(recipeDto == null) throw new InvalidArgumentsException("Sent recipe argument data cannot be null!");

        /* Saves image locally so we know where to access it */
        saveImage.save("../coolinarka-gui/src/uploads", recipeDto.getImage().getOriginalFilename(), recipeDto.getImage());

        /* Saves recipe in DB */
        Recipe recipe = recipeRepository.save(recipeMapper.recipeDtoToRecipe(recipeDto));

        /* Goes through list of ingredients and saves each in DB Recipe_Ingredient */
        recipeDto.getIngredients().forEach(recipeIngredientDto -> {
            RecipeIngredient recipeIngredient = recipeIngredientMapper.recipeIngredientDtoToRecipeIngredient(recipeIngredientDto);
            recipeIngredient.setRecipe(recipe);
            recipeIngredientRepository.save(recipeIngredient);
        });

        /* Goes through list of phases and saves each in DB Recipe_Phase */
        recipeDto.getPhases().forEach(recipePhaseDto -> {
            RecipePhase recipePhase = recipePhaseMapper.recipePhaseDtoToRecipePhase(recipePhaseDto);
            recipePhase.setRecipe(recipe);
            recipePhaseRepository.save(recipePhase);
        });

        recipeDto.getEvents().forEach(event -> recipeEventRepository.save(RecipeEvent.builder().recipe(recipe).event(event).build()));

        recipeDto.getMealGroup().forEach(mealGroup -> recipeMealGroupRepository.save(RecipeMealGroup.builder().mealGroup(mealGroup).recipe(recipe).build()));

        return true;
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
