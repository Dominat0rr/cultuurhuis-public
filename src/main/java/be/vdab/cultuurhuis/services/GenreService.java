package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Genre;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface GenreService {
    List<Genre> findAll();
    Optional<Genre> findById(long id);
}
