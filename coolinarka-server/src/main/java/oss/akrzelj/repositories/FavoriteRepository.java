package oss.akrzelj.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.Favorites;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, String> {

    Favorites findByRecipe(Recipe recipe);

    List<Favorites> findByUser(User user, PageRequest pageRequest);

    List<Favorites> findByUserId(String userId);

    Optional<Favorites> findByRecipeId(String favoriteId);

    Optional<Favorites> findByRecipeIdAndUserId(String favoriteId, String userId);
}