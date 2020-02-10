package be.vdab.cultuurhuis.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Entity
@Table(name = "klanten")
public class Klant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String voornaam;
    @NotBlank
    private String familienaam;
    @Valid
    @Embedded
    private Adres adres;
    @NotBlank
    private String gebruikersnaam;
    @NotBlank
    private String paswoord;

    protected Klant() { }

    public Klant(@NotBlank String voornaam, @NotBlank String familienaam, @Valid Adres adres, @NotBlank String gebruikersnaam, @NotBlank String paswoord) throws NullPointerException {
        if (adres == null) throw new NullPointerException("Adres kan niet null zijn");
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.adres = adres;
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = "{bcrypt}" + paswoord; // "{bcrypt}"
    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Adres getAdres() {
        return adres;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Klant)) return false;
        Klant klant = (Klant) o;
        return Objects.equals(gebruikersnaam, klant.gebruikersnaam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gebruikersnaam);
    }
}
