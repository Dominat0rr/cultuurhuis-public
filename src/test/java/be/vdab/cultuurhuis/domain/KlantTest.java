package be.vdab.cultuurhuis.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class KlantTest {
    private Klant klant1;
    private Klant klant1Dubbel;
    private Klant klant2;

    @Before
    public void before() {
        klant1 = new Klant("test1", "test1", new Adres("test", "test", "test", "test"), "test1", "test1");
        klant1Dubbel = new Klant("test1", "test1", new Adres("test", "test", "test", "test"), "test1", "test1");
        klant2 = new Klant("test2", "test2", new Adres("test", "test", "test", "test"), "test2", "test2");
    }

    @Test
    public void eenNullAdresInDeConstructorMislukt() {
        assertThatNullPointerException().isThrownBy(()-> new Klant("test1", "test1", null, "test1", "test1"));
    }

    @Test
    public void klantenZijnGelijkAlsHunGebruikersnaamGelijkZijn() {
        assertThat(klant1).isEqualTo(klant1Dubbel);
    }

    @Test
    public void klantenZijnVerschillendAlsHunGebruikersnaamVerschillendZijn() {
        assertThat(klant1).isNotEqualTo(klant2);
    }

    @Test
    public void eenKlantVerschiltVanNull() {
        assertThat(klant1).isNotEqualTo(null);
    }

    @Test
    public void eenKlantVerschiltVanEenAnderTypeObject() {
        assertThat(klant1).isNotEqualTo("");
    }

    @Test
    public void gelijkeKlantenHebbenDezelfdeHashCode() {
        assertThat(klant1).hasSameHashCodeAs(klant1Dubbel);
    }

    @Test
    public void verschillendeKlantenHebbenVerschillendeHashCode() {
        assertThat(klant1.hashCode()).isNotEqualTo(klant2.hashCode());
    }
}
