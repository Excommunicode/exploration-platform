package kz.orbitalis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import kz.orbitalis.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT CASE WHEN (COUNT(c) > 0) THEN TRUE ELSE FALSE END " +
            "FROM Category c " +
            "WHERE LOWER(c.name) LIKE :name")
    boolean existsByName(String name);
}