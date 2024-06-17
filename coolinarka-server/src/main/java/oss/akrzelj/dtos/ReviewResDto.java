package oss.akrzelj.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oss.akrzelj.dtos.recipe.response.RecipeResponseDto;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.models.User;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResDto {

    private String id;

    private UserResDto user;

    private RecipeResponseDto recipe;

    private int rating;

    private String comment;

    private Date createdAt;
}
