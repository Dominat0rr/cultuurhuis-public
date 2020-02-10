package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Adres;
import be.vdab.cultuurhuis.domain.Klant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertKlant.sql")
public class KlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String KLANTEN = "klanten";
    @Autowired
    private KlantRepository klantRepository;
    private Klant klant;

    @Before
    public void before() {
        klant = new Klant("test", "test", new Adres("test", "test", "test", "test"),
                "test", "test");
    }

    private long idVanTestKlant() {
        return super.jdbcTemplate.queryForObject("select id from klanten where voornaam = 'test'", Long.class);
    }

    private String gebruikersnaamVanTestKlant() {
        return super.jdbcTemplate.queryForObject("select gebruikersnaam from klanten where voornaam = 'test'", String.class);
    }

    @Test
    public void findById() {
        Optional<Klant> optionalKlant = klantRepository.findById(idVanTestKlant());
        assertThat(optionalKlant.get().getVoornaam()).isEqualTo("test");
    }

    @Test
    public void findByGebruikersnaam() {
        Optional<Klant> optionalKlant = klantRepository.findByGebruikersnaam(gebruikersnaamVanTestKlant());
        assertThat(optionalKlant.get().getGebruikersnaam()).isEqualTo("test");
    }

    @Test
    public void create() {
        int aantalKlanten = super.countRowsInTable(KLANTEN);
        Klant klantNieuw = new Klant("testk", "testk", new Adres("test", "test", "test", "test"),
                "testk", "testk");
        klantRepository.save(klantNieuw);
        assertEquals(aantalKlanten + 1, super.countRowsInTable(KLANTEN));
    }
}
