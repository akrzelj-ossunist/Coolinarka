package oss.akrzelj.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.Favorites;
import oss.akrzelj.models.Recipe;
import oss.akrzelj.models.User;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, String> {

    Favorites findByRecipe(Recipe recipe);

    List<Favorites> findByUser(User user, PageRequest pageRequest);
}