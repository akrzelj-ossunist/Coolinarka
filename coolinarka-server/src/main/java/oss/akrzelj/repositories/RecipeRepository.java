package oss.akrzelj.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {

    Page<Recipe> findByUserId(String userId, Pageable pageable);

    @Query("SELECT r FROM Recipe r WHERE "
            + "(:name IS NULL OR LENGTH(:name) < 3 OR LOWER(r.name) LIKE %:name%) "
            + "AND (:country IS NULL OR LOWER(r.country) LIKE %:country%) "
            + "AND (:season IS NULL OR LOWER(r.season) LIKE %:season%) "
            + "AND (:difficulty IS NULL OR LOWER(r.difficulty) LIKE %:difficulty%) "
            + "AND (:ingredients IS NULL OR ("
            + "    SELECT COUNT(DISTINCT LOWER(ri.ingredient)) "
            + "    FROM RecipeIngredient ri "
            + "    WHERE ri.recipe.id = r.id AND LOWER(ri.ingredient) IN :ingredients) = :ingredientCount)")
    Page<Recipe> findRecipesByFilters(
            @Param("name") String name,
            @Param("country") String country,
            @Param("season") String season,
            @Param("difficulty") String difficulty,
            @Param("ingredients") List<String> ingredients,
            @Param("ingredientCount") long ingredientCount,
            Pageable pageable);
}