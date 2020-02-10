package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Klant;
import be.vdab.cultuurhuis.domain.Reservatie;
import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.services.KlantService;
import be.vdab.cultuurhuis.services.ReservatieService;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.session.Mandje;
import be.vdab.cultuurhuis.utils.VrijePlaatsenError;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping("reservatie")
public class ReservatieController {
    private final ReservatieService reservatieService;
    private final KlantService klantService;
    private final VoorstellingService voorstellingService;
    private final Mandje mandje;

    public ReservatieController(ReservatieService reservatieService, VoorstellingService voorstellingService,
                                KlantService klantService, Mandje mandje) {
        this.reservatieService = reservatieService;
        this.voorstellingService = voorstellingService;
        this.klantService = klantService;
        this.mandje = mandje;
    }

    @GetMapping("toevoegen")
    ModelAndView toevoegen() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ModelAndView modelAndView = new ModelAndView("bestellingbevestigen");
        if (username != null && !username.isEmpty()) {
            klantService.findByGebruikersnaam(username).ifPresent(klant -> {
                modelAndView.addObject(klant);
            });
        }
        return modelAndView;
    }

    @PostMapping("bevestigen")
    ModelAndView create(SessionStatus session, HttpSession httpSession, HttpServletRequest request) {
        if (mandje.isLeeg()) return new ModelAndView("redirect:/");

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Klant klant = klantService.findByGebruikersnaam(username).get();

        Map<Voorstelling, Integer> gelukteVoorstellingen = new LinkedHashMap<>();
        Map<Voorstelling, VrijePlaatsenError> mislukteVoorstellingen = new LinkedHashMap<>();
        mandje.getVoorstellingen().forEach((id, aantal) -> {
          Voorstelling voorstelling = voorstellingService.findById(id).get();

          if (voorstelling.isAantalVrijePlaatsenValid(aantal)) {
              voorstelling.setVrijeplaatsen(voorstelling.getVrijeplaatsen() - aantal);
              voorstellingService.update(voorstelling);
              gelukteVoorstellingen.put(voorstelling, aantal);
              reservatieService.create(new Reservatie(klant, voorstelling, aantal));
          }
          else {
              VrijePlaatsenError vrijePlaatsen = new VrijePlaatsenError();
              vrijePlaatsen.put(aantal, voorstelling.getVrijeplaatsen());
              VrijePlaatsenError put = mislukteVoorstellingen.put(voorstelling, vrijePlaatsen);
          }
        });

        mandje.maakLeeg();
        request.getSession().invalidate();
        int mapSize = mislukteVoorstellingen.size();
        ModelAndView modelAndView = new ModelAndView("reservatie")
                .addObject("mapSize", mapSize)
                .addObject("mislukteVoorstellingen", mislukteVoorstellingen)
                .addObject("gelukteVoorstellingen", gelukteVoorstellingen);
        return modelAndView;
    }
}
