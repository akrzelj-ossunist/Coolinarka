package oss.akrzelj.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import oss.akrzelj.dtos.ReviewDto;
import oss.akrzelj.dtos.ReviewResDto;
import oss.akrzelj.models.Review;
import oss.akrzelj.services.interfaces.RecipeService;
import oss.akrzelj.services.interfaces.UserService;

import java.util.List;

@Mapper
public interface ReviewMapper {


    @Mapping(target = "user", expression = "java(userService.findUserById(reviewDto.getUser()))")
    @Mapping(target = "recipe", expression = "java(recipeService.filterById(reviewDto.getRecipe()))")
    @Mapping(target = "createdAt", expression = "java(new java.util.Date())")
    Review reviewDtoToReview(ReviewDto reviewDto, RecipeService recipeService, UserService userService);

    ReviewResDto reviewToReviewDto(Review review);

    List<ReviewResDto> reviewListToReviewDtoList(List<Review> reviewList);
}
