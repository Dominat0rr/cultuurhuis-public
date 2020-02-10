package be.vdab.cultuurhuis.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Entity
@Table(name = "voorstellingen")
public class Voorstelling implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String titel;
    @NotBlank
    private String uitvoerders;
    @NotNull
    @DateTimeFormat(style = "SS")
    private LocalDateTime datum;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "genreid")
    private Genre genre;
    @NotNull
    private BigDecimal prijs;
    @NotNull
    private int vrijeplaatsen;
    @Version
    private long versie;

    protected Voorstelling() { }

    public Voorstelling(@NotBlank String titel, @NotBlank String uitvoerders, @NotNull LocalDateTime datum, Genre genre, @NotNull BigDecimal prijs, @NotNull int vrijeplaatsen, long versie) {
        this.titel = titel;
        this.uitvoerders = uitvoerders;
        this.datum = datum;
        setGenre(genre);
        this.prijs = prijs;
        this.vrijeplaatsen = vrijeplaatsen;
        this.versie = versie;
    }

    public long getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getUitvoerders() {
        return uitvoerders;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public Genre getGenre() {
        return genre;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getVrijeplaatsen() {
        return vrijeplaatsen;
    }

    public void setGenre(Genre genre) throws NullPointerException {
        if (!genre.getVoorstellingen().contains(this)) genre.add(this);
        this.genre = genre;
    }

    public void setVrijeplaatsen(int vrijeplaatsen) {
        this.vrijeplaatsen = vrijeplaatsen;
    }

    public void verminderVrijePlaatsen(int aantal) throws IllegalArgumentException {
        if (vrijeplaatsen - aantal < 0) throw new IllegalArgumentException("Ongeldig aantal vrije plaatsen");
        vrijeplaatsen = vrijeplaatsen - aantal;
    }

    public boolean isAantalVrijePlaatsenValid(int aantal) {
        return aantal > 0 && aantal <= vrijeplaatsen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voorstelling)) return false;
        Voorstelling that = (Voorstelling) o;
        return Objects.equals(titel.toLowerCase(), that.titel.toLowerCase()) &&
                Objects.equals(datum, that.datum) &&
                Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titel.toLowerCase(), datum, genre);
    }
}
