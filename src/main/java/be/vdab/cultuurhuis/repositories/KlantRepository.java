package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Klant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface KlantRepository extends JpaRepository<Klant, Long> {
    Optional<Klant> findByGebruikersnaam(String gebruikersnaam);
}
