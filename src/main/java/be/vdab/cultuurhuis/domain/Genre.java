package be.vdab.cultuurhuis.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Entity
@Table(name = "genres")
public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String naam;
    @OneToMany(mappedBy = "genre")
    @OrderBy("titel")
    private Set<Voorstelling> voorstellingen;

    protected Genre() { }

    public Genre(@NotBlank String naam) {
        this.naam = naam;
        this.voorstellingen = new LinkedHashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Voorstelling> getVoorstellingen() {
        return Collections.unmodifiableSet(voorstellingen);
    }

    public boolean add(Voorstelling voorstelling) {
        boolean toegevoegd = voorstellingen.add(voorstelling);
        Genre oudGenre = voorstelling.getGenre();

        if (oudGenre != null && oudGenre != this) oudGenre.voorstellingen.remove(voorstelling);
        if (this != oudGenre) voorstelling.setGenre(this);

        return toegevoegd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return Objects.equals(naam.toLowerCase(), genre.naam.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam.toLowerCase());
    }
}
