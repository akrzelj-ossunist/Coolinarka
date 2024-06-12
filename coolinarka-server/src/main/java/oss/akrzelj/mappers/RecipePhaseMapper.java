package oss.akrzelj.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oss.akrzelj.dtos.recipe.RecipePhaseDto;
import oss.akrzelj.dtos.recipe.response.RecipeIngredientResponseDto;
import oss.akrzelj.dtos.recipe.response.RecipePhaseResponseDto;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.models.RecipeIngredient;
import oss.akrzelj.models.RecipePhase;
import oss.akrzelj.services.interfaces.RecipeService;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecipePhaseMapper {

    @Mapping(target = "recipe", ignore = true)
    RecipePhase recipePhaseDtoToRecipePhase(RecipePhaseDto recipePhaseDto);

    List<RecipePhaseResponseDto> phaseListToPhaseDtoList(List<RecipePhase> recipePhases);
}
