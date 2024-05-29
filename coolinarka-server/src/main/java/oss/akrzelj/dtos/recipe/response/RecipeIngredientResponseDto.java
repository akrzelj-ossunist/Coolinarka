package oss.akrzelj.dtos.recipe.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientResponseDto {
    private String id;
    private String ingredient;
    private double amount;
    private String unit;
}
