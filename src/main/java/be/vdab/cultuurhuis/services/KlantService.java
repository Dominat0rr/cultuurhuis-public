package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Klant;

import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public interface KlantService {
    void create(Klant klant);
    Optional<Klant> findById(long id);
    Optional<Klant> findByGebruikersnaam(String gebruikersnaam);
}
