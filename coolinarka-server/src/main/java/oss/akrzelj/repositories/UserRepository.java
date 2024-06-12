package oss.akrzelj.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE "
            + "(:username IS NULL OR (character_length(CAST(:username AS text)) >= 3 AND LOWER(u.username) LIKE %:username%)) "
            + "AND (:firstName IS NULL OR (character_length(CAST(:firstName AS text)) >= 3 AND LOWER(u.firstName) LIKE %:firstName%)) "
            + "AND (:lastName IS NULL OR (character_length(CAST(:lastName AS text)) >= 3 AND LOWER(u.lastName) LIKE %:lastName%))")
    Page<User> findByUsernameAndFirstNameAndLastName(String username, String firstName, String lastName, Pageable pageable);
}