package oss.akrzelj.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {


    @NotNull
    private String user;

    @NotNull
    private String recipe;

    @NotBlank
    private int rating;

    private String comment;

}
