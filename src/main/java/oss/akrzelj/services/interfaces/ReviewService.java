package oss.akrzelj.services.interfaces;

import oss.akrzelj.dtos.ReviewDto;
import oss.akrzelj.dtos.ReviewResDto;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    ReviewResDto createReview(ReviewDto reviewDto);

    ReviewResDto updateReview(String reviewId, ReviewDto reviewDto);

    void deleteReview(String reviewId);

    List<ReviewResDto> recipeReviews(String recipeId, Map<String, String> allParams); //All params will have page, perPage, sort

}
