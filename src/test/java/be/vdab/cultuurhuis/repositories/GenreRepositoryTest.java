package be.vdab.cultuurhuis.repositories;

import be.vdab.cultuurhuis.domain.Genre;
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

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/insertGenre.sql")
public class GenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GENRES = "genres";
    @Autowired
    private GenreRepository genreRepository;
    private Genre genre;

    @Before
    public void before() {
        genre = new Genre("test1");
    }

    private long idVanTestGenre() {
        return super.jdbcTemplate.queryForObject("select id from genres where naam = 'test1'", Long.class);
    }

    @Test
    public void findById() {
        Optional<Genre> optionalGenre = genreRepository.findById(idVanTestGenre());
        assertThat(optionalGenre.get().getNaam()).isEqualTo("test1");
    }
}
