package be.vdab.cultuurhuis.services;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.repositories.VoorstellingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class DefaultVoorstellingService implements VoorstellingService {
    private final VoorstellingRepository voorstellingRepository;

    DefaultVoorstellingService(VoorstellingRepository voorstellingRepository) {
        this.voorstellingRepository = voorstellingRepository;
    }

    @Override
    public Page<Voorstelling> findAll(Pageable pageable) {
        return voorstellingRepository.findAll(pageable);
    }

    @Override
    public Optional<Voorstelling> findById(long id) {
        return voorstellingRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void update(Voorstelling voorstelling) {
        voorstellingRepository.save(voorstelling);
    }
}
