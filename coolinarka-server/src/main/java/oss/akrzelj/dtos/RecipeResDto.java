package oss.akrzelj.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oss.akrzelj.models.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResDto {

    private String id;

    private String name;

    private String description;

    private String[] instructions;

    private String[] ingredients;

    private User user;
}
