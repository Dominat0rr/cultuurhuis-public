package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.forms.BestelForm;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.session.Mandje;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping("voorstellingen")
public class VoorstellingController {
    private final VoorstellingService voorstellingService;
    private final Mandje mandje;

    public VoorstellingController(VoorstellingService voorstellingService, Mandje mandje) {
        this.voorstellingService = voorstellingService;
        this.mandje = mandje;
    }

    @GetMapping
    public ModelAndView voorstellingen(Pageable pageable) {
        return new ModelAndView("voorstellingen", "page", voorstellingService.findAll(pageable));
    }

    @GetMapping("{id}")
    ModelAndView voorstelling(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("voorstelling");
        voorstellingService.findById(id).ifPresent(voorstelling -> {
            modelAndView.addObject(voorstelling);
            Optional<Integer> optionalAantal = mandje.getAantal(id);
            BestelForm form = new BestelForm(optionalAantal.isPresent() ? optionalAantal.get() : null);
            modelAndView.addObject(form);
        });
        return modelAndView;
    }

    @PostMapping("{id}/bestellen")
    public ModelAndView bestellen(@PathVariable long id, @Valid BestelForm bestelForm, Errors errors) {
        if (errors.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("voorstelling");
            voorstellingService.findById(id).ifPresent(voorstelling -> modelAndView.addObject(voorstelling));
            return modelAndView;
        }
        else {
            ModelAndView modelAndViewError = new ModelAndView("voorstelling");
            voorstellingService.findById(id).ifPresent(voorstellingObj -> modelAndViewError.addObject(voorstellingObj));
            Voorstelling voorstelling = voorstellingService.findById(id).get();

            if (voorstelling.getVrijeplaatsen() == 0) {
                return modelAndViewError.addObject("foutboodschap", "Er zijn geen vrije plaatsen meer voor deze voorstelling");
            } else if (voorstelling.getVrijeplaatsen() < bestelForm.getAantal()) {
                return modelAndViewError.addObject("foutboodschap", "Aantal is ongeldig t.o.v. vrije plaatsen");
            }

            mandje.voegToe(id, bestelForm.getAantal());
            return new ModelAndView("redirect:/mandje");
        }
    }
}
