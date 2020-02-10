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

public class ReservatieTest {
    private Klant klant;
    private Genre genre;
    private Voorstelling voorstelling1;
    private Voorstelling voorstelling2;
    private Reservatie reservatie1;
    private Reservatie reservatie1Dubbel;
    private Reservatie reservatie2;

    @Before
    public void before () {
        klant = new Klant("test1", "test1", new Adres("test", "test", "test", "test"), "test1", "test1");
        genre = new Genre("test1");
        voorstelling1 = new Voorstelling("test1", "test1", LocalDateTime.now(), genre, BigDecimal.ONE, 10, 1);
        voorstelling2 = new Voorstelling("test2", "test2", LocalDateTime.now(), genre, BigDecimal.TEN, 10, 1);
        reservatie1 = new Reservatie(klant, voorstelling1, 5);
        reservatie1Dubbel = new Reservatie(klant, voorstelling1, 5);
        reservatie2 = new Reservatie(klant, voorstelling2, 100);
    }

    @Test
    public void eenNullKlantInDeConstructorMislukt() {
        assertThatNullPointerException().isThrownBy(()-> new Reservatie(null, voorstelling1, 5));
    }

    @Test
    public void eenNullVoorstellingInDeConstructorMislukt() {
        assertThatNullPointerException().isThrownBy(()-> new Reservatie(klant, null, 5));
    }

    @Test
    public void ReservatiesZijnGelijkAlsHunKlantEnVoorstellingGelijkZijn() {
        assertThat(reservatie1).isEqualTo(reservatie1Dubbel);
    }

    @Test
    public void ReservatiesZijnVerschillendAlsHunKlantEnVoorstellingVerschillendZijn() {
        assertThat(reservatie1).isNotEqualTo(reservatie2);
    }

    @Test
    public void eenReservatieVerschiltVanNull() {
        assertThat(reservatie1).isNotEqualTo(null);
    }

    @Test
    public void eenReservatieVerschiltVanEenAnderTypeObject() {
        assertThat(reservatie1).isNotEqualTo("");
    }

    @Test
    public void gelijkeReservatiesHebbenDezelfdeHashCode() {
        assertThat(reservatie1).hasSameHashCodeAs(reservatie1Dubbel);
    }

    @Test
    public void verschillendeReservatiesHebbenNietDezelfdeHashCode() {
        assertThat(reservatie1.hashCode()).isNotEqualTo(reservatie2.hashCode());
    }
}
