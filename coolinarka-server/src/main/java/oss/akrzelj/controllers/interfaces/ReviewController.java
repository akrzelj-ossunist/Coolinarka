package oss.akrzelj.controllers.interfaces;

import org.springframework.http.ResponseEntity;
import oss.akrzelj.dtos.ReviewDto;
import oss.akrzelj.dtos.ReviewPageDto;
import oss.akrzelj.dtos.ReviewResDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;

import java.util.List;
import java.util.Map;

public interface ReviewController {

    ResponseEntity<ReviewResDto> createReview(ReviewDto reviewDto) throws ObjectDoesntExistException, InvalidArgumentsException, AlreadyExistException;

    ResponseEntity<Boolean> deleteReview(String reviewId) throws ObjectDoesntExistException;

    ResponseEntity<ReviewPageDto> recipeReviews(String recipeId, Map<String, String> allParams);

}
