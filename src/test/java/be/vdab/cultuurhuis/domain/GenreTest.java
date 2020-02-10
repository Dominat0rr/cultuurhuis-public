package be.vdab.cultuurhuis.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class GenreTest {
    private Genre genre1;
    private Genre genre1Dubbel;
    private Genre genre2;
    private Voorstelling voorstelling1;

    @Before
    public void before() {
        genre1 = new Genre("test1");
        genre1Dubbel = new Genre("test1");
        genre2 = new Genre("test2");
        voorstelling1 = new Voorstelling("test1", "test1", LocalDateTime.now(), genre1, BigDecimal.ONE, 10, 1);
    }

    @Test
    public void genre1IsHetGenreVanVoorstelling1() {
        assertThat(voorstelling1.getGenre()).isEqualTo(genre1);
        assertThat(genre1.getVoorstellingen()).containsOnly(voorstelling1);
    }

    @Test
    public void voorstelling1VerhuistNaarGenre2() {
        assertThat(genre2.add(voorstelling1)).isTrue();
        assertThat(genre2.getVoorstellingen()).containsOnly(voorstelling1);
        assertThat(voorstelling1.getGenre()).isEqualTo(genre2);
    }

    @Test
    public void eenNullVoorstellingToevoegenMislukt() {
        assertThatNullPointerException().isThrownBy(() -> genre1.add(null));
    }

    @Test
    public void genresZijnGelijkAlsHunNaamGelijkZijn() {
        assertThat(genre1).isEqualTo(genre1Dubbel);
    }

    @Test
    public void genresZijnVerschillendAlsHunNamenVerschillendZijn() {
        assertThat(genre1).isNotEqualTo(genre2);
    }

    @Test
    public void eenGenreVerschiltVanNull() {
        assertThat(genre1).isNotEqualTo(null);
    }

    @Test
    public void eenGenreVerschiltVanEnAnderTypeObject() {
        assertThat(genre1).isNotEqualTo("");
    }

    @Test
    public void gelijkeGenresHebbenDezelfdeHashCode() {
        assertThat(genre1).hasSameHashCodeAs(genre1);
    }
}
