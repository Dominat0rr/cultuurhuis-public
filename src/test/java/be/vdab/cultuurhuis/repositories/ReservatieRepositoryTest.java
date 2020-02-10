package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertGenre.sql")
@Sql("/insertVoorstelling.sql")
@Sql("/insertKlant.sql")
@Sql("/insertReservatie.sql")
public class ReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String RESERVATIES = "reservaties";
    @Autowired
    private ReservatieRepository reservatieRepository;
    private Genre genre;
    private Voorstelling voorstelling;
    private Klant klant;
    private Reservatie reservatie;

    @Before
    public void before() {
        genre = new Genre("test1");
        voorstelling = new Voorstelling("test1", "test1", LocalDateTime.now(), genre, BigDecimal.ONE, 10, 1);
        klant = new Klant("test", "test", new Adres("test", "test", "test", "test"),
                "test", "test");
        reservatie = new Reservatie(klant, voorstelling, 5);
    }

    @Test
    public void create() {
        int aantalReservaties = super.countRowsInTable(RESERVATIES);
        Reservatie reservatieNieuw = new Reservatie(klant, voorstelling, 5);
        reservatieRepository.save(reservatieNieuw);
        assertEquals(aantalReservaties + 1, super.countRowsInTable(RESERVATIES));
    }
}
