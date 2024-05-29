package oss.akrzelj.dtos.recipe.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipePhaseResponseDto {
    private String id;
    private int index;
    private String description;
}
