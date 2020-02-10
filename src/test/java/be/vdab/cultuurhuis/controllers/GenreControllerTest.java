package be.vdab.cultuurhuis.controllers;

import be.vdab.cultuurhuis.domain.Genre;
import be.vdab.cultuurhuis.services.GenreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @version 1.0
 * @author Dominik Claerman
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class GenreControllerTest {
    private GenreController genreController;
    @Mock
    private GenreService genreService;

    @Before
    public void before() {
        genreController = new GenreController(genreService);
    }

    // genres
    @Test
    public void genresGebruiktDeThymeleafPaginaGenres() {
        assertThat(genreController.genres().getViewName()).isEqualTo("genres");
    }

    @Test
    public void genresnGeeftGenresDoorAanDeThymeleafPagina() {
        assertThat(genreController.genres().getModel().get("genres")).isInstanceOf(List.class);
    }
}
