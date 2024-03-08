package oss.akrzelj.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotEmpty
    private String[] instructions;

    @NotEmpty
    private String[] ingredients;

    @NotNull
    private String user;
}
