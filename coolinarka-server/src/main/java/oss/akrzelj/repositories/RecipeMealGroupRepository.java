package oss.akrzelj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import oss.akrzelj.models.RecipeMealGroup;

public interface RecipeMealGroupRepository extends JpaRepository<RecipeMealGroup, String> {
}
