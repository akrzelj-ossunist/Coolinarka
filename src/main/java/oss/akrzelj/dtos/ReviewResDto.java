package oss.akrzelj.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.models.User;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResDto {

    private String id;

    private User user;

    private Recipe recipe;

    private int rating;

    private String comment;

    private Date createdAt;
}
