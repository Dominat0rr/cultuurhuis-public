package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Voorstelling;
import be.vdab.cultuurhuis.services.VoorstellingService;
import be.vdab.cultuurhuis.session.Mandje;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final VoorstellingService voorstellingService;
    private Map<Voorstelling, Integer> voorstellingen = new LinkedHashMap<>();

    public MandjeController(Mandje mandje, VoorstellingService voorstellingService) {
        this.mandje = mandje;
        this.voorstellingService = voorstellingService;
    }

    @NumberFormat(pattern = "0.00")
    private BigDecimal getTotaalPrijs() {
        BigDecimal prijs = BigDecimal.ZERO;

        for (Map.Entry<Voorstelling, Integer> entry : voorstellingen.entrySet()) {
            Voorstelling key = entry.getKey();
            Integer value = entry.getValue();

            BigDecimal prijsPerSoort = key.getPrijs().multiply(BigDecimal.valueOf(value));
            prijs = prijs.add(prijsPerSoort);
        }
        return prijs;
    }

    @GetMapping
    public ModelAndView mandje() {
        ModelAndView modelAndView = new ModelAndView("mandje");

        if (mandje.isLeeg()) return modelAndView;
        voorstellingen.clear();

        mandje.getVoorstellingen().forEach((id, aantal) -> {
            voorstellingen.put(voorstellingService.findById(id).get(), aantal);
        });

        voorstellingen.forEach((voorstelling, aantal) -> System.out.println(voorstelling.getId() + " " + voorstelling.getPrijs()));
        modelAndView.addObject("voorstellingen", voorstellingen);
        modelAndView.addObject("totaalPrijs", getTotaalPrijs());
        return modelAndView;
    }

    @PostMapping("verwijderen")
    String verwijderen(@RequestParam("voorstelling_verwijder") Optional<List<String>> optionalVoorstellingId, RedirectAttributes redirect) {
        if (optionalVoorstellingId.isPresent()) {
            List<String> idVoorstelling = optionalVoorstellingId.get();

            if (idVoorstelling != null){
                for (String id : idVoorstelling) {
                    long voorstellingId = Long.parseLong(id);
                    mandje.verwijder(voorstellingId);
                }
            }
        }
        return "redirect:/mandje";
    }
}
