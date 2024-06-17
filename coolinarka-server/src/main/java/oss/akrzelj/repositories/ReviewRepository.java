package oss.akrzelj.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.models.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    Review findByUserId(String user);

    Page<Review> findByRecipeId(String recipeId, Pageable pageable);

    List<Review> findByRecipeId(String recipeId);

    Review findByUserIdAndRecipeId(String user, String recipe);
}