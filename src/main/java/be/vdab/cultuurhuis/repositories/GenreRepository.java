package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
