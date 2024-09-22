package kz.orbitalis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import kz.orbitalis.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("SELECT l " +
            "FROM Location l " +
            "WHERE l.lat = :lat " +
            "AND l.lon = :lon")
    Location findByLatAndLon(Double lat, Double lon);
}