package oss.akrzelj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.RecipeEvent;

public interface RecipeEventRepository extends JpaRepository<RecipeEvent, String> {
}
