package be.vdab.cultuurhuis.forms;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

public class BestelForm {
    @NotNull()
    @Positive()
    @Range(min = 1, max = 9999, message = "Aantal moet tussen 1 en 9999 zijn")
    private Integer aantal;

    public BestelForm(Integer aantal) {
        this.aantal = aantal;
    }

    public Integer getAantal() {
        return aantal;
    }
}
