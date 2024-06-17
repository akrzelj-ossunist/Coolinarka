package oss.akrzelj.services.interfaces;

import oss.akrzelj.dtos.ReviewDto;
import oss.akrzelj.dtos.ReviewPageDto;
import oss.akrzelj.dtos.ReviewResDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    ReviewResDto createReview(ReviewDto reviewDto) throws InvalidArgumentsException, ObjectDoesntExistException, AlreadyExistException;


    void deleteReview(String reviewId) throws ObjectDoesntExistException;

    ReviewPageDto recipeReviews(String recipeId, Map<String, String> allParams); //All params will have page, perPage, sort

}
