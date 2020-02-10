package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Reservatie;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface ReservatieRepository extends JpaRepository<Reservatie, Long> {
}
