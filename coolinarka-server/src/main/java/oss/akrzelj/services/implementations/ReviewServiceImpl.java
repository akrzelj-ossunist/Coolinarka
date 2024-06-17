package oss.akrzelj.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import oss.akrzelj.dtos.ReviewDto;
import oss.akrzelj.dtos.ReviewPageDto;
import oss.akrzelj.dtos.ReviewResDto;
import oss.akrzelj.exceptions.AlreadyExistException;
import oss.akrzelj.exceptions.InvalidArgumentsException;
import oss.akrzelj.exceptions.ObjectDoesntExistException;
import oss.akrzelj.mappers.UserMapper;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.models.Review;
import oss.akrzelj.models.User;
import oss.akrzelj.repositories.RecipeRepository;
import oss.akrzelj.repositories.ReviewRepository;
import oss.akrzelj.services.interfaces.ReviewService;
import oss.akrzelj.services.interfaces.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final RecipeRepository recipeRepository;
    private final UserMapper userMapper;

    @Override
    public ReviewResDto createReview(ReviewDto reviewDto) throws InvalidArgumentsException, ObjectDoesntExistException, AlreadyExistException {
        if(reviewDto == null) throw  new InvalidArgumentsException("Sent review arguments cannot be null!");

        User user = userService.findUserById(reviewDto.getUser());
        if(user == null) throw new ObjectDoesntExistException("User that wants to review doesnt exist!");

        Optional<Recipe> recipe = recipeRepository.findById(reviewDto.getRecipe());
        if(recipe.isEmpty()) throw new ObjectDoesntExistException("Recipe that wants to be reviewed doesnt exist!");

        Review reviewAlreadyExist = reviewRepository.findByUserIdAndRecipeId(reviewDto.getUser(), reviewDto.getRecipe());
        if(reviewAlreadyExist != null) throw new AlreadyExistException("User already reviewed this recipe!");

        Review review = reviewRepository.save(Review.builder().recipe(recipe.get()).user(user).rating(reviewDto.getRating()).comment(reviewDto.getComment()).build());

        recipe.get().setRating(getRecipeRating(reviewDto.getRecipe()));

        recipeRepository.save(recipe.get());

        return ReviewResDto.builder().id(review.getId()).createdAt(review.getCreatedAt()).comment(review.getComment()).rating(review.getRating()).build();
    }

    public double getRecipeRating(String recipeId) throws ObjectDoesntExistException {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if(recipe.isEmpty()) throw new ObjectDoesntExistException("Recipe that wants to be reviewed doesnt exist!");

        List<Review> reviews = reviewRepository.findByRecipeId(recipeId);

        return reviews.stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);
    }

    @Override
    public void deleteReview(String reviewId) throws ObjectDoesntExistException {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if(review.isEmpty()) throw new ObjectDoesntExistException("Review you wanna delete doesnt exit!");

        reviewRepository.delete(review.get());
    }

    @Override
    public ReviewPageDto recipeReviews(String recipeId, Map<String, String> allParams) {
        int page = allParams.get("page") != null ? Integer.parseInt(allParams.get("page")) - 1 : 0;
        int size = allParams.get("size") != null ? Integer.parseInt(allParams.get("size")) : 10;

        Pageable pageable = (Pageable) PageRequest.of(page, size);

        Page<Review> reviewPage = reviewRepository.findByRecipeId(recipeId, pageable);

        List<Review> reviews = reviewPage.getContent();

        List<ReviewResDto> reviewResDtos = reviews.stream().map(review -> ReviewResDto.builder()
                                                                                        .id(review.getId())
                                                                                        .createdAt(review.getCreatedAt())
                                                                                        .comment(review.getComment())
                                                                                        .rating(review.getRating())
                                                                                        .user(userMapper.userToUserDto(review.getUser()))
                                                                                        .createdAt(review.getCreatedAt())
                                                                                        .build()).toList();
        return ReviewPageDto.builder()
                .reviewPage(reviewResDtos)
                .pageNum(page)
                .pageSize(size)
                .lastPage(reviewPage.getTotalPages())
                .build();
    }

}
