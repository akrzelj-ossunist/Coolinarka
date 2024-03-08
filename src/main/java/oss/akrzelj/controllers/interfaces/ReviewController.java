package oss.akrzelj.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import oss.akrzelj.dtos.ReviewDto;
import oss.akrzelj.dtos.ReviewResDto;

import java.util.List;
import java.util.Map;

public interface ReviewController {

    ResponseEntity<ReviewResDto> createReview(ReviewDto reviewDto);

    ResponseEntity<ReviewResDto> updateReview(String reviewId, ReviewDto reviewDto);

    ResponseEntity<Boolean> deleteReview(String reviewId);

    ResponseEntity<List<ReviewResDto>> recipeReviews(String recipeId, Map<String, String> allParams);

}
