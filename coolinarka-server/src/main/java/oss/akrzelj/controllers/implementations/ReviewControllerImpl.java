package oss.akrzelj.controllers.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oss.akrzelj.controllers.interfaces.ReviewController;
import oss.akrzelj.dtos.ReviewDto;
import oss.akrzelj.dtos.ReviewPageDto;
import oss.akrzelj.dtos.ReviewResDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
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
    @PostMapping("/create")
    public ResponseEntity<ReviewResDto> createReview(@RequestBody ReviewDto reviewDto) throws ObjectDoesntExistException, InvalidArgumentsException, AlreadyExistException {
        System.out.println(reviewDto);
        ReviewResDto review = reviewService.createReview(reviewDto);
        return ResponseEntity.status(HttpStatus.OK).body(review);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable String id) throws ObjectDoesntExistException {
        reviewService.deleteReview(id);
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @Override
    @GetMapping("/list/recipe/{id}")
    public ResponseEntity<ReviewPageDto> recipeReviews(@PathVariable String id, @RequestParam Map<String, String> allParams) {
        ReviewPageDto reviewList = reviewService.recipeReviews(id, allParams);
        System.out.println(reviewList);
        return ResponseEntity.status(HttpStatus.OK).body(reviewList);
    }
}
