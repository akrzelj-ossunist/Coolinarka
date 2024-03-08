package oss.akrzelj.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oss.akrzelj.dtos.ReviewDto;
import oss.akrzelj.dtos.ReviewResDto;
import oss.akrzelj.repositories.ReviewRepository;
import oss.akrzelj.services.interfaces.ReviewService;

import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public ReviewResDto createReview(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public ReviewResDto updateReview(String reviewId, ReviewDto reviewDto) {
        return null;
    }

    @Override
    public void deleteReview(String reviewId) {

    }

    @Override
    public List<ReviewResDto> recipeReviews(String recipeId, Map<String, String> allParams) {
        return null;
    }

}
