package oss.akrzelj.dtos.recipe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    private MultipartFile image;
    private String name;
    private String description;
    private String user;
    private String country;
    private String difficulty;
    private int people;
    private int prepTime;
    private List<RecipeIngredientDto> ingredients = new ArrayList<>();
    private List<RecipePhaseDto> phases = new ArrayList<>();
    private List<String> mealGroup = new ArrayList<>();
    private String season;
    private List<String> events = new ArrayList<>();
}
