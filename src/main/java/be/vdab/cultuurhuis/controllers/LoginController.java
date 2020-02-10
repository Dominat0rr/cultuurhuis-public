package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.services.KlantService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping("login")
class LoginController {
    private final KlantService klantService;

    LoginController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping
    public ModelAndView login() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView modelAndView = new ModelAndView("login");
        if (username != null && !username.isEmpty()) {
            //modelAndView.addObject("username", username);
            klantService.findByGebruikersnaam(username).ifPresent(klant -> {
                modelAndView.addObject(klant);
            });
        }
        return modelAndView;
    }
}
