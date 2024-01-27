package oss.akrzelj.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

}