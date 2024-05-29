package oss.akrzelj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.RecipePhase;

public interface RecipePhaseRepository extends JpaRepository<RecipePhase, String> {
}
