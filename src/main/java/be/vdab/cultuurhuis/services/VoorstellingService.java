package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface VoorstellingService {
    //List<Voorstelling> findAll();
    Page<Voorstelling> findAll(Pageable pageable);
    Optional<Voorstelling> findById(long id);
    void update(Voorstelling voorstelling);
}
