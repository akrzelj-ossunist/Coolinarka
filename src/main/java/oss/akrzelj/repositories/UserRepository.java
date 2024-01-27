package oss.akrzelj.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}