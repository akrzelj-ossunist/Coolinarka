package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oss.akrzelj.controllers.interfaces.ReviewController;
import oss.akrzelj.dtos.ReviewDto;
import oss.akrzelj.dtos.ReviewResDto;
import oss.akrzelj.mappers.ReviewMapper;
import oss.akrzelj.services.interfaces.ReviewService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/review")
@CrossOrigin
public class ReviewControllerImpl implements ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewControllerImpl(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Override
    public ResponseEntity<ReviewResDto> createReview(ReviewDto reviewDto) {
        ReviewResDto review = reviewService.createReview(reviewDto);
        return null;
    }

    @Override
    public ResponseEntity<ReviewResDto> updateReview(String reviewId, ReviewDto reviewDto) {
        ReviewResDto review = reviewService.updateReview(reviewId, reviewDto);
        return null;
    }

    @Override
    public ResponseEntity<Boolean> deleteReview(String reviewId) {
        reviewService.deleteReview(reviewId);
        return null;
    }

    @Override
    public ResponseEntity<List<ReviewResDto>> recipeReviews(String recipeId, Map<String, String> allParams) {
        List<ReviewResDto> reviewList = reviewService.recipeReviews(recipeId, allParams);
        return null;
    }
}
