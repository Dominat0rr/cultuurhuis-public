package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultGenreService implements GenreService {
    private final GenreRepository genreRepository;

    DefaultGenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return genreRepository.findById(id);
    }
}
