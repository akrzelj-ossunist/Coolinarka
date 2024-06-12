package oss.akrzelj.dtos.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oss.akrzelj.dtos.RecipeResDto;
import oss.akrzelj.dtos.recipe.response.RecipeResponseDto;
import oss.akrzelj.models.Recipe;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipePageDto {
    private int pageNum;
    private int pageSize;
    private int lastPage;
    private List<RecipeResponseDto> recipePage;
}
