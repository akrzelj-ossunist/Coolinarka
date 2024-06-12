package oss.akrzelj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.RecipeMealGroup;

import java.util.List;

public interface RecipeMealGroupRepository extends JpaRepository<RecipeMealGroup, String> {
    List<RecipeMealGroup> findByRecipeId(String id);
}
