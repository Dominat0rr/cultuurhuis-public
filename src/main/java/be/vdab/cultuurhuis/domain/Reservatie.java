package be.vdab.cultuurhuis.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Entity
@Table(name = "reservaties")
public class Reservatie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "klantid")
    private Klant klant;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voorstellingid")
    private Voorstelling voorstelling;
    @NotNull
    private int plaatsen;

    protected Reservatie() { }

    public Reservatie(Klant klant, Voorstelling voorstelling, int plaatsen) throws NullPointerException {
        if (klant == null || voorstelling == null) throw new NullPointerException();
        this.klant = klant;
        this.voorstelling = voorstelling;
        this.plaatsen = plaatsen;
    }

    public long getId() {
        return id;
    }

    public Klant getKlantid() {
        return klant;
    }

    public Voorstelling getVoorstellingid() {
        return voorstelling;
    }

    public int getPlaatsen() {
        return plaatsen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservatie)) return false;
        Reservatie that = (Reservatie) o;
        return Objects.equals(klant, that.klant) &&
                Objects.equals(voorstelling, that.voorstelling);
    }

    @Override
    public int hashCode() {
        return Objects.hash(klant, voorstelling);
    }
}
