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

public class VoorstellingTest {
    private LocalDateTime date;
    private Voorstelling voorstelling1;
    private Voorstelling voorstelling1Dubbel;
    private Voorstelling voorstelling2;
    private Genre genre1;
    private Genre genre2;

    @Before
    public void before() {
        genre1 = new Genre("test1");
        genre2 = new Genre("test2");
        date = LocalDateTime.now();
        voorstelling1 = new Voorstelling("test1", "test1", date, genre1, BigDecimal.ONE, 10, 1);
        voorstelling1Dubbel = new Voorstelling("test1", "test1", date, genre1, BigDecimal.ONE, 10, 1);
        voorstelling2 = new Voorstelling("test2", "test2", date, genre1, BigDecimal.ONE, 5, 1);
    }

    @Test
    public void eenNullGenreInDeConstructorMislukt() {
        assertThatNullPointerException().isThrownBy(()-> new Voorstelling("test1", "test1", date, null, BigDecimal.ONE, 10, 1));
    }

    @Test
    public void voorstellingenZijnGelijkAlsHunTitelDatumEnGenreGelijkZijn() {
        assertThat(voorstelling1).isEqualTo(voorstelling1Dubbel);
    }

    @Test
    public void voorstellingenZijnVerschillendAlsHunTitelDatumEnGenreVerschillendZijn() {
        assertThat(voorstelling1).isNotEqualTo(voorstelling2);
    }

    @Test
    public void eenVoorstellingVerschiltVanNull() {
        assertThat(voorstelling1).isNotEqualTo(null);
    }

    @Test
    public void eenVoorstellingVerschiltVanEenAnderObject() {
        assertThat(voorstelling1).isNotEqualTo("");
    }

    @Test
    public void gelijkeVoorstellingenHebbenDezelfdeHashCode() {
        assertThat(voorstelling1).hasSameHashCodeAs(voorstelling1Dubbel);
    }

    @Test
    public void meerdereVoorstellingenKunndenTotHetzelfdeGenreHoren() {
        assertThat(genre1.getVoorstellingen()).containsOnly(voorstelling1, voorstelling2);
    }

    @Test
    public void voorstelling1KomtVoortInGenre1() {
        assertThat(voorstelling1.getGenre()).isEqualTo(genre1);
        assertThat(genre1.getVoorstellingen()).contains(voorstelling1);
    }

    @Test
    public void voorstelling1VerhuistNaarGenre2() {
        voorstelling1.setGenre(genre2);
        assertThat(voorstelling1.getGenre()).isEqualTo(genre2);
        assertThat(genre1.getVoorstellingen().isEmpty());
        assertThat(genre2.getVoorstellingen()).contains(voorstelling1);
    }

    @Test
    public void eenNullGenreInDeSetterMislukt() {
        assertThatNullPointerException().isThrownBy(() -> voorstelling1.setGenre(null));
    }

    @Test
    public void isValidAantalVrijePlaatsen() {
        assertThat(voorstelling1.isAantalVrijePlaatsenValid(9));
    }

    @Test
    public void isNotValidAantalVrijePlaatsen() {
        assertThat(!voorstelling1.isAantalVrijePlaatsenValid(11));
    }
}
