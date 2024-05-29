package oss.akrzelj.dtos.recipe.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oss.akrzelj.dtos.UserResDto;
import oss.akrzelj.dtos.recipe.RecipeIngredientDto;
import oss.akrzelj.dtos.recipe.RecipePhaseDto;
import oss.akrzelj.models.enums.RecipeDiff;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponseDto {
    private String id;
    private String name;
    private String description;
    private UserResDto user;
    private String country;
    private RecipeDiff difficulty;
    private int people;
    private int prepTime;
    private List<RecipeIngredientResponseDto> ingredients = new ArrayList<>();
    private List<RecipePhaseResponseDto> phases = new ArrayList<>();
    private List<String> mealGroups = new ArrayList<>();
    private List<String> events = new ArrayList<>();
    private List<String> seasons = new ArrayList<>();
}
