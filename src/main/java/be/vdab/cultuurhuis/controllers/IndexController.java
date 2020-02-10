package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@Controller
@RequestMapping("/")
@SessionAttributes("mandje")
public class IndexController {
    private final GenreService genreService;

    IndexController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("genres", genreService.findAll());
        return modelAndView;
    }

    @GetMapping("genres/{id}")
    public ModelAndView genre(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("genre");
        modelAndView.addObject("genres", genreService.findAll());
        modelAndView.addObject("genre");
        genreService.findById(id).ifPresent(genre -> {
            modelAndView.addObject(genre);
            modelAndView.addObject("voorstellingen", genre.getVoorstellingen());
        });
        return modelAndView;
    }
}
