package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.session.Mandje;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@ControllerAdvice
class MyControllerAdvice {
    private final Mandje mandje;

    MyControllerAdvice(Mandje mandje) {
        this.mandje = mandje;
    }

    @ModelAttribute
    void extraDataToevoegenAanModel(Model model) {
        model.addAttribute(mandje);
    }
}
