package oss.akrzelj.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {

}