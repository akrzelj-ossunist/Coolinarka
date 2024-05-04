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

    @Query("SELECT u FROM User u " +
            "WHERE (:username IS NULL OR LENGTH(:username) >= 3 AND LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))) " +
            "AND (:firstName IS NULL OR LENGTH(:firstName) >= 3 AND LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) " +
            "AND (:lastName IS NULL OR LENGTH(:lastName) >= 3 AND LOWER(u.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) " +
            "AND (:email IS NULL OR u.email = :email)")
    Page<User> findByUsernameAndFirstNameAndLastNameAndEmail(String username, String firstName, String lastName, String email, Pageable pageable);
}