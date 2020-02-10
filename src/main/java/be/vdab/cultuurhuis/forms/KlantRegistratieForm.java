package be.vdab.cultuurhuis.forms;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class KlantRegistratieForm {
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    //@Pattern(regexp = "^[a-zA-Z\\s]+", message="Geen geldige voornaam!")
    //@Pattern(regexp = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){0,24}$", message="Geen geldige voornaam!")
    @Pattern(regexp = "^(\\p{IsAlphabetic}|-)+", message="Geen geldige voornaam!")
    private final String voornaam;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^(\\p{IsAlphabetic}|-)+", message="Geen geldige familienaam!")
    private final String familienaam;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^(\\p{IsAlphabetic}|-)+", message="Geen geldige straatnaam!")
    private final String straat;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    //@Pattern(regexp = "^[0-9]+[a-zA-Z\\s]+", message="Geen geldig huisnummer!")
    private final String huisnr;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^(?:(?:[1-9])(?:\\d{3}))$", message="Geen geldige postcode!")
    private final String postcode;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    //@Pattern(regexp = "^[a-zA-Z\\s]+", message="Geen geldige gemeente!")
    private final String gemeente;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^[A-Za-z0-9]+(?:[ _-][A-Za-z0-9]+)*$", message="Geen geldige gebruikersnaam!")
    @Pattern(regexp = "^.{6,}$", message="Gebruikersnaam moet minstens 6 tekens lang zijn!")
    private final String gebruikersnaam;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^.{6,}$", message="Paswoord moet minstens 6 tekens lang zijn!")
    private String paswoord;
    @NotBlank
    @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
    @Pattern(regexp = "^.{6,}$", message="Paswoord moet minstens 6 tekens lang zijn!")
    private String herhaalPaswoord;

    public KlantRegistratieForm(String voornaam, String familienaam, String straat, String huisnr, String postcode, String gemeente,
                                String gebruikersnaam, String paswoord, String herhaalPaswoord) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.straat = straat;
        this.huisnr = huisnr;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.gebruikersnaam = gebruikersnaam;
        this.paswoord = paswoord;
        this.herhaalPaswoord = herhaalPaswoord;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnr() {
        return huisnr;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getPaswoord() {
        return paswoord;
    }

    public String getHerhaalPaswoord() {
        return herhaalPaswoord;
    }

    public boolean isValidPaswoord(String paswoord, String herhaalPaswoord) {
        return paswoord.equals(herhaalPaswoord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KlantRegistratieForm)) return false;
        KlantRegistratieForm that = (KlantRegistratieForm) o;
        return Objects.equals(gebruikersnaam.toLowerCase(), that.gebruikersnaam.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gebruikersnaam.toLowerCase());
    }
}
