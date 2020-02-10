package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.domain.Voorstelling;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/insertGenre.sql")
@Sql("/insertVoorstelling.sql")
public class VoorstellingRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String VOORSTELLINGEN = "voorstellingen";
    @Autowired
    private VoorstellingRepository voorstellingRepository;
    private Genre genre;
    private Voorstelling voorstelling;

    @Before
    public void before() {
        genre = new Genre("test1");
        voorstelling = new Voorstelling("test1", "test1", LocalDateTime.now(), genre, BigDecimal.ONE, 10, 1);
    }

    private long idVanTestVoorstelling() {
        return super.jdbcTemplate.queryForObject("select id from voorstellingen where titel = 'test1'", Long.class);
    }

    @Test
    public void findById() {
        Optional<Voorstelling> optionalVoorstelling = voorstellingRepository.findById(idVanTestVoorstelling());
        assertThat(optionalVoorstelling.get().getTitel()).isEqualTo("test1");
    }

    @Test
    public void update() {
        Optional<Voorstelling> optionalVoorstelling = voorstellingRepository.findById(idVanTestVoorstelling());
        optionalVoorstelling.get().setVrijeplaatsen(5);
        voorstellingRepository.save(optionalVoorstelling.get());

        Optional<Voorstelling> optionalVoorstelling1 = voorstellingRepository.findById(idVanTestVoorstelling());
        assertThat(optionalVoorstelling1.get().getVrijeplaatsen()).isEqualTo(5);
    }
}
