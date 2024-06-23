package oss.akrzelj.services.implementations;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import oss.akrzelj.dtos.recipe.RecipeDto;
import oss.akrzelj.dtos.recipe.RecipePageDto;
import oss.akrzelj.dtos.recipe.response.RecipeResponseDto;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
import oss.akrzelj.mappers.RecipeIngredientMapper;
import oss.akrzelj.mappers.RecipeMapper;
import oss.akrzelj.mappers.RecipePhaseMapper;
import oss.akrzelj.mappers.UserMapper;
import oss.akrzelj.models.*;
import oss.akrzelj.repositories.*;
import oss.akrzelj.services.interfaces.RecipeService;
import oss.akrzelj.utils.ImageUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    private final UserMapper userMapper;
    private final ImageUtil imageUtil;

    @Override
    public Boolean createRecipe(RecipeDto recipeDto) throws InvalidArgumentsException, IOException {

        if(recipeDto == null) throw new InvalidArgumentsException("Sent recipe argument data cannot be null!");

        /* Saves image locally so we know where to access it */
        imageUtil.save("../coolinarka-gui/src/uploads", recipeDto.getImage().getOriginalFilename(), recipeDto.getImage());

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
    public RecipeResponseDto updateRecipe(String recipeId, RecipeDto recipeDto) throws ObjectDoesntExistException, IOException, InvalidArgumentsException {
        deleteRecipe(recipeId);
        createRecipe(recipeDto);
        return null;
    }

    @Override
    public void deleteRecipe(String recipeId) throws ObjectDoesntExistException {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()) throw new ObjectDoesntExistException("Recipe you want to delete doesn't exist");

        Recipe recipe = recipeOptional.get();
        imageUtil.delete(recipe.getImage());

        recipeRepository.delete(recipe);
    }


    @Override
    public RecipePageDto listAllRecipes(Map<String, String> allParams) {
        int page = allParams.get("page") != null ? Integer.parseInt(allParams.get("page")) - 1 : 0;
        int size = allParams.get("size") != null ? Integer.parseInt(allParams.get("size")) : 8;

        Pageable pageable = (Pageable) PageRequest.of(page, size);

        List<Recipe> recipeList = recipeRepository.findAll(pageable).getContent();

        List<RecipeResponseDto> recipeResponses = recipeList.stream().map(this::recipeResponseMapper).toList();

        return RecipePageDto.builder()
                .recipePage(recipeResponses)
                .pageNum(page)
                .pageSize(size)
                .lastPage((int) Math.ceil((double) recipeRepository.count() / size))
                .build();
    }

    @Override
    public RecipeResponseDto recipeResponseMapper(Recipe recipe) {
        List<RecipeIngredient> ingredients = recipeIngredientRepository.findByRecipeId(recipe.getId());
        List<RecipePhase> phases = recipePhaseRepository.findByRecipeId(recipe.getId());
        List<RecipeMealGroup> mealGroups = recipeMealGroupRepository.findByRecipeId(recipe.getId());
        List<RecipeEvent> events = recipeEventRepository.findByRecipeId(recipe.getId());

        return RecipeResponseDto.builder()
                .id(recipe.getId())
                .image(recipe.getImage())
                .country(recipe.getCountry())
                .name(recipe.getName())
                .description(recipe.getDescription())
                .difficulty(recipe.getDifficulty())
                .user(userMapper.userToUserDto(recipe.getUser()))
                .people(recipe.getPeople())
                .prepTime(recipe.getPrepTime())
                .ingredients(recipeIngredientMapper.ingredientListToIngredientDtoList(ingredients))
                .season(recipe.getSeason())
                .phases(recipePhaseMapper.phaseListToPhaseDtoList(phases))
                .mealGroups(mealGroups.stream().map(RecipeMealGroup::getMealGroup).toList())
                .events(events.stream().map(RecipeEvent::getEvent).toList())
                .build();
    }


    public RecipePageDto filterRecipes(Map<String, String> allParams) {
        int page = allParams.get("page") != null ? Integer.parseInt(allParams.get("page")) - 1 : 0;
        int size = allParams.get("size") != null ? Integer.parseInt(allParams.get("size")) : 6;

        String sort = allParams.get("sort");
        if (StringUtils.isEmpty(sort)) {
            sort = "id";
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, sort));

        String name = allParams.get("name");
        String country = allParams.get("country");
        String season = allParams.get("season");
        String userId = allParams.get("userId");
        String difficulty = allParams.get("difficulty");
        List<String> ingredients = allParams.get("ingredients") != null && !allParams.get("ingredients").isEmpty()
                ? Arrays.asList(allParams.get("ingredients").toLowerCase().split(","))
                : null;

        long ingredientCount = ingredients != null ? ingredients.size() : 0;

        Page<Recipe> recipePage = recipeRepository.findRecipesByFilters(
                name != null && !name.isEmpty() ? name.toLowerCase() : null,
                country != null && !country.isEmpty() ? country.toLowerCase() : null,
                season != null && !season.isEmpty() ? season.toLowerCase() : null,
                difficulty != null && !difficulty.isEmpty() ? difficulty.toLowerCase() : null,
                userId != null && !userId.isEmpty() ? userId : null,
                ingredients,
                ingredientCount,
                pageable);

        List<Recipe> recipeList = recipePage.getContent();

        return RecipePageDto.builder()
                .recipePage(recipeList.stream().map(this::recipeResponseMapper).toList())
                .pageNum(page)
                .pageSize(size)
                .lastPage(recipePage.getTotalPages())
                .build();
    }

    @Override
    public RecipeResponseDto filterById(String recipeId) throws ObjectDoesntExistException {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);

        if(recipe.isEmpty()) throw new ObjectDoesntExistException("Recipe you are looking for doesnt exist");

        return recipeResponseMapper(recipe.get());
    }

    @Override
    public RecipePageDto filterByUser(String userId, Map<String, String> allParams) {
        int page = allParams.get("page") != null ? Integer.parseInt(allParams.get("page")) - 1 : 0;
        int size = allParams.get("size") != null ? Integer.parseInt(allParams.get("size")) : 1;

        Pageable pageable = (Pageable) PageRequest.of(page, size);

        List<Recipe> recipeList = recipeRepository.findByUserId(userId, pageable).getContent();

        List<RecipeResponseDto> recipeResponses = recipeList.stream().map(this::recipeResponseMapper).toList();

        return RecipePageDto.builder()
                .recipePage(recipeResponses)
                .pageNum(page)
                .pageSize(size)
                .lastPage((int) Math.ceil((double) recipeRepository.count() / size))
                .build();
    }

}
