package oss.akrzelj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.RecipeEvent;

import java.util.List;

public interface RecipeEventRepository extends JpaRepository<RecipeEvent, String> {
    List<RecipeEvent> findByRecipeId(String id);
}
