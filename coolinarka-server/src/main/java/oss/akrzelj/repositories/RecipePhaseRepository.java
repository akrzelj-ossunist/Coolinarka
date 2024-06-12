package oss.akrzelj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.RecipePhase;

import java.util.List;

public interface RecipePhaseRepository extends JpaRepository<RecipePhase, String> {
    List<RecipePhase> findByRecipeId(String id);
}
