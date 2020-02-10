package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Voorstelling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface VoorstellingRepository extends JpaRepository<Voorstelling, Long> {
    @Override
    Page<Voorstelling> findAll(Pageable pageable);
}
