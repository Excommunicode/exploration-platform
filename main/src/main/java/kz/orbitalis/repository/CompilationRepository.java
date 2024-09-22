package kz.orbitalis.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import kz.orbitalis.model.Compilation;

import java.util.List;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
    @Query("SELECT c " +
            "FROM Compilation c " +
            "WHERE c.pinned = :pinned")
    List<Compilation> findAllByPinned(boolean pinned, Pageable pageable);
}