package oss.akrzelj.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.Favorites;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorites, String> {

}