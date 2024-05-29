package oss.akrzelj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.RecipeIngredient;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, String> {
}
